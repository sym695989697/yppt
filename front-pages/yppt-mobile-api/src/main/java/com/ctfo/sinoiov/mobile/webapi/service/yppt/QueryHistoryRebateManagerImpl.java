package com.ctfo.sinoiov.mobile.webapi.service.yppt;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.common.models.PaginationResult;
import com.ctfo.gatewayservice.interfaces.bean.credit.CreditIO;
import com.ctfo.gatewayservice.interfaces.service.ICreditService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryHistoryRebateListReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.QueryHistoryRebateListRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.RebateVo;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.yppt.baseservice.system.beans.UserStat;
import com.ctfo.yppt.baseservice.system.intf.IUserStatManager;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：历史返利
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
@Service("queryHistoryRebateManagerImpl")
public class QueryHistoryRebateManagerImpl implements IJsonService{
	//日志信息
	protected static final Log logger = LogFactory.getLog(QueryHistoryRebateManagerImpl.class);
	
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		
		QueryHistoryRebateListRsp queryRebateListRsp = new QueryHistoryRebateListRsp();
		
		//构造查询条件
		QueryHistoryRebateListReq rebateListReq = (QueryHistoryRebateListReq) request.getBody();
		Map<String,Object> map = new HashMap<String,Object>();
		
		//历史返利记录接口，应只显示收入的旺金币，不应显示支出的旺金币
		map.put("model", "1"); // "收支类型(以账户的角度：1收入，2支出)"   
		map.put("userId", rebateListReq.getUserId());
		map.put("page", rebateListReq.getPage());
		map.put("pageSize", rebateListReq.getPageSize());
		
		try {
			
			ICreditService creditService = null;
			try {
				creditService = ImplementationSupport.getICreditService();
			} catch (Exception ex) {
				logger.error("服务内部错误，获取返利接口（ICreditService）服务失败！！！");
				ex.printStackTrace();
			}
			if(creditService == null){
				queryRebateListRsp.setResult("1");
				queryRebateListRsp.setMsg("服务内部错误，获取充值申请服务接口失败！！！！");
				return queryRebateListRsp;
			}
			
			//*****************获取历史返利**************
			PaginationResult<CreditIO> results = creditService.queryCreditIO(map);;
			List<RebateVo> list = new ArrayList<RebateVo>();
			if(results != null && results.getData() != null && !results.getData().isEmpty()){
				List<CreditIO> historyRebates = (List<CreditIO>) results.getData();
				for (CreditIO credit : historyRebates) {
					if(credit == null) break;
					RebateVo rebateVo = new RebateVo();
					rebateVo.setRebateTime(String.valueOf(credit.getOperateTime()));
					if(credit.getCreditNum()!=null){
						rebateVo.setCionCount(credit.getCreditNum());
					}
					list.add(rebateVo);
				}
			}
			queryRebateListRsp.setList(list);
			//*****************获取历史返利（end）**************
			
			//**************************获取累计旺金币数量
			//获取用户统计相关信息
			IUserStatManager  iUserStatManager = null;
			try {
				iUserStatManager = ImplementationSupport.getUserStatManager();
			} catch (Exception e) {
				logger.error("****************获取用户统计服务接口（iUserStatManager）失败！！！");
				e.printStackTrace();
			}
			if(iUserStatManager == null){
				queryRebateListRsp.setResult("1");
				queryRebateListRsp.setMsg("服务内部错误，获取用户统计服务接口失败！！！！");
				return queryRebateListRsp;
			}
			
			UserStat stat = iUserStatManager.getUserstatByUserId(rebateListReq.getUserId());
			//获取旺金币总数
			if(stat!=null && stat.getGoldCoinNum()!=null){
				queryRebateListRsp.setTotalCoinCount(Integer.valueOf(String.valueOf(stat.getGoldCoinNum())));
			}
			//**************************获取累计旺金币数量(end)
			
			queryRebateListRsp.setTotalNum(results.getData().size());
			queryRebateListRsp.setResult("0");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("****************查询历史返利失败："+e);
			queryRebateListRsp.setResult("1");
			queryRebateListRsp.setMsg("服务内部错误！！！！");
			throw new ClientException("服务内部错误,通过UserId查询历史返利失败 userId="+rebateListReq.getUserId(),e);
		}
		return queryRebateListRsp;
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		QueryHistoryRebateListReq req = (QueryHistoryRebateListReq) request.getBody();
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E120002",UserError.E120002);
		}
	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", QueryHistoryRebateListReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}
	
}


