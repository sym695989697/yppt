package com.ctfo.sinoiov.mobile.webapi.service.yppt;


import java.math.BigDecimal;
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

import com.ctfo.base.service.beans.SimpleCode;
import com.ctfo.base.service.beans.SimpleCodeExampleExtended;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.MainDataReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.ImageBean;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.MainDataRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.CardTypeVo;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.PropertyImageUtils;
import com.ctfo.sinoiov.mobile.webapi.util.PropertyUtils;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.yppt.baseservice.system.beans.UserStatExampleExtended;
import com.ctfo.yppt.bean.UserStatBean;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：首页
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



@Service("mainDataManagerImpl")
public class MainDataManagerImpl implements IJsonService {
	
	protected static final Log logger = LogFactory.getLog(MainDataManagerImpl.class);
	
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		MainDataRsp mainDataRsp = new MainDataRsp();
		MainDataReq req = (MainDataReq) request.getBody();
		Head head = request.getHead();
		logger.debug("手机APP客户端调用【查询首页数据（Y300001）】接口，用户ID：" + req.getUserId());
		
		try {
			if(StringUtils.isNotBlank(req.getUserId())){
			
				logger.info(">>>>>>>>>>>>>>>调用后台服务，用户ID["+req.getUserId()+"]开始时间["+new Date()+"]<<<<<<<<<<<<<<<<<<<");
				UserStatBean userBean = ImplementationSupport.getUserStatManager().getUserStat(req.getUserId());
				logger.info(">>>>>>>>>>>>>>>调用后台服务，用户ID["+req.getUserId()+"]结束时间["+new Date()+"]<<<<<<<<<<<<<<<<<<<");
				if(userBean != null){
					logger.info(">>>>>>>>>>>>>>>>>>>>>>>首页数据转换<<<<<<<<<<<<<<");
					mainDataRsp.setTotalBackCoinCount(""+userBean.getGoldCoinNum());
					mainDataRsp.setTotalOilingL(PublicStaticParam.fen2Yuan(userBean.getOilNum()).toString());
					mainDataRsp.setTotalOilingY(PublicStaticParam.fen2Yuan(userBean.getConsumeMoney()).toString());
				
					if(StringUtils.isNotBlank(userBean.getLastMonthRebateCoinNum()+"")){
						mainDataRsp.setLastMonthBackCoinCount(userBean.getLastMonthRebateCoinNum()+"");  
					}else{
						mainDataRsp.setLastMonthBackCoinCount("0");
					}
					if(userBean.getGoldCoinNum() != null){
						mainDataRsp.setTotalBackCoinCount(userBean.getGoldCoinNum().toString());
					}else{
						mainDataRsp.setTotalBackCoinCount("0");
					}
			    	mainDataRsp.setLastMonthOilingL(PublicStaticParam.fen2Yuan(userBean.getLastMonthOilNum()).toString());
			    	mainDataRsp.setLastMonthOilingY(PublicStaticParam.fen2Yuan(userBean.getLastMonthComsumeMoney()).toString());
			    	mainDataRsp.setCardCount(userBean.getApplyCardCount()+userBean.getUsedCardCount());
			    	mainDataRsp.setApplyCardCount(userBean.getApplyCardCount()+"");
			    	mainDataRsp.setApplyCardFailCount(userBean.getApplyCardFailCount()+"");
			    	BigDecimal cardBalance = ImplementationSupport.getICCardManager().getCardsBalanceByUserId(req.getUserId());
			    	mainDataRsp.setCardBalance(""+PublicStaticParam.fen2Yuan(cardBalance));
			    	
			    	
			    	logger.info(">>>>>>>>>>>>>>>>>>>>>>>首页数据转换 结束<<<<<<<<<<<<<<");
				}else{
					logger.info(">>>>>>>>>>>>>>>>>>>>>>>首页记录为空<<<<<<<<<<<<<<");
				}
			}else{
				logger.info(">>>>>>>>>>>>>>>>>>>>>>>用户ID为空<<<<<<<<<<<<<<");
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>");
			logger.error("查询数据有误", e);
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>");
			throw new ClientException("通过UserId查询首页数据失败 userId="+req.getUserId(),e);
		}
		List<ImageBean> imageList = new ArrayList<ImageBean>();
		
		/************获取图片***************/
		int imageCount = Integer.parseInt(PropertyImageUtils.getValueByKey("IMAGE_DEFAULT_IMAGE_COUNT"));
		for(int i=0;i<imageCount;i++){
			ImageBean image = new ImageBean();
			image.setUrl(PropertyImageUtils.getUrlByKey("IMAGE_DEFAULT_"+(i+1)));
			imageList.add(image);
		}
		mainDataRsp.setImageUrl(imageList);
		mainDataRsp.setCoinIntroduceUrl(PropertyImageUtils.getUrlByKey("COIN_INTRODUCE_URL"));
		mainDataRsp.setCardFavorableUrl(PropertyImageUtils.getUrlByKey("IMAGE_DEFAULT_PREFERENTIAL"));
		
		return mainDataRsp;
		
		
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		/*MainDataReq req = (MainDataReq) request.getBody();
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E120002",UserError.E120002);
		}*/

	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", MainDataReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}
}


