package com.ctfo.chpt.action.iccard.sync;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.chpt.service.iccard.sync.ISyncService;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Converter;
import com.ctfo.csm.uaa.dao.beans.UAAUser;

@Controller
@RequestMapping("/syncUser")
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SyncUserController extends BaseControler{
	private static Log logger = LogFactory.getLog(SyncUserController.class);
	
	public SyncUserController() {
		model = new UAAUser();
	}
	
	@Autowired
	private ISyncService iSyncService;
	
	/**
	 * 副卡信息列表
	 */
	@ResponseBody
	public PaginationResult getSyncListPage() {
		try {
			if(requestParam == null){
				requestParam = new DynamicSqlParameter();
			}
			result = iSyncService.getListPage(requestParam);
			
		} catch (Exception e) {
			forwardError(((e.getMessage() == null
					|| e.getMessage().isEmpty() || e.getMessage().equals(
					EMPTY_STRING)) ? "查询信息集合异常!" : e.getMessage()));
			logger.error("查询信息集合异常!", e);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 按ID刷新用户
	 */
	@ResponseBody
	@RequestMapping(value="/updateUserById" ,produces = "application/json")
	public PaginationResult updateUserById(){
		String id = ((UAAUser)model).getId();
		try {
			Index index = (Index)request.getSession().getAttribute(Converter.SESSION_INDEX);
			iSyncService.updateUserById(index,id);
			logger.info("按ID刷新用户信息成功.");
			
			if(requestParam == null){
				requestParam = new DynamicSqlParameter();
			}
			result = iSyncService.getListPage(requestParam);
			
		} catch (Exception e) {
			logger.error("按ID刷新用户信息时异常.",e);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 刷新所有用户
	 */
	@ResponseBody
	@RequestMapping(value="/updateUserAll" ,produces = "application/json")
	public PaginationResult updateUserAll(){
		try {
			Index index = (Index)request.getSession().getAttribute(Converter.SESSION_INDEX);
			iSyncService.updateAllUser(index);
			logger.info("刷新所有用户信息成功.");
			
			if(requestParam == null){
				requestParam = new DynamicSqlParameter();
			}
			result = iSyncService.getListPage(requestParam);
			
		} catch (Exception e1) {
			logger.error("刷新所有用户信息时异常.",e1);
			result.setMessage(e1.getMessage());
		}
		
		return result;
	}
	
	 
}
