package com.ctfo.yppt.basemanager.rebate.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.yppt.baseservice.rebate.beans.ICRebateMessMember;
import com.ctfo.yppt.baseservice.rebate.beans.ICRebateMessMemberExampleExtended;
import com.ctfo.yppt.baseservice.rebate.intf.IICRebateMessMemberManager;

/**
 * 
 * 
 * IC卡会员月返利记录管理.(基本操作父类有实现)
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月20日    dxs    新建
 * </pre>
 */
@Service("ICRebateMessMemberManager")
public class ICRebateMessMemberManagerImpl extends
		GenericManagerImpl<ICRebateMessMember, ICRebateMessMemberExampleExtended>
		implements IICRebateMessMemberManager {
	
	private static Log logger = LogFactory
			.getLog(ICRebateMessMemberManagerImpl.class);
	
	@Override
	public BigDecimal statUserRebatePreMonth(String userId)
			throws BusinessException {
		BigDecimal result = null;
		try {
			if (userId == null) {
				logger.info("================》params(查询参数)不能为空！《================");
				throw new BusinessException("params(查询参数)不能为空");
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", userId);
			//获取上月第一天零点时间
			params.put("operateTimeStart", getLastMonthFirstDayPreTime());
			//获取上月最后一天24时间
			params.put("operateTimeEnd", getlastMonthEndDaySubTime());
			Long creditNum = (Long) queryObjectBySQL(
					"ICRebate_Extend.countUserRebate", params );
			if(creditNum == null){
				creditNum = 0L;
			}
			result = BigDecimal.valueOf(creditNum);
		}catch (BusinessException e){
			logger.error("查询用户上月返利异常!", e);
			throw e;
		} catch (Exception e) {
			logger.error("获取充值记录信息总数异常！", e);
			throw new BusinessException("获取充值记录信息总数异常！", e);
		}
		return result;
	}

	private Long getLastMonthFirstDayPreTime() {
		
		Calendar calendar3 = Calendar.getInstance();
		calendar3.add(Calendar.MONTH, -1);
		calendar3.set(Calendar.DAY_OF_MONTH, 1);
		calendar3.set(Calendar.AM_PM, Calendar.AM);
		calendar3.set(Calendar.HOUR, 0);
		calendar3.set(Calendar.MINUTE, 0);
		calendar3.set(Calendar.SECOND, 0);
		calendar3.set(Calendar.MILLISECOND, 0);
		return calendar3.getTimeInMillis();
	}

	private Long getlastMonthEndDaySubTime() {
		Calendar calendar3 = Calendar.getInstance();
		calendar3.set(Calendar.DAY_OF_MONTH, 1);
		calendar3.add(Calendar.DAY_OF_MONTH, -1);
		calendar3.set(Calendar.AM_PM, Calendar.PM);
		calendar3.set(Calendar.HOUR, 11);
		calendar3.set(Calendar.MINUTE, 59);
		calendar3.set(Calendar.SECOND, 59);
		calendar3.set(Calendar.MILLISECOND, 999);
		return calendar3.getTimeInMillis();
	}
}
