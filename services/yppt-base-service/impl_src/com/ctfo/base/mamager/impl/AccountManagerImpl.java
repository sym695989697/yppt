package com.ctfo.base.mamager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.yppt.baseservice.system.beans.ICCardAccountConfig;
import com.ctfo.yppt.baseservice.system.beans.ICCardAccountConfigExampleExtended;
import com.ctfo.yppt.baseservice.system.intf.IAccountManager;

/**
 * 
 * @ClassName: 中石油,中石化,数据抓取
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author yuguangyang
 * @date 2015年2月7日 上午11:21:35
 *
 */
@Service("IAccountManagerImpl")
public class AccountManagerImpl extends GenericManagerImpl<ICCardAccountConfig, ICCardAccountConfigExampleExtended> implements IAccountManager {
	
	
	private static final Log logger = LogFactory.getLog(AccountManagerImpl.class);
	
	/**
	 * 
	 * @Description: (这里用一句话描述这个方法的作用)
	 * @param 参数
	 * @return 返回类型
	 * @throws
	 */
	@Override
	public boolean sendMessage(String templateCode,String mobilelist,List<String> messageParamlist) {
		try {
			MessageUtil.sendShortMessage(mobilelist, templateCode, messageParamlist);
		} catch (Exception e) {
			logger.error("发生短信失败", e);
		}
		return true;
	}

}
