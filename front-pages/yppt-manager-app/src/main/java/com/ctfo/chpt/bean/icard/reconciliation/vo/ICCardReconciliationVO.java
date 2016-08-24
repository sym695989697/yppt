package com.ctfo.chpt.bean.icard.reconciliation.vo;

import javax.xml.bind.annotation.XmlElement;

public class ICCardReconciliationVO {

	@XmlElement(name = "(交易流水号;NOMAL_ENUM)imp", required = true)
	private String tradeId;

	@XmlElement(name = "(金额（元）;NOMAL_ENUM)imp", required = true)
	private String money;

	@XmlElement(name = "(交易类型;MAP_ENUM)imp", required = true)
	private String tradeType;

	@XmlElement(name = "(交易状态;MAP_ENUM)imp", required = true)
	private String states;

	@XmlElement(name = "(卡号/客户编号[M];NOMAL_ENUM)imp", required = true)
	private String cardNo;

	@XmlElement(name = "(客户名称;NOMAL_ENUM)imp", required = false)
	private String userName;

	@XmlElement(name = "(商品种类;NOMAL_ENUM)imp", required = false)
	private String productType;

	@XmlElement(name = "(油量（升）;NOMAL_ENUM)imp", required = false)
	private String oilNum;

	@XmlElement(name = "(省份;PROVINCE_ENUM)imp", required = true)
	private String provice;

	@XmlElement(name = "(受理机构;NOMAL_ENUM)imp", required = false)
	private String address;

	@XmlElement(name = "(业务日期;NOMAL_ENUM)imp", required = false)
	private String businessTime;

	@XmlElement(name = "(交易时间;NOMAL_ENUM)imp", required = true)
	private String tradeTime;

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getOilNum() {
		return oilNum;
	}

	public void setOilNum(String oilNum) {
		this.oilNum = oilNum;
	}


	public String getProvice() {
		return provice;
	}

	public void setProvice(String provice) {
		this.provice = provice;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBusinessTime() {
		return businessTime;
	}

	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

}
