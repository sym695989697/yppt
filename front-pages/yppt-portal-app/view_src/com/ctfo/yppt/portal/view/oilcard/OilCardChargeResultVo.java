package com.ctfo.yppt.portal.view.oilcard;

import java.io.Serializable;
/**
 * 油卡订单返回结果VO
 * @author fuxiaohui
 *
 * @datetime 2015-1-29 下午05:26:37
 *
 */
public class OilCardChargeResultVo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 3690612044592662886L;
    private String url;
    private String OrderId;
    private boolean isOnlyCredit;
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getOrderId() {
        return OrderId;
    }
    public void setOrderId(String orderId) {
        OrderId = orderId;
    }
    public boolean isOnlyCredit() {
        return isOnlyCredit;
    }
    public void setOnlyCredit(boolean isOnlyCredit) {
        this.isOnlyCredit = isOnlyCredit;
    }
    
}
