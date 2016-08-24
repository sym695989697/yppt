package com.ctfo.sinoiov.mobile.webapi.service.yppt;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.beans.SimpleCode;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryTradeRecordInfoReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.QueryTradeRecordInfoRsp;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.TradeInfoError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfo;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoHistory;
import com.ctfo.yppt.bean.RechargeApplyDetailInfoBean;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：交易记录详情
 * <br>
 * 描述：
 * <br>
 * 授权 : (C) Copyright (c) 2011
 * <br>
 * 公司 : 北京中交车联科技服务有限公司
 * <br>
 * -----------------------------------------------------------------------------
 * <br>
 * 修改历史
 * <br>
 * <table width="432" border="1">
 * <tr><td>版本</td><td>时间</td><td>作者</td><td>改变</td></tr>
 * <tr><td>1.0</td><td>2015-1-23</td><td>JiangXF</td><td>创建</td></tr>
 * </table>
 * <br>
 * <font color="#FF0000">注意: 本内容仅限于[北京中交兴路车联网科技有限公司]内部使用，禁止转发</font>
 * <br>
 * 
 * @version 1.0
 * 
 * @author JiangXF
 * @since JDK1.6
 */

@Service("queryTradeRecordInfoManagerImpl")
public class QueryTradeRecordInfoManagerImpl implements IJsonService {
	
	//打日志信息
	protected static final Log logger = LogFactory.getLog(QueryTradeRecordInfoManagerImpl.class);
	
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		logger.info(">>>>>>>>>>>>查询交易详情<<<<<<<<<<<<");
		QueryTradeRecordInfoRsp response = new QueryTradeRecordInfoRsp();
		
		//获取后台代码服务
		QueryTradeRecordInfoReq req = (QueryTradeRecordInfoReq) request.getBody();
		//创造查询条件前提
		try {
			logger.debug("手机APP客户端调用【查询交易详情（Y300015）】接口，用户ID：" + req.getUserId());
			logger.info(">>>>>>>>>>>>用户ID["+req.getUserId()+"],交易ID["+req.getTradeId()+"],查询类型0：表示加油；1：表示充值:["+req.getTradeType()+"]<<<<<<<<<<<<");
			if("1".equals(req.getTradeType())){
				logger.info(">>>>>>>>>>>>调用查询充值交易详情，开始时间["+new Date()+"]<<<<<<<<<<<<");
				//ICardRechageMetaBean rechageMetaBean = ImplementationSupport.getICRechargeApplyManager().getICardRechageMetaBeanByapplyId(req.getTradeId());
				RechargeApplyDetailInfoBean bean = new RechargeApplyDetailInfoBean();
				bean = ImplementationSupport.getICRechargeApplyDetailManager().getRechargeInfoForApp(req.getTradeId());
				logger.info(">>>>>>>>>>>>调用查询充值交易详情，结束时间["+new Date()+"]<<<<<<<<<<<<");
				if(bean != null){
					response.setId(req.getTradeId());
					response.setCardNum(bean.getCardNo());
					response.setTradeMoney(PublicStaticParam.fen2Yuan(bean.getCaptMoney()).toString());
					response.setTradeTime(Long.parseLong(bean.getTradeTime()));
					response.setBalance("0.00");
					SimpleCode code = ImplementationSupport.getSimpleCodeManager().getSimpleCodeByTypeAndCode(PublicStaticParam.PAY_MODE, bean.getPayWay());
					if(code != null){
						response.setPayMode(code.getName());
					}
				}
			}else{
				ICCardTradeInfo q_bean =  new ICCardTradeInfo();
				q_bean.setId(req.getTradeId());
				logger.info(">>>>>>>>>>>>调用查询加油交易详情，开始时间["+new Date()+"]<<<<<<<<<<<<");
				//ImplementationSupport.getICCardTradeInfoManager()
				ICCardTradeInfo bean = ImplementationSupport.getICCardTradeInfoManager().getById(q_bean);
				//当前月没有交易信息则查询之前的信息
				if(bean == null){
					ICCardTradeInfoHistory cardTradeHist= new ICCardTradeInfoHistory();
					cardTradeHist.setId(req.getTradeId());
					cardTradeHist = ImplementationSupport.getICCardTradeInfoHistoryManager().getById(cardTradeHist);
					bean = new ICCardTradeInfo();
					bean.setId(cardTradeHist.getId());
					bean.setCardNo(cardTradeHist.getCardNo());
					bean.setTradeMoney(cardTradeHist.getTradeMoney());
					bean.setProductName(cardTradeHist.getProductName());
					bean.setProductNum(cardTradeHist.getProductNum());
					bean.setProductNum(cardTradeHist.getProductNum());
					bean.setProductPrice(cardTradeHist.getProductPrice());
					bean.setTradeTime(cardTradeHist.getTradeTime());
					bean.setTradeAdress(cardTradeHist.getTradeAdress());
					bean.setTradeState(cardTradeHist.getTradeState());
					bean.setTradeType(cardTradeHist.getTradeType());
					bean.setVehicleNo(cardTradeHist.getVehicleNo());
					bean.setCardBalance(cardTradeHist.getCardBalance());
				}
				
				logger.info(">>>>>>>>>>>>调用查询加油交易详情，结束时间["+new Date()+"]<<<<<<<<<<<<");
				logger.info(">>>>>>>>>>>>数据对象转换开始<<<<<<<<<<<<");
				if(bean != null){
					response.setId(bean.getId());
					response.setCardNum(bean.getCardNo());
					response.setTradeMoney(""+PublicStaticParam.fen2Yuan(bean.getTradeMoney()));
					response.setOil(bean.getProductName());
					response.setCount(PublicStaticParam.fen2Yuan(new BigDecimal(bean.getProductNum())).toString());
					response.setPrice(""+PublicStaticParam.fen2Yuan(bean.getProductPrice()));
					response.setTradeTime(bean.getTradeTime());
					response.setTradePlace(bean.getTradeAdress());
					response.setTradeState(bean.getTradeState());
					response.setTradeType(bean.getTradeType());
					response.setVehicleNo(bean.getVehicleNo());
					//元转分
					response.setBalance(bean.getCardBalance()==null?"0.00":(bean.getCardBalance().divide(new BigDecimal(100)).toString()));
					logger.info(">>>>>>>>>>>>数据对象转换结束<<<<<<<<<<<<");
				}else{
					logger.info(">>>>>>>>>>>>查询记录为空<<<<<<<<<<<<");
				}
			}
			logger.info(">>>>>>>>>>>>查询结束<<<<<<<<<<<<");
			response.setTradeType(req.getTradeType());
			
			
		} catch (Exception e) {
			logger.error("查询失败！",e);
			throw new ClientException("查询交易详情失败,UserId="+req.getUserId(),e);
		}
		
		return response;
	}
	
	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		QueryTradeRecordInfoReq req = (QueryTradeRecordInfoReq) request.getBody();
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E120002",UserError.E120002);
		}
		if(StringUtils.isBlank(req.getTradeId())){
			throw new ClientException("E230007",TradeInfoError.E230007);
		}
		if(StringUtils.isBlank(req.getTradeType())){
			throw new ClientException("E230006",TradeInfoError.E230006);
		}

	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", QueryTradeRecordInfoReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}

}
