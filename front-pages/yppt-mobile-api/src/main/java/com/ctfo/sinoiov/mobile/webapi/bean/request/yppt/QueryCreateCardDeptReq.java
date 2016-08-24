package com.ctfo.sinoiov.mobile.webapi.bean.request.yppt;

public class QueryCreateCardDeptReq extends BaseBeanReq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId ;		//用户Id

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
