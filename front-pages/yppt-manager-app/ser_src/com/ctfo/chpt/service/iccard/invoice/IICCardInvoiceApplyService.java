package com.ctfo.chpt.service.iccard.invoice;

import com.ctfo.base.service.IService;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApply;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApplyProcessLog;

/**
 * 
 * 项目名称：yppt-manager-app<br>
 * *********************************<br>
 * <P>类名称：IICCardInvoiceApply</P>
 * *********************************<br>
 * <P>类描述：发票管理功能App-Service接口</P>
 * 创建人：李飞<br>
 * 创建时间：2015年1月21日 下午3:08:49<br>
 * 修改人：李飞<br>
 * 修改时间：2015年1月21日 下午3:08:49<br>
 * 修改备注：<br>
 * @version 1.0<br>
 */
public interface IICCardInvoiceApplyService extends IService {

	/**
	 * 
	 * <p>方法描述: 根据发票信息查询发票操作日志</p>
	 *
	 * <p>方法备注: 根据发票信息查询发票操作日志</p>
	 *
	 * @param requestParam
	 * @return
	 *
	 * <p>创建人：李飞</p>
	 *
	 * <p>创建时间：2015年1月29日 下午5:30:12</p>
	 *
	 */
	PaginationResult<?> queryICCardInvoiceApplyLogPage(Object model);

	/**
	 * 
	 * <p>方法描述: 审核发票信息</p>
	 *
	 * <p>方法备注: 审核发票信息</p>
	 *
	 * @param ICCardInvoiceApply  发票申请实体
	 *        ICCardInvoiceApplyProcessLog  操作实体
	 * 
	 * @return
	 *
	 * <p>创建人：李飞</p>
	 *
	 * <p>创建时间：2015年1月30日 上午10:08:03</p>
	 *
	 */
	int auditingICCardInvoiceApply(ICCardInvoiceApply invoice,ICCardInvoiceApplyProcessLog objLog) throws BusinessException;

}
