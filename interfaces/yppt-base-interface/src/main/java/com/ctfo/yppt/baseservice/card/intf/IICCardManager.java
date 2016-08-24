package com.ctfo.yppt.baseservice.card.intf;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ctfo.base.baseservice.IBaseManager;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.beans.ICCardExampleExtended;

/**
 * 
 * 
 * IC卡子卡管理.(基本操作父类有实现)
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月20日    dxs    新建
 * </pre>
 */
public interface IICCardManager extends
        IBaseManager<ICCard, ICCardExampleExtended> {

    /**
     * 
     * 通过用户id获取用户所有卡的卡余额总数.
     * 
     * @param userId 用户id
     * @return 卡余额; 以分为单位的金额;
     * @throws BusinessException
     * 
     *             <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月20日    dxs    新建
     * </pre>
     */
    BigDecimal getCardsBalanceByUserId(String userId) throws BusinessException;

    /**
     * 
     * 通过卡号与卡类型获取子卡.
     * 
     * @param userId 用户id
     * @return 
     * @throws BusinessException
     * 
     *             <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月20日    dxs    新建
     * </pre>
     */
    ICCard getCardByCardNoAndType(String cardType, String cardNo)
            throws BusinessException;

    /**
     * 
     * 
     * 空卡导入功能<br/>
     * 实体填写字段内容 (卡号，主卡号，卡类型，创建人)
     * 
     * @param icCardList 空卡信息
     * @return 返回导入结果信息 key=卡类型+卡号，value=(成功时为卡号记录对应的UUID,失败是返回字符串'fail')
     * @throws BusinessException
     * 
     *             <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月21日    dxs    新建
     * </pre>
     */
    Map<String, String> addEmptyCardBatch(List<ICCard> icCardList)
            throws BusinessException;

    /**
     * 
     * 
     * 根据卡号和卡类型修改子卡余额.
     * 
     * @param cardType
     * @param cardNo
     * @param balance
     * @return
     * @throws BusinessException
     * 
     *             <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月27日    dxs    新建
     * </pre>
     */
    int updateICCardBalance(String cardType, String cardNo, BigDecimal balance)
            throws BusinessException;
    /**
     * 
     *
     * 根据ID修改卡关联车辆信息.
     *
     * @param uuid  卡ID
     * @param vehicleNo  车辆号
     * @param vehicleColor 车辆颜色（可为空，为空时添加默认颜色)
     * @param modifyUserId 修改人UUID
     * @return
     * @throws BusinessException
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月30日    dxs    新建
     * </pre>
     */
    int updateICCardVehicleById(String uuid, String vehicleNo,
            String vehicleColor, String modifyUserId) 
            throws BusinessException;

}
