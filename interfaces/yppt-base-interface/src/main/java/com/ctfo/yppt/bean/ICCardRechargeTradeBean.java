package com.ctfo.yppt.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class ICCardRechargeTradeBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String cardNo;
	private String vehicleNo;
	private BigDecimal money;
	private Long time;
	private String tradeType;
    private String state;
    private String color;
    private String faileReason;//失败原因
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFaileReason() {
		return faileReason;
	}
	public void setFaileReason(String faileReason) {
		this.faileReason = faileReason;
	}
    
    

}
