package com.ctfo.chpt.bean.iccard.vo;

import javax.xml.bind.annotation.XmlElement;

public class CardDetail {
	@XmlElement(name = "(车牌号[M];NOMAL_ENUM)imp", required = false)
	private String vehicleNo;
	@XmlElement(name = "(绑定手机;NOMAL_ENUM)imp", required = false)
	private String bindMobile;
	@XmlElement(name = "(短信提醒;MAP_ENUM)imp", required = false)
	private String messageRemind;
	@XmlElement(name = "(卡号;NOMAL_ENUM)imp", required = false)
	private String cardNumber;
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getBindMobile() {
		return bindMobile;
	}
	public void setBindMobile(String bindMobile) {
		this.bindMobile = bindMobile;
	}
	public String getMessageRemind() {
		return messageRemind;
	}
	public void setMessageRemind(String messageRemind) {
		this.messageRemind = messageRemind;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	
}
