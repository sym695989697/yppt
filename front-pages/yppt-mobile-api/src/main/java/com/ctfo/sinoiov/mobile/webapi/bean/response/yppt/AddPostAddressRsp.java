package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

public class AddPostAddressRsp extends BaseBeanRsp{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int result;  //添加状态
	
	private String msg;	//添加反馈信息

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
