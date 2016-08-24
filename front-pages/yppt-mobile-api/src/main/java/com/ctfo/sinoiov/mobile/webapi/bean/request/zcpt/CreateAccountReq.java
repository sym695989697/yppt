package com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt;

import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.BaseBeanReq;

/**
 * 创建帐户(老车后使用，不需要验证码)参数对象
 * 
 * @author sunchuanfu
 */
public class CreateAccountReq extends BaseBeanReq {
	private static final long serialVersionUID = -493532406590998496L;

	private String userId;// 用户ID（重复定义一个用户id）

	private String ownerUserId;// 统一认证用户Id

	private String ownerLoginName;// 统一认证用户名称

	private String payPassword;// 用户密码（可不传，如果传值则需要经过MD5加密）

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
