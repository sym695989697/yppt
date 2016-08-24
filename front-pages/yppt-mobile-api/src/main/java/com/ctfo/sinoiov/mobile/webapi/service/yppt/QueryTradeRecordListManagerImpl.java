package com.ctfo.sinoiov.mobile.webapi.service.yppt;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.common.models.PaginationResult;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam.PageValue;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryTradeRecordListReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.QueryTradeRecordListRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.OilCardTradeRecordVo;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.TradeInfoError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.yppt.bean.ICCardRechageApplyBean;
import com.ctfo.yppt.bean.ICCardTradeInfoBean;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：查询交易记录列表
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

@Service("queryTradeRecordListManagerImpl")//查询交易列表
public class QueryTradeRecordListManagerImpl implements IJsonService{
	
	//日志信息
	protected static final Log logger = LogFactory.getLog(QueryTradeRecordListManagerImpl.class);

	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		logger.info(">>>>>>>>>>>>调用IC卡充值、加油交易<<<<<<<<<<<<");
		QueryTradeRecordListRsp response  = new QueryTradeRecordListRsp();
		QueryTradeRecordListReq req = (QueryTradeRecordListReq) request.getBody();
		try {
			logger.debug("手机APP客户端调用【查询交易记录（Y300014）】接口，用户ID：" + req.getUserId());
			
	          //取时间 1本月 2上月 3近三月
	          
	          long times [] = getDateSet(new Long(req.getTimeType()));
	          
	         if(times == null || times.length<2){
	        	 logger.info(">>>>>>>>>>>>获取交易时间为空<<<<<<<<<<<<");
	        	 throw new ClientException("时间参数为空！");
	         }else{
	        	 logger.info(">>>>>>>>>>>>交易开始时间["+times[0]+"],交易结束时间["+times[1]+"]<<<<<<<<<<<<");
	         }
	          
	         logger.info(">>>>>>>>>>>>用户ID["+req.getUserId()+"]<<<<<<<<<<<<");
	          Map<String,Object> map = new HashMap<String,Object>();
	          map.put("userId", req.getUserId());
	          map.put("startTime", times[0]);
	          map.put("endTime", times[1]);
	          map.put("page", req.getPage());
	          map.put("pageSize", req.getPageSize());
	          /**
	           * 根据类型查询相应的数据
	           * 
	           */
	          PaginationResult<?> result = null;
	          BigDecimal countMoney = new BigDecimal(0);
	          if(req.getType()==1){
	        	  //充值
	        	  logger.info(">>>>>>>>>>>>调用充值后台服务查询交易开始时间["+new Date()+"]<<<<<<<<<<<<");
	        	  result = ImplementationSupport.getICRechargeApplyManager().paginateRecharge(map);
	        	  logger.info(">>>>>>>>>>>>调用充值后台服务查询交易结束时间["+new Date()+"]<<<<<<<<<<<<");
	        	  logger.info(">>>>>>>>>>>>调用充值后台服务查询交易总金额开始时间["+new Date()+"]<<<<<<<<<<<<");
	        	  Map<String,Object> tradeCountParams = new HashMap<String,Object>();
	        	  tradeCountParams.put("userId", req.getUserId());
	        	  tradeCountParams.put("startTime", times[0]);
	        	  tradeCountParams.put("endTime", times[1]);
	        	  tradeCountParams.put("page", req.getPage());
	        	  tradeCountParams.put("pageSize", req.getPageSize());
	        	  tradeCountParams.put("status", "05");//统计充值成功的总金额
	        	  countMoney = ImplementationSupport.getICRechargeApplyManager().sumRecharge(tradeCountParams);
	        	  logger.info(">>>>>>>>>>>>调用充值后台服务查询交易总金额结束时间["+new Date()+"]<<<<<<<<<<<<");
	          }else{
	        	  //加油
	        	  if("1".equals(req.getTimeType())){
	        		  //查加油本月数据
	        		  logger.info(">>>>>>>>>>>>调用本月加油后台服务查询交易总金额开始时间["+new Date()+"]<<<<<<<<<<<<");
	        		  result = ImplementationSupport.getICCardTradeInfoManager().paginateTradeInfo(map);
	        		  logger.info(">>>>>>>>>>>>调用本月加油后台服务查询交易结束时间["+new Date()+"]<<<<<<<<<<<<");
		        	  logger.info(">>>>>>>>>>>>调用本月加油后台服务查询交易总金额开始时间["+new Date()+"]<<<<<<<<<<<<");
	        		  countMoney = ImplementationSupport.getICCardTradeInfoManager().countTradeInfo(map);
		        	  logger.info(">>>>>>>>>>>>调用本月加油后台服务查询交易总金额结束时间["+new Date()+"]<<<<<<<<<<<<");
	        		  
	        	  }else{
		        	  logger.info(">>>>>>>>>>>>调用历史加油后台服务查询交易总金额开始时间["+new Date()+"]<<<<<<<<<<<<");
	        		  result = ImplementationSupport.getICCardTradeInfoHistoryManager().paginateTradeInfoHistory(map);
	        		  logger.info(">>>>>>>>>>>>调用历史加油后台服务查询交易结束时间["+new Date()+"]<<<<<<<<<<<<");
		        	  logger.info(">>>>>>>>>>>>调用历史加油后台服务查询交易总金额开始时间["+new Date()+"]<<<<<<<<<<<<");
	        		  countMoney = ImplementationSupport.getICCardTradeInfoHistoryManager().statTradeInfoHistory(map);
		        	  logger.info(">>>>>>>>>>>>调用历史加油后台服务查询交易总金额结束时间["+new Date()+"]<<<<<<<<<<<<");
	        	  }
	        	 
	          }
	          toObject(result,req.getType()+"",response);
	          response.setTotal(""+PublicStaticParam.fen2Yuan(countMoney));
    		  response.setTotalNum(result.getTotal());
			
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			logger.error("查询单张油卡交易列表失败："+e);
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			throw new ClientException("查询单张油卡交易列表失败,MemberId="+req.getMemberId(),e);
		}
		return response;
	}
	
	public static void toObject(PaginationResult<?> result,String type,QueryTradeRecordListRsp response){
		if(result == null || result.getData() == null)
		{
			logger.info(">>>>>>>>>>>>查询数据为空<<<<<<<<<<<<");
			return;
		}
		if("1".equals(type)){
			//充值
			logger.info(">>>>>>>>>>>>充值交易数据转换<<<<<<<<<<<<");
			List<ICCardRechageApplyBean> list = (List<ICCardRechageApplyBean>) result.getData();
			List<OilCardTradeRecordVo> cardTradeList = null;
			if(list != null && list.size()>0){
				cardTradeList = new ArrayList<OilCardTradeRecordVo>();
				for(ICCardRechageApplyBean bean:list){
					OilCardTradeRecordVo cardTrade = new OilCardTradeRecordVo();
					cardTrade.setTradeId(bean.getId());
					cardTrade.setTradeMoney(bean.getTradeMoney()+"");
					
					if(StringUtils.isNotBlank(bean.getTradeStatus())){
						String status = "0";	//处理中
						if("03".equals(bean.getTradeStatus())){
							//失败
							status = "2";		//失败
						}else if("05".equals(bean.getTradeStatus())){
							//成功
							status = "1";	//成功
						}
						cardTrade.setTradeState(status);
					}
					//cardTrade.setTradeState("1");
					cardTrade.setCardNum(bean.getCardNo());
					cardTrade.setVehicleNum(bean.getVehicleNo());
					cardTrade.setTradeTime(bean.getTradeTime()+"");
					cardTrade.setResultMsg(bean.getFaileReason());
					cardTrade.setTradeType(type);
					cardTradeList.add(cardTrade);
				}
			}
			logger.info(">>>>>>>>>>>>充值交易数据转换完成<<<<<<<<<<<<");
			response.setList(cardTradeList);
		}else{
			//加油
			logger.info(">>>>>>>>>>>>加油交易数据转换<<<<<<<<<<<<");
			List<ICCardTradeInfoBean> list = (List<ICCardTradeInfoBean>) result.getData();
			List<OilCardTradeRecordVo> cardTradeList = null;
			if(list != null && list.size()>0){
				cardTradeList = new ArrayList<OilCardTradeRecordVo>();
				for(ICCardTradeInfoBean bean:list){
					OilCardTradeRecordVo cardTrade = new OilCardTradeRecordVo();
					cardTrade.setTradeId(bean.getId());
					cardTrade.setTradeMoney(bean.getTradeMoney()+"");
					//if(StringUtils.isNotBlank(bean.getTradeState())){
					cardTrade.setTradeState("1");
					//}
					cardTrade.setTradeType(type);
					cardTrade.setCardNum(bean.getCardNo());
					cardTrade.setVehicleNum(bean.getVehicleNo());
					cardTrade.setTradeTime(bean.getTradeTime()+"");
					cardTrade.setResultMsg("");
					cardTradeList.add(cardTrade);
				}
			}
			logger.info(">>>>>>>>>>>>加油交易数据转换完成<<<<<<<<<<<<");
			response.setList(cardTradeList);
			
		}
	}
	 
	 /**
	  * type 1当月 2上月 3近三月
	  * @param type
	  * @return  
	  * @Description:
	  */
	 public long[] getDateSet(long type){
			
			long[] times = new long [2];
			Calendar calendar = Calendar.getInstance();
			System.out.println("now time >>>>>>>>>>>>>"+calendar.getTimeInMillis());
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 999);
			System.out.println("year:"+calendar.get(Calendar.YEAR)+"month:"+(calendar.get(Calendar.MONTH)+1)+",day:"+calendar.get(Calendar.DAY_OF_MONTH)+",hour:"+calendar.get(Calendar.HOUR)+",minute:"+calendar.get(Calendar.MINUTE)+",millisecond:"+calendar.get(Calendar.SECOND));
			if(type==1){
				times[0] = calendar.getTimeInMillis();
				calendar.add(Calendar.MONTH, 1);
				calendar.add(Calendar.SECOND, -1);
				times[1] = calendar.getTimeInMillis();
				System.out.println("year:"+calendar.get(Calendar.YEAR)+"month:"+(calendar.get(Calendar.MONTH)+1)+",day:"+calendar.get(Calendar.DAY_OF_MONTH)+",hour:"+calendar.get(Calendar.HOUR)+",minute:"+calendar.get(Calendar.MINUTE)+",millisecond:"+calendar.get(Calendar.SECOND));
			}else if(type ==2 ){
				times[1] = calendar.getTimeInMillis()-1;
				calendar.add(Calendar.MONTH, -1);
				times[0] = calendar.getTimeInMillis();
				System.out.println("year:"+calendar.get(Calendar.YEAR)+"month:"+(calendar.get(Calendar.MONTH)+1)+",day:"+calendar.get(Calendar.DAY_OF_MONTH)+",hour:"+calendar.get(Calendar.HOUR)+",minute:"+calendar.get(Calendar.MINUTE)+",millisecond:"+calendar.get(Calendar.SECOND));
			}else if(type == 3){
				
				times[1] = calendar.getTimeInMillis()-1;
				calendar.add(Calendar.MONTH, -3);
				times[0] = calendar.getTimeInMillis();
				System.out.println("year:"+calendar.get(Calendar.YEAR)+"month:"+(calendar.get(Calendar.MONTH)+1)+",day:"+calendar.get(Calendar.DAY_OF_MONTH)+",hour:"+calendar.get(Calendar.HOUR)+",minute:"+calendar.get(Calendar.MINUTE)+",millisecond:"+calendar.get(Calendar.SECOND));
			}
			return times;
		}
	@Override
	public void validate(Parameter request) throws ClientException {
		
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		QueryTradeRecordListReq req = (QueryTradeRecordListReq) request.getBody();
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E120002",UserError.E120002);
		}
		if(StringUtils.isBlank(req.getTimeType())){
			throw new ClientException("E230005",TradeInfoError.E230005);
		}
		if(req.getPage() == 0){
			req.setPage(PageValue.page);
		}
		if(req.getPageSize() ==0){
			req.setPageSize(PageValue.pageSize);
		}
		
	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", QueryTradeRecordListReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}
}
