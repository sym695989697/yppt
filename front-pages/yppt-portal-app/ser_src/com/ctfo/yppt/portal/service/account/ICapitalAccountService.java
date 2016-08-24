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
 * 工程名 ：yppt-portal-app <br>
 * 功能：资金账户管理service层接口 <br>
 * 描述：此类继承IService <br>
 * 授权 : (C) Copyright (c) 2011 <br>
 * 公司 : 北京中交兴路信息科技有限公司 <br>
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
 * <font color="#FF0000">注意: 本内容仅限于[北京中交兴路信息科技有限公司]内部使用，禁止转发</font> <br>
 * 
 * @version 1.0
 * 
 * @author fuxiaohui
 * @since JDK1.6
 */
@AnnotationName(name = "资金账户管理")
public interface ICapitalAccountService extends IService {
    /**
     * 查询资金账户可用余额
     * @param userId
     * @return
     * @throws AAException
     */
    @AnnotationName(name="查询资金账户可用余额")
    String queryCapitalAccountBalanceavailable(String userId) throws AAException;
}
