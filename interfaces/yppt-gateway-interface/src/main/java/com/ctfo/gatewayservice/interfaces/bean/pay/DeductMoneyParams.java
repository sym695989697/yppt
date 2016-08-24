package com.ctfo.gatewayservice.interfaces.bean.pay;

import java.io.Serializable;

/**
 * 扣款(油卡充值到中交收益帐户)
 * 
 * @author sunchuanfu
 */
public class DeductMoneyParams implements Serializable {
	private static final long serialVersionUID = -3035318720689001521L;

	private String userId;// 付款用户Id
	private String accountNo;// 付款账号
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
