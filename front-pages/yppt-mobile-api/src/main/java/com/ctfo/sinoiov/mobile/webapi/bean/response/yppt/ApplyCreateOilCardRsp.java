package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

public class ApplyCreateOilCardRsp extends BaseBeanRsp{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private int result;	//状态码
	
	private String  msg;	//错误信息

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
