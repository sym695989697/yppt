package com.ctfo.gatewayservice.interfaces.bean.pay;

import java.io.Serializable;

/**
 * 创建帐户（需要手机证码）输入参数对象
 * 
 * @author sunchuanfu
 */
public class CreateAccountSmsCodeParams implements Serializable {
	private static final long serialVersionUID = -4741671430665116766L;

	private String ownerUserId;// 统一认证用户Id
	private String ownerLoginName;// 统一认证用户名称
	private String MD5;// 支付密码（传明文，在网关会进行MD5加密）
	private String mobileNo;// 电话号码
	private String smsCode;// 短信验证码

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

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getMD5() {
		return MD5;
	}

	public void setMD5(String mD5) {
		MD5 = mD5;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

}
