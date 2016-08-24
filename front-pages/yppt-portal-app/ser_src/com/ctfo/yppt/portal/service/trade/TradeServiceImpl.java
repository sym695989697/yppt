package com.ctfo.yppt.portal.service.trade;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.HashMap;
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
import com.ctfo.common.utils.Converter;
import com.ctfo.common.utils.GlobalMessage;
import com.ctfo.csm.annotations.AnnotationName;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.yppt.baseservice.card.beans.ICCardExampleExtended;
import com.ctfo.yppt.baseservice.card.intf.IICCardManager;
import com.ctfo.yppt.baseservice.system.intf.IICCardStatManager;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfo;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoHistory;
import com.ctfo.yppt.baseservice.trade.intf.IICCardTradeInfoHistoryManager;
import com.ctfo.yppt.baseservice.trade.intf.IICCardTradeInfoManager;

/**
 * 油卡相关业务服务类
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午04:42:26
 *
 */
@AnnotationName(name = "油卡业务服务")
@Service("tradeService")
public class TradeServiceImpl extends ServiceImpl implements ITradeService {

	private static Log logger = LogFactory.getLog(TradeServiceImpl.class);

    private static IICCardManager icCardManager;
    private static IICCardTradeInfoManager tradeManager;
    private static IICCardTradeInfoHistoryManager tradeHistoryManager;

    private static int MONEY_VALUE = 100;

    private static IICCardStatManager icCardStatManager;


	// 构造方法，将后台对象实例化
	private  TradeServiceImpl() {
	}
	static {
		logger.info(">>>>>>>>" + GlobalMessage.REMO_START_OBJECT);
		icCardManager = (IICCardManager) ServiceFactory.getFactory().getService(IICCardManager.class);
		icCardStatManager = (IICCardStatManager) ServiceFactory.getFactory().getService(IICCardStatManager.class);
		tradeManager = (IICCardTradeInfoManager) ServiceFactory.getFactory().getService(IICCardTradeInfoManager.class);
		tradeHistoryManager = (IICCardTradeInfoHistoryManager) ServiceFactory.getFactory().getService(IICCardTradeInfoHistoryManager.class);
		logger.info(">>>>>>>>" + GlobalMessage.REMO_END_OBJECT);
	}
    
    /**
     * 统计油卡消费（加油）记录（实时）
     */
    @Override
    public BigDecimal countOilCardTradeInfo(DynamicSqlParameter requestParam, Index index) throws AAException {
        BigDecimal countOilCardTrade = new BigDecimal(0);
        return countOilCardTrade;
    }
    
    /**
     * 统计油卡消费（加油）记录（历史）
     */
    @Override
    public BigDecimal countOilCardTradeInfoHistory(DynamicSqlParameter requestParam, Index index) throws AAException {
        BigDecimal countOilCardTradeHistory = new BigDecimal(0);
        return countOilCardTradeHistory;
    }
	
    /**
     * 查询油卡消费（加油）记录（实时）
     */
    @Override
    public PaginationResult<?> queryOilCardTradeInfoList(DynamicSqlParameter requestParam, Index index) throws AAException {
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
            		if(requestParam.getEqual().get("threeswitch")!=null){
            			try {
            				map.put("threeSwitch", URLDecoder.decode(requestParam.getEqual().get("threeswitch"),"UTF-8"));
            			} catch (UnsupportedEncodingException e) {
            				logger.warn("转换参数异常", e);
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
        	result = tradeManager.paginateTradeInfo(map);
		} catch (BusinessException e) {
			logger.error("icRechargeApplyManager.paginateRecharge查询油卡充值记录失败"+e);
			throw new AAException("icRechargeApplyManager.paginateRecharge查询油卡充值记录失败",e);
		}
        return result;
    }
	
    /**
     * 查询油卡消费（加油）记录（历史）
     */
    @Override
    public PaginationResult<?> queryOilCardTradeInfoHistoryList(DynamicSqlParameter requestParam, Index index) throws AAException {
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
            		if(requestParam.getEqual().get("threeswitch")!=null){
            			try {
            				map.put("threeSwitch", URLDecoder.decode(requestParam.getEqual().get("threeswitch"),"UTF-8"));
            			} catch (UnsupportedEncodingException e) {
            				logger.warn("转换参数异常", e);
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
        	result = tradeHistoryManager.paginateTradeInfoHistory(map);
        	result.setStart(requestParam.getPage());
		} catch (BusinessException e) {
			logger.error("icRechargeApplyManager.paginateRecharge查询油卡充值记录失败"+e);
			throw new AAException("icRechargeApplyManager.paginateRecharge查询油卡充值记录失败",e);
		}
        return result;
    }
	
	/**
	 * 根据用户ID统计用户油卡数量
	 * @param requestParam
	 * @param index
	 * @return
	 * @throws AAException
	 */
	@Override
	public Integer countOilCardByUserId(DynamicSqlParameter requestParam, Index index) throws AAException {
	    Integer countCard = 0;
	    try {
	        logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
	        if(index != null && StringUtils.isNotBlank(index.getUserId())) {
	            ICCardExampleExtended icCardExample = new ICCardExampleExtended();
	            icCardExample.createCriteria().andUserIdEqualTo(index.getUserId());
	            Converter.paramToExampleExtended(requestParam, icCardExample);
	            countCard = icCardManager.count(icCardExample);
	        } else {
	            logger.debug(">>>>>>>>账户所属用户不存在或ID为空");
	        }
	        logger.info(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
        } catch (Exception e) {
            logger.error(GlobalMessage.REMO_EXCEPTION, e);
            throw new AAException(GlobalMessage.REMO_EXCEPTION, e);
        }
	    return countCard;
	}
}
