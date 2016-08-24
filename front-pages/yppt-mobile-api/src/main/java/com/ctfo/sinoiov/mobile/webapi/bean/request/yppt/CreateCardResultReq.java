package com.ctfo.sinoiov.mobile.webapi.bean.request.yppt;

public class CreateCardResultReq extends BaseBeanReq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId; //用户ID

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
