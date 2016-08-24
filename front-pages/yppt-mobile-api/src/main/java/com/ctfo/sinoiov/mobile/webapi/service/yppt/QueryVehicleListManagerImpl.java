package com.ctfo.sinoiov.mobile.webapi.service.yppt;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.chpt.boss.beans.Vehicle;
import com.ctfo.chpt.boss.intf.IVehicleManager;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryVehicleListReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.QueryVehicleListRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.VehicleVo;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.BeanUtil;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：查询车辆信息
 * <br>
 * 描述：
 * <br>
 * 授权 : (C) Copyright (c) 2011
 * <br>
 * 公司 : 北京中交车联科技服务有限公司
 * <br>
 * -----------------------------------------------------------------------------
 * <br>
 * 修改历史
 * <br>
 * <table width="432" border="1">
 * <tr><td>版本</td><td>时间</td><td>作者</td><td>改变</td></tr>
 * <tr><td>1.0</td><td>2015-1-23</td><td>JiangXF</td><td>创建</td></tr>
 * </table>
 * <br>
 * <font color="#FF0000">注意: 本内容仅限于[北京中交兴路车联网科技有限公司]内部使用，禁止转发</font>
 * <br>
 * 
 * @version 1.0
 * 
 * @author JiangXF
 * @since JDK1.6
 */
@Service("queryVehicleListManagerImpl")
public class QueryVehicleListManagerImpl implements IJsonService{
	
	//日志信息
	protected static final Log logger = LogFactory.getLog(QueryVehicleListManagerImpl.class);

	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		QueryVehicleListRsp queryVehicleListRsp = new QueryVehicleListRsp();
		//获取后台服务
		IVehicleManager vehicleManager = (IVehicleManager) ServiceFactory.getFactory().getService(IVehicleManager.class);
		//构造查询条件
		QueryVehicleListReq queryVehicleListReq = (QueryVehicleListReq) request.getBody();
		logger.debug("手机APP客户端调用【未绑定车辆（Y300009）】接口，用户ID：" + queryVehicleListReq.getUserId());
		
		//查询到数据库中的车辆bean,不能被转换
		List<Vehicle> result = null;
		try {
			//result = vehicleManager.getOnwerUnBindCar(queryVehicleListReq.getMemberId(),queryVehicleListReq.getPage(),queryVehicleListReq.getPageSize(),null);
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			logger.error("查询未绑定车辆失败：", e);
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		//创建集合装转换后的bean
		List<VehicleVo> list = new ArrayList<VehicleVo>();
		
		if(result !=null && result.size()>0){
			//数据库中的bean
			Vehicle vehicle = null;
			for(int i=0 ; i<result.size() ;i++){
				VehicleVo vehicleVo = new VehicleVo();
				vehicle = result.get(i);
				BeanUtil.copyPropertiesWithTypeCast(vehicle,vehicleVo);
				list.add(vehicleVo);
			}
		}
		queryVehicleListRsp.setList(list);
		queryVehicleListRsp.setTotalNum(result.size());
		return queryVehicleListRsp;
	}
	@Override
	public void validate(Parameter request) throws ClientException {
		
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		QueryVehicleListReq req = (QueryVehicleListReq) request.getBody();
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E120002",UserError.E120002);
		}
	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", QueryVehicleListReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}

}
