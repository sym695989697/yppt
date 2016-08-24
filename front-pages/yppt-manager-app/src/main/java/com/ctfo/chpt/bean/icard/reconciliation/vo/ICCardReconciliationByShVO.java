package com.ctfo.chpt.bean.icard.reconciliation.vo;

import javax.xml.bind.annotation.XmlElement;

public class ICCardReconciliationByShVO {

	@XmlElement(name = "(卡号[M];NOMAL_ENUM)imp", required = true)
	private String cardNo;

	@XmlElement(name = "(交易时间;NOMAL_ENUM)imp", required = true)
	private String tradeTime;

	@XmlElement(name = "(业务类型;MAP_ENUM)imp", required = true)
	private String tradeType;

	@XmlElement(name = "交易商品名称;NOMAL_ENUM)imp", required = false)
	private String oilName;

	@XmlElement(name = "(油量（升）;NOMAL_ENUM)imp", required = false)
	private String oilNum;

	@XmlElement(name = "商品单价;NOMAL_ENUM)imp", required = false)
	private String productPrice;

	@XmlElement(name = "(金额（元）;NOMAL_ENUM)imp", required = true)
	private String tradeMoney;

	@XmlElement(name = "(余额;NOMAL_ENUM)imp", required = false)
	private String balance;

	@XmlElement(name = "交易地点;NOMAL_ENUM)imp", required = true)
	private String tradeAdress;

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

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getOilName() {
		return oilName;
	}

	public void setOilName(String oilName) {
		this.oilName = oilName;
	}

	public String getOilNum() {
		return oilNum;
	}

	public void setOilNum(String oilNum) {
		this.oilNum = oilNum;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(String tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getTradeAdress() {
		return tradeAdress;
	}

	public void setTradeAdress(String tradeAdress) {
		this.tradeAdress = tradeAdress;
	}

}
