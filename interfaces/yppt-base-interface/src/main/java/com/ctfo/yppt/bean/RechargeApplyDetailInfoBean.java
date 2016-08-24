package com.ctfo.yppt.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class RechargeApplyDetailInfoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private  BigDecimal captMoney;
	private  String cardNo;
	private  String tradeTime;
	private String payWay;
	public BigDecimal getCaptMoney() {
		return captMoney;
	}
	public void setCaptMoney(BigDecimal captMoney) {
		this.captMoney = captMoney;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

}
