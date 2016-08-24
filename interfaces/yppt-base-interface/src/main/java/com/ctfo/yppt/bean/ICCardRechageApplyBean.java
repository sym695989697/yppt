package com.ctfo.yppt.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author rao yongbing 
 * @Description 充值记录bean
 * 2015年1月21日
 */
public class ICCardRechageApplyBean implements Serializable{

	private static final long serialVersionUID = 1L;
	/**************recharge apply**************/
	private Long tradeTime;//充值申请时间
	private String tradeStatus;//状态
	private String applyId;//申请id
	
	/***************recharge apply detail*************************/
	private String id;//recharge apply detail id
	private String cardNo;//充值支付卡卡号
	private String tradeType;//交易类型
	private String payType;//交易类型
	private BigDecimal tradeMoney;//充值金额（单位：分）
	private String cardId;//充值支付卡ID
	private BigDecimal cardBalance;//充值支付卡余额
	private String cardType;//支付卡类型
	private String faileReason;//失败原因
	private String vehicleNo;//车牌号

	public Long getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Long tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public BigDecimal getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(BigDecimal cardBalance) {
		this.cardBalance = cardBalance;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getFaileReason() {
		return faileReason;
	}

	public void setFaileReason(String faileReason) {
		this.faileReason = faileReason;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
}
