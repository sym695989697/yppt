package com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt;

import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.BaseBeanReq;

/**
 * 发送短信参数对象
 * 
 * @author sunchuanfu
 */
public class SendMobileMsgReq extends BaseBeanReq {
	private static final long serialVersionUID = 3056807988754683843L;

	private String mobiles;// 电话号码(多个号码中间以英文符号","分格)
	private String content;// 短信内容

	public String getMobiles() {
		return mobiles;
	}

	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
