package com.ctfo.gatewayservice.service;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctfo.gatewayservice.interfaces.bean.EnumYpptGateWayErrorCodes;
import com.ctfo.gatewayservice.interfaces.bean.YpptGatewayException;
import com.ctfo.gatewayservice.interfaces.service.IMessageService;
import com.ctfo.gatewayservice.util.EnvironmentUtil;
import com.ctfo.gatewayservice.util.MD5Encoder;
import com.ctfo.notification.hessian.intf.INotificationService;

/**
 * 发送短信服务
 * 
 * @author sunchuanfu
 */
@Service("IMessageService")
public class MessageServiceImpl implements IMessageService {
	private static final Log logger = LogFactory.getLog(MessageServiceImpl.class);

	@Autowired
	private INotificationService iNotificationService;

	private static EnvironmentUtil resourceInstance = EnvironmentUtil.getInstance();

	// 油品接入短信平台用户名
	private static String myMessagePlatformCode = resourceInstance.getPropertyValue("MESSAGE_PLATFORM_CODE");
	// 密码
	private static String myMessagePassword = resourceInstance.getPropertyValue("MESSAGE_PASSWORD");
	// 签名
	private static String myMessageSign = resourceInstance.getPropertyValue("MESSAGE_SIGN");

	@Override
	public boolean sendMessage(String mobiles, String content) throws YpptGatewayException {
		try {
			if (StringUtils.isBlank(mobiles) || StringUtils.isBlank(content)) {
				throw new YpptGatewayException(EnumYpptGateWayErrorCodes.YPPT_PHONE_OR_MESSAGE_ERROR.getCode(),
						EnumYpptGateWayErrorCodes.YPPT_PHONE_OR_MESSAGE_ERROR.getMsg());
			}

			// 格式：手机号码+_+密码+用户名+签名key
			String signValue = MD5Encoder.getMD5String(mobiles + "_" + myMessagePassword + myMessagePlatformCode
					+ myMessageSign);
			logger.info(">>>>>>>>>>>给手机号：" + mobiles + "发送短信>>>>>>>>>");
			iNotificationService.sendMessage(mobiles, content, myMessagePlatformCode, myMessagePassword, signValue);
		} catch (YpptGatewayException e) {
			logger.error(">>>>>>>>>>>给手机号：" + mobiles + "发送短信失败>>>>>>>>>", e);
			throw e;
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>给手机号：" + mobiles + "发送短信失败>>>>>>>>>", e);
			throw new YpptGatewayException(EnumYpptGateWayErrorCodes.YPPT_SEND_PHONE_MESSAGE_ERROR.getCode(),
					EnumYpptGateWayErrorCodes.YPPT_SEND_PHONE_MESSAGE_ERROR.getMsg());
		}

		return true;
	}
}
