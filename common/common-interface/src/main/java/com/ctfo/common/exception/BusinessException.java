package com.ctfo.common.exception;

/***
 * 可捕获异常
 *
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public BusinessException(String errorCode, String msg, Throwable cause) {
		super(msg, cause);
		this.setErrorCode(errorCode);
	}

	public BusinessException(String errorCode, String msg) {
		super(msg);
		this.setErrorCode(errorCode);
	}

	public BusinessException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BusinessException(String msg) {
		super(msg);
	}
}
