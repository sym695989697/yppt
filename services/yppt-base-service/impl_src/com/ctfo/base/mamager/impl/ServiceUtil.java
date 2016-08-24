package com.ctfo.base.mamager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ctfo.common.exception.BusinessException;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.uaa.external.intf.IUAABusinessManager;
import com.ctfo.csm.uaa.external.intf.IUAASystemManager;
import com.ctfo.csm.uaa.intf.authentication.IAuthentication;
import com.ctfo.gatewayservice.interfaces.service.IMessageService;
import com.ctfo.gatewayservice.interfaces.service.IUserService;
import com.ctfo.gatewayservice.interfaces.service.IVehicleService;

public class ServiceUtil {
    private static Log logger = LogFactory.getLog(ServiceUtil.class);

    public static IMessageService getMessageService() throws BusinessException {
        IMessageService messageService = (IMessageService) ServiceFactory
                .getFactory().getService(IMessageService.class);
        if (messageService == null) {
            logger.error("获取消息服务失败，不能发送短信！");
            throw new BusinessException("获取消息服务失败，不能发送短信！");
        }
        return messageService;
    }

    public static IVehicleService getVehicleService() throws BusinessException {
        IVehicleService vehicleService = (IVehicleService) ServiceFactory
                .getFactory().getService(IVehicleService.class);
        if (vehicleService == null) {
            logger.error("获取车辆服务失败，不能进行车辆相关服务！");
            throw new BusinessException("获取车辆服务失败，不能进行车辆相关服务！");
        }
        return vehicleService;
    }

    public static IUserService getUserService() throws BusinessException {
        IUserService userService = (IUserService) ServiceFactory.getFactory()
                .getService(IUserService.class);
        if (userService == null) {
            logger.error("获取用户服务失败，不能进行用户相关服务！");
            throw new BusinessException("获取用户服务失败，不能进行用户相关服务！");
        }
        return userService;
    }

    public static IUAABusinessManager getIUAABusinessManager()
            throws BusinessException {
        IUAABusinessManager manager = (IUAABusinessManager) ServiceFactory
                .getFactory().getService(IUAABusinessManager.class);
        if (manager == null) {
            logger.error("获取IAuthentication服务失败，不能进行IAuthentication相关服务！");
            throw new BusinessException(
                    "获取IAuthentication服务失败，不能进行IAuthentication相关服务！");
        }
        return manager;
    }

    public static IAuthentication getIAuthentication()
            throws BusinessException {
        IAuthentication authManage = (IAuthentication) ServiceFactory
                .getFactory().getService(IAuthentication.class);
        if (authManage == null) {
            logger.error("获取IAuthentication服务失败，不能进行IAuthentication相关服务！");
            throw new BusinessException("获取IAuthentication服务失败，不能进行IAuthentication相关服务！");
        }
        return authManage;
    }
    
    public static IUAASystemManager getIUAASystemManager()
            throws BusinessException {
        IUAASystemManager authManage = (IUAASystemManager) ServiceFactory
                .getFactory().getService(IUAASystemManager.class);
        if (authManage == null) {
            logger.error("获取IUAASystemManager服务失败，不能进行IUAASystemManager相关服务！");
            throw new BusinessException("获取IUAASystemManager服务失败，不能进行IUAASystemManager相关服务！");
        }
        return authManage;
    }
    
    
    

}
