package com.ctfo.yppt.basemanager.invoice.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.mamager.impl.MessageUtil;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApply;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApplyExampleExtended;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApplyProcessLog;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApplyProcessLogExampleExtended;
import com.ctfo.yppt.baseservice.invoice.intf.IICCardInvoiceApplyManager;
import com.ctfo.yppt.baseservice.recharge.cons.ICCardRechargeCons;

@Service("ICCardInvoiceApplyManagerImpl")
public class ICCardInvoiceApplyManagerImpl
		extends
		GenericManagerImpl<ICCardInvoiceApply, ICCardInvoiceApplyExampleExtended>
		implements IICCardInvoiceApplyManager {

	private static Log logger = LogFactory
			.getLog(ICCardInvoiceApplyManagerImpl.class);

	@Override
	public String apply(ICCardInvoiceApply obj) throws BusinessException {
		logger.info("=================>开始开票申请<===================");
		String uuid = null;
		try {
			if (obj == null) {
				logger.info("================》obj(申请实体)不能为空！《================");
				throw new BusinessException("obj(申请实体)不能为空");
			}
			
			uuid = add(obj);
		}catch (BusinessException e){
			throw e;
		}catch (Exception e) {
			logger.error("开票申请异常！"+e.getMessage(), e);
			throw new BusinessException("开票申请异常！"+e.getMessage(), e);
		}
		logger.info("=================>结束开票申请<===================");
		return uuid;
	}

	@Override
	public int auditing(ICCardInvoiceApply obj,ICCardInvoiceApplyProcessLog objLog) throws BusinessException {
		logger.info("=================>开始开票审核<===================");
		int result=0;
		try {
			if (obj == null) {
				logger.info("================》obj(开票审核实体)不能为空！《================");
				throw new BusinessException("obj(开票审核实体)不能为空");
			}
			
			if (obj.getId()==null) {
				logger.info("================》申请id,不能为空！《================");
				throw new BusinessException("申请id,不能为空");
			}
			
			if (obj.getStatus()==null) {
				logger.info("================》申请状态不能为空！《================");
				throw new BusinessException("申请状态不能为空");
			}
			//审核不通过,备注必填
			if (ICCardRechargeCons.INVOICE_APPLY_STATE_OPENING_FAIL.equals(obj.getStatus())||ICCardRechargeCons.INVOICE_APPLY_STATE_AUDITING_FAIL.equals(obj.getStatus())) {
				if (obj.getMark()==null) {
					logger.info("================》审核不通过,备注必填,不能为空！《================");
					throw new BusinessException("审核不通过,备注必填,不能为空");
				}
			}
			//审核
			result = update(obj);
			//审核日志
			invoiceProcessLog(objLog);
			//发送短信
			try {
				if(ICCardRechargeCons.INVOICE_APPLY_STATE_OPENING_SUCC.equals(obj.getStatus()) || 
						ICCardRechargeCons.INVOICE_APPLY_STATE_AUDITING_FAIL.equals(obj.getStatus()) ||
						ICCardRechargeCons.INVOICE_APPLY_STATE_OPENING_FAIL.equals(obj.getStatus())){
					
					logger.info("开票发送短信调用开始：发送状态：" + (ICCardRechargeCons.INVOICE_APPLY_STATE_OPENING_SUCC.equals(obj.getStatus()) ? "开票成功" : "开票失败" ));
					
					String templateCode = "INVOICE_MESSAGE_AUDITING_SUCC";
					SimpleDateFormat df=new SimpleDateFormat("HH:mm:ss");
					String time = df.format(new Date());
					List<String> messageParamslist=new ArrayList<String>();
					String invoiceMoney = "0.00";
					BigDecimal bigDecimal = obj.getInvoiceMoney();
					if(bigDecimal != null){
						invoiceMoney = String.valueOf(bigDecimal.divide(new BigDecimal("100.00")).setScale(2,BigDecimal.ROUND_HALF_UP));
					}
					messageParamslist.add(time);
					messageParamslist.add(invoiceMoney);
					if(ICCardRechargeCons.INVOICE_APPLY_STATE_OPENING_SUCC.equals(obj.getStatus())){
						messageParamslist.add(obj.getExpressCompany());
						messageParamslist.add(obj.getExpressOrderNo());
						
		    		}else if(ICCardRechargeCons.INVOICE_APPLY_STATE_AUDITING_FAIL.equals(obj.getStatus()) ||
		    				ICCardRechargeCons.INVOICE_APPLY_STATE_OPENING_FAIL.equals(obj.getStatus())){
		    			templateCode = "INVOICE_MESSAGE_AUDITING_FAIL";
		    		}else{
		    			
		    		}
					logger.info("开票发送短信调用开始：发送手机号："+obj.getReceiverPhoneNum()+"模板："+templateCode+"参数："+messageParamslist.toString());
					MessageUtil.sendShortMessage(obj.getReceiverPhoneNum(), templateCode, messageParamslist);
					
					logger.info("开票发送短信调用结束：发送状态：" + (ICCardRechargeCons.INVOICE_APPLY_STATE_OPENING_SUCC.equals(obj.getStatus()) ? "开票成功" : "开票失败" ));
				}
			} catch (Exception e) {
				logger.error("发生短信失败",e);
			}
			
			
			logger.info("=================>结束开票审核<===================");
		}catch (BusinessException e){
			throw e;
		} catch (Exception e) {
			logger.error("开票审核异常！"+e.getMessage(), e);
			throw new BusinessException("开票审核异常！"+e.getMessage(), e);
		}
		
		
		return result;
	}

	private void invoiceProcessLog(ICCardInvoiceApplyProcessLog obj) throws Exception {
		idao.addModel(obj);
	}

	@Override
	public List<ICCardInvoiceApplyProcessLog> getICCardInvoiceApplyProcessLogByApplyID(String applyId) throws BusinessException {
		logger.info("================>开始 查询开票申请操作日志记录 <==============");
		List<?> result = null;
		try {
			if (applyId== null) {
				logger.info("================》applyId不能为空！《================");
				throw new BusinessException("applyId不能为空");
			}
			ICCardInvoiceApplyProcessLogExampleExtended exampleExtended=new ICCardInvoiceApplyProcessLogExampleExtended();
			exampleExtended.createCriteria().andApplyIdEqualTo(applyId);
			result=idao.getModels(exampleExtended);
			logger.info("================>结束 查询开票申请操作日志记录<==============");
			
		}catch (BusinessException e){
			throw e;	
		} catch (Exception e) {
			logger.error("查询开票申请操作日志记录异常！"+e.getMessage(), e);
			throw new BusinessException("查询开票申请操作日志记录异常！"+e.getMessage(), e);

		}
		
		return (List<ICCardInvoiceApplyProcessLog>) result;
	}

	@Override
	public ICCardInvoiceApplyProcessLog getICCardInvoiceApplyProcessLog(
			String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal countInvoiceMoney(Map<String, Object> params)
			throws BusinessException {

		logger.info("=================>开始累计开票金额<===================");
		BigDecimal result = null;
		try {
			if (params == null) {
				logger.info("================》params(查询参数)不能为空！《================");
				throw new BusinessException("params(查询参数)不能为空");
			}
			if (params.get("userId") == null) {
				logger.info("================》用户id不能为空！《================");
				throw new BusinessException("用户id不能为空");
			}
			//开票成功
			params.put("status", "04");
			
//			if (params.get("status") == null) {
//				logger.info("================》申请状态不能为空！《================");
//				throw new BusinessException("申请状态不能为空");
//			}
//			if (!"04".equals(params.get("status"))) {
//				logger.info("================》申请状态不 是04(开票成功)！《================");
//				throw new BusinessException("申请状态不 是04(开票成功)");
//			}

			result = (BigDecimal) queryObjectBySQL(
					"TB_IC_CARD_INVOICE_APPLY_EXTEND.abatorgenerated_countInvoiceMoney",
					params);
		}catch (BusinessException e){
			throw e;
		}catch (Exception e) {
			logger.error("获取累计开票金额异常！", e);
			throw new BusinessException("获取累计开票金额异常！", e);

		}
		logger.info("=================>结束累计开票金额<===================");
		return result;
	}

	@Override
	public int remove(ICCardInvoiceApply bean) throws Exception {
		logger.info("remove方法暂时不支持！");
		throw new BusinessException("remove方法暂时不支持！");
	}

	@Override
	public int removeBatch(List<ICCardInvoiceApply> beans) throws Exception {
		logger.info("removeBatch方法暂时不支持！");
		throw new BusinessException("removeBatch方法暂时不支持！");
	}

	@Override
	public int updateAll(ICCardInvoiceApply bean) throws Exception {
		logger.info("updateAll方法暂时不支持！");
		throw new BusinessException("updateAll方法暂时不支持！");
	}

	@Override
	public int updateByOther(ICCardInvoiceApply bean1, ICCardInvoiceApply bean2)
			throws Exception {
		logger.info("updateByOther方法暂时不支持！");
		throw new BusinessException("updateByOther方法暂时不支持！");
	}
	
	

}
