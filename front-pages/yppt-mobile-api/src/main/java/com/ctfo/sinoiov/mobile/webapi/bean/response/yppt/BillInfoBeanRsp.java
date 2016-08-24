package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：发票详情信息
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
 * <tr><td>1.0</td><td>2015-1-30</td><td>JiangXF</td><td>创建</td></tr>
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
public class BillInfoBeanRsp extends BaseBeanRsp {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String billType;	//发票类型
	
	private String billTitle;	//发票抬头
	
	/*********图片对应字段**********/
	
	private String billInfo;	//开票信息
	
	private String businessLicense;	//营业执照
	
	private String taxPayerCertificates;	//纳税人证明
	
	private String orgCode;		//组织机构代码证
	
	private String taxCertificates;	//税务登记证

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBillTitle() {
		return billTitle;
	}

	public void setBillTitle(String billTitle) {
		this.billTitle = billTitle;
	}

	public String getBillInfo() {
		return billInfo;
	}

	public void setBillInfo(String billInfo) {
		this.billInfo = billInfo;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getTaxPayerCertificates() {
		return taxPayerCertificates;
	}

	public void setTaxPayerCertificates(String taxPayerCertificates) {
		this.taxPayerCertificates = taxPayerCertificates;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getTaxCertificates() {
		return taxCertificates;
	}

	public void setTaxCertificates(String taxCertificates) {
		this.taxCertificates = taxCertificates;
	}
	
	
}
