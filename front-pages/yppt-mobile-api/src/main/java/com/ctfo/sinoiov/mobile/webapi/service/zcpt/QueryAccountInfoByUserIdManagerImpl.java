package com.ctfo.sinoiov.mobile.webapi.service.zcpt;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.csm.uaa.dao.beans.UAAUser;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.QueryAccountInfoByUserIdReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.zcpt.QueryAccountInfoByUserIdRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.UppResult;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.PayError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.sinoiov.mobile.webapi.util.PayErrorCodesZFPT;

/**
 * 根据用户Id查询帐户
 * 
 * @author sunchuanfu
 */
@Service("queryAccountInfoByUserIdManagerImpl")
@SuppressWarnings("unchecked")
public class QueryAccountInfoByUserIdManagerImpl implements IJsonService {
	protected static final Log logger = LogFactory.getLog(QueryAccountInfoByUserIdManagerImpl.class);

	@Override
	public Body execute(Parameter request, Object... obj) throws ClientException {
		QueryAccountInfoByUserIdRsp rsp = new QueryAccountInfoByUserIdRsp();
		QueryAccountInfoByUserIdReq req = null;

		UppResult uppResult = null;
		try {
			req = (QueryAccountInfoByUserIdReq) request.getBody();
			Map<String, String> mpParas = null;

			// 查询帐户的基本信息
			mpParas = new HashMap<String, String>();
			mpParas.put("userId", req.getUserId());
			uppResult = PayManagerUtil.invokeUPP(mpParas, "UPP_QUERY_BY_USERID");
			if(uppResult == null || StringUtils.isBlank(uppResult.getResult())){
				return rsp;
			}
			if (UppResult.FAILURE.equals(uppResult.getResult())) {
				if (PayErrorCodesZFPT.ACCOUNT_NOT_EXIST.equals(uppResult.getErrorcode())) {
					// 如果账户不存在则返回空对象
					return rsp;
				} else {
					throw new ClientException(PayError.E240001, uppResult.getError());
				}
			}
			rsp = (QueryAccountInfoByUserIdRsp) JSONObject.toBean(JSONObject.fromObject(uppResult.getData()),
					QueryAccountInfoByUserIdRsp.class);

			// 查询帐户的累计收入和支出
			mpParas = new HashMap<String, String>();
			mpParas.put("accountNo", rsp.getAccountNo());
			// mpParas.put("startTime", );//mpParas.put("endTime", );
			// 这里时间不传，支付会默认查询所有时间
			uppResult = PayManagerUtil.invokeUPP(mpParas, "UPP_GET_SUM_ACCOUNT_BALANCE");
			if (uppResult.getResult().equals(UppResult.FAILURE)) {
				throw new ClientException(PayError.E240001, uppResult.getError());
			}
			Map<String, String> mp = (Map<String, String>) JSONObject.toBean(
					JSONObject.fromObject(uppResult.getData()), Map.class);
			rsp.setPaySumBalance(mp.get("paySumBalance"));
			rsp.setIncomeSumBalance(mp.get("incomeSumBalance"));

			// 查询帐户是否设置支付密码(这里传入参数同上)
			uppResult = PayManagerUtil.invokeUPP(mpParas, "UPP_IS_SET_PAYPASSWORD");
			if (uppResult.getResult().equals(UppResult.FAILURE)) {
				throw new ClientException(PayError.E240001, uppResult.getError());
			}
			mp = (Map<String, String>) JSONObject.toBean(JSONObject.fromObject(uppResult.getData()), Map.class);
			rsp.setIsSetPayPassword(mp.get("data").equals(UppResult.SUCCESS) ? PublicStaticParam.RESULT_SUCCESS
					: PublicStaticParam.RESULT_FAIL);
		} catch (ClientException e) {
			logger.error("根据用户Id查询帐户信息时报错！", e);
			throw e;
		} catch (Exception e) {
			logger.error("根据用户Id查询帐户信息时报错！", e);
			throw new ClientException(PayError.E240001, "根据用户Id查询帐户信息时报错！");
		}

		return rsp;
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if (request == null || request.getBody() == null) {
			throw new ClientException("E240004", PayError.E240004);
		}
		QueryAccountInfoByUserIdReq req = (QueryAccountInfoByUserIdReq) request.getBody();
		// 验证用户是否存在
		if (StringUtils.isBlank(req.getUserId())) {
			throw new ClientException("E240009", PayError.E240009);
		} else {
			UAAUser user = ImplementationSupport.getUserManager().queryUaaUserById(req.getUserId(), null);
			if (user == null) {
				throw new ClientException("E120011", UserError.E120011);
			}
		}
	}

	@Override
	public Parameter convertParameter(String request) {
		try {
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
			classMap.put("head", Head.class);
			classMap.put("body", QueryAccountInfoByUserIdReq.class);
			Parameter param = (Parameter) JSONObject.toBean(JSONObject.fromObject(request), Parameter.class, classMap);
			return param;
		} catch (Exception e) {
			throw new ClientException("E000005", Common.E000005);
		}
	}

}
