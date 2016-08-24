package com.ctfo.chpt.action;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ctfo.catchservice.bean.JResult;
import com.ctfo.catchservice.interfaces.external.ITaskService;
import com.ctfo.csm.utils.SpringBUtils;
import com.ctfo.vims.quartz.servlet.ScheduleBaseServlet;

public class QuartzCatchDataServlet extends ScheduleBaseServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Log logger = LogFactory.getLog(QuartzCatchDataServlet.class);
	
	@Override
	protected boolean executeTask(Map<String, String> paramMap) {
		boolean flag = false;
		try {
			
			//调用service
			logger.info("开始调用定时抓取数据任务.");
			ITaskService service = (ITaskService) SpringBUtils.getBean(ITaskService.class.getSimpleName());
			JResult jresult = service.executeTask("");
			flag=jresult.isSuccess();
		} catch (Exception e) {
			logger.error("抓取数据定时任务触发发生错误：", e);
		}
		return flag;
	}
}
