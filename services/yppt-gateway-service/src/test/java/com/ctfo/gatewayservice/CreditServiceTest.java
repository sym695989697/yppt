package com.ctfo.gatewayservice;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.ctfo.gatewayservice.interfaces.service.ICreditService;

public class CreditServiceTest {
	protected static ICreditService creditService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			String url = "http://localhost:9080/gatewayService/hessian-remote/com.ctfo.gatewayservice.interfaces.service.ICreditService";
			com.caucho.hessian.client.HessianProxyFactory factory = new HessianProxyFactory();
			creditService = (ICreditService) factory.create(ICreditService.class,
					url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void createAccount() {
		creditService.createCreditAccount("870836fb-1929-487f-a6f1-9b368bb3f86c");
	}
	@Test
	public void queryBalance() {
		creditService.queryBalance("870836fb-1929-487f-a6f1-9b368bb3f86c");
	}
	@Test
	public void freeze() {
		creditService.freeze("870836fb-1929-487f-a6f1-9b368bb3f86c", 10L ,"null");
	}
	
	@Test
	public void queryCreditIO() {
		Map<String, Object> parmaMap = new HashMap<String, Object>();
		parmaMap.put("userId", "870836fb-1929-487f-a6f1-9b368bb3f86c");
		creditService.queryCreditIO(parmaMap );
	}
}
