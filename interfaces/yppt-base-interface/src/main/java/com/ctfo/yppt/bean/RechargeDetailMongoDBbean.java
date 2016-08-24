package com.ctfo.yppt.bean;

import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyDetail;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
/***
 * 
 * @author rao yongbing 
 * @Description 交易订单祥请
 * 2015年2月9日
 */
@Entity(value="Recharge_Detail_MongoDB_Bean", noClassnameStored=true)
public class RechargeDetailMongoDBbean  extends ICRechargeApplyDetail{
	
	private static final long serialVersionUID = 1L;
	@Id
	private String mid;

	
}
