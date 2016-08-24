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

import com.ctfo.crm.boss.beans.UserInvoiceInfo;
import com.ctfo.crm.boss.beans.UserInvoiceInfoExampleExtended;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.BillListReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.BillInfoBeanRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.BillListBeanRsp;
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
 * 功能：发票管理列表
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
@Service("billListManagerImpl")
public class BillListManagerImpl implements IJsonService {

	protected static final Log logger = LogFactory.getLog(BillListManagerImpl.class);
	
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		BillListBeanRsp response = null;
		try{
			logger.error(">>>>>>>>>>>>>>>调用查询发票列表接口<<<<<<<<<<<<<<<");
			BillListReq req = (BillListReq) request.getBody();
			UserInvoiceInfoExampleExtended invoiceInfoExample = new UserInvoiceInfoExampleExtended();
			invoiceInfoExample.createCriteria().andUserIdEqualTo(req.getUserId());
			logger.error(">>>>>>>>>>>>>>>用户ID["+req.getUserId()+"],开始时间["+new Date()+"]<<<<<<<<<<<<<<<");
			List<UserInvoiceInfo> list = ImplementationSupport.getUserInvoiceManager().queryInvoiceInfo(invoiceInfoExample);
			logger.error(">>>>>>>>>>>>>>>用户ID["+req.getUserId()+"],结束时间["+new Date()+"]<<<<<<<<<<<<<<<");
			if(list != null && list.size()>0){
				logger.error(">>>>>>>>>>>>>>>查询列表记录数["+list.size()+"]条<<<<<<<<<<<<<<<");
				response =  toObject(list);
			}else{
				logger.error(">>>>>>>>>>>>>>>查询列表为空<<<<<<<<<<<<<<<");
			}
		}catch(Exception e){
			logger.error("查询发票列表失败", e);
			throw new ClientException("E200001",BillRrror.E200001);
		}
		return response;
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E200001",BillRrror.E200001);
		}
		BillListReq req = (BillListReq) request.getBody();
		
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E200002",BillRrror.E200002);
		}
		/*if(StringUtils.isBlank(req.getId())){
			throw new ClientException("E200006",BillRrror.E200006);
		}*/

	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", BillListReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}
	
	private BillListBeanRsp toObject(List<UserInvoiceInfo> list){
		if(list == null)
			return null;
		BillListBeanRsp result = new BillListBeanRsp();
		List<BillInfoBeanRsp>  billList = new ArrayList<BillInfoBeanRsp>();
		for(UserInvoiceInfo invoiceInfo:list){
			BillInfoBeanRsp rsp = new BillInfoBeanRsp();
			rsp.setId(invoiceInfo.getId());
			rsp.setBillTitle(invoiceInfo.getInvoiceName());
			rsp.setBillType(invoiceInfo.getInvoiceType());
			rsp.setBusinessLicense(invoiceInfo.getBusinessLicenseUrl());
			rsp.setOrgCode(invoiceInfo.getOrgCodeCertificateUrl());
			rsp.setTaxPayerCertificates(invoiceInfo.getGeneralTaxProveUrl());
			rsp.setTaxCertificates(invoiceInfo.getTaxRegCertificateUrl());
			rsp.setBillInfo(invoiceInfo.getInvoiceFileUrl());
			billList.add(rsp);
		}
		result.setList(billList);
		result.setTotalNum(list.size());
		return result;
	}

}
