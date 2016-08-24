package com.ctfo.gatewayservice.interfaces.bean.pay;

import java.io.Serializable;

/**
 * 帐户信息
 */
public class Account implements Serializable {
	private static final long serialVersionUID = -6087239398546465447L;

	private String accountNo;// 帐户号码
	private String accountStatus;// 0：表示不可以;1：表示可用
	private String accountType;// 类型code
	private String createTime;// 创建时间
	private String frozenBalance;// 用户被冻结的金额
	private String mobileNo;// 电话号码
	private String ownerLoginName;// 登录用户名
	private String ownerUserId;// 登录用户Id
	private String partShowIdcardNo;// 部分显示的用户身份证号
	private String totalBalance;// 总余额
	private String unableTakecashBalance;// 不可提现金额
	private String usableBalance;// 可用金额

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFrozenBalance() {
		return frozenBalance;
	}

	public void setFrozenBalance(String frozenBalance) {
		this.frozenBalance = frozenBalance;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getOwnerLoginName() {
		return ownerLoginName;
	}

	public void setOwnerLoginName(String ownerLoginName) {
		this.ownerLoginName = ownerLoginName;
	}

	public String getOwnerUserId() {
		return ownerUserId;
	}

	public void setOwnerUserId(String ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

	public String getPartShowIdcardNo() {
		return partShowIdcardNo;
	}

	public void setPartShowIdcardNo(String partShowIdcardNo) {
		this.partShowIdcardNo = partShowIdcardNo;
	}

	public String getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(String totalBalance) {
		this.totalBalance = totalBalance;
	}

	public String getUnableTakecashBalance() {
		return unableTakecashBalance;
	}

	public void setUnableTakecashBalance(String unableTakecashBalance) {
		this.unableTakecashBalance = unableTakecashBalance;
	}

	public String getUsableBalance() {
		return usableBalance;
	}

	public void setUsableBalance(String usableBalance) {
		this.usableBalance = usableBalance;
	}

}
