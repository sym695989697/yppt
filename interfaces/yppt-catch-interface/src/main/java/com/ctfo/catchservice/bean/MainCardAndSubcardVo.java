package com.ctfo.catchservice.bean;

/**
 * 单位主卡信息VO（主卡副卡信息）
 * 
 * @author jichao
 */
public class MainCardAndSubcardVo extends CatchDataVO {

	/**
	 * 驾驶员名称
	 */
	private String driverName;
	/**
	 * 架驶员卡号
	 */
	private String asn;
	/**
	 * 是否为主卡
	 */
	private String isMaster;
	
	private String parentNumber;
	/**
	 * 卡状态
	 */
	private String cardStatus;

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getAsn() {
		return asn;
	}

	public void setAsn(String asn) {
		this.asn = asn;
	}

	public String getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(String isMaster) {
		this.isMaster = isMaster;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getParentNumber() {
		return parentNumber;
	}

	public void setParentNumber(String parentNumber) {
		this.parentNumber = parentNumber;
	}

}
