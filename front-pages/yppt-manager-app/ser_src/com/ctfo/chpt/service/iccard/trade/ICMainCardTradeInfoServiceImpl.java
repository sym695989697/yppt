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
import com.ctfo.yppt.baseservice.card.beans.ICCardExampleExtended;
import com.ctfo.yppt.baseservice.trade.beans.ICCardMainTradeInfo;
import com.ctfo.yppt.baseservice.trade.beans.ICCardMainTradeInfoExampleExtended;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfo;
import com.ctfo.yppt.baseservice.trade.intf.IICCardMainTradeInfoManager;

@Service(value = "iCMainCardTradeInfoService")
public class ICMainCardTradeInfoServiceImpl extends ServiceImpl implements IICMainCardTradeInfoService {
	private static Log logger = LogFactory.getLog(ICMainCardTradeInfoServiceImpl.class);

	private IICCardMainTradeInfoManager iICCardMainTradeInfoManager;

	public ICMainCardTradeInfoServiceImpl() {
		iICCardMainTradeInfoManager = (IICCardMainTradeInfoManager) ServiceFactory.getFactory().getService(IICCardMainTradeInfoManager.class);
	}

	@Override
	public PaginationResult<?> queryListPage(DynamicSqlParameter requestParam) {
		PaginationResult<?> result = new PaginationResult<ICCardTradeInfo>();
		try {
//			String cardNumber = null;// IC卡号
//			String ownOrg = null;// 所属组织
			ICCardMainTradeInfoExampleExtended extended = new ICCardMainTradeInfoExampleExtended();
//			Map<String, String> params = requestParam.getEqual();
//			Map<String, String> startWithParam = requestParam.getStartwith();
//			Map<String, String> endWithParam = requestParam.getEndwith();
//			ICCardMainTradeInfoExampleExtended.Criteria criteria = extended.createCriteria();
//			if (params != null && !params.isEmpty()) {
//				String tradeType = params.get("tradeType");
//				String cardType = params.get("cardType");
//				ownOrg = params.get("ownOrg");
//				String openCardOfficeCode = params.get("openCardOfficeCode");
//				
//				if (!StringUtils.isBlank(ownOrg))
//					criteria.andOwnOrgEqualTo(ownOrg);
//				if (!StringUtils.isBlank(tradeType))
//					criteria.andTradeTypeEqualTo(tradeType);
//				if (vailidateParams(cardType)) {
//					criteria.andCardTypeEqualTo(cardType);
//				}
//				if(vailidateParams(openCardOfficeCode)){
//					criteria.andOpenCardOfficeCodeEqualTo(openCardOfficeCode);
//				}
//			}
//			Map<String,String> like = requestParam.getLike();
//			if(like!=null){
//				cardNumber = like.get("cardNum");
//				if(vailidateParams(cardNumber)){
//					if (!org.apache.commons.lang.StringUtils.isBlank(cardNumber))
//						criteria.andCardNoLike(cardNumber);
//				}
//				
//			}
//			
//			if (startWithParam != null && endWithParam != null) {
//				String startTradeTime = startWithParam.get("tradeTime");
//				String endTradeTime = endWithParam.get("tradeTime");
//				if (!StringUtils.isBlank(startTradeTime) && !StringUtils.isBlank(endTradeTime)) {
//					criteria.andTradeTimeBetween(Long.parseLong(startTradeTime), Long.parseLong(endTradeTime));
//				}
//			}
			ICCardMainTradeInfoExampleExtended.Criteria criteria = (ICCardMainTradeInfoExampleExtended.Criteria) Converter.paramToExampleExtendedCriteriaNoException(requestParam, extended);
			extended.setOrderByClause("TRADE_TIME  desc nulls last,TRADE_TIME  desc nulls last");
			result = iICCardMainTradeInfoManager.paginate(extended);
		} catch (Exception e) {
			logger.error("分页查询ic卡主卡交易记录异常", e);
		}
		return result;
	}
}
