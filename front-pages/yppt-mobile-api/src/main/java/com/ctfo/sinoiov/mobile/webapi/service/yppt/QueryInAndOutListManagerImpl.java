package com.ctfo.sinoiov.mobile.webapi.service.yppt;


import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.chpt.boss.beans.AccountIO;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam.PageValue;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryInAndOutListReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.QueryInAndOutListRsp;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：帐户交易记录
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
@Service("queryInAndOutListManagerImpl")
public class QueryInAndOutListManagerImpl implements IJsonService{
	
	//日志提示
	protected static final Log logger = LogFactory.getLog(QueryInAndOutListManagerImpl.class);
	private int page ;
	private int pageSize;
	private static final String CARD_APPLY_STATE_OK = "1";  //充值成功
	private static final String CARD_APPLY_STATE_FAILIED = "2";//充值失败
	private static final String CARD_APPLY_STATE_ING = "0";//处理中
	
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		
		QueryInAndOutListRsp response = new QueryInAndOutListRsp();//返回给页面的对象
		
		
		QueryInAndOutListReq req = (QueryInAndOutListReq) request.getBody();
		
		PaginationResult<AccountIO> result = new PaginationResult<AccountIO>();
		try{
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			logger.error("查询收入支出明细失败："+e);
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			throw new ClientException("查询收入支出明细失败",e);
		}
			
		return response;
		
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		QueryInAndOutListReq req = (QueryInAndOutListReq) request.getBody();
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E120002",UserError.E120002);
		}
		if(StringUtils.isBlank(req.getId())){
			throw new ClientException("E120002",UserError.E120002);
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
	        classMap.put("body", QueryInAndOutListReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}

}
