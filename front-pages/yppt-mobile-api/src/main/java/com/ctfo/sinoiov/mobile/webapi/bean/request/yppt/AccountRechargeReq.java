package com.ctfo.sinoiov.mobile.webapi.bean.request.yppt;

import java.io.File;

/**
 * 
 * 帐号充值申请
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年2月6日    dxs    新建
 * </pre>
 */
public class AccountRechargeReq extends BaseBeanReq {

	/**
	 * 参数名称	参数编码	是否必填	说明
		付款人	payer	是	类型：字符串
		用户ID	userId	是	类型：字符串
		付款账号	payAccount	是	类型：整型；
		收款人	payee	是	类型：整型；
		汇款金额	remittance	是	类型：double
		付款日期	payTime	是	类型：字符串
		付款凭证上传	imageClass	是	类型：String 格式："transportPermitImg:1,vehicleImage:1",请参照车旺APP图片传输方式
		付款凭证上传文件流	----(无参数)	是	参照车旺APP图片传输方式

	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 付款人标识
	 */
	private String payer;
	
	/**
	 * 付款人名字
	 */
	private String payerName;
	
	/**
	 * 付款账号
	 */
	private String payAccount; 
	/**
	 * 收款人账号
	 */
	private String payee;
	/**
	 * 收款人名字
	 */
	private String payeeName;
	
	/**
	 * 汇款金额
	 */
	private String remittance; 
	/**
	 * 付款日期
	 */
	private String payTime; 
	/**
	 * 付款凭证
	 */
	private File payProof;
	
	/**
	 * 资金帐户内部帐号 
	 */
	private  String insideAccountNo ;
	
	
	/**
	 * 
	 */
	private String imageClass;
	
	public String getImageClass() {
		return imageClass;
	}
	public void setImageClass(String imageClass) {
		this.imageClass = imageClass;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPayer() {
		return payer;
	}
	public void setPayer(String payer) {
		this.payer = payer;
	}
	public String getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	
	public String getRemittance() {
		return remittance;
	}
	public void setRemittance(String remittance) {
		this.remittance = remittance;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public File getPayProof() {
		return payProof;
	}
	public void setPayProof(File payProof) {
		this.payProof = payProof;
	}
	/**
	 * @return the payerName
	 */
	public String getPayerName() {
		return payerName;
	}
	/**
	 * @param payerName the payerName to set
	 */
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	/**
	 * @return the payeeName
	 */
	public String getPayeeName() {
		return payeeName;
	}
	/**
	 * @param payeeName the payeeName to set
	 */
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getInsideAccountNo() {
		return insideAccountNo;
	}
	public void setInsideAccountNo(String insideAccountNo) {
		this.insideAccountNo = insideAccountNo;
	}
	
}
