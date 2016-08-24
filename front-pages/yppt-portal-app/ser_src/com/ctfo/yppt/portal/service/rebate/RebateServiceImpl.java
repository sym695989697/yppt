package com.ctfo.yppt.portal.service.rebate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.ServiceImpl;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Constants;
import com.ctfo.common.utils.GlobalMessage;
import com.ctfo.csm.annotations.AnnotationName;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.gatewayservice.interfaces.bean.credit.CreditIO;
import com.ctfo.gatewayservice.interfaces.service.ICreditService;
import com.ctfo.yppt.baseservice.rebate.intf.IICRebateMessMemberManager;
import com.ctfo.yppt.baseservice.system.beans.UserStat;
import com.ctfo.yppt.baseservice.system.intf.IUserStatManager;

/**
 * 油卡相关业务服务类
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午04:42:26
 *
 */
@AnnotationName(name = "油卡业务服务")
@Service("rebateService")
public class RebateServiceImpl extends ServiceImpl implements IRebateService {

	private static Log logger = LogFactory.getLog(RebateServiceImpl.class);

    private static IICRebateMessMemberManager icRebateMessMemberManager;
    private static ICreditService creditService;
    private static IUserStatManager userStatManager;

	// 构造方法，将后台对象实例化
	private  RebateServiceImpl() {
	}
	static {
		logger.info(">>>>>>>>" + GlobalMessage.REMO_START_OBJECT);
		icRebateMessMemberManager = (IICRebateMessMemberManager) ServiceFactory.soaService(IICRebateMessMemberManager.class);
		creditService = (ICreditService) ServiceFactory.soaService(ICreditService.class);
		userStatManager = (IUserStatManager) ServiceFactory.soaService(IUserStatManager.class);
		logger.info(">>>>>>>>" + GlobalMessage.REMO_END_OBJECT);
	}
    
    /**
     * 查询油卡返利记录
     */
    @Override
    public PaginationResult<CreditIO> queryOilCardRebateList(DynamicSqlParameter requestParam, String userId) throws AAException {
    	PaginationResult<CreditIO> result = new PaginationResult<CreditIO>();
            try {
                logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
                if (StringUtils.isNotBlank(userId)) {
                    //取其中的分页配置、时间参数
                    if(requestParam.getEndwith() != null && requestParam.getEndwith().get("operateTime") != null && StringUtils.isNotBlank(requestParam.getEndwith().get("operateTime"))){
                        requestParam.getEndwith().put("operateTime", String.valueOf(new Long(requestParam.getEndwith().get("operateTime")) + 86400000L));
                    }
                    Map<String, Object> parmaMap = new HashMap<String, Object>();
                    parmaMap.put("userId", userId);
                    if(requestParam != null ){
                    	if(requestParam.getStartwith() != null){
                    		parmaMap.put("dealStartTime", requestParam.getStartwith().get("operateTime"));
                    	}
                    	if(requestParam.getEndwith() != null){
                    		parmaMap.put("dealEndTime", requestParam.getEndwith().get("operateTime"));
                    	}
                    	if(requestParam.getEqual() != null){
                    		parmaMap.put("model", requestParam.getEqual().get("model"));
                    	}
                    	parmaMap.put("page", requestParam.getPage());
                    	parmaMap.put("pageSize", requestParam.getRows());//TODO 确定前台门户的分页参数..
                    }
                    //TODO 查询油卡返利记录接口
                    result = creditService.queryCreditIO(parmaMap);
                    result.setStart(requestParam.getPage());
                    result.setMessage(GlobalMessage.SUCCESS);
                    result.setDataObject(Constants.OPER_SUCCESS);
                } else {
                    result.setMessage(GlobalMessage.FAIL);
                    result.setDataObject(Constants.OPER_FAIL);
                    logger.debug(">>>>>>>>账户所属用户ID为空【requestParam.equal.userId=" + userId + "】");
                }
                logger.info(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
            } catch (Exception e) {
                result.setMessage(GlobalMessage.FAIL);
                result.setDataObject(Constants.OPER_ERROR);
                logger.error(GlobalMessage.REMO_EXCEPTION, e);
                throw new AAException(GlobalMessage.REMO_EXCEPTION, e);
            }
            return result;
    }
	/**
	 * 上月累计油卡返利
	 */
	@Override
	public BigDecimal queryOilCardRebatePreMonth(DynamicSqlParameter requestParam, Index index)throws AAException{
	    BigDecimal rebateMoney = new BigDecimal(0);
        try {
            if (index != null && StringUtils.isNotBlank(index.getUserId())) {
            	//TODO
                rebateMoney = icRebateMessMemberManager.statUserRebatePreMonth(index.getUserId());
//            	UserStat userStat = userStatManager.getUserstatByUserId(index.getUserId());
            	
//            	rebateMoney = userStat.getRebateMoney();
            } else {
                logger.debug(">>>>>>>>账户所属用户不存或ID为空");
            }
        } catch (Exception e) {
            logger.error(GlobalMessage.REMO_EXCEPTION, e);
            throw new AAException(GlobalMessage.REMO_EXCEPTION, e);
        }
        return rebateMoney;
	}
	
	/**
	 * 查询油卡返利进出账总额
	 */
    @Override
    public PaginationResult<CreditIO> queryOilCardRebateIO(DynamicSqlParameter requestParam, Index index)throws AAException{
        PaginationResult<CreditIO> result = new PaginationResult<CreditIO>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        try {
//            if (index != null && StringUtils.isNotBlank(index.getUserId())) {
//                // 取其中的分页配置、时间参数
//                queryMap.put("userId", index.getUserId());
//                if(requestParam.getEqual() != null && StringUtils.isNotBlank(requestParam.getEqual().get("model"))){
//                    queryMap.put("model", requestParam.getEqual().get("model"));
//                }
//                if(requestParam.getStartwith() != null && requestParam.getStartwith().get("operateTime") != null && StringUtils.isNotBlank(requestParam.getStartwith().get("operateTime"))){
//                    queryMap.put("startTime", requestParam.getEqual().get("operateTime"));
//                }
//                if(requestParam.getEndwith() != null && requestParam.getEndwith().get("operateTime") != null && StringUtils.isNotBlank(requestParam.getEndwith().get("operateTime"))){
//                    queryMap.put("endTime", new Long(requestParam.getEndwith().get("operateTime"))+ 86400000L);
//                }
//                //TODO 查询油卡返利进出账总额接口
//                //List resultList = chptManager.dynamicSelect(sql);
//                List resultList = null;
//                List totalList = new ArrayList<Map<String, String>>();
//                Map<String, String> totalMap = new HashMap<String, String>();
//                for(Object map : resultList) {
//                    Object type = ((Map)map).get("TYPE");
//                    Object total = ((Map)map).get("TOTAL");
//                    if(total != null && type != null) {
//                        if("1".equalsIgnoreCase(type.toString())) {
//                            totalMap.put("INPUT", total.toString());
//                        }
//                        if("2".equalsIgnoreCase(type.toString())) {
//                            totalMap.put("OUTPUT", total.toString());
//                        }
//                    }
//                }
//                totalList.add(totalMap);
//                result.setData(totalList);
//                result.setMessage(GlobalMessage.SUCCESS);
//                result.setDataObject(Constants.OPER_SUCCESS);
//            } else {
//                result.setMessage(GlobalMessage.FAIL);
//                result.setDataObject(Constants.OPER_FAIL);
//                logger.debug(">>>>>>>>账户所属用户不存或ID为空");
//            }
        } catch (Exception e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error(GlobalMessage.REMO_EXCEPTION, e);
            throw new AAException(GlobalMessage.REMO_EXCEPTION, e);
        }
        return result;
    }

}
