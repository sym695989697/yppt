package com.ctfo.yppt.basemanager.trade.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.yppt.baseservice.trade.beans.ICCardMainTradeInfo;
import com.ctfo.yppt.baseservice.trade.beans.ICCardMainTradeInfoExampleExtended;
import com.ctfo.yppt.baseservice.trade.intf.IICCardMainTradeInfoManager;
@Service("ICCardMainTradeInfoManagerImpl")
public class ICCardMainTradeInfoManagerImpl extends GenericManagerImpl<ICCardMainTradeInfo, ICCardMainTradeInfoExampleExtended> implements IICCardMainTradeInfoManager{

	private static Log logger = LogFactory.getLog(ICCardMainTradeInfoManagerImpl.class);


}
