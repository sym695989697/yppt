package com.ctfo.sinoiov.mobile.webapi.bean.request.yppt;

public class QueryOilCardTypeReq extends BaseBeanReq{

	/**
	 *   参数名称	参数编码	是否必填	说明
		用户ID	userId	是	类型：字符串；
		页数	Page		Int
		每页记录数	pageSize		Int

	 */
	private static final long serialVersionUID = 1L;
	
//	private String userId;
	
	private String cardType; // 卡类型
	
	private int page =0;
	
	private int pageSize =10;
	

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
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

//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//	

}
