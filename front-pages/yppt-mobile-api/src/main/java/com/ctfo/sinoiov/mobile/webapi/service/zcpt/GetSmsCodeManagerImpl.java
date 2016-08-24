package com.ctfo.sinoiov.mobile.webapi.service.zcpt;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.GetSmsCodeReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.zcpt.GetSmsCodeRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.UppResult;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.CheckValue;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.PayError;
import com.ctfo.sinoiov.mobile.webapi.util.PayConstants;

/**
 * 发送短信验证码
 * 
 * @author sunchuanfu
 */
@Service("getSmsCodeManagerImpl")
public class GetSmsCodeManagerImpl implements IJsonService {
	protected static final Log logger = LogFactory.getLog(GetSmsCodeManagerImpl.class);

	@Override
	public Body execute(Parameter request, Object... obj) throws ClientException {
		GetSmsCodeRsp rsp = new GetSmsCodeRsp();
		GetSmsCodeReq req = null;
		UppResult uppResult = null;

		try {
			req = (GetSmsCodeReq) request.getBody();

			if (req.getIsCreateAccount().equals("0")) {
				// 如果是开户时需要帐户，此时没有传帐户号，这里传入商户代码
				req.setAccountNo(PayConstants.myMerchantCode);
			}

			Map<String, String> mpParas = new HashMap<String, String>();
			mpParas.put("accountNo", req.getAccountNo());
			mpParas.put("mobileNo", req.getMobileNo());
			uppResult = PayManagerUtil.invokeUPP(mpParas, "UPP_SMS_CODE");

			if (uppResult.getResult().equals(UppResult.FAILURE)) {
				throw new ClientException(PayError.E240001, uppResult.getError());
			}

			@SuppressWarnings("unchecked")
			Map<String, String> mp = (Map<String, String>) JSONObject.toBean(
					JSONObject.fromObject(uppResult.getData()), Map.class);

			rsp.setData(mp.get("data").equals(UppResult.SUCCESS) ? PublicStaticParam.RESULT_SUCCESS
					: PublicStaticParam.RESULT_FAIL);
		} catch (ClientException e) {
			logger.error("给帐户" + req.getAccountNo() + "手机号" + req.getMobileNo() + "发送短信验证码时报错！", e);
			throw e;
		} catch (Exception e) {
			logger.error("给帐户" + req.getAccountNo() + "手机号" + req.getMobileNo() + "发送短信验证码时报错！", e);
			throw new ClientException(PayError.E240001, "给帐户" + req.getAccountNo() + "手机号" + req.getMobileNo()
					+ "发送短信验证码时报错！");
		}

		return rsp;
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if (request == null || request.getBody() == null) {
			throw new ClientException("E240004", PayError.E240004);
		}
		GetSmsCodeReq req = (GetSmsCodeReq) request.getBody();
		// 帐号
		String isCreateAccount = req.getIsCreateAccount();
		if (StringUtils.isBlank(isCreateAccount) && isCreateAccount.equals("1")) {
			if (StringUtils.isBlank(req.getAccountNo())) {
				throw new ClientException("E240005", PayError.E240005);
			}
		}
		// 电话
		if (StringUtils.isBlank(req.getMobileNo())) {
			throw new ClientException("E000017", Common.E000017);
		} else if (!CheckValue.isPhone(req.getMobileNo())) {
			throw new ClientException("E000016", Common.E000016);
		}
	}

	@Override
	public Parameter convertParameter(String request) {
		try {
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
			classMap.put("head", Head.class);
			classMap.put("body", GetSmsCodeReq.class);
			Parameter param = (Parameter) JSONObject.toBean(JSONObject.fromObject(request), Parameter.class, classMap);
			return param;
		} catch (Exception e) {
			throw new ClientException("E000005", Common.E000005);
		}
	}

}
