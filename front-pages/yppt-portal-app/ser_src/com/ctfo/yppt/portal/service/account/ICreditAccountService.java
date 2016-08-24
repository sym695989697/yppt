package com.ctfo.yppt.portal.service.account;

import com.ctfo.base.service.IService;
import com.ctfo.csm.annotations.AnnotationName;
import com.ctfo.csm.uaa.intf.support.AAException;

/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-portal-app <br>
 * 功能：积分账户相关业务service层接口 <br>
 * 描述：此类继承IService <br>
 * 授权 : (C) Copyright (c) 2015 <br>
 * 公司 : 北京中交兴路车联网科技有限公司 <br>
 * -----------------------------------------------------------------------------
 * <br>
 * 修改历史 <br>
 * <table width="432" border="1">
 * <tr>
 * <td>版本</td>
 * <td>时间</td>
 * <td>作者</td>
 * <td>改变</td>
 * </tr>
 * <tr>
 * <td>1.0</td>
 * <td>2015-1-20</td>
 * <td>fuxiaohui</td>
 * <td>创建</td>
 * </tr>
 * </table>
 * <br>
 * <font color="#FF0000">注意: 本内容仅限于[北京中交兴路车联网科技有限公司]内部使用，禁止转发</font> <br>
 * 
 * @version 1.0
 * 
 * @author fuxiaohui
 * @since JDK1.6
 */
@AnnotationName(name = "积分账户相关业务接口")
public interface ICreditAccountService extends IService {
	/**
	 * 查询积分账户可用余额
	 * @param requestParam 查询参数
	 * @param userId 用户ID
	 * @return
	 * @throws AAException
	 */
	@AnnotationName(name = "查询积分账户可用余额")
	public Long queryCreditAccountBalanceavailable(String userId)throws AAException;
	
}
