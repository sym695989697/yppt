package com.ctfo.yppt.portal.service.billing;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.ServiceImpl;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Constants;
import com.ctfo.common.utils.GlobalMessage;
import com.ctfo.crm.boss.beans.UserInvoiceInfo;
import com.ctfo.crm.boss.beans.UserInvoiceInfoExampleExtended;
import com.ctfo.csm.annotations.AnnotationName;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.file.boss.IFileService;
import com.ctfo.gatewayservice.interfaces.bean.YpptGatewayException;
import com.ctfo.gatewayservice.interfaces.service.IUserInvoiceService;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApply;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApplyExampleExtended;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApplyExampleExtended.Criteria;
import com.ctfo.yppt.baseservice.invoice.intf.IICCardInvoiceApplyManager;
import com.ctfo.yppt.baseservice.recharge.cons.ICCardRechargeCons;
import com.ctfo.yppt.baseservice.system.beans.UserStat;
import com.ctfo.yppt.portal.view.billing.BillingApplyVo;
import com.ctfo.yppt.portal.view.billing.BillingInfoVo;

/**
 * 发票相关业务服务类
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午04:42:26
 *
 */
@AnnotationName(name = "发票业务服务")
@Service("billingService")
public class BillingServiceImpl extends ServiceImpl implements IBillingService {

	private static Log logger = LogFactory.getLog(BillingServiceImpl.class);

    private static IUserInvoiceService userInvoiceService;
    private static IICCardInvoiceApplyManager icCardInvoiceApplyManager;
	private IFileService fileService = null;
	// 构造方法，将后台对象实例化
	private  BillingServiceImpl() {
	}
	static {
		logger.info(">>>>>>>>" + GlobalMessage.REMO_START_OBJECT);
		userInvoiceService = (IUserInvoiceService) ServiceFactory.getFactory().getService(IUserInvoiceService.class);
		icCardInvoiceApplyManager = (IICCardInvoiceApplyManager) ServiceFactory.getFactory().getService(IICCardInvoiceApplyManager.class);
		logger.info(">>>>>>>>" + GlobalMessage.REMO_END_OBJECT);
	}
	
    /**
     * 查询开票信息列表
     */
    @Override
    public PaginationResult<UserInvoiceInfo> queryBillingInfoList(DynamicSqlParameter requestParam, Index index) throws AAException {
        PaginationResult<UserInvoiceInfo> result = new PaginationResult<UserInvoiceInfo>();
        try {
            UserInvoiceInfoExampleExtended uiee = new UserInvoiceInfoExampleExtended();
//            Converter.paramToExampleExtended(requestParam, uiee);
            uiee.createCriteria().andUserIdEqualTo(index.getUserId());
            logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
            logger.info(">>>>>>>>调用查询开票信息列表开始...");
            List<UserInvoiceInfo> list = userInvoiceService.queryInvoiceInfo(uiee);
            if(list != null && !list.isEmpty()){
            	for (UserInvoiceInfo obj : list) {
            		//businessLicenseUrl  单位营业执照
        			if(!StringUtils.isBlank(obj.getBusinessLicenseUrl())){
        				obj.setBusinessLicenseUrl(getFileService().getFileURL(obj.getBusinessLicenseUrl(), IFileService.IMAGE_MAX));
        			}
        			//invoiceFileUrl 发票找票
        			if(!StringUtils.isBlank(obj.getInvoiceFileUrl())){
        				obj.setInvoiceFileUrl(getFileService().getFileURL(obj.getInvoiceFileUrl(), IFileService.IMAGE_MAX));
        			}
        			//taxRegCertificateUrl  税务登记号
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
				}
            }
            
            result.setData(list) ;
            result.setTotal(list.size());
            logger.info(">>>>>>>>调用查询开票信息列表结束...");
            logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
            
        } catch (Exception e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error(GlobalMessage.REMO_EXCEPTION, e);
            throw new AAException(GlobalMessage.REMO_EXCEPTION, e);
        }
        return result;
    }
	
    /**
     * 新增开票申请
     * @throws BusinessException 
     */
    @Override
    public String billingApply(BillingApplyVo billingApplyVo, Index index) throws AAException{
        String billingApplyId = null;
        try {
            ICCardInvoiceApply icCardInvoiceApply = new ICCardInvoiceApply();
            logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
            logger.info(">>>>>>>>调用查询开票信息开始...");
            UserInvoiceInfo userInvoiceInfo = userInvoiceService.getInvoiceInfoById(billingApplyVo.getBillingInfoId());
            logger.info(">>>>>>>>调用查询开票信息结束...");
            logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
            //TODO 开票申请信息补充
            icCardInvoiceApply.setInvoiceType(userInvoiceInfo.getInvoiceType());
            icCardInvoiceApply.setUserRegPhone(index.getMobile());
            icCardInvoiceApply.setInvoiceName(userInvoiceInfo.getInvoiceName());
            icCardInvoiceApply.setBusinessLicenseUrl(userInvoiceInfo.getBusinessLicenseUrl());
            icCardInvoiceApply.setGeneralTaxProveUrl(userInvoiceInfo.getGeneralTaxProveUrl());
            icCardInvoiceApply.setOpenBankTaxNo(userInvoiceInfo.getOpenBankTaxNo());
            icCardInvoiceApply.setUserType(index.getUserType());
            icCardInvoiceApply.setUserName(index.getUserName());
            icCardInvoiceApply.setTaxRegCertificateUrl(userInvoiceInfo.getTaxRegCertificateUrl());
            icCardInvoiceApply.setOrgCodeCertificateUrl(userInvoiceInfo.getOrgCodeCertificateUrl());
            icCardInvoiceApply.setInvoiceFileUrl(userInvoiceInfo.getInvoiceFileUrl());
            icCardInvoiceApply.setUserId(index.getUserId());
            icCardInvoiceApply.setAddress(billingApplyVo.getRecAddress());
            icCardInvoiceApply.setProvince(billingApplyVo.getRecProvince());
            icCardInvoiceApply.setDistrict(billingApplyVo.getRecDistrict());
            icCardInvoiceApply.setReceiverAreaId(billingApplyVo.getRecAddressId());
            icCardInvoiceApply.setCity(billingApplyVo.getRecCity());
            icCardInvoiceApply.setCreateTime(new Date().getTime());
            icCardInvoiceApply.setCreator(index.getUserLogin());
            icCardInvoiceApply.setReceiverName(billingApplyVo.getRecName());
            icCardInvoiceApply.setReceiverPhoneNum(billingApplyVo.getRecMobi());
            icCardInvoiceApply.setZipCode(billingApplyVo.getRecZipCode());
            BigDecimal invoiceMoney = new BigDecimal(billingApplyVo.getBillingMoney());
            invoiceMoney = invoiceMoney.multiply(new BigDecimal(100));
            icCardInvoiceApply.setInvoiceMoney(invoiceMoney);
            icCardInvoiceApply.setStatus("1");
            logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
            logger.info(">>>>>>>>调用开票申请开始...");
            billingApplyId = icCardInvoiceApplyManager.apply(icCardInvoiceApply);
            logger.info(">>>>>>>>调用开票申请结束...");
            logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
        } catch (BusinessException e) {
            logger.error(GlobalMessage.REMO_EXCEPTION, e);
            throw new AAException(GlobalMessage.REMO_EXCEPTION, e);
        }
        return billingApplyId;
    }
	
	 /**
     * 新增发票信息
     */
    @Override
    public String addBillingInfo(BillingInfoVo billingInfoVo, Index index) throws AAException {
        String billingInfoId = null;
        try {
            UserInvoiceInfo userInvoiceInfo = new UserInvoiceInfo();
            userInvoiceInfo.setInvoiceFileUrl(billingInfoVo.getKpfjImg());
            userInvoiceInfo.setBusinessLicenseUrl(billingInfoVo.getDwyyzzImg());
            userInvoiceInfo.setGeneralTaxProveUrl(billingInfoVo.getNszzImg());
            userInvoiceInfo.setTaxRegCertificateUrl(billingInfoVo.getSwdjImg());
            userInvoiceInfo.setOrgCodeCertificateUrl(billingInfoVo.getZzjgdmImg());
            
            userInvoiceInfo.setStatus("0");
            userInvoiceInfo.setCreateTime(new Date().getTime());
            userInvoiceInfo.setModifyTime(new Date().getTime());
            userInvoiceInfo.setCreator(index.getUserId());
            userInvoiceInfo.setModifier(index.getUserId());
            userInvoiceInfo.setInvoiceName(billingInfoVo.getBillingTitle());
            userInvoiceInfo.setInvoiceType(billingInfoVo.getBillingType());
            userInvoiceInfo.setUserId(index.getUserId());
            logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
            billingInfoId = userInvoiceService.addInvoiceInfo(userInvoiceInfo);
            logger.info(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
        } catch (YpptGatewayException e) {
            logger.error(GlobalMessage.REMO_EXCEPTION, e);
            throw new AAException(GlobalMessage.REMO_EXCEPTION, e);
        }
        return billingInfoId;
    }

    @Override
    public PaginationResult<ICCardInvoiceApply> queryBillingApplyList(DynamicSqlParameter requestParam, Index index) {
        PaginationResult<ICCardInvoiceApply> result = new PaginationResult<ICCardInvoiceApply>();
        try {
        	Long startTime = null;
        	Long endTime = null;
            ICCardInvoiceApplyExampleExtended iiee = new ICCardInvoiceApplyExampleExtended();
            iiee.setOrderByClause(" create_time desc ");
            Criteria criteria = iiee.createCriteria();
            criteria.andUserIdEqualTo(index.getUserId());
            Map<String, String> mapend = requestParam.getEndwith();
        	if(mapend != null && !mapend.isEmpty()){
        		if(!StringUtils.isBlank(mapend.get("createTime"))){
        			endTime = Long.valueOf(mapend.get("createTime")) + 86400000l;
        		}
        	}
        	Map<String, String> mapstart = requestParam.getStartwith();
        	if(mapstart != null && !mapstart.isEmpty()){
        		if(!StringUtils.isBlank(mapstart.get("createTime"))){
        			startTime = Long.valueOf(mapstart.get("createTime")) ;
        		}
        	}
        	if(startTime!=null){
				criteria.andCreateTimeGreaterThanOrEqualTo(startTime);
			}
			if(endTime!=null){
				criteria.andCreateTimeLessThanOrEqualTo(endTime);
			}
			
			Map<String, Object> inMap = requestParam.getInMap();
        	if(inMap != null && !inMap.isEmpty()){
        		if(inMap.get("status") != null){
        			String status = String.valueOf(inMap.get("status"));
        			if(!StringUtils.isBlank(status)){
        				criteria.andStatusIn(Arrays.asList(status.split(",")));
        			}
        		}
        	}
			
			int offset = (requestParam.getPage()-1)*requestParam.getRows();
			int limit = requestParam.getRows();
			
			iiee.setSkipNum(offset);
			iiee.setLimitNum(limit);
        	
            logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
            logger.info(">>>>>>>>调用查询开票申请列表开始...");
            result = icCardInvoiceApplyManager.paginate(iiee);
            
            List<ICCardInvoiceApply> list = result.getData();
            if(list != null && !list.isEmpty()){
            	for (ICCardInvoiceApply obj : list) {
            		
            		//发票状态1：待审核 2、审核通过 3、审核失败4、开票通过 5开票失败
            		//1、审核中  2、开票成功 3、开票失败
            		if(ICCardRechargeCons.INVOICE_APPLY_STATE_OPENING_SUCC.equals(obj.getStatus())){
            			
            			obj.setStatus(ICCardRechargeCons.INVOICE_REVIEW_STATE_SUCC);
            			
            		}else if(ICCardRechargeCons.INVOICE_APPLY_STATE_AUDITING_FAIL.equals(obj.getStatus()) ||
            				ICCardRechargeCons.INVOICE_APPLY_STATE_OPENING_FAIL.equals(obj.getStatus())){
            			
            			obj.setStatus(ICCardRechargeCons.INVOICE_REVIEW_STATE_FAIL);
            			
            		}else if(ICCardRechargeCons.INVOICE_APPLY_STATE_WAITING.equals(obj.getStatus())){
            			
            			obj.setStatus(ICCardRechargeCons.INVOICE_REVIEW_STATE_SUB);
            		}else{
            			obj.setStatus(ICCardRechargeCons.INVOICE_REVIEW_STATE_WAITING);
            		}
            		
            		//businessLicenseUrl  单位营业执照
        			if(!StringUtils.isBlank(obj.getBusinessLicenseUrl())){
        				obj.setBusinessLicenseUrl(getFileService().getFileURL(obj.getBusinessLicenseUrl(), IFileService.IMAGE_MAX));
        			}
        			//invoiceFileUrl 发票找票
        			if(!StringUtils.isBlank(obj.getInvoiceFileUrl())){
        				obj.setInvoiceFileUrl(getFileService().getFileURL(obj.getInvoiceFileUrl(), IFileService.IMAGE_MAX));
        			}
        			//taxRegCertificateUrl  税务登记号
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
        			
        			BigDecimal value = obj.getInvoiceMoney().divide(new BigDecimal(100));
        			obj.setInvoiceMoney(value.setScale(2, BigDecimal.ROUND_HALF_UP));
				}
            }
            result.setData(list) ;
            result.setStart(requestParam.getPage());
            logger.info(">>>>>>>>调用查询开票申请列表结束...");
            logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
            
        } catch (Exception e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error(GlobalMessage.REMO_EXCEPTION, e);
            throw new AAException(GlobalMessage.REMO_EXCEPTION, e);
        }
        return result;
    }

	@Override
	public PaginationResult<?> getTotalOpenMoney(String id) {
		
		logger.info("根据条件查询累计开票金额调用开始：getTotalOpenMoney");
		if(id == null){
			logger.info("根据条件查询累计开票金额调用开始：getTotalOpenMoney,参数为："+ id);
			return null;
		}
		
		PaginationResult<UserStat> result = new PaginationResult<UserStat>();
		
		try {
			//TODO 根据条件查询累计开票金额调用开始：接口未提供
			logger.info("根据条件查询累计开票金额调用开始(ypptBaseService)结束：接口名称：userStatService,Method:getTotalOpenMoney");
			
			ICCardInvoiceApplyExampleExtended extend = new ICCardInvoiceApplyExampleExtended();
			extend.createCriteria().andUserIdEqualTo(id).andStatusEqualTo(ICCardRechargeCons.INVOICE_APPLY_STATE_OPENING_SUCC);
			List<ICCardInvoiceApply> list = icCardInvoiceApplyManager.getList(extend);
			BigDecimal total = new BigDecimal(0);
			for(ICCardInvoiceApply invoice : list){
				total = total.add(invoice.getInvoiceMoney());
			}
			
			if(total != null){
				total = total.divide(new BigDecimal("100.00")).setScale(2,BigDecimal.ROUND_HALF_UP);
			}
			logger.info("根据条件查询累计开票金额调用开始(ypptBaseService)结束：接口名称：userStatService,Method:getTotalOpenMoney");
			result.setDataObject(String.valueOf(total));
		} catch (Exception e) {
			result.setDataObject(0.00);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public PaginationResult<?> getBillingApplyById(String id) {
		
		logger.info("根据主键查询发票信息调用开始：getBillingApplyById ,参数："+id);
		
		if(StringUtils.isBlank(id)){
			logger.info("根据主键查询发票信息调用开始：getBillingApplyById,参数为："+id);
			return null;
		}
		PaginationResult<ICCardInvoiceApply> result = new PaginationResult<ICCardInvoiceApply>();
		try {
			
			logger.info("查询发票信息远程调用(ypptBaseService)开始：接口名称：icCardInvoiceApplyManager,Method:getById");
			
			ICCardInvoiceApply bean = new ICCardInvoiceApply();
			bean.setId(id);
			
			ICCardInvoiceApply obj  = icCardInvoiceApplyManager.getById(bean);
			
			if(ICCardRechargeCons.INVOICE_APPLY_STATE_OPENING_SUCC.equals(obj.getStatus())){
    			
    			obj.setStatus(ICCardRechargeCons.INVOICE_REVIEW_STATE_SUCC);
    			
    		}else if(ICCardRechargeCons.INVOICE_APPLY_STATE_AUDITING_FAIL.equals(obj.getStatus()) ||
    				ICCardRechargeCons.INVOICE_APPLY_STATE_OPENING_FAIL.equals(obj.getStatus())){
    			
    			obj.setStatus(ICCardRechargeCons.INVOICE_REVIEW_STATE_FAIL);
    			
    		}else if(ICCardRechargeCons.INVOICE_APPLY_STATE_WAITING.equals(obj.getStatus())){
    			
    			obj.setStatus(ICCardRechargeCons.INVOICE_REVIEW_STATE_SUB);
    		}else{
    			obj.setStatus(ICCardRechargeCons.INVOICE_REVIEW_STATE_WAITING);
    		}
			
			BigDecimal value = obj.getInvoiceMoney().divide(new BigDecimal(100));
			obj.setInvoiceMoney(value.setScale(2, BigDecimal.ROUND_HALF_UP));
			
			result.setDataObject(obj);
			
            result.setMessage(GlobalMessage.SUCCESS);
			
			logger.info("查询发票信息远程调用(ypptBaseService)结束：接口名称：icCardInvoiceApplyManager,Method:getById");
			
		} catch (Exception e) {
			result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error(GlobalMessage.REMO_EXCEPTION, e);
            throw new AAException(GlobalMessage.REMO_EXCEPTION, e);
		}
		return result;
	}
	
	@Override
	public PaginationResult<?> deleteBillingInfoById(String id) {
		
		logger.info("根据主键删除发票信息调用开始：deleteBillingInfoById ,参数："+id);
		
		if(StringUtils.isBlank(id)){
			logger.info("根据主键删除发票信息调用开始：deleteBillingInfoById,参数为："+id);
			return null;
		}
		PaginationResult<ICCardInvoiceApply> result = new PaginationResult<ICCardInvoiceApply>();
		try {
			
			logger.info("根据主键删除发票信息调用开始(gatewayService)开始：接口名称：userInvoiceService,Method:deleteInvoiceInfo");
			
			ICCardInvoiceApply bean = new ICCardInvoiceApply();
			bean.setId(id);
			
			userInvoiceService.deleteInvoiceInfo(id);
			
            result.setMessage(GlobalMessage.SUCCESS);
			
			logger.info("根据主键删除发票信息调用开始(gatewayService)结束：接口名称：userInvoiceService,Method:deleteInvoiceInfo");
			
		} catch (Exception e) {
			result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error(GlobalMessage.REMO_EXCEPTION, e);
            throw new AAException(GlobalMessage.REMO_EXCEPTION, e);
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
