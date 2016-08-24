package com.ctfo.base.action.code;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.base.service.IService;
import com.ctfo.base.service.simplecode.ICodeService;
import com.ctfo.common.models.PaginationResult;
/**
 * 码表相关业务控制类
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午04:35:42
 *
 */
@Controller
@Scope("prototype")
public class CodeController extends BaseControler {

	private static Log logger = LogFactory.getLog(CodeController.class);

	private ICodeService codeService;

	// 获取码表的基本信息
	
	@ResponseBody
	public PaginationResult<?> getBaseCodeList() {
		logger.debug("获取码表的基本信息  ");
		try {
			result = codeService.queryListPage(requestParam);
		} catch (Exception e) {
			logger.error("获取码表信息失败", e);
		}
		return result;
	}

	@Resource(name="codeService", description="码表管理service")
	public void setCodeService(ICodeService codeService) {
		this.codeService = codeService;
	}
	
	@Resource(name="codeService", description="码表管理service")
	public void setService(IService codeService) {
		this.service = codeService;
	}
}
