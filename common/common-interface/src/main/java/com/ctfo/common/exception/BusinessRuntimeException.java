package com.ctfo.common.exception;

/***
 * 不可恢复异常
 *
 */
public class BusinessRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public BusinessRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BusinessRuntimeException(String msg) {
		super(msg);
	}
}
