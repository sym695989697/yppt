package com.ctfo.common.models;

import java.io.Serializable;
import java.util.List;

public class Index implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String platform;
	private String platformName;
	
	private String systemId;
	private String systemName;
	private String systemSign;
	
	private String userId;
	private String userName;
	private String userLogin;
	private String userType;
	private String userOwningOrgId;
	
	private String orgId;
	private String orgName;
	
	private String roleId;
	private String roleName;
	
	private List roleList;
	
	//车后会员id
    private String memberId;
    //车后会员编码
    //private String staffCode;
	//手机号
	private String mobile;
	//审核状态(0：待审核;1：审核通过(认证通过);2：审核中;3：审核失败);//卡申请,资金账户充值;卡充值;---如果未认证;提示用户,跳到车旺的认证页面(页面URL待确认);
	private String checkStatus;
	
	private String headPicURL;
	
	private String idcardNo;
	
	//////////set get/////////////////
	
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getSystemSign() {
		return systemSign;
	}
	public void setSystemSign(String systemSign) {
		this.systemSign = systemSign;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getUserOwningOrgId() {
		return userOwningOrgId;
	}
	public void setUserOwningOrgId(String userOwningOrgId) {
		this.userOwningOrgId = userOwningOrgId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public List getRoleList() {
		return roleList;
	}
	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
//    public String getStaffCode() {
//        return staffCode;
//    }
//    public void setStaffCode(String staffCode) {
//        this.staffCode = staffCode;
//    }
	public String getHeadPicURL() {
		return headPicURL;
	}
	public void setHeadPicURL(String headPicURL) {
		this.headPicURL = headPicURL;
	}
	public String getIdcardNo() {
		return idcardNo;
	}
	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}
}
