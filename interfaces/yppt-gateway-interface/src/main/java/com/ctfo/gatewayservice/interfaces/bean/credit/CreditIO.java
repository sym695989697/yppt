package com.ctfo.gatewayservice.interfaces.bean.credit;

import java.io.Serializable;

/**
 * 旺金币收支记录
 * @author 徐宝
 *
 */
public class CreditIO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	// "用户统一认证(用户中心)ID;user_id"
    private String userId;
    //"用户类型:(2:企业,3:个人）"
	private String userType;
	//"用户名称"
	private String userName;
	//"用户注册手机号"
	private String userRegPhone;
    // "积分账户ID"
    private String accountId;
    // "交易类型: recharge :充值; rebate:返利 "
    private String type;
    // "收支类型(以账户的角度：1收入，2支出)"
    private String model;
    // "积分数量"
    private Long creditNum;
    // "收支记录创建原因id"
    private String reasonId;
    // "操作后账户余额"
    private Long balance;
    // "状态，0成功，1失败"
    private String state;
    // "操作时间"
    private Long operateTime;
    // "操作人"
    private String operator;
    // "备注"
    private String remark;
    // 交易时间(返利时,填写返利账单期)
    private Long tradeTime;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRegPhone() {
		return userRegPhone;
	}
	public void setUserRegPhone(String userRegPhone) {
		this.userRegPhone = userRegPhone;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Long getCreditNum() {
		return creditNum;
	}
	public void setCreditNum(Long creditNum) {
		this.creditNum = creditNum;
	}
	public String getReasonId() {
		return reasonId;
	}
	public void setReasonId(String reasonId) {
		this.reasonId = reasonId;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Long operateTime) {
		this.operateTime = operateTime;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Long tradeTime) {
		this.tradeTime = tradeTime;
	}
}
