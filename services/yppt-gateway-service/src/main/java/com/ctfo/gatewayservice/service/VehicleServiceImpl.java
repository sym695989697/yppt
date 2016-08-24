package com.ctfo.gatewayservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctfo.gatewayservice.interfaces.bean.EnumYpptGateWayErrorCodes;
import com.ctfo.gatewayservice.interfaces.bean.YpptGatewayException;
import com.ctfo.gatewayservice.interfaces.service.IVehicleService;
import com.ctfo.gatewayservice.util.EnvironmentUtil;
import com.vims.external.bean.ImageExternalBean;
import com.vims.external.bean.QueryParam;
import com.vims.external.bean.RequestParam;
import com.vims.external.bean.ResponseResult;
import com.vims.external.bean.ScopesExternalBean;
import com.vims.external.bean.VehicleExternalBean;
import com.vims.external.intf.ICallService;
import com.vims.external.utils.EnumErrorCodes;

/**
 * 与车辆中心服务对接
 * 
 * @author sunchuanfu
 */
@Service("IVehicleService")
public class VehicleServiceImpl implements IVehicleService {

	private static final Log logger = LogFactory.getLog(VehicleServiceImpl.class);

	// 网关系统在车辆中心的用户编码
	private static String userCode = EnvironmentUtil.getInstance().getPropertyValue("USER_CODE");

	@Autowired
	private ICallService iCallService;

	@Override
	public String addVehicle(String vehicleNo, String vehicleColor) throws YpptGatewayException {
		String vid = null;

		// 封装请求参数
		VehicleExternalBean extVehicle = new VehicleExternalBean();
		extVehicle.setVehiclePlateNo(vehicleNo);
		extVehicle.setVehiclePlateColor(vehicleColor);
		RequestParam requestParam = this.convert2RequestParam(extVehicle, "V001");

		ResponseResult responseResult = null;
		try {
			// 发起请求并返回json
			String resultJson = iCallService.call(JSONObject.fromObject(requestParam).toString());
			responseResult = (ResponseResult) JSONObject
					.toBean(JSONObject.fromObject(resultJson), ResponseResult.class);
			Object result = responseResult.getResult();
			if (result != null) {
				vid = (String) result;
			}
			String errorCode = responseResult.getStatusCode();
			if (errorCode.equals(EnumErrorCodes.VEHICLE_EXISTED.getCode())) {
				// 如果车辆存在，则再查询对应的ID
				vid = this.queryByNoAndColor(vehicleNo, vehicleColor);
			} else if (!errorCode.equals(EnumErrorCodes.SUCCESS.getCode())) {
				throw new YpptGatewayException("CL" + errorCode, EnumErrorCodes.getNameByCode(errorCode));
			}
		} catch (YpptGatewayException e) {
			throw e;
		} catch (Exception e) {
			throw new YpptGatewayException(EnumYpptGateWayErrorCodes.YPPT_ADD_VEHICLE_ERROR.getCode(),
					EnumYpptGateWayErrorCodes.YPPT_ADD_VEHICLE_ERROR.getMsg());
		}

		return vid;
	}

	@Override
	public VehicleExternalBean getVehicleById(String vid) throws YpptGatewayException {
		QueryParam query = new QueryParam();
		Map<String, String> equal = new HashMap<String, String>();
		equal.put("id", vid);
		query.setEqual(equal);

		List<VehicleExternalBean> lstVehicles = null;
		try {
			lstVehicles = this.queryVehicles(query);
		} catch (YpptGatewayException e) {
			throw e;
		}

		if (lstVehicles != null && lstVehicles.size() != 0) {
			return lstVehicles.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据车牌号和颜色查询车辆是否存在
	 * 
	 * @param vehicleNo
	 * @param vehicleColor
	 * @return
	 * @throws YpptGatewayException
	 */
	private String queryByNoAndColor(String vehicleNo, String vehicleColor) throws YpptGatewayException {
		QueryParam query = new QueryParam();
		Map<String, String> equal = new HashMap<String, String>();
		equal.put("vehiclePlateNo", vehicleNo);
		equal.put("vehiclePlateColor", vehicleColor);
		query.setEqual(equal);

		List<VehicleExternalBean> lstVehicles = null;
		try {
			lstVehicles = this.queryVehicles(query);
		} catch (YpptGatewayException e) {
			throw e;
		}

		if (lstVehicles != null && lstVehicles.size() != 0) {
			return lstVehicles.get(0).getId();
		} else {
			return null;
		}
	}

	/**
	 * 查询车辆封装
	 * 
	 * @param query
	 * @return
	 * @throws YpptGatewayException
	 */
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	private List<VehicleExternalBean> queryVehicles(QueryParam query) throws YpptGatewayException {
		// 构造查询参数对象
		RequestParam request = new RequestParam();
		request.setUserCode(userCode);
		request.setMethodCode("V003");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bean", query);
		request.setParam(map);

		String result = null;
		try {
			// 调用车辆中心API
			result = iCallService.call(JSONObject.fromObject(request).toString());
		} catch (Exception e) {
			logger.error("查询车辆时报错！", e);
			throw new YpptGatewayException(EnumYpptGateWayErrorCodes.YPPT_QUERY_VEHICLE_ERROR.getCode(),
					EnumYpptGateWayErrorCodes.YPPT_QUERY_VEHICLE_ERROR.getMsg());
		}

		Map<String, String> mpResponResult = (Map<String, String>) JSONObject.toBean(JSONObject.fromObject(result),
				Map.class);

		String statusCode = mpResponResult.get("statusCode");
		if (!statusCode.equals(EnumErrorCodes.SUCCESS.getCode())) {
			throw new YpptGatewayException("CL" + statusCode, EnumErrorCodes.getNameByCode(statusCode));
		}

		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("imageUrls", ImageExternalBean.class);
		classMap.put("bussinessScopes", ScopesExternalBean.class);

		// TODO 下面方法过期
		List<VehicleExternalBean> lstVehicles = (List<VehicleExternalBean>) JSONArray.toList(
				JSONArray.fromObject(mpResponResult.get("result")), VehicleExternalBean.class, classMap);

		return lstVehicles;
	}

	/**
	 * 封装请求参数对象
	 * 
	 * @param extVehicle
	 * @param methodCode
	 * @return
	 */
	private RequestParam convert2RequestParam(VehicleExternalBean extVehicle, String methodCode) {
		RequestParam request = new RequestParam();
		request.setUserCode(userCode);
		request.setMethodCode(methodCode);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bean", extVehicle);
		request.setParam(map);
		return request;
	}

}
