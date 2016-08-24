package com.ctfo.catchservice.service.queue.dataprocessing;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ctfo.catchservice.bean.CatchDataVO;
import com.ctfo.csm.utils.SpringBUtils;

/**
 * 
 * @ClassName: DataProcessingWorkerFacade
 * @Description: 抓取数据队列实现门面类
 * @author yuguangyang
 * @date 2015年2月28日 下午5:52:37
 *
 */
public class DataProcessingWorkerFacade extends DataProcessingWorker<CatchDataVO> {

	private static final Log logger = LogFactory.getLog(DataProcessingWorkerFacade.class);
	@SuppressWarnings("rawtypes")
	private DataProcessingWorker worker;

	public DataProcessingWorkerFacade(BlockingQueue<CatchDataVO> queue, CountDownLatch latch) {
		super(queue, latch);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doWork(CatchDataVO dto) throws InterruptedException {
		logger.info("the queue work  ... in work facade,handle catch data");
		worker = (DataProcessingWorker) SpringBUtils.getBean("handleDataProcessingWorker");
		worker.doWork(dto);
	}

}
