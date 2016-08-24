package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

public class CreateCardResultRsp extends BaseBeanRsp{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String result;  //结果详情；静态页面的URL

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
