package com.ctfo.yppt.portal.service.account;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.ServiceImpl;
import com.ctfo.common.utils.GlobalMessage;
import com.ctfo.csm.annotations.AnnotationName;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.gatewayservice.interfaces.service.ICreditService;

/**
 * 积分账户相关业务
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午05:26:00
 *
 */
@Service("creditAccountService")
@AnnotationName(name="积分账户相关业务")
public class CreditAccountServiceImpl extends ServiceImpl implements ICreditAccountService {
	private static Log logger = LogFactory.getLog(CreditAccountServiceImpl.class);
	private static ICreditService creditService;
	// 构造方法，将后台对象实例化
	private  CreditAccountServiceImpl() {
	}
	static {
		logger.info(">>>>>>>>" + GlobalMessage.REMO_START_OBJECT);
		creditService = (ICreditService) ServiceFactory.getFactory().getService(ICreditService.class);
		logger.info(">>>>>>>>" + GlobalMessage.REMO_END_OBJECT);
	}
	/**
	 * 查询积分账户可用积分
	 */
	@Override
	public Long queryCreditAccountBalanceavailable(String userId) throws AAException {
	    Long accountBalanceavailable = 0L;
		try {
			logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
			if (StringUtils.isNotBlank(userId)) {
			    accountBalanceavailable = creditService.queryBalance(userId);
			} else {
				logger.debug(">>>>>>>>账户所属组织机构ID为空【requestParam.equal.userId=" + userId + "】");
			}
			logger.info(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
		} catch (Exception e) {
			logger.error(GlobalMessage.REMO_EXCEPTION, e);
			throw new AAException(GlobalMessage.REMO_EXCEPTION, e);
		}
		return accountBalanceavailable;
	}
}
