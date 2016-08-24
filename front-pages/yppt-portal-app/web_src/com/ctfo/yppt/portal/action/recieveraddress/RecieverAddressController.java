package com.ctfo.yppt.portal.action.recieveraddress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Constants;
import com.ctfo.common.utils.GlobalMessage;
import com.ctfo.crm.boss.beans.UserRecieverAddress;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.yppt.portal.service.datastat.IUserStatService;
import com.ctfo.yppt.portal.service.recieveraddress.RecieverAddressService;


/**
 * 
 * 油卡相关业务控制类
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午04:26:22
 *
 */
@Controller
@Scope("prototype")
public class RecieverAddressController extends BaseControler{
	
	@Resource(name="recieverAddressService" ,description= "油卡管理service")
	RecieverAddressService recieverAddressService;
	@Resource(name="userStatService" ,description= "用户数据统计service")
    IUserStatService userStatService;
	private static Log logger = LogFactory.getLog(RecieverAddressController.class);

	 /**
     * 查询地址列表
     * 
     * @return
     */
    @ResponseBody
    public PaginationResult<?> queryAddressList() {
        if (requestParam == null) {
            requestParam = new DynamicSqlParameter();
        }
        if (requestParam.getEqual() == null) {
            requestParam.setEqual(new HashMap<String, String>());
        }
        try {
        	List list = new ArrayList();
        	list = recieverAddressService.queryAddressList(requestParam, index);
        	if(!list.isEmpty()){
        		result.setData(list);
        	}
            result.setDataObject(Constants.OPER_SUCCESS);
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (AAException e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======查询油卡返利记录异常：======", e);
        }
        return result;
    }
    /**
     * 新增/修改地址
     * @param userRecieverAddress
     * @return
     */
    @ResponseBody
    public PaginationResult<?> addAddress(UserRecieverAddress userRecieverAddress){
    	
    	try {
			if(recieverAddressService.addAddress(userRecieverAddress, index)){
				result.setDataObject(Constants.OPER_SUCCESS);
			}else{
				result.setDataObject(Constants.OPER_ERROR);
			}
		} catch (Exception e) {
			logger.error("新增地址失败："+e);
		}
		return result;
    }
    
    /**
     * 新增/修改地址
     * @param id
     * @return
     */
    @ResponseBody
    public PaginationResult<?> modifiedRecieverAddressInfoOften(@RequestParam("id") String id){
    	
    	try {
			if(recieverAddressService.modifiedRecieverAddressInfoOften(id, index)){
				result.setDataObject(Constants.OPER_SUCCESS);
			}else{
				result.setDataObject(Constants.OPER_ERROR);
			}
		} catch (Exception e) {
			logger.error("新增地址失败："+e);
		}
		return result;
    }
    
    /**
     * 删除地址
     * @param id
     * @return
     */
    @ResponseBody
    public PaginationResult<?> deleteAddress(@RequestParam("id") String id){
    	
    	try {
			if(recieverAddressService.deleteAddress(id, index)){
				result.setDataObject(Constants.OPER_SUCCESS);
			}else{
				result.setDataObject(Constants.OPER_ERROR);
			}
		} catch (Exception e) {
			logger.error("删除地址失败："+e);
		}
		return result;
    }
    /**
     * 查询常用地址
     * 
     * @return
     */
    @ResponseBody
    public PaginationResult<?> queryStockAddress() {
        if (requestParam == null) {
            requestParam = new DynamicSqlParameter();
        }
        if (requestParam.getEqual() == null) {
            requestParam.setEqual(new HashMap<String, String>());
        }
        try {
        	List list = new ArrayList();
        	list = recieverAddressService.queryStockAddress(requestParam, index);
        	if(!list.isEmpty()){
        		result.setData(list);
        	}
            result.setDataObject(Constants.OPER_SUCCESS);
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (AAException e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======查询油卡返利记录异常：======", e);
        }
        return result;
    }
	
   
}