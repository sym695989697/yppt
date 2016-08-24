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

import com.ctfo.common.models.PaginationResult;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam.PageValue;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.BillApplyListReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.BillApplyBeanRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.BillApplyListBeanRsp;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.BillRrror;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApply;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApplyExampleExtended;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：开票列表
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
 * <tr><td>1.0</td><td>2015-1-30</td><td>JiangXF</td><td>创建</td></tr>
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
public class BillApplyListManagerImpl implements IJsonService {

	protected static final Log logger = LogFactory.getLog(BillApplyListManagerImpl.class);
	
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		logger.info(">>>>>>>>>>>>>>>>>>>>>调用开票申请列表查询<<<<<<<<<<<<<<<<<<<<<<<");
		BillApplyListBeanRsp response = null;
		try{
			BillApplyListReq req = (BillApplyListReq) request.getBody();
			ICCardInvoiceApplyExampleExtended  applyExample = new ICCardInvoiceApplyExampleExtended();
			ICCardInvoiceApplyExampleExtended.Criteria applyCriteria = applyExample.createCriteria();
			logger.info(">>>>>>>>>>>>>>>>>>>>>用户ID["+req.getUserId()+"]<<<<<<<<<<<<<<<<<<<<<<<");
			applyCriteria.andUserIdEqualTo(req.getUserId());
			applyExample.setSkipNum((req.getPage()-1)*req.getPageSize());
			applyExample.setLimitNum(req.getPageSize());
			applyExample.setOrderByClause(ICCardInvoiceApply.fieldCreateTime() + " desc");
			logger.info(">>>>>>>>>>>>>>>>>>>>>用户ID["+req.getUserId()+"]<<<<<<<<<<<<<<<<<<<<<<<");
			logger.info(">>>>>>>>>>>>>>>>>>>>>调用后台调接口，开始时间["+new Date()+"]<<<<<<<<<<<<<<<<<<<<<<<");
			PaginationResult<ICCardInvoiceApply> resutlt = ImplementationSupport.getICCardInvoiceApplyManager().paginate(applyExample);
			logger.info(">>>>>>>>>>>>>>>>>>>>>调用后台调接口，结束时间["+new Date()+"]<<<<<<<<<<<<<<<<<<<<<<<");
			response = toObject(resutlt);
		}catch(Exception e){
			logger.error("查询开票申请列表失败",e);
			throw new ClientException("查询开票申请列表失败",e);
		}
		return response;
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E200001",BillRrror.E200001);
		}
		BillApplyListReq req = (BillApplyListReq) request.getBody();
		
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E200002",BillRrror.E200002);
		}
		/*if(StringUtils.isBlank(req.getId())){
			throw new ClientException("E200006",BillRrror.E200006);
		}*/
		if(req.getPage()==0){
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
	        classMap.put("body", BillApplyListReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}
	
	private BillApplyListBeanRsp toObject(PaginationResult<ICCardInvoiceApply> result){
		
		BillApplyListBeanRsp response = null;
		if(result != null && result.getData() != null){
			logger.info(">>>>>>>>>>>>>>>>>>>>>对象转换开始<<<<<<<<<<<<<<<<<<<<<<<");
			response = new BillApplyListBeanRsp();
			response.setTotalNum(result.getTotal());
			List<BillApplyBeanRsp> list = new ArrayList<BillApplyBeanRsp>();
			for(ICCardInvoiceApply apply:result.getData()){
				BillApplyBeanRsp rs = new BillApplyBeanRsp();
				rs.setId(apply.getId());
				if(apply.getInvoiceMoney() != null)
					rs.setAmountMoney(PublicStaticParam.fen2Yuan(apply.getInvoiceMoney()).toString());
				rs.setAddress(apply.getAddress());
				rs.setBillTitle(apply.getInvoiceName());
				rs.setBillType(apply.getInvoiceType());
				rs.setCity(apply.getCity());
				rs.setCounty(apply.getDistrict());
				rs.setProvince(apply.getProvince());
				rs.setCreateTime(apply.getCreateTime()+"");
				rs.setMark(apply.getMark());
				String status = "0";	//审核中
				if("4".equals(apply.getStatus())){
					status = "1";	//成功
				}else if("3".equals(apply.getStatus())||"5".equals(apply.getStatus())){
					status = "2";	//失败
				}
				rs.setStatus(status);
				list.add(rs);
			}
			response.setList(list);
			logger.info(">>>>>>>>>>>>>>>>>>>>>对象转换开始<<<<<<<<<<<<<<<<<<<<<<<");
		}else{
			logger.info(">>>>>>>>>>>>>>>>>>>>>对象为空<<<<<<<<<<<<<<<<<<<<<<<");
		}
		return response;
	}

}
