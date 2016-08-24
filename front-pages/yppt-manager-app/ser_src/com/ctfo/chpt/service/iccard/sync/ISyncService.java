package com.ctfo.chpt.service.iccard.sync;

import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.csm.uaa.dao.beans.UAAUser;
import com.ctfo.csm.uaa.intf.support.Operator;
import com.vims.external.bean.VehicleExternalBean;

/**
 * 信息同步
 * 
 * @author jichao
 */
public interface ISyncService {

	/**
	 * 获取列表
	 */
	public PaginationResult<?> getListPage(DynamicSqlParameter requestParam) throws Exception;

	/**
	 * 更新用户信息(单条)
	 */
	public void updateUserById(Index index ,String id) throws Exception;

	/**
	 * 更新车辆信息(单条)
	 */
	public void updateVehicleById(String vid) throws Exception;

	/**
	 * 更新所有用户信息
	 */
	public void updateAllUser(Index index) throws Exception;

	/**
	 * 更新所有车辆信息
	 */
	public void updateAllVehicle() throws Exception;

}
