package com.ctfo.gatewayservice.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.crm.boss.beans.UserRecieverAddress;
import com.ctfo.crm.boss.beans.UserRecieverAddressExampleExtended;
import com.ctfo.crm.internal.bizz.intf.UserRecieverAddressService;
import com.ctfo.crm.meta.beans.ResultObject;
import com.ctfo.csm.intf.support.Paginator;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.uaa.intf.support.Operator;
import com.ctfo.gatewayservice.interfaces.bean.YpptGatewayException;
import com.ctfo.gatewayservice.interfaces.service.IUserRecieverAddressService;

/**
 * 与旺金币服务对接
 */
@Service("IUserRecieverAddressService")
public class UserRecieverAddressServiceImpl implements IUserRecieverAddressService {

	private static final Log logger = LogFactory.getLog(UserRecieverAddressServiceImpl.class);
	private static final String systemUri ="YPPT";
	
	@Override
	public String addRecieverAddressInfo(UserRecieverAddress invoiceInfo)
			throws YpptGatewayException {
		try {
			if(invoiceInfo == null || StringUtils.isBlank(invoiceInfo.getUserId())){
				throw new YpptGatewayException("userid参数为空!");
			}
			logger.info("获取UserRecieverAddress服务发送开始---------");
			UserRecieverAddressService invoiceManager = (UserRecieverAddressService) ServiceFactory.soaService(UserRecieverAddressService.class);
//			logger.info("获取UserRecieverAddress服务发送结束---------:" + invoiceManager == null? "未获取到服务": invoiceManager.toString());
			//车后会员ID
			Operator op = new Operator();
			op.setUserId(invoiceInfo.getCreator());
			op.setSystemsign(systemUri);
			
			ResultObject ro = invoiceManager.addRecieverAddressInfo(invoiceInfo, systemUri, op);
			
			//
			if(ro != null && ResultObject.ResultCode_Success.equals(ro.getCode())){//成功
				String[] uuidArr = (String[]) ro.getObj();
				return uuidArr[0];
			}else{
				throw new YpptGatewayException("创建收件人地址信息发生异常!");
			}
		} catch (YpptGatewayException e) {
			logger.error("创建收件人地址信息发生异常!", e);
			throw e;
		} catch (Exception e) {
			logger.error("创建收件人地址信息发生异常!", e);
			throw new YpptGatewayException("创建收件人地址信息发生异常!", e);
		}
	}
	@Override
	public void modifyRecieverAddressInfo(UserRecieverAddress invoiceInfo)
			throws YpptGatewayException {
		try {
			if(invoiceInfo == null || StringUtils.isBlank(invoiceInfo.getUserId())){
				throw new YpptGatewayException("userid参数为空!");
			}
			UserRecieverAddressService invoiceManager = (UserRecieverAddressService) ServiceFactory.soaService(UserRecieverAddressService.class);
			//车后会员ID
			Operator op = new Operator();
			op.setUserId(invoiceInfo.getModifier());
			op.setSystemsign(systemUri);
			ResultObject ro = invoiceManager.modifyRecieverAddressInfo(invoiceInfo, systemUri, op);
			if(ro == null || !ResultObject.ResultCode_Success.equals(ro.getCode())){//失败
				throw new YpptGatewayException("创建收件人地址信息发生异常!");
			}
		} catch (YpptGatewayException e) {
			logger.error("修改收件人地址信息发生异常!", e);
			throw e;
		} catch (Exception e) {
			logger.error("修改收件人地址信息发生异常!", e);
			throw new YpptGatewayException("修改收件人地址信息发生异常!", e);
		}
	}
	@Override
	public List<UserRecieverAddress> queryRecieverAddressInfo(
			UserRecieverAddressExampleExtended inoviceInfoOption) {
		try {
			
			if(inoviceInfoOption == null){
				throw new YpptGatewayException("查询参数为空!");
			}
			UserRecieverAddressService invoiceManager = (UserRecieverAddressService) ServiceFactory.soaService(UserRecieverAddressService.class);
			//车后会员ID
			Paginator<UserRecieverAddress> pagResult = invoiceManager.queryRecieverAddressInfo(inoviceInfoOption, systemUri, null);
			if(pagResult != null && pagResult.getData() != null){
				return pagResult.getData();
			}else{
				return new ArrayList<UserRecieverAddress>();
			}
		} catch (YpptGatewayException e) {
			logger.error("查询收件人地址信息列表发生异常!", e);
			throw e;
		} catch (Exception e) {
			logger.error("查询收件人地址信息列表发生异常!", e);
			throw new YpptGatewayException("查询收件人地址信息列表发生异常!", e);
		}
	}
	@Override
	public UserRecieverAddress getRecieverAddressInfoById(String invoiceId) {
		try {
			if(StringUtils.isBlank(invoiceId)){
				return null;
			}
			UserRecieverAddressService invoiceManager = (UserRecieverAddressService) ServiceFactory.soaService(UserRecieverAddressService.class);
			return invoiceManager.getRecieverAddressInfoById(invoiceId, systemUri);
		} catch (YpptGatewayException e) {
			logger.error("查询收件人地址信息发生异常!", e);
			throw e;
		} catch (Exception e) {
			logger.error("查询收件人地址信息发生异常!", e);
			throw new YpptGatewayException("查询收件人地址信息发生异常!", e);
		}
	}
	@Override
	public void deleteRecieverAddressInfo(String invoiceId)
			throws YpptGatewayException {
		try {
			if(StringUtils.isBlank(invoiceId)){
				throw new YpptGatewayException("userid参数为空!");
			}
			UserRecieverAddressService invoiceManager = (UserRecieverAddressService) ServiceFactory.soaService(UserRecieverAddressService.class);
			invoiceManager.deleteRecieverAddressInfo(invoiceId, systemUri);
		} catch (YpptGatewayException e) {
			logger.error("删除收件人地址信息发生异常!", e);
			throw e;
		} catch (Exception e) {
			logger.error("删除收件人地址信息发生异常!", e);
			throw new YpptGatewayException("删除收件人地址信息发生异常!", e);
		}
	}
	@Override
	public void modifiedRecieverAddressInfoOften(String invoiceId)
			throws YpptGatewayException {
		try {
			if(StringUtils.isBlank(invoiceId)){
				throw new YpptGatewayException("userid参数为空!");
			}
			UserRecieverAddressService invoiceManager = (UserRecieverAddressService) ServiceFactory.soaService(UserRecieverAddressService.class);
			invoiceManager.modifiedRecieverAddressInfoOften(invoiceId, systemUri);
		} catch (YpptGatewayException e) {
			logger.error("修改默认收件人地址信息发生异常!", e);
			throw e;
		} catch (Exception e) {
			logger.error("修改默认收件人地址信息发生异常!", e);
			throw new YpptGatewayException("修改默认收件人地址信息发生异常!", e);
		}
	}
}
