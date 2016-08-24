package com.test.demo;

import org.junit.Test;

import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.CreateAccountReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.GetCardRechargeUrlReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.GetSmsCodeReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.IsPayPasswordReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.IsSetPayPasswordReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.ModifyPayPasswordReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.QueryAccountInfoByUserIdReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.SendMobileMsgReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.SetPayPasswordReq;
import com.ctfo.sinoiov.mobile.webapi.util.MD5Utils;
import com.test.util.BaseTestUnti;

/**
 * 支付接口测试类
 * 
 * @author sunchuanfu
 */
public class TestPayManager {
	
	@Test
	public void createAccount() {
		try {
			// TODO 接口可去掉
			CreateAccountReq body = new CreateAccountReq();
			body.setOwnerUserId("145");
			body.setOwnerLoginName("sunchuanfu145");
//			body.setPayPassword("123456");
			body.setMobileNo("18500851753");

			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Z000007", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
			System.out.println("http返回结果：" + result);// MA1423485164023705
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void isSetPayPasswordReq() {
		try {
			IsSetPayPasswordReq body = new IsSetPayPasswordReq();
			body.setAccountNo("MA1423485164023705");

			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Z000004", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
			System.out.println("http返回结果：" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void setPayPasswordReq() {
		try {
			SetPayPasswordReq body = new SetPayPasswordReq();
			body.setUserId("145");
			body.setOwnerUserId("145");
			body.setOwnerLoginName("sunchuanfu145");
			body.setMobileNo("18500851753");
			body.setMd5(MD5Utils.getDefaultMd5String("123456"));
//			body.setSmsCode("044358");

			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Z000002", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
			System.out.println("http返回结果：" + result);// 该账户［MA1423485164023705］已经设置过密码！
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void isPayPasswordReq() {
		try {
			IsPayPasswordReq body = new IsPayPasswordReq();
			body.setAccountNo("MA1423581943593263");
			body.setMobileNo("15160019964");
			body.setMd5(MD5Utils.getDefaultMd5String("12345678"));
			body.setSmsCode("441370");

			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Z000003", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
			System.out.println("http返回结果：" + result);// 该账户［MA1423485164023705］已经设置过密码！
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void modifyPayPassword() {
		try {
			ModifyPayPasswordReq body = new ModifyPayPasswordReq();
			body.setAccountNo("MA1423289362866770");
			body.setOldMD5(MD5Utils.getDefaultMd5String("1234567"));
			body.setNewMD5(MD5Utils.getDefaultMd5String("654321"));
			body.setSmsCode("680885");
			body.setMobileNo("18500851753");
			
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Z000001", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
			System.out.println("http返回结果：" + result);// 修改账号[MA1423485164023705]交易密码失败，原因
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getDeductMoneyUrl() {
		try {
			GetCardRechargeUrlReq params = new GetCardRechargeUrlReq();
			params.setMerchantOrderAmount("0.01");
			params.setMerchantOrderNo("6573211");
//			params.setMerchantOrderRemark("fdasfas1");
			params.setAccountNo("MA14234851640237051");
//			params.setCallbackURL("fasdfasdf");
			params.setClentId("7aa34f25-f313-4dc3-b948-0e658ea2831a");
			params.setClentType("2");
			params.setFcallbackURL("ffffd");
			params.setIdentityId("7aa34f25-f313-4dc3-b948-0e658ea2831a");
			params.setIdentityType("2");
			params.setProductCatalog("1");
			params.setProductName("油卡充值");
			params.setUserId("7aa34f25-f313-4dc3-b948-0e658ea2831a");
			params.setUserLoginName("michael");
//			params.setUserUa("ffff");
			params.setUserIP("192.168.110.23");

			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Z000005", params);
			String result = BaseTestUnti.getRetureFromUrl(url);
			System.out.println("http返回结果：" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void queryAccountInfoByUserId() {
		try {
			QueryAccountInfoByUserIdReq body = new QueryAccountInfoByUserIdReq();
			body.setUserId("077cd6b9-89b4-4cde-ac2e-978a8466fcde");

			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Z000006", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
			System.out.println("http返回结果：" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void sendMsg() {
		try {
			SendMobileMsgReq body = new SendMobileMsgReq();
			body.setMobiles("18500851753");
			body.setContent("testsss");
			
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y900002", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
			System.out.println("http返回结果：" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getSmsCode() {
		try {
			GetSmsCodeReq body = new GetSmsCodeReq();
			body.setAccountNo("MA1423485164023705");
			body.setMobileNo("18500851753");
//			body.setIsCreateAccount("0");
			
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Z000008", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
			System.out.println("http返回结果：" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
