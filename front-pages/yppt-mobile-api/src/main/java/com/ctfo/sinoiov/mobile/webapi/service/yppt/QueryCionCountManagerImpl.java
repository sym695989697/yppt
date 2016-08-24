package com.ctfo.sinoiov.mobile.webapi.service.yppt;


import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.gatewayservice.interfaces.service.ICreditService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryCionCountReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.QueryCionCountRsp;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.yppt.baseservice.system.intf.IUserStatManager;
import com.ctfo.yppt.bean.UserStatBean;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：旺金币统计
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
@Service("queryCionCountManagerImpl")
public class QueryCionCountManagerImpl implements IJsonService {

//基本通过
	
	
	//日志
	protected static final Log logger = LogFactory.getLog(QueryCionCountManagerImpl.class);
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		QueryCionCountReq cionCountReq = (QueryCionCountReq) request.getBody();  //构造查询条件的前提
		QueryCionCountRsp queryCionCountRsp = new QueryCionCountRsp();
		try {
			//查询旺金币信息，查询返利信息表
		    IUserStatManager userStatManager = ImplementationSupport.getUserStatManager();
		    ICreditService creditService = ImplementationSupport.getICreditService();
		    
		    UserStatBean usb=userStatManager.getUserStat(cionCountReq.getUserId());
		    String lastMonthRebateCoinNum=null;
		    Long goldCoinNum= 0L ;
			try {
				goldCoinNum = creditService.queryBalance(cionCountReq.getUserId());
			} catch (Exception e) {
				logger.info("查询用户积分异常", e);
			}
		    
		    goldCoinNum = goldCoinNum == null ? 0:goldCoinNum;
		    if(usb!=null){
		        lastMonthRebateCoinNum=usb.getLastMonthRebateCoinNum()==null?"0":usb.getLastMonthRebateCoinNum().toString();
		    }else{
		        lastMonthRebateCoinNum="0";
		    }
			queryCionCountRsp.setPrecedingMonthCionCount(lastMonthRebateCoinNum);
			queryCionCountRsp.setTotalCionCount(goldCoinNum.toString());
			queryCionCountRsp.setRate(PublicStaticParam.CALCULATE_VALUE);
		} catch (Exception e) { 
			logger.error(">>>>>>>>>>>>>>>>>>>>");
			logger.error("查询旺金币失败："+e);
			logger.error(">>>>>>>>>>>>>>>>>>>>");
			throw new ClientException("查询旺金币失败,userId="+cionCountReq.getMemberId(),e);
		}
		return queryCionCountRsp;
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		QueryCionCountReq req = (QueryCionCountReq) request.getBody();
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E120002",UserError.E120002);
		}

	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", QueryCionCountReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}

}

