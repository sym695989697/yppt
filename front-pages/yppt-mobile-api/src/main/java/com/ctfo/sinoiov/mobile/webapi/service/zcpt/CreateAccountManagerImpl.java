package com.ctfo.sinoiov.mobile.webapi.service.zcpt;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.CreateAccountReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.zcpt.CreateAccountRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.UppResult;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.PayError;

/**
 * 创建帐户
 * TODO 这个接口以后要去掉
 * 
 * @author sunchuanfu
 */
@Service("createAccountManagerImpl")
public class CreateAccountManagerImpl implements IJsonService {
	protected static final Log logger = LogFactory.getLog(CreateAccountManagerImpl.class);

	@Override
	public Body execute(Parameter request, Object... obj) throws ClientException {
		CreateAccountRsp rsp = new CreateAccountRsp();
		/*CreateAccountReq req = null;

		UppResult uppResult = null;
		try {
			req = (CreateAccountReq) request.getBody();
			// TODO
			req.setUserId(null);

			uppResult = PayManagerUtil.invokeUPP(req, "UPP_CREATE_ACCOUNT");

			if (uppResult.getResult().equals(UppResult.FAILURE)) {
				// 抛出支付平台的错误信息
				throw new ClientException(PayError.E240001, "创建用户" + req.getUserId() + "的支付帐户时报错！>>>>"
						+ uppResult.getError());
			}

			@SuppressWarnings("unchecked")
			Map<String, String> mp = (Map<String, String>) JSONObject.toBean(
					JSONObject.fromObject(uppResult.getData()), Map.class);

			rsp.setAccountNo(mp.get("accountNo"));
		} catch (ClientException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("创建用户" + req.getUserId() + "的支付帐户时报错！", e);
			throw new ClientException(PayError.E240001, "创建用户" + req.getUserId() + "的支付帐户时报错！");
		}*/

		return rsp;
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		// TODO
		System.out.println(request);
	}

	@Override
	public Parameter convertParameter(String request) {
		try {
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
			classMap.put("head", Head.class);
			classMap.put("body", CreateAccountReq.class);
			Parameter param = (Parameter) JSONObject.toBean(JSONObject.fromObject(request), Parameter.class, classMap);
			return param;
		} catch (Exception e) {
			throw new ClientException("E000005", Common.E000005);
		}
	}

}
