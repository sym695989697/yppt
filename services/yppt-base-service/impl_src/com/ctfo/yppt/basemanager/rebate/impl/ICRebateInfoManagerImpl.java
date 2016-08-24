package com.ctfo.yppt.basemanager.rebate.impl;

import org.springframework.stereotype.Service;

import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.yppt.baseservice.rebate.beans.ICRebateInfo;
import com.ctfo.yppt.baseservice.rebate.beans.ICRebateInfoExampleExtended;
import com.ctfo.yppt.baseservice.rebate.intf.IICRebateInfoManager;

/**
 * 
 * 
 * IC卡返利记录明细管理.(基本操作父类有实现)
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月20日    dxs    新建
 * </pre>
 */
@Service("ICRebateInfoManager")
public class ICRebateInfoManagerImpl extends
		GenericManagerImpl<ICRebateInfo, ICRebateInfoExampleExtended> implements
		IICRebateInfoManager {

}
