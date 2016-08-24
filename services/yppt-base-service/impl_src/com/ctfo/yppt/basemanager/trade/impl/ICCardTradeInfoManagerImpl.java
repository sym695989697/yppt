package com.ctfo.yppt.basemanager.trade.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfo;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoExampleExtended;
import com.ctfo.yppt.baseservice.trade.intf.IICCardTradeInfoManager;

@Service("ICCardTradeInfoManagerImpl")
public class ICCardTradeInfoManagerImpl extends
		GenericManagerImpl<ICCardTradeInfo, ICCardTradeInfoExampleExtended>
		implements IICCardTradeInfoManager {

	private static Log logger = LogFactory
			.getLog(ICCardTradeInfoManagerImpl.class);

	@Override
	public PaginationResult<?> paginateTradeInfo(Map<String, Object> params)
			throws BusinessException {

		logger.info("================>开始查询油品记录<==============");

		PaginationResult<?> result = new PaginationResult();
		try {

			if (params == null) {
				logger.info("================》params(查询参数)不能为空！《================");
				throw new BusinessException("params(查询参数)不能为空");
			}
			if (params.get("userId") == null) {
				logger.info("================》用户id不能为空！《================");
				throw new BusinessException("用户id不能为空");
			}

			int page = 1;
			if (params.get("page") != null) {
				page = (Integer) params.get("page");
			}

			int pageSize = 10;
			if (params.get("pageSize") != null) {
				pageSize = (Integer) params.get("pageSize");
			}

			Integer startRow = (page - 1) * pageSize;
			Integer endRow = page * pageSize;
			params.put("startRow", startRow);
			params.put("endRow", endRow);

			List list = queryListBySQL(
					"TB_IC_CARD_TRADE_INFO_EXTEND.abatorgenerated_selectByPaginateTradeInfo",
					params);
			Integer total = (Integer) queryObjectBySQL(
					"TB_IC_CARD_TRADE_INFO_EXTEND.abatorgenerated_countByPaginateTradeInfo",
					params);

			result.setData(list);
			result.setTotal(total);
		}catch (BusinessException e){
			throw e;
		} catch (Exception e) {
			logger.error("获取油卡交易记录信息异常！", e);
			throw new BusinessException("获取油卡交易记录信息异常！", e);

		}
		logger.info("================>结束查询油品记录<==============");
		return result;

	}

	@Override
	public BigDecimal countTradeInfo(Map<String, Object> params)
			throws BusinessException {
		logger.info("================>进入查询油品消费总数<==============");

		BigDecimal result = null;
		try {
			if (params == null) {
				logger.info("================》params(查询参数)不能为空！《================");
				throw new BusinessException("params(查询参数)不能为空");
			}
			if (params.get("userId") == null) {
				logger.info("================》用户id不能为空！《================");
				throw new BusinessException("用户id不能为空");
			}

			result = (BigDecimal) queryObjectBySQL(
					"TB_IC_CARD_TRADE_INFO_EXTEND.abatorgenerated_countTrade", params);
		}catch (BusinessException e){
			throw e;
		} catch (Exception e) {
			logger.error("获取充值记录信息总数异常！", e);
			throw new BusinessException("获取充值记录信息总数异常！", e);

		}
		logger.info("================>结束查询油品消费总数<==============");

		return result;
	}

	@Override
	public int update(ICCardTradeInfo bean) throws Exception {
		logger.info("update暂不支持！");
		throw new BusinessException("update暂不支持！");
	}

	@Override
	public int updateAll(ICCardTradeInfo bean) throws Exception {
		logger.info("updateAll暂不支持！");
		throw new BusinessException("updateAll暂不支持！");
	}

	@Override
	public int updatePart(ICCardTradeInfo bean) throws Exception {
		logger.info("updatePart暂不支持！");
		throw new BusinessException("updatePart暂不支持！");
	}

	@Override
	public int updateByOther(ICCardTradeInfo bean1, ICCardTradeInfo bean2)
			throws Exception {
		logger.info("updateByOther暂不支持！");
		throw new BusinessException("updateByOther暂不支持！");
	}

	@Override
	public int remove(ICCardTradeInfo bean) throws Exception {
		logger.info("remove暂不支持！");
		throw new BusinessException("remove暂不支持！");
	}

	@Override
	public int removeBatch(List<ICCardTradeInfo> beans) throws Exception {
		logger.info("removeBatch暂不支持！");
		throw new BusinessException("removeBatch暂不支持！");
	}

}