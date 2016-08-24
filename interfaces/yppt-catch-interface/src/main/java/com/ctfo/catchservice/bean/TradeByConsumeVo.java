package com.ctfo.catchservice.bean;

/**
 * 消费交易查询Vo
 * 
 * @author jichao
 */
public class TradeByConsumeVo extends CatchDataVO {
	/**
	 * 交易ID
	 */
	private String tradeId;
	/**
	 * 卡号
	 */
	private String cardAsn;
	/**
	 * 金额(元)
	 */
	private String amount;
	/**
	 * 余额(元)
	 */
	private String balance;
	/**
	 * 交易类型
	 */
	private String tradeType;
	private String tradeName;
	private String productPrice;
	/**
	 * 交易地点
	 */
	private String orgName;
	/**
	 * 交易时间
	 */
	private String occurTime;
	/**
	 * 商品名称
	 */
	private String giftName;
	/**
	 * 商品类别
	 */
	private String catName;
	/**
	 * 油量(升)
	 */
	private String volumn;

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getCardAsn() {
		return cardAsn;
	}

	public void setCardAsn(String cardAsn) {
		this.cardAsn = cardAsn;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOccurTime() {
		return occurTime;
	}

	public void setOccurTime(String occurTime) {
		this.occurTime = occurTime;
	}

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getVolumn() {
		return volumn;
	}

	public void setVolumn(String volumn) {
		this.volumn = volumn;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

}
