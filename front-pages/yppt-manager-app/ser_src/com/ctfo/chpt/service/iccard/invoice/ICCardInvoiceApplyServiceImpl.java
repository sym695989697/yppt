package com.ctfo.chpt.service.iccard.invoice;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.ServiceImpl;
import com.ctfo.base.utils.DateTimeUtils;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.file.boss.IFileService;
import com.ctfo.gatewayservice.interfaces.service.IUserService;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApply;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApplyExampleExtended;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApplyExampleExtended.Criteria;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApplyProcessLog;
import com.ctfo.yppt.baseservice.invoice.intf.IICCardInvoiceApplyManager;

/**
 * 
 * 项目名称：yppt-manager-app<br>
 * *********************************<br>
 * <P>类名称：ICCardInvoiceApplyServiceImpl</P>
 * *********************************<br>
 * <P>类描述：发票管理功能App-Service接口实现</P>
 * 创建人：李飞<br>
 * 创建时间：2015年1月21日 下午1:27:25<br>
 * 修改人：李飞<br>
 * 修改时间：2015年1月21日 下午1:27:25<br>
 * 修改备注：<br>
 * @version 1.0<br>
 */
@Service(value="iCCardInvoiceApplyService")
public class ICCardInvoiceApplyServiceImpl extends ServiceImpl implements IICCardInvoiceApplyService {
	
	private static Log logger = LogFactory.getLog(ICCardInvoiceApplyServiceImpl.class);
	
	private IICCardInvoiceApplyManager icCardInvoiceApplyManager;
	
	private IUserService userService = null;
	
	private IFileService fileService = null;
	
	public ICCardInvoiceApplyServiceImpl() {
		icCardInvoiceApplyManager =  (IICCardInvoiceApplyManager) ServiceFactory.getFactory().getService(IICCardInvoiceApplyManager.class);
	}


	@Override
	public PaginationResult<?> queryListPage(DynamicSqlParameter requestParam) {
		
		logger.info("查询发票信息调用开始：Method:queryListPage;params:"+requestParam.toString());
		
		PaginationResult<ICCardInvoiceApply> result = null;
		try {
			String userName = null;//会员名称
			String receiverPhoneNum = null;//注册手机号
			String invoiceType = null;//发票类型
			String invoiceStatus = null;//开票状态
			String invoiceName = null;//开票抬头
			Long startTime = null;//申请时间开始
			Long endTime = null;//申请时间结束
			ICCardInvoiceApplyExampleExtended extended = new ICCardInvoiceApplyExampleExtended();
			extended.setOrderByClause(" create_time desc ");
			Map<String, String> paramsEqual = requestParam.getEqual();
			Map<String, String> paramsLike = requestParam.getLike();
			if(paramsLike != null && !paramsLike.isEmpty()){
				userName = paramsLike.get("userName");
				receiverPhoneNum = paramsLike.get("receiverPhoneNum");
				invoiceName = paramsLike.get("invoiceName");
			}
			if(paramsEqual != null && !paramsEqual.isEmpty()){
				invoiceType = paramsEqual.get("invoiceType");
				invoiceStatus = paramsEqual.get("invoiceStatus");
				startTime = paramsEqual.get("startTime") == null ? null : DateTimeUtils.convert2long(paramsEqual.get("startTime"), DateTimeUtils.DATE_FORMAT);
				endTime = paramsEqual.get("endTime") == null ? null : DateTimeUtils.convert2long(paramsEqual.get("endTime"), DateTimeUtils.DATE_FORMAT) + 86400000l;
			}
			Criteria criteria = extended.createCriteria();
			if(!StringUtils.isBlank(userName)) 
				criteria.andUserNameLike(userName);
			if(!StringUtils.isBlank(receiverPhoneNum)) 
				criteria.andUserRegPhoneLike(receiverPhoneNum);
			if(!StringUtils.isBlank(invoiceType)) 
				criteria.andInvoiceTypeEqualTo(invoiceType);
			if(!StringUtils.isBlank(invoiceStatus)) 
				criteria.andStatusEqualTo(invoiceStatus);
			if(!StringUtils.isBlank(invoiceName)) 
				criteria.andInvoiceNameLike(invoiceName);
			if(startTime!=null){
				criteria.andCreateTimeGreaterThanOrEqualTo(startTime);
			}
			if(endTime!=null){
				criteria.andCreateTimeLessThanOrEqualTo(endTime);
			}
			int offset = (requestParam.getPage()-1)*requestParam.getRows();
			int limit = requestParam.getRows();
			
			logger.info("查询发票信息调用开始：Method:queryListPage;userName:"+userName+";receiverPhoneNum:"+receiverPhoneNum+
					";invoiceType:"+invoiceType+";invoiceStatus:"+invoiceStatus+";invoiceName:"+invoiceName+";offset:"+offset+";limit"+limit);
			extended.setSkipNum(offset);
			extended.setLimitNum(limit);
			
			logger.info("查询发票信息远程调用：(ypptBaseService)开始：接口名称：icCardInvoiceApplyManager, userName:"+userName+";receiverPhoneNum:"+receiverPhoneNum+
					";invoiceType:"+invoiceType+";invoiceStatus:"+invoiceStatus+";invoiceName:"+invoiceName+";offset:"+offset+";limit"+limit);
			
			result = icCardInvoiceApplyManager.paginate(extended);
			
			logger.info("查询发票信息远程调用：(ypptBaseService)结束：接口名称：icCardInvoiceApplyManager ;userName:"+userName+";receiverPhoneNum:"+receiverPhoneNum+
					";invoiceType:"+invoiceType+";invoiceStatus:"+invoiceStatus+";invoiceName:"+invoiceName+";offset:"+offset+";limit"+limit);
		} catch (Exception e) {
			logger.error("分页查询发票记录异常", e);
		}
		return result;
	}
	
	
	@Override
	public int auditingICCardInvoiceApply(ICCardInvoiceApply invoice,ICCardInvoiceApplyProcessLog objLog) throws BusinessException {
		
		logger.info("审核发票信息调用开始：auditing");
		
		if(invoice == null){
			logger.info("审核发票信息调用开始：auditing,参数为："+invoice);
			throw new BusinessException("obj(开票审核实体)不能为空");
		}
		
		int result = 0;
		
		try {
				
			logger.info("开票审核远程调用(ypptBaseService)开始：Method:auditing");
			result = icCardInvoiceApplyManager.auditing(invoice, objLog);
			
			logger.info("开票审核远程调用(ypptBaseService)结束：Method:auditing");
			
		} catch (Exception e) {
			
			throw new BusinessException(e.getMessage());
		}
		
		return result;
	}

	@Override
	public PaginationResult<?> queryObjectById(Object model) throws BusinessException {
		
		logger.info("根据主键查询发票信息调用开始：queryObjectById");
		if(model == null){
			logger.info("根据主键查询发票信息调用开始：queryObjectById,参数为："+model);
			return null;
		}
		PaginationResult<ICCardInvoiceApply> result = new PaginationResult<ICCardInvoiceApply>();
		
		try {
			if(model instanceof ICCardInvoiceApply){
				String id =  ((ICCardInvoiceApply)model).getId();
				logger.info("根据主键查询发票信息调用开始：queryObjectById,id："+id);
				
				if(StringUtils.isBlank(id)){
					logger.info("根据主键查询发票信息调用开始：queryObjectById,id is null："+id);
					return null;
				}
				logger.info("查询发票信息远程调用(ypptBaseService)开始：接口名称：icCardInvoiceApplyManager,Method:getById");
				ICCardInvoiceApply obj  = icCardInvoiceApplyManager.getById((ICCardInvoiceApply)model);
				
				//businessLicenseUrl  单位营业执照
				if(!StringUtils.isBlank(obj.getBusinessLicenseUrl())){
					obj.setBusinessLicenseUrl(getFileService().getFileURL(obj.getBusinessLicenseUrl(), IFileService.IMAGE_MAX));
				}
				//invoiceFileUrl 发票找票
				if(!StringUtils.isBlank(obj.getInvoiceFileUrl())){
					obj.setInvoiceFileUrl(getFileService().getFileURL(obj.getInvoiceFileUrl(), IFileService.IMAGE_MAX));
				}
				//taxRegCertificateUrl  单位营业执照
				if(!StringUtils.isBlank(obj.getTaxRegCertificateUrl())){
					obj.setTaxRegCertificateUrl(getFileService().getFileURL(obj.getTaxRegCertificateUrl(), IFileService.IMAGE_MAX));
				}
				//orgCodeCertificateUrl 法人组织机构代码证
				if(!StringUtils.isBlank(obj.getOrgCodeCertificateUrl())){
					obj.setOrgCodeCertificateUrl(getFileService().getFileURL(obj.getOrgCodeCertificateUrl(), IFileService.IMAGE_MAX));
				}
				//generalTaxProveUrl 一般纳税人证明
				if(!StringUtils.isBlank(obj.getGeneralTaxProveUrl())){
					obj.setGeneralTaxProveUrl(getFileService().getFileURL(obj.getGeneralTaxProveUrl(), IFileService.IMAGE_MAX));
				}
				result.setDataObject(obj);
				logger.info("查询发票信息远程调用(ypptBaseService)结束：接口名称：icCardInvoiceApplyManager,Method:getById");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public IUserService getUserService() {
		if(userService==null)
			userService=(IUserService) ServiceFactory.getFactory().getService(IUserService.class);
		return userService;
	}


	@Override
	public PaginationResult<?> queryICCardInvoiceApplyLogPage(Object model) {
		logger.info("根据发票主键查询发票操作日志信息调用开始：queryICCardInvoiceApplyLogPage");
		if(model == null){
			logger.info("根据发票主键查询发票操作日志信息调用开始：queryICCardInvoiceApplyLogPage,参数为："+model);
			return null;
		}
		PaginationResult<ICCardInvoiceApplyProcessLog> result = new PaginationResult<ICCardInvoiceApplyProcessLog>();
		
		try {
			if(model instanceof ICCardInvoiceApply){
				String id =  ((ICCardInvoiceApply)model).getId();
				logger.info("根据发票主键查询发票操作日志信息调用开始：queryICCardInvoiceApplyLogPage,id："+id);
				
				if(StringUtils.isBlank(id)){
					logger.info("根据发票主键查询发票操作日志信息调用开始：queryICCardInvoiceApplyLogPage,id is null："+id);
					return null;
				}
				
				logger.info("根据发票主键查询发票操作日志信息调用(ypptBaseService)开始：接口名称：icCardInvoiceApplyManager,Method:getById");
				
				//TODO 根据发票信息主键查询发票操作记录信息接口没有提供
				List<ICCardInvoiceApplyProcessLog> list = icCardInvoiceApplyManager.getICCardInvoiceApplyProcessLogByApplyID(id);
				result.setData(list);
				result.setTotal(list.size());
				logger.info("根据发票主键查询发票操作日志信息调用(ypptBaseService)结束：接口名称：icCardInvoiceApplyManager,Method:getById");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 
	 * <p>方法描述: 返回附件接口实例</p>
	 *
	 * <p>方法备注: 返回附件接口实例</p>
	 *
	 * @return
	 *
	 * <p>创建人：李飞</p>
	 *
	 * <p>创建时间：2015年2月2日 下午7:48:56</p>
	 *
	 */
	private IFileService getFileService(){
		
		if(fileService == null){
			
			fileService = (IFileService) ServiceFactory.getFactory().getService(IFileService.class);
		}
		
		return fileService;
	}
	
}
