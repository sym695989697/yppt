package com.ctfo.sinoiov.mobile.webapi.service.yppt;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.crm.boss.beans.UserRecieverAddress;
import com.ctfo.crm.boss.beans.UserRecieverAddressExampleExtended;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryPostAddressListReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.PostAddressListRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.AddressVo;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.sinoiov.mobile.webapi.util.PropertyImageUtils;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：邮寄地址管理
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
@Service
public class PostAddressListManagerImpl implements IJsonService {
	
	//日志信息
	protected static final Log logger = LogFactory.getLog(PostAddressListManagerImpl.class);
	
	private int page = 1;
	private int pageSzie = 5;
	
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		logger.info(">>>>>>>>>>>>>>>调用常用地址列表查询<<<<<<<<<<<<<<<<");
		//响应对象
		PostAddressListRsp addressListRsp = null;
		QueryPostAddressListReq req = (QueryPostAddressListReq) request.getBody();
		try{
			logger.info(">>>>>>>>>>>>>>>调用常用地址列表查询<<<<<<<<<<<<<<<<");
			UserRecieverAddressExampleExtended addressExample = new UserRecieverAddressExampleExtended();
			//addressExample.createCriteria().andUserIdEqualTo(req.getUserId());
			UserRecieverAddressExampleExtended.Criteria addressCriteria = addressExample.createCriteria();
			addressCriteria.andUserIdEqualTo(req.getUserId());
			logger.info(">>>>>>>>>>>>>>>调用常用地址列表查询，用户ID：["+req.getUserId()+"]<<<<<<<<<<<<<<<<");
			//addressExample.setEndNum(page);
			//addressExample.setLimitNum(pageSzie);
			logger.info(">>>>>>>>>>>>>>>调用常用地址列表查询，开始时间["+new Date()+"]<<<<<<<<<<<<<<<<");
			List<UserRecieverAddress> list = ImplementationSupport.getUserRecieverAddressManager().queryRecieverAddressInfo(addressExample);
			logger.info(">>>>>>>>>>>>>>>调用常用地址列表查询，结束时间["+new Date()+"]<<<<<<<<<<<<<<<<");
			
			if(list != null && list.size()>0){
				addressListRsp = new PostAddressListRsp();
				toObject(list,addressListRsp);
			}
			
		}catch(Exception e){
			logger.error("查询失败",e);
			throw new ClientException("E120013", UserError.E120013);
		}
		return addressListRsp;
	}
	
	
	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		QueryPostAddressListReq req = (QueryPostAddressListReq) request.getBody();
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E120002",UserError.E120002);
		}
		
	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", QueryPostAddressListReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}

	
	private void toObject(List<UserRecieverAddress> list,PostAddressListRsp result){
		if(list != null){
			List<AddressVo> listVo = new ArrayList<AddressVo>();
			for(UserRecieverAddress userAddress:list){
				AddressVo response = new AddressVo();
				response.setAddressId(userAddress.getId());
				response.setAddress(userAddress.getAddress());
				response.setCity(userAddress.getCity());
				response.setCounty(userAddress.getDistrict());
				response.setProvince(userAddress.getProvince());
				response.setTelNum(userAddress.getPhoneNum());
				response.setName(userAddress.getRecieverName());
				response.setPost(userAddress.getZipCode());
				listVo.add(response);
			}
			result.setList(listVo);
		}
	}
}
