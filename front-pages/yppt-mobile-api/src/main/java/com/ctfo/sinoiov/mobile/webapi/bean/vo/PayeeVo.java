package com.ctfo.sinoiov.mobile.webapi.bean.vo;

import com.ctfo.sinoiov.mobile.webapi.util.PropertityCopyAnnotation;

public class PayeeVo {
	
	/**
	 *  	参数编码	是否必填	说明
			收款人ID	ID 	是	类型：字符串；
			银行名称	bankName	是	类型：字符串；用户编码
			卡号	cardNum	是	类型：字符串；

	 */
	private String payeeId;	//收款人ID
	
	private String bankName;	//银行名称
	
	private String cardNum;
	
	private String companyName;

	public String getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
