package com.ctfo.catchservice.bean;

/**
 * 油卡余额信息类
 * 
 * @author jichao
 */
public class OilCardBalanceVo extends CatchDataVO {
	/**
	 * 卡号
	 */
	private String asn;
	/**
	 * 卡内余额(元)
	 */
	private String cardBalance;
	/**
	 * 备用金余额(元)
	 */
	private String balance;
	/**
	 * 卡内积分(分)
	 */
	private String cardLoyaltyBalance;
	/**
	 * 备用积分余额(分)
	 */
	private String loyaltyBalance;
	private String lastCardTime;
	private String lastCardLoyaltyTime;

	public String getAsn() {
		return asn;
	}

	public void setAsn(String asn) {
		this.asn = asn;
	}

	public String getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(String cardBalance) {
		this.cardBalance = cardBalance;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getCardLoyaltyBalance() {
		return cardLoyaltyBalance;
	}

	public void setCardLoyaltyBalance(String cardLoyaltyBalance) {
		this.cardLoyaltyBalance = cardLoyaltyBalance;
	}

	public String getLoyaltyBalance() {
		return loyaltyBalance;
	}

	public void setLoyaltyBalance(String loyaltyBalance) {
		this.loyaltyBalance = loyaltyBalance;
	}

	public String getLastCardTime() {
		return lastCardTime;
	}

	public void setLastCardTime(String lastCardTime) {
		this.lastCardTime = lastCardTime;
	}

	public String getLastCardLoyaltyTime() {
		return lastCardLoyaltyTime;
	}

	public void setLastCardLoyaltyTime(String lastCardLoyaltyTime) {
		this.lastCardLoyaltyTime = lastCardLoyaltyTime;
	}

}
