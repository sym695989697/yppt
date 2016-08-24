package com.ctfo.sinoiov.mobile.webapi.bean.response.zcpt;

import java.io.Serializable;

import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;

/**
 * 根据用户Id查询帐户返回的帐户对象
 * 
 * @author sunchuanfu
 */
public class QueryAccountInfoByUserIdRsp implements Body, Serializable {
	private static final long serialVersionUID = 5291421130011262336L;

	private String accountNo;// 帐户号码
	private String accountStatus;// 0：表示不可以;1：表示可用
	private String accountType;// 类型code
	private String createTime;// 创建时间
	private String frozenBalance = "0";// 用户被冻结的金额
	private String mobileNo;// 电话号码
	private String ownerLoginName;// 登录用户名
	private String ownerUserId;// 登录用户Id
	private String partShowIdcardNo;// 部分显示的用户身份证号
	private String totalBalance = "0";// 总余额
	private String unableTakecashBalance = "0";// 不可提现金额
	private String usableBalance = "0";// 可用金额
	private String paySumBalance = "0";// 累计支出
	private String incomeSumBalance = "0";// 累计收入

	// 是否有支付密码
	private String isSetPayPassword = PublicStaticParam.RESULT_FAIL;

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

	public String getIsSetPayPassword() {
		return isSetPayPassword;
	}

	public void setIsSetPayPassword(String isSetPayPassword) {
		this.isSetPayPassword = isSetPayPassword;
	}

	public String getPaySumBalance() {
		return paySumBalance;
	}

	public void setPaySumBalance(String paySumBalance) {
		this.paySumBalance = paySumBalance;
	}

	public String getIncomeSumBalance() {
		return incomeSumBalance;
	}

	public void setIncomeSumBalance(String incomeSumBalance) {
		this.incomeSumBalance = incomeSumBalance;
	}

}
