package com.ctfo.sinoiov.mobile.webapi.base.intf.impl;

import java.util.logging.Logger;

import com.ctfo.base.service.intf.ISimpleCodeManager;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.gatewayservice.interfaces.service.ICreditService;
import com.ctfo.gatewayservice.interfaces.service.IUserInvoiceService;
import com.ctfo.gatewayservice.interfaces.service.IUserRecieverAddressService;
import com.ctfo.gatewayservice.interfaces.service.IUserService;
import com.ctfo.gatewayservice.interfaces.service.IVehicleService;
import com.ctfo.notification.hessian.intf.INotificationService;
import com.ctfo.yppt.baseservice.card.intf.IICCardApplyManager;
import com.ctfo.yppt.baseservice.card.intf.IICCardMainManager;
import com.ctfo.yppt.baseservice.card.intf.IICCardManager;
import com.ctfo.yppt.baseservice.invoice.intf.IICCardInvoiceApplyManager;
import com.ctfo.yppt.baseservice.recharge.intf.IICRechargeApplyDetailManager;
import com.ctfo.yppt.baseservice.recharge.intf.IICRechargeApplyManager;
import com.ctfo.yppt.baseservice.system.intf.IICCardStatManager;
import com.ctfo.yppt.baseservice.system.intf.IUserStatManager;
import com.ctfo.yppt.baseservice.trade.intf.IICCardTradeInfoHistoryManager;
import com.ctfo.yppt.baseservice.trade.intf.IICCardTradeInfoManager;

public class ImplementationSupport {
	Logger logger = Logger.getLogger(this.getClass().getName());

	// ////////////////////////////////////////////
	// ////////面向服务的接口实例化

	/*
	 * public static IAccountRechargeManager getAccountRechargeManager(){ return
	 * (IAccountRechargeManager)
	 * ServiceFactory.getFactory().getService(IAccountRechargeManager.class); }
	 */

	/**
	 * 码表服务
	 */
	public static ISimpleCodeManager getSimpleCodeManager() {
		return (ISimpleCodeManager) ServiceFactory.getFactory().getService(ISimpleCodeManager.class);
	}

	/**
	 * IC卡充值申请服务
	 * 
	 * @return
	 */
	public static IICRechargeApplyManager getICRechargeApplyManager() throws Exception{
		return (IICRechargeApplyManager) ServiceFactory.getFactory().getService(IICRechargeApplyManager.class);
	}

	/**
	 * IC卡副卡服务
	 * 
	 * @return
	 */
	public static IICCardManager getICCardManager() {
		return (IICCardManager) ServiceFactory.getFactory().getService(IICCardManager.class);
	}

	/**
	 * IC卡主卡服务
	 * 
	 * @return
	 */
	public static IICCardMainManager getICCardMainManager() {
		return (IICCardMainManager) ServiceFactory.getFactory().getService(IICCardMainManager.class);
	}

	/**
	 * IC卡开卡服务
	 * 
	 * @return
	 */
	public static IICCardApplyManager getICCardApplyManager() {
		return (IICCardApplyManager) ServiceFactory.getFactory().getService(IICCardApplyManager.class);
	}

	/**
	 * IC卡统计服务
	 * 
	 * @return
	 */
	public static IICCardStatManager getICCardStatManager() {
		return (IICCardStatManager) ServiceFactory.getFactory().getService(IICCardStatManager.class);
	}

	/**
	 * 获取用户统计相关信息
	 * 
	 * @return
	 */
	public static IUserStatManager getUserStatManager() {
		return (IUserStatManager) ServiceFactory.getFactory().getService(IUserStatManager.class);
	}

	/**
	 * 车辆服务
	 * 
	 * @return
	 */
	public static IVehicleService getVehicleManager() {
		return (IVehicleService) ServiceFactory.getFactory().getService(IVehicleService.class);
	}

	/**
	 * 用户服务
	 * 
	 * @return
	 */
	public static IUserService getUserManager() {
		return (IUserService) ServiceFactory.getFactory().getService(IUserService.class);
	}

	/**
	 * 发票服务
	 * 
	 * @return
	 */
	public static IUserInvoiceService getUserInvoiceManager() {
		return (IUserInvoiceService) ServiceFactory.getFactory().getService(IUserInvoiceService.class);
	}

	/**
	 * 开票服务
	 * 
	 * @return
	 */
	public static IICCardInvoiceApplyManager getICCardInvoiceApplyManager() {
		return (IICCardInvoiceApplyManager) ServiceFactory.getFactory().getService(IICCardInvoiceApplyManager.class);
	}

	/**
	 * 收件地址服务
	 * 
	 * @return
	 */
	public static IUserRecieverAddressService getUserRecieverAddressManager() {
		return (IUserRecieverAddressService) ServiceFactory.getFactory().getService(IUserRecieverAddressService.class);
	}

	/**
	 * 短信服务
	 * 
	 * @return
	 */
	public static INotificationService getNotificationManager() {
		return (INotificationService) ServiceFactory.getFactory().getRemoteService(INotificationService.class);
	}

	/**
	 * IC卡历史加油交易
	 * @return
	 */
	public static IICCardTradeInfoHistoryManager getICCardTradeInfoHistoryManager(){
		return (IICCardTradeInfoHistoryManager) ServiceFactory.getFactory().getService(IICCardTradeInfoHistoryManager.class);
	}
	
	/**
	 * IC卡本月加油交易
	 * @return
	 */
	public static IICCardTradeInfoManager getICCardTradeInfoManager(){
		return (IICCardTradeInfoManager) ServiceFactory.getFactory().getService(IICCardTradeInfoManager.class);
	}
	
	/**
	 * <p>
	 * 获得历史返利记录
	 * </p>
	 * @author yaoshenghua
	 * @date 2015年2月5日 下午9:32:04
	 * @return
	 * @throws Exception
	 * @see 
	 */
	public static ICreditService getICreditService() throws Exception{
		return (ICreditService) ServiceFactory.soaService(ICreditService.class);
	}
	
	public static IICRechargeApplyDetailManager getICRechargeApplyDetailManager(){
		return (IICRechargeApplyDetailManager) ServiceFactory.getFactory().getService(IICRechargeApplyDetailManager.class);
	}
}
