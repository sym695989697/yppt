package com.ctfo.catchservice.bean;

import java.io.Serializable;

/**
 * 调用抓取接口返回结果
 * 
 * @author jichao
 */
public class JResult implements Serializable {
	
	private static final long serialVersionUID = -6531873454452307516L;
	/**
	 * 是否成功，true成功 false失败
	 */
	private boolean success;
	/**
	 * 返回记录数
	 */
	private int count;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
