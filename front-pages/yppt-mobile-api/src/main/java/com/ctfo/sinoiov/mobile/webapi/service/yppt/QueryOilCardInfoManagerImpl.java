package com.ctfo.sinoiov.mobile.webapi.service.yppt;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctfo.sinoiov.mobile.webapi.base.intf.ICodeService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryOilCardInfoReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.QueryOilCardInfoRsp;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.BeanUtil;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.AccountError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.sinoiov.mobile.webapi.util.Zone_Util;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.system.beans.CardStat;
import com.ctfo.yppt.baseservice.system.beans.CardStatExampleExtended;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：油卡详情查询
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
@Service("queryOilCardInfoManagerImpl")
public class QueryOilCardInfoManagerImpl implements IJsonService {

	protected static final Log logger = LogFactory.getLog(QueryOilCardInfoManagerImpl.class);
	
	@Autowired
	private ICodeService codeService;
	
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		logger.info(">>>>>>>>>>>>>>根据卡ID查询IC卡详情<<<<<<<<<<<<<<<<<<<");
		QueryOilCardInfoRsp oilCardVo =  new QueryOilCardInfoRsp();
		
		try {
			QueryOilCardInfoReq oilCardInfoReq = (QueryOilCardInfoReq) request.getBody();
			logger.info(">>>>>>>>>>>>>>IC卡ID为["+oilCardInfoReq.getCardId()+"]<<<<<<<<<<<<<<<<<<<");
			//数据库中的bean
			ICCard icCard1 = new ICCard();
			icCard1.setId(oilCardInfoReq.getCardId());
			logger.info(">>>>>>>>>>>>>>调用baseService查询IC卡信息[开始时间："+new Date()+"]<<<<<<<<<<<<<<<<<<<");
			ICCard icCard = (ICCard)ImplementationSupport.getICCardManager().getById(icCard1);
			logger.info(">>>>>>>>>>>>>>调用baseService查询IC卡信息[结束时间："+new Date()+"]<<<<<<<<<<<<<<<<<<<");
			if(icCard == null){
				logger.info(">>>>>>>>>>>>>>查询IC卡结果为空<<<<<<<<<<<<<<<<<<<");
				return null;
			}
			//前台页面的bean
			BeanUtil.copyPropertiesWithTypeCast(icCard, oilCardVo);
			//TODO icCard.getCardType() 从码表获取
			logger.info(">>>>>>>>>>>>>>从码表获取IC卡类型，code为："+icCard.getCardType()+"<<<<<<<<<<<<<<<<<<<");
			oilCardVo.setCardType(codeService.queryCodeNameByCode(PublicStaticParam.IC_CARD_TYPE, icCard.getCardType()));
			logger.info(">>>>>>>>>>>>>>从码表获取IC卡类型："+oilCardVo.getCardType()+"<<<<<<<<<<<<<<<<<<<");
			logger.info(">>>>>>>>>>>>>>从码表获取车牌颜色，code为："+icCard.getVehicleColor()+"<<<<<<<<<<<<<<<<<<<");
			oilCardVo.setVehicleColor(codeService.queryCodeNameByCode(PublicStaticParam.VEHICLE_COLOR, icCard.getVehicleColor()));//服务端设置车辆颜色的值
			logger.info(">>>>>>>>>>>>>>从码表获取车牌颜色，code为："+oilCardVo.getVehicleColor()+"<<<<<<<<<<<<<<<<<<<");
			oilCardVo.setCardState("正常");//卡片状态 //TODO 暂时写死均为可用
			oilCardVo.setCardNum(icCard.getCardNumber());
			oilCardVo.setId(icCard.getId());
			if(null!=icCard.getBalance()){
				logger.info(">>>>>>>>>>>>>>查询出IC卡余额："+icCard.getBalance()+"<<<<<<<<<<<<<<<<<<<");
				BigDecimal balance = PublicStaticParam.fen2Yuan(icCard.getBalance());
				oilCardVo.setBalance(""+balance);
				logger.info(">>>>>>>>>>>>>>除以100转换后IC卡余额："+oilCardVo.getBalance()+"<<<<<<<<<<<<<<<<<<<");
			}
			oilCardVo.setCardDept(Zone_Util.converterZoneToAPP(icCard.getCardAreaCode()));
			//TODO 添加新的上月加油  累计加油 字段 iccard get不到
			CardStatExampleExtended cardEE = new CardStatExampleExtended();
			cardEE.createCriteria().andCardIdEqualTo(icCard.getId());
			logger.info(">>>>>>>>>>>>>>调用baseService查询IC卡统计（加油升、加油金额）[开始时间："+new Date()+"]<<<<<<<<<<<<<<<<<<<");
			CardStat csList = ImplementationSupport.getICCardStatManager().getIICCardStatByCardId(icCard.getId());
			logger.info(">>>>>>>>>>>>>>调用baseService查询IC卡统计（加油升、加油金额）[结束时间："+new Date()+"]<<<<<<<<<<<<<<<<<<<");
			if(csList != null){
				//CardStat cs = (CardStat)csList.get(0);
				logger.info(">>>>>>>>>>>>>>累计加油升数["+csList.getOilNum()+"],累计加油金额["+csList.getComsumeMoney()+"]<<<<<<<<<<<<<<<<<<<");
				logger.info(">>>>>>>>>>>>>>上月加油升数["+csList.getLastMonthOilNum()+"],上月加油金额["+csList.getLastMonthComsumeMoney()+"]<<<<<<<<<<<<<<<<<<<");
				oilCardVo.setTotalOilingL(csList.getOilNum()== null ?""+ 0 : ""+PublicStaticParam.fen2Yuan(csList.getLastMonthOilNum()).doubleValue());
				oilCardVo.setTotalOilingY(csList.getComsumeMoney()== null ?""+ 0 : ""+PublicStaticParam.fen2Yuan(csList.getLastMonthComsumeMoney()).doubleValue());
				logger.info(">>>>>>>>>>>>>>转换后：上月加油升数["+oilCardVo.getTotalOilingL()+"],上月加油金额["+oilCardVo.getTotalOilingY()+"]<<<<<<<<<<<<<<<<<<<");
			}
			
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			logger.error("查询油卡详情：", e);
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			throw new ClientException("查询卡详情失败！", e);
		}
		logger.info(">>>>>>>>>>>>>>调用baseService查询IC卡详情结束<<<<<<<<<<<<<<<<<<<");
		return oilCardVo;
		
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		QueryOilCardInfoReq req = (QueryOilCardInfoReq) request.getBody();
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E120002",UserError.E120002);
		}
		if(StringUtils.isBlank(req.getCardId())){
			throw new ClientException("E120016",AccountError.E150006);
		}
	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", QueryOilCardInfoReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}

	public ICodeService getCodeService() {
		return codeService;
	}
	
	@Resource(name="codeService", description="码表服务service")
	public void setCodeService(ICodeService codeService) {
		this.codeService = codeService;
	}

}
