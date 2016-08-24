package com.ctfo.yppt.basemanager.rebate.impl;

import org.springframework.stereotype.Service;

import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.beans.ICCardExampleExtended;
import com.ctfo.yppt.baseservice.rebate.intf.IICRebateMessCardManager;

/**
 * 
 * 
 * IC卡月返利记录管理.(基本操作父类有实现)
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月20日    dxs    新建
 * </pre>
 */
@Service("ICRebateMessCardManager")
public class ICRebateMessCardManagerImpl extends
		GenericManagerImpl<ICCard, ICCardExampleExtended> implements
		IICRebateMessCardManager {

}
