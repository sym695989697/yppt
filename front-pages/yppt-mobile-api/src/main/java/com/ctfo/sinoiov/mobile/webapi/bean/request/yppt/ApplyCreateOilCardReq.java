package com.ctfo.sinoiov.mobile.webapi.bean.request.yppt;

import com.ctfo.sinoiov.mobile.webapi.util.PropertityCopyAnnotation;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;

public class ApplyCreateOilCardReq extends BaseBeanReq implements Body{

	/**
	 * 参数名称	参数编码	是否必填	说明
		卡类型	cardType	是	例如：0：表示金卡；1：表示普通卡
		用户ID	userID	是	类型：字符串
		绑定车牌号	vehicleNum	是	类型：字符串
		车牌颜色	vehicleColor	是	类型：字符串
		卡绑定手机号	telNum	是	类型：字符串
		接受短信	acceptMessage	是	类型：字符串；0：表示不接收；1：接收
		邮寄地址	addressId	是	类型：字符串;邮寄地址唯一标示
		发卡地区	cardCreateDept	是	类型：字符串

	 */
	private static final long serialVersionUID = 1L;
	
	@PropertityCopyAnnotation(destFieldName = "cardType")
	private String cardType; 		//卡类型
		
	private String vehicleNum;	//绑定车牌号
	
	@PropertityCopyAnnotation(destFieldName = "phoneNum")
	private String telNum;		//卡绑定手机号
	
	private int acceptMessage;	//接受短信
	
	@PropertityCopyAnnotation(destFieldName = "address")
	private String address;	//邮寄地址
	
	private String vehicleColor;
	
	@PropertityCopyAnnotation(destFieldName = "province")
	private String province;	//邮件省
	
	@PropertityCopyAnnotation(destFieldName = "city")
	private String city;	//邮件市
	
	@PropertityCopyAnnotation(destFieldName = "district")
	private String county;	//邮件县
	
	
	private String cardCreateDept;
	
	@PropertityCopyAnnotation(destFieldName = "idNo")
	private String identityCard;		//身份证
	
	@PropertityCopyAnnotation(destFieldName = "oftenArea")
	private String commonArea;		//常用地址
	
	@PropertityCopyAnnotation(destFieldName = "receiverName")
	private String receivePerson;	//收件人名称
	
	@PropertityCopyAnnotation(destFieldName = "receiverPhoneNum")
	private String receiverPhoneNum;	//收件人手机号
	
	/***********图片**************/
	private String vehicleLicense;	//车辆行驶证图片

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getVehicleNum() {
		return vehicleNum;
	}

	public void setVehicleNum(String vehicleNum) {
		this.vehicleNum = vehicleNum;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public int getAcceptMessage() {
		return acceptMessage;
	}

	public void setAcceptMessage(int acceptMessage) {
		this.acceptMessage = acceptMessage;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCardCreateDept() {
		return cardCreateDept;
	}

	public void setCardCreateDept(String cardCreateDept) {
		this.cardCreateDept = cardCreateDept;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getCommonArea() {
		return commonArea;
	}

	public void setCommonArea(String commonArea) {
		this.commonArea = commonArea;
	}

	public String getReceivePerson() {
		return receivePerson;
	}

	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}

	public String getReceiverPhoneNum() {
		return receiverPhoneNum;
	}

	public void setReceiverPhoneNum(String receiverPhoneNum) {
		this.receiverPhoneNum = receiverPhoneNum;
	}

	public String getVehicleLicense() {
		return vehicleLicense;
	}

	public void setVehicleLicense(String vehicleLicense) {
		this.vehicleLicense = vehicleLicense;
	}
	

}
