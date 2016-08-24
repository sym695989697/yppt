package com.ctfo.yppt.baseservice.system.intf;

import java.math.BigDecimal;

import com.ctfo.base.baseservice.IBaseManager;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.yppt.baseservice.system.beans.CardStat;
import com.ctfo.yppt.baseservice.system.beans.CardStatExampleExtended;

/**
 * 
 * 
 * IC卡消费统计管理
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月20日    dxs    新建
 * </pre>
 */
public interface IICCardStatManager extends
        IBaseManager<CardStat, CardStatExampleExtended> {

    /**
     * 
     * 
     * 根据子卡信息查询卡消费信息.
     * 
     * @return 卡消费统计实体
     * @throws BusinessException
     * 
     *             <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月25日    dxs    新建
     * </pre>
     */
    CardStat getIICCardStatByCardId(String cardId) throws BusinessException;

    /**
     * 
     * 
     * 根据卡类型与卡号查询 上月累计消费金额与本月累计充值金额
     * 
     * @param cardId
     * @param yearMonth 格式："yyyy-MM"
     * @return [0]=本月充值 [1]=上月消费
     * @throws BusinessException
     * 
     *             <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月27日    dxs    新建
     * </pre>
     */
    BigDecimal[] getICCardLastConsumptionAndRecharge(String cardId,
            String yearMonth) throws BusinessException;

}
