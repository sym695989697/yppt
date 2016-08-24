package com.ctfo.sinoiov.mobile.webapi.bean.vo;

import com.ctfo.sinoiov.mobile.webapi.util.PropertityCopyAnnotation;

public class CardTypeVo {
	
	/**
	 * 参数名称	参数编码	是否必填	说明
	卡LOGO	logo	是	类型：字符串；logo的URL
	卡名称	cardName	是	类型：字符串；
	卡描述	cardDesc	是	类型：字符串；
	卡类型	cardType		类型：字符串

	 */
	private String logo;  //卡LOGO   logo的URL
	
	private String cardName; //卡名称
	
	private String cardType; //卡类型
	
	@PropertityCopyAnnotation(destFieldName = "description")
	private String cardDesc;   //卡描述
	
	
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardDesc() {
		return cardDesc;
	}

	public void setCardDesc(String cardDesc) {
		this.cardDesc = cardDesc;
	}
	
}
