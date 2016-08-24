package com.ctfo.yppt.portal.action.trade;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Constants;
import com.ctfo.common.utils.GlobalMessage;
import com.ctfo.common.utils.NumberUtil;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.yppt.portal.service.trade.ITradeService;


/**
 * 
 * 油卡相关业务控制类
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午04:26:22
 *
 */
@Controller
@Scope("prototype")
public class TradeController extends BaseControler{
	
	@Resource(name="tradeService" ,description= "油卡交易管理service")
	ITradeService tradeService;
	private static Log logger = LogFactory.getLog(TradeController.class);

	
	/**
	 * 查询油卡交易记录
	 * @return
	 */
	@ResponseBody
	public PaginationResult<?> queryOilCardTradeList() {
		if(requestParam == null){
			requestParam = new DynamicSqlParameter();
		}
		if(requestParam.getEqual() == null){
			requestParam.setEqual( new HashMap<String, String>());
		}
		try {
			//交易状态为处理中/处理失败的情况,不用查询数据库,直接返回无数据.
			if("2".equals(requestParam.getEqual().get("paystatus")) 
					|| "3".equals(requestParam.getEqual().get("paystatus")) ){
				return result;
			}else{
				requestParam.getEqual().remove("paystatus");
			}
		    if("1".equals(requestParam.getEqual().get("queryHistory"))){//查询历史记录
		        result = tradeService.queryOilCardTradeInfoList(requestParam, index);
		    } else {
		        result = tradeService.queryOilCardTradeInfoHistoryList(requestParam, index);
		    }
            result.setDataObject(Constants.OPER_SUCCESS);
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (AAException e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======查询油卡交易记录异常：======", e);
        }
		return result;
	}
	
	@ResponseBody
	public PaginationResult<?> queryOilCardTradeListForHomePage() {
		if(requestParam == null){
			requestParam = new DynamicSqlParameter();
		}
		if(requestParam.getEqual() == null){
			requestParam.setEqual( new HashMap<String, String>());
		}
		try {
			//交易状态为处理中/处理失败的情况,不用查询数据库,直接返回无数据.
	        result = tradeService.queryOilCardTradeInfoList(requestParam, index);
		    if(result == null || result.getTotal() < 5){//查询五条以上的数据
		    	PaginationResult resultHis = tradeService.queryOilCardTradeInfoHistoryList(requestParam, index);
		    	int loopNum =  5 - result.getTotal();
		    	loopNum = loopNum >resultHis.getTotal() ? resultHis.getTotal() : loopNum;
		    	List resultList = result.getData();
		    	List resultHisList = resultHis.getData();
		    	for (int i = 0; i< loopNum; i++){
		    		resultList.add(resultHisList.get(i));
		    	}
		    	result.setTotal(result.getTotal() + loopNum);
		    	
		    }
            result.setDataObject(Constants.OPER_SUCCESS);
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (AAException e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======查询油卡交易记录异常：======", e);
        }
		return result;
	}
	
    /**
     * 加油总量统计
     * @return
     */
    @ResponseBody
    public PaginationResult<?> getFuelTotal(){
        if (requestParam == null) {
            requestParam = new DynamicSqlParameter();
        }
        if (requestParam.getEqual() == null) {
            requestParam.setEqual(new HashMap<String, String>());
        }
        try {
            BigDecimal countOilCardTrade = new BigDecimal(0);
            //TODO 判断是历史还是实时
//            if(true){
//                countOilCardTrade = tradeService.countOilCardTradeInfo(requestParam, index);
//            } else {
//                countOilCardTrade = tradeService.countOilCardTradeInfoHistory(requestParam, index);
//            }
            result.setDataObject(NumberUtil.converMoneyDefaultFormat(countOilCardTrade));
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (AAException e) {
            result.setDataObject(0);
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======查询加油总量统计异常：======", e);
        }
        return result;
    }
}