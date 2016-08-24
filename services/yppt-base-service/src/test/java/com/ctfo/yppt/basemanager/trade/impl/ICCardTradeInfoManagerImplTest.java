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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-all.xml")
public class ICCardTradeInfoManagerImplTest {
	
	@Autowired
	ICCardTradeInfoManagerImpl manager;
	
	@Test
	public void testPaginateTradeInfo() {
		try {
			Map<String,Object> params=new HashMap();
			params.put("page", 1);
			params.put("pageSize", 10);
			params.put("userId","12321");
			params.put("status", "1");
			params.put("startTime", 0);
			params.put("endTime",new Long("1520529802000"));
			params.put("threeSwitch","1000113500002703822");
			PaginationResult<?> rslt=manager.paginateTradeInfo(params);
			assertEquals(1,rslt.getTotal() );
			assertEquals(1,rslt.getData().size() );
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCountTradeInfo() {
		try {
			Map<String,Object> params=new HashMap();
			params.put("page", 1);
			params.put("pageSize", 10);
			params.put("userId","12321");
			params.put("status", "1");
			params.put("startTime", 0);
			params.put("endTime", new Long("1520529802000"));
			params.put("threeSwitch","1000113500002703822");
			BigDecimal rslt=manager.countTradeInfo(params);
			assertEquals(new BigDecimal(8000000),rslt);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
