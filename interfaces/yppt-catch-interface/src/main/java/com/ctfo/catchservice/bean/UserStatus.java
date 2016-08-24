package com.ctfo.catchservice.bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: UserStatus
 * @Description: 返回用户状态等信息
 * @author yuguangyang
 * @date 2015年2月3日 下午4:18:44
 *
 */
public class UserStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String status;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
}
