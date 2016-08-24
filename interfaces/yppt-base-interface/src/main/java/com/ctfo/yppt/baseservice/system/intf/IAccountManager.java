package com.ctfo.yppt.baseservice.system.intf;

import java.util.List;

import com.ctfo.base.baseservice.IBaseManager;
import com.ctfo.yppt.baseservice.system.beans.ICCardAccountConfig;
import com.ctfo.yppt.baseservice.system.beans.ICCardAccountConfigExampleExtended;

/**
 * 
 * 
 * 中石油中石化账户管理.(基本操作父类有实现)
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月20日    dxs    新建
 * </pre>
 */
public interface IAccountManager extends IBaseManager<ICCardAccountConfig, ICCardAccountConfigExampleExtended> {
	/**
	 * 
	 * @Description:发送短信功能实现
	 * @param    templateCode 短信模版 
	 * @param    mobilelist 手机号  多个以","分隔
	 * @return     返回类型
	 * @throws
	 */
	public boolean sendMessage(String templateCode,String mobilelist,List<String> messageParamlist);
}
