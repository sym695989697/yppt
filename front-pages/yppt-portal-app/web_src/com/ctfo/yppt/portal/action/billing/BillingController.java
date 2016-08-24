package com.ctfo.yppt.portal.action.billing;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Constants;
import com.ctfo.common.utils.GlobalMessage;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApply;
import com.ctfo.yppt.portal.service.billing.IBillingService;
import com.ctfo.yppt.portal.view.billing.BillingApplyVo;
import com.ctfo.yppt.portal.view.billing.BillingInfoVo;


/**
 * 
 * 发票相关业务控制类
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午04:26:22
 *
 */
@Controller
@Scope("prototype")
public class BillingController extends BaseControler{
	
	@Resource(name="billingService" ,description= "发票管理service")
	IBillingService billingService;
	private static Log logger = LogFactory.getLog(BillingController.class);
	
	public BillingController(){
		model = new ICCardInvoiceApply();
	}
    /**
     * 查询开票记录列表
     * 
     * @return
     */
    @ResponseBody
    public PaginationResult<?> queryBillingApplyList(){
        if (requestParam == null) {
            requestParam = new DynamicSqlParameter();
        }
        if (requestParam.getEqual() == null) {
            requestParam.setEqual(new HashMap<String, String>());
        }
        try {
            result = billingService.queryBillingApplyList(requestParam, index);
            result.setDataObject(Constants.OPER_SUCCESS);
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (AAException e) {
            logger.error("======controller层======查询开票记录列表数据异常：======", e);
            result.setMessage(GlobalMessage.FAIL);
            result.setDataObject(Constants.OPER_ERROR);
        }
        return result;
	}
	
    /**
     * 查询开票信息列表
     * 
     * @return
     */
    @ResponseBody
	public PaginationResult<?> queryBillingInfoList(){
        if (requestParam == null) {
            requestParam = new DynamicSqlParameter();
        }
        if (requestParam.getEqual() == null) {
            requestParam.setEqual(new HashMap<String, String>());
        }
        try {
            result = billingService.queryBillingInfoList(requestParam, index);
            result.setDataObject(Constants.OPER_SUCCESS);
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (AAException e) {
            logger.error("======controller层======查询开票信息列表数据异常：======", e);
            result.setMessage(GlobalMessage.FAIL);
            result.setDataObject(Constants.OPER_ERROR);
        }
        return result;
    }
	
    /**
     * 新增开票申请
     * @param billingApplyVo
     * @return
     */
    @ResponseBody
    public PaginationResult<?> billingApply(BillingApplyVo billingApplyVo){
        try {
            result.setDataObject(billingService.billingApply(billingApplyVo, index));
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (Exception e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======新增开票申请异常：======", e);
        }
        return result;
    }

    /**
     * 新增发票信息
     * @param billingInfoVo
     * @return
     */
    @ResponseBody
    public PaginationResult<?> addBillingInfo(BillingInfoVo billingInfoVo){
        try {
            if(billingService.addBillingInfo(billingInfoVo, index) != null){
                result.setDataObject(Constants.OPER_SUCCESS);
                result.setMessage(GlobalMessage.SUCCESS);
            }else{
                result.setDataObject(Constants.OPER_FAIL);
                result.setMessage(GlobalMessage.FAIL);
            }
        } catch (Exception e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======新增发票信息异常：======", e);
        }
        return result;
    }
    
    @ResponseBody
    public PaginationResult<?> getTotalOpenMoney(){
        try {
        	
        	result = billingService.getTotalOpenMoney(index.getUserId());
        	
        } catch (Exception e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======查询累计开票金额异常：======", e);
        }
        return result;
    }
    
    @ResponseBody
    public PaginationResult<?> getBillingApplyById(@RequestParam("id") String id) {
        if (requestParam == null) {
            requestParam = new DynamicSqlParameter();
        }
        if (requestParam.getEqual() == null) {
            requestParam.setEqual(new HashMap<String, String>());
        }
        
        try {
            result = billingService.getBillingApplyById(id);
            
            result.setMessage(GlobalMessage.SUCCESS);
            
        } catch (AAException e) {
        	logger.error("======controller层======根据ID查询开票信息异常：======", e);
            result.setMessage(GlobalMessage.FAIL);
            result.setDataObject(Constants.OPER_ERROR);
        }
        return result;
    }
    
    @ResponseBody
    public PaginationResult<?> deleteBillingInfoById(@RequestParam("id") String id) {
        if (requestParam == null) {
            requestParam = new DynamicSqlParameter();
        }
        if (requestParam.getEqual() == null) {
            requestParam.setEqual(new HashMap<String, String>());
        }
        
        try {
        	
            result = billingService.deleteBillingInfoById(id);
            
            result.setMessage(GlobalMessage.SUCCESS);
            
        } catch (AAException e) {
        	logger.error("======controller层======根据ID查询开票信息异常：======", e);
            result.setMessage(GlobalMessage.FAIL);
            result.setDataObject(Constants.OPER_ERROR);
        }
        return result;
    }
    
}