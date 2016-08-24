package com.ctfo.gatewayservice.interfaces.bean.pay;

import java.io.Serializable;

/**
 * 转帐传入参数对象
 * 
 * @author sunchuanfu
 */
public class TransferAccountParams implements Serializable {
	private static final long serialVersionUID = -3035318720689001521L;

	private String userId;// 付款用户Id
	private String accountNo;// 付款账号
	private String collectMoneyAccountNo;// 收款账号
	private String workOrderNo;// 业务订单号
	private String orderAmount;// 转账金额(单位元)
	private String storeCode;// 商户编号(此属性网关赋值)

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getCollectMoneyAccountNo() {
		return collectMoneyAccountNo;
	}

	public void setCollectMoneyAccountNo(String collectMoneyAccountNo) {
		this.collectMoneyAccountNo = collectMoneyAccountNo;
	}

	public String getWorkOrderNo() {
		return workOrderNo;
	}

	public void setWorkOrderNo(String workOrderNo) {
		this.workOrderNo = workOrderNo;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

}
