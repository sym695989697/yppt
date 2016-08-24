package com.ctfo.gatewayservice.interfaces.service;

import java.util.List;

import com.ctfo.crm.boss.beans.UserInvoiceInfo;
import com.ctfo.crm.boss.beans.UserInvoiceInfoExampleExtended;
import com.ctfo.gatewayservice.interfaces.bean.YpptGatewayException;

public interface IUserInvoiceService {
	/**
	 * 新增发票信息
	 * 
	 * @param invoiceInfo
	 * @param systemUri
	 *            系统标识
	 * @param op
	 *            TODO
	 * @return {@link String}
	 * 
	 */
	public String addInvoiceInfo(UserInvoiceInfo invoiceInfo) throws YpptGatewayException;

	/**
	 * 修改客户资料，字段null表示不做修改；集合字段修改包括增删改，没有id表示增，有id表示改，集合中没有的记录表示删除
	 * 
	 * @param customerMeta
	 * @param systemUri
	 *            系统标识
	 * @return {@link String}
	 * 
	 */
	public void modifyInvoiceInfo(UserInvoiceInfo invoiceInfo) throws YpptGatewayException;

	/**
	 * 查询客户发票信息
	 * 
	 * @param inoviceInfoOption
	 * @param offset 开始值
	 * @param limit 结束值
	 * @return
	 * 
	 */
	public List<UserInvoiceInfo> queryInvoiceInfo( UserInvoiceInfoExampleExtended inoviceInfoOption) ;

	/**
	 * <p>
	 * 方法描述: 根据发票主键ID查询客户发票资料
	 * </p>
	 * 
	 * @param invoiceId
	 *            发票信息Id
	 * 
	 * @param systemUri
	 *            业务系统标识
	 * 
	 * @return UserInvoiceInfo : 发票信息详情
	 * 
	 * 
	 */
	public UserInvoiceInfo getInvoiceInfoById(String invoiceId);

	/**
	 * <p>
	 * 方法描述: 删除发票信息
	 * </p>
	 * @param invoiceId
	 *            发票信息Id
	 * 
	 * @param systemUri
	 *            业务系统标识
	 * @return {@link String}
	 * 
	 */
	 public void deleteInvoiceInfo(String invoiceId) throws YpptGatewayException;

	 /**
	  * <p>
	  * 方法描述: 更改常用发票信息
	  * </p>
	  * @param invoiceId
	  *            发票信息Id
	  * 
	  * @param systemUri
	  *            业务系统标识
	  * @return {@link String}
	  * 
	  */
	 public void modifiedInvoiceInfoOften(String invoiceId) throws YpptGatewayException;
	 
}
