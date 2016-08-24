package com.ctfo.yppt.basemanager.rebate.impl;

import org.springframework.stereotype.Service;

import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.yppt.baseservice.rebate.beans.ICRebateMonthMess;
import com.ctfo.yppt.baseservice.rebate.beans.ICRebateMonthMessExampleExtended;
import com.ctfo.yppt.baseservice.rebate.intf.IICRebateMonthMessManager;

/**
 * 
 * 
 * IC月返利记录管理.(基本操作父类有实现)
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月20日    dxs    新建
 * </pre>
 */
@Service("ICRebateMonthMessManager")
public class ICRebateMonthMessManagerImpl extends
		GenericManagerImpl<ICRebateMonthMess, ICRebateMonthMessExampleExtended>
		implements IICRebateMonthMessManager {

}
