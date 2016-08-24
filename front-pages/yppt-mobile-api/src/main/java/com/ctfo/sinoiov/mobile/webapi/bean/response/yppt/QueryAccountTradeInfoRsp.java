package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

import java.util.List;

public class QueryAccountTradeInfoRsp extends BaseBeanRsp{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String balance;		//交易后余额
	private int tradeType;  //交易类型
	private String tradeMoney;  //交易金额
//	private String cardNum; //卡号
//	private String vhicleNum;  //车牌号
	private String tradeTime;  //交易时间
	private String tradeResult; //交易结果
	
	private List cardList;  //卡號list
	private List vhicleList;  //車牌號list
	private List tradeList;   // 交易金額list
	
	
	public List getCardList() {
		return cardList;
	}
	public void setCardList(List cardList) {
		this.cardList = cardList;
	}
	public List getVhicleList() {
		return vhicleList;
	}
	public void setVhicleList(List vhicleList) {
		this.vhicleList = vhicleList;
	}
	public List getTradeList() {
		return tradeList;
	}
	public void setTradeList(List tradeList) {
		this.tradeList = tradeList;
	}
	public String getTradeResult() {
		return tradeResult;
	}
	public void setTradeResult(String tradeResult) {
		this.tradeResult = tradeResult;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public int getTradeType() {
		return tradeType;
	}
	public void setTradeType(int tradeType) {
		this.tradeType = tradeType;
	}
	public String getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(String tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

}
