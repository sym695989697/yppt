package com.ctfo.yppt.portal.view.billing;

import java.io.Serializable;
/**
 * 开票信息VO
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-30 下午04:26:08
 *
 */
public class BillingInfoVo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 7432847284768424043L;

    private String billingType;
    private String billingTitle;
    private String dwyyzzImg;
    private String nszzImg;
    private String zzjgdmImg;
    private String swdjImg;
    private String kpfjImg;
    public String getBillingType() {
        return billingType;
    }
    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }
    public String getBillingTitle() {
        return billingTitle;
    }
    public void setBillingTitle(String billingTitle) {
        this.billingTitle = billingTitle;
    }
    public String getDwyyzzImg() {
        if(dwyyzzImg != null && dwyyzzImg.contains("min_")) {
            dwyyzzImg = dwyyzzImg.substring(4);
        }
        return dwyyzzImg;
    }
    public void setDwyyzzImg(String dwyyzzImg) {
        this.dwyyzzImg = dwyyzzImg;
    }
    public String getNszzImg() {
        if(nszzImg != null && nszzImg.contains("min_")) {
            nszzImg = nszzImg.substring(4);
        }
        return nszzImg;
    }
    public void setNszzImg(String nszzImg) {
        this.nszzImg = nszzImg;
    }
    public String getZzjgdmImg() {
        if(zzjgdmImg != null && zzjgdmImg.contains("min_")) {
            zzjgdmImg = zzjgdmImg.substring(4);
        }
        return zzjgdmImg;
    }
    public void setZzjgdmImg(String zzjgdmImg) {
        this.zzjgdmImg = zzjgdmImg;
    }
    public String getSwdjImg() {
        if(swdjImg != null && swdjImg.contains("min_")) {
            swdjImg = swdjImg.substring(4);
        }
        return swdjImg;
    }
    public void setSwdjImg(String swdjImg) {
        this.swdjImg = swdjImg;
    }
    public String getKpfjImg() {
        if(kpfjImg != null && kpfjImg.contains("min_")) {
            kpfjImg = kpfjImg.substring(4);
        }
        return kpfjImg;
    }
    public void setKpfjImg(String kpfjImg) {
        this.kpfjImg = kpfjImg;
    }
    
}
