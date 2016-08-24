package com.ctfo.catchservice.service.queue.dataprocessing;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.ctfo.catchservice.bean.CatchDataVO;

/**
 * 
 * @ClassName: DataProcessingQueue
 * @Description:存储抓取到的数据，队列封装
 * @author yuguangyang
 * @date 2015年2月28日 下午5:51:30
 */
public class DataProcessingQueue {

	public final BlockingQueue<CatchDataVO> queue;
	public final CountDownLatch latch = new CountDownLatch(1);
	private ThreadPoolExecutor executor;
	private int workThread =5;

	private DataProcessingQueue() {
		queue = new LinkedBlockingQueue<CatchDataVO>(10000);
		executor = new ThreadPoolExecutor(workThread, 10, 10000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1000));
	}
	
	public void startWorker() {
		for (int i = 0; i < workThread; i++) {
			DataProcessingWorkerFacade worker = new DataProcessingWorkerFacade(queue, latch);
			executor.execute(worker);
		}
	}
	private static class SingletonHolder {
		private static DataProcessingQueue instance = new DataProcessingQueue();
	}
	public static DataProcessingQueue getInstance() {
		return SingletonHolder.instance;
	}
}
