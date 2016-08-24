package com.ctfo.yppt.basemanager.card.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.csm.utils.BeanCopierUtils;
import com.ctfo.util.DateUtil;
import com.ctfo.yppt.baseservice.card.beans.ICCardExampleExtended;
import com.ctfo.yppt.baseservice.card.beans.ICCardMain;
import com.ctfo.yppt.baseservice.card.beans.ICCardMainExampleExtended;
import com.ctfo.yppt.baseservice.card.intf.IICCardMainManager;
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
@Service("ICCardMainManager")
public class ICCardMainManagerImpl extends
        GenericManagerImpl<ICCardMain, ICCardMainExampleExtended> implements
        IICCardMainManager {

    private Log logger = LogFactory.getLog(this.getClass());

    @Override
    public String add(ICCardMain bean) throws Exception {
        try {
            if (bean == null) {
                logger.info("实体不能为空！");
                throw new BusinessException("实体不能为空！");
            }
            if (StringUtils.isEmpty(bean.getCardNumber())) {
                logger.info("卡号不能为空！");
                throw new BusinessException("卡号不能为空！");
            }
            if (StringUtils.isEmpty(bean.getOpencardofficecode())) {
                logger.info("发卡机构不能为空！");
                throw new BusinessException("发卡机构不能为空！");
            }
            if (StringUtils.isEmpty(bean.getCtrdituser())) {
                logger.info("创建人不能为空！");
                throw new BusinessException("创建人不能为空！");
            }
            if (StringUtils.isEmpty(bean.getCardType())) {
                logger.info("卡类型不能为空！");
                throw new BusinessException("卡类型不能为空！");
            }
            ICCardMainExampleExtended imee = new ICCardMainExampleExtended();
            ICCardMainExampleExtended.Criteria criteria = imee.createCriteria();
            criteria.andCardNumberEqualTo(bean.getCardNumber());
            criteria.andCardTypeEqualTo(bean.getCardType());
            List<ICCardMain> queryBeanList = this.getList(imee);
            if (queryBeanList != null && queryBeanList.size() > 0) {
            	
                logger.info("主卡卡号&主卡类型：" + bean.getCardNumber() +"&"+bean.getCardType() +"存在");
                throw new BusinessException("主卡卡号："+bean.getCardNumber()+",主卡类型:"+bean.getCardType()+"已经存在！");
            }

            long currentDate = DateUtil.getCurrentUtcMsTime();

            bean.setCreatedTime(currentDate);
            String flag = super.add(bean);
            logger.info("主卡添加操作UUID=" + flag);

            return flag;
        } catch (Exception e) {
            logger.error("主卡添加操作异常", e);
            throw new BusinessException("主卡添加操作异常："+e.getMessage());
        }

    }

    @Override
    public int update(ICCardMain bean) throws Exception {

        try {

            if (bean == null) {
                logger.info("实体不能为空！");
                throw new BusinessException("实体不能为空！");
            }
            if (StringUtils.isEmpty(bean.getId())) {
                logger.info("ID不能为空！");
                throw new BusinessException("ID不能为空！");
            }

            if (StringUtils.isNotEmpty(bean.getCardNumber())
                    && StringUtils.isNotEmpty(bean.getCardType())) {
                ICCardMainExampleExtended imee = new ICCardMainExampleExtended();
                ICCardMainExampleExtended.Criteria criteria = imee
                        .createCriteria();
                criteria.andCardNumberEqualTo(bean.getCardNumber());
                criteria.andCardTypeEqualTo(bean.getCardType());
                List<ICCardMain> queryBeanList = this.getList(imee);
                if (queryBeanList != null && queryBeanList.size() > 0) {
                    if (!queryBeanList.get(0).getId().equals(bean.getId())) {
                        logger.info(String.format("卡号已经存在，不能修改为%s！",bean.getCardNumber()));
                        throw new BusinessException("主卡卡号："+bean.getCardNumber()+",主卡类型:"+bean.getCardType()+"已经存在！");
                    }
                }
            } else {
                bean.setCardNumber(null);
                bean.setCardType(null);
            }

            long currentDate = DateUtil.getCurrentUtcMsTime();
            bean.setModifiedTime(currentDate);

            int flag = super.update(bean);
            logger.info("主卡修改操作标识：" + flag);
            return flag;

        } catch (Exception e) {
            logger.error("主卡修改操作异常", e);
            throw new BusinessException("主卡修改操作异常:"+e.getMessage());
        }
    }

    @Override
    public int remove(ICCardMain bean) throws Exception {
        try {

            if (bean == null) {
                logger.info("实体不能为空！");
                throw new BusinessException("实体不能为空！");
            }
            if (StringUtils.isEmpty(bean.getId())) {
                logger.info("ID不能为空！");
                throw new BusinessException("ID不能为空！");
            }

            ICCardMain deleteBean = super.getById(bean);
            if (deleteBean == null) {
                logger.info("主卡信息不存在！");
                throw new BusinessException("主卡信息不存在！");
            }

            ICCardExampleExtended iCCard = new ICCardExampleExtended();
            iCCard.createCriteria().andParentIdEqualTo(bean.getId());

            int childNum = idao.countModels(iCCard);
            if (childNum > 0) {
                logger.info("主卡信息已被使用，不能删除！");
                throw new BusinessException("主卡信息已被使用，不能删除！");
            }

            int flag = super.remove(bean);
            logger.info("主卡删除操作标识：" + flag);
            return flag;

        } catch (Exception e) {
            logger.error("主卡删除操作异常", e);
            throw new BusinessException("主卡删除操作异常:"+e.getMessage());
        }

    }

    @Override
    public int removeBatch(List<ICCardMain> beans) throws Exception {
        try {
            if (beans == null) {
                return 0;
            }
            int result = 0;
            for (ICCardMain bean : beans) {
                result += this.remove(bean);
            }
            return result;

        } catch (Exception e) {
            logger.error("主卡批量删除操作异常", e);
            throw new BusinessException("主卡批量删除操作异常", e);
        }
    }

    @Override
    public int updateAll(ICCardMain bean) throws Exception {
        logger.info("updateAll暂不支持！");
        throw new BusinessException("updateAll暂不支持！");
        // return super.updateAll(bean);
    }

    @Override
    public PaginationResult<ICCardMainMetaBean> queryIICCardMainAndChildCardNum(
            ICCardMainExampleExtended ICCardMainExampleExtended)
            throws BusinessException {
        try {
            PaginationResult<ICCardMain> result = super
                    .paginate(ICCardMainExampleExtended);
            PaginationResult<ICCardMainMetaBean> metaResult = new PaginationResult<ICCardMainMetaBean>();

            if (result.getData() == null || result.getData().size() == 0) {
                metaResult.setData(null);
                metaResult.setDataObject(result.getDataObject());
                metaResult.setMessage(result.getMessage());
                metaResult.setStart(result.getStart());
                metaResult.setTotal(result.getTotal());
                return metaResult;
            }
            List<ICCardMainMetaBean> metaList = new ArrayList<ICCardMainMetaBean>();
            for (ICCardMain bean : result.getData()) {
                ICCardExampleExtended icee = new ICCardExampleExtended();
                icee.createCriteria().andParentIdEqualTo(bean.getId());
                int childCardNum = idao.countModels(icee);
                ICCardMainMetaBean immb = new ICCardMainMetaBean();
                BeanCopierUtils.copyProperties(bean, immb);
                immb.setChildCardNum(childCardNum);
                metaList.add(immb);
            }
            metaResult.setData(metaList);
            metaResult.setDataObject(result.getDataObject());
            metaResult.setMessage(result.getMessage());
            metaResult.setStart(result.getStart());
            metaResult.setTotal(result.getTotal());
            return metaResult;
        } catch (Exception e) {
            logger.error("查询主卡信息，包含主卡下的子卡数量操作异常", e);
            throw new BusinessException("查询主卡信息，包含主卡下的子卡数量操作异常", e);
        }
    }

    @Override
    public int updateICCardMainBalance(String cardType, String cardNo,
            BigDecimal balance) throws BusinessException {
        try {
            logger.info("--------------------------开始更改主卡余额操作--------------------------");
            if (StringUtils.isEmpty(cardType)) {
                logger.info("主卡类型不能为空！");
                throw new BusinessException("主卡类型不能为空！");
            }
            
            if (StringUtils.isEmpty(cardNo)) {
                logger.info("主卡卡号不能为空！");
                throw new BusinessException("主卡卡号不能为空！");
            }
            if (balance==null) {
                logger.info("金额不能为空！");
                throw new BusinessException("金额不能为空！");
            }
            ICCardMainExampleExtended icmee = new ICCardMainExampleExtended();
            ICCardMainExampleExtended.Criteria criteria = icmee
                    .createCriteria();
            criteria.andCardTypeEqualTo(cardType);
            criteria.andCardNumberEqualTo(cardNo);
            List<ICCardMain> icCardMainList = idao.getModels(icmee);
            if(icCardMainList==null || icCardMainList.size()==0){
                logger.info("主卡信息不存在！");
                throw new BusinessException("主卡信息不存在！");
            }
            ICCardMain icm=new ICCardMain();
            icm.setId(icCardMainList.get(0).getId());
            icm.setBalance(balance);
            icm.setBalanceModifiedTime(System.currentTimeMillis());
            int result=this.updatePart(icm);
            logger.info("--------------------------结束更改主卡余额操作--------------------------");
            return result;
        } catch (Exception e) {
            logger.error("更改主卡余额操作异常", e);
            throw new BusinessException("更改主卡余额操作异常", e);
        }
    }

}
