package com.ctfo.sinoiov.mobile.webapi.service.yppt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.csm.uaa.dao.beans.UAAUser;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.BillApplyByIdReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.BillApplyBeanRsp;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.BillRrror;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApply;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：开票申请详情
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
public class BillApplyInfoManagerImpl implements IJsonService {
	
	protected static final Log logger = LogFactory.getLog(BillApplyInfoManagerImpl.class);

	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		logger.info(">>>>>>>>>>>>>>>>>调用开票申请情详接口<<<<<<<<<<<<<<<<<<");
		try{
			BillApplyByIdReq req = (BillApplyByIdReq) request.getBody();
			logger.info(">>>>>>>>>>>>>>>>>传参ID：["+req.getId()+"]<<<<<<<<<<<<<<<<<<");
			ICCardInvoiceApply applyId = new ICCardInvoiceApply();
			applyId.setId(req.getId());
			logger.info(">>>>>>>>>>>>>>>>>调用开票服务时间，开始时间["+new Date()+"]<<<<<<<<<<<<<<<<<<");
			ICCardInvoiceApply result = ImplementationSupport.getICCardInvoiceApplyManager().getById(applyId);
			logger.info(">>>>>>>>>>>>>>>>>完成调用开票服务时间，结束时间["+new Date()+"]<<<<<<<<<<<<<<<<<<");
			return toObject(result);
		}catch(Exception e){
			logger.error("调用删除开票申请失败",e);
			throw new ClientException(BillRrror.E200005, e);
		}
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E200001",BillRrror.E200001);
		}
		BillApplyByIdReq req = (BillApplyByIdReq) request.getBody();
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
	        classMap.put("body", BillApplyByIdReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}
	
	private BillApplyBeanRsp toObject(ICCardInvoiceApply apply){
		BillApplyBeanRsp result = null;
		if(apply != null){
			result = new BillApplyBeanRsp();
			result.setId(apply.getId());
			result.setProvince(apply.getProvince());
			result.setCity(apply.getCity());
			result.setCounty(apply.getDistrict());
			result.setAddress(apply.getAddress());
			result.setCreateTime(apply.getCreateTime()+"");
			result.setMark(apply.getMark());
		}
		return result;
	}

}
