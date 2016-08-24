package com.ctfo.sinoiov.mobile.webapi.bean.vo;

import com.ctfo.sinoiov.mobile.webapi.util.PropertityCopyAnnotation;

public class TradeInfoVo {

	
	/**
	 * 参数名称	参数编码	是否必填	说明
		加油（消费）	oilCost	是	类型：double；用户用卡消费的钱数值
		交易类型	tradeType	是	0:表示加油交易；1：表示充值交易
		充值状态	rechargeState	是	比如：0：表示处理中，1：表示充值完成；2：表示充值失败，等等
		充值状态错误提示	rechargeStateMsg	是	类型：字符串；
		充值（存款）	money	是	类型：double；用户向卡中存储的钱数值
		交易时间	tradeTime		类型：字符串；时间戳


参数名称	参数编码	是否必填	说明
交易类型	tradeType	是	类型：字符串；
交易ID	tradeID	是	字符串
卡尾号	cardTail	是	字符串;后台反馈卡号，APP端可以自行截取
车牌号	vehicleNum		类型：字符串
车牌颜色	vehicleColor		类型：字符串
交易金额	tradeMoney	是	类型：double；
交易状态	tradeState	是	类型：int
0：表示处理中；1：表示处理完成
交易时间	tradeTime	是	类型：字符串；时间戳 
失败原因	resultMsg		类型：字符串：


参数名称	参数编码	是否必填	说明
加油（消费）	oilCost	是	类型：double；用户用卡消费的钱数值
交易类型	Type  	是	0:表示加油交易；1：表示充值交易
充值（存款）	money	是	类型：double；用户向卡中存储的钱数值
交易时间	tradeTime	是	类型：String；给的是一个时间戳
交易ID	tradeID	是	每条记录增加交易ID
账户余额	balance	是	类型：double

	 */
	
	private long money;
	private long oilCost;
	private int rechargeState;
	private String rechargeStateMsg;
	
	private String tradeType;	//交易类型
	@PropertityCopyAnnotation(destFieldName = "licenseNumber")
	private String vehicleNum;  //车牌号
	
	private String cardTail;  //卡尾号
	
	private String vehicleColor;	//车牌颜色
	
	private String vehicleId;  //车辆id
	
	private double tradeMoney;  //交易金额
	
	private int tradeState;  //交易状态
	
	private String tradeTime;  //交易时间
	
	private String resultMsg;  //失败原因
	
	private String oil;  //油品种类
	
	@PropertityCopyAnnotation(destFieldName = "oilNum")
	private long oilCount;  //单笔消费油的量（L）
	
	private long price; //油品单价
	
	@PropertityCopyAnnotation(destFieldName = "cardBalance")
	private long balance; //完成交易后卡的余额
	
	@PropertityCopyAnnotation(destFieldName = "tradeAdress")
	private String tradePlace; //  交易地点
	
	private String tradeId;   //交易ID
	
	private String cardNum;   //卡号
	
	
	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public long getOilCost() {
		return oilCost;
	}

	public void setOilCost(long oilCost) {
		this.oilCost = oilCost;
	}

	public int getRechargeState() {
		return rechargeState;
	}

	public void setRechargeState(int rechargeState) {
		this.rechargeState = rechargeState;
	}

	public String getRechargeStateMsg() {
		return rechargeStateMsg;
	}

	public void setRechargeStateMsg(String rechargeStateMsg) {
		this.rechargeStateMsg = rechargeStateMsg;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getOil() {
		return oil;
	}

	public void setOil(String oil) {
		this.oil = oil;
	}

	public long getOilCount() {
		return oilCount;
	}

	public void setOilCount(long oilCount) {
		this.oilCount = oilCount;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String getTradePlace() {
		return tradePlace;
	}

	public void setTradePlace(String tradePlace) {
		this.tradePlace = tradePlace;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}


	public String getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getVehicleNum() {
		return vehicleNum;
	}

	public void setVehicleNum(String vehicleNum) {
		this.vehicleNum = vehicleNum;
	}

	public double getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(double tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public int getTradeState() {
		return tradeState;
	}

	public void setTradeState(int tradeState) {
		this.tradeState = tradeState;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

}
