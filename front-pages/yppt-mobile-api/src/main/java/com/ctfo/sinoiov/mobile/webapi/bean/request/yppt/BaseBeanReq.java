package com.ctfo.sinoiov.mobile.webapi.bean.request.yppt;

import java.io.Serializable;

/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： pltp-webapi
 * <br>
 * 功能：基础实体
 * <br>
 * 描述：包括各实体中一些公共的属性
 * <br>
 * 授权 : (C) Copyright (c) 2011
 * <br>
 * 公司 : 北京中交车联科技服务有限公司
 * <br>
 * -----------------------------------------------------------------------------
 * <br>
 * 修改历史
 * <br>
 * <table width="432" border="1">
 * <tr><td>版本</td><td>时间</td><td>作者</td><td>改变</td></tr>
 * <tr><td>1.0</td><td>2014-3-3</td><td>JiangXF</td><td>创建</td></tr>
 * </table>
 * <br>
 * <font color="#FF0000">注意: 本内容仅限于[北京中交兴路信息科技有限公司]内部使用，禁止转发</font>
 * <br>
 * 
 * @version 1.0
 * 
 * @author JiangXF
 * @since JDK1.6
 */
public class BaseBeanReq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tokenId;		//用户认证
	
	private String userId;		//用户ID

	private String userType;	//用户类型
	
	private String userName;	// 用户名称
	
	private String userLogin;	//登陆账户
		
	private String imageClass;		//用于上传图片
	
	//车后会员id
//	private String memberId;
	private String memberId = "f575a230-f5ed-4693-a574-c112ea1baaf3";
	//车后会员名称
	private String memberName;
	//车后会员类型
	private String memberType;
	//车后会员编码
	private String staffCode;
	//审核状态(0：待审核;1：审核通过(认证通过);2：审核中;3：审核失败);//卡申请,资金账户充值;卡充值;---如果未认证;提示用户,跳到车旺的认证页面(页面URL待确认);
	private String checkStatus;
	//手机号
	private String mobile;
	// 乐观锁
	private String version;
	

	private String id;	
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getImageClass() {
		return imageClass;
	}

	public void setImageClass(String imageClass) {
		this.imageClass = imageClass;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	
}
