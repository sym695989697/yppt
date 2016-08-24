package com.ctfo.catchservice.bean;
/**
 * 
 * @ClassName: CatchDataVO
 * @Description:保存数据抓取，定义数据结构，此为父类
 * @author yuguangyang
 * @date 2015年2月28日 下午5:59:52
 *
 */
public class CatchDataVO {
	
	String userName;
	String hostType;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHostType() {
		return hostType;
	}
	public void setHostType(String hostType) {
		this.hostType = hostType;
	}
}
