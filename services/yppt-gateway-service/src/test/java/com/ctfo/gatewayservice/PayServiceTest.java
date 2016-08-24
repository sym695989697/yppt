package com.ctfo.gatewayservice;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.ctfo.gatewayservice.interfaces.bean.YpptGatewayException;
import com.ctfo.gatewayservice.interfaces.bean.pay.Account;
import com.ctfo.gatewayservice.interfaces.bean.pay.CreateAccountParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.CreateAccountSmsCodeParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.DeductMoneyParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.DeductMoneySureParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.DeductMoneySureResponse;
import com.ctfo.gatewayservice.interfaces.bean.pay.FreezeBalanceParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.GetCashierDeductUrlParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.RechargeToUserAccountParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.TransferAccountParams;
import com.ctfo.gatewayservice.interfaces.service.IPayService;

public class PayServiceTest {
	private static IPayService iPayService;

	private static String myMerchantcode = "201501211146539567052";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			String url = "http://192.168.16.207:8080/gatewayService/hessian-remote/com.ctfo.gatewayservice.interfaces.service.IPayService";
			// String url =
			// "http://192.168.100.66:8083/gatewayService/hessian-remote/com.ctfo.gatewayservice.interfaces.service.IPayService";
			// String url =
			// "http://192.168.100.66:9091/gatewayService/hessian-remote/com.ctfo.gatewayservice.interfaces.service.IPayService";
			com.caucho.hessian.client.HessianProxyFactory factory = new HessianProxyFactory();
			iPayService = (IPayService) factory.create(IPayService.class, url);
		} catch (YpptGatewayException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void createAccount() {
		try {
			CreateAccountParams createAccountParams = new CreateAccountParams();
			createAccountParams.setMobileNo("13552979202");
			createAccountParams.setOwnerLoginName("2001001967");
			createAccountParams.setOwnerUserId("e8665302-f59a-480e-a1e5-3b6514f39ac2");
			// createAccountParams.setPayPassword("");/

			String accountNo = iPayService.createAccount(createAccountParams);
			System.out.println("创建的帐号名称：" + accountNo);// MA1422971838503379
		} catch (YpptGatewayException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void createAccountSmsCode() {
		try {
			CreateAccountSmsCodeParams createAccountParams = new CreateAccountSmsCodeParams();
			createAccountParams.setOwnerLoginName("sunchuanfu2");
			createAccountParams.setOwnerUserId("1234");
			createAccountParams.setMD5("123456");
			createAccountParams.setMobileNo("18500851753");
			createAccountParams.setSmsCode("107308");

			String accountNo = iPayService.createAccountSmsCode(createAccountParams);
			System.out.println("创建的帐号名称：" + accountNo);// MA1423209052511617
		} catch (YpptGatewayException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void queryAccountByAccountNo() {
		try {
			Account account = iPayService.queryAccountByAccountNo("MA1422971838503379");
			System.out.println("查询到的帐号" + account);
		} catch (YpptGatewayException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void queryAccountByUserId() {
		try {
			// String userId = "tony111120157978978987";
			String userId = "7aa34f25-f313-4dc3-b948-0e658ea2831a";
			Account account = iPayService.queryAccountByUserId(userId);
			System.out.println("查询到的帐号" + account);
		} catch (YpptGatewayException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void getCashierDeductMoneyUrl() {
		try {
			GetCashierDeductUrlParams params = new GetCashierDeductUrlParams();
			params.setMerchantOrderAmount("0.01");
			params.setMerchantOrderNo("121212");
			params.setMerchantOrderRemark("test");
			params.setAccountNo("MA1421346329090228");
			params.setCallbackURL("fasdfasdf");
			params.setClentId("7aa34f25-f313-4dc3-b948-0e658ea2831a");
			params.setClentType("2");
			params.setFcallbackURL("ffffd");
			params.setIdentityId("7aa34f25-f313-4dc3-b948-0e658ea2831a");
			params.setIdentityType("2");
			params.setProductCatalog("1");
			params.setProductName("油卡充值");
			params.setUserId("7aa34f25-f313-4dc3-b948-0e658ea2831a");
			params.setUserLoginName("michael");
			params.setUserUa("ffff");
			String url = iPayService.getCashierDeductMoneyUrl(params);
			// 生成url userid没生成
			System.out.println(url);
		} catch (YpptGatewayException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void deductMoneySure() {
		try {
			// 先getCashierDeductMoneyUrl
			DeductMoneySureParams params = new DeductMoneySureParams();
			params.setAccountNo("MA1421346329090228");
			params.setOrderNo("45454");
			params.setMerchantOrderNo("121212");
			params.setResult("1");
			params.setOrderAmount("0.01");
			DeductMoneySureResponse response = iPayService.deductMoneySure(params);
			System.out.println(response);
		} catch (YpptGatewayException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void freezeBalance() {
		try {
			FreezeBalanceParams freezeBalanceParams = new FreezeBalanceParams();
			freezeBalanceParams.setAccountNo("MA1423143316478148");
			freezeBalanceParams.setOrderAmount("0.01");
			freezeBalanceParams.setWorkOrderNo("123456789");
			// PayOrderResponse orderNo =
			// iPayService.freezeBalance(freezeBalanceParams);
			String orderNo = iPayService.freezeBalance(freezeBalanceParams);
			System.out.println(orderNo);
		} catch (YpptGatewayException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void unFreezeBalance() {
		try {
			FreezeBalanceParams unFreezeBalanceParams = new FreezeBalanceParams();
			unFreezeBalanceParams.setAccountNo("MA1423143316478148");
			unFreezeBalanceParams.setOrderAmount("0.01");
			unFreezeBalanceParams.setWorkOrderNo("rc20150207142408663");
			// PayOrderResponse orderNo =
			// iPayService.unFreezeBalance(unFreezeBalanceParams);
			String orderNo = iPayService.unFreezeBalance(unFreezeBalanceParams);
			System.out.println(orderNo);
		} catch (YpptGatewayException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void merchantOrderClearing() {
		try {
			// TODO 还没有实现
			List<String> orders = iPayService.merchantOrderClearing();
			System.out.println(orders);
		} catch (YpptGatewayException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void transferAccounts() {
		try {
			TransferAccountParams transferAccountParams = new TransferAccountParams();
			transferAccountParams.setAccountNo("MA1421346329090228");// 付款账号
			transferAccountParams.setCollectMoneyAccountNo("MA1422884423202122");
			transferAccountParams.setOrderAmount("0.01");
			transferAccountParams.setStoreCode(myMerchantcode);
			transferAccountParams.setUserId("test1");
			transferAccountParams.setWorkOrderNo("123");// 业务订单号要注意改

			String orderNo = iPayService.transferAccounts(transferAccountParams);
			System.out.println(orderNo);
		} catch (YpptGatewayException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void deductMoney() {
		try {
			DeductMoneyParams transferAccountParams = new DeductMoneyParams();
			transferAccountParams.setAccountNo("MA1423360910458356");// 付款账号
			transferAccountParams.setOrderAmount("0.01");
			transferAccountParams.setStoreCode(myMerchantcode);
			transferAccountParams.setUserId("ss");
			transferAccountParams.setWorkOrderNo("222221212");// 业务订单号要注意改

			// PayOrderResponse orderNo =
			// iPayService.deductMoney(transferAccountParams);
			String orderNo = iPayService.deductMoney(transferAccountParams);
			System.out.println(orderNo);
		} catch (YpptGatewayException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void rechargeToUserAccount() {
		try {
			RechargeToUserAccountParams params = new RechargeToUserAccountParams();
			params.setCollectMoneyAccountNo("MA1423056108310501");
			params.setOrderAmount("0.02");
			params.setStoreCode(myMerchantcode);
			params.setUserId("c2f805be-17ff-43e8-83c1-604d5b20f93b");
			params.setWorkOrderNo("Zfasdfafasfd");// 业务订单号要注意改

			// PayOrderResponse orderNo =
			// iPayService.rechargeToUserAccount(params);
			String orderNo = iPayService.rechargeToUserAccount(params);
			System.out.println(orderNo);
		} catch (YpptGatewayException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
