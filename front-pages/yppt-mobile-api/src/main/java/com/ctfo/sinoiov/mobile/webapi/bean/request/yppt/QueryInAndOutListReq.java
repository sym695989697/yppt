package com.ctfo.sinoiov.mobile.webapi.bean.request.yppt;

public class QueryInAndOutListReq extends BaseBeanReq{

	/**
	 * 参数名称	参数编码	是否必填	说明
		用户Id	userId	是	类型：字符串；
		筛选标示	tradeType		类型：int  0:全部 1：收入 2：支出
		页数	page	是	类型：int
		页面记录条数	pageSize	是	类型：int


	 */
	private static final long serialVersionUID = 1L;
	
	private int page;   //页数
	
	private int pageSize;  //页面记录条数
	
	private int tradeType;  //0:查全部 1：查收入 2：查支出     筛选标示
	

	public int getTradeType() {
		return tradeType;
	}

	public void setTradeType(int tradeType) {
		this.tradeType = tradeType;
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
	
	

}