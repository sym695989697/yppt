package com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt;

import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.BaseBeanReq;

/**
 * 发送短信参数对象
 * 
 * @author sunchuanfu
 */
public class GetSmsCodeReq extends BaseBeanReq {
	private static final long serialVersionUID = 9048744534851586702L;

	private String accountNo;// 帐户号
	private String mobileNo;// 电话
	private String isCreateAccount = "1";// 1代表其它情况；0代表开户时获取验证码(此时帐户号为空，需要置成商户编号)

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getIsCreateAccount() {
		return isCreateAccount;
	}

	public void setIsCreateAccount(String isCreateAccount) {
		this.isCreateAccount = isCreateAccount;
	}

}
