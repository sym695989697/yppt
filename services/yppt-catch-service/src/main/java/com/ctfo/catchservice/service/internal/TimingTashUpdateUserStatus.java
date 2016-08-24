package com.ctfo.catchservice.service.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ctfo.catchservice.interfaces.internal.ICatchInternalService;
import com.ctfo.csm.utils.SpringBUtils;

//定时任务 实现定时刷新用户状态
public class TimingTashUpdateUserStatus extends QuartzJobBean {
	private static final Log logger = LogFactory.getLog(TimingTashUpdateUserStatus.class);

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		ICatchInternalService catchInternalService = (ICatchInternalService) SpringBUtils.getBean("catchInternalService");
		catchInternalService.updateAccountStatus();
		logger.info("定时任务，刷新用户在线状态");
	}
}
