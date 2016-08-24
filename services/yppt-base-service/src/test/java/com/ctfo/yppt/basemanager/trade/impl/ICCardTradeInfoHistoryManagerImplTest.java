package com.ctfo.yppt.basemanager.trade.impl;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoHistory;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-all.xml")
public class ICCardTradeInfoHistoryManagerImplTest {

	@Autowired
	ICCardTradeInfoHistoryManagerImpl manager;
	
	@Test
	public void testAddTradeInfoHistory() {
		fail("Not yet implemented");
	}
	@Test
	public void testAddTradeInfoHistory(ICCardTradeInfoHistory model){
		
	}
	@Test
	public void testPaginateTradeInfoHistory() {

		try {
			Map<String,Object> params=new HashMap();
			params.put("page", 1);
			params.put("pageSize", 10);
			params.put("userId","12321");
			params.put("status", "1");
			params.put("startTime", 0);
			params.put("endTime", 2);
			params.put("threeSwitch","3");
			PaginationResult<?> rslt=manager.paginateTradeInfoHistory(params);
			assertEquals(1,rslt.getTotal() );
			assertEquals(1,rslt.getData().size() );
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCountTradeInfoHistory() {
		
		try {
			Map<String,Object> params=new HashMap();
			params.put("page", 1);
			params.put("pageSize", 10);
			params.put("userId","12321");
			params.put("status", "1");
			params.put("startTime", 0);
			params.put("endTime", 2);
			params.put("threeSwitch","3");
			BigDecimal rslt=manager.statTradeInfoHistory(params);
			assertEquals(new BigDecimal(100),rslt);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testPaginateTradeInfoAndTradeInfoHistory() {
		fail("Not yet implemented");
	}

}
