package com.ctfo.catchservice.service.queue.acceptrequest;

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
public class AcceptRequestListener implements ApplicationListener<ContextRefreshedEvent> {
	private static final Log logger = LogFactory.getLog(AcceptRequestListener.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// if (event.getApplicationContext().getParent() == null) {
		// // root applicationContext没有parent，保证是统一的context
		// logger.info("the queue work to start ...");
		// startQueue();
		// }
		logger.info("处理抓取数据请求监听器已启动,开始监听请求队列并做处理...");
		startQueue();
	}

	private void startQueue() {
		AcceptRequestQueue queue = AcceptRequestQueue.getInstance();
		queue.startWorker();
		queue.latch.countDown();
		logger.info("accept request the queue is working ...");
	}
}
