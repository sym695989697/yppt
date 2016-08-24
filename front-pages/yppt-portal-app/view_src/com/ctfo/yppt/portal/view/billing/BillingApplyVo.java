package com.ctfo.yppt.portal.view.billing;

import java.io.Serializable;
/**
 * 开票申请VO
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-30 下午04:26:08
 *
 */
public class BillingApplyVo implements Serializable{

    private static final long serialVersionUID = -7335508406588251520L;
    private String recName;
    private String recMobi;
    private String recProvince;
    private String recCity;
    private String recDistrict;
    private String recAddress;
    private String recZipCode;
    private String billingInfoId;
    private String billingMoney;
    private String recAddressId;
    public String getRecName() {
        return recName;
    }
    public void setRecName(String recName) {
        this.recName = recName;
    }
    public String getRecMobi() {
        return recMobi;
    }
    public void setRecMobi(String recMobi) {
        this.recMobi = recMobi;
    }
    public String getRecProvince() {
        return recProvince;
    }
    public void setRecProvince(String recProvince) {
        this.recProvince = recProvince;
    }
    public String getRecCity() {
        return recCity;
    }
    public void setRecCity(String recCity) {
        this.recCity = recCity;
    }
    public String getRecDistrict() {
        return recDistrict;
    }
    public void setRecDistrict(String recDistrict) {
        this.recDistrict = recDistrict;
    }
    public String getRecAddress() {
        return recAddress;
    }
    public void setRecAddress(String recAddress) {
        this.recAddress = recAddress;
    }
    public String getRecZipCode() {
        return recZipCode;
    }
    public void setRecZipCode(String recZipCode) {
        this.recZipCode = recZipCode;
    }
    public String getBillingInfoId() {
        return billingInfoId;
    }
    public void setBillingInfoId(String billingInfoId) {
        this.billingInfoId = billingInfoId;
    }
    public String getBillingMoney() {
        return billingMoney;
    }
    public void setBillingMoney(String billingMoney) {
        this.billingMoney = billingMoney;
    }
	public String getRecAddressId() {
		return recAddressId;
	}
	public void setRecAddressId(String recAddressId) {
		this.recAddressId = recAddressId;
	}
   
}
