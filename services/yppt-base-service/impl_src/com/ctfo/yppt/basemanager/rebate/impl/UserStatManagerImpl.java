package com.ctfo.yppt.basemanager.rebate.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.common.utils.TimeUtil;
import com.ctfo.csm.utils.BeanCopierUtils;
import com.ctfo.util.DateUtil;
import com.ctfo.yppt.basemanager.trade.impl.ICCardTradeInfoHistoryManagerImpl;
import com.ctfo.yppt.baseservice.card.beans.ICCardExampleExtended;
import com.ctfo.yppt.baseservice.card.intf.IICCardApplyManager;
import com.ctfo.yppt.baseservice.card.intf.IICCardManager;
import com.ctfo.yppt.baseservice.rebate.intf.IICRebateMessMemberManager;
import com.ctfo.yppt.baseservice.system.beans.UserStat;
import com.ctfo.yppt.baseservice.system.beans.UserStatExampleExtended;
import com.ctfo.yppt.baseservice.system.intf.IUserStatManager;
import com.ctfo.yppt.baseservice.trade.intf.IICCardTradeInfoHistoryManager;
import com.ctfo.yppt.bean.UserStatBean;

/**
 * 
 * 
 * 用户返利统计.(基本操作父类有实现)
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月20日    dxs    新建
 * </pre>
 */
@Service("UserStatManager")
public class UserStatManagerImpl extends
        GenericManagerImpl<UserStat, UserStatExampleExtended> implements
        IUserStatManager {

    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private IICCardManager iCCardManager;
    @Autowired
    private IICCardApplyManager iCCardApplyManager;
    @Autowired
    private IICRebateMessMemberManager iCRebateMessMemberManager;
    @Autowired
    private IICCardTradeInfoHistoryManager iCCardTradeInfoHistoryManager ;
    
    @Override
    public UserStat getUserstatByUserId(String userId) throws BusinessException {
        try {
            if (StringUtils.isBlank(userId)) {
                throw new BusinessException("用户id为空!");
            }
            UserStatExampleExtended usEE = new UserStatExampleExtended();
            usEE.createCriteria().andUserIdEqualTo(userId);
            List<UserStat> list = getList(usEE);
            if (list ==null || list.size()==0) {
                return null;
            }
            UserStat usb=list.get(0);
            queryPreMonthData(userId,usb);
            return usb;
            
        } catch (Exception e) {
            throw new BusinessException("查询用户统计信息发生异常:", e);
        }
    }

    private void queryPreMonthData(String userId , UserStat usb) throws BusinessException{
        
        long monOfFirstDay=TimeUtil.getLastMonthOneDateZeroUtc();
        long monOfLastDay=TimeUtil.getLastMillisecondOfMonth(monOfFirstDay);
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("startTime", monOfFirstDay);
        params.put("endTime", monOfLastDay);
        params.put("userId", userId);
        params.put("status", "IC-TRADE-SUCCESS");
        
        BigDecimal lastMonthOilNum=null;
        lastMonthOilNum=iCCardTradeInfoHistoryManager.statOilTradeInfoHistory(params);
        usb.setLastMonthOilNum(lastMonthOilNum);
        logger.info(String.format("用户%s上月加油数量%s", userId, lastMonthOilNum));
        
        BigDecimal lastMonthOilBalance=null;
        lastMonthOilBalance=iCCardTradeInfoHistoryManager.statTradeInfoHistory(params);
        usb.setLastMonthComsumeMoney(lastMonthOilBalance);
        logger.info(String.format("用户%s上月加油金额%s", userId, lastMonthOilBalance));
        
        BigDecimal lastRebateNum=null;
        lastRebateNum=iCRebateMessMemberManager.statUserRebatePreMonth(userId);
        usb.setLastMonthRebateCoinNum(lastRebateNum.longValue());
        logger.info(String.format("用户%s上月返利%s", userId, lastRebateNum));
    }
    
    @Override
    public UserStatBean getUserStat(String userId) throws BusinessException {
        try {
            if (StringUtils.isEmpty(userId)) {
                logger.info("用户ID不能为空！");
                throw new BusinessException("用户ID不能为空！");
            }
            UserStatBean usb = new UserStatBean();
            UserStat userStat = getUserstatByUserId(userId);
            if(userStat==null){
                logger.info("用户统计信息不存在,实例化一个空的实体！");
                userStat=new UserStatBean();
                queryPreMonthData(userId,userStat);
            }
            BeanCopierUtils.copyProperties(userStat, usb);
            usb.setUserId(userId);

            ICCardExampleExtended icee = new ICCardExampleExtended();
            icee.createCriteria().andUserIdEqualTo(userId);
            int cardNum = iCCardManager.count(icee);
            usb.setUsedCardCount(cardNum);
            logger.info(String.format("用户%s使用中卡张数：%s", userId, cardNum));

            Map<String, String> param = new HashMap<String, String>();
            param.put("userId", userId);
            int processCardNum = iCCardApplyManager.countProcessCard(param);
            usb.setApplyCardCount(processCardNum);
            logger.info(String.format("用户%s申请中卡张数：%s", userId, processCardNum));
            
            param = new HashMap<String, String>();
            param.put("userId", userId);
            int failCardNum = iCCardApplyManager.countFailCard(param);
            usb.setApplyCardFailCount(failCardNum);
            logger.info(String.format("用户%s申请失败卡张数：%s", userId, failCardNum));
            
            BigDecimal cardBalance = iCCardManager
                    .getCardsBalanceByUserId(userId);
            usb.setCardBalance(cardBalance);
            logger.info(String.format("用户%s使用中卡余额：%s", userId, cardBalance));

            return usb;
        } catch (Exception e) {
            logger.error("查询用户相关信息异常！", e);
            throw new BusinessException("查询用户相关信息异常！", e);
        }
    }

}
