package com.ctfo.yppt.portal.view.oilcard;

import java.io.Serializable;
/**
 * 油卡充值支付
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午05:16:52
 *
 */
public class OilCardPayVo implements Serializable{
	
	private static final long serialVersionUID = 2712125649429385515L;

	private String paycheck1;  //旺金币 1使用 0不使用
	private String paycheck2;  //账户余额1使用0不使用
	private String paychecknum1;  //旺金币总值
	private String paychecknum2;  //账户余额总值
	private String orderId; //订单ID
    public String getPaychecknum1() {
		return paychecknum1;
	}
	public void setPaychecknum1(String paychecknum1) {
		this.paychecknum1 = paychecknum1;
	}
	public String getPaychecknum2() {
		return paychecknum2;
	}
	public void setPaychecknum2(String paychecknum2) {
		this.paychecknum2 = paychecknum2;
	}
	public String getPaycheck1() {
		return paycheck1;
	}
	public void setPaycheck1(String paycheck1) {
		this.paycheck1 = paycheck1;
	}
	public String getPaycheck2() {
		return paycheck2;
	}
	public void setPaycheck2(String paycheck2) {
		this.paycheck2 = paycheck2;
	}
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
