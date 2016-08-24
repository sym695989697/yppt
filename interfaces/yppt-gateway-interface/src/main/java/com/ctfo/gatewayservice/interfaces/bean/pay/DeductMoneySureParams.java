package com.ctfo.gatewayservice.interfaces.bean.pay;

import java.io.Serializable;

/**
 * 扣款确认参数
 * 
 * @author sunchuanfu
 */
public class DeductMoneySureParams implements Serializable {

	private static final long serialVersionUID = 7694020233780236930L;

	private String accountNo;// 帐户名称
	private String orderNo;// 支付订单号
	private String merchantOrderNo;// 业务订单号
	private String orderAmount;// 金额
	private String result;// 结果(1：成功；－1：失败)
	private String payType;// 支付类型（NET FASTPAY ACCOUNT）

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

}
