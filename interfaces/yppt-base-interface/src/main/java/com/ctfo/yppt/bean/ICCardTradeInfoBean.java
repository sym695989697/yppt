package com.ctfo.yppt.bean;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 
 * @author rao yongbing 
 * @Description 加油记录bean
 * 2015年1月23日
 */
public class ICCardTradeInfoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;		//
    private String cardNo; //卡号
	private BigDecimal tradeMoney;//交易金额
	private BigDecimal cardBalance;//交易后余额
	private Long tradeTime;//交易时间
	private String tradeState;//交易状态
	private String tradeAdress;//交易地址
	private String productName;//交易油品名称
	private BigDecimal productPrice;//商品单价
	private Long productNum;//交易油品数量
	private String vehicleNo;//车牌号
	private String tradeType;//交易类型
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	public BigDecimal getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(BigDecimal cardBalance) {
		this.cardBalance = cardBalance;
	}
	public Long getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Long tradeTime) {
		this.tradeTime = tradeTime;
	}
	public String getTradeState() {
		return tradeState;
	}
	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}
	public String getTradeAdress() {
		return tradeAdress;
	}
	public void setTradeAdress(String tradeAdress) {
		this.tradeAdress = tradeAdress;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getProductNum() {
		return productNum;
	}
	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
	
}
