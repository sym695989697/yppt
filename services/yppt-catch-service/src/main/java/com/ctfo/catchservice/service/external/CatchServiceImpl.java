package com.ctfo.catchservice.service.external;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.catchservice.bean.AcceptRequestDTO;
import com.ctfo.catchservice.bean.JResult;
import com.ctfo.catchservice.bean.WorkerTypes;
import com.ctfo.catchservice.interfaces.external.ICatchService;
import com.ctfo.catchservice.service.queue.acceptrequest.AcceptRequestQueue;

/**
 * 抓取数据外部接口实现类
 * 
 * @author jichao
 */
@Service(value = "catchService")
public class CatchServiceImpl implements ICatchService {
	private static final Log logger = LogFactory.getLog(CatchServiceImpl.class);

	@Override
	public String hello() {
		return "hello";
	}

	@Override
	public JResult getOilCardBalance(String hostType, String driverCardNo, String userName) throws Exception {
		AcceptRequestDTO acceptDto = new AcceptRequestDTO();
		acceptDto.setUserName(userName);
		acceptDto.setHostType(hostType);
		acceptDto.setRunningStatel("0");
		acceptDto.setWorkerType(WorkerTypes.CATCH_CARDBALANCE);
		AcceptRequestQueue.getInstance().queue.put(acceptDto);
		logger.info("receive user catch the card balance,into the queue,hosttype:" + hostType + ";userName:" + userName + ";cardNo:" + driverCardNo);
		JResult jResult = new JResult();
		jResult.setSuccess(true);
		return jResult;
	}

	@Override
	public JResult getAccountTrade(String hostType, String driverCardNo, String startDate, String endDate, String tradeType, String userName) throws Exception {
		AcceptRequestDTO acceptDto = new AcceptRequestDTO();
		// 定义条件属性
		acceptDto.setUserName(userName);
		acceptDto.setHostType(hostType);
		acceptDto.setCardNo(driverCardNo);
		acceptDto.setStartDate(startDate);
		acceptDto.setEndDate(endDate);
		// 定义队列信息
		acceptDto.setRunningStatel("0");
		acceptDto.setWorkerType(WorkerTypes.CATCH_MAINCARD_TRADEINFO);
		AcceptRequestQueue.getInstance().queue.put(acceptDto);
		logger.info("receive user catch the main card trade info,into the queue,hosttype:" + hostType + ";userName:" + userName + ";cardNo:" + driverCardNo + ";startDate:" + startDate + ";endDate:"
				+ endDate);
		JResult jResult = new JResult();
		jResult.setSuccess(true);
		return jResult;
	}

	@Override
	public JResult getTradeByConsume(String hostType, String driverCardNo, String startDate, String endDate, String userName) throws Exception {
		AcceptRequestDTO acceptDto = new AcceptRequestDTO();
		acceptDto.setUserName(userName);
		acceptDto.setHostType(hostType);
		acceptDto.setCardNo(driverCardNo);
		acceptDto.setStartDate(startDate);
		acceptDto.setEndDate(endDate);
		acceptDto.setRunningStatel("0");
		acceptDto.setWorkerType(WorkerTypes.CATCH_SUBCARD_TRADEINFO);
		AcceptRequestQueue.getInstance().queue.put(acceptDto);
		logger.info("receive user catch the subcard trade info,into the queue,hosttype:" + hostType + ";userName:" + userName + ";cardNo:" + driverCardNo + ";startDate:" + startDate + ";endDate:"
				+ endDate);
		JResult jResult = new JResult();
		jResult.setSuccess(true);
		return jResult;
	}

	@Override
	public JResult getMainCardAndSubcard(String hostType, String cardNo, String cardStatus, String userName) throws Exception {
		AcceptRequestDTO acceptDto = new AcceptRequestDTO();
		acceptDto.setUserName(userName);
		acceptDto.setHostType(hostType);
		acceptDto.setRunningStatel("0");
		acceptDto.setWorkerType(WorkerTypes.CATCH_MAINANDSUBCARD);
		AcceptRequestQueue.getInstance().queue.put(acceptDto);
		logger.info("receive user catch the subcard trade info,into the queue,hosttype:" + hostType + ";userName" + userName);
		JResult jResult = new JResult();
		jResult.setSuccess(true);
		return jResult;
	}
	//
	// @SuppressWarnings({ "rawtypes", "unchecked" })
	// private void dealOracleInsert(final String dealThread, final List list,
	// final String hostType, final String userName, final int type) {
	// Runnable task = new Runnable() {
	// @Override
	// public void run() {
	// int sleepTime = 0;
	// while (true) {
	// int sleep = 5000;
	// sleepTime = sleep + sleepTime;
	// if (list == null || list.size() == 0) {
	// try {
	// Thread.sleep(sleep);
	// } catch (InterruptedException e) {
	// logger.error("InterruptedException异常", e);
	// }
	// if (sleepTime > 60 * 1000 * 30) {
	// logger.info("30分钟没有数据退出:" + sleepTime);
	// return;
	// }
	// } else {
	// sleepTime = 0;
	// // 处理List数据
	// int i = list.size();
	// List tmpList = new ArrayList(list);
	// switch (type) {
	// case addAccountTradeData:
	// catchInternalService.addAccountTradeData(tmpList, hostType, userName);
	// break;
	// case addMainCardAndSubcardByHostType:
	// try {
	// catchInternalService.addMainCardAndSubcardByHostType(tmpList, hostType,
	// userName);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// break;
	// case addTradeByConsumeData:
	// catchInternalService.addTradeByConsumeData(tmpList, hostType);
	// break;
	// case updateBalance:
	// catchInternalService.updateBalance(tmpList, hostType);
	// break;
	// default:
	// logger.info("调用类型错误！！" + type);
	// break;
	// }
	// logger.info("--" + dealThread + "--处理list信息条数：" + i);
	// list.removeAll(tmpList);
	// }
	// }
	// }
	// };
	// Thread thread = new Thread(task);
	// thread.start();
	// }
}
