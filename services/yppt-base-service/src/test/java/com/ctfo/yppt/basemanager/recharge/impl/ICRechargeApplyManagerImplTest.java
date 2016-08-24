package com.ctfo.yppt.basemanager.recharge.impl;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ctfo.common.exception.BusinessException;
import com.ctfo.gatewayservice.interfaces.bean.pay.CreateAccountParams;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApply;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyDetail;
import com.ctfo.yppt.baseservice.recharge.intf.IICRechargeApplyDetailManager;
import com.ctfo.yppt.baseservice.recharge.intf.IICRechargeApplyManager;
import com.ctfo.yppt.bean.ICCardRechageApplyMetaBean;
import com.ctfo.yppt.bean.ICardRechageMetaBean;
import com.ctfo.yppt.bean.RechargeApplyDetailInfoBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-all.xml") 
public class ICRechargeApplyManagerImplTest {

	
	@Autowired
	IICRechargeApplyManager manager;
	@Autowired
	IICRechargeApplyDetailManager remanager;
	
	String userId="7aa34f25-f313-4dc3-b948-0e658ea2831a";
	//String userId="8ba86ac6-a257-4e3b-9719-f81f6cca5816";
	String orderNo="rc1111111111";
	BigDecimal money=new BigDecimal("0.01");
	String st="01";
	
	ICRechargeApply apply;
	ICRechargeApplyDetail applyDetail;
	ICardRechageMetaBean beans=new ICardRechageMetaBean();
	@Before
	public void setUp(){
		apply=new ICRechargeApply();
		apply.setOrderNo("rc20150202170721130");
		apply.setCaptialMoney(null);
		apply.setState(st);
		apply.setApplyPersonId("xxxxxxxxxx");
		apply.setUserId(userId);
		apply.setDataSource("1");
		apply.setTotalMoney(new BigDecimal("2222"));
		apply.setId("axxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		apply.setActualDivMoney(new BigDecimal("2222"));
		apply.setApprovalPersonId("111111xxxxxxx");
		apply.setUserId(userId);
		apply.setCardNum(1);
		apply.setCreditNum(new BigDecimal(1));
		apply.setId("8eba8dad-9615-4198-8add-0245179c818e");
		
		applyDetail=new ICRechargeApplyDetail();
		applyDetail.setApplyId("8eba8dad-9615-4198-8add-0245179c818e");
		applyDetail.setMoney(money);
		
		applyDetail.setCardNo("1000113500002493297");
		applyDetail.setActualDivMoney(money);
		
		
		List<ICRechargeApplyDetail> applyDetails=new ArrayList<ICRechargeApplyDetail>();
		applyDetails.add(applyDetail);
		
		beans.setRechargeApply(apply);
		beans.setIcRechargeApplyDetails(applyDetails);
		
	}
	
	@Test
	public void createAccount(){
		CreateAccountParams createAccountParams=new CreateAccountParams();
		createAccountParams.setMobileNo("15679425089");
		createAccountParams.setOwnerUserId("7aa34f25-f313-4dc3-b948-0e658ea2831b");
		createAccountParams.setOwnerLoginName("15679425089");
		createAccountParams.setPayPassword("abcd1234");
		
		try {
			//String r=manager.createAccount(createAccountParams);
			//System.out.println(r);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testQueryAccount(){
//		try {
// 			Account account=manager.checkAccount(userId);
//			System.out.println(account.getAccountNo());
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	@Test
	public void testICRechargeApplyManagerImpl() {
		fail("Not yet implemented");
	}

	@Test
	public void testRechargeApply() {
		ICCardRechageApplyMetaBean model=new ICCardRechageApplyMetaBean();
	    //apply.setCreditNum(100L);
		model.setRechargeApply(apply);
		List<ICRechargeApplyDetail> applyDetails=new ArrayList<ICRechargeApplyDetail>();
				applyDetails.add(applyDetail);
		model.setRechargeDeatils(applyDetails);
		try {
			//manager.applyRecharge(model, userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testApplyUnFreeze() {
		fail("Not yet implemented");
	}

	@Test
	public void testAuditRecharge() {
		
		try {
			//ICardRechageMetaBean rechageMetaBean,ICCardRechangeProcessLog processLog
			
			manager.auditRecharge(beans,null);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testPaginateRechargeAndTradeInfoForAPP() {
//		fail("Not yet implemented");
		try {
			Map parma = new HashMap();
			parma.put("userId", "3350e822-418e-4529-b611-ca0eddda762a");
			parma.put("cardId", "11a9ff58-0cd3-4271-985a-30daf5b8197c");
			
			manager.paginateRechargeAndTradeInfoForAPP(parma);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testPaginateRecharge() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountRecharge() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetICardRechageMetaBeanByapplyId() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddBusinessOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuildRequestContent() {
		try {
//		String url=	manager.buildRequestContent("tony111120157978978987","15679425088","1111111111111", "油卡充值",new BigDecimal("2222"));
		System.out.println("==============================================>");
//		System.out.println(url);
		System.out.println("<==============================================");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}

	@Test
	public void testCallBackPay() {
//		fail("Not yet implemented");
		try {
			RechargeApplyDetailInfoBean rb = remanager.getRechargeInfoForApp("afb6ce86-09cb-4f71-a1ce-06f8f0eb41bd");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSuccess() {
		fail("Not yet implemented");
	}

	@Test
	public void testFail() {
		fail("Not yet implemented");
	}

	@Test
	public void testSuccessBackError() {
		fail("Not yet implemented");
	}

	@Test
	public void testFailBackError() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountSingleRecharge(){
		Map<String,Object> params=new HashMap<String,Object>();
		try {
			params.put("cardId","");
			params.put("startTime", "0");
			params.put("endTime", "19999999999999");
			BigDecimal b=manager.countSingleRecharge(params);
			System.out.println("=============="+b+"==================");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
