package com.ctfo.sinoiov.mobile.webapi.bean.vo;

import com.ctfo.ccapp.beans.PropertityCopyAnnotation;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;

public class AddressVo implements Body {
	@PropertityCopyAnnotation(destFieldName = "address")  
	private String addressId; 	//邮件地址id
	
	private String name;		//姓名
	
	@PropertityCopyAnnotation(destFieldName = "phoneNum")
	private String telNum;    	//电话
	
	private String address;		//详细地址
	
	private String province;	//省
	
	private String city;		//市
	
	private String county;		//县
	
	private String post;	//邮编
	
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

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}
	

}
