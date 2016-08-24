package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

public class RechargeApplyRsp extends BaseBeanRsp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//使用父类中的字段
	//....
	
	private String result;	//申请状态
	
	private String msg;  //申请反馈信息
	

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
