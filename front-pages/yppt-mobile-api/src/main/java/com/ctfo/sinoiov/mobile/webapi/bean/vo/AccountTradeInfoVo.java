package com.ctfo.sinoiov.mobile.webapi.bean.vo;

import com.ctfo.sinoiov.mobile.webapi.util.PropertityCopyAnnotation;

public class AccountTradeInfoVo {
	/**
	 * 
		参数名称	参数编码	是否必填	说明
		加油（消费）
		油卡充值	oilCost	是	类型：String；用户用卡消费的钱数值
		交易类型	tradeType  	是	0:表示充油卡；1：表示充值
		充值（存款）
		账户充值	tradeMoney	是	类型：double；用户向账户中存储的钱数值
		交易时间	tradeTime	是	类型：String；给的是一个时间戳
		交易ID	tradeID	是	每条记录增加交易ID
		账户余额	balance	是	类型：double
*/
	
	private String oilCost;   //油卡充值
	
	private int tradeType;	  //交易类型
	
	private String tradeID;		//交易ID
	
	private Double tradeMoney;	//账户充值
	
	@PropertityCopyAnnotation(destFieldName = "cardBalance")
	private Double balance;   //账户余额
	
	private Long tradeTime;	  //交易时间
	
	private String tradeState; //交易状态 0待审核 1审核成功 2是审核失败
	
	
	
	public String getTradeState() {
		return tradeState;
	}
	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}
	public String getOilCost() {
		return oilCost;
	}
	public void setOilCost(String oilCost) {
		this.oilCost = oilCost;
	}
	
	public int getTradeType() {
		return tradeType;
	}
	public void setTradeType(int tradeType) {
		this.tradeType = tradeType;
	}
	
	public String getTradeID() {
		return tradeID;
	}
	public void setTradeID(String tradeID) {
		this.tradeID = tradeID;
	}
	
	public Double getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(Double tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Long getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Long tradeTime) {
		this.tradeTime = tradeTime;
	}
	
}
