package com.ctfo.yppt.baseservice.recharge.intf;

import com.ctfo.base.baseservice.IBaseManager;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyDetail;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyDetailExampleExtended;
import com.ctfo.yppt.bean.RechargeApplyDetailInfoBean;
/**
 * 
 * @author rao yongbing 
 * @Description 充值详情
 * 2015年2月6日
 */
public interface IICRechargeApplyDetailManager extends IBaseManager<ICRechargeApplyDetail, ICRechargeApplyDetailExampleExtended>  {

	public RechargeApplyDetailInfoBean getRechargeInfoForApp(String detailId)throws BusinessException;
}
