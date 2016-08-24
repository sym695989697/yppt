package com.ctfo.yppt.basemanager.rebate.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.mamager.impl.MessageUtil;
import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.gatewayservice.interfaces.service.ICreditService;
import com.ctfo.yppt.baseservice.card.beans.ICCardCreditAccountIO;
import com.ctfo.yppt.baseservice.card.beans.ICCardCreditAccountIOExampleExtended;
import com.ctfo.yppt.baseservice.rebate.intf.IRebateSettleCurrencyManager;
import com.ctfo.yppt.baseservice.system.beans.UserStat;
import com.ctfo.yppt.baseservice.system.beans.UserStatExampleExtended;
import com.ctfo.yppt.baseservice.system.intf.IUserStatManager;
import com.ctfo.yppt.bean.RebateImportBean;

@Service("IRebateSettleCurrencyManager")
public class RebateSettleCurrencyManagerImpl extends
GenericManagerImpl<ICCardCreditAccountIO, ICCardCreditAccountIOExampleExtended> implements
		IRebateSettleCurrencyManager {
	
	private ICreditService iCreditService=null;
	@Resource(name="UserStatManager",description="UserState service")
	private IUserStatManager iUserStatManager;
	
	private static Log logger = LogFactory.getLog(RebateSettleCurrencyManagerImpl.class);
	@Override
	public String settleCurrency(RebateImportBean rebateImport,String opId)throws Exception{
		String result=null;
		try {
			if(rebateImport==null){
				logger.error("传入的返利对象为空");
				throw new NullPointerException();
			}
			if(StringUtils.isBlank(opId)){
				logger.error("操作人不能为空");
				throw new Exception("操作人不能为空");
			}
			if(StringUtils.isBlank(rebateImport.getOwnerId())){
				logger.error("所属会员UAAID不能为空");
				throw new Exception("所属会员UAAID不能为空");
			}
			if(StringUtils.isBlank(rebateImport.getOwnerId())){
				logger.error("传入的返利对象为空");
				throw new Exception("ownerId不能为空");
			}
			if(StringUtils.isBlank(rebateImport.getCurrencyCount())||new Long(rebateImport.getCurrencyCount())<0L){
				logger.error("CurrencyCount不能小于0");
				throw new Exception("CurrencyCount不能小于0");
			}
			//判断是否存在积分账户
			if(!(getICreditService().checkExistCreditAccount(rebateImport.getOwnerId()))){//不存在，则创建积分账户
				getICreditService().createCreditAccount(rebateImport.getOwnerId());
			}
			//增加旺金币
			result = getICreditService().add(rebateImport.getOwnerId(), new Long(rebateImport.getCurrencyCount()), rebateImport.getId());
			//旺金币返利成功后发送短信
			String templateCode = "REBATE_MESSAGE_SUCC";
			List<String> messageParamslist=new ArrayList<String>();
			messageParamslist.add(rebateImport.getImportYear()+"年"+rebateImport.getImportMonth()+"月");
			messageParamslist.add(rebateImport.getCurrencyCount());
			SimpleDateFormat df=new SimpleDateFormat("dd日HH:mm:ss");
			String time = df.format(new Date());
			messageParamslist.add(time);
			try {
				MessageUtil.sendShortMessage(rebateImport.getRegisterMobile(), templateCode, messageParamslist);
			} catch (Exception e) {
				logger.error("发生短信失败",e);
			}
			try {
				//添加旺金币交易记录
				ICCardCreditAccountIO bean=new ICCardCreditAccountIO();
				bean.setUserId(rebateImport.getOwnerId());
				bean.setUserName(rebateImport.getOwnerName());
				bean.setUserRegPhone(rebateImport.getRegisterMobile());
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM");
				Long tradeTime= format.parse(rebateImport.getImportYear()+"-"+rebateImport.getImportMonth()).getTime();
//				bean.setTradeTime(System.currentTimeMillis());
				bean.setTradeTime(tradeTime);
				//bean.setAccountId(accountId);//积分账户Id
				bean.setType("rebate");//交易类型(recharge:积分充值;rebate:积分返利收入)
				bean.setModel("1");//收支类型(以账户的角度：1收入，2支出)
				bean.setCreditNum(new Long(rebateImport.getCurrencyCount()));
				bean.setReasonId(rebateImport.getId());
				bean.setBalance(getICreditService().queryBalance(rebateImport.getOwnerId()));//操作后余额
				bean.setState("0");//状态，0成功，1失败
				bean.setOperateTime(System.currentTimeMillis());
				bean.setOperator(opId);
				bean.setRemark(rebateImport.getImportYear()+"年"+rebateImport.getImportMonth()+"月份消费所得返利");
				this.add(bean);
				//修改或新增UserState
				UserStatExampleExtended userStateExample=new UserStatExampleExtended();
				userStateExample.createCriteria().andUserIdEqualTo(rebateImport.getOwnerId());
				List<UserStat> userstats=iUserStatManager.getList(userStateExample); 
				if(userstats!=null && userstats.size()>0){
					UserStat stat=userstats.get(0);
					BigDecimal sumGoldCoinNum = stat.getGoldCoinNum()==null ? new BigDecimal(0).add(new BigDecimal(rebateImport.getCurrencyCount())) : stat.getGoldCoinNum().add(new BigDecimal(rebateImport.getCurrencyCount()));
					stat.setGoldCoinNum(sumGoldCoinNum);//返利旺金币数量
					BigDecimal sumRebateMoney = stat.getRebateMoney()==null ? new BigDecimal(0).add(new BigDecimal(rebateImport.getChangeMoney())) : stat.getRebateMoney().add(new BigDecimal(rebateImport.getChangeMoney()));
					stat.setRebateMoney(sumRebateMoney);//返利金额
					stat.setUpdateTime(System.currentTimeMillis());
					
					//更新上月返利金额和上月返利旺金币--无返利的会员则不能更新--暂不采用这种方式 ，采用查询旺金币交易记录的方式
//					Calendar cImpot=Calendar.getInstance();
//					cImpot.set(Calendar.YEAR, new Integer(rebateImport.getImportYear()));
//					cImpot.set(Calendar.YEAR, new Integer(rebateImport.getImportMonth())-1);
//					Calendar c=Calendar.getInstance();
//					c.set(Calendar.MONTH, c.get(Calendar.MONTH)-1);
//					if(cImpot.get(Calendar.YEAR)==c.get(Calendar.YEAR) && cImpot.get(Calendar.MONTH)==c.get(Calendar.MONTH)){
//						stat.setLastMonthRebateCoinNum(new Long(rebateImport.getCurrencyCount()));
//						stat.setLastMonthRebateMoney(new BigDecimal(rebateImport.getChangeMoney()));
//					}
					
					iUserStatManager.update(stat);
				}else if(userstats.size()==0){
					UserStat stat=new UserStat();
					stat.setUserId(rebateImport.getOwnerId());
					stat.setGoldCoinNum(new BigDecimal(rebateImport.getCurrencyCount()));//返利旺金币数量
					stat.setRebateMoney(new BigDecimal(rebateImport.getChangeMoney()));//返利金额
					stat.setUpdateTime(System.currentTimeMillis());
					
					//更新上月返利金额和上月返利旺金币--无返利的会员则不能更新--暂不采用这种方式 ，采用查询旺金币交易记录的方式
//					Calendar cImpot=Calendar.getInstance();
//					cImpot.set(Calendar.YEAR, new Integer(rebateImport.getImportYear()));
//					cImpot.set(Calendar.YEAR, new Integer(rebateImport.getImportMonth())-1);
//					Calendar c=Calendar.getInstance();
//					c.set(Calendar.MONTH, c.get(Calendar.MONTH)-1);
//					if(cImpot.get(Calendar.YEAR)==c.get(Calendar.YEAR) && cImpot.get(Calendar.MONTH)==c.get(Calendar.MONTH)){
//						stat.setLastMonthRebateCoinNum(new Long(rebateImport.getCurrencyCount()));
//						stat.setLastMonthRebateMoney(new BigDecimal(rebateImport.getChangeMoney()));
//					}
					
					
					iUserStatManager.add(stat);
				}
			} catch (Exception e) {
				logger.error("添加旺金币交易记录异常",e);
			}
		} catch (Exception e) {
			logger.error("结算到旺金币异常",e);
			throw e;
		}
		return result;
	}
	public ICreditService getICreditService(){
		if(iCreditService==null){
			iCreditService=(ICreditService) ServiceFactory.getFactory().getService(ICreditService.class);
		}
		return iCreditService;
	}
	public static void main(String[] args) {
		Calendar c=Calendar.getInstance();
		c.set(Calendar.MONTH, c.get(Calendar.MONTH)-1);
		
	}
}
