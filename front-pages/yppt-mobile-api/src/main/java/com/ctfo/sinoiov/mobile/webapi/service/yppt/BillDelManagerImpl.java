package com.ctfo.sinoiov.mobile.webapi.service.yppt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.BillByIdReq;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.BillRrror;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：删除发票
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
 * <tr><td>1.0</td><td>2015-1-25</td><td>JiangXF</td><td>创建</td></tr>
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
@Service
public class BillDelManagerImpl implements IJsonService {
	
	protected static final Log logger = LogFactory.getLog(BillDelManagerImpl.class);

	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		logger.info(">>>>>>>>>>>>>>>调用发票删除接口<<<<<<<<<<<<<<<<");
		try{
			BillByIdReq req = (BillByIdReq) request.getBody();
			logger.info(">>>>>>>>>>>>>>>删除ID为["+req.getId()+"]<<<<<<<<<<<<<<<<");
			logger.info(">>>>>>>>>>>>>>>调用删除接口，开始时间["+new Date()+"]<<<<<<<<<<<<<<<<");
			ImplementationSupport.getUserInvoiceManager().deleteInvoiceInfo(req.getId());
			logger.info(">>>>>>>>>>>>>>>调用删除接口，结束时间["+new Date()+"]<<<<<<<<<<<<<<<<");
		}catch(Exception e){
			logger.error("删除发票信息失败", e);
			throw new ClientException(BillRrror.E200005, e);
		}
		return null;
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E200001",BillRrror.E200001);
		}
		BillByIdReq req = (BillByIdReq) request.getBody();
		/*if(StringUtils.isNotBlank(req.getTokenId())){
			if(StringUtils.isBlank(req.getUserId())){
				throw new ClientException("E120002",UserError.E120002);
			}
		}*/
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E200002",BillRrror.E200002);
		}
		if(StringUtils.isBlank(req.getId())){
			throw new ClientException("E200006",BillRrror.E200006);
		}
		

	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", BillByIdReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}

}
