package com.ctfo.yppt.bean;

import java.util.List;

import com.ctfo.common.models.BaseSerializable;
import com.ctfo.yppt.baseservice.card.cons.AutoArrayList;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApply;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyDetail;

/**
 * IC卡充值申请MetaBean
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-25 下午04:47:20
 *
 */
public class ICCardRechageApplyMetaBean extends BaseSerializable{
	
	private static final long serialVersionUID = 1L;
	
	private ICRechargeApply rechargeApply = new ICRechargeApply();
	
	private List<ICRechargeApplyDetail> rechargeDeatils=new AutoArrayList(ICRechargeApplyDetail.class);

	public ICRechargeApply getRechargeApply() {
		return rechargeApply;
	}

	public void setRechargeApply(ICRechargeApply rechargeApply) {
		this.rechargeApply = rechargeApply;
	}

	public List<ICRechargeApplyDetail> getRechargeDeatils() {
		return rechargeDeatils;
	}

	public void setRechargeDeatils(List<ICRechargeApplyDetail> rechargeDeatils) {
		this.rechargeDeatils = rechargeDeatils;
	}

	
	
	
	


}
