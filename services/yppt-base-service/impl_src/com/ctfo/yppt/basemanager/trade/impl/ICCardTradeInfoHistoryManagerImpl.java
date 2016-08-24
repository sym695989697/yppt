package com.ctfo.yppt.basemanager.trade.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.yppt.baseservice.system.beans.CardStat;
import com.ctfo.yppt.baseservice.system.beans.CardStatExampleExtended;
import com.ctfo.yppt.baseservice.system.beans.UserStat;
import com.ctfo.yppt.baseservice.system.beans.UserStatExampleExtended;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoHistory;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoHistoryExampleExtended;
import com.ctfo.yppt.baseservice.trade.intf.IICCardTradeInfoHistoryManager;
import com.ctfo.yppt.bean.ICCardTradeInfoBean;

@Service("ICCardTradeInfoHistoryManagerImpl")
public class ICCardTradeInfoHistoryManagerImpl
		extends
		GenericManagerImpl<ICCardTradeInfoHistory, ICCardTradeInfoHistoryExampleExtended>
		implements IICCardTradeInfoHistoryManager {

	private static Log logger = LogFactory
			.getLog(ICCardTradeInfoHistoryManagerImpl.class);

	@Override
	public String addTradeInfoHistory(ICCardTradeInfoHistory model)
			throws BusinessException {

		String uuid = null;
		try {
			if (model == null) {
				logger.info("交易对象不能为空！");
				throw new BusinessException("交易对象不能为空！");
			}
			if (model.getUserId() == null && "".equals(model.getUserId())) {
				logger.info("用户id不能为空！");
				throw new BusinessException("用户id不能为空！");
			}
			if (model.getCardId() == null && "".equals(model.getCardId())) {
				logger.info("卡id不能为空！");
				throw new BusinessException("卡id不能为空！");
			}

			// 1保存到卡历史表
			logger.info("================>开始保存卡交易到历史表<==============");
			uuid = idao.addModel(model);
			logger.info("================>保存卡交易到历史表结束<==============");
			
			//如果是油品消费,则修改统计表
			if("IC-GAS".equals(model.getTradeType())){
				// 2操作用户统计信息
				logger.info("================>开始操作用户统计信息<==============");
				operateUserStat(model);
				logger.info("================>操作用户统计信息结束<==============");
				// 3操作卡统计信息
				logger.info("================>开始操作卡统计信息<==============");
				operateCardStat(model);
				logger.info("================>操作卡统计信息结束<==============");
			}

		}catch (BusinessException e){
			throw e;
		} catch (Exception e) {
			logger.error("保存交易到交易历史表和操作用户统计信息、卡统计信息异常！", e);
			throw new BusinessException("保存交易到交易历史表和操作用户统计信息、卡统计信息异常！", e);

		}
		return uuid;
	}

	private void operateCardStat(ICCardTradeInfoHistory model) throws Exception {
		// 判断卡统计信息是否存在
		CardStatExampleExtended cardStatExampleExtended = new CardStatExampleExtended();
		cardStatExampleExtended.createCriteria().andCardIdEqualTo(
				model.getCardId());
		List<CardStat> cardStats = idao.getModels(cardStatExampleExtended);
		// 新增卡统计信息
		if (cardStats == null || cardStats.isEmpty()) {
			addCardStat(model);
		} else {
			// 修改卡统计信息
			updateCardStat(model, cardStats.get(0));
		}
	}

	private void updateCardStat(ICCardTradeInfoHistory model,
			CardStat cardStatModel) throws Exception {
		if (model.getProductNum() != null) {
			BigDecimal oilNum = new BigDecimal(model.getProductNum());
			if (cardStatModel.getOilNum() != null) {
				oilNum = cardStatModel.getOilNum().add(
						new BigDecimal(model.getProductNum()).abs());
			}
			cardStatModel.setOilNum(oilNum);
		}

		if (model.getTradeMoney() != null) {
			BigDecimal consumeMoney = model.getTradeMoney();
			if (cardStatModel.getComsumeMoney() != null) {
				consumeMoney = cardStatModel.getComsumeMoney().add(
						model.getTradeMoney().abs());
			}
			cardStatModel.setComsumeMoney(consumeMoney);
		}
		idao.updateModelPart(cardStatModel);
	}

	private void addCardStat(ICCardTradeInfoHistory model) throws Exception {
		CardStat cardStat = new CardStat();
		cardStat.setId(UUID.randomUUID().toString());
		cardStat.setCardId(model.getCardId());
		if (model.getProductNum() != null) {
			cardStat.setOilNum(new BigDecimal(model.getProductNum()).abs());
		}
		if (model.getTradeMoney() != null) {
			cardStat.setComsumeMoney(model.getTradeMoney().abs());
		}

		idao.addModel(cardStat);
	}

	private void operateUserStat(ICCardTradeInfoHistory model) throws Exception {
		// 判断用户统计信息是否存在
		UserStatExampleExtended userStatExampleExtended = new UserStatExampleExtended();
		userStatExampleExtended.createCriteria().andUserIdEqualTo(
				model.getUserId());
		List<UserStat> userStats = idao.getModels(userStatExampleExtended);

		// 用户统计信息不存在，创新用户统计信息
		if (userStats == null || userStats.isEmpty()) {
			addUserStat(model);
		} else {

			// 用户信息存在，更新用户信息表
			updateUserStat(model, userStats.get(0));
		}
	}

	private void updateUserStat(ICCardTradeInfoHistory model,
			UserStat userStatModel) throws Exception {
		if (model.getProductNum() != null) {
			BigDecimal oilNum = new BigDecimal(model.getProductNum());
			if (userStatModel.getOilNum() != null) {
				oilNum = userStatModel.getOilNum().add(
						new BigDecimal(model.getProductNum()).abs());
			}
			userStatModel.setOilNum(oilNum);
		}

		if (model.getTradeMoney() != null) {
			BigDecimal consumeMoney = model.getTradeMoney();
			if (userStatModel.getConsumeMoney() != null) {
				consumeMoney = userStatModel.getConsumeMoney().add(
						model.getTradeMoney().abs());
			}
			userStatModel.setConsumeMoney(consumeMoney);
		}

		idao.updateModelPart(userStatModel);
	}

	private void addUserStat(ICCardTradeInfoHistory model) throws Exception {
		UserStat userStat = new UserStat();
		userStat.setId(UUID.randomUUID().toString());
		userStat.setUserId(model.getUserId());
		userStat.setUpdateTime(System.currentTimeMillis());

		if (model.getProductNum() != null) {
			userStat.setOilNum(new BigDecimal(model.getProductNum()).abs());
		}

		if (model.getTradeMoney() != null) {
			userStat.setConsumeMoney(model.getTradeMoney().abs());
		}

		idao.addModel(userStat);
	}

	@Override
	public PaginationResult<ICCardTradeInfoBean> paginateTradeInfoHistory(
			Map<String, Object> params) throws BusinessException {
		logger.info("================>进入查询油品历史记录<==============");
		PaginationResult<ICCardTradeInfoBean> result = new PaginationResult<ICCardTradeInfoBean>();
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
					"TB_IC_CARD_TRADE_INFO_HISTORY_EXTEND.abatorgenerated_selectByPaginateTradeInfoHistory",
					params);
			Integer total = (Integer) queryObjectBySQL(
					"TB_IC_CARD_TRADE_INFO_HISTORY_EXTEND.abatorgenerated_countByPaginateTradeInfoHistory",
					params);

			result.setData(list);
			result.setTotal(total);
		}catch (BusinessException e){
			throw e;
		} catch (Exception e) {
			logger.error("获取油卡交易记录信息异常！", e);
			throw new BusinessException("获取油卡交易记录信息异常！", e);

		}
		logger.info("================>结束查询油品历史记录<==============");
		return result;

	}

	@Override
	public BigDecimal statTradeInfoHistory(Map<String, Object> params)
			throws BusinessException {
		logger.info("================>进入查询油品消费总数<==============");
		BigDecimal result = null;
		try {
			if (params == null) {
				logger.info("================》params(查询参数)不能为空！《================");
				throw new BusinessException("params(查询参数)不能为空");
			}
			if (params.get("userId") == null&&params.get("cardId") == null) {
				logger.info("================》用户id或cardId不能为空！《================");
				throw new BusinessException("用户id或cardId不能为空");
			}

			result = (BigDecimal) queryObjectBySQL(
					"TB_IC_CARD_TRADE_INFO_HISTORY_EXTEND.abatorgenerated_countTrade",
					params);
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
	public PaginationResult<?> paginateTradeInfoAndTradeInfoHistory(
			Map<String, Object> params) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int remove(ICCardTradeInfoHistory bean) throws Exception {
		logger.info("remove暂不支持！");
		throw new BusinessException("remove暂不支持！");
	}

	@Override
	public int removeBatch(List<ICCardTradeInfoHistory> beans) throws Exception {
		logger.info("removeBatch暂不支持！");
		throw new BusinessException("removeBatch暂不支持！");
	}

	@Override
	public BigDecimal statOilTradeInfoHistory(Map<String, Object> params)
			throws BusinessException {
		logger.info("================>进入查询加油总数<==============");
		BigDecimal result = null;
		try {
			if (params == null) {
				logger.info("================》params(查询参数)不能为空！《================");
				throw new BusinessException("params(查询参数)不能为空");
			}
			if (params.get("cardId") == null&&params.get("userId") == null) {
				logger.info("================》用户id或cardID不能为空！《================");
				throw new BusinessException("用户id或cardID不能为空");
			}

			result = (BigDecimal) queryObjectBySQL(
					"TB_IC_CARD_TRADE_INFO_HISTORY_EXTEND.abatorgenerated_sumOilTrade",
					params);
		}catch (BusinessException e){
			throw e;
		} catch (Exception e) {
			logger.error("获取加总数异常！", e);
			throw new BusinessException("获取加总数异常！", e);

		}
		logger.info("================>结束查询加总数<==============");
		return result;
	}

}