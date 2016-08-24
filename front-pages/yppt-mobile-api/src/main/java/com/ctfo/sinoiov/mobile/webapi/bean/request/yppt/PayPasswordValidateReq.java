package com.ctfo.sinoiov.mobile.webapi.bean.request.yppt;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：验证支付密码及短信验证码
 * <br>
 * 描述：
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
 * <tr><td>1.0</td><td>2015-1-26</td><td>JiangXF</td><td>创建</td></tr>
 * </table>
 * <br>
 * <font color="#FF0000">注意: 本内容仅限于[北京中交兴路车联网科技有限公司]内部使用，禁止转发</font>
 * <br>
 * 
 * @version 1.0
 * 
 * @author JiangXF
 * @since JDK1.6
 */
public class PayPasswordValidateReq extends BaseBeanReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String payPassword;		//支付密码
	
	private String messageCode;		//短信验证码
	
	private String messageCodeId;	//验证码唯一标识

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessageCodeId() {
		return messageCodeId;
	}

	public void setMessageCodeId(String messageCodeId) {
		this.messageCodeId = messageCodeId;
	}
	
	
}
