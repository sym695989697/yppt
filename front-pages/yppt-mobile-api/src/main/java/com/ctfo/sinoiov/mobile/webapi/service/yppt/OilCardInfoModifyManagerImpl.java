package com.ctfo.sinoiov.mobile.webapi.service.yppt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.common.exception.BusinessException;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.ICCardInfoModifyReq;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.ICCardError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：油卡详情修改
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
public class OilCardInfoModifyManagerImpl implements IJsonService {

	protected static final Log logger = LogFactory.getLog(MainDataManagerImpl.class);
	
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		logger.info(">>>>>>>>>>>>>>>>更新IC卡绑定手机号、车牌号<<<<<<<<<<<<<<<<<<<");
		try{
			ICCardInfoModifyReq cardReq = (ICCardInfoModifyReq) request.getBody();
			ICCard card = new ICCard();
			card.setId(cardReq.getId());
			logger.info(">>>>>>>>>>>>>>>>IC卡ID为["+cardReq.getId()+"]<<<<<<<<<<<<<<<<<<<");
			if(StringUtils.isNotBlank(cardReq.getVehicleNo())){
				card.setVehicleNo(cardReq.getVehicleNo());
			}
			if(StringUtils.isNotBlank(cardReq.getPhone())){
				card.setTelephoneNumber(cardReq.getPhone());
			}
			
			card.setModifinguser(cardReq.getUserId());
			card.setModifiedTime(System.currentTimeMillis());
			logger.info(">>>>>>>>>>>>>>>>调用更新IC卡绑定手机号、车牌号[开始时间："+new Date()+"]<<<<<<<<<<<<<<<<<<<");
			int count = updateCardInfo(card);
			logger.info(">>>>>>>>>>>>>>>>调用更新IC卡绑定手机号、车牌号[结束时间："+new Date()+"]<<<<<<<<<<<<<<<<<<<");
			if(count<=0){
				logger.info(">>>>>>>>>>>>>>>>更新记录数为["+count+"]<<<<<<<<<<<<<<<<<<<");
				throw new ClientException("更新卡信息失败");
			}else{
				logger.info(">>>>>>>>>>>>>>>>成功更新["+count+"]条<<<<<<<<<<<<<<<<<<<");
			}
		}catch(Exception e){
			logger.debug("更新卡信息失败",e);
			throw new ClientException("更新卡信息失败", e);
		}
		return null;
	}
	private int updateCardInfo(ICCard card) throws ClientException{
		int count = 0;
		try {
			if(StringUtils.isNotBlank(card.getVehicleNo())){
				logger.info(">>>>>>>>>>>>>>>>调用更新IC卡绑定手机号、车牌号[开始时间："+new Date()+"]<<<<<<<<<<<<<<<<<<<");
				count = ImplementationSupport.getICCardManager().updateICCardVehicleById(card.getId(),card.getVehicleNo(),null,card.getModifinguser());
				logger.info(">>>>>>>>>>>>>>>>调用更新IC卡绑定手机号、车牌号[结束时间："+new Date()+"]<<<<<<<<<<<<<<<<<<<");
			}
			if(StringUtils.isNotBlank(card.getTelephoneNumber())){
			
				logger.info(">>>>>>>>>>>>>>>>调用更新IC卡绑定手机号、车牌号[开始时间："+new Date()+"]<<<<<<<<<<<<<<<<<<<");
				count = ImplementationSupport.getICCardManager().update(card);
				
				logger.info(">>>>>>>>>>>>>>>>调用更新IC卡绑定手机号、车牌号[结束时间："+new Date()+"]<<<<<<<<<<<<<<<<<<<");
			}
			
		} catch (Exception e) {
			
			throw new ClientException("更新失败!", e);
		}
		return count;
		
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		ICCardInfoModifyReq cardReq = (ICCardInfoModifyReq) request.getBody();
		if(StringUtils.isBlank(cardReq.getUserId())){
			logger.info(">>>>>>>>>>>>>>>>用户ID为空<<<<<<<<<<<<<<<<<<<");
			throw new ClientException("E120002",UserError.E120002);
		}
		if(StringUtils.isBlank(cardReq.getId())){
			logger.info(">>>>>>>>>>>>>>>>卡详情ID为空<<<<<<<<<<<<<<<<<<<");
			throw new ClientException("E140007",ICCardError.E140007);
		}
		if(StringUtils.isBlank(cardReq.getVehicleNo()) && StringUtils.isBlank(cardReq.getPhone())){
			throw new ClientException("E140009",ICCardError.E140009);
		}

	}

	@Override
	public Parameter convertParameter(String request) {
		
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", ICCardInfoModifyReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}

}
