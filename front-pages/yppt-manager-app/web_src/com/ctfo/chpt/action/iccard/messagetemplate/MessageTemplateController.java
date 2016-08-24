package com.ctfo.chpt.action.iccard.messagetemplate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ctfo.base.action.base.BaseControler;
import com.ctfo.chpt.service.iccard.messagetemplate.IMessageTemplateService;
import com.ctfo.common.models.PaginationResult;

/**
 * 
 * 
 * 短信管理action.
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年2月3日    dxs    新建
 * </pre>
 */
@Controller
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/messageTemplate")
public class MessageTemplateController  extends BaseControler{
	
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(MessageTemplateController.class);
	
	@Autowired
	private IMessageTemplateService messageTemplateService;
	
	
	@ResponseBody
	public PaginationResult<?> createMessageTemplate(){
		try {
			result.setMessage(messageTemplateService.createMessageTemplate(requestParam));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("创建短信模板发生错误", e);
			result.setMessage("创建短信模板发生错误");
		}
		return result;
	}
	
	@ResponseBody
	public PaginationResult<?> initMessageTemplateGrid(){
		try {
			result = messageTemplateService.initMessageTemplateGrid();
		} catch (Exception e) {
			logger.error("初始化短信模板列表发生错误", e);
			result.setMessage("初始化短信模板列表发生错误");
		}
		return result;
	}
	
	@ResponseBody
	public PaginationResult<?> stopMessageTemplate(){
		try {
			result.setMessage(messageTemplateService.stopMessageTemplate(requestParam));
		} catch (Exception e) {
			logger.error("停止短信模板发生错误", e);
			result.setMessage("停止短信模板发生错误");
		}
		return result;
	}
	
	@ResponseBody
	public PaginationResult<?> startMessageTemplate(){
		try {
			result.setMessage(messageTemplateService.startMessageTemplate(requestParam));
		} catch (Exception e) {
			logger.error("启动短信模板发生错误", e);
			result.setMessage("启动短信模板发生错误");
		}
		return result;
	}
	
	@ResponseBody
	public PaginationResult<?> deleteMessageTemplate(){
		try {
			result.setMessage(messageTemplateService.deleteMessageTemplate(requestParam));
		} catch (Exception e) {
			logger.error("删除短信模板发生错误", e);
			result.setMessage("删除短信模板发生错误");
		}
		return result;
	}
	
	@ResponseBody
	public PaginationResult<?> modifySign(){
		try {
			result.setMessage(messageTemplateService.modifySign(requestParam));
		} catch (Exception e) {
			logger.error("修改短信签名发生错误", e);
			result.setMessage("修改短信签名发生错误");
		}
		return result;
	}
	
	@ResponseBody
	public PaginationResult<?> querySign(){
		try {
			result = messageTemplateService.querySign();
		} catch (Exception e) {
			logger.error("查询短信签名发生错误", e);
			result.setMessage("查询短信签名发生错误");
		}
		return result;
	}
	
	@ResponseBody
	public PaginationResult<?> updateMessageTemplate(){
		try {
			result.setMessage(messageTemplateService.updateMessageTemplate(requestParam));
		} catch (Exception e) {
			logger.error("修改短信模板发生错误", e);
			result.setMessage("修改短信模板发生错误");
		}
		return result;
	}

	public IMessageTemplateService getMessageTemplateService() {
		return messageTemplateService;
	}

	public void setMessageTemplateService(IMessageTemplateService messageTemplateService) {
		this.messageTemplateService = messageTemplateService;
	}

}
