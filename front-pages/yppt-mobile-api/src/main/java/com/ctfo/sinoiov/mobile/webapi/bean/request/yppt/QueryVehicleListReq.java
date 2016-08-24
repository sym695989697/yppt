package com.ctfo.sinoiov.mobile.webapi.bean.request.yppt;

public class QueryVehicleListReq extends BaseBeanReq{

	/**
	 * 参数名称	参数编码	是否必填	说明
		用户id	userId	是	类型：字符串；用户ID
		页数	page	是	类型：int；
		记录条数	pageSize	是	类型：int；

	 */
	private static final long serialVersionUID = 1L;
	
	private int page ; 					//页数
	
	private int pageSize ;				//页面记录条数

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
