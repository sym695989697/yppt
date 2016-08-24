package com.ctfo.chpt.action.iccard.invoice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.chpt.service.iccard.invoice.ICCardInvoiceApplyServiceImpl;
import com.ctfo.chpt.service.iccard.invoice.IICCardInvoiceApplyService;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApply;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApplyProcessLog;

/**
 * 
 * 项目名称：yppt-manager-app<br>
 * *********************************<br>
 * <P>类名称：ICCardInvoiceApplyController</P>
 * *********************************<br>
 * <P>类描述：发票功能逻辑Controller：ICCardInvoiceApplyController</P>
 * 创建人：李飞<br>
 * 创建时间：2015年1月21日 下午1:12:15<br>
 * 修改人：李飞<br>
 * 修改时间：2015年1月21日 下午1:12:15<br>
 * 修改备注：<br>
 * @version 1.0<br>
 */

@Controller
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/invoice")
public class ICCardInvoiceApplyController extends BaseControler{
	
	private static Log logger = LogFactory.getLog(ICCardInvoiceApplyController.class);
	
	public ICCardInvoiceApplyController(){
		model=new ICCardInvoiceApply();
		service=new ICCardInvoiceApplyServiceImpl();
	}
	
	
	/**
	 * 
	 * <p>方法描述: 分页查询发票信息</p>
	 *
	 * <p>方法备注: 分页查询发票信息</p>
	 *
	 * @return
	 *
	 * <p>创建人：李飞</p>
	 *
	 * <p>创建时间：2015年1月21日 下午1:25:06</p>
	 *
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/queryICCardInvoiceApplyListPage" ,produces = "application/json")
	@ResponseBody
	public PaginationResult queryICCardInvoiceApplyListPage() {
		try {
			result = (PaginationResult<?>)((IICCardInvoiceApplyService)service).queryListPage(requestParam);
		} catch (Exception e) {
			forwardError(((e.getMessage() == null || e.getMessage().isEmpty() || e.getMessage().equals(EMPTY_STRING)) ? "查询信息集合异常!" : e.getMessage()));
			logger.error("查询信息集合异常!", e);
		}
		return result;
	}
	
	/**
	 * 
	 * <p>方法描述: 审核发票信息</p>
	 *
	 * <p>方法备注: 审核发票信息</p>
	 *
	 * @return
	 *
	 * <p>创建人：李飞</p>
	 *
	 * <p>创建时间：2015年1月21日 下午3:04:46</p>
	 *
	 */
	@RequestMapping(value="/auditingICCardInvoiceApply" ,produces = "application/json")
	@ResponseBody
	public PaginationResult<?> auditingICCardInvoiceApply(){
		
		try {
			
			if(model instanceof ICCardInvoiceApply){
				
				ICCardInvoiceApply invoice = (ICCardInvoiceApply)model;
				
				ICCardInvoiceApplyProcessLog objLog = new ICCardInvoiceApplyProcessLog();
				
				objLog.setApplyId(invoice.getId());
				objLog.setRemark(invoice.getMark());
				objLog.setState(invoice.getStatus());
				objLog.setApprovalPerson(index.getUserName());
				objLog.setApprovalPersonId(index.getUserId());
				objLog.setStepNo("test");
				objLog.setApprovalTime(System.currentTimeMillis());
				objLog.setApprovalState(invoice.getStatus());
				objLog.setSuggestion(invoice.getMark());
				
				invoice.setModifier(index.getUserId());
				invoice.setModifierName(index.getUserName());
				invoice.setModifyTime(System.currentTimeMillis());
				
				((IICCardInvoiceApplyService)service).auditingICCardInvoiceApply(invoice,objLog);
				
				result.setMessage("操作成功！");
			}
			
		} catch (BusinessException e) {
			
			logger.error("操作失败！", e);
			
			result.setMessage(e.getMessage());
		}
		 return result;
	}
	
	/**
	 * 
	 * <p>方法描述: 查看发票信息</p>
	 *
	 * <p>方法备注: 查看发票信息</p>
	 *
	 * @return
	 *
	 * <p>创建人：李飞</p>
	 *
	 * <p>创建时间：2015年1月21日 下午3:04:46</p>
	 *
	 */
	@RequestMapping(value="/viewICCardInvoiceApply" ,produces = "application/json")
	@ResponseBody
	public PaginationResult<?> viewICCardInvoiceApply(){
		 try {
			 
			 result = ((IICCardInvoiceApplyService)service).update(model);
			
		} catch (BusinessException e) {
			logger.error("查看发票异常!", e);
		}
		 return result;
	}
	
	/**
	 * 
	 * <p>方法描述: 根据主键查询发票申请信息</p>
	 *
	 * <p>方法备注: 根据主键查询发票申请信息</p>
	 *
	 * @return
	 *
	 * <p>创建人：李飞</p>
	 *
	 * <p>创建时间：2015年1月27日 下午11:55:01</p>
	 *
	 */
	@RequestMapping(value="/getICCardInvoiceApplyById" ,produces = "application/json")
	@ResponseBody
	public PaginationResult<?> getICCardInvoiceApplyById(){
		 
		try {
			 
			 result = ((IICCardInvoiceApplyService)service).queryObjectById(model);
			
		} catch (BusinessException e) {
			
			logger.error("根据发票ID查询发票信息异常!", e);
		}
		
		return result;
	}
	
	
	/**
	 * 
	 * <p>方法描述: 根据发票主键分页查询发票操作信息</p>
	 *
	 * <p>方法备注: 根据发票主键分页查询发票操作信息</p>
	 *
	 * @return
	 *
	 * <p>创建人：李飞</p>
	 *
	 * <p>创建时间：2015年1月21日 下午1:25:06</p>
	 *
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/getICCardInvoiceApplyLogById" ,produces = "application/json")
	@ResponseBody
	public PaginationResult getICCardInvoiceApplyLogById() {
		try {
			result = (PaginationResult<?>)((IICCardInvoiceApplyService)service).queryICCardInvoiceApplyLogPage(model);
		} catch (Exception e) {
			forwardError(((e.getMessage() == null || e.getMessage().isEmpty() || e.getMessage().equals(EMPTY_STRING)) ? "查询信息集合异常!" : e.getMessage()));
			logger.error("查询信息集合异常!", e);
		}
		return result;
	}
	
}
