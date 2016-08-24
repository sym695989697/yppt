package com.ctfo.yppt.portal.service.recieveraddress;

import java.util.List;

import com.ctfo.base.service.IService;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.crm.boss.beans.UserRecieverAddress;
import com.ctfo.csm.annotations.AnnotationName;
import com.ctfo.csm.uaa.intf.support.AAException;

/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-portal-app <br>
 * 功能：油卡业务服务service层接口 <br>
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
 * @author fanxin
 * 
 * @since JDK1.6
 */
@AnnotationName(name = "用户常用地址服务接口")
public interface RecieverAddressService extends IService {
	
	/**
	 * 查询用户常用地址
	 * @return
	 * @throws AAException
	 */
	public List queryAddressList(DynamicSqlParameter requestParam,Index index) throws AAException;
    
	/**
	 * 新增地址
	 * @param userRecieverAddress
	 * @param index
	 * @return
	 * @throws AAException
	 */
	public boolean addAddress(UserRecieverAddress userRecieverAddress,Index index)throws AAException;

	/**
	 * 新增地址
	 * @param id
	 * @param index
	 * @return
	 * @throws AAException
	 */
	public boolean modifiedRecieverAddressInfoOften(String id, Index index);

	/**
	 * 
	 * <p>方法描述: 查询常用地址</p>
	 *
	 * <p>方法备注: </p>
	 *
	 * @param requestParam
	 * @param index
	 * @return
	 *
	 * <p>创建人：李飞</p>
	 *
	 * <p>创建时间：2015年2月5日 下午6:41:20</p>
	 *
	 */
	public List queryStockAddress(DynamicSqlParameter requestParam, Index index);
	/**
	 * 新增地址
	 * @param id
	 * @param index
	 * @return
	 * @throws AAException
	 */
	public boolean deleteAddress(String id, Index index)throws AAException;
	
}
