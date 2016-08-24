package com.ctfo.sinoiov.mobile.webapi.service.yppt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.beans.SimpleCode;
import com.ctfo.crm.boss.beans.UserInvoiceInfo;
import com.ctfo.csm.uaa.dao.beans.UAAUser;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IMobileAppMongoService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.BillAddReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.BillInfoBeanRsp;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.CommonUtil;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.BillRrror;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.SimpleCodeError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：发票添加
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
@Service("billAddManagerImpl")
public class BillAddManagerImpl implements IJsonService {

	//打日志信息
	protected static final Log logger = LogFactory.getLog(BillAddManagerImpl.class);
	
	@Autowired(required=false)
	private IMobileAppMongoService mobileAppMongoService;
	
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		logger.info(">>>>>>>>>>>>>>>调用发票添加接口<<<<<<<<<<<<<<<<");
		BillInfoBeanRsp response = new BillInfoBeanRsp();
		BillAddReq req = (BillAddReq) request.getBody();
		try{
			logger.info(">>>>>>>>>>>>>>>上传图片，并生成文件名<<<<<<<<<<<<<<<<");
			
			String[] fileNames = mobileAppMongoService.saveFiles(obj);		//新生成的文件名
			logger.info(">>>>>>>>>>>>>>>上传成功<<<<<<<<<<<<<<<<");
			Map<String,String[]> map = CommonUtil.getFiledValue(req.getImageClass(), fileNames);
			logger.info(">>>>>>>>>>>>>>>对像转换开始<<<<<<<<<<<<<<<<");
			UserInvoiceInfo invoiceInfo = new UserInvoiceInfo();
			invoiceInfo.setId(req.getId());
			logger.info(">>>>>>>>>>>>>>>记录ID[ "+req.getId()+" ]<<<<<<<<<<<<<<<<");
			invoiceInfo.setInvoiceName(req.getBillTitle());
			invoiceInfo.setInvoiceType(req.getBillType());
			invoiceInfo.setGeneralTaxProveUrl(CommonUtil.getPicValueToString(map,"taxPayerCertificates"));	//一般纳税人证明
			invoiceInfo.setOrgCodeCertificateUrl(CommonUtil.getPicValueToString(map,"orgCode"));	//组织机构代码证
			invoiceInfo.setTaxRegCertificateUrl(CommonUtil.getPicValueToString(map,"taxCertificates"));	//税务登记证
			invoiceInfo.setBusinessLicenseUrl(CommonUtil.getPicValueToString(map,"businessLicense"));	//营业执照
			invoiceInfo.setInvoiceFileUrl(CommonUtil.getPicValueToString(map,"billInfo"));	//开票信息
			invoiceInfo.setCreateTime(System.currentTimeMillis());
			invoiceInfo.setCreator(req.getUserId());
			invoiceInfo.setUserId(req.getUserId());
			logger.info(">>>>>>>>>>>>>>>对像转换结束<<<<<<<<<<<<<<<<");
			if(StringUtils.isBlank(invoiceInfo.getId())){
				logger.info(">>>>>>>>>>>>>>>调用添加发票信息,开始时间["+new Date()+"]<<<<<<<<<<<<<<<<");
				String id = ImplementationSupport.getUserInvoiceManager().addInvoiceInfo(invoiceInfo);
				logger.info(">>>>>>>>>>>>>>>调用添加发票信息,结束时间["+new Date()+"]<<<<<<<<<<<<<返回ID为："+id);
				if(StringUtils.isBlank(id)){
					throw new ClientException("添加发票信息失败");
				}
				response.setId(id);
				response.setBillTitle(req.getBillTitle());
				response.setBillInfo(req.getBillInfo());
			}else{
				logger.info(">>>>>>>>>>>>>>>修改发票信息,开始时间["+new Date()+"]<<<<<<<<<<<<<<<<");
				ImplementationSupport.getUserInvoiceManager().modifyInvoiceInfo(invoiceInfo);
				logger.info(">>>>>>>>>>>>>>>调用添加发票信息,结束时间["+new Date()+"]<<<<<<<<<<<<<<<<");
			}
		}catch(Exception e){
			logger.error("添加发票或修改发票信息失败", e);
			throw new ClientException(BillRrror.E200005, e);
		}
		return response;
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E200001",BillRrror.E200001);
		}
		BillAddReq req = (BillAddReq) request.getBody();
		UAAUser user = null;
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E200002",BillRrror.E200002);
		}else{
			logger.info(">>>>>>>>>>>>>>>验证用户是否存在，ID["+req.getUserId()+"]开始时间["+new Date()+"]<<<<<<<<<<<<<<<<<<<");
			user = ImplementationSupport.getUserManager().queryUaaUserById(req.getUserId(), null);
			logger.info(">>>>>>>>>>>>>>>结束时间["+new Date()+"]<<<<<<<<<<<<<<<<<<<");
			if(user == null){
				logger.info(">>>>>>>>>>>>>>>没有查到相关的用过<<<<<<<<<<<<<<<<<<<");
				throw new ClientException("E120011", UserError.E120011);
			}else{
				logger.info(">>>>>>>>>>>>>>>查到相关的用户<<<<<<<<<<<<<<<<<<<");
			}
		}
		if(StringUtils.isBlank(req.getBillType())){
			throw new ClientException("E200003",BillRrror.E200003);
		}else{
			try {
				SimpleCode code = ImplementationSupport.getSimpleCodeManager().getSimpleCodeByTypeAndCode(PublicStaticParam.BILL_TYPE, req.getBillType());
				if(code == null){
					throw new ClientException("E210004", SimpleCodeError.E210004);
				}
			} catch (Exception e) {
				throw new ClientException("E210005", SimpleCodeError.E210005);
			}
		}
		if(StringUtils.isBlank(req.getBillTitle())){
			throw new ClientException("E200004",BillRrror.E200004);
		}
		

	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", BillAddReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}

}
