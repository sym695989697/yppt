package com.ctfo.catchservice.service.queue.dataprocessing;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ctfo.catchservice.bean.AccountTradeVo;
import com.ctfo.catchservice.bean.CatchDataVO;
import com.ctfo.catchservice.bean.MainCardAndSubcardVo;
import com.ctfo.catchservice.bean.OilCardBalanceVo;
import com.ctfo.catchservice.bean.TradeByConsumeVo;
import com.ctfo.catchservice.interfaces.internal.ICatchInternalService;

/**
 * 
 * @ClassName: handleDataProcessingWorker
 * @Description: 处理抓取数据的具体实现类
 * @author yuguangyang
 * @date 2015年2月28日 下午5:53:08
 *
 */
@Component
@Lazy(false)
public class handleDataProcessingWorker extends DataProcessingWorker<CatchDataVO> {
	private static final Log logger = LogFactory.getLog(handleDataProcessingWorker.class);
	@Resource
	private ICatchInternalService catchInternalService;

	@Override
	public void doWork(CatchDataVO catchdatavo) throws InterruptedException {
		try {
			if (catchdatavo instanceof MainCardAndSubcardVo) {
				logger.info("匹配对象类型为主副卡,调用添加主副卡方法...");
				catchInternalService.addMainCardAndSubcardByHostType(catchdatavo);
			} else if (catchdatavo instanceof OilCardBalanceVo) {
				logger.info("匹配对象类型为刷新卡余额,调用刷新卡余额方法处理...");
				catchInternalService.updateBalance(catchdatavo);
			} else if (catchdatavo instanceof AccountTradeVo) {
				logger.info("匹配对象类型为主卡交易记录,调用添加主卡交易记录方法...");
				catchInternalService.addAccountTradeData(catchdatavo);
			} else if (catchdatavo instanceof TradeByConsumeVo) {
				logger.info("匹配对象类型为副卡交易记录,调用添加副卡交易记录方法..");
				catchInternalService.addTradeByConsumeData(catchdatavo);
			} else {
				logger.error("匹配对象类型未成功匹配");
			}
		} catch (Exception e) {
			logger.error("处理抓取数据添加过程中发生异常:",e);
		}
	}
}