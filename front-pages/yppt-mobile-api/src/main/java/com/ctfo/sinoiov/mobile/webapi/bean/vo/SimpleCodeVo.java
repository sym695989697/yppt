package com.ctfo.sinoiov.mobile.webapi.bean.vo;

import com.ctfo.ccapp.beans.PropertityCopyAnnotation;

public class SimpleCodeVo {
	
	@PropertityCopyAnnotation(destFieldName = "openCardOrgCode")
	private String name;	//卡发机关名称
	
	private String code;	//卡发机关编码
	
	private String ID;		//机关标示

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
}
