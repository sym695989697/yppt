package com.ctfo.sinoiov.mobile.webapi.service.yppt;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.beans.SimpleCode;
import com.ctfo.base.service.beans.SimpleCodeExampleExtended;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryOilCardTypeReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.QueryOilCardTypeRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.CardTypeVo;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.PropertyImageUtils;
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
 * 功能：油卡类型管理
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
@Service("queryOilCardTypeManagerImpl")
public class QueryOilCardTypeManagerImpl implements IJsonService{

	//日志信息
	protected static final Log logger = LogFactory.getLog(QueryOilCardTypeManagerImpl.class);
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		QueryOilCardTypeRsp queryOilCardTypeRsp = new QueryOilCardTypeRsp();

		try {
			//构造查询前提
			SimpleCodeExampleExtended codeExampleExtended  = new SimpleCodeExampleExtended();
			
			QueryOilCardTypeReq cardTypeReq = (QueryOilCardTypeReq) request.getBody();
			logger.error(">>>>>>>>>>>>>>>>>调用后台服务开始，开始时间["+new Date()+"]<<<<<<<<<<<<<<<<");
			//TODO 增加油卡类型码表标示
			//TODO 等码表功能实现再进行调整
			codeExampleExtended.createCriteria().andTypeCodeEqualTo(PublicStaticParam.IC_CARD_TYPE);
			//查询到数据库中的bean集合
			PaginationResult<SimpleCode> paginationResult =ImplementationSupport.getSimpleCodeManager().paginate(codeExampleExtended);
			logger.error(">>>>>>>>>>>>>>>>>调用后台服务开始，开始时间["+new Date()+"]<<<<<<<<<<<<<<<<");
			//用来存放转换的bean
			List<CardTypeVo> list = new ArrayList<CardTypeVo>();
			if(paginationResult != null && paginationResult.getTotal()>0){
				SimpleCode simple = null;
				List<SimpleCode> simples = paginationResult.getData();
				for(int i=0 ;i<simples.size();i++){
					simple = (SimpleCode) simples.get(i);
					
					CardTypeVo cardCode = new CardTypeVo();
					if(PublicStaticParam.IC_CARD_TYPE_2.equals(simple.getCode())){
						cardCode.setLogo(PropertyImageUtils.getUrlByKey("IMAGE_OIL_CARD_1"));
					}else if(PublicStaticParam.IC_CARD_TYPE_1.equals(simple.getCode())){
						cardCode.setLogo(PropertyImageUtils.getUrlByKey("IMAGE_OIL_CARD_2"));
					}
					cardCode.setCardName(simple.getName());
					cardCode.setCardType(simple.getCode());
					cardCode.setCardDesc(simple.getDescription());
					list.add(cardCode);
				}
				queryOilCardTypeRsp.setList(list);
				queryOilCardTypeRsp.setTatolNum(paginationResult.getTotal());
			}
			
			
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			logger.error("查询油卡类型失败：", e);
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			throw new ClientException("E120013", UserError.E120013);
		}
		
		return queryOilCardTypeRsp;
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
