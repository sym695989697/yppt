package com.ctfo.sinoiov.mobile.webapi.service.yppt;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctfo.csm.uaa.dao.beans.UAAUser;
import com.ctfo.gatewayservice.interfaces.service.ICreditService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IMobileAppMongoService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.RechargeApplyReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.RechargeApplyRsp;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.AccountError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.ICCardError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.MoneyError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.PayerError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApply;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyDetail;
import com.ctfo.yppt.baseservice.recharge.intf.IICRechargeApplyManager;
import com.ctfo.yppt.bean.ICCardRechageApplyExtend;
import com.ctfo.yppt.bean.ICCardRechageApplyMetaBean;

/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：卡充值申请管理
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

@Service("rechargeApplyManagerImpl")
public class RechargeApplyManagerImpl implements IJsonService {
	//日志信息
	protected static final Log logger = LogFactory.getLog(RechargeApplyManagerImpl.class);
	
	@Autowired(required=false)
	private IMobileAppMongoService mobileAppMongoService;
	
	UAAUser user = null;
	
	/* (non-Javadoc)
	 * @see com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService#execute(com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter, java.lang.Object[])
	 */
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		
		RechargeApplyRsp rechargeApplyRsp = new RechargeApplyRsp();
		logger.info("*********************生成业务订单号开始*********************");
		try{
			
			RechargeApplyReq req = (RechargeApplyReq) request.getBody();//页面请求充值的bean
			String coinCount = req.getCoinCount();

			if(StringUtils.isBlank(coinCount) && new BigDecimal(coinCount).longValue()<0){
				rechargeApplyRsp.setResult("1");
				rechargeApplyRsp.setMsg("使用旺金币值为负数，不合法");
				return rechargeApplyRsp;
			}
			
			//后台服务
			IICRechargeApplyManager iicCardRechargeManager = null;
			try {
				iicCardRechargeManager = ImplementationSupport.getICRechargeApplyManager();
			} catch (Exception e) {
				logger.error("***********获取充值申请服务接口失败！！！！");
				e.printStackTrace();
			}
			
			if(iicCardRechargeManager == null){
				rechargeApplyRsp.setResult("1");
				rechargeApplyRsp.setMsg("服务内部错误，获取充值申请服务接口失败！！！！");
				return rechargeApplyRsp;
			}
			
			//如果选择使用旺金币支付校验旺金币账户数量
			if(new BigDecimal(coinCount).longValue() > 0){
				//获取用户统计相关信息
				ICreditService creditService = null;
				try {
					creditService = ImplementationSupport.getICreditService();
				} catch (Exception e) {
					logger.error("****************获取用户统计服务接口（iUserStatManager）失败！！！");
					e.printStackTrace();
				}
				if(creditService == null){
					rechargeApplyRsp.setResult("1");
					rechargeApplyRsp.setMsg("服务内部错误，获取用户统计服务接口失败！！！！");
					return rechargeApplyRsp;
				}
				//查询用户旺金币数量
				Long goldCoinNum = creditService.queryBalance(req.getUserId());
				if(goldCoinNum != null){
					if(new BigDecimal(coinCount).longValue() > goldCoinNum){
						rechargeApplyRsp.setResult("1");
						logger.info("***************当前使用旺金币数量："+coinCount);
						logger.info("***************当前账户可用旺金币数量："+goldCoinNum);
						rechargeApplyRsp.setMsg("旺金币账户余额不足！");
						return rechargeApplyRsp;
					}
				}
			}
			
			ICCardRechageApplyMetaBean rechargeApplyBean = new ICCardRechageApplyMetaBean();
			ICRechargeApply rechargeApply = new ICRechargeApply();
			//数据库中钱以分进行保存，本服务中的钱全部乘100对元进行换算
			rechargeApply.setCardNum(Integer.parseInt(StringUtils.isBlank(req.getCardNum())==true?"0":req.getCardNum().trim()));//必填
			
			rechargeApply.setTotalMoney(PublicStaticParam.yuan2Fen(new BigDecimal(req.getTotalMoney())));//必填//本次充值总金额（旺金币抵扣金额+应付金额）
			logger.info("**************本次充值总金额："+rechargeApply.getTotalMoney());
			rechargeApply.setCaptialMoney(PublicStaticParam.yuan2Fen(new BigDecimal(req.getMoney())));//除旺金币应付金额
			logger.info("**************本次充值应付金额："+rechargeApply.getCaptialMoney());
			
			rechargeApply.setCreditMoney(new BigDecimal(StringUtils.isBlank(req.getCoinCount())==true?"0":req.getCoinCount().trim()));//旺金币抵扣金额
			logger.info("**************本次充值旺金币抵扣金额："+rechargeApply.getCreditMoney());
			rechargeApply.setCreditNum(new BigDecimal(req.getCoinCount()));//旺金币数量
			rechargeApply.setDataSource(PublicStaticParam.DATA_SOURCE);//数据来源（手机APP）必填
			Long systemTime=System.currentTimeMillis();
			rechargeApply.setApplyTime(systemTime);
			
			rechargeApply.setUserId(req.getUserId());//必填
			rechargeApply.setApplyPersonId(req.getUserId());//必填
			rechargeApply.setUserName(req.getUserName());
			rechargeApply.setUserLoginName(req.getUserLogin());//必填
			rechargeApply.setUserRegPhone(req.getMobile());//必填
			
			rechargeApplyBean.setRechargeApply(rechargeApply);
			
			List<ICRechargeApplyDetail> rechargeApplyDetails = req.getRechargeApplyDetails();
			List<ICRechargeApplyDetail> tmp_rechargeApplyDetails = new ArrayList<ICRechargeApplyDetail>();
			if(rechargeApplyDetails != null){
				for(ICRechargeApplyDetail rechargeApplyDetail: rechargeApplyDetails){
					rechargeApplyDetail.setMoney(rechargeApplyDetail.getMoney()==null? new BigDecimal(0):(rechargeApplyDetail.getMoney().multiply(new BigDecimal(100))));
					tmp_rechargeApplyDetails.add(rechargeApplyDetail);
				}
			}
			rechargeApplyBean.setRechargeDeatils(req.getRechargeApplyDetails());
			logger.info(">>>>>>>>>>>>>>>>调用生成业务订单号服务[开始时间："+new Date()+"]<<<<<<<<<<<<<<<<<<");
			ICCardRechageApplyExtend rechageApply=iicCardRechargeManager.applyRechargeForApp(rechargeApplyBean);
			if(rechageApply == null){
				rechargeApplyRsp.setResult("1");
				rechargeApplyRsp.setMsg("生成业务订单号失败！调用后台订单号接口返回结果为空！");
			}else if(rechageApply.getRechargeApply() == null){
				rechargeApplyRsp.setResult("1");
				rechargeApplyRsp.setMsg("生成业务订单号失败！调用后台订单号接口返回实体为空！");
			}else if(StringUtils.isBlank(rechageApply.getRechargeApply().getOrderNo())){//业务订单号
				rechargeApplyRsp.setResult("1");
				rechargeApplyRsp.setMsg("生成业务订单号失败！调用后台订单号接口返回业务订单号为空！");
			}else{
				rechargeApplyRsp.setResult("0");
				rechargeApplyRsp.setMsg(rechageApply.getRechargeApply().getOrderNo());//业务单号
				rechargeApplyRsp.setId(rechageApply.getRechargeApply().getId());//业务单号ID
				logger.info(">>>>>>>>>>>>>>>>调用生成业务订单号[结束时间："+new Date()+"]<<<<<<<<<返回业务订单号为<<<<<<<<<"+rechageApply.getRechargeApply().getOrderNo());
			}
		}catch(Exception e){
			logger.error("生成业务订单号失败！", e);
			throw new ClientException("生成业务订单号失败！", e);
		}
		logger.info("*********************生成业务订单号结束*********************");
		
		return rechargeApplyRsp;
	}
	
	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		RechargeApplyReq req = (RechargeApplyReq) request.getBody();
		if(req==null){
			throw new ClientException("E120001",UserError.E120001);
		}
		if(StringUtils.isBlank(req.getUserId())){//UAAID
			throw new ClientException("E120002",UserError.E120002);
		}else{
			//通过用户ID，验证该用户是否有效
			user = ImplementationSupport.getUserManager().queryUaaUserById(req.getUserId(), null);
			if(user == null){
				throw new ClientException("E120011", UserError.E120011);
			}
		}
		
		if(req.getRechargeApplyDetails()==null || req.getRechargeApplyDetails().size()<0){
			throw new ClientException("E140007",ICCardError.E140007);
		}
		
		for(ICRechargeApplyDetail details: req.getRechargeApplyDetails()){
			if(StringUtils.isBlank(details.getCardId())){
				throw new ClientException("E140007",ICCardError.E140007);
			}
		}
		
		if(StringUtils.isBlank(req.getCardNum())){//卡数量
			throw new ClientException("E140006",ICCardError.E140006);
		}
		
		if(StringUtils.isBlank(req.getMoney()+"")){//充值金额
			throw new ClientException("E160001",MoneyError.E160001);
		}
		if(req.getDgrecharge()==1){//设置是否对公转账
			if(StringUtils.isBlank(req.getPayAccountNumber())){//支付账户
				throw new ClientException("E150002",AccountError.E150002);
			}
			if(StringUtils.isBlank(req.getPayee())){//支付人
				throw new ClientException("E150002",AccountError.E150002);
			}
			if(StringUtils.isBlank(req.getPayer())){//收款人
				throw new ClientException("E170001",PayerError.E170001);
			}
			if(StringUtils.isBlank(req.getRemitAmount())){//汇款金额
				throw new ClientException("E160002",MoneyError.E160002);
			}
		}
			
	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", RechargeApplyReq.class);
	        classMap.put("rechargeApplyDetails", ICRechargeApplyDetail.class);

	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}
	
	/*
	private ICCardRechargeMetaBean dataToMetaBean(RechargeApplyReq request,Object...obj) throws ClientException{
		ICCardRechargeMetaBean result = new ICCardRechargeMetaBean();
		try{
			
			String[] fileNames = mobileAppMongoService.saveFiles(obj);		//新生成的文件名
			
			Map<String,String[]> map = CommonUtil.getFiledValue(request.getImageClass(), fileNames);
			
			
			IICCardManager iicCardManager = (IICCardManager) ServiceFactory.getFactory().getService(IICCardManager.class);
			ICCardExampleExtended cardExample = new ICCardExampleExtended();
			cardExample.createCriteria().andIdIn(Arrays.asList(request.getCardId().split(",")));
			PaginationResult icardList = iicCardManager.getICCardListPageByExampleExtended(cardExample);
			Map<String,String> vehicleMap = new HashMap<String,String>();
			
			if(icardList != null && icardList.getData() != null){
				List<ICCard> cardList = (List<ICCard>) icardList.getData();
				for(ICCard card:cardList){
					vehicleMap.put(card.getId(), card.getVehicleNo());
				}
			}
			List<ICRechargeApplyDetail> applyDetailList = null;
			String[] cardIds = request.getCardId().split(",");
			String[] cardNos = request.getCardNum().split(",");
			String[] moneys = request.getMoney().split(",");
			BigDecimal moneySum = new BigDecimal(0);	//总金额
			if(cardIds != null && cardIds.length>0){
				applyDetailList = new ArrayList<ICRechargeApplyDetail>();
				for(int i = 0;i<cardIds.length;i++){
					String cardId = cardIds[i];
					ICRechargeApplyDetail rechargeApplyDetail = new ICRechargeApplyDetail();
					rechargeApplyDetail.setCardId(cardId);
					rechargeApplyDetail.setMoney(new BigDecimal(moneys[i]).multiply(new BigDecimal(MONEY_VALUE)));
					rechargeApplyDetail.setCardNo(cardNos[i]);
					rechargeApplyDetail.setVehicleNo(vehicleMap.get(cardId) == null?"车A01111":vehicleMap.get(cardId));
					applyDetailList.add(rechargeApplyDetail);
					moneySum = new BigDecimal(moneys[i]).multiply(new BigDecimal(100)).add(moneySum);
				}
				result.setRechargeDeatils(applyDetailList);
			}
			
			
			*//**
			 * 充值申请数据
			 *//*
			ICRechargeApply rechargeApply = new ICRechargeApply(); 
			rechargeApply.setTotalMoney(moneySum);	//总金额
			rechargeApply.setCardNum(new BigDecimal(request.getCardNum().split(",").length));	//卡数量
			rechargeApply.setApplyTime(System.currentTimeMillis());	//申请时间
			rechargeApply.setApplyPersonId(request.getUserId());	//申请人ID
			rechargeApply.setMemberId(request.getUserId());	//会员ID
			rechargeApply.setDataSource("1");	//来款方式
			rechargeApply.setRemitter(request.getPayer());	//付款人
			rechargeApply.setRemittanceAccount(request.getPayAccountNumber());	//付款帐号
			//rechargeApply.setFundsArriveMoney(new BigDecimal(request.getRemitAmount()==null?"0":request.getRemitAmount()));
			
	
			if(request.getDgrecharge()==1){
				rechargeApply.setFundsArriveTime(Long.valueOf(request.getPayTime()));
	            rechargeApply.setRemittanceAccount(request.getPayAccountNumber());	
				rechargeApply.setFundsArriveMoney(new BigDecimal(request.getRemitAmount()).multiply(new BigDecimal(MONEY_VALUE)));
			
				ICRechargeTransferDetail transferDetail = new ICRechargeTransferDetail();
				transferDetail.setPayer(request.getPayer());
				transferDetail.setPayeebankNo(request.getPayee());
				transferDetail.setPayBankAccount(request.getPayAccountNumber());
				transferDetail.setPayMoney(new BigDecimal(request.getRemitAmount()).multiply(new BigDecimal(MONEY_VALUE)));//从前台取出乘100
				if(request.getPayTime()!=null){
					transferDetail.setPayDate(Long.valueOf(request.getPayTime()));
				}
				String[] _imageName = map.get("attachId");
				if(_imageName != null && _imageName.length>0){
					transferDetail.setAttachId(_imageName[0]);
				}
				
				
				//选择了对公充值
				List<ICRechargeTransferDetail> listTransferDetail = new ArrayList();
				listTransferDetail.add(transferDetail);
				result.setTransferDetails(listTransferDetail);
			}
			
			//账户余额充值
			if(!String.valueOf(request.getBalance()).equals("0.0")){
				rechargeApply.setCaptialMoney(new BigDecimal(request.getBalance()).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP)); //从前台取出乘100
			}
			// 选择了旺金币
			if (!String.valueOf(request.getCoinCount()).equals("0.0")) {
				rechargeApply.setCreditNum(new BigDecimal(request.getCoinCount()).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP));//从前台取出乘100
			}
			result.setRechargeApply(rechargeApply);
		}catch(Exception e){
			logger.error("数据转换失败", e);
			throw new ClientException("数据转换失败！", e);
		}
		return result;
	}*/

}
