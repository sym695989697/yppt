package com.ctfo.sinoiov.mobile.webapi.bean.request.yppt;

public class QueryOneOilCardTradeReq extends BaseBeanReq{

	/**
	 * 参数名称	参数编码	是否必填	说明
		用户id	userId	是	类型：字符串；用户的ID
		卡号	cardNum	是	类型：字符串；用户油卡的编号
		页数	page	是	类型：int；页数
		记录条数	pageSize	是	类型：int；每页记录数

	 */
	private static final long serialVersionUID = 1L;

	private String cardNum;				//卡号
	
//	private String vehicleNum;			//车牌号
	
//	private String vehicleColor;		//车辆颜色
	
	private int page ; 					//页数
	
	private int pageSize ;				//页面记录条数
	
	private String cardId;  		//卡ID   用来查询单张油卡的交易列表
	
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
