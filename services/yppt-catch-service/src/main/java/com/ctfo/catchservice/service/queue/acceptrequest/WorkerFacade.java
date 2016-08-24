package com.ctfo.catchservice.service.queue.acceptrequest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ctfo.catchservice.bean.AcceptRequestDTO;
import com.ctfo.csm.utils.SpringBUtils;

/**
 * 
 * @ClassName: WorkerFacade
 * @Description: 处理队列实现的门面类
 * @author yuguangyang
 * @date 2015年2月28日 下午5:50:55
 *
 */
public class WorkerFacade extends AcceptRequestWorker<AcceptRequestDTO> {

	private static final Log logger = LogFactory.getLog(WorkerFacade.class);
	@SuppressWarnings("rawtypes")
	private AcceptRequestWorker worker;

	public WorkerFacade(BlockingQueue<AcceptRequestDTO> queue, CountDownLatch latch) {
		super(queue, latch);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doWork(AcceptRequestDTO dto) throws InterruptedException {
		logger.info("the queue work  ... in work facade,handle accept request");
		worker = (AcceptRequestWorker) SpringBUtils.getBean("handleAcceptRequestWorker");
		worker.doWork(dto);
	}

}
