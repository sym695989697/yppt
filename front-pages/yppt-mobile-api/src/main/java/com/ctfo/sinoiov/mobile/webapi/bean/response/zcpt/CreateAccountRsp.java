package com.ctfo.sinoiov.mobile.webapi.bean.response.zcpt;

import java.io.Serializable;

import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;

/**
 * 创建帐户(老车后使用，不需要验证码)返回对象
 * 
 * @author sunchuanfu
 */
public class CreateAccountRsp implements Body, Serializable {
	private static final long serialVersionUID = -6928055581863903870L;

	private String accountNo;// 帐户号

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

}
