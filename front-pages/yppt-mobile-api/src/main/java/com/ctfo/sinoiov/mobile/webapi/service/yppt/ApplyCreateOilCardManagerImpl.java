package com.ctfo.sinoiov.mobile.webapi.service.yppt;


import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.beans.SimpleCode;
import com.ctfo.csm.uaa.dao.beans.UAAUser;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IMobileAppMongoService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.ApplyCreateOilCardReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.ApplyCreateOilCardRsp;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.BeanUtil;
import com.ctfo.sinoiov.mobile.webapi.util.CheckValue;
import com.ctfo.sinoiov.mobile.webapi.util.CommonUtil;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.ICCardError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.SimpleCodeError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.yppt.baseservice.card.beans.ICCardApply;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyDetail;
import com.ctfo.yppt.bean.ICCardApplyMetaBean;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：开卡申请
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
@Service("applyCreateOilCardManagerImpl")
public class ApplyCreateOilCardManagerImpl implements IJsonService {
	
	//打日志信息
	protected static final Log logger = LogFactory.getLog(ApplyCreateOilCardManagerImpl.class);
	
	@Autowired(required=false)
	private IMobileAppMongoService mobileAppMongoService;
	
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		
		ApplyCreateOilCardRsp applyCreateOilCardRsp = new ApplyCreateOilCardRsp();
		ApplyCreateOilCardReq oilCardReq = (ApplyCreateOilCardReq) request.getBody();
		String[] fileNames = mobileAppMongoService.saveFiles(obj);		//新生成的文件名
		
		Map<String,String[]> map = CommonUtil.getFiledValue(oilCardReq.getImageClass(), fileNames);
	  
		
		logger.debug("手机APP客户端调用【开卡申请（Y300006）】接口，用户ID：" + oilCardReq.getUserId());
		try {
			ICCardApplyMetaBean cardApply = new ICCardApplyMetaBean();
			//开卡申请的请求类 添加
			ICCardApply icCardApply = new ICCardApply();
			logger.info(">>>>>>>>>>>>>>>>下面进行数据转换<<<<<<<<<<<<<<<<<<");
			//同步自己定义的bean和数据库中bean
			BeanUtil.copyPropertiesWithTypeCast(oilCardReq, icCardApply);
			logger.info(">>>>>>>>>>>>>>>>数据转换成完<<<<<<<<<<<<<<<<<<");
			logger.info(">>>>>>>>>>>>>>>>用户ID：["+oilCardReq.getUserId()+"]<<<<<<<<<<<<<<<<<<");
			icCardApply.setUserId(oilCardReq.getUserId());
			
			icCardApply.setStatus(PublicStaticParam.IC_CARD_APPLY_STATUS);
			icCardApply.setDataSource(PublicStaticParam.DATA_SOURCE);
			icCardApply.setApplyType(PublicStaticParam.IC_CARD_APPLY_TYPE);
			icCardApply.setCreateTime(System.currentTimeMillis());
			icCardApply.setUserRegPhone(oilCardReq.getMobile());
			icCardApply.setApplyUserName(oilCardReq.getUserName());
			String areas[] = oilCardReq.getCommonArea().split(",");
			String oftenArea = "";
			for(int i =0;i<areas.length;i++){
				String s =areas[i];
				if(i>0 && i<areas.length){
					oftenArea += ",";
				}
				oftenArea += PublicStaticParam.toProvince(s);
			}
			icCardApply.setOftenArea(oftenArea);
			icCardApply.setProvince(PublicStaticParam.toProvince(oilCardReq.getProvince()));
			icCardApply.setCity(PublicStaticParam.toCity(oilCardReq.getCity()));
			List<ICCardApplyDetail> listCardDetail = new ArrayList<ICCardApplyDetail>();
			
			ICCardApplyDetail detail = new ICCardApplyDetail();
			detail.setAcceptMessage(oilCardReq.getAcceptMessage()+"");
			detail.setVehicleNo(oilCardReq.getVehicleNum());
			detail.setVehicleColor(oilCardReq.getVehicleColor());
			detail.setPhoneNum(oilCardReq.getTelNum());
			detail.setCardType(oilCardReq.getCardType());
			logger.info(">>>>>>>>>>>>>>>>获取上传图片文件名<<<<<<<<<<<<<<<<<<");
			detail.setVehicleLicense(CommonUtil.getPicValueToString(map,"vehicleLicense"));
			logger.info(">>>>>>>>>>>>>>>>图片文件名获取成功<<<<<<<<<<<<<<<<<<");
			listCardDetail.add(detail);
			cardApply.setiCCardApplyDetail(listCardDetail);
			icCardApply.setCardNum(listCardDetail.size());
			cardApply.setiCCardApply(icCardApply);
			logger.info(">>>>>>>>>>>>>>>>调用开卡申请服务[开始时间："+new Date()+"]<<<<<<<<<<<<<<<<<<");
			String id = ImplementationSupport.getICCardApplyManager().addIcCardApply(cardApply);
			logger.info(">>>>>>>>>>>>>>>>调用开卡申请服务[结束时间："+new Date()+"]<<<<<<<<<返回ID为<<<<<<<<<"+id);
			if(StringUtils.isNotBlank(id)){
				applyCreateOilCardRsp.setResult(0);
			}else{
				applyCreateOilCardRsp.setResult(1);
				applyCreateOilCardRsp.setMsg("IC卡申请失败");
			}
		} catch (Exception e) {
			//shibai 
			applyCreateOilCardRsp.setResult(1);
			applyCreateOilCardRsp.setMsg("IC卡申请失败");
			//TODO logger 日志
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			logger.error("IC卡申请失败", e);
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		
		return applyCreateOilCardRsp;
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		ApplyCreateOilCardReq req = (ApplyCreateOilCardReq) request.getBody();
		UAAUser user = null;
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E120002",UserError.E120002);
		}else{
			//通过用户ID，验证该用户是否有效
			user = ImplementationSupport.getUserManager().queryUaaUserById(req.getUserId(), null);
			if(user == null){
				throw new ClientException("E120011", UserError.E120011);
			}
		}
		if(StringUtils.isBlank(req.getMobile())){
			throw new ClientException("E140003",ICCardError.E140003);
		}/*else{
			if(user != null && !req.getMobile().equals(user.getUserLogin())){
				throw new ClientException("E120017", UserError.E120017);
			}
		}*/
		if(StringUtils.isBlank(req.getCardType())){
			throw new ClientException("E140002",ICCardError.E140002);
		}else{
			try {
				SimpleCode code = ImplementationSupport.getSimpleCodeManager().getSimpleCodeByTypeAndCode("IC-CARD-TYPE", req.getCardType());
				if(code == null){
					throw new ClientException("E210004", SimpleCodeError.E210004);
				}
			} catch (Exception e) {
				throw new ClientException("E210005", SimpleCodeError.E210005);
			}
		}
			
		if(!CheckValue.isPhone(req.getTelNum())){
			throw new ClientException("E140003",ICCardError.E140003);
		}
		if(!CheckValue.checkIDCard(req.getIdentityCard())){
			logger.info(">>>>>>>>>>>>>身份证号不正确<<<<<<<<<<<<<<<<");
			throw new ClientException("E120018",UserError.E120018);
		}
		if(StringUtils.isBlank(req.getProvince())){
			logger.info(">>>>>>>>>>>>>邮寄省份为空<<<<<<<<<<<<<<<<");
			throw new ClientException("E000014",Common.E000014);
		}
		if(StringUtils.isBlank(req.getCity())){
			logger.info(">>>>>>>>>>>>>邮寄城市为空<<<<<<<<<<<<<<<<");
			throw new ClientException("E000014",Common.E000014);
		}
		if(StringUtils.isBlank(req.getCounty())){
			logger.info(">>>>>>>>>>>>>邮寄城区为空<<<<<<<<<<<<<<<<");
			throw new ClientException("E000014",Common.E000014);
		}
		if(StringUtils.isBlank(req.getCommonArea())){
			logger.info(">>>>>>>>>>>>>卡常用地区为空<<<<<<<<<<<<<<<<");
			throw new ClientException("E000014",Common.E000014);
		}
		if(StringUtils.isBlank(req.getUserName())){
			logger.info(">>>>>>>>>>>>>修改人名称为空<<<<<<<<<<<<<<<<");
			throw new ClientException("E000014",Common.E000014);
		}
	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", ApplyCreateOilCardReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}

}
