package com.ctfo.catchservice.service.queue.acceptrequest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.ctfo.catchservice.bean.AcceptRequestDTO;

/**
 * 
 * @ClassName: AcceptRequestQueue
 * @Description:封装接收前台用户抓取数据请求队列
 * @author yuguangyang
 * @date 2015年2月28日 下午5:47:27
 *
 */
public class AcceptRequestQueue {

	public final BlockingQueue<AcceptRequestDTO> queue;
	public final CountDownLatch latch = new CountDownLatch(1);
	private ThreadPoolExecutor executor;
	private int workThread =5;

	private AcceptRequestQueue() {
		queue = new LinkedBlockingQueue<AcceptRequestDTO>(1000);
		executor = new ThreadPoolExecutor(workThread, 10, 10000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10));
	}
	
	public void startWorker() {
		for (int i = 0; i < workThread; i++) {
			WorkerFacade worker = new WorkerFacade(queue, latch);
			executor.execute(worker);
		}
	}
	public void shutdownWorker() {
		executor.shutdown();
	}
	private static class SingletonHolder {
		private static AcceptRequestQueue instance = new AcceptRequestQueue();
	}
	public static AcceptRequestQueue getInstance() {
		return SingletonHolder.instance;
	}
}
