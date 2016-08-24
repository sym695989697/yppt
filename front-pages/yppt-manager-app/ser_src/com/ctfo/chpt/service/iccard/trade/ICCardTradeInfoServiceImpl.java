package com.ctfo.chpt.service.iccard.trade;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.ServiceImpl;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Converter;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfo;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoExampleExtended;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoHistory;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoHistoryExampleExtended;
import com.ctfo.yppt.baseservice.trade.intf.IICCardTradeInfoManager;

@Service(value = "iCCardTradeInfoService")
public class ICCardTradeInfoServiceImpl extends ServiceImpl implements IICCardTradeInfoService {
	private static Log logger = LogFactory.getLog(ICCardTradeInfoServiceImpl.class);

	private IICCardTradeInfoManager iICCardTradeInfoManager;

	public ICCardTradeInfoServiceImpl() {
		iICCardTradeInfoManager = (IICCardTradeInfoManager) ServiceFactory.getFactory().getService(IICCardTradeInfoManager.class);
	}

	@Override
	public PaginationResult<?> queryListPage(DynamicSqlParameter requestParam) {
		PaginationResult<?> result = new PaginationResult<ICCardTradeInfo>();
		try {
			ICCardTradeInfoExampleExtended example = new ICCardTradeInfoExampleExtended();
			ICCardTradeInfoExampleExtended.Criteria criteria = (ICCardTradeInfoExampleExtended.Criteria) Converter.paramToExampleExtendedCriteriaNoException(requestParam, example);
			example.setOrderByClause("TRADE_TIME desc nulls last,TRADE_TIME  desc nulls last");
			result = iICCardTradeInfoManager.paginate(example);
		} catch (Exception e) {
			logger.error("分页查询ic卡交易记录异常", e);
		}
		return result;
	}

	// public * getIcCardTradeManager() {
	// if(icCardManager==null)
	// icCardManager=(ICCardManager)
	// ServiceFactory.getFactory().getService(ICCardManager.class);
	// return icCardManager;
	// }
}
