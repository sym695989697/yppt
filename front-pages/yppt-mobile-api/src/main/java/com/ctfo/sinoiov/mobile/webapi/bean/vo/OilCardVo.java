package com.ctfo.sinoiov.mobile.webapi.bean.vo;

import java.io.Serializable;

import com.ctfo.ccapp.beans.PropertityCopyAnnotation;

/**
 * 
 * 参数名称	参数编码	是否必填	说明
卡id	id	是	类型：字符串；
卡号	cardNum	是	类型：字符串；用户油卡的编号String值
卡内余额	balance	是	类型：字符串；用户油卡里余额double值
车牌号	vehicleNum	是	类型：字符串
开卡时间	createCardTime		类型：字符串
卡片状态	cardState		例如：0：表示卡片审核中；1：表示审核完成 2：表示油卡申请失败
失败详情	failureDetails		类型：字符串
油卡图片	imageClass	是	类型：String 格式："transportPermitImg:1,vehicleImage:1",请参照车旺APP图片传输方式
付款凭证上传文件流	----(无参数)	是	参照车旺APP图片传输方式
 *
 */

public class OilCardVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PropertityCopyAnnotation(destFieldName = "id")
	private String id ;			//油卡Id 
	
	@PropertityCopyAnnotation(destFieldName = "cardNumber")
	private String cardNum ;	//卡号
	
	@PropertityCopyAnnotation(destFieldName = "balance")
	private Double balance;		//昨日余额
	
	@PropertityCopyAnnotation(destFieldName = "vehicleNo")
	private String vehicleNum;	//绑定车辆
	
//	@PropertityCopyAnnotation(destFieldName = "vehicleColor")
//	private String vehicleColor;  //车牌颜色			
	
	@PropertityCopyAnnotation(destFieldName = "createdTime")
	private String createCardTime;  //开卡时间
	
	@PropertityCopyAnnotation(destFieldName = "state")
	private String  cardState;  	//卡状态   例如：0：表示激活状态；1：表示停用态     	
	
	private String failureDetails;  //失败详情      	//iccard数据库中的bean没有
	
	private String cardImg;	//油卡图片         		//iccard数据库中的bean没有
	
	private String  auditTime;		//审核时间
	
	private String type;
	
	public String getCreateCardTime() {
		return createCardTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public void setCreateCardTime(String createCardTime) {
		this.createCardTime = createCardTime;
	}

	public String getFailureDetails() {
		return failureDetails;
	}

	public void setFailureDetails(String failureDetails) {
		this.failureDetails = failureDetails;
	}

	public String getCardImg() {
		return cardImg;
	}

	public void setCardImg(String cardImg) {
		this.cardImg = cardImg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCardState() {
		return cardState;
	}

	public void setCardState(String cardState) {
		this.cardState = cardState;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	

}
