package com.ctfo.sinoiov.mobile.webapi.service.yppt;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctfo.common.models.PaginationResult;
import com.ctfo.sinoiov.mobile.webapi.base.intf.ICodeService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam.PageValue;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryOneOilCardTradeReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.QueryOneOilCardTradeRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.OneOilCardTradeVo;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.ICCardError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.yppt.bean.ICCardRechargeTradeBean;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：单张油卡交易记录
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
@Service("queryOneOilCardTradeListManagerImpl")
public class QueryOneOilCardTradeListManagerImpl implements IJsonService{
	
	//日志
	protected static final Log logger = LogFactory.getLog(QueryPayeeListManagerImpl.class);//日志
	@Autowired
	private ICodeService codeService;

	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		logger.info(">>>>>>>>>>>>>>>>>查询单张油卡交易记录<<<<<<<<<<<<<<<<<<<<<");
		QueryOneOilCardTradeRsp response  = new QueryOneOilCardTradeRsp();
		
		QueryOneOilCardTradeReq req = (QueryOneOilCardTradeReq) request.getBody();
		//日志
		logger.debug("手机APP客户端调用【查询一张油卡交易记录列表（Y300005 ）】接口，用户ID：" + req.getUserId());
		
		try {
			 Map<String,Object> map = new HashMap<String,Object>();
			 map.put("userId", req.getUserId());
			 map.put("cardId", req.getCardId());
			 map.put("page", req.getPage());
			 map.put("pageSize", req.getPageSize());
			 logger.info(">>>>>>>>>>>>>>>>>调用后台查询单张油卡交易记录，开始时间["+new Date()+"]<<<<<<<<<<<<<<<<<<<<<");
			 PaginationResult<ICCardRechargeTradeBean> result = (PaginationResult<ICCardRechargeTradeBean>) ImplementationSupport.getICRechargeApplyManager().paginateRechargeAndTradeInfoForAPP(map);
			 logger.info(">>>>>>>>>>>>>>>>>调用后台查询单张油卡交易记录，结束时间["+new Date()+"]<<<<<<<<<<<<<<<<<<<<<");
			 toObject(result,response);
			 
		} catch (Exception e) {
             logger.error("查询单张油卡交易记录失败",e);		
             throw new ClientException("查询单张油卡交易失败", e);
		}


		return response;
	}

	private  void toObject(PaginationResult<?> result ,QueryOneOilCardTradeRsp response ){
		if(result == null || result.getData() == null){
			 logger.info(">>>>>>>>>>>>>>>>>数据为空<<<<<<<<<<<<<<<<<<<<<");
			return;
		}
		 //转换tradeType 字典
		// Map<String,String> tradeType = codeService.queryCodeAndNameMapByTypeCode("IC-TRADE-TYPE");
		logger.info(">>>>>>>>>>>>>>>>>数据转换<<<<<<<<<<<<<<<<<<<<<");
		List<OneOilCardTradeVo> list = new ArrayList<OneOilCardTradeVo>();
		for(Object obj : result.getData()){
			ICCardRechargeTradeBean bean = (ICCardRechargeTradeBean) obj;
			OneOilCardTradeVo cardTrade = new OneOilCardTradeVo();
			cardTrade.setTradeId(bean.getId());
			if(PublicStaticParam.IC_TRADE_STATUS_CZ.equals(bean.getTradeType())){
				String status = "0";	//处理中
				if("03".equals(bean.getState())){
					//失败
					status = "2";		//失败
				}else if("05".equals(bean.getState())){
					//成功
					status = "1";	//成功
				}
				cardTrade.setRechargeState(status);
			}else if(PublicStaticParam.IC_TRADE_STATUS_JY.equals(bean.getTradeType())){
				cardTrade.setRechargeState("1");
			}
			
			cardTrade.setTradeTime(bean.getTime());
			cardTrade.setMoney(PublicStaticParam.fen2Yuan(bean.getMoney()).doubleValue());
			cardTrade.setOilCost(PublicStaticParam.fen2Yuan(bean.getMoney()).toString());
			cardTrade.setRechargeStateMsg(bean.getFaileReason());
			cardTrade.setTradeType(bean.getTradeType());
			list.add(cardTrade);
			
		}
		logger.info(">>>>>>>>>>>>>>>>>数据转换结束<<<<<<<<<<<<<<<<<<<<<");
		response.setList(list);
		response.setTotalNum(result.getTotal());
	}
	
	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		QueryOneOilCardTradeReq req = (QueryOneOilCardTradeReq) request.getBody();
		/*if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E120002",UserError.E120002);
		}*/
		if(StringUtils.isBlank(req.getCardId())){
			throw new ClientException("E140007",ICCardError.E140007);
		}
		if(req.getPage() == 0){
			req.setPage(PageValue.page);
		}
		if(req.getPageSize() == 0){
			req.setPageSize(PageValue.pageSize);
		}
	}
	
	// 将客户端请求的参数转换成json串 
	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", QueryOneOilCardTradeReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}
	
	public ICodeService getCodeService() {
		return codeService;
	}
	
	@Resource(name="codeService", description="码表服务service")
	public void setCodeService(ICodeService codeService) {
		this.codeService = codeService;
	}

}
