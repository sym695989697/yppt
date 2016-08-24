package com.ctfo.chpt.bean.iccard.catchdata.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: CatchdataGetYZMVO
 * @Description:返回调用远程baseservice，根据用户名，类型返回的验证码相关信息
 * @author yuguangyang
 * @date 2015年2月2日 下午5:28:05
 *
 */
@XmlRootElement(name = "CatchdataGetYZMVO")
public class CatchdataGetYZMVO  implements Serializable {
	private static final long serialVersionUID = 3487256737106754041L;
	private String username;
	private String yzmUrl;
	private String cookies;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getYzmUrl() {
		return yzmUrl;
	}
	public void setYzmUrl(String yzmUrl) {
		this.yzmUrl = yzmUrl;
	}
	public String getCookies() {
		return cookies;
	}
	public void setCookies(String cookies) {
		this.cookies = cookies;
	}
}
