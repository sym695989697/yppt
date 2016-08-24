package com.ctfo.yppt.basemanager.card.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import com.ctfo.csm.soa.support.JsonSupport;
import com.ctfo.util.DateUtil;
import com.ctfo.yppt.baseservice.recharge.intf.IICRechargeApplyManager;
import com.ctfo.yppt.baseservice.system.beans.CardStat;
import com.ctfo.yppt.baseservice.system.beans.CardStatExampleExtended;
import com.ctfo.yppt.baseservice.system.beans.UserStat;
import com.ctfo.yppt.baseservice.system.intf.IICCardStatManager;
import com.ctfo.yppt.baseservice.trade.intf.IICCardTradeInfoHistoryManager;
import com.ctfo.yppt.bean.UserStatBean;

/**
 * 
 * 
 * IC卡消费统计管理 .
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月21日    dxs    新建
 * </pre>
 */
@Service("ICCardStatManager")
public class ICCardStatManagerImpl extends
        GenericManagerImpl<CardStat, CardStatExampleExtended> implements
        IICCardStatManager {

    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    IICRechargeApplyManager iICRechargeApplyManager;
    @Autowired
    private IICCardTradeInfoHistoryManager iCCardTradeInfoHistoryManager ;
    
    @Override
    public CardStat getIICCardStatByCardId(String cardId)
            throws BusinessException {
        try {
            if (StringUtils.isEmpty(cardId)) {
                logger.info("子卡ID不能为空！");
                throw new BusinessException("子卡ID不能为空！");
            }
            CardStatExampleExtended csee = new CardStatExampleExtended();
            csee.createCriteria().andCardIdEqualTo(cardId);
            List<CardStat> list = this.getList(csee);
            if (list == null || list.size() == 0) {
                return null;
            } else {
                CardStat usb=list.get(0);
                queryPreMonthData(cardId,usb);
                return usb;
                
            }
        } catch (Exception e) {
            logger.error("根据子卡ID查询消费统计异常！", e);
            throw new BusinessException("根据子卡ID查询消费统计异常！", e);
        }

    }
    
    /**
     * 
     *
     * 添加额外信息
     *
     * @param cardId  卡ID
     * @param usb   卡消费实体
     * @throws BusinessException
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2015年2月11日    dxs    新建
     * </pre>
     */
    private void queryPreMonthData(String cardId , CardStat usb) throws BusinessException{
        
        long monOfFirstDay=TimeUtil.getLastMonthOneDateZeroUtc();
        long monOfLastDay=TimeUtil.getLastMillisecondOfMonth(monOfFirstDay);
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("startTime", monOfFirstDay);
        params.put("endTime", monOfLastDay);
        params.put("cardId", cardId);
        params.put("status", "IC-TRADE-SUCCESS");
        
        BigDecimal lastMonthOilNum=null;
        lastMonthOilNum=iCCardTradeInfoHistoryManager.statOilTradeInfoHistory(params);
        usb.setLastMonthOilNum(lastMonthOilNum);
        logger.info(String.format("卡号%s上月加油数量%s", cardId, lastMonthOilNum));
        
        BigDecimal lastMonthOilBalance=null;
        lastMonthOilBalance=iCCardTradeInfoHistoryManager.statTradeInfoHistory(params);
        usb.setLastMonthComsumeMoney(lastMonthOilBalance);
        logger.info(String.format("卡号%s上月加油金额%s", cardId, lastMonthOilBalance));
        
    }
    
    @Override
    public BigDecimal[] getICCardLastConsumptionAndRecharge(String cardId,
            String yearMonth) throws BusinessException {
        try {
            if (StringUtils.isEmpty(cardId)) {
                logger.info("卡类型不能为空！");
                throw new BusinessException("卡类型不能为空！");
            }
            if (StringUtils.isEmpty(yearMonth)) {
                logger.info("年月不能为空！");
                throw new BusinessException("年月不能为空！");
            }
            
            Map<String,Object> params=new HashMap<String,Object>();
            params.put("cardId", cardId);
            
            long monOfFirstDay=DateUtil.getDate(DateUtil.DEFAULT_FORMATSHORT, yearMonth+"-"+"01").getTime();
            long monOfLastDay=TimeUtil.getLastMillisecondOfMonth(monOfFirstDay);
            params.put("startTime", monOfFirstDay);
            params.put("endTime", monOfLastDay);
            
            JsonSupport js=new JsonSupport();
            BigDecimal currMonthRecharge=iICRechargeApplyManager.countSingleRecharge(params);
            logger.info("本月充值："+js.serialize(currMonthRecharge));
            
            BigDecimal lastMonthComsume=null;
            params.put("status", "IC-TRADE-SUCCESS");
            lastMonthComsume=iCCardTradeInfoHistoryManager.statTradeInfoHistory(params);
            logger.info(String.format("上月消费%s", lastMonthComsume));
            
            BigDecimal[] bd=new BigDecimal[2];
            bd[0]=currMonthRecharge ;
            bd[1]=lastMonthComsume  ; 
            return bd;
        } catch (Exception e) {
            logger.error("根据卡ID查询上月累计消费金额与本月累计充值金额操作异常！", e);
            throw new BusinessException("根据卡ID查询 上月累计消费金额与本月累计充值金额操作异常！", e);
        }
    }

}
