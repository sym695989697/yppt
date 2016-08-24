package com.ctfo.chpt.action.iccard.trade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.chpt.bean.iccard.vo.GoldTradeDetailsVo;
import com.ctfo.chpt.service.iccard.trade.IGoldTradeService;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.gatewayservice.interfaces.bean.credit.CreditIO;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApply;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyDetail;
import com.ctfo.yppt.bean.ICardRechageMetaBean;

/**
 * 旺金币交易记录
 * 
 * @author jichao
 */
@Controller
@RequestMapping("/goldTrade")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("all")
public class GoldTradeController extends BaseControler{

	private static Log logger = LogFactory.getLog(GoldTradeController.class);
	
	@Autowired
	private IGoldTradeService iGoldTradeService;
	@ResponseBody
	public PaginationResult getGoldTradeListPage() {
		PaginationResult t = new PaginationResult();
		try {
			Map<String, Object> param=new HashMap<String, Object>();
			if(requestParam!=null){
				if( requestParam.getEqual()!=null){
					param.put("type",requestParam.getEqual().get("tradeType"));
					param.put("regPhone",requestParam.getEqual().get("userRegPhone") );
					param.put("userName", requestParam.getEqual().get("userName"));
				}
				if(requestParam.getStartwith()!=null){
					param.put("dealStartTime", requestParam.getStartwith().get("tradeTime"));
				}
				if(requestParam.getEndwith()!=null){
					param.put("dealEndTime", requestParam.getEndwith().get("tradeTime"));
				}
			}
			param.put("page", requestParam.getPage());
			param.put("pageSize", requestParam.getRows());
			
			
			result = iGoldTradeService.getListPage(param);
			
		} catch (Exception e) {
			forwardError(((e.getMessage() == null
					|| e.getMessage().isEmpty() || e.getMessage().equals(
					EMPTY_STRING)) ? "查询信息集合异常!" : e.getMessage()));
			logger.error("查询信息集合异常!", e);
			result.setMessage(e.getMessage());
		}
		//return t;
		return result;
	}
	
	
	
	@ResponseBody
	public PaginationResult getGoldTradeDetailsPage(@RequestParam("reasonId")String reasonId) {
		try {
			 if(requestParam == null){
				requestParam = new DynamicSqlParameter();
			}
			if(requestParam.getEqual() == null){
				requestParam.setEqual( new HashMap<String, String>());
			}
			//按原因ID查询交易申请记录
			List goldTradeDetailsVoList = iGoldTradeService.getGoldTradeDetailsVoList(reasonId);
			//获取卡充值记录
			result.setData(goldTradeDetailsVoList);
			
		} catch (Exception e) {
			forwardError(((e.getMessage() == null
					|| e.getMessage().isEmpty() || e.getMessage().equals(
					EMPTY_STRING)) ? "查询信息集合异常!" : e.getMessage()));
			logger.error("查询信息集合异常!", e);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
}
