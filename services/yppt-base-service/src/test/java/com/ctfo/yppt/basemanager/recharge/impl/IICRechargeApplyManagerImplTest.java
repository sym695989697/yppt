package com.ctfo.yppt.basemanager.recharge.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.gatewayservice.interfaces.bean.pay.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-all.xml") 
public class IICRechargeApplyManagerImplTest{

	@Autowired
	ICRechargeApplyManagerImpl manager;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRechargeApply() {
		fail("Not yet implemented");
	}

	@Test
	public void testAuditRecharge() {
		fail("Not yet implemented");
	}

	@Test
	public void testPaginateRechargeAndTradeInfoForAPP() {
		fail("Not yet implemented");
	}
	@Test
	public void testQueryAccount(){
//		try {
//			Account account=manager.checkAccount("tony111120157978978987");
//			System.out.println(account.getAccountNo());
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	@Test
	public void testPaginateRecharge() {

		try {
			Map<String,Object> params=new HashMap();
			params.put("page", 1);
			params.put("pageSize", 10);
			params.put("userId","12321");
			params.put("status", "1");
			params.put("startTime", 0);
			params.put("endTime", 2);
			params.put("threeSwitch","33");
			PaginationResult<?> rslt=manager.paginateRecharge(params);
			assertEquals(1,rslt.getTotal() );
			assertEquals(1,rslt.getData().size() );
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCountRecharge() {
		try {
			Map<String,Object> params=new HashMap();
			params.put("startrow", 0);
			params.put("endrow", 3);
			params.put("userId","1");
			params.put("paystatus", "1");
			params.put("startTime", 0);
			params.put("endTime", 2);
			params.put("threeswitch","3");
			BigDecimal rslt=manager.countRecharge(params);
			assertEquals(new BigDecimal(2),rslt);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetICardRechageMetaBeanByapplyId() {
		fail("Not yet implemented");
	}

}
