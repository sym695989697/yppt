package com.ctfo.sinoiov.mobile.webapi.bean.request.yppt;

public class QueryHistoryRebateListReq extends BaseBeanReq{

	/**
	 * 
	 */
private static final long serialVersionUID = 1L;
	
	private int page;
	
	private int pageSize;
	
	private String model;// "收支类型(以账户的角度：1收入，2支出)"
	

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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
