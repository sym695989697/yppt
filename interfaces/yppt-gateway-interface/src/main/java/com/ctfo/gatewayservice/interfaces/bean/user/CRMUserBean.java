package com.ctfo.gatewayservice.interfaces.bean.user;

import com.ctfo.csm.uaa.dao.beans.UAAUser;

/**
 * 带有crm字段的bean
 * 
 * @author 徐宝
 */
public class CRMUserBean extends UAAUser {
	private static final long serialVersionUID = 1L;
	private String crmName;
	private String crmMobile;
	private String crmCustomerType;
	private String crmCheckStatus;
	private String crmEnterpriseCheckStatus;
	private String crmHeadPicID;
	private String crmHeadPicURL;
	private String crmIdcardNo;

	public String getCrmName() {
		return crmName;
	}

	public void setCrmName(String crmName) {
		this.crmName = crmName;
	}

	public String getCrmMobile() {
		return crmMobile;
	}

	public void setCrmMobile(String crmMobile) {
		this.crmMobile = crmMobile;
	}

	public String getCrmCustomerType() {
		return crmCustomerType;
	}

	public void setCrmCustomerType(String crmCustomerType) {
		this.crmCustomerType = crmCustomerType;
	}

	public String getCrmCheckStatus() {
		return crmCheckStatus;
	}

	public void setCrmCheckStatus(String crmCheckStatus) {
		this.crmCheckStatus = crmCheckStatus;
	}

	public String getCrmEnterpriseCheckStatus() {
		return crmEnterpriseCheckStatus;
	}

	public void setCrmEnterpriseCheckStatus(String crmEnterpriseCheckStatus) {
		this.crmEnterpriseCheckStatus = crmEnterpriseCheckStatus;
	}

	public String getCrmHeadPicID() {
		return crmHeadPicID;
	}

	public void setCrmHeadPicID(String crmHeadPicID) {
		this.crmHeadPicID = crmHeadPicID;
	}

	public String getCrmHeadPicURL() {
		return crmHeadPicURL;
	}

	public void setCrmHeadPicURL(String crmHeadPicURL) {
		this.crmHeadPicURL = crmHeadPicURL;
	}

	public String getCrmIdcardNo() {
		return crmIdcardNo;
	}

	public void setCrmIdcardNo(String crmIdcardNo) {
		this.crmIdcardNo = crmIdcardNo;
	}
}
