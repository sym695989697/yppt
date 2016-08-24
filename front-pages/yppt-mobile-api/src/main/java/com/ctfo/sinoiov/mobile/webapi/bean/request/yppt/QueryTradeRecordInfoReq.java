package com.ctfo.sinoiov.mobile.webapi.bean.request.yppt;

public class QueryTradeRecordInfoReq extends BaseBeanReq {

	/**
	 * 参数名称	参数编码	是否必填	说明
		用户Id	userId	是	类型：字符串；
		交易id	tradeId	是	类型：字符串；

	 */
	private static final long serialVersionUID = 1L;
	
	

	
	private String tradeId;  //交易id
	private String tradeType;  //交易類型  0：表示加油；1：表示充值

	

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
 
}
