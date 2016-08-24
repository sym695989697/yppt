package com.ctfo.chpt.bean.iccard.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class ICCardApplyVO {
	
	@XmlElement(name = "(注册手机号[M];NOMAL_ENUM)imp", required = false)
	private String mobile;
	@XmlElement(name = "(油卡类型;MAP_ENUM)imp", required = false)
	private String cardType;
	@XmlElement(name = "(常用地区1;PROVINCE_ENUM)imp", required = false)
	private String oftenAddress1;
	@XmlElement(name = "(常用地区2;PROVINCE_ENUM)imp", required = false)
	private String oftenAddress2;
	@XmlElement(name = "(常用地区3;PROVINCE_ENUM)imp", required = false)
	private String oftenAddress3;
	@XmlElement(name = "(姓名;NOMAL_ENUM)imp", required = false)
	private String name;
	@XmlElement(name = "(身份证号;NOMAL_ENUM)imp", required = false)
	private String idCardNo;
	@XmlElement(name = "(收件人;NOMAL_ENUM)imp", required = false)
	private String addressee;
	@XmlElement(name = "(收件人手机号;NOMAL_ENUM)imp", required = false)
	private String receiverPhoneNum;
	@XmlElement(name = "(收件省份;PROVINCE_ENUM)imp", required = false)
	private String province;
	@XmlElement(name = "(收件城市;CITY_ENUM)imp", required = false)
	private String city;
	@XmlElement(name = "(收件区县;TOWN_ENUM)imp", required = false)
	private String district;
	@XmlElement(name = "(收件详细地址;NOMAL_ENUM)imp", required = false)
	private String address;
	@XmlElement(name = "(邮编;NOMAL_ENUM)imp", required = false)
	private String zipCode;
	@XmlElement(name = "(快递公司;NOMAL_ENUM)imp", required = false)
	private String expressCompany;
	@XmlElement(name = "(快递信息（单号）;NOMAL_ENUM)imp", required = false)
	private String express;
	@XmlElement(name = "(LIST;com.ctfo.chpt.bean.iccard.vo.CardDetail)imp", required = false)
	List<CardDetail> cardList=new ArrayList<CardDetail>();
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	public String getOftenAddress1() {
		return oftenAddress1;
	}
	public void setOftenAddress1(String oftenAddress1) {
		this.oftenAddress1 = oftenAddress1;
	}
	public String getOftenAddress2() {
		return oftenAddress2;
	}
	public void setOftenAddress2(String oftenAddress2) {
		this.oftenAddress2 = oftenAddress2;
	}
	public String getOftenAddress3() {
		return oftenAddress3;
	}
	public void setOftenAddress3(String oftenAddress3) {
		this.oftenAddress3 = oftenAddress3;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getAddressee() {
		return addressee;
	}
	public String getExpressCompany() {
		return expressCompany;
	}
	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}
	public String getReceiverPhoneNum() {
		return receiverPhoneNum;
	}
	public void setReceiverPhoneNum(String receiverPhoneNum) {
		this.receiverPhoneNum = receiverPhoneNum;
	}
	public void setAddressee(String addressee) {
		this.addressee = addressee;
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getExpress() {
		return express;
	}
	public void setExpress(String express) {
		this.express = express;
	}
	public List<CardDetail> getCardList() {
		return cardList;
	}
	public void setCardList(List<CardDetail> cardList) {
		this.cardList = cardList;
	}
	
	
	


	
	
	
}
