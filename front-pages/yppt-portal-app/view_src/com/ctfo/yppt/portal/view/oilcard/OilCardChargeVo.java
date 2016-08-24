package com.ctfo.yppt.portal.view.oilcard;

import java.io.Serializable;

/**
 * 油卡充值
 * 
 * @author fuxiaohui
 *
 * @datetime 2014-11-29 下午02:01:22
 *
 */
public class OilCardChargeVo implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -294564138348329955L;
    private String id;
    private String cardNO;
    private String carNO;
    private String balance;
    private String rechargeAmount;
    private String payImg;
    
    public String getPayImg() {
        if(payImg != null && payImg.contains("min_")) {
            payImg = payImg.substring(4);
        }
        return payImg;
    }
    public void setPayImg(String payImg) {
        this.payImg = payImg;
    }
    public String getCardNO() {
        return cardNO;
    }
    public void setCardNO(String cardNO) {
        this.cardNO = cardNO;
    }
    public String getCarNO() {
        return carNO;
    }
    public void setCarNO(String carNO) {
        this.carNO = carNO;
    }
    public String getBalance() {
        return balance;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }
    public String getRechargeAmount() {
        return rechargeAmount;
    }
    public void setRechargeAmount(String rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
