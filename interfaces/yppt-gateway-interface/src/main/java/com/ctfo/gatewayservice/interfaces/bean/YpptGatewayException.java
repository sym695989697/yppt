package com.ctfo.gatewayservice.interfaces.bean;

/**
 * 油品网关服务异常类
 * 
 * @author sunchuanfu
 */
public class YpptGatewayException extends RuntimeException {

	private static final long serialVersionUID = 1687064926325206530L;

	/**
	 * 错误信息
	 */
	protected String message;

	/**
	 * 错误码 数值参照com.ctfo.gatewayservice.interfaces.bean.EnumYpptGateWayErrorCodes
	 */
	protected String errorcode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public YpptGatewayException(String message) {
		this.message = message;
	}

	public YpptGatewayException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public YpptGatewayException(String errorcode, String message) {
		this.message = message;
		this.errorcode = errorcode;
	}

	public YpptGatewayException(Throwable cause) {
		super(cause);
	}

	public static void throwExcetion(YpptGatewayException exception) {
		throw exception;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
}
