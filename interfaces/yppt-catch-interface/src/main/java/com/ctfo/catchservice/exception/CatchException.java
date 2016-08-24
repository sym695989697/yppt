package com.ctfo.catchservice.exception;

/**
 * 捕获数据异常
 * 
 * @author jichao
 */
public class CatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public CatchException(String errorCode, String msg, Throwable cause) {
		super(msg, cause);
		this.setErrorCode(errorCode);
	}

	public CatchException(String errorCode, String msg) {
		super(msg);
		this.setErrorCode(errorCode);
	}

	public CatchException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public CatchException(String msg) {
		super(msg);
	}

	public CatchException(Throwable cause) {
		super(cause);
	}

}
