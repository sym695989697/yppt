package com.ctfo.sinoiov.mobile.webapi.bean.vo;

import com.ctfo.sinoiov.mobile.webapi.util.PropertityCopyAnnotation;

public class VehicleVo {
	
	private String id;	//车牌ID
	@PropertityCopyAnnotation(destFieldName = "licenseNumber")  
	private String vehicleNum; //车牌号
	@PropertityCopyAnnotation(destFieldName = "licenseColor")  
	private String vehicleColor ; //车牌颜色
	
	private String state;   //车辆状态
	
	private String cardNO;		//卡号

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCardNO() {
		return cardNO;
	}

	public void setCardNO(String cardNO) {
		this.cardNO = cardNO;
	}

	public String getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	public String getVehicleNum() {
		return vehicleNum;
	}

	public void setVehicleNum(String vehicleNum) {
		this.vehicleNum = vehicleNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
