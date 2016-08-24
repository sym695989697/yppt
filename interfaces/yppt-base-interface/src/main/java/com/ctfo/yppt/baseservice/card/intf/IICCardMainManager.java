package com.ctfo.yppt.baseservice.card.intf;

import java.math.BigDecimal;

import com.ctfo.base.baseservice.IBaseManager;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.yppt.baseservice.card.beans.ICCardMain;
import com.ctfo.yppt.baseservice.card.beans.ICCardMainExampleExtended;
import com.ctfo.yppt.bean.ICCardMainMetaBean;

/**
 * 
 * 
 * IC卡主卡管理.(基本操作父类有实现)
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月20日    dxs    新建
 * </pre>
 */
public interface IICCardMainManager extends
        IBaseManager<ICCardMain, ICCardMainExampleExtended> {

    /**
     * 
     * 
     * 查询主卡信息，包含主卡下的子卡数量.
     * 
     * @param ICCardMainExampleExtended
     * @return
     * @throws BusinessException
     * 
     *             <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月21日    dxs    新建
     * </pre>
     */
    PaginationResult<ICCardMainMetaBean> queryIICCardMainAndChildCardNum(
            ICCardMainExampleExtended ICCardMainExampleExtended)
            throws BusinessException;

    /**
     * 
     *
     * 根据卡号和卡类型修改主卡余额.
     *
     * @param cardType
     * @param cardNo
     * @param balance
     * @return
     * @throws BusinessException
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月27日    dxs    新建
     * </pre>
     */
    int updateICCardMainBalance(String cardType, String cardNo,
            BigDecimal balance) throws BusinessException;

}
