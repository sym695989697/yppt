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
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.PostAddressAddReq;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.CheckValue;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.BillRrror;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.PostAddressError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：邮寄地址修改或新增
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
@Service("postAddressAddorUpdateManagerImpl")
public class PostAddressAddorUpdateManagerImpl implements IJsonService {


	protected static final Log logger = LogFactory.getLog(PostAddressAddorUpdateManagerImpl.class);
	
	
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		logger.info(">>>>>>>>>>>>>>>调用添加邮件地址<<<<<<<<<<<<<<<<<<<");
		try{
			PostAddressAddReq req = (PostAddressAddReq) request.getBody();
			UserRecieverAddress userAddress = new UserRecieverAddress();
			userAddress.setId(req.getId());
			logger.info(">>>>>>>>>>>>>>>记录ID["+req.getId()+"]<<<<<<<<<<<<<<<<<<<");
			userAddress.setRecieverName(req.getReceivePerson());
			userAddress.setAddress(req.getAddress());
			userAddress.setCity(PublicStaticParam.toCity(req.getCity()));
			userAddress.setProvince(PublicStaticParam.toProvince(req.getProvince()));
			userAddress.setDistrict(req.getDistrict());
			userAddress.setPhoneNum(req.getPhone());
			userAddress.setZipCode(req.getPost());
			userAddress.setCreateTime(System.currentTimeMillis());
			userAddress.setCreator(req.getUserId());
			userAddress.setUserId(req.getUserId());
			logger.info(">>>>>>>>>>>>>>>调用服务添加/修改邮寄地址，开始时间["+new Date()+"]<<<<<<<<<<<<<<<<<<<");
			if(StringUtils.isBlank(userAddress.getId())){
				logger.info(">>>>>>>>>>>>>>>添加邮寄地址<<<<<<<<<<<<<<<<<<<");
				ImplementationSupport.getUserRecieverAddressManager().addRecieverAddressInfo(userAddress);
			}else{
				logger.info(">>>>>>>>>>>>>>>修改邮寄地址<<<<<<<<<<<<<<<<<<<");
				ImplementationSupport.getUserRecieverAddressManager().modifyRecieverAddressInfo(userAddress);
			}
			logger.info(">>>>>>>>>>>>>>>调用服务添加/修改邮寄地址，结束时间["+new Date()+"]<<<<<<<<<<<<<<<<<<<");
		}catch(Exception e){
			logger.info("新增或修改邮寄地址失败",e);
			throw new ClientException("E220010", PostAddressError.E220010);
		}
		return null;
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E200001",BillRrror.E200001);
		}
		PostAddressAddReq req = (PostAddressAddReq) request.getBody();
		UAAUser user = null;
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E220002",PostAddressError.E220002);
		}else{
			logger.info(">>>>>>>>>>>>>>>验证用户是否存在，ID["+req.getUserId()+"]开始时间["+new Date()+"]<<<<<<<<<<<<<<<<<<<");
			user = ImplementationSupport.getUserManager().queryUaaUserById(req.getUserId(), null);
			logger.info(">>>>>>>>>>>>>>>结束时间["+new Date()+"]<<<<<<<<<<<<<<<<<<<");
			if(user == null){
				logger.info(">>>>>>>>>>>>>>>没有查到相关的用过<<<<<<<<<<<<<<<<<<<");
				throw new ClientException("E120011", UserError.E120011);
			}else{
				logger.info(">>>>>>>>>>>>>>>查到相关的用户<<<<<<<<<<<<<<<<<<<");
			}
		}
		if(StringUtils.isBlank(req.getReceivePerson())){
			throw new ClientException("E220003",PostAddressError.E220003);
		}
		if(!CheckValue.isPhone(req.getPhone())){
			throw new ClientException("E220004",PostAddressError.E220004);
		}
		if(!CheckValue.checkPostcode(req.getPost())){
			throw new ClientException("E220009",PostAddressError.E220009);
		}
		if(StringUtils.isBlank(req.getProvince())){
			throw new ClientException("E220005",PostAddressError.E220005);
		}
		
		if(StringUtils.isBlank(req.getCity())){
			throw new ClientException("E220006",PostAddressError.E220006);
		}
		if(StringUtils.isBlank(req.getDistrict())){
			throw new ClientException("E220007",PostAddressError.E220007);
		}
		if(StringUtils.isBlank(req.getAddress())){
			throw new ClientException("E220008",PostAddressError.E220008);
		}
	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", PostAddressAddReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}

}
