package com.ctfo.sinoiov.mobile.webapi.bean.response.zcpt;

import java.io.Serializable;

import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;

/**
 * 发送短信返回对象
 * 
 * @author sunchuanfu
 */
public class GetSmsCodeRsp implements Body, Serializable {
	private static final long serialVersionUID = 1306321460190701261L;
	
	private String data = PublicStaticParam.RESULT_FAIL;// 0成功;1失败

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
