package com.ctfo.gatewayservice.interfaces.service;

import java.util.List;

import com.ctfo.crm.boss.beans.UserRecieverAddress;
import com.ctfo.crm.boss.beans.UserRecieverAddressExampleExtended;
import com.ctfo.gatewayservice.interfaces.bean.YpptGatewayException;

public interface IUserRecieverAddressService {
	/**
	 * 新增收货地址信息
	 * 
	 * @param recieverAddress
	 * @param systemUri
	 *            系统标识
	 * @param op
	 *            TODO
	 * @return {@link String}
	 * 
	 */
	public String addRecieverAddressInfo(UserRecieverAddress recieverAddress);

	/**
	 * 修改客户资料，字段null表示不做修改；集合字段修改包括增删改，没有id表示增，有id表示改，集合中没有的记录表示删除
	 * 
	 * @param customerMeta
	 * @param systemUri
	 *            系统标识
	 * @return {@link String}
	 * 
	 */
	public void modifyRecieverAddressInfo(UserRecieverAddress recieverAddress);

	/**
	 * 查询客户收货地址信息
	 * 
	 * @param inoviceInfoOption
	 * @return
	 * 
	 */
	public List<UserRecieverAddress> queryRecieverAddressInfo( UserRecieverAddressExampleExtended inoviceInfoOption);

	/**
	 * <p>
	 * 方法描述: 根据收货地址主键ID查询客户收货地址资料
	 * </p>
	 * 
	 * @param recieverAddressId
	 *            收货地址信息Id
	 * 
	 * @param systemUri
	 *            业务系统标识
	 * 
	 * @return UserRecieverAddressInfo : 收货地址信息详情
	 * 
	 * 
	 */
	public UserRecieverAddress getRecieverAddressInfoById(String recieverAddressId);

	/**
	 * <p>
	 * 方法描述: 删除收货地址信息
	 * </p>
	 * @param recieverAddressId
	 *            收货地址信息Id
	 * 
	 * @param systemUri
	 *            业务系统标识
	 * @return {@link String}
	 * 
	 */
	 public void deleteRecieverAddressInfo(String recieverAddressId);
	 /**
	  * <p>
	  * 方法描述: 更改常用发票信息
	  * </p>
	  * @param recieverAddressId
	  *            收货地址信息Id
	  * 
	  * @param systemUri
	  *            业务系统标识
	  * @return {@link String}
	  * 
	  */
	 public void modifiedRecieverAddressInfoOften(String recieverAddressId) throws YpptGatewayException;
	 
}
