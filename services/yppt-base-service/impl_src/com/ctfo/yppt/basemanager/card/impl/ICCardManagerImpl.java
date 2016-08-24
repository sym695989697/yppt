package com.ctfo.yppt.basemanager.card.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.mamager.impl.ServiceUtil;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.csm.soa.support.JsonSupport;
import com.ctfo.util.DateUtil;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.beans.ICCardExampleExtended;
import com.ctfo.yppt.baseservice.card.beans.ICCardMain;
import com.ctfo.yppt.baseservice.card.cons.ICCardCons;
import com.ctfo.yppt.baseservice.card.intf.IICCardManager;

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
@Service("ICCardManager")
public class ICCardManagerImpl extends
        GenericManagerImpl<ICCard, ICCardExampleExtended> implements
        IICCardManager {

    private Log logger = LogFactory.getLog(this.getClass());

    @Override
    public BigDecimal getCardsBalanceByUserId(String userId)
            throws BusinessException {
        try {
            if (StringUtils.isEmpty(userId)) {
                logger.info("获取用户卡余额时，用户ID不存在！");
                throw new BusinessException("获取用户卡余额时，用户ID不存在！");
            }

            ICCardExampleExtended icee = new ICCardExampleExtended();
            ICCardExampleExtended.Criteria criteria = icee.createCriteria();
            criteria.andUserIdEqualTo(userId);
            List<ICCard> list = super.getList(icee);
            if (list == null || list.size() == 0) {
                return new BigDecimal(0);
            }

            BigDecimal bd = new BigDecimal(0);
            for (ICCard bean : list) {
                bd=bd.add(bean.getBalance() == null ? new BigDecimal(0) : bean
                        .getBalance());
            }

            return bd;

        } catch (Exception e) {
            logger.error("通过用户id获取用户所有卡的卡余额总数操作异常", e);
            throw new BusinessException("通过用户id获取用户所有卡的卡余额总数操作异常", e);
        }
    }

    @Override
    public Map<String, String> addEmptyCardBatch(List<ICCard> icCardList)
            throws BusinessException {
        try {
            if (icCardList == null || icCardList.size() == 0) {
                logger.info("子卡信息集合为空，返回空结果集！");
                return null;
            }
            long currentDate = DateUtil.getCurrentUtcMsTime();

            Map<String, String> result = new HashMap<String, String>();

            logger.info(String.format("开始空卡导入功能"));
            JsonSupport js = new JsonSupport();
            int index=0;
            for (ICCard bean : icCardList) {
            	index++;
                logger.info(String.format("开卡信息%s", js.serialize(bean)));

                try {
                    boolean flag = true;
                    if (StringUtils.isEmpty(bean.getCardNumber())) {
                        logger.info("卡号不能为空！");
                        throw new BusinessException("卡号不能为空！");
                    }
                    if (StringUtils.isEmpty(bean.getParentId())) {
                        logger.info("主卡ID不能为空！");
                        throw new BusinessException("主卡ID不能为空！");
                    }
                    if (StringUtils.isEmpty(bean.getCardType())) {
                        logger.info("卡号类型不能为空！");
                        throw new BusinessException("卡号类型不能为空！");
                    }
                    if (StringUtils.isEmpty(bean.getCtrdituser())) {
                        logger.info("创建人不能为空！");
                        throw new BusinessException("创建人不能为空！");
                    }

                    ICCardMain icCardMainDb = new ICCardMain();
                    icCardMainDb.setId(bean.getParentId());
                    icCardMainDb = (ICCardMain) idao.getModelById(icCardMainDb);
                    if (icCardMainDb == null) {
                        logger.info("主卡信息不存在！");
                        throw new BusinessException("主卡信息不存在！");
                    }

                    ICCard icCardDb = this.getCardByCardNoAndType(
                            bean.getCardType(), bean.getCardNumber());

                    if (icCardDb != null) {
                        logger.info("卡号已存在！");
                        throw new BusinessException("卡号已存在！");
                    }

                    bean.setCreatedTime(currentDate);

                    ICCard icCard = new ICCard();
                    icCard.setParentId(bean.getParentId());
                    icCard.setParentCardNumber(icCardMainDb.getCardNumber());
                    icCard.setCardNumber(bean.getCardNumber());
                    icCard.setCardType(bean.getCardType());
                    icCard.setCtrdituser(bean.getCtrdituser());
                    icCard.setCreatedTime(bean.getCreatedTime() == null ? currentDate
                            : bean.getCreatedTime());
                    // 指定发卡地区
                    icCard.setOpencardofficeid(icCardMainDb
                            .getOpencardofficecode());
                    icCard.setCardAreaCode(icCardMainDb.getCardAreaCode());
                    icCard.setCardAreaName(icCardMainDb.getCardAreaName());
                    String uuid = super.add(bean);
                    // 添加结果
                    result.put(
                            bean.getCardType() + ICCardCons.SEPERATOR
                                    + bean.getCardNumber()+ICCardCons.SEPERATOR+index,
                            ICCardCons.OPER_SUCCESS);

                    logger.info(String.format("开卡信息%s结果为%s",
                            js.serialize(bean), uuid));
                } catch (Exception e) {
                    // 添加结果
                    result.put(
                            bean.getCardType() + ICCardCons.SEPERATOR
                                    + bean.getCardNumber()+ICCardCons.SEPERATOR+index,
                            String.format("错误信息：%s", e.getMessage()));
                }
            }

            logger.info(String.format("结束空卡导入功能"));
            return result;
        } catch (Exception e) {
            logger.error("空卡导入功能操作异常", e);
            throw new BusinessException("空卡导入功能操作异常", e);
        }
    }

    @Override
    public String add(ICCard bean) throws BusinessException {
        try {
            long currentDate = DateUtil.getCurrentUtcMsTime();
            if (bean == null) {
                logger.info("实体不能为空！");
                throw new BusinessException("实体不能为空！");
            }
            if (StringUtils.isEmpty(bean.getCardNumber())) {
                logger.info("卡号不能为空！");
                throw new BusinessException("卡号不能为空！");
            }
            if (StringUtils.isEmpty(bean.getParentId())) {
                logger.info("主卡ID不能为空！");
                throw new BusinessException("主卡ID不能为空！");
            }
            if (StringUtils.isEmpty(bean.getCardType())) {
                logger.info("卡号类型不能为空！");
                throw new BusinessException("卡号类型不能为空！");
            }
            if (StringUtils.isEmpty(bean.getCtrdituser())) {
                logger.info("创建人不能为空！");
                throw new BusinessException("创建人不能为空！");
            }

            ICCardMain icCardMainDb = new ICCardMain();
            icCardMainDb.setId(bean.getParentId());
            icCardMainDb = (ICCardMain) idao.getModelById(icCardMainDb);
            if (icCardMainDb == null) {
                logger.info("主卡信息不存在！");
                throw new BusinessException("主卡信息不存在！");
            }

            ICCard icCard = new ICCard();
            icCard.setParentId(icCardMainDb.getId());
            icCard.setParentCardNumber(icCardMainDb.getCardNumber());
            icCard.setCardNumber(bean.getCardNumber());
            icCard.setCardType(icCardMainDb.getCardType());
            icCard.setCtrdituser(bean.getCtrdituser());
            icCard.setCreatedTime(currentDate);
            // 指定发卡地区
            icCard.setOpencardofficeid(icCardMainDb.getOpencardofficecode());
            icCard.setCardAreaCode(icCardMainDb.getCardAreaCode());
            icCard.setCardAreaName(icCardMainDb.getCardAreaName());
            String uuid = super.add(bean);
            JsonSupport js = new JsonSupport();
            logger.info(String.format("开卡信息%s结果为%s", js.serialize(bean), uuid));
            return uuid;
        } catch (Exception e) {
            logger.error("添加子卡信息操作异常", e);
            throw new BusinessException("添加子卡信息操作异常", e);
        }
    }

    @Override
    public List<String> addBatch(List<ICCard> beans) throws Exception {
        logger.info("addBatch暂不支持！");
        throw new BusinessException("addBatch暂不支持！");
        // return super.addBatch(beans);
    }

    @Override
    public int removeBatch(List<ICCard> beans) throws Exception {
        logger.info("removeBatch暂不支持！");
        throw new BusinessException("removeBatch暂不支持！");
        // return super.removeBatch(beans);
    }

    @Override
    public int update(ICCard bean) throws Exception {
        try {

            if (bean == null) {
                logger.info("实体不能为空！");
                throw new BusinessException("实体不能为空！");
            }
            if (StringUtils.isEmpty(bean.getId())) {
                logger.info("ID不能为空！");
                throw new BusinessException("ID不能为空！");
            }


//            if (StringUtils.isEmpty(bean.getModifinguser())) {
//                logger.info("修改人不能为空！");
//                throw new BusinessException("修改人不能为空！");
//            }

            long currentDate = DateUtil.getCurrentUtcMsTime();
            bean.setModifinguser(bean.getModifinguser());
            bean.setModifiedTime(currentDate);
            int flag = super.update(bean);
            logger.info("子卡修改操作标识：" + flag);
            return flag;

        } catch (Exception e) {
            logger.error("子卡修改操作异常", e);
            throw new BusinessException("子卡修改操作异常", e);
        }
    }

    @Override
    public int updateAll(ICCard bean) throws Exception {
        logger.info("updateAll暂不支持！");
        throw new BusinessException("updateAll暂不支持！");
        // return super.updateAll(bean);
    }

    @Override
    public int updateICCardBalance(String cardType, String cardNo,
            BigDecimal balance) throws BusinessException {
        try {
            logger.info("--------------------------开始更改子卡余额操作--------------------------");
            if (StringUtils.isEmpty(cardType)) {
                logger.info("子卡类型不能为空！");
                throw new BusinessException("子卡类型不能为空！");
            }

            if (StringUtils.isEmpty(cardNo)) {
                logger.info("子卡卡号不能为空！");
                throw new BusinessException("子卡卡号不能为空！");
            }
            if (balance == null) {
                logger.info("金额不能为空！");
                throw new BusinessException("金额不能为空！");
            }
            ICCardExampleExtended icmee = new ICCardExampleExtended();
            ICCardExampleExtended.Criteria criteria = icmee.createCriteria();
            criteria.andCardTypeEqualTo(cardType);
            criteria.andCardNumberEqualTo(cardNo);
            List<ICCard> icCardList = idao.getModels(icmee);
            if (icCardList == null || icCardList.size() == 0) {
                logger.info("子卡信息不存在！");
                throw new BusinessException("子卡信息不存在！");
            }
            ICCard icm = new ICCard();
            icm.setId(icCardList.get(0).getId());
            icm.setBalance(balance);
            icm.setBalanceModifiedTime(System.currentTimeMillis());
            int result = this.updatePart(icm);
            logger.info("--------------------------结束更改子卡余额操作--------------------------");
            return result;
        } catch (Exception e) {
            logger.error("更改子卡余额操作异常", e);
            throw new BusinessException("更改子卡余额操作异常", e);
        }
    }

    @Override
    public ICCard getCardByCardNoAndType(String cardType, String cardNo)
            throws BusinessException {
        try {
            logger.info("--------------------------开始通过卡号与卡类型获取子卡--------------------------");
            if (StringUtils.isEmpty(cardType)) {
                logger.info("子卡卡类型不能为空！");
                throw new BusinessException("卡类型不能为空！");
            }
            if (StringUtils.isEmpty(cardNo)) {
                logger.info("子卡卡号不能为空！");
                throw new BusinessException("子卡卡号不能为空！");
            }

            ICCardExampleExtended icee = new ICCardExampleExtended();
            ICCardExampleExtended.Criteria criteria = icee.createCriteria();
            criteria.andCardTypeEqualTo(cardType);
            criteria.andCardNumberEqualTo(cardNo);

            List<ICCard> list = super.getList(icee);
            if (list == null || list.size() == 0) {
                return null;
            }
            logger.info("--------------------------结束通过卡号与卡类型获取子卡--------------------------");
            return list.get(0);
        } catch (Exception e) {
            logger.error("通过卡号与卡类型获取子卡操作异常", e);
            throw new BusinessException("通过卡号与卡类型获取子卡操作异常", e);
        }
    }

    @Override
    public int updateICCardVehicleById(String uuid, String vehicleNo,
            String vehicleColor, String modifyUserId) throws BusinessException {
        try {
            logger.info("--------------------------开始通过卡号与卡类型获取子卡--------------------------");
            if (StringUtils.isEmpty(uuid)) {
                logger.info("卡ID不能为空！");
                throw new BusinessException("卡ID不能为空！");
            }
            if (StringUtils.isEmpty(vehicleNo)) {
                logger.info("车牌号不能为空！");
                throw new BusinessException("车牌号不能为空！");
            }
            if (StringUtils.isEmpty(modifyUserId)) {
                logger.info("修改人不能为空！");
                throw new BusinessException("修改人不能为空！");
            }


            if (StringUtils.isEmpty(vehicleColor)) {
                vehicleColor = ICCardCons.VEHICLE_COLOR_DEFAULT;
            }
            
            logger.info("开始添加车辆信息到车辆系统！");
            String vehicleId="";
            try {
                vehicleId=ServiceUtil.getVehicleService().addVehicle(vehicleNo,
                        vehicleColor);
                logger.info("结束添加车辆信息到车辆系统！");
                logger.info("添加车辆结果！"+vehicleId);
            } catch (Exception e) {
                logger.error("添加车辆信息到车辆系统异常" + e);
                throw e;
            }
            
            long currentDate = DateUtil.getCurrentUtcMsTime();
            ICCard icCard = new ICCard();
            icCard.setId(uuid);
            icCard.setVehicleNo(vehicleNo);
            icCard.setVehicleColor(vehicleColor);
            icCard.setVehicleId(vehicleId);
            icCard.setModifiedTime(currentDate);
            icCard.setModifinguser(modifyUserId);
            int result = this.updatePart(icCard);
            return result;
        } catch (Exception e) {
            logger.error("根据ID修改卡关联车辆信息", e);
            throw new BusinessException("根据ID修改卡关联车辆信息", e);
        }
    }

}
