package com.ctfo.yppt.portal.service.recharge;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.ServiceImpl;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.GlobalMessage;
import com.ctfo.csm.annotations.AnnotationName;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.intf.IICCardManager;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApply;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyDetail;
import com.ctfo.yppt.baseservice.recharge.cons.ICCardRechargeCons;
import com.ctfo.yppt.baseservice.recharge.intf.IICRechargeApplyManager;
import com.ctfo.yppt.bean.ICCardRechageApplyExtend;
import com.ctfo.yppt.bean.ICCardRechageApplyMetaBean;
import com.ctfo.yppt.portal.view.oilcard.OilCardChargeResultVo;
import com.ctfo.yppt.portal.view.oilcard.OilCardChargeVo;
import com.ctfo.yppt.portal.view.oilcard.OilCardChargeVos;
import com.ctfo.yppt.portal.view.oilcard.OilCardPayVo;

/**
 * 油卡相关业务服务类
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午04:42:26
 *
 */
@AnnotationName(name = "油卡业务服务")
@Service("rechargeService")
public class RechargeServiceImpl extends ServiceImpl implements IRechargeService {

	private static Log logger = LogFactory.getLog(RechargeServiceImpl.class);

    private static IICCardManager icCardManager;
    private static IICRechargeApplyManager icRechargeApplyManager;

    private static BigDecimal MONEY_VALUE = new BigDecimal(100);

	// 构造方法，将后台对象实例化
	private  RechargeServiceImpl() {
	}
	static {
		logger.info(">>>>>>>>" + GlobalMessage.REMO_START_OBJECT);
		icCardManager = (IICCardManager) ServiceFactory.getFactory().getService(IICCardManager.class);
		icRechargeApplyManager = (IICRechargeApplyManager) ServiceFactory.getFactory().getService(IICRechargeApplyManager.class);
		logger.info(">>>>>>>>" + GlobalMessage.REMO_END_OBJECT);
	}
	
	@Override
	public ICRechargeApply getApplyRechargeById(String id)throws Exception{
		try {
			ICRechargeApply bean=new ICRechargeApply();
			bean.setId(id);
			return  icRechargeApplyManager.getById(bean);
		} catch (Exception e) {
			 logger.error("查询充值申请记录异常！", e);
             throw new Exception("查询充值申请记录异常！", e);
		}
		
	};
	/**
     * 油卡充值申请
     * @throws  
     */
    @Override
    public OilCardChargeResultVo rechargeApply(OilCardPayVo oilCardPayVo, OilCardChargeVos oilCardChargeVos, Index index) throws AAException {
        OilCardChargeResultVo oilCardChargeResultVo = new OilCardChargeResultVo();
        try {
            ICCardRechageApplyMetaBean rechargeMetaBean = new ICCardRechageApplyMetaBean();
            // TODO
            // 先给充值申请bean赋值 多少张卡 多少钱 。。。
            BigDecimal totalRechargeMoney = new BigDecimal(0);
            ICRechargeApply rechargeApply = new ICRechargeApply();
            // 之后对每张单卡充值信息bean进行记录-判断是否有卡充值-进行循环填充
            List<ICRechargeApplyDetail> rechargeDetailList = new ArrayList<ICRechargeApplyDetail>();
                for (OilCardChargeVo oilCardChargeVo : oilCardChargeVos.getCardRecharges()) {
                    if (StringUtils.isNotBlank(oilCardChargeVo.getRechargeAmount())) {
                        BigDecimal singleRecharge = new BigDecimal(oilCardChargeVo.getRechargeAmount());
                        totalRechargeMoney = totalRechargeMoney.add(singleRecharge);
                        try {
                            // 通过id获取卡信息-》卡充值信息
                            ICRechargeApplyDetail rechargeDetail = new ICRechargeApplyDetail();
                            ICCard icCard = new ICCard();
                            if (StringUtils.isNotBlank(oilCardChargeVo.getId())) {
                                 icCard.setId(oilCardChargeVo.getId());
                                 icCard = icCardManager.getById(icCard);
                                 //从icccard->rechargeDetail
                                 rechargeDetail.setCardType(icCard.getCardType());
                                 rechargeDetail.setCardId(icCard.getId());
                                 rechargeDetail.setCardNo(icCard.getCardNumber());
                                 rechargeDetail.setVehicleNo(oilCardChargeVo.getCarNO());
                                 rechargeDetail.setMoney(new BigDecimal(oilCardChargeVo.getRechargeAmount()).multiply(MONEY_VALUE));  //从前台取出乘100
                                 rechargeDetailList.add(rechargeDetail);
                                 rechargeDetail.setVehicleColor(icCard.getVehicleColor());
                                 rechargeDetail.setVehicleNo(icCard.getVehicleNo());
                                 rechargeDetail.setParentCardNumber(icCard.getParentCardNumber());
                                 rechargeDetail.setCardAreaCode(icCard.getOpencardofficeid());
                                
                            }
                        } catch (Exception e) {
                            logger.error("通过卡id查询卡详情失败：卡id为：" + oilCardChargeVo.getId(), e);
                            logger.error(GlobalMessage.REMO_EXCEPTION, e);
                            throw new AAException(GlobalMessage.REMO_EXCEPTION, e);
                        }
                    }
                }
            rechargeApply.setId(oilCardPayVo.getOrderId());
            rechargeApply.setTotalMoney(totalRechargeMoney.multiply(MONEY_VALUE));
            rechargeApply.setCardNum(oilCardChargeVos.getCardRecharges().size());
            rechargeApply.setApplyTime((new Date()).getTime());
            rechargeApply.setApplyPersonId(index.getUserId());
            rechargeApply.setUserId(index.getUserId());
            rechargeApply.setUserRegPhone(index.getMobile());
            rechargeApply.setUserName(index.getUserName());
            rechargeApply.setUserType(index.getUserType());
            rechargeApply.setUserLoginName(index.getUserLogin());
            rechargeApply.setDataSource("0");
            rechargeApply.setRemark(ICCardRechargeCons.PRODUCT_NAME);
            rechargeApply.setApplyPersonName(index.getUserName());
            // 账户余额
            rechargeApply.setCaptialMoney(new BigDecimal(oilCardPayVo.getPaychecknum2()).multiply(MONEY_VALUE)); //从前台取出乘100
            // 选择了旺金币
            if (oilCardPayVo.getPaycheck1() != null && oilCardPayVo.getPaycheck1().equals("on")) {
                if(oilCardPayVo.getPaychecknum1() != null) {
                	rechargeApply.setCreditMoney(new BigDecimal(oilCardPayVo.getPaychecknum1()).multiply(MONEY_VALUE));
                    //从前台取出乘100
                    rechargeApply.setCreditNum(new BigDecimal(oilCardPayVo.getPaychecknum1()).multiply(MONEY_VALUE));
                }
            }
            rechargeMetaBean.setRechargeApply(rechargeApply);
            rechargeMetaBean.setRechargeDeatils(rechargeDetailList);
            
            try {
                //进行充值申请业务逻辑处理
                logger.info("生成业务订单开始...");
                ICCardRechageApplyExtend icCardRechageApplyExtend = icRechargeApplyManager.applyRecharge(rechargeMetaBean);
                logger.info("生成业务订单结束...");
                if(icCardRechageApplyExtend != null && icCardRechageApplyExtend.getRechargeApply() != null) {
                    oilCardChargeResultVo.setOrderId(icCardRechageApplyExtend.getRechargeApply().getId());
                    oilCardChargeResultVo.setOnlyCredit(icCardRechageApplyExtend.isOnlyCredit());
                    if(!icCardRechageApplyExtend.isOnlyCredit()) {
                        logger.info("生成跳转支付中心URL开始...");
                        String url = icRechargeApplyManager.buildRequestContent(icCardRechageApplyExtend.getRechargeApply().getId(),index.getUserId(),index.getUserLogin(), icCardRechageApplyExtend.getRechargeApply().getOrderNo(), icCardRechageApplyExtend.getRechargeApply().getRemark(), icCardRechageApplyExtend.getRechargeApply().getCaptialMoney());
                        logger.info("生成跳转支付中心URL结束...");
                        oilCardChargeResultVo.setUrl(url);
                    } else {
                        logger.info("全部使用旺金币支付,不需要生成跳转支付中心URL");
                    }
                } else {
                    logger.error("生成业务订单失败");
                }
            } catch (BusinessException e) {
                logger.error("调用远程油卡充值申请失败（可能生成业务订单或生成跳转支付中心URL失败）", e);
                logger.error(GlobalMessage.REMO_EXCEPTION, e);
                throw new AAException(GlobalMessage.REMO_EXCEPTION, e);
            }
        } catch (Exception e) {
            logger.error("油卡充值申请失败！", e);
        }
        return oilCardChargeResultVo;
    }


    /**
     * 查询油卡充值记录
     * @param requestParam
     * @param index
     * @return
     */
    @Override
    public PaginationResult<?> queryOilCardRechargeList(DynamicSqlParameter requestParam, Index index) throws AAException {
        PaginationResult<?> result = new PaginationResult();
        try {
        	/* @param params page    第几页
            pageSize  每页行数
            userId   用户id
            threeSwitch  卡号/车牌号/电话号
            startTime   开始时间
            endTime     结束时间
            status  充值状态
            */
            Map<String, Object> map = new HashMap<String, Object>();
            if(requestParam != null){
            	//取其中的分页配置、时间参数
                if(requestParam.getEndwith() != null && requestParam.getEndwith().get("operateTime") != null && StringUtils.isNotBlank(requestParam.getEndwith().get("operateTime"))){
                    requestParam.getEndwith().put("operateTime", String.valueOf(new Long(requestParam.getEndwith().get("operateTime")) + 86400000L));
                }
            	map.put("page", requestParam.getPage());
            	map.put("pageSize", requestParam.getRows());
            	map.put("userId", index.getUserId());
            	if(requestParam.getEqual() != null){
            		if(requestParam.getEqual().get("processStatus")!=null){
            			map.put("processStatus", "true");
            		}
            		if(requestParam.getEqual().get("threeswitch")!=null){
            			try {
            				map.put("threeSwitch", URLDecoder.decode(requestParam.getEqual().get("threeswitch"),"UTF-8"));
            			} catch (UnsupportedEncodingException e) {
            				e.printStackTrace();
            			}
            		}
            		if(requestParam.getEqual().get("paystatus") != null){
            			
            			map.put("status", requestParam.getEqual().get("paystatus"));
            		}
            	}
            	if(requestParam.getStartwith() != null){
            		map.put("startTime", requestParam.getStartwith().get("operateTime"));
            	}
            	if(requestParam.getEndwith() != null){
            		map.put("endTime", requestParam.getEndwith().get("operateTime"));
            	}
            }
        	result = icRechargeApplyManager.paginateRecharge(map);
        	result.setStart(requestParam.getPage());
		} catch (BusinessException e) {
			logger.error("icRechargeApplyManager.paginateRecharge查询油卡充值记录失败"+e);
			throw new AAException("icRechargeApplyManager.paginateRecharge查询油卡充值记录失败",e);
		}
        return result;

    }
    
    /**
      * 统计累计油卡充值
      * @param requestParam
      * @param index
      * @return
      */
    @Override
    public BigDecimal countOilCardRecharge(DynamicSqlParameter requestParam, Index index) {
        
        BigDecimal countOilCardRecharge = new BigDecimal(0);
        
        return countOilCardRecharge;
    }
}
