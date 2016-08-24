package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

import java.math.BigDecimal;


public class CWPersonCentRsp extends BaseBeanRsp{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String balance;  //我的资金账户余额
	
	private int totalCoinCount; //我的旺金币数量


	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public int getTotalCoinCount() {
		return totalCoinCount;
	}

	public void setTotalCoinCount(int totalCoinCount) {
		this.totalCoinCount = totalCoinCount;
	}
	
	

}
