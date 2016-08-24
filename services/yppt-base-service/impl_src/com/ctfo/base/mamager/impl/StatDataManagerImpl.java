package com.ctfo.base.mamager.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.intf.IStatDataManager;
import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.gatewayservice.interfaces.service.ICreditService;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.beans.ICCardExampleExtended;

/**
 * 
 * @author 数据统计管理
 * 
 */
@Service("StatDataManager")
public class StatDataManagerImpl extends GenericManagerImpl implements
        IStatDataManager {

    private static Log logger = LogFactory.getLog(StatDataManagerImpl.class);

	@Override
	public Long queryCreditBalanceByUserId(String userId) throws Exception {
		Long balance = 0L;
        try {
        	logger.debug(">>>>>>>>开始查询用户积分账户余额：userId=" + userId + "】");
        	if(StringUtils.isBlank(userId)) {
        		logger.info("传入userId为空!");
        		return balance;
        	}
        	ICreditService creditService = ServiceFactory.soaService(ICreditService.class);
        	logger.debug(">>>>>>>>获取到积分服务：" + (creditService  == null ? "没有获取到积分账户服务": "已经获取到积分账户服务" ));
        	balance = creditService.queryBalance(userId);
            logger.debug(">>>>>>>>开始查询到用户积分账户余额：userId=" + userId + " 余额为：" + balance.longValue());
        } catch (Exception e) {
            logger.error("对外提供的;查询油卡余额发生异常", e);
            throw new Exception("对外提供的;查询油卡余额发生异常", e);
        }
        return balance;
	}


	@Override
	public Long queryAllUserCardsBalanceByUserId(String userId)
			throws Exception {
		BigDecimal cardBalance = BigDecimal.ZERO;
        try {
        	logger.debug(">>>>>>>>开始查询用户所有卡余额：userId=" + userId + "】");
            if(StringUtils.isNotBlank(userId)) {
                ICCardExampleExtended icCardExampleExtended = new ICCardExampleExtended();
                icCardExampleExtended.createCriteria().andUserIdEqualTo(userId);
                List<Object> list = getList(icCardExampleExtended);
                for (Object o : list) {
                    ICCard card = (ICCard) o;
                    if(card.getBalance() != null){
                        cardBalance = card.getBalance().add(cardBalance);
                    }
                }
            } else {
                logger.debug(">>>>>>>>账户ID为空【requestParam.equal.userId=" + userId + "】");
            }
            logger.debug(">>>>>>>>开始查询到用户所有卡余额：userId=" + userId + " 余额为：" + cardBalance.longValue());
        } catch (Exception e) {
            logger.error("对外提供的;查询油卡余额发生异常", e);
            throw new Exception("对外提供的;查询油卡余额发生异常", e);
        }
        return cardBalance.longValue();
	}
}
