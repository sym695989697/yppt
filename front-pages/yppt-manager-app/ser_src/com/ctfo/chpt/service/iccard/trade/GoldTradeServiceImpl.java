package com.ctfo.chpt.service.iccard.trade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ctfo.chpt.bean.iccard.vo.GoldTradeDetailsVo;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.gatewayservice.interfaces.bean.credit.CreditIO;
import com.ctfo.gatewayservice.interfaces.service.ICreditService;
import com.ctfo.yppt.baseservice.card.beans.ICCardCreditAccountIO;
import com.ctfo.yppt.baseservice.card.beans.ICCardCreditAccountIOExampleExtended;
import com.ctfo.yppt.baseservice.credit.intf.IICCardCreditAccountManager;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApply;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyDetail;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyExampleExtended;
import com.ctfo.yppt.baseservice.recharge.intf.IICRechargeApplyManager;
import com.ctfo.yppt.bean.ICardRechageMetaBean;

/**
 * 旺金币交易记录服务实现
 * 
 * @author jichao
 */
@Service
public class GoldTradeServiceImpl implements IGoldTradeService {

	ICreditService iCreditService;//旺金币 对接接口服务
	IICCardCreditAccountManager iICCardCreditAccountManager;//积分账户
	IICRechargeApplyManager iICRechargeApplyManager;//申请充值
	
	
	public GoldTradeServiceImpl(){
		iCreditService = (ICreditService)ServiceFactory.getFactory().getService(ICreditService.class);
		iICCardCreditAccountManager = (IICCardCreditAccountManager)ServiceFactory.getFactory().getService(IICCardCreditAccountManager.class);
		iICRechargeApplyManager =(IICRechargeApplyManager)ServiceFactory.getFactory().getService(IICRechargeApplyManager.class);
	}
	
	@Override
	public PaginationResult<?> getListPage(Map<String, Object> param) throws Exception {
//		Map<String, String> equalMap = requestParam.getEqual();
//		Map<String, String> startMap = requestParam.getStartwith();
//		Map<String, String> endMap = requestParam.getEndwith();
//		Map<String, Object> parmaMap = new HashMap<String, Object>();
//		if(startMap!=null) parmaMap.put("dealStartTime", startMap.get("tradeTime"));
//		if(endMap!=null) parmaMap.put("dealEndTime", endMap.get("tradeTime"));
//		if(equalMap!=null) parmaMap.put("type", equalMap.get("tradeType"));
//		if(equalMap!=null) parmaMap.put("regPhone", equalMap.get("userRegPhone"));
//		if(equalMap!=null) parmaMap.put("userName", equalMap.get("userName"));
		PaginationResult<CreditIO> list = iCreditService.queryCreditIO(param);
		return list;
	}

	@Override
	public PaginationResult<ICCardCreditAccountIO> getICCardCreditAccountIO(ICCardCreditAccountIOExampleExtended exampleExtended) throws Exception {
		return iICCardCreditAccountManager.paginate(exampleExtended);
	}

	@Override
	public List<ICardRechageMetaBean> getICardRechageMetaBeanListByReasonId(String reasonId) throws Exception {
		List<ICardRechageMetaBean> icardRechageMetaBeanList = new ArrayList<ICardRechageMetaBean>();
		
		//根据USERID获取交易详细信息
		ICRechargeApplyExampleExtended example = new ICRechargeApplyExampleExtended();
		example.createCriteria().andIdEqualTo(reasonId);
		List<ICRechargeApply> list = iICRechargeApplyManager.getList(example);
		
		//根据获申请ID（applayId）取充值信息
		for(ICRechargeApply iCRechargeApply :list){
			String applyId = iCRechargeApply.getId();
			ICardRechageMetaBean iCardRechageMetaBean = iICRechargeApplyManager.getICardRechageMetaBeanByapplyId(applyId);
			icardRechageMetaBeanList.add(iCardRechageMetaBean);
		}
		return icardRechageMetaBeanList;
	}

	@Override
	public List getGoldTradeDetailsVoList(String reasonId) throws Exception{
		//获取卡充值记录
		List<ICardRechageMetaBean> list = getICardRechageMetaBeanListByReasonId(reasonId);
		List goldTradeDetailsVoList = new ArrayList();
		if(list!=null){
			for(ICardRechageMetaBean iCardRechageMetaBean :list){
				//提取 “充值申请表”记录
				ICRechargeApply iCRechargeApply  = iCardRechageMetaBean.getRechargeApply();
				//提取"充值申请详细"记录
				List<ICRechargeApplyDetail> detailsList = iCardRechageMetaBean.getIcRechargeApplyDetails();
				
				if(detailsList!=null&&detailsList.size()>0){
					for(ICRechargeApplyDetail details :detailsList){
						//交易详细
						GoldTradeDetailsVo vo = new GoldTradeDetailsVo();
						String cardNo = details.getCardNo();//卡号
						String vehicleNo = details.getVehicleNo();//车牌号
						vo.setCardNo(cardNo);
						vo.setVehicleNo(vehicleNo);
						vo.setMoney(details.getActualDivMoney()==null?"0.00":details.getActualDivMoney().divide(new BigDecimal("100.00")).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
						vo.setTotalMoney(iCRechargeApply.getTotalMoney()==null?"0.00":iCRechargeApply.getTotalMoney().divide(new BigDecimal("100.00")).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
						vo.setCreditMoney(iCRechargeApply.getCreditMoney()==null?"0.00":iCRechargeApply.getCreditMoney().divide(new BigDecimal("100.00")).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
						vo.setPayWay(iCRechargeApply.getPayWay());
						vo.setCaptialMoney(iCRechargeApply.getCaptialMoney()==null?"0.00":iCRechargeApply.getCaptialMoney().divide(new BigDecimal("100.00")).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
						goldTradeDetailsVoList.add(vo);
					}
				}else{
					GoldTradeDetailsVo vo = new GoldTradeDetailsVo();
					vo.setBalance(iCRechargeApply.getBalance()==null?"0.00":iCRechargeApply.getBalance().divide(new BigDecimal("100.00")).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
					vo.setTotalMoney(iCRechargeApply.getTotalMoney()==null?"0.00":iCRechargeApply.getTotalMoney().divide(new BigDecimal("100.00")).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
					vo.setCreditMoney(iCRechargeApply.getCreditMoney()==null?"0.00":iCRechargeApply.getCreditMoney().divide(new BigDecimal("100.00")).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
					vo.setPayWay(iCRechargeApply.getPayWay());
					vo.setCaptialMoney(iCRechargeApply.getCaptialMoney()==null?"0.00":iCRechargeApply.getCaptialMoney().divide(new BigDecimal("100.00")).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
					goldTradeDetailsVoList.add(vo);
				}
			}
		}
		return goldTradeDetailsVoList;
	}
}
