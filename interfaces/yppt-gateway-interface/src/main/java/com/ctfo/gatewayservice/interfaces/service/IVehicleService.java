package com.ctfo.gatewayservice.interfaces.service;

import com.ctfo.gatewayservice.interfaces.bean.YpptGatewayException;
import com.vims.external.bean.VehicleExternalBean;

public interface IVehicleService {

	/**
	 * 添加车辆
	 * 
	 * @param vehicleNo
	 * @param vehicleColor
	 * @return 返回车辆ID(如果车辆已存在也返回已存在车辆的ID)
	 * @throws YpptGatewayException
	 */
	public String addVehicle(String vehicleNo, String vehicleColor) throws YpptGatewayException;

	/**
	 * 根据车辆Id查询车辆
	 * 
	 * @param vid
	 * @return
	 * @throws YpptGatewayException
	 */
	public VehicleExternalBean getVehicleById(String vid) throws YpptGatewayException;
}
