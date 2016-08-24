package com.ctfo.yppt.basemanager.card.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.yppt.baseservice.card.beans.ICCardCreditAccountIO;
import com.ctfo.yppt.baseservice.card.beans.ICCardCreditAccountIOExampleExtended;
import com.ctfo.yppt.baseservice.card.intf.IBaseLocalCreditManager;

/**
 * 
 * @author 卡管理
 * 
 */
@Service("IBaseLocalCreditManager")
public class BaseLocalCreditManagerImpl extends
		GenericManagerImpl<ICCardCreditAccountIO, ICCardCreditAccountIOExampleExtended> implements
		IBaseLocalCreditManager {

	private static Log logger = LogFactory.getLog(BaseLocalCreditManagerImpl.class);

	@Override
	public PaginationResult<ICCardCreditAccountIO> paginateCreditIORecords(Map<String, Object> queryMap)
			throws BusinessException {
		PaginationResult<ICCardCreditAccountIO> result = new PaginationResult<ICCardCreditAccountIO>();
		try {
			ICCardCreditAccountIOExampleExtended creditIOEE = new ICCardCreditAccountIOExampleExtended();
			ICCardCreditAccountIOExampleExtended.Criteria criteria = creditIOEE
					.createCriteria();
			// 交易时间
			Long startTime = null;
			if (queryMap.get("dealStartTime") != null) {
				startTime = Long.valueOf((String) queryMap.get("dealStartTime"));
			}
			Long endTime = null;
			if (queryMap.get("dealEndTime") != null) {
				endTime = Long.valueOf((String) queryMap.get("dealEndTime"));
			}
			
			if (startTime != null && endTime != null) {
				criteria.andTradeTimeBetween(startTime, endTime);
			} else if (startTime != null) {
				criteria.andTradeTimeGreaterThan(startTime);
			} else if (endTime != null) {
				criteria.andTradeTimeLessThan(endTime);
			}
			
			//收支类型
			if (queryMap.get("model") != null
					&& !"".equals(queryMap.get("model"))) {
				criteria.andModelEqualTo((String) queryMap.get("model"));
			}//交易类型
			if (queryMap.get("type") != null
					&& !"".equals(queryMap.get("type"))) {
				criteria.andTypeEqualTo((String) queryMap.get("type"));
			}//用户id
			if (queryMap.get("userId") != null
					&& !"".equals(queryMap.get("userId"))) {
				criteria.andUserIdEqualTo((String) queryMap.get("userId"));
			}//注册手机号
			if (queryMap.get("regPhone") != null
					&& !"".equals(queryMap.get("regPhone"))) {
				criteria.andUserRegPhoneLike("%"+ queryMap.get("regPhone")+"%");
			}//用户名称
			if (queryMap.get("userName") != null
					&& !"".equals(queryMap.get("userName"))) {
				criteria.andUserNameLike("%"+ queryMap.get("userName")+"%");
			}
			
			// 增加分页条件
			int page = 1;
			int pageSize = 10;
			if (queryMap.containsKey("page")) {
				page = (Integer) queryMap.get("page");
			}
			if (queryMap.containsKey("pageSize")) {
				pageSize = (Integer) queryMap.get("pageSize");
			}

			int start = (page - 1) * pageSize;
			int end = page * pageSize;
			creditIOEE.setSkipNum(start);
			creditIOEE.setEndNum(end + 1);
			creditIOEE.setOrderByClause(ICCardCreditAccountIO
					.fieldTradeTime() + " desc");
			result = paginate(creditIOEE);
		} catch (Exception e) {
			logger.error("查询积分收支发生异常", e);
			throw new BusinessException("查询积分收支发生异常", e);
		}
		return result;
	}

}