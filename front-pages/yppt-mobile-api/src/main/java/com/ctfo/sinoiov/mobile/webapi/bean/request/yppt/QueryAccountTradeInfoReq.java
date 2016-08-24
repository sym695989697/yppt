package com.ctfo.sinoiov.mobile.webapi.bean.request.yppt;

public class QueryAccountTradeInfoReq extends BaseBeanReq {
	
	/**
	 * 参数名称	参数编码	是否必填	说明
		用户Id	userId	是	类型：字符串
		收支交易ID	id	是	类型:字符串

	 */
	private static final long serialVersionUID = 1L;

	private String userId;  //用户ID
	
	private String tradeId;  //交易ID
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	
	

}
