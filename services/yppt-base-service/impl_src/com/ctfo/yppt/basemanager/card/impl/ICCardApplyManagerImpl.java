package com.ctfo.yppt.basemanager.card.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.util.JSONUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctfo.base.mamager.impl.MessageUtil;
import com.ctfo.base.mamager.impl.ServiceUtil;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.csm.soa.support.JsonSupport;
import com.ctfo.csm.uaa.dao.beans.UAAUser;
import com.ctfo.csm.utils.BeanCopierUtils;
import com.ctfo.gatewayservice.interfaces.bean.user.CRMUserBean;
import com.ctfo.util.DateUtil;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.beans.ICCardApply;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyDetail;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyDetailExampleExtended;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyExampleExtended;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyProcessLog;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyProcessLogExampleExtended;
import com.ctfo.yppt.baseservice.card.beans.ICCardExampleExtended;
import com.ctfo.yppt.baseservice.card.cons.ICCardCons;
import com.ctfo.yppt.baseservice.card.intf.IICCardApplyManager;
import com.ctfo.yppt.baseservice.card.intf.IICCardManager;
import com.ctfo.yppt.bean.ICCardApplyDetailExtend;
import com.ctfo.yppt.bean.ICCardApplyMetaBean;

/**
 * 
 * 
 * IC卡申请管理.(基本操作父类有实现)
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月20日    dxs    新建
 * </pre>
 */
@Service("ICCardApplyManager")
public class ICCardApplyManagerImpl extends
        GenericManagerImpl<ICCardApply, ICCardApplyExampleExtended> implements
        IICCardApplyManager {

    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    IICCardManager iICCardManager;

    @SuppressWarnings("unchecked")
    @Override
    public String addIcCardApply(ICCardApplyMetaBean icCardApplyMetaBean)
            throws BusinessException {
        try {
            logger.info("--------------------------开始卡申请操作--------------------------");

            if (icCardApplyMetaBean == null
                    || icCardApplyMetaBean.getiCCardApply() == null
                    || icCardApplyMetaBean == null) {
                logger.info("实体不能为空！");
                throw new BusinessException("实体不能为空！");
            }
            ICCardApply iCCardApply = icCardApplyMetaBean.getiCCardApply();
            List<ICCardApplyDetail> iCCardApplyDetailList = icCardApplyMetaBean
                    .getiCCardApplyDetail();

            if (StringUtils.isEmpty(iCCardApply.getUserId())) {
                logger.info("用户ID不能为空！");
                throw new BusinessException("用户ID不能为空！");
            }
            if (StringUtils.isEmpty(iCCardApply.getApplyUserName())) {
                logger.info("用户姓名不能为空！");
                throw new BusinessException("用户姓名不能为空！");
            }

            if (StringUtils.isEmpty(iCCardApply.getIdNo())) {
                logger.info("用户身份证号不能为空！");
                throw new BusinessException("用户身份证号不能为空！");
            }

            if (StringUtils.isEmpty(iCCardApply.getCardType())) {
                logger.info("油卡类型不能为空！");
                throw new BusinessException("油卡类型不能为空！");
            }

            if (StringUtils.isEmpty(iCCardApply.getReceiverPhoneNum())) {
                logger.info("收件人手机号不能为空！");
                throw new BusinessException("收件人手机号不能为空！");
            }

            if (StringUtils.isEmpty(iCCardApply.getReceiverName())) {
                logger.info("收件人姓名不能为空！");
                throw new BusinessException("收件人姓名不能为空！");
            }

            if (StringUtils.isEmpty(iCCardApply.getProvince())) {
                logger.info("邮寄省份不能为空！");
                throw new BusinessException("邮寄省份不能为空！");
            }

            if (StringUtils.isEmpty(iCCardApply.getCity())) {
                logger.info("邮寄城市不能为空！");
                throw new BusinessException("邮寄城市不能为空！");
            }

            if (StringUtils.isEmpty(iCCardApply.getDistrict())) {
                logger.info("邮寄区县不能为空！");
                throw new BusinessException("邮寄区县不能为空！");
            }

            if (StringUtils.isEmpty(iCCardApply.getAddress())) {
                logger.info("邮寄详细街道地址不能为空！");
                throw new BusinessException("邮寄详细街道地址不能为空！");
            }
            if (StringUtils.isEmpty(iCCardApply.getDataSource())) {
                logger.info("数据来源不能为空！");
                throw new BusinessException("数据来源不能为空！");
            }
            if (StringUtils.isEmpty(iCCardApply.getOftenArea())) {
                logger.info("常用地区不能为空！");
                throw new BusinessException("常用地区不能为空！");
            }

            logger.info(String.format("查询客户资料信息,UAAID=%s", iCCardApply.getUserId()));
            CRMUserBean crmUserBean=ServiceUtil.getUserService().
                    queryCRMUserByUserId(iCCardApply.getUserId(), null);
            if(crmUserBean==null){
                logger.info(String.format("客户资料系统不存在当前用户信息%s",
                        icCardApplyMetaBean.getiCCardApply().getUserId()));
                throw new BusinessException(String.format("客户资料系统不存在当前用户信息%s",
                        icCardApplyMetaBean.getiCCardApply().getUserId()));
            }
            
            iCCardApply.setUserType(crmUserBean.getCrmCustomerType());
            iCCardApply.setUserName(crmUserBean.getCrmName());
            iCCardApply.setUserRegPhone(crmUserBean.getCrmMobile());
            
            logger.info("添加申请单信息");
            long currentDate = DateUtil.getCurrentUtcMsTime();
            iCCardApply.setStatus(ICCardCons.APPLY_AUDIT_STATE_CALL_CENTER_YET);
            iCCardApply.setCreateTime(currentDate);
            String uuid = this.add(iCCardApply);
            if (uuid == null) {
                logger.info(String.format("IC卡申请添加失败！卡信息为%s",
                        icCardApplyMetaBean));
                throw new BusinessException(String.format("IC卡申请添加失败！卡信息为%s",
                        icCardApplyMetaBean));
            }

            logger.info("添加申请单明细信息");
            for (ICCardApplyDetail iCCardApplyDetailBean : iCCardApplyDetailList) {
                // 绑定主卡ID
                iCCardApplyDetailBean.setApplyId(uuid);
                // 给每条申请明细都添加油卡类型
                iCCardApplyDetailBean.setCardType(iCCardApply.getCardType());

                if (StringUtils.isEmpty(iCCardApplyDetailBean.getVehicleNo())) {
                    logger.info("车牌号不能为空！");
                    throw new BusinessException("车牌号不能为空！");
                }
                if (StringUtils.isEmpty(iCCardApplyDetailBean
                        .getAcceptMessage())) {
                    logger.info("是否接收消息标志不能为空！");
                    throw new BusinessException("是否接收消息标志不能为空！");
                }
                if (StringUtils.isEmpty(iCCardApplyDetailBean.getPhoneNum())) {
                    logger.info("绑定手机号不能为空！");
                    throw new BusinessException("绑定手机号不能为空！");
                }
                if (StringUtils
                        .isEmpty(iCCardApplyDetailBean.getVehicleColor())) {
                    logger.info(String.format("提供默认车辆颜色:%s",
                            ICCardCons.VEHICLE_COLOR_DEFAULT));
                    iCCardApplyDetailBean
                            .setVehicleColor(ICCardCons.VEHICLE_COLOR_DEFAULT);
                }

                idao.addModel(iCCardApplyDetailBean);
            }

            logger.info("添加处理日志信息");
            ICCardApplyProcessLog iccapl = new ICCardApplyProcessLog();
            iccapl.setApplyId(uuid);
            iccapl.setStepNo("0");
            iccapl.setApprovalTime(currentDate);
            iccapl.setState(ICCardCons.APPLY_AUDIT_STATE_CALL_CENTER_YET);
            idao.addModel(iccapl);

            logger.info("--------------------------结束卡申请操作--------------------------");
            return uuid;
        } catch (Exception e) {
            logger.error("添加IC卡申请操作异常", e);
            throw new BusinessException("添加IC卡申请操作异常", e);
        }
    }

    @Override
    public int auditingIcCardApply(ICCardApplyMetaBean icCardApplyMetaBean,
            ICCardApplyProcessLog auditLog) throws BusinessException {
        logger.info("--------------------------开始卡申请审核操作--------------------------");
        try {

            if (icCardApplyMetaBean == null || auditLog == null) {
                logger.info("实体不能为空！");
                throw new BusinessException("实体不能为空！");
            }

            ICCardApply iCCardApply = icCardApplyMetaBean.getiCCardApply();
            List<ICCardApplyDetail> iCCardApplyDetailList = icCardApplyMetaBean
                    .getiCCardApplyDetail();
            long currentDate = DateUtil.getCurrentUtcMsTime();
            String cardNo="";
            if (StringUtils.isEmpty(iCCardApply.getId())) {
                logger.info("IC卡申请单ID不能为空！");
                throw new BusinessException("IC卡申请单ID不能为空！");
            }

            ICCardApply iCCardApplyDb = this.getById(iCCardApply);
            if (StringUtils.isEmpty(iCCardApply.getId())) {
                logger.info("IC卡申请单不存在！");
                throw new BusinessException("IC卡申请单不存在！");
            }

            if (StringUtils.isEmpty(iCCardApply.getStatus())) {
                logger.info("IC卡申请单状态不能为空！");
                throw new BusinessException("IC卡申请单状态不能为空！");
            }

            logger.info("修改申请单信息");
            ICCardApply iCCardApplyNew = new ICCardApply();
            BeanCopierUtils.copyProperties(iCCardApply, iCCardApplyNew);
            iCCardApplyNew.setModifyTime(currentDate);
            iCCardApplyNew.setCardType(null); // 不修改类型，申请时已经有油卡信息
            int updateNumer = this.update(iCCardApplyNew);

            logger.info("增加审核日志信息");
            ICCardApplyProcessLog auditLogNew = new ICCardApplyProcessLog();
            BeanCopierUtils.copyProperties(auditLog, auditLogNew);
            // auditLogNew.setStepNo(stepNo); //考虑步骤号app生成还是service生成
            auditLog.setApprovalTime(currentDate);
            auditLog.setApprovalState(iCCardApply.getStatus());
            String logUuid = idao.addModel(auditLog);

            logger.info("修改申请单明细信息， 同时将用户、车辆信息与子卡信息绑定，并提示用户审核成功");
            if (iCCardApplyDetailList != null
                    && ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_PASS
                            .equals(iCCardApply.getStatus())) {

                for (ICCardApplyDetail iCCardApplyDetail : iCCardApplyDetailList) {
                    if (StringUtils.isEmpty(iCCardApplyDetail.getId())) {
                        logger.info("IC卡申请明细表ID不能为空！");
                        throw new BusinessException("IC卡申请明细表ID不能为空！");
                    }

                    // 先根据ID查询出此条明细，与卡建立关联关系时使用
                    ICCardApplyDetail iCCardApplyDetailDb = (ICCardApplyDetail) idao
                            .getModelById(iCCardApplyDetail);

                    logger.info("修改明细表信息");
                    iCCardApplyDetail.setApplyId(iCCardApply.getId());

                    ICCard icceeList = this.getICCardApplyDetail(
                            iCCardApplyDetail.getCardNum(),
                            iCCardApplyDb.getCardType());
                    iCCardApplyDetail.setCardId(icceeList.getId());

                    idao.updateModelPart(iCCardApplyDetail);

                    logger.info("子卡信息关联用户车辆信息");
                    ICCard relateICCardDb = getICCardApplyDetail(
                            iCCardApplyDetail.getCardNum(),
                            iCCardApplyDb.getCardType());

                    if (relateICCardDb == null) {
                        logger.info(String.format(
                                "查询不到子卡：cardNumber=%s,cardType=%s",
                                iCCardApplyDetail.getCardNum(),
                                iCCardApplyDb.getCardType()));
                        throw new BusinessException(String.format(
                                "查询不到子卡：cardNumber=%s,cardType=%s",
                                iCCardApplyDetail.getCardNum(),
                                iCCardApplyDb.getCardType()));
                    }
                    if (StringUtils.isNotEmpty(relateICCardDb.getVehicleNo())
                            && StringUtils.isNotEmpty(relateICCardDb.getUserId())) {
                        logger.info("卡号已被使用！");
                        throw new BusinessException("卡号已被使用！");
                    }
                    
                    
                    ICCard relateICCard = new ICCard();
                    relateICCard.setId(relateICCardDb.getId());
                    relateICCard.setUserId(iCCardApplyDb.getUserId());
                    relateICCard.setUserName(iCCardApplyDb.getUserName());
                    relateICCard.setUserType(iCCardApplyDb.getUserType());
                    relateICCard.setUserRegPhone(iCCardApplyDb
                            .getUserRegPhone());
                    relateICCard.setTelephoneNumber(iCCardApplyDetailDb.getPhoneNum());
                    relateICCard.setVehicleNo(iCCardApplyDetailDb
                            .getVehicleNo());
                    relateICCard.setVehicleColor(iCCardApplyDetailDb
                            .getVehicleColor());
                    relateICCard.setVehicleLicense(iCCardApplyDetailDb
                            .getVehicleLicense());
                    relateICCard.setModifiedTime(currentDate);
                    relateICCard.setModifinguser(iCCardApply.getModifier());
                    relateICCard.setOpenCardTime(currentDate);
                    relateICCard.setMessageOrNot(iCCardApplyDetailDb.getAcceptMessage());
                    
                    logger.info("调用车辆系统功能查询车辆ID,绑定车辆信息ID");
                    relateICCard.setVehicleId(ServiceUtil.getVehicleService()
                            .addVehicle(iCCardApplyDetailDb.getVehicleNo(),
                                    iCCardApplyDetailDb.getVehicleColor()));

                    idao.updateModelPart(relateICCard);
                    cardNo= relateICCardDb.getCardNumber();

                }
            }
            
            {
                
                logger.info("最后发送短信 提示用户成功，发送短信调用外部接口，前面操作全部成功后在调用");
                Integer cardNum=iCCardApplyDb.getCardNum();
                String expressCompany=iCCardApply.getExpressCompany();
                String expressInfo=iCCardApply.getExpressInfo();
                String receivePhoneNum=iCCardApplyDb.getReceiverPhoneNum();
                // 单卡时卡号为cardNo 
                List<String> list=new ArrayList<String>();
                if(ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_PASS.equals(iCCardApply.getStatus()) && cardNum>1){
                    list.add(cardNum.toString());
                    list.add(expressCompany);
                    list.add(expressInfo);
                }
                if(ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_PASS.equals(iCCardApply.getStatus()) && cardNum==1){
                    if(cardNo.length()>=4){
                        cardNo = cardNo.substring(cardNo.length()-4); 
                    }
                    list.add(cardNo);
                    list.add(expressCompany);
                    list.add(expressInfo);
                }
                sendMessage(receivePhoneNum, list ,cardNum , iCCardApply.getStatus());
                
            }
            
            logger.info("--------------------------结束卡申请审核操作--------------------------");
            return 0;
        } catch (Exception e) {
            logger.error("IC卡申请审核操作异常", e);
            throw new BusinessException("IC卡申请审核操作异常", e);
        }

    }

    /**
     * 
     *
     * 发送短信服务
     *
     * @param receivePhoneNum
     * @param templateCode
     * @param list
     * @param cardNum
     * @param status
     * @return
     * @throws BusinessException
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2015年2月5日    dxs    新建
     * </pre>
     */
    public String sendMessage(String receivePhoneNum  , 
            List<String> list , Integer cardNum , String status) throws BusinessException{
        String templateCode="";
        String flag="";
        try {
            if (ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_PASS.equals(status)
                    && cardNum > 1) {
                templateCode = "ICCARD_APPLY_BATCH_SUCC";
                flag= MessageUtil.sendShortMessage(receivePhoneNum, templateCode, list);
            }
            if (ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_PASS.equals(status)
                    && cardNum == 1) {
                templateCode = "ICCARD_APPLY_SINGLE_SUCC";
                flag= MessageUtil.sendShortMessage(receivePhoneNum, templateCode, list);
            }
            if (ICCardCons.APPLY_AUDIT_STATE_NOT_PASS.equals(status)
                    && cardNum > 1) {
                templateCode = "ICCARD_APPLY_BATCH_FAIL";
                flag= MessageUtil.sendShortMessage(receivePhoneNum, templateCode, list);
            }
            if (ICCardCons.APPLY_AUDIT_STATE_NOT_PASS.equals(status)
                    && cardNum == 1) {
                templateCode = "ICCARD_APPLY_SINGLE_FAIL";
                flag= MessageUtil.sendShortMessage(receivePhoneNum, templateCode, list);
            }
            return flag;
        } catch (Exception e) {
            logger.info("短信发送失败", e);
            throw new BusinessException("短信发送失败", e);
        }
    }
    
    @Override
    public int updateIcCardApply(ICCardApplyMetaBean icCardApplyMetaBean)
            throws BusinessException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ICCardApplyMetaBean getIcCardApplyMetaBean(String id)
            throws BusinessException {
        logger.info("--------------------------开始根据申请单查询ID查询申请信息--------------------------");
        try {
            if (StringUtils.isEmpty(id)) {
                logger.info("IC卡申请ID不能为空！");
                throw new BusinessException("IC卡申请ID不能为空！");
            }
            ICCardApply bean = new ICCardApply();
            bean.setId(id);
            bean = super.getById(bean);
            if (bean == null) {
                return null;
            }

            JsonSupport js = new JsonSupport();
            logger.info(String.format("查询IC卡申请单结果：%s", js.serialize(bean)));

            ICCardApplyDetailExampleExtended icadee = new ICCardApplyDetailExampleExtended();
            ICCardApplyDetailExampleExtended.Criteria criteria = icadee
                    .createCriteria();
            criteria.andApplyIdEqualTo(id);
            List<ICCardApplyDetail> list = this.idao.getModels(icadee);
            logger.info(String.format("查询IC卡申请单明细结果：%s", js.serialize(list)));
            ICCardApplyMetaBean icamb = new ICCardApplyMetaBean();

            icamb.setiCCardApply(bean);
            icamb.setiCCardApplyDetail(list);

            logger.info("--------------------------结束根据申请单查询ID查询申请信息--------------------------");
            return icamb;
        } catch (Exception e) {
            logger.error("查询IC卡申请操作异常", e);
            throw new BusinessException("查询IC卡申请操作异常", e);
        }
    }

    @Override
    public List<ICCardApplyProcessLog> queryICCardApplyProcessLogByApplyID(
            String applyId) throws BusinessException {
        try {
            logger.info("--------------------------开始根据申请单ID查询申请日志信息--------------------------");
            if (StringUtils.isEmpty(applyId)) {
                logger.info("IC卡申请ID不能为空！");
                throw new BusinessException("IC卡申请ID不能为空！");
            }
            ICCardApplyProcessLogExampleExtended iccaplee = new ICCardApplyProcessLogExampleExtended();
            iccaplee.setOrderByClause(ICCardApplyProcessLog.fieldApprovalTime()
                    + " desc");
            iccaplee.createCriteria().andApplyIdEqualTo(applyId);

            List<ICCardApplyProcessLog> list = idao.getModels(iccaplee);

            logger.info(String.format("查询IC卡申请%s操作日志记录结果：%s条数", applyId,
                    (list == null ? list.size() : list.size())));

            logger.info("--------------------------结束根据申请单ID查询申请日志信息--------------------------");
            return list;
        } catch (Exception e) {
            logger.error("查询审核日志集合操作异常", e);
            throw new BusinessException("查询审核日志集合操作异常", e);
        }
    }

    @Override
    public ICCardApplyProcessLog getICCardApplyProcessLog(String id)
            throws BusinessException {
        logger.info("--------------------------开始根据申请单日志ID查询申请日志信息--------------------------");
        try {

            if (StringUtils.isEmpty(id)) {
                logger.info("IC卡申请操作日志不能为空！");
                throw new BusinessException("IC卡申请操作日志不能为空！");
            }
            ICCardApplyProcessLog bean = new ICCardApplyProcessLog();
            bean.setId(id);
            bean = (ICCardApplyProcessLog) idao.getModelById(bean);
            logger.info(String.format("查询IC卡审核记录%s",
                    JSONUtils.valueToString(bean)));
            logger.info("--------------------------结束根据申请单查询ID查询申请日志信息--------------------------");
            return bean;
        } catch (Exception e) {
            logger.error("查询审核操作日志操作异常", e);
            throw new BusinessException("查询审核操作日志操作异常", e);
        }
    }

    @Override
    public Map<String, String> addCardImportBatch(
            ICCardApplyMetaBean icCardApplyMetaBean,
            ICCardApplyProcessLog auditLog) throws BusinessException {
        logger.info("--------------------------开始批量开卡服务--------------------------");

        Map<String, String> result = new HashMap<String, String>();

        try {
            if (icCardApplyMetaBean == null
                    || icCardApplyMetaBean.getiCCardApply() == null
                    || icCardApplyMetaBean == null) {
                logger.info("实体不能为空！");
                throw new BusinessException("实体不能为空！");
            }
            ICCardApply iCCardApply = icCardApplyMetaBean.getiCCardApply();
            List<ICCardApplyDetail> iCCardApplyDetailList = icCardApplyMetaBean
                    .getiCCardApplyDetail();

            if (StringUtils.isEmpty(iCCardApply.getApplyUserName())) {
                logger.info("用户姓名不能为空！");
                throw new BusinessException("用户姓名不能为空！");
            }

            if (StringUtils.isEmpty(iCCardApply.getIdNo())) {
                logger.info("用户身份证号不能为空！");
                throw new BusinessException("用户身份证号不能为空！");
            }

            if (StringUtils.isEmpty(iCCardApply.getCardType())) {
                logger.info("油卡类型不能为空！");
                throw new BusinessException("油卡类型不能为空！");
            }

            if (StringUtils.isEmpty(iCCardApply.getReceiverPhoneNum())) {
                logger.info("收件人手机号不能为空！");
                throw new BusinessException("收件人手机号不能为空！");
            }

            if (StringUtils.isEmpty(iCCardApply.getReceiverName())) {
                logger.info("收件人姓名不能为空！");
                throw new BusinessException("收件人姓名不能为空！");
            }

            if (StringUtils.isEmpty(iCCardApply.getProvince())) {
                logger.info("邮寄省份不能为空！");
                throw new BusinessException("邮寄省份不能为空！");
            }

            if (StringUtils.isEmpty(iCCardApply.getCity())) {
                logger.info("邮寄城市不能为空！");
                throw new BusinessException("邮寄城市不能为空！");
            }

            if (StringUtils.isEmpty(iCCardApply.getDistrict())) {
                logger.info("邮寄区县不能为空！");
                throw new BusinessException("邮寄区县不能为空！");
            }

            if (StringUtils.isEmpty(iCCardApply.getAddress())) {
                logger.info("邮寄详细街道地址不能为空！");
                throw new BusinessException("邮寄详细街道地址不能为空！");
            }

            if (StringUtils.isEmpty(iCCardApply.getUserRegPhone())) {
                logger.info("注册手机号不能为空！");
                throw new BusinessException("注册手机号不能为空！");
            }

            if (StringUtils.isEmpty(iCCardApply.getDataSource())) {
                logger.info("数据来源不能为空！");
                throw new BusinessException("数据来源不能为空！");
            }

            if (StringUtils.isEmpty(iCCardApply.getOftenArea())) {
                logger.info("常用地区不能为空！");
                throw new BusinessException("常用地区不能为空！");
            }

            UAAUser user = ServiceUtil.getUserService().queryUserByUserLogin(
                    iCCardApply.getUserRegPhone(), null);
            if (user == null) {
                logger.info("申请人信息不存在，不能进行批量导入！");
                return null;
            } else {
                // UAA用户ID设置到申请单里
                logger.info(String.format("查询客户资料信息,UAAID=%s", iCCardApply.getUserId()));
                CRMUserBean crmUserBean=ServiceUtil.getUserService().
                        queryCRMUserByUserId(user.getId(), null);
                if(crmUserBean==null){
                    logger.info(String.format("客户资料系统不存在当前用户信息%s",
                            icCardApplyMetaBean.getiCCardApply().getUserId()));
                    return null;
                }
                
                iCCardApply.setUserType(crmUserBean.getCrmCustomerType());
                iCCardApply.setUserName(crmUserBean.getCrmName());
                iCCardApply.setUserRegPhone(crmUserBean.getCrmMobile());
                icCardApplyMetaBean.getiCCardApply().setUserId(user.getId());
            }
            if (StringUtils.isEmpty(icCardApplyMetaBean.getiCCardApply()
                    .getApplyType())) {
                icCardApplyMetaBean.getiCCardApply().setApplyType(
                        ICCardCons.IC_CARD_APPLY_TYPE_OPEN);
            }
            /**
             * 取系统时间
             * */
            long currentDate = DateUtil.getCurrentUtcMsTime();

            logger.info("添加申请单信息");
            iCCardApply
                    .setStatus(ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_PASS);
            iCCardApply.setCreateTime(currentDate);
            // 修改人与修改时间通创建人与创建时间
            iCCardApply.setModifyTime(currentDate);
            iCCardApply.setModifier(iCCardApply.getCreator());
            
            String uuid = this.add(iCCardApply);
            iCCardApply.setId(uuid);
            if (uuid == null) {
                logger.info(String.format("IC卡批量导入失败，申请单插入失败！卡信息为%s",
                        icCardApplyMetaBean));
                throw new BusinessException(String.format(
                        "IC卡批量导入失败，申请单插入失败！卡信息为%s", icCardApplyMetaBean));
            }
            int successCardCount = 0;
            int index=0;
            logger.info("添加申请单明细信息");
            for (ICCardApplyDetail iCCardApplyDetailBean : iCCardApplyDetailList) {
            	index++;
                try {
                	
                	iCCardApplyDetailBean.setCardType(iCCardApply.getCardType());
                	
                    if (StringUtils.isEmpty(iCCardApplyDetailBean
                            .getVehicleNo())) {
                        logger.info("[车牌号]不能为空！");
                        throw new BusinessException("[车牌号]不能为空！");
                    }
                    if (StringUtils
                            .isEmpty(iCCardApplyDetailBean.getPhoneNum())) {
                        logger.info("[绑定手机]不能为空！");
                        throw new BusinessException("[绑定手机]不能为空！");
                    }
                    if (StringUtils.isEmpty(iCCardApplyDetailBean
                    		.getAcceptMessage())) {
                    	logger.info("[短信提醒]不能为空！");
                    	throw new BusinessException("[短信提醒]不能为空！");
                    }
                    if (StringUtils.isEmpty(iCCardApplyDetailBean
                    		.getCardNum())) {
                    	logger.info("[卡号]不能为空！");
                    	throw new BusinessException("[卡号]不能为空！");
                    }
                    
                    
                    if (StringUtils.isEmpty(iCCardApplyDetailBean
                            .getVehicleColor())) {
                        logger.info(String.format("提供默认车辆颜色:%s",
                                ICCardCons.VEHICLE_COLOR_DEFAULT));
                        iCCardApplyDetailBean
                                .setVehicleColor(ICCardCons.VEHICLE_COLOR_DEFAULT);
                    }

                    logger.info("申请单明细的卡类型从申请单获取！");
                    iCCardApplyDetailBean
                            .setCardType(iCCardApply.getCardType());

                    logger.info(String.format("正在查询子卡信息(卡类型%s,卡号%s)",
                            iCCardApplyDetailBean.getCardType(),
                            iCCardApplyDetailBean.getCardNum()));

                    ICCard iCCard = getICCardApplyDetail(
                            iCCardApplyDetailBean.getCardNum(),
                            iCCardApplyDetailBean.getCardType());
                    if (iCCard == null) {
                        logger.info("卡信息不存在！");
                        throw new BusinessException("卡信息不存在！");
                    }
                    if (StringUtils.isNotEmpty(iCCard.getVehicleNo())
                            && StringUtils.isNotEmpty(iCCard.getUserId())) {
                        logger.info("卡号已被使用！");
                        throw new BusinessException("卡号已被使用！");
                    }

                    // 绑定主卡ID
                    iCCardApplyDetailBean.setApplyId(uuid);
                    logger.info("查询车辆信息是否存在车辆中心，如不存在则添加车辆");
                    String vehicleUuid = null;
                    if (vehicleUuid == null) {
                        try {
                            vehicleUuid = ServiceUtil
                                    .getVehicleService()
                                    .addVehicle(
                                            iCCardApplyDetailBean
                                                    .getVehicleNo(),
                                            iCCardApplyDetailBean
                                                    .getVehicleColor());
                        } catch (Exception e) {
                            logger.error("调用车辆服务失败！");
                            throw new BusinessException("调用车辆服务失败！");
                        }
                    }

                    iCCardApplyDetailBean.setCardId(iCCard.getId());

                    idao.addModel(iCCardApplyDetailBean);
                    successCardCount++;
                    if (successCardCount == 1) {// 第一条卡发卡地区
                        iCCardApply.setOpenCardOrgCode(iCCard
                                .getOpencardofficeid());
                    }
                    logger.info("子卡信息关联用户车辆信息");
                    ICCard relateICCard = new ICCard();
                    relateICCard.setId(iCCard.getId());
                    relateICCard.setUserId(iCCardApply.getUserId());
                    relateICCard.setUserName(iCCardApply.getUserName());//取会员名称 非申请人名称 modify by zhujianbo 2015-02-06
                    relateICCard.setUserType(iCCardApply.getUserType());
                    relateICCard.setUserRegPhone(iCCardApply.getUserRegPhone());
                    relateICCard.setVehicleNo(iCCardApplyDetailBean
                            .getVehicleNo());
                    relateICCard.setVehicleColor(iCCardApplyDetailBean
                            .getVehicleColor());
                    relateICCard.setVehicleLicense(iCCardApplyDetailBean
                            .getVehicleLicense());
                    relateICCard.setModifiedTime(currentDate);
                    relateICCard.setModifinguser(iCCardApply.getModifier());
                    relateICCard.setMessageOrNot(iCCardApplyDetailBean
                            .getAcceptMessage());
                    relateICCard.setTelephoneNumber(iCCardApplyDetailBean
                            .getPhoneNum());
                    logger.info("绑定车辆信息ID");
                    relateICCard.setVehicleId(vehicleUuid); // 绑定车辆ID
                    // 更新开卡时间
                    relateICCard.setOpenCardTime(currentDate);
                    idao.updateModelPart(relateICCard);
                    result.put(
                            iCCardApplyDetailBean.getCardType()
                                    + ICCardCons.SEPERATOR
                                    + iCCardApplyDetailBean.getCardNum()+ICCardCons.SEPERATOR+index,
                            ICCardCons.OPER_SUCCESS);
                } catch (Exception e) {
                    result.put(
                            iCCardApplyDetailBean.getCardType()
                                    + ICCardCons.SEPERATOR
                                    + iCCardApplyDetailBean.getCardNum()+ICCardCons.SEPERATOR+index,
                            String.format("错误信息：%s", e.getMessage()));
                }

            }
            logger.info("更新卡数量、发卡地区...");
            if (successCardCount > 0) {
                iCCardApply.setCardNum(successCardCount);
                this.update(iCCardApply);
                logger.info("添加处理日志信息");
                auditLog.setApplyId(uuid);
                auditLog.setStepNo("0");
                auditLog.setApprovalTime(currentDate);
                auditLog.setState(ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_PASS);
                auditLog.setApprovalState(ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_PASS);
                idao.addModel(auditLog);
            } else {// 如果没有明细成功 则删除申请记录
                this.remove(iCCardApply);
            }

            logger.info("--------------------------结束批量开卡服务--------------------------");

            return result;

        } catch (Exception e) {
            logger.error("添加IC卡申请操作异常", e);
            throw new BusinessException("添加IC卡申请操作异常", e);
        }
    }

    private ICCard getICCardApplyDetail(String cardNum, String cardType)
            throws Exception {
        ICCardExampleExtended iccee1 = new ICCardExampleExtended();
        ICCardExampleExtended.Criteria icceeCriteria1 = iccee1.createCriteria();
        icceeCriteria1.andCardNumberEqualTo(cardNum);
        icceeCriteria1.andCardTypeEqualTo(cardType);
        List<ICCard> icceeList1 = idao.getModels(iccee1);
        if (icceeList1 == null || icceeList1.size() == 0) {
            return null;
        }
        return icceeList1.get(0);
    }

    @Override
    public ICCardApplyMetaBean getIcCardApplyDetail(String applyDetailId)
            throws BusinessException {
        logger.info("--------------------------开始根据申请单查询ID查询申请信息--------------------------");
        try {

            if (StringUtils.isEmpty(applyDetailId)) {
                logger.info("IC卡申请单明细ID不能为空！");
                throw new BusinessException("IC卡申请单明细ID不能为空！");
            }

            JsonSupport js = new JsonSupport();
            ICCardApplyDetail icd = new ICCardApplyDetail();
            icd.setId(applyDetailId);
            ICCardApplyDetail iCCardApplyDetail = (ICCardApplyDetail) this.idao
                    .getModelById(icd);
            logger.info(String.format("查询IC卡申请单明细结果：%s",
                    js.serialize(iCCardApplyDetail)));
            if (iCCardApplyDetail == null) {
                return null;
            }

            ICCardApply bean = new ICCardApply();
            bean.setId(iCCardApplyDetail.getApplyId());
            bean = super.getById(bean);
            logger.info(String.format("查询IC卡申请单结果：%s", js.serialize(bean)));

            ICCardApplyMetaBean icamb = new ICCardApplyMetaBean();
            icamb.setiCCardApply(bean);
            List<ICCardApplyDetail> list = new ArrayList<ICCardApplyDetail>();
            list.add(iCCardApplyDetail);
            icamb.setiCCardApplyDetail(list);

            logger.info("--------------------------结束根据申请单查询ID查询申请信息--------------------------");
            return icamb;
        } catch (Exception e) {
            logger.error("查询IC卡申请明细操作异常", e);
            throw new BusinessException("查询IC卡申请明细操作异常", e);
        }
    }

    
    @Override
    public PaginationResult<ICCardApplyDetailExtend> queryProcessCard(
            Map<String, String> param) throws BusinessException {
        try {
            logger.info("--------------------------开始根据用户UAAID查询处理中卡片信息--------------------------");
            Map<String,Object> innerParam=new HashMap<String,Object>();
            innerParam.putAll(param);
            List<String> list = new ArrayList<String>();
            list.add(ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_PASS);
            innerParam.put("cardStateNotIn", list);
            PaginationResult<ICCardApplyDetailExtend> pr = queryUserAplly(innerParam);
            logger.info("--------------------------结束根据用户UAAID查询处理中卡片信息--------------------------");
            return pr;
        } catch (Exception e) {
            logger.error("查询处理中卡片信息异常.", e);
            throw new BusinessException("查询处理中卡片信息异常.", e);
        }
    }
    
    @Override
    public Integer countProcessCard(Map<String, String> param) throws BusinessException {
        try {
            logger.info("--------------------------开始统计用户申请中卡数量信息--------------------------");
            Map<String,Object> innerParam=new HashMap<String,Object>();
            innerParam.putAll(param);
            List<String> list = new ArrayList<String>();
            list.add(ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_PASS);
            list.add(ICCardCons.APPLY_AUDIT_STATE_NOT_PASS);
            innerParam.put("cardStateNotIn", list);
            
            int total = (Integer) idao.queryObjectBySQL(
                    "ICCARD_APPLY.countQueryUserAplly", innerParam);
            logger.info("--------------------------结束统计用户申请中卡数量信息--------------------------");
            return total;
        } catch (Exception e) {
            logger.error("查询处理中卡片信息异常.", e);
            throw new BusinessException("查询处理中卡片信息异常.", e);
        }
    }
    
    @Override
    public Integer countFailCard(Map<String,String> param)  throws BusinessException{
    	try {
            logger.info("--------------------------开始统计用户申请失败的卡数量信息--------------------------");
            Map<String,Object> innerParam=new HashMap<String,Object>();
            innerParam.putAll(param);
            List<String> list = new ArrayList<String>();
            //list.add(ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_PASS);
            list.add(ICCardCons.APPLY_AUDIT_STATE_NOT_PASS);
            innerParam.put("cardStateIn", list);
            
            int total = (Integer) idao.queryObjectBySQL(
                    "ICCARD_APPLY.countQueryUserAplly", innerParam);
            logger.info("--------------------------结束统计用户申请失败的卡数量信息--------------------------");
            return total;
        } catch (Exception e) {
            logger.error("查询失败的卡片信息异常.", e);
            throw new BusinessException("查询失败的卡片信息异常.", e);
        }
    }
    @Override
    public PaginationResult<ICCardApplyDetailExtend> queryUserAllAplly(
            Map<String, String> param) throws BusinessException {
        logger.info("--------------------------开始根据用户参数查询所有申请信息--------------------------");
        try {
            param.put("isFetchBalance", "true");
            
            Map<String,Object> innerParam=new HashMap<String,Object>();
            innerParam.putAll(param);
            PaginationResult<ICCardApplyDetailExtend> pr = queryUserAplly(innerParam);
            logger.info("--------------------------结束根据用户参数查询所有申请信息--------------------------");
            return pr;
        } catch (Exception e) {
            logger.error("根据用户参数查询所有申请信息异常", e);
            throw new BusinessException("根据用户参数查询所有申请信息异常", e);
        }

    }

    public PaginationResult<ICCardApplyDetailExtend> queryUserAplly(
            Map<String, Object> param) throws BusinessException {
        try {

            if (param == null) {
                logger.info("参数不能为空!");
                throw new BusinessException("参数不能为空!");
            }
            if (StringUtils.isEmpty((String)param.get("userId"))) {
                logger.info("用户ID不能为空!");
                throw new BusinessException("用户ID不能为空!");
            }
            if (StringUtils.isEmpty((String)param.get("start"))
                    || StringUtils.isEmpty((String)param.get("limit"))) {
                logger.info("分页信息不能为空!");
                throw new BusinessException("分页信息不能为空!");
            }

            JsonSupport js = new JsonSupport();

            List list = idao.queryListBySQL("ICCARD_APPLY.queryUserAplly",
                    param);
            int total = (Integer) idao.queryObjectBySQL(
                    "ICCARD_APPLY.countQueryUserAplly", param);
            logger.info(String.format("查询结果条数%s",
                    list == null ? 0 : list.size()));
            logger.info(String.format("查询结果%s", js.serialize(list)));

            PaginationResult<ICCardApplyDetailExtend> pr = new PaginationResult<ICCardApplyDetailExtend>();
            pr.setData(list);
            pr.setStart(Integer.valueOf((String)param.get("start")));
            pr.setTotal(total);

            return pr;
        } catch (Exception e) {
            logger.error("根据用户参数查询申请信息异常", e);
            throw new BusinessException("根据用户参数查询申请信息异常", e);
        }
    }

}
