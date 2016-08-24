package com.ctfo.chpt.action.iccard.sync;

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
import com.ctfo.common.models.PaginationResult;
import com.vims.external.bean.VehicleExternalBean;

@Controller
@RequestMapping("/syncVehicle")
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SyncVehicleController extends BaseControler{
	private static Log logger = LogFactory.getLog(SyncVehicleController.class);
	 
	@Autowired
	private ISyncService iSyncService;
	
	public SyncVehicleController(){
		model = new VehicleExternalBean();
	}
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
	 * 按ID刷新车辆信息
	 */
	@ResponseBody
	@RequestMapping(value="/updateVehicleById" ,produces = "application/json")
	public PaginationResult updateVehicleById(){
		String id = ((VehicleExternalBean)model).getId();
		try {
			iSyncService.updateVehicleById(id);
			logger.info("按ID刷新车辆成功.");
			
			if(requestParam == null){
				requestParam = new DynamicSqlParameter();
			}
			result = iSyncService.getListPage(requestParam);
		} catch (Exception e1) {
			result.setMessage(e1.getMessage());
			logger.error("按ID刷新车辆异常.",e1);
		}
		return result;
	}
	
	
	/**
	 * 刷新所有车辆信息
	 */
	@ResponseBody
	@RequestMapping(value="/updateVehicleAll" ,produces = "application/json")
	public PaginationResult updateVehicleAll(){
		try {
			iSyncService.updateAllVehicle();
			logger.info("刷新所有车辆信息成功.");
			
			if(requestParam == null){
				requestParam = new DynamicSqlParameter();
			}
			result = iSyncService.getListPage(requestParam);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			logger.error("刷新所有车辆信息异常.",e);
		}
		return result;
	}
	
}
