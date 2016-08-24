package com.ctfo.gatewayservice;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.ctfo.gatewayservice.interfaces.bean.YpptGatewayException;
import com.ctfo.gatewayservice.interfaces.service.IMessageService;

public class MessageServiceTest {
	private static IMessageService iMessageService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			String url = "http://localhost:8080/gatewayService/hessian-remote/com.ctfo.gatewayservice.interfaces.service.IMessageService";
			com.caucho.hessian.client.HessianProxyFactory factory = new HessianProxyFactory();
			iMessageService = (IMessageService) factory.create(
					IMessageService.class, url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void sendMessage() {
		try {
			String mobiles = "13001133185";
			String content = "123";

			boolean result = iMessageService.sendMessage(mobiles, content);
			Assert.assertTrue(result);
		} catch (YpptGatewayException e) {
			System.out.println(e.getMessage());
		}
	}

}
