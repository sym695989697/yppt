package com.ctfo.sinoiov.mobile.webapi.bean.request.yppt;

public class AddPostAddressReq extends BaseBeanReq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId ;				//用户id
	
	private String name;				//收件人姓名
	
	private String telNum;				//手机号码
	
	private String postcode;			//邮政编码
	
	private String province;			//省
	
	private String city; 				//市/区
	
	private String county; 				//县
	
	private String address; 			//详细地址

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
