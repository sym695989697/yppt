package com.ctfo.gatewayservice.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.crm.boss.beans.UserInvoiceInfo;
import com.ctfo.crm.boss.beans.UserInvoiceInfoExampleExtended;
import com.ctfo.crm.internal.bizz.intf.UserInvoiceService;
import com.ctfo.crm.meta.beans.ResultObject;
import com.ctfo.csm.intf.support.Paginator;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.uaa.intf.support.Operator;
import com.ctfo.gatewayservice.interfaces.bean.YpptGatewayException;
import com.ctfo.gatewayservice.interfaces.service.IUserInvoiceService;

/**
 * 与发票服务对接
 */
@Service("IUserInvoiceService")
public class UserInvoiceServiceImpl implements IUserInvoiceService {

	private static final Log logger = LogFactory.getLog(UserInvoiceServiceImpl.class);
	private static final String systemUri ="YPPT";
	
	@Override
	public String addInvoiceInfo(UserInvoiceInfo invoiceInfo)
			throws YpptGatewayException {
		try {
			if(invoiceInfo == null || StringUtils.isBlank(invoiceInfo.getUserId())){
				throw new YpptGatewayException("userid参数为空!");
			}
			UserInvoiceService invoiceManager = (UserInvoiceService) ServiceFactory.soaService(UserInvoiceService.class);
			//车后会员ID
			Operator op = new Operator();
			op.setUserId(invoiceInfo.getCreator());
			op.setSystemsign(systemUri);
			ResultObject ro = invoiceManager.addInvoiceInfo(invoiceInfo, systemUri, op);
			if(ro != null && ResultObject.ResultCode_Success.equals(ro.getCode())){//成功
				String[] uuidArr = (String[]) ro.getObj();
				return uuidArr[0];
			}else{
				throw new YpptGatewayException("创建发票信息发生异常!");
			}
		} catch (YpptGatewayException e) {
			logger.error("创建发票信息发生异常!", e);
			throw e;
		} catch (Exception e) {
			logger.error("创建发票信息发生异常!", e);
			throw new YpptGatewayException("创建发票信息发生异常!", e);
		}
	}
	@Override
	public void modifyInvoiceInfo(UserInvoiceInfo invoiceInfo)
			throws YpptGatewayException {
		try {
			if(invoiceInfo == null || StringUtils.isBlank(invoiceInfo.getUserId())){
				throw new YpptGatewayException("userid参数为空!");
			}
			UserInvoiceService invoiceManager = (UserInvoiceService) ServiceFactory.soaService(UserInvoiceService.class);
			//车后会员ID
			ResultObject ro = invoiceManager.modifyInvoiceInfo(invoiceInfo, systemUri, null);
			if(ro == null || !ResultObject.ResultCode_Success.equals(ro.getCode())){//失败
				throw new YpptGatewayException("创建发票信息发生异常!");
			}
		} catch (YpptGatewayException e) {
			logger.error("修改发票信息发生异常!", e);
			throw e;
		} catch (Exception e) {
			logger.error("修改发票信息发生异常!", e);
			throw new YpptGatewayException("修改发票信息发生异常!", e);
		}
	}
	@Override
	public List<UserInvoiceInfo> queryInvoiceInfo(
			UserInvoiceInfoExampleExtended inoviceInfoOption) {
		try {
			if(inoviceInfoOption == null){
				throw new YpptGatewayException("查询参数为空!");
			}
			UserInvoiceService invoiceManager = (UserInvoiceService) ServiceFactory.soaService(UserInvoiceService.class);
			//车后会员ID
			Paginator<UserInvoiceInfo> pagResult = invoiceManager.queryInvoiceInfo(inoviceInfoOption, systemUri, null);
			if(pagResult != null && pagResult.getData() != null){
				return pagResult.getData();
			}else{
				return new ArrayList<UserInvoiceInfo>();
			}
		} catch (YpptGatewayException e) {
			logger.error("查询发票信息列表发生异常!", e);
			throw e;
		} catch (Exception e) {
			logger.error("查询发票信息列表发生异常!", e);
			throw new YpptGatewayException("查询发票信息列表发生异常!", e);
		}
	}
	@Override
	public UserInvoiceInfo getInvoiceInfoById(String invoiceId) {
		try {
			if(StringUtils.isBlank(invoiceId)){
				return null;
			}
			UserInvoiceService invoiceManager = (UserInvoiceService) ServiceFactory.soaService(UserInvoiceService.class);
			return invoiceManager.getInvoiceInfoById(invoiceId, systemUri);
		} catch (YpptGatewayException e) {
			logger.error("查询发票信息发生异常!", e);
			throw e;
		} catch (Exception e) {
			logger.error("查询发票信息发生异常!", e);
			throw new YpptGatewayException("查询发票信息发生异常!", e);
		}
	}
	@Override
	public void deleteInvoiceInfo(String invoiceId)
			throws YpptGatewayException {
		try {
			if(StringUtils.isBlank(invoiceId)){
				throw new YpptGatewayException("userid参数为空!");
			}
			UserInvoiceService invoiceManager = (UserInvoiceService) ServiceFactory.soaService(UserInvoiceService.class);
			invoiceManager.deleteInvoiceInfo(invoiceId, systemUri);
		} catch (YpptGatewayException e) {
			logger.error("删除发票信息发生异常!", e);
			throw e;
		} catch (Exception e) {
			logger.error("删除发票信息发生异常!", e);
			throw new YpptGatewayException("删除发票信息发生异常!", e);
		}
	}
	@Override
	public void modifiedInvoiceInfoOften(String invoiceId)
			throws YpptGatewayException {
		try {
			if(StringUtils.isBlank(invoiceId)){
				throw new YpptGatewayException("userid参数为空!");
			}
			UserInvoiceService invoiceManager = (UserInvoiceService) ServiceFactory.soaService(UserInvoiceService.class);
			invoiceManager.modifiedInvoiceInfoOften(invoiceId, systemUri);
		} catch (YpptGatewayException e) {
			logger.error("修改默认发票信息发生异常!", e);
			throw e;
		} catch (Exception e) {
			logger.error("修改默认发票信息发生异常!", e);
			throw new YpptGatewayException("修改默认发票信息发生异常!", e);
		}
	}
}
