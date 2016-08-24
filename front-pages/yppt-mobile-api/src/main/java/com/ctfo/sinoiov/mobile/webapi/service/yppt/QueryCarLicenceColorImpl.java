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
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.QueryCarLicenceColorRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.CarLicenceColorVo;
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
 * 功能：车牌颜色列表
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
@Service("queryCarLicenceColorImpl")
public class QueryCarLicenceColorImpl implements IJsonService{

	//日志信息
	protected static final Log logger = LogFactory.getLog(QueryCarLicenceColorImpl.class);
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		QueryCarLicenceColorRsp queryCarLicenceColorRsp = new QueryCarLicenceColorRsp();
		
		//后台服务
		//IICCardGenericManager cardGenericManager = (IICCardGenericManager) ServiceFactory.getFactory().getService(IICCardGenericManager.class);

		try {
			//构造查询前提
			SimpleCodeExampleExtended codeExampleExtended  = new SimpleCodeExampleExtended();
			
			QueryOilCardTypeReq cardTypeReq = (QueryOilCardTypeReq) request.getBody();
			
			//TODO 增加油卡类型码表标示
			//TODO 等码表功能实现再进行调整
			codeExampleExtended.createCriteria().andTypeCodeEqualTo("4000");
			//查询到数据库中的bean集合
			PaginationResult<Object> paginationResult = null;//cardGenericManager.paginateModels(codeExampleExtended);
			//用来存放转换的bean
			List<CarLicenceColorVo> list = new ArrayList<CarLicenceColorVo>();
			int total = 0;
			if(paginationResult != null && paginationResult.getTotal()>0){
				SimpleCode simple = null;
				List<Object> simples = (List<Object>) paginationResult.getData();
			    if(!simples.isEmpty()){
			    	for(Object s : simples){
			    		simple = (SimpleCode)s;
			    		CarLicenceColorVo vo = new CarLicenceColorVo();
			    		vo.setColorId(simple.getCode());
			    		vo.setColorName(simple.getName());
			    		list.add(vo);
			    	} 
			    }
			}
			queryCarLicenceColorRsp.setList(list);
			
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			logger.error("查询油卡类型失败：", e);
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		
		return queryCarLicenceColorRsp;
	}
	
	@Override
	public void validate(Parameter request) throws ClientException {
		
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		QueryOilCardTypeReq req = (QueryOilCardTypeReq) request.getBody();
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E120002",UserError.E120002);
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
