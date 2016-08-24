package com.ctfo.yppt.baseservice.system.intf;

import com.ctfo.base.baseservice.IBaseManager;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.yppt.baseservice.system.beans.UserStat;
import com.ctfo.yppt.baseservice.system.beans.UserStatExampleExtended;
import com.ctfo.yppt.bean.UserStatBean;
/**
 * 
 * 
 * 用户返利统计.(基本操作父类有实现)
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月20日    dxs    新建
 * </pre>
 */
public interface IUserStatManager extends
		IBaseManager<UserStat, UserStatExampleExtended> {
	
	/**
	 * 根据用户查询用户统计记录
	 * @param userId	用户ID
	 * @return
	 * @throws BusinessException
	 */
	public UserStat getUserstatByUserId(String userId) throws BusinessException;
	
    /**
     * 
     *<pre>
     * 查询用户相关信息<br/>
     *   累计旺金币返利                                                    
     *   上月返利的金币数量                                        
     *   累计加油消费的钱数                        
     *   累计加油的量             
     *   上月加油消费的钱数                      
     *   上月加油的量
     *   用户绑定油卡张数
     *   用户油卡余额
     *   申请卡的数量
     *</pre>
     * @param userId
     * @return
     * @throws BusinessException
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2015年2月2日    dxs    新建
     * </pre>
     */
    UserStatBean getUserStat(String userId) throws BusinessException;
    
}
