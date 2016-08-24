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

import com.ctfo.common.models.PaginationResult;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam.PageValue;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryOilCardListReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.QueryOilCardListRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.OilCardVo;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.sinoiov.mobile.webapi.util.PropertyImageUtils;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.beans.ICCardExampleExtended;
import com.ctfo.yppt.bean.ICCardApplyDetailExtend;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：油卡列表（申请中、申请成功、申请失败）
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
@Service("queryOilCardListManagerImpl")
public class QueryOilCardListManagerImpl implements IJsonService {

	// 日志信息
	protected static final Log logger = LogFactory
			.getLog(QueryOilCardListManagerImpl.class);

	
	

	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		QueryOilCardListRsp cardListRsps = null;
		try{
			logger.info(">>>>>>>>>>>>>>>查询IC卡列表开始<<<<<<<<<<<<<<<<<<<");
			
			cardListRsps = new QueryOilCardListRsp();
			QueryOilCardListReq oilCardListReq = (QueryOilCardListReq) request.getBody();
			logger.debug("手机APP客户端调用【查询油卡列表（Y300003）】接口，用户ID："+ oilCardListReq.getUserId());
			logger.info(">>>>>>>>>>>>>>>查询IC卡列表参数，用户ID["+oilCardListReq.getUserId()+"]、页数["+oilCardListReq.getPage()+"]、每页记录数["+oilCardListReq.getPageSize()+"]<<<<<<<<<<<<<<<<<<<");
			if("1".equals(oilCardListReq.getApplyType())){
				//0为待审核，1为审核通过，2为审核不通过
				cardListRsps = getCardInfo(oilCardListReq);
			}else{
				cardListRsps = getCardAll(oilCardListReq);
			}
		
		
		}catch(Exception e){
			logger.info(">>>>>>>>>>>>>>>查询卡列表失败<<<<<<<<<<<<<<<<<<<");
			throw new ClientException("查询卡列表失败",e);
		}
		return cardListRsps;
	}

	@Override
	public void validate(Parameter request) throws ClientException {

		if (request == null || request.getBody() == null) {
			throw new ClientException("E120001", UserError.E120001);
		}
		QueryOilCardListReq req = (QueryOilCardListReq) request.getBody();
		if (StringUtils.isBlank(req.getUserId())) {
			throw new ClientException("E120002", UserError.E120002);
		}
		if(req.getPage() == 0){
			req.setPage(PageValue.page);
		}
		if(req.getPageSize() == 0){
			req.setPageSize(PageValue.pageSize);
		}
	}

	@Override
	public Parameter convertParameter(String request) {
		try {
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
			classMap.put("head", Head.class);
			classMap.put("body", QueryOilCardListReq.class);
			Parameter param = (Parameter) JSONObject.toBean(
					JSONObject.fromObject(request), Parameter.class, classMap);
			return param;
		} catch (Exception e) {
			throw new ClientException("E000005", Common.E000005);
		}
	}

	/**
	 * 正常
	 * @param req
	 * @return
	 */
	public QueryOilCardListRsp getCardInfo(QueryOilCardListReq req){
		QueryOilCardListRsp response = new QueryOilCardListRsp();
		try {
			
			ICCardExampleExtended icCardExample = new ICCardExampleExtended();
			ICCardExampleExtended.Criteria cardCriteria = icCardExample.createCriteria();
			cardCriteria.andUserIdEqualTo(req.getUserId());
			if(StringUtils.isNotBlank(req.getQueryKey())){
				cardCriteria.andCardNumberLike("%"+req.getQueryKey()+"%");
				
				ICCardExampleExtended icCardExample1 = new ICCardExampleExtended();
				ICCardExampleExtended.Criteria cardCriteria1 = icCardExample1.createCriteria();
				cardCriteria1.andUserIdEqualTo(req.getUserId());
				cardCriteria1.andVehicleNoLike("%"+req.getQueryKey()+"%");
				icCardExample.or(cardCriteria1);
			}
			icCardExample.setEndNum(req.getPage()-1);
			icCardExample.setLimitNum(req.getPageSize());
			logger.info(">>>>>>>>>>>>>>>调用后台服务开始，开始时间:["+new Date()+"]<<<<<<<<<<<<<<<<<<<");
			PaginationResult<ICCard> cardList = ImplementationSupport.getICCardManager().paginate(icCardExample);
			logger.info(">>>>>>>>>>>>>>>调用后台服务开始，开始时间:["+new Date()+"]<<<<<<<<<<<<<<<<<<<");
			if(cardList != null && cardList.getData() != null && cardList.getData().size()>0){
				logger.info(">>>>>>>>>>>>>>>查询记录["+cardList.getData().size()+"]条<<<<<<<<<<<<<<<<<<<");
				List<OilCardVo> list = new ArrayList<OilCardVo>();
				for(ICCard card:cardList.getData()){
					OilCardVo cardVo = new OilCardVo();
					cardVo.setId(card.getId());
					if(card.getBalance() != null){
						cardVo.setBalance(PublicStaticParam.fen2Yuan(card.getBalance()).doubleValue());
					}
					cardVo.setCardNum(card.getCardNumber());
					String status = "0";	//审核中
					if("08".equals(card.getState())){
						status = "1";	//成功
					}else if("10".equals(card.getState())){
						status = "2";	//失败
					}
					cardVo.setCardState(status);
					cardVo.setCreateCardTime(card.getCreatedTime()+"");
					cardVo.setVehicleNum(card.getVehicleNo());
					cardVo.setFailureDetails("");
					cardVo.setAuditTime(card.getModifiedTime()+"");
					if (StringUtils.isNotBlank(card.getCardType()) && PublicStaticParam.IC_CARD_TYPE_2.equals(card.getCardType())){
						//石化
						cardVo.setCardImg(PropertyImageUtils.getUrlByKey("IMAGE_OIL_LIST_CARD_ZSH"));
					}else{
						cardVo.setCardImg(PropertyImageUtils.getUrlByKey("IMAGE_OIL_LIST_CARD_ZSY"));
					}
					
					list.add(cardVo);
				}
				response.setList(list);
			}
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>>调用后台服务开始，开始时间:["+new Date()+"]<<<<<<<<<<<<<<<<<<<");
			throw new ClientException("查询正常卡失败",e);
		}
		
		
		return response;
	}
	
	/**
	 * 全部
	 * @param oilCardListReq
	 * @return
	 */
	public QueryOilCardListRsp getCardAll(QueryOilCardListReq oilCardListReq){
		QueryOilCardListRsp cardListRsps = new QueryOilCardListRsp();
	
		try{
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("userId", oilCardListReq.getUserId());
			if(StringUtils.isNotBlank(oilCardListReq.getQueryKey())){
				param.put("condition", oilCardListReq.getQueryKey());
			}
			/*if(StringUtils.isNotBlank(oilCardListReq.getApplyType())){
				String value = ICCardCons.APPLY_AUDIT_STATE_NOT_PASS;	//不通过
				if("1".equals(oilCardListReq.getApplyType())){
					//0为待审核，1为审核通过，2为审核不通过
					value = ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_PASS;	//通过
					param.put("cardStateIn", Arrays.asList(new String[]{value}));
				}else{
					if("0".equals(oilCardListReq.getApplyType())){
						param.put("cardStateNotIn", Arrays.asList(new String[]{ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_PASS,ICCardCons.APPLY_AUDIT_STATE_NOT_PASS}));
					}else{
						param.put("cardStateIn", Arrays.asList(new String[]{value}));
					}
					
				} 
				
			}*/
			
			param.put("start", ((oilCardListReq.getPage()-1)*oilCardListReq.getPageSize())+"");
			param.put("limit", oilCardListReq.getPageSize()+"");
			
			// 分页查询
			logger.info(">>>>>>>>>>>>>>>调用后台服务开始，开始时间:["+new Date()+"]<<<<<<<<<<<<<<<<<<<");
			PaginationResult<ICCardApplyDetailExtend> iICardList = ImplementationSupport.getICCardApplyManager().queryUserAplly(param);//.queryUserAllAplly(param);//iCCardManager.queryICardAndApply(oilCardListReq.getQueryKey(), oilCardListReq.getUserId(),oilCardListReq.getPage(),oilCardListReq.getPageSize(),oilCardListReq.getApplyType());
			logger.info(">>>>>>>>>>>>>>>调用后台服务结束，结束时间:["+new Date()+"]<<<<<<<<<<<<<<<<<<<");
			if(iICardList != null && iICardList.getData() != null && iICardList.getData().size()>0){
				logger.info(">>>>>>>>>>>>>>>查询记录["+iICardList.getData().size()+"]条<<<<<<<<<<<<<<<<<<<");
				List<OilCardVo> list = new ArrayList<OilCardVo>();
				for(ICCardApplyDetailExtend cardApply:iICardList.getData()){
					OilCardVo cardVo = new OilCardVo();
					cardVo.setId(cardApply.getCardId());
					if(cardApply.getBalance() != null){
						cardVo.setBalance(PublicStaticParam.fen2Yuan(cardApply.getBalance()).doubleValue());
					}
					cardVo.setCardNum(cardApply.getCardNum());
					String status = "0";	//审核中
					if("08".equals(cardApply.getState())){
						status = "1";	//成功
					}else if("10".equals(cardApply.getState())){
						status = "2";	//失败
					}
					cardVo.setCardState(status);
					cardVo.setCreateCardTime(cardApply.getCreateTime()+"");
					cardVo.setVehicleNum(cardApply.getVehicleNo());
					cardVo.setFailureDetails(cardApply.getReason());
					cardVo.setAuditTime(cardApply.getAuditTime()+"");
					if (StringUtils.isNotBlank(cardApply.getCardType()) && PublicStaticParam.IC_CARD_TYPE_2.equals(cardApply.getCardType())){
						//石化
						cardVo.setCardImg(PropertyImageUtils.getUrlByKey("IMAGE_OIL_LIST_CARD_ZSH"));
					}else{
						cardVo.setCardImg(PropertyImageUtils.getUrlByKey("IMAGE_OIL_LIST_CARD_ZSY"));
					}
					
					list.add(cardVo);
				}
				cardListRsps.setList(list);
				

		}else{
			logger.info(">>>>>>>>>>>>>>>查询IC卡无记录数据<<<<<<<<<<<<<<<<<<<");
		}
		
		
		cardListRsps.setTotalNum(iICardList.getTotal());
	} catch (Exception e) {
		logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		logger.error("查询油卡列表失败：", e);
		logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		throw new ClientException("查询油卡列表失败", e);
	}
	return cardListRsps;
	}

}
