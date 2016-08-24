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
import com.ctfo.csm.boss.beans.SimpleCode;
import com.ctfo.csm.boss.beans.SimpleCodeExampleExtended;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryOilCardTypeReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.QueryCreateCardDeptListRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.CardCreateDpetVo;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.BeanUtil;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.ICCardError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.sinoiov.mobile.webapi.util.Zone_Util;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：开卡地区列表
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
@Service("queryCreateCardDeptManagerImpl")
public class QueryCreateCardDeptManagerImpl implements IJsonService{
	//日志
	protected static final Log logger = LogFactory.getLog(QueryCreateCardDeptManagerImpl.class);
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		
		QueryCreateCardDeptListRsp queryCreateCardDeptRsp = new QueryCreateCardDeptListRsp();
		
		
		//构造查询条件
		SimpleCodeExampleExtended codeExampleExtended = new SimpleCodeExampleExtended();
		
		QueryOilCardTypeReq queryOilCardTypeReq = (QueryOilCardTypeReq) request.getBody();
		logger.debug("手机APP客户端调用【查询开卡地区（Y300008）】接口，用户ID：" + queryOilCardTypeReq.getUserId());
		
		//拼接查询条件
		codeExampleExtended.createCriteria().andTypeCodeEqualTo(queryOilCardTypeReq.getCardType() + "_FKDQ");
		
		//分页条件
		codeExampleExtended.setSkipNum((queryOilCardTypeReq.getPage()-1)*queryOilCardTypeReq.getPageSize());
		codeExampleExtended.setEndNum(queryOilCardTypeReq.getPage()*queryOilCardTypeReq.getPageSize());
		
		//查询数据库中的bean
		try {
			PaginationResult<Object>  paginationResult = null;//cardGenericManager.paginateModels(codeExampleExtended);
			
			List<CardCreateDpetVo> list = new ArrayList<CardCreateDpetVo>();
			
			if(paginationResult !=null && paginationResult.getTotal()>0){
				SimpleCode simpleCode  = null;
				List<Object> simples = (List<Object>) paginationResult.getData();
				 for(int i=0;i<simples.size();i++){
					 CardCreateDpetVo simpleCodeVo = new CardCreateDpetVo();
					 simpleCode = (SimpleCode) simples.get(i);
					 //加注解
					 BeanUtil.copyPropertiesWithTypeCast(simpleCode ,simpleCodeVo);
					 simpleCodeVo.setCode(Zone_Util.converterZoneToAPP(simpleCodeVo.getCode()));
					 
					 list.add(simpleCodeVo);
				 }
				
			}
			queryCreateCardDeptRsp.setList(list);
			queryCreateCardDeptRsp.setTotalNum(paginationResult.getTotal());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			logger.error("查询开卡地区失败:"+e);
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			throw new ClientException("查询开卡地区失败",e);
		}
		
		return queryCreateCardDeptRsp;
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		QueryOilCardTypeReq req = (QueryOilCardTypeReq) request.getBody();
		if(StringUtils.isBlank(req.getCardType())){
			throw new ClientException("E140002",ICCardError.E140002);
		}

	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", QueryOilCardTypeReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}
}
