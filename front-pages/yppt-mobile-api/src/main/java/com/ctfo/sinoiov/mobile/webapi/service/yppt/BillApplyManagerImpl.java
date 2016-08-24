package com.ctfo.sinoiov.mobile.webapi.service.yppt;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.common.exception.BusinessException;
import com.ctfo.crm.boss.beans.UserInvoiceInfo;
import com.ctfo.csm.uaa.dao.beans.UAAUser;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.BillApplyReq;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.BillRrror;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.PostAddressError;
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
 * 功能：开票申请
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
public class BillApplyManagerImpl implements IJsonService {
	
	protected static final Log logger = LogFactory.getLog(BillApplyManagerImpl.class);

	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		logger.info(">>>>>>>>>>>>>>>>>调用发票申请接口<<<<<<<<<<<<<<<<<<<");
		try {
			BillApplyReq req = (BillApplyReq) request.getBody();
			UserInvoiceInfo userInvoice = ImplementationSupport.getUserInvoiceManager().getInvoiceInfoById(req.getBillId());
			ICCardInvoiceApply icCardApply = new ICCardInvoiceApply();
			icCardApply.setId(req.getId());
			logger.info(">>>>>>>>>>>>>>>>>申请记录ID:["+req.getId()+"]<<<<<<<<<<<<<<<<<<<");
			icCardApply.setUserId(req.getUserId());
			icCardApply.setUserName(req.getUserName());
			icCardApply.setUserType(req.getUserType());
			icCardApply.setUserRegPhone(req.getMobile());
			icCardApply.setReceiverName(req.getReceiverName());
			icCardApply.setReceiverPhoneNum(req.getReceiverPhoneNum());
			icCardApply.setProvince(PublicStaticParam.toProvince(req.getProvince()));
			icCardApply.setCity(PublicStaticParam.toCity(req.getCity()));
			icCardApply.setDistrict(req.getDistrict());
			icCardApply.setAddress(req.getAddress());
			icCardApply.setStatus(PublicStaticParam.BILL_APPLY_STATUS);
			icCardApply.setDataSource(PublicStaticParam.DATA_SOURCE);
			
			icCardApply.setInvoiceMoney(PublicStaticParam.yuan2Fen(new BigDecimal(req.getAmountMoney())));
			if(userInvoice != null){
				//发票信息
				icCardApply.setInvoiceName(userInvoice.getInvoiceName());
				icCardApply.setInvoiceType(userInvoice.getInvoiceType());
				icCardApply.setInvoiceFileUrl(userInvoice.getInvoiceFileUrl());
				icCardApply.setBusinessLicenseUrl(userInvoice.getBusinessLicenseUrl());
				icCardApply.setTaxRegCertificateUrl(userInvoice.getTaxRegCertificateUrl());
				icCardApply.setOrgCodeCertificateUrl(userInvoice.getOrgCodeCertificateUrl());
				icCardApply.setGeneralTaxProveUrl(userInvoice.getGeneralTaxProveUrl());
				
			}
			logger.info(">>>>>>>>>>>>>>>>>调用服务添加发票信息，开始时间["+new Date()+"]<<<<<<<<<<<<<<<<<<<");
			if(StringUtils.isBlank(icCardApply.getId())){
				icCardApply.setCreateTime(System.currentTimeMillis());
				icCardApply.setStatus(PublicStaticParam.BILL_APPLY_STATUS);
					String id = ImplementationSupport.getICCardInvoiceApplyManager().apply(icCardApply);
					logger.info(">>>>>>>>>>>>>>返回的ID ["+id+"]<<<<<<<<<<<<<<<<<<<");
			}else{
				//ImplementationSupport.getICCardInvoiceApplyManager().
			}
			logger.info(">>>>>>>>>>>>>>>>>调用服务添加发票信息，结束时间["+new Date()+"]<<<<<<<<<<<<<<<<<<<");
		} catch (BusinessException e) {
			logger.info("新增发票申请单失败",e);
			throw new ClientException("E200005", BillRrror.E200005);
		}
		return null;
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E200001",BillRrror.E200001);
		}
		BillApplyReq req = (BillApplyReq) request.getBody();
		UAAUser user = null;
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E200002",BillRrror.E200002);
		}else{
			user = ImplementationSupport.getUserManager().queryUaaUserById(req.getUserId(), null);
			if(user == null){
				throw new ClientException("E120011", UserError.E120011);
			}
		}
		if(StringUtils.isBlank(req.getUserName())){
			throw new ClientException("E200008",BillRrror.E200008);
		}
		if(StringUtils.isBlank(req.getMobile())){
			throw new ClientException("E200009",BillRrror.E200009);
		}
		if(StringUtils.isBlank(req.getBillId())){
			throw new ClientException("E200006",BillRrror.E200006);
		}
		if(StringUtils.isBlank(req.getAmountMoney())){
			throw new ClientException("E200007",BillRrror.E200007);
		}
		
		if(StringUtils.isBlank(req.getReceiverName())){
			throw new ClientException("E220003",PostAddressError.E220003);
		}

		if(StringUtils.isBlank(req.getProvince())){
			throw new ClientException("E220005",PostAddressError.E220005);
		}
		if(StringUtils.isBlank(req.getCity())){
			throw new ClientException("E220006",PostAddressError.E220006);
		}
		if(StringUtils.isBlank(req.getDistrict())){
			throw new ClientException("E220007",PostAddressError.E220007);
		}
		if(StringUtils.isBlank(req.getAddress())){
			throw new ClientException("E220008",PostAddressError.E220008);
		}
		if(StringUtils.isBlank(req.getPost())){
			throw new ClientException("E220009",PostAddressError.E220009);
		}
	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", BillApplyReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}

}
