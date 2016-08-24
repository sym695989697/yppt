package com.ctfo.sinoiov.mobile.webapi.bean.vo;

import com.ctfo.sinoiov.mobile.webapi.util.PropertityCopyAnnotation;

public class OneOilCardTradeVo {
	/**
		 参数名称	参数编码	是否必填	说明
加油（消费）	oilCost	是	类型：double；用户用卡消费的钱数值
交易类型	tradeType	是	0:表示加油交易；1：表示充值交易
充值状态	rechargeState	是	比如：0：表示处理中，1：表示充值完成；2：表示充值失败，等等
充值状态错误提示	rechargeStateMsg	是	类型：字符串；
充值（存款）	money	是	类型：double；用户向卡中存储的钱数值
交易时间	tradeTime		类型：字符串；时间戳

*/
	@PropertityCopyAnnotation(destFieldName = "id")
	private String tradeId;//交易id
	@PropertityCopyAnnotation(destFieldName = "tradeMoney")
	private String oilCost;   //交易金额
	
	@PropertityCopyAnnotation(destFieldName = "tradeType")
	private String tradeType;  //交易类型
	
	@PropertityCopyAnnotation(destFieldName = "tradeState")
	private String rechargeState;   //交易状态
	
	private String rechargeStateMsg;	//交易失败原因
	
	@PropertityCopyAnnotation(destFieldName = "tradeMoney")
	private double money;		//交易后卡余额
	
	@PropertityCopyAnnotation(destFieldName = "tradeTime")
	private Long tradeTime;		//交易时间
	
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getOilCost() {
		return oilCost;
	}
	public void setOilCost(String oilCost) {
		this.oilCost = oilCost;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getRechargeState() {
		return rechargeState;
	}
	public void setRechargeState(String rechargeState) {
		this.rechargeState = rechargeState;
	}
	public String getRechargeStateMsg() {
		return rechargeStateMsg;
	}
	public void setRechargeStateMsg(String rechargeStateMsg) {
		this.rechargeStateMsg = rechargeStateMsg;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Long getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Long tradeTime) {
		this.tradeTime = tradeTime;
	}
	

}
