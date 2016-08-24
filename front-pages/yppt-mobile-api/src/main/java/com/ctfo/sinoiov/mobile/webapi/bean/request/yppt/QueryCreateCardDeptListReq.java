package com.ctfo.sinoiov.mobile.webapi.bean.request.yppt;

public class QueryCreateCardDeptListReq extends BaseBeanReq{

	/**
	 * 参数名称	参数编码	是否必填	说明
		卡类型	cardType	是	类型：字符串；
		用户ID	userId	是	类型：字符串
		页数	page	是	类型：int；
		页面记录条数	pageSize	是	类型：int；

	 */
	private static final long serialVersionUID = 1L;
	
	private String userId ;		//用户Id
	
	private String cardType;  //卡类型
	
	private int page; 		//页数
	
	private int pageSize;   //每页记录数
	
	

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

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
