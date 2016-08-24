package com.ctfo.yppt.bean;


import java.io.Serializable;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity(value="yppt_message_signature", noClassnameStored=true)
public class MessageSignature implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5422409449819877861L;
	
	@Id
	private String id;

	private String sign;//短信签名

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign() {
		return sign;
	}
}
