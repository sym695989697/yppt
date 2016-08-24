package com.ctfo.yppt.bean;

import java.io.Serializable;

public class CreateAccountBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String ownerUserId;// 统一认证用户Id

	private String ownerLoginName;// 统一认证用户名称

	private String payPassword;// 用户密码（可不传）

	private String mobileNo;// 电话号码

	public String getOwnerUserId() {
		return ownerUserId;
	}

	public void setOwnerUserId(String ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

	public String getOwnerLoginName() {
		return ownerLoginName;
	}

	public void setOwnerLoginName(String ownerLoginName) {
		this.ownerLoginName = ownerLoginName;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
}
