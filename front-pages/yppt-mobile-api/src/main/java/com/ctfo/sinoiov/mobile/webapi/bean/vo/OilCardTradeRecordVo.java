package com.ctfo.sinoiov.mobile.webapi.bean.vo;

import com.ctfo.sinoiov.mobile.webapi.util.PropertityCopyAnnotation;

public class OilCardTradeRecordVo {
	/**
	 * 参数名称	参数编码	是否必填	说明
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
	 */
	
	private String tradeType;	//交易类型
	
	private String tradeId;   //交易ID
	
	private String cardNum;   //卡尾号
	
	@PropertityCopyAnnotation(destFieldName = "licenseNumber")
	private String vehicleNum;  //车牌号
	
	private String vehicleColor;	//车牌颜色
	
	private String tradeMoney;  //交易金额
	
	private String tradeState;  //交易状态
	
	private String tradeTime;  //交易时间
	
	private String resultMsg;  //失败原因
	
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
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
	public String getVehicleNum() {
		return vehicleNum;
	}
	public void setVehicleNum(String vehicleNum) {
		this.vehicleNum = vehicleNum;
	}
	public String getVehicleColor() {
		return vehicleColor;
	}
	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}
	
	public String getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(String tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	public String getTradeState() {
		return tradeState;
	}
	public void setTradeState(String tradeState) {
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
