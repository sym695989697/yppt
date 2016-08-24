package com.ctfo.yppt.baseservice.invoice.intf;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ctfo.base.baseservice.IBaseManager;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApply;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApplyExampleExtended;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApplyProcessLog;

public interface IICCardInvoiceApplyManager extends IBaseManager<ICCardInvoiceApply,ICCardInvoiceApplyExampleExtended>{

	/**
	 * 
	 * @author rao yongbing 
	 * @Description 申请开票
	 * @param obj
	 * @return
	 * @throws BusinessException
	 */
	public String  apply(ICCardInvoiceApply obj)throws BusinessException;
	
	
	/**
	 * 
	 * @author rao yongbing 
	 * @Description 累计开票金额
	 * @param params userId 用户id
	 *               status 申请状态（必须为1，表示成功的的申请）
	 * @return
	 * @throws BusinessException
	 */
	public BigDecimal countInvoiceMoney(Map<String,Object> params)throws BusinessException;
	/**
	 * 
	 * @author rao yongbing 
	 * @Description  审核开票
	 * @param obj
	 * @return
	 * @throws BusinessException
	 */
	public int  auditing(ICCardInvoiceApply obj,ICCardInvoiceApplyProcessLog objLog)throws BusinessException;


	/**
	 * 
	 * @author rao yongbing 
	 * @Description 查询开票申请操作日志记录.
	 * @param params applyId
	 * @return
	 * @throws BusinessException
	 */
	public List<ICCardInvoiceApplyProcessLog> getICCardInvoiceApplyProcessLogByApplyID(String applyId)throws BusinessException;
	/**
	 * 
	 * @author rao yongbing 
	 * @Description 根据ID查询申请操作日志记录.
	 * @param id
	 * @return
	 */
	public ICCardInvoiceApplyProcessLog getICCardInvoiceApplyProcessLog(String id);


	
}
