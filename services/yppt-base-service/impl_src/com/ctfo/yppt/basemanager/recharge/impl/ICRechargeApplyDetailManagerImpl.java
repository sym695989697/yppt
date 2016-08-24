package com.ctfo.yppt.basemanager.recharge.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyDetail;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyDetailExampleExtended;
import com.ctfo.yppt.baseservice.recharge.intf.IICRechargeApplyDetailManager;
import com.ctfo.yppt.bean.RechargeApplyDetailInfoBean;

@Service("ICRechargeApplyDetailManagerImpl")
public class ICRechargeApplyDetailManagerImpl extends GenericManagerImpl<ICRechargeApplyDetail, ICRechargeApplyDetailExampleExtended> implements IICRechargeApplyDetailManager {
	private static Log logger = LogFactory.getLog(ICRechargeApplyDetailManagerImpl.class);

	@Override
	public RechargeApplyDetailInfoBean getRechargeInfoForApp(String detailId)
			throws BusinessException {
		if(StringUtils.isEmpty(detailId)){
			logger.info("查询充值详情信息异常！ detailId  能为空");
			throw new BusinessException("查询充值详情detailId不能为空！");
		}
		try {
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("detailId", detailId);
			RechargeApplyDetailInfoBean  result = (RechargeApplyDetailInfoBean) queryObjectBySQL(
					"TB_IC_CARD_RECHARGE_APPLY_EXTEND.queryRechargeInfo",
					params);
			return result;
		} catch (Exception e) {
			logger.error("查询充值详情信息异常！", e);
			throw new BusinessException("查询充值详情信息异常！", e);
		}
	}

}
