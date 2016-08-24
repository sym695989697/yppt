package com.ctfo.catchservice.service.queue.dataprocessing;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: InstantiationTracingBeanPostProcessor
 * @Description: 监听器 ，随系统启动，监听前台用户发送的抓取数据请求
 * @author yuguangyang
 * @date 2015年2月28日 上午11:45:21
 *
 */
@Component
public class CatchDataProcessingListener implements ApplicationListener<ContextRefreshedEvent> {
	private static final Log logger = LogFactory.getLog(CatchDataProcessingListener.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("处理抓取数据监听器已启动,开始监听队列数据并进行处理...");
		startQueue();
	}

	private void startQueue() {
		DataProcessingQueue queue = DataProcessingQueue.getInstance();
		queue.startWorker();
		queue.latch.countDown();
		logger.info("handle catch data the queue is working ...");
	}
}
