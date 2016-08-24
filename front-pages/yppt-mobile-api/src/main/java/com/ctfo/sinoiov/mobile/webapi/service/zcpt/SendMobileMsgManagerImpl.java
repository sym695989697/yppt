package com.ctfo.sinoiov.mobile.webapi.service.zcpt;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.SendMobileMsgReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.zcpt.SendMobileMsgRsp;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.PayError;
import com.ctfo.sinoiov.mobile.webapi.util.MD5Utils;
import com.ctfo.util.EnvironmentUtil;

/**
 * 发送短信
 * 
 * @author sunchuanfu
 */
@Service("sendMobileMsgServiceImpl")
public class SendMobileMsgManagerImpl implements IJsonService {
	protected static final Log logger = LogFactory.getLog(SendMobileMsgManagerImpl.class);

	private static EnvironmentUtil resourceInstance = EnvironmentUtil.getInstance();

	// MobileAPI接入短信平台用户名
	private static String myMessagePlatformCode = resourceInstance.getPropertyValue("MESSAGE_PLATFORM_CODE");
	// 密码
	private static String myMessagePassword = resourceInstance.getPropertyValue("MESSAGE_PASSWORD");
	// 签名
	private static String myMessageSign = resourceInstance.getPropertyValue("MESSAGE_SIGN");

	@Override
	public Body execute(Parameter request, Object... obj) throws ClientException {
		SendMobileMsgRsp rsp = new SendMobileMsgRsp();
		SendMobileMsgReq req = null;
		String mobiles = null;
		try {
			req = (SendMobileMsgReq) request.getBody();
			mobiles = req.getMobiles();
			String content = req.getContent();

			// 格式：手机号码+_+密码+用户名+签名key
			String signValue = MD5Utils.getDefaultMd5String(mobiles + "_" + myMessagePassword + myMessagePlatformCode
					+ myMessageSign);
			logger.info(">>>>>>>>>>>给手机号：" + mobiles + "发送短信>>>>>>>>>");
			ImplementationSupport.getNotificationManager().sendMessage(mobiles, content, myMessagePlatformCode,
					myMessagePassword, signValue);
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>给手机号：" + mobiles + "发送短信失败>>>>>>>>>", e);
			throw new ClientException(">>>>>>>>>>>给手机号：" + mobiles + "发送短信失败>>>>>>>>>", e);
		}

		return rsp;
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if (request == null || request.getBody() == null) {
			throw new ClientException("E240004", PayError.E240004);
		}
		SendMobileMsgReq req = (SendMobileMsgReq) request.getBody();
		// 手机号
		if (StringUtils.isBlank(req.getMobiles())) {
			throw new ClientException("E000017", Common.E000017);
		}
		// 短信内容
		if (StringUtils.isBlank(req.getContent())) {
			throw new ClientException("E000019", Common.E000019);
		}
	}

	@Override
	public Parameter convertParameter(String request) {
		try {
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
			classMap.put("head", Head.class);
			classMap.put("body", SendMobileMsgReq.class);
			Parameter param = (Parameter) JSONObject.toBean(JSONObject.fromObject(request), Parameter.class, classMap);
			return param;
		} catch (Exception e) {
			throw new ClientException("E000005", Common.E000005);
		}
	}

}
