package com.ctfo.sinoiov.mobile.webapi.bean.request.yppt;

public class QueryOilCardInfoReq extends BaseBeanReq{
	
	/**
	 * 参数名称	参数编码	是否必填	说明
		用户id	userId	是	类型：字符串；用户的ID
		卡id	cardId	是	类型：字符串；

	 */
	private static final long serialVersionUID = 1L;

	
	private String cardId ;			//卡id
	
	private String userId;		//用户id

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

}
