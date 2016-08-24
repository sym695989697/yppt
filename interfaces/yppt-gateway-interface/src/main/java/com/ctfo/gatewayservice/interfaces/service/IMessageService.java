package com.ctfo.gatewayservice.interfaces.service;

import com.ctfo.gatewayservice.interfaces.bean.YpptGatewayException;

public interface IMessageService {
	/**
	 * 发送短信
	 * 
	 * @param mobiles
	 *            手机号码，手机号码之间以英文逗号分隔
	 * @param content
	 *            短信内容
	 * @return 发送成功返回true
	 * @throws YpptGatewayException
	 */
	public boolean sendMessage(String mobiles, String content) throws YpptGatewayException;
}
