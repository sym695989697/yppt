package com.ctfo.chpt.bean.iccard.vo;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

public class ICCardVO {
	
	@XmlElement(name = "(卡号;NOMAL_ENUM)imp", required = false)
	private String cardNumber;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	
}
