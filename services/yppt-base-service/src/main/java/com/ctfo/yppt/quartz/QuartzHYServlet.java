package com.ctfo.yppt.quartz;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ctfo.csm.utils.SpringBUtils;
import com.ctfo.vims.quartz.servlet.ScheduleBaseServlet;
import com.ctfo.yppt.baseservice.recharge.intf.IICRechargeApplyManager;

public class QuartzHYServlet extends ScheduleBaseServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Log logger = LogFactory.getLog(QuartzHYServlet.class);
	
	@Override
	protected boolean executeTask(Map<String, String> paramMap) {
		boolean flag = false;
		try {
			logger.info("-----------------开始执行油卡充值订单超时任务-----------------");
			//调用service
			IICRechargeApplyManager service = (IICRechargeApplyManager) SpringBUtils.getBean("ICRechargeApplyManagerImpl");
			String exptime = "600000";
			if(paramMap.containsKey("exptime")){
				exptime = paramMap.get("exptime");
				logger.info("取得执行油卡充值订单超时任务!");
			}
			service.changeUselessRechargeApplyTask(exptime);
			logger.info("-----------------执行油卡充值订单超时任务成功-----------------");
		} catch (Exception e) {
			logger.error("执行油卡充值订单超时任务时发送异常!", e);
		}
		return flag;
	}
}
