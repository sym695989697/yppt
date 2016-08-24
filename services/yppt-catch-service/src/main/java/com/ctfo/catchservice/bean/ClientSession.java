package com.ctfo.catchservice.bean;

import java.io.Serializable;

import org.apache.commons.httpclient.HttpClient;

/**
 * 客户端登录SESSION信息
 * 
 * @author jichao
 */
public class ClientSession implements Serializable {
	private static final long serialVersionUID = 1L;

	public ClientSession(HttpClient client, String cookies, boolean isLogin) {
		this.client = client;
		this.cookies = cookies;
		this.isLogin = isLogin;
	}
	private HttpClient client;
	private String cookies="";
	private boolean isLogin = false;//是否登录
	private String cardAreaCode;//发卡地区
	private String ownOrg;//所属组织
	private String cardType;
	
	public HttpClient getClient() {
		return client;
	}

	public void setClient(HttpClient client) {
		this.client = client;
	}

	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void isLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public String getCardAreaCode() {
		return cardAreaCode;
	}

	public void setCardAreaCode(String cardAreaCode) {
		this.cardAreaCode = cardAreaCode;
	}

	public String getOwnOrg() {
		return ownOrg;
	}

	public void setOwnOrg(String ownOrg) {
		this.ownOrg = ownOrg;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	

}
