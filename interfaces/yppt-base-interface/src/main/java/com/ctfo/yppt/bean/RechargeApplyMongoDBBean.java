package com.ctfo.yppt.bean;

import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApply;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
/**
 * 
 * @author rao yongbing 
 * @Description交易订单表
 * 2015年2月9日
 */
@Entity(value="RechargeApply_MongoDB_Bean", noClassnameStored=true)
public class RechargeApplyMongoDBBean extends ICRechargeApply {
	
	@Id
	private String mid;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}
	
	

}