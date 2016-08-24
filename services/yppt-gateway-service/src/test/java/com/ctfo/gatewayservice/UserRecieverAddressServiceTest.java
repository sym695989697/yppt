package com.ctfo.gatewayservice;

import org.junit.BeforeClass;
import org.junit.Test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.ctfo.crm.boss.beans.UserRecieverAddress;
import com.ctfo.crm.boss.beans.UserRecieverAddressExample;
import com.ctfo.crm.boss.beans.UserRecieverAddressExampleExtended;
import com.ctfo.gatewayservice.interfaces.service.IUserRecieverAddressService;

public class UserRecieverAddressServiceTest {
	protected static IUserRecieverAddressService creditService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			String url = "http://localhost:9080/gatewayService/hessian-remote/com.ctfo.gatewayservice.interfaces.service.IUserRecieverAddressService";
			com.caucho.hessian.client.HessianProxyFactory factory = new HessianProxyFactory();
			creditService = (IUserRecieverAddressService) factory.create(IUserRecieverAddressService.class,
					url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void createUserRecieverAddress() {
		UserRecieverAddress invoiceInfo = new UserRecieverAddress();
		invoiceInfo.setUserId("aaaaaa");
		creditService.addRecieverAddressInfo(invoiceInfo);
	}
	
	@Test
	public void queryUserRecieverAddress() {
		UserRecieverAddressExampleExtended invoiceInfo = new UserRecieverAddressExampleExtended();
//		invoiceInfo.createCriteria().andUserIdEqualTo("7aa34f25-f313-4dc3-b948-0e658ea2831a");
		invoiceInfo.createCriteria().andUserIdEqualTo("9c036e32-9d82-48b2-8641-28e81b5cab7a");
		creditService.queryRecieverAddressInfo(invoiceInfo);
	}
}
