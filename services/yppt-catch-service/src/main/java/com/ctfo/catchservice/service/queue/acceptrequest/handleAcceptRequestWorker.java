package com.ctfo.catchservice.service.queue.acceptrequest;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.ctfo.catchservice.bean.AcceptRequestDTO;
import com.ctfo.catchservice.bean.WorkerTypes;
import com.ctfo.catchservice.interfaces.internal.ICatchInternalService;

/**
 * @Description:队列处理的具体实现类
 * @author yuguangyang
 * @date 2015年2月28日 上午11:19:39
 */
@Component
public class handleAcceptRequestWorker extends AcceptRequestWorker<AcceptRequestDTO> {
	private static final Log logger = LogFactory.getLog(handleAcceptRequestWorker.class);
	@Resource
	private ICatchInternalService catchInternalService;

	@Override
	public void doWork(AcceptRequestDTO acceptDto) throws InterruptedException {
		int workerType = acceptDto.getWorkerType();
		String hostType = acceptDto.getHostType();
		String userName = acceptDto.getUserName();
		String driverCardNo = "";
		String startDate = "";
		String endDate = "";
		try {
			switch (workerType) {
			case WorkerTypes.CATCH_CARDBALANCE:
				driverCardNo = acceptDto.getCardNo();
				catchInternalService.getOilCardBalance(hostType, driverCardNo, userName);
				logger.info("handle catch card balance task.");
				break;
			case WorkerTypes.CATCH_MAINANDSUBCARD:
				driverCardNo = acceptDto.getCardNo();
				String cardStatus = "";
				catchInternalService.getMainCardAndSubcard(hostType, driverCardNo, cardStatus, userName);
				logger.info("handle catch mainandsubcard task.");
				break;
			case WorkerTypes.CATCH_MAINCARD_TRADEINFO:
				startDate = acceptDto.getStartDate();
				endDate = acceptDto.getEndDate();
				String tradeType = "";
				catchInternalService.getAccountTrade(hostType, driverCardNo, startDate, endDate, tradeType, userName);
				logger.info("handle catch main card trade info task");
				break;
			case WorkerTypes.CATCH_SUBCARD_TRADEINFO:
				startDate = acceptDto.getStartDate();
				endDate = acceptDto.getEndDate();
				catchInternalService.getTradeByConsume(hostType, driverCardNo, startDate, endDate, userName);
				logger.info("handle catch subcard trade info task");
				break;
			default:
				logger.error(" none workerType");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
