package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

import com.ctfo.sinoiov.mobile.webapi.util.PropertityCopyAnnotation;

public class QueryOilCardInfoRsp extends BaseBeanRsp{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * 参数名称	参数编码	是否必填	说明
油卡类型	cardType	是	例如：0：表示金卡；1：表示普通卡
发卡地区	cardDept	是	类型：字符串；发卡地区名称
卡号	cardNum	是	类型：字符串；每张卡的唯一标示
绑定车牌号	vehicleNum		类型：字符串；车辆的唯一标识
车牌颜色	vehicleColor		类型：字符串
绑定手机号	telNum	是	类型：字符串；对应油卡上的手机号
卡片状态	cardState	是	例如：0：表示激活状态；1：表示停用态
昨日余额	balance	是	类型：双精度；卡内余额的double值
累计加油（元）	totalOilingY	是	类型：double；上月加油消费的钱数
累计加油（L）	totalOilingL	是	类型：double；上月加油的量，单位是升（L）



	 */
	@PropertityCopyAnnotation(destFieldName = "icType")  
	private String  cardType;		//"卡类型(0:主卡,1:副卡) "
	
	@PropertityCopyAnnotation(destFieldName = "openCardProvince")
	private String cardDept;	//发卡地区
	
	@PropertityCopyAnnotation(destFieldName = "cardNumber")
	private String cardNum ;	//卡号
	
	@PropertityCopyAnnotation(destFieldName = "vehicleNo")
	private String vehicleNum;	//绑定车辆
	
	@PropertityCopyAnnotation(destFieldName = "vehicleColor")
	private String vehicleColor;  //车牌颜色
	
	@PropertityCopyAnnotation(destFieldName = "telephoneNumber")
	private String telNum;		//绑定手机号
	
	@PropertityCopyAnnotation(destFieldName = "state")
	private String  cardState;  	//卡状态   例如：0：表示激活状态；1：表示停用态
	
	@PropertityCopyAnnotation(destFieldName = "balance")
	private String balance;		//昨日余额
	
	private String totalOilingY; //累计加油（元）		//数据库中对应Bean无此字段
	
	private String totalOilingL; //累计加油（L）		//数据库中对应Bean无此字段

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardDept() {
		return cardDept;
	}

	public void setCardDept(String cardDept) {
		this.cardDept = cardDept;
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

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public String getCardState() {
		return cardState;
	}

	public void setCardState(String cardState) {
		this.cardState = cardState;
	}

	

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getTotalOilingY() {
		return totalOilingY;
	}

	public void setTotalOilingY(String totalOilingY) {
		this.totalOilingY = totalOilingY;
	}

	public String getTotalOilingL() {
		return totalOilingL;
	}

	public void setTotalOilingL(String totalOilingL) {
		this.totalOilingL = totalOilingL;
	}
}
