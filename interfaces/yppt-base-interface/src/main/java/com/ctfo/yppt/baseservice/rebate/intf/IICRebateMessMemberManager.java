package com.ctfo.yppt.baseservice.rebate.intf;

import java.math.BigDecimal;

import com.ctfo.base.baseservice.IBaseManager;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.yppt.baseservice.rebate.beans.ICRebateMessMember;
import com.ctfo.yppt.baseservice.rebate.beans.ICRebateMessMemberExampleExtended;
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
public interface IICRebateMessMemberManager extends
		IBaseManager<ICRebateMessMember, ICRebateMessMemberExampleExtended> {
	
	/**
	 * 统计用户上月的返利金额
	 * @param userId 用户id
	 * @return 用户累积返利金额
	 * @throws BusinessException
	 */
	public BigDecimal statUserRebatePreMonth(String userId) throws BusinessException;
	
//	/**
//	 * 统计用户累积返利金额
//	 * @param userId 用户id
//	 * @return 用户累积返利金额
//	 * @throws BusinessException
//	 */
//	public BigDecimal statUserRebate(String userId) throws BusinessException;
}
