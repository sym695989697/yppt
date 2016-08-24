package com.ctfo.catchservice.service.queue.acceptrequest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 
 * @Description:抓取数据请求队列实现，父类
 * @author yuguangyang
 * @date 2015年2月28日 上午10:56:18
 */
public abstract class AcceptRequestWorker<T> implements Runnable {
	private static final Log logger =  LogFactory.getLog(AcceptRequestWorker.class);
	
	private final BlockingQueue<T> queue;
	private final CountDownLatch latch;

	public AcceptRequestWorker() {
		this.queue = null;
		this.latch = null;
	}

	public AcceptRequestWorker(BlockingQueue<T> queue, CountDownLatch latch) {
		this.queue = queue;
		this.latch = latch;
	}

	public void run() {
		try {
			latch.await(); // 放闸之前老实的等待着
			for (;;) {
				doWork(queue.take());
			}
		} catch (InterruptedException e) {
			logger.error("Worker thread will be interrupted...");
		}
	}
	public abstract void doWork(T dto) throws InterruptedException;
}