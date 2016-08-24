package com.ctfo.yppt.portal.service.account;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.ServiceImpl;
import com.ctfo.common.utils.GlobalMessage;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.gatewayservice.interfaces.bean.pay.Account;
import com.ctfo.gatewayservice.interfaces.service.IPayService;

@Service("capitalAccountService")
public class CapitalAccountServiceImpl extends ServiceImpl implements ICapitalAccountService {
	private static Log logger = LogFactory.getLog(CapitalAccountServiceImpl.class);
	private static IPayService payService;
	// 构造方法，将后台对象实例化
	private  CapitalAccountServiceImpl() {
	}
	static {
		logger.info(">>>>>>>>" + GlobalMessage.REMO_START_OBJECT);
		payService = (IPayService) ServiceFactory.getFactory().getService(IPayService.class);
		logger.info(">>>>>>>>" + GlobalMessage.REMO_END_OBJECT);
	}
	
	/**
     * 查询资金账户可用余额
     */
    @Override
    public String queryCapitalAccountBalanceavailable(String userId) throws AAException {
        String accountBalanceavailable = "0.00";
        try {
            logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
            if (StringUtils.isNotBlank(userId)) {
                Account account = payService.queryAccountByUserId(userId);
                accountBalanceavailable = account.getUsableBalance();
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
