package com.ctfo.gatewayservice.interfaces.bean.pay;

import java.io.Serializable;

/**
 * 冻结或者解冻帐户输入参数对象
 * 
 * @author sunchuanfu
 */
public class FreezeBalanceParams implements Serializable {

	private static final long serialVersionUID = 386815137292386842L;

	private String accountNo;// 帐户号
	private String orderAmount;// 冻结或者解冻的金额
	private String workOrderNo;// 业务订单号

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getWorkOrderNo() {
		return workOrderNo;
	}

	public void setWorkOrderNo(String workOrderNo) {
		this.workOrderNo = workOrderNo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

}
