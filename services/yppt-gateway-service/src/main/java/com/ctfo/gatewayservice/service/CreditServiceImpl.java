package com.ctfo.gatewayservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.chpt.boss.beans.MemberOrg;
import com.ctfo.chpt.boss.beans.MemberOrgExampleExtended;
import com.ctfo.chpt.boss.intf.ICHPTGenericManager;
import com.ctfo.chpt.deal.beans.CreditAccount;
import com.ctfo.chpt.deal.beans.CreditAccountExampleExtended;
import com.ctfo.chpt.deal.beans.CreditBalanceChangeLog;
import com.ctfo.chpt.deal.beans.CreditBalanceChangeLogExampleExtended;
import com.ctfo.chpt.deal.beans.DealCreditOperate;
import com.ctfo.chpt.deal.beans.DealExternalAdjustment;
import com.ctfo.chpt.deal.intf.ICreditAccountCreator;
import com.ctfo.chpt.deal.intf.IDealController;
import com.ctfo.chpt.deal.intf.IDealSystemQuery;
import com.ctfo.chpt.deal.intf.support.IDeal;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.csm.boss.beans.OperatorExampleExtended;
import com.ctfo.csm.intf.support.Paginator;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.gatewayservice.interfaces.bean.YpptGatewayException;
import com.ctfo.gatewayservice.interfaces.bean.credit.CreditIO;
import com.ctfo.gatewayservice.interfaces.service.ICreditService;
import com.ctfo.yppt.baseservice.card.intf.IBaseLocalCreditManager;

/**
 * 与旺金币服务对接
 */
@Service("ICreditService")
public class CreditServiceImpl implements ICreditService {

	private static final Log logger = LogFactory.getLog(CreditServiceImpl.class);
	
	@Override
	public Boolean checkExistCreditAccount(String userId)
			throws YpptGatewayException {
		try {
			if(StringUtils.isBlank(userId)){
				return Boolean.FALSE;
			}
			IDealSystemQuery dealSystemQuery = (IDealSystemQuery) ServiceFactory.soaService(IDealSystemQuery.class);
			CreditAccountExampleExtended creditAccountExampleExtended = new CreditAccountExampleExtended();
			creditAccountExampleExtended.createCriteria().andOwnerIdEqualTo(queryCreditAccountOwningIdByUserId(userId));
			Paginator<Object> paginator = dealSystemQuery.paginateObjs(creditAccountExampleExtended);
			
			if(paginator!=null && paginator.getData() != null && !paginator.getData().isEmpty()){
				return Boolean.TRUE;
			}else{
				return Boolean.FALSE;
			}
		} catch (Exception e) {
			logger.error("查询旺金币账户是否存在发生异常!", e);
			throw new YpptGatewayException("查询旺金币账户是否存在发生异常!", e);
		}
	}
	
	@Override
	public String createCreditAccount(String userId)
			throws YpptGatewayException {
		try {
			if(StringUtils.isBlank(userId)){
				throw new YpptGatewayException("userid为空!");
			}
			logger.info("----------------开始获取积分账户查询服务----------------");
			IDealSystemQuery queryControl = (IDealSystemQuery) ServiceFactory.soaService(IDealSystemQuery.class);
			CreditAccountExampleExtended caExtended = new CreditAccountExampleExtended();
			//车后会员ID
			
			String owningId = queryCreditAccountOwningIdByUserId(userId);
			caExtended.createCriteria().andOwnerIdEqualTo(owningId);
			List<Object> caList = queryControl.getObjsByExampleExtended(caExtended);
			if(caList != null && !caList.isEmpty()) 
				throw new YpptGatewayException("会员已经存在了积分账户:" + userId + "的积分账户!");
			ICreditAccountCreator iCreditAccountCreator = (ICreditAccountCreator) ServiceFactory.soaService(ICreditAccountCreator.class);
			return iCreditAccountCreator.createCreditAccount(owningId, "1");// 1是会员;2是车辆
		} catch (YpptGatewayException e) {
			logger.error("创建旺金币账户发生异常!", e);
			throw e;
		} catch (Exception e) {
			logger.error("创建旺金币账户发生异常!", e);
			throw new YpptGatewayException("创建旺金币账户发生异常!", e);
		}
	}

	@Override
	public Long queryBalance(String userId) throws YpptGatewayException {
		try {
			if(StringUtils.isBlank(userId)){
				logger.error("userid为空!");
				throw new YpptGatewayException("userid为空!");
			}
			IDealSystemQuery dealSystemQuery = (IDealSystemQuery) ServiceFactory.soaService(IDealSystemQuery.class);
			CreditAccountExampleExtended creditAccountExampleExtended = new CreditAccountExampleExtended();
			creditAccountExampleExtended.createCriteria().andOwnerIdEqualTo(queryCreditAccountOwningIdByUserId(userId));
			Paginator<Object> paginator = dealSystemQuery.paginateObjs(creditAccountExampleExtended);
			
			if(paginator!=null && paginator.getData() != null && !paginator.getData().isEmpty()){
				return ((CreditAccount)paginator.getData().get(0)).getBalanceavailable();
			}else{
				throw new YpptGatewayException("userid不存在积分账户!userid:" + userId);
			}
		} catch (YpptGatewayException e) {
			logger.error("查询旺金币余额发生异常!", e);
			throw e;
		} catch (Exception e) {
			logger.error("查询旺金币余额发生异常!", e);
			throw new YpptGatewayException("查询旺金币余额发生异常!", e);
		}
	}

	@Override
	public String freeze(String userId, Long creditNum, String reasonNo)
			throws YpptGatewayException {
		
		try {
			if(StringUtils.isBlank(userId)){
				throw new YpptGatewayException("userid为空!");
			}
			if( creditNum <=0 ){
				throw new YpptGatewayException("creditNum小于零!"+ creditNum);
			}
			IDealSystemQuery queryControl = (IDealSystemQuery) ServiceFactory.soaService(IDealSystemQuery.class);
			CreditAccountExampleExtended caExtended = new CreditAccountExampleExtended();
			//车后会员ID
			String owningId = queryCreditAccountOwningIdByUserId(userId);
			caExtended.createCriteria().andOwnerIdEqualTo(owningId);
			List<Object> caList = queryControl.getObjsByExampleExtended(caExtended);
			if(caList == null || caList.isEmpty()) 
				throw new YpptGatewayException("没有会员:" + userId + "的积分账户!");
			CreditAccount ca = (CreditAccount) caList.get(0);
			
			IDealController dealController = (IDealController) ServiceFactory.soaService(IDealController.class);
			//TODO 冻结积分
			if(creditNum > ca.getBalanceavailable() || ca.getBalanceavailable() <=0){
				throw new YpptGatewayException("会员:" + userId + "的积分账户余额不足!余额为:" + ca.getBalanceavailable() + "\t请求操作积分为: " + creditNum);
			}
			
			DealCreditOperate deal = new DealCreditOperate();
			deal.setDealType(IDeal.DealType_FREEZE);
			deal.setUserId(owningId);
			deal.setAccountId(ca.getId());
			deal.setReasonId(reasonNo);
			deal.setCreditNum(creditNum);
			deal.setMark("油品ICCard交易-积分充值");
			return dealController.submitDeal(deal);
		} catch (YpptGatewayException e) {
			logger.error("冻结旺金币余额发生异常!userId:" + userId + "\tcreditNum:" + creditNum, e);
			throw e;
		} catch (Exception e) {
			logger.error("冻结旺金币余额发生异常!userId:" + userId + "\tcreditNum:" + creditNum, e);
			throw new YpptGatewayException("冻结旺金币余额发生异常!userId:" + userId + "\tcreditNum:" + creditNum, e);
		}
	}

	@Override
	public String unfreeze(String userId, String freezeOrderId) throws YpptGatewayException {
		
		try {
			if(StringUtils.isBlank(freezeOrderId)){
				throw new YpptGatewayException("freezeOrderId为空!");
			}
			IDealSystemQuery queryControl = (IDealSystemQuery) ServiceFactory.soaService(IDealSystemQuery.class);
			CreditBalanceChangeLogExampleExtended caExtended = new CreditBalanceChangeLogExampleExtended();
			caExtended.createCriteria().andReasonIdEqualTo(freezeOrderId);
			
			//车后会员ID
			List<Object> caList = queryControl.getObjsByExampleExtended(caExtended);
			if(caList == null || caList.isEmpty()) 
				throw new YpptGatewayException("不存在冻结订单freezeOrderId:" + freezeOrderId + "!");
			CreditBalanceChangeLog cbcl = (CreditBalanceChangeLog) caList.get(0);
			return unfreeze(userId, cbcl.getDeltaFrozen(), null);
			
		} catch (YpptGatewayException e) {
			logger.error("解冻结旺金币余额发生异常!userId:" + userId + "\t freezeOrderId:" + freezeOrderId, e);
			throw e;
		} catch (Exception e) {
			logger.error("解冻结旺金币余额发生异常!userId:" + userId + "\t freezeOrderId:" + freezeOrderId, e);
			throw new YpptGatewayException("解冻结旺金币余额发生异常!userId:" + userId + "\t freezeOrderId:" + freezeOrderId, e);
		}
	}
	
	@Override
	public String unfreeze(String userId, Long creditNum, String reasonNo) throws YpptGatewayException {
		try {
			if(StringUtils.isBlank(userId)){
				throw new YpptGatewayException("userid为空!");
			}
			if( creditNum <=0 ){
				throw new YpptGatewayException("creditNum小于零!"+ creditNum);
			}
			//车后会员ID
			String owningId = queryCreditAccountOwningIdByUserId(userId);
			
			IDealSystemQuery queryControl = (IDealSystemQuery) ServiceFactory.soaService(IDealSystemQuery.class);
			CreditAccountExampleExtended caExtended = new CreditAccountExampleExtended();
			//车后会员ID
			caExtended.createCriteria().andOwnerIdEqualTo(owningId);
			List<Object> caList = queryControl.getObjsByExampleExtended(caExtended);
			if(caList == null || caList.isEmpty()) 
				throw new Exception("没有会员:" + owningId + "的积分账户!");
			CreditAccount ca = (CreditAccount) caList.get(0);
			IDealController dealController = (IDealController) ServiceFactory.soaService(IDealController.class);
			DealCreditOperate deal = new DealCreditOperate();
			deal.setDealType(IDeal.DealType_UNFREEZE);
			deal.setUserId(owningId);
			deal.setAccountId(ca.getId());
			deal.setCreditNum(creditNum);
			return dealController.submitDeal(deal);
		} catch (YpptGatewayException e) {
			logger.error("解冻结旺金币余额发生异常!userId:" + userId + "\tcreditNum:" + creditNum, e);
			throw e;
		} catch (Exception e) {
			logger.error("解冻结旺金币余额发生异常!userId:" + userId + "\tcreditNum:" + creditNum, e);
			throw new YpptGatewayException("解冻结旺金币余额发生异常!userId:" + userId + "\tcreditNum:" + creditNum, e);
		}		
	}

	@Override
	public String deduct(String userId, Long creditNum, String reasonNo)
			throws YpptGatewayException {
		
		try {
			if(StringUtils.isBlank(userId)){
				throw new YpptGatewayException("userid为空!");
			}
			if( creditNum <=0 ){
				throw new YpptGatewayException("creditNum小于零!"+ creditNum);
			}
			//车后会员ID
			String owningId = queryCreditAccountOwningIdByUserId(userId);
			IDealSystemQuery queryControl = (IDealSystemQuery) ServiceFactory.soaService(IDealSystemQuery.class);
			CreditAccountExampleExtended caExtended = new CreditAccountExampleExtended();
			caExtended.createCriteria().andOwnerIdEqualTo(owningId);
			List<Object> caList = queryControl.getObjsByExampleExtended(caExtended);
			if(caList == null || caList.isEmpty()) 
				throw new Exception("没有会员:" + owningId + "的积分账户!");
			CreditAccount ca = (CreditAccount) caList.get(0);
			IDealController dealController = (IDealController) ServiceFactory.soaService(IDealController.class);
			DealCreditOperate deal = new DealCreditOperate();
			deal.setDealType(IDeal.DealType_OPERATE);
			deal.setUserId(owningId);
			deal.setAccountId(ca.getId());
			deal.setCreditNum(-creditNum);
			return dealController.submitDeal(deal);
		} catch (YpptGatewayException e) {
			logger.error("扣除旺金币余额发生异常!userId:" + userId + "\tcreditNum:" + creditNum, e);
			throw e;
		} catch (Exception e) {
			logger.error("扣除旺金币余额发生异常!userId:" + userId + "\tcreditNum:" + creditNum, e);
			throw new YpptGatewayException("扣除旺金币余额发生异常!userId:" + userId + "\tcreditNum:" + creditNum, e);
		}
	}

	@Override
	public String deductByFreezeOrder(String userId,String freezeOrderId)
			throws YpptGatewayException {
		try {
			if(StringUtils.isBlank(freezeOrderId)){
				throw new YpptGatewayException("freezeOrderId为空!");
			}
			IDealSystemQuery queryControl = (IDealSystemQuery) ServiceFactory.soaService(IDealSystemQuery.class);
			CreditBalanceChangeLogExampleExtended caExtended = new CreditBalanceChangeLogExampleExtended();
			caExtended.createCriteria().andReasonIdEqualTo(freezeOrderId);
			
			//车后会员ID
			List<Object> caList = queryControl.getObjsByExampleExtended(caExtended);
			if(caList == null || caList.isEmpty()) 
				throw new YpptGatewayException("不存在冻结订单freezeOrderId:" + freezeOrderId + "!");
			CreditBalanceChangeLog cbcl = (CreditBalanceChangeLog) caList.get(0);
			return deduct(userId, cbcl.getDeltaFrozen(),null );
			
		} catch (YpptGatewayException e) {
			logger.error("扣除旺金币余额发生异常!", e);
			throw e;
		} catch (Exception e) {
			logger.error("扣除旺金币余额发生异常!", e);
			throw new YpptGatewayException("扣除旺金币余额发生异常!", e);
		}
	}

	@Override
	public String add(String userId, Long creditNum, String reasonNo)
			throws YpptGatewayException {
		try {
			if(StringUtils.isBlank(userId)){
				throw new YpptGatewayException("userid为空!");
			}
			if( creditNum <=0 ){
				throw new YpptGatewayException("creditNum小于零!"+ creditNum);
			}
			IDealSystemQuery queryControl = (IDealSystemQuery) ServiceFactory.soaService(IDealSystemQuery.class);
			CreditAccountExampleExtended caExtended = new CreditAccountExampleExtended();
			//车后会员ID
			String owningId = queryCreditAccountOwningIdByUserId(userId);
			caExtended.createCriteria().andOwnerIdEqualTo(owningId);
			List<Object> caList = queryControl.getObjsByExampleExtended(caExtended);
			if(caList == null || caList.isEmpty()) 
				throw new YpptGatewayException("没有会员:" + userId + "的积分账户!");
			CreditAccount ca = (CreditAccount) caList.get(0);
			
			IDealController dealController = (IDealController) ServiceFactory.soaService(IDealController.class);
			
			DealExternalAdjustment deal = new DealExternalAdjustment();
			deal.setAccountId(ca.getId());
			deal.setCreditAdjust(creditNum);
			deal.setDealType(IDeal.DealType_ExternalAjustment); // 积分返利
			return dealController.submitDeal(deal);
		} catch (YpptGatewayException e) {
			logger.error("增加旺金币余额发生异常!userId:" + userId + "\tcreditNum:" + creditNum, e);
			throw e;
		} catch (Exception e) {
			logger.error("增加旺金币余额发生异常!userId:" + userId + "\tcreditNum:" + creditNum, e);
			throw new YpptGatewayException("增加旺金币余额发生异常!userId:" + userId + "\tcreditNum:" + creditNum, e);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public PaginationResult<CreditIO> queryCreditIO(Map<String, Object> parmaMap)
			throws YpptGatewayException {
		try {
			PaginationResult<CreditIO> pResult = new PaginationResult<CreditIO>();
			IBaseLocalCreditManager localCrredit = ServiceFactory.soaService(IBaseLocalCreditManager.class);
			PaginationResult localPResult =  localCrredit.paginateCreditIORecords(parmaMap);
			if(localPResult == null || localPResult.getData() == null ){
				return pResult;
			}else{
				pResult.setTotal(localPResult.getTotal());
				List locallist = localPResult.getData();
				List<CreditIO> resultList = new ArrayList<CreditIO>();
				for (int i = 0; i < locallist.size(); i++) {
					CreditIO cio = new CreditIO();
					PropertyUtils.copyProperties( cio, locallist.get(i));
					resultList.add(cio);
				}
				pResult.setData(resultList);
				return pResult;
			}
		} catch (YpptGatewayException e) {
			logger.error("查询旺金币收支发生异常!", e);
			throw e;
		} catch (Exception e) {
			logger.error("查询旺金币收支发生异常!", e);
			throw new YpptGatewayException("查询旺金币收支发生异常", e);
		}
	}
	
	
	public String queryCreditAccountOwningIdByUserId(String userId)
			throws Exception {
		logger.info("----------------开始获取积分账户所属用户id：user_id为：" + userId + "----------------");
		MemberOrg member = queryChptMemberInfoByUserId(userId);
		return member.getId();
	}
	
	public MemberOrg queryChptMemberInfoByUserId(String userId)
			throws Exception {
		MemberOrg memberOrg = null;
	    try {
	    	OperatorExampleExtended operatorExample = new OperatorExampleExtended();
			operatorExample.createCriteria().andUaaUuidEqualTo(userId);
			operatorExample.setSelectedField(com.ctfo.csm.boss.beans.Operator.fieldOrgId());
	    	
	    	MemberOrgExampleExtended memExtended = new MemberOrgExampleExtended();
	    	memExtended.createCriteria().andIdIn(Arrays.asList(operatorExample));
	    	logger.info("----------------开始获取积分账户所属用户的车后服务----------------");
	    	ICHPTGenericManager manager = (ICHPTGenericManager) ServiceFactory.soaService(ICHPTGenericManager.class);
	    	logger.info("----------------成功获取积分账户所属用户的车后服务----------------");
	    	@SuppressWarnings("unchecked")
			List<Object> meList = manager.getModels(memExtended);
	    	if(meList != null && !meList.isEmpty()){
	    		memberOrg = (MemberOrg) meList.get(0);
	    		memberOrg.setId(memberOrg.getId());
	    	}else{
	    		throw new YpptGatewayException("没有查询到旺金币的车后会员信息!userId : "+ userId);
	    	}
	    } catch (YpptGatewayException e) {
            logger.warn("调用车后InfoService 查询车后会员信息发生异常！userId :" + userId, e);
			throw e;
        } catch (Exception e) {
            logger.warn("调用车后InfoService 查询车后会员信息发生异常！userId :" + userId, e);
            throw new YpptGatewayException("调用车后InfoService 查询车后会员信息发生异常!userId : "+ userId, e);
        }
	    return memberOrg;
	    
	}

}
