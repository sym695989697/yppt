package com.ctfo.gatewayservice.interfaces.bean.pay;

import java.io.Serializable;

/**
 * 创建帐户输入参数对象
 * 
 * @author sunchuanfu
 */
public class CreateAccountParams implements Serializable {
	private static final long serialVersionUID = -4741671430665116766L;

	private String ownerUserId;// 统一认证用户Id
	private String ownerLoginName;// 统一认证用户名称
	private String payPassword;// 用户密码（可不传；赋值为明文，在网关会进行MD5加密）
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
