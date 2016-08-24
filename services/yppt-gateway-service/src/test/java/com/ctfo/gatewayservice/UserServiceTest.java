package com.ctfo.gatewayservice;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.ctfo.gatewayservice.interfaces.service.IUserService;

public class UserServiceTest {
	protected static IUserService iUserService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			String url = "http://localhost:8082/gatewayService/hessian-remote/com.ctfo.gatewayservice.interfaces.service.IUserService";
			com.caucho.hessian.client.HessianProxyFactory factory = new HessianProxyFactory();
			iUserService = (IUserService) factory.create(IUserService.class,
					url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void authenLogin() {
		String userAlias = "vims";
		String md5password = com.ctfo.csm.utils.Encoder.getMD5String("vims1234");
		
		boolean result = false;
		try {
			result = iUserService.authenLogin(userAlias, md5password);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertTrue(result);
	}
}
