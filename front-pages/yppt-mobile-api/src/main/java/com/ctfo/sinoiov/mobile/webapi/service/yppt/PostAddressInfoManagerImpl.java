package com.ctfo.sinoiov.mobile.webapi.service.yppt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.crm.boss.beans.UserRecieverAddress;
import com.ctfo.csm.uaa.dao.beans.UAAUser;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.PostAddressByIdReq;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.AddressVo;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.BillRrror;
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
 * 功能：邮寄地址详情
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
 * <tr><td>1.0</td><td>2015-1-25</td><td>JiangXF</td><td>创建</td></tr>
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
@Service
public class PostAddressInfoManagerImpl implements IJsonService {

	protected static final Log logger = LogFactory.getLog(PostAddressInfoManagerImpl.class);
	
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		AddressVo response = null;
		PostAddressByIdReq req = (PostAddressByIdReq) request.getBody();
		try{
			logger.info(">>>>>>>>>>>>>调用服务查询地址详情，查询ID为：["+req.getId()+"],查询开始时间为["+new Date()+"]<<<<<<<<<<<<<");
			UserRecieverAddress  userAddress = ImplementationSupport.getUserRecieverAddressManager().getRecieverAddressInfoById(req.getId());
			logger.info(">>>>>>>>>>>>>调用服务查询地址详情，查询结果为：["+(userAddress == null)+"],查询结束时间为["+new Date()+"]<<<<<<<<<<<<<");
			if(userAddress != null){
				response = new AddressVo();
				response.setAddressId(userAddress.getId());
				response.setAddress(userAddress.getAddress());
				response.setCity(userAddress.getCity());
				response.setCounty(userAddress.getDistrict());
				response.setProvince(userAddress.getProvince());
				response.setTelNum(userAddress.getPhoneNum());
				response.setName(userAddress.getRecieverName());
				response.setPost(userAddress.getZipCode());
			}
		}catch(Exception e){
			logger.error("查询地址详情失败", e);
			throw new ClientException("E200005", BillRrror.E200005);
		}
		return response;
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E000001",Common.E000001);
		}
		PostAddressByIdReq req = (PostAddressByIdReq) request.getBody();
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E200002",BillRrror.E200002);
		}
		
		if(StringUtils.isBlank(req.getId())){
			throw new ClientException("E200002",BillRrror.E200002);
		}

	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", PostAddressByIdReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}

}
