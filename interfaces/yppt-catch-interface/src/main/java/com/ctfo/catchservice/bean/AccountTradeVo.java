package com.ctfo.catchservice.bean;

/**
 * 账户交易 VO
 * @author jichao
 */
public class AccountTradeVo extends CatchDataVO {
	/**
	 * 交易号
	 */
	private String TradeSn;
	/**
	 * 卡号
	 */
	private String asn;
	/**
	 * 账户类型
	 */
	private String accountType;
	/**
	 * 金额（元）
	 */
	private String amount;
	/**
	 * 交易时间
	 */
	private String time;
	/**
	 * 余额（元）
	 */
	private String loyaltyAndBalance;
	/**
	 * 交易类型
	 */
	private String tradeType;
	private String tradeName;
	/**
	 * 交易状态
	 */
	private String tradeStatus;
	/**
	 * 交易地点
	 */
	private String org;
	
	private String cardType;

	public String getTradeSn() {
		return TradeSn;
	}

	public void setTradeSn(String tradeSn) {
		TradeSn = tradeSn;
	}

	public String getAsn() {
		return asn;
	}

	public void setAsn(String asn) {
		this.asn = asn;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLoyaltyAndBalance() {
		return loyaltyAndBalance;
	}

	public void setLoyaltyAndBalance(String loyaltyAndBalance) {
		this.loyaltyAndBalance = loyaltyAndBalance;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
}
