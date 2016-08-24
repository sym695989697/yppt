package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

public class AccountRechargeRsp extends BaseBeanRsp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String result; //充值结果反馈
	
	private String attachId;	//付款凭证附件名称

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}
	
	
}
