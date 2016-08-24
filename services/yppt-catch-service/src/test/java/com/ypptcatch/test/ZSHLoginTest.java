//package com.ypptcatch.test;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.junit.BeforeClass;
//
//import com.ctfo.catchservice.interfaces.internal.ILoginInternalService;
//
//public class ZSHLoginTest extends BaseTest{
//	private static final Log log = LogFactory.getLog(ZSHLoginTest.class);
//	/**
//	 * 测试单用户登录
//	 */
//	@BeforeClass
//	public static void userLoginTest() {
//		ILoginInternalService loginInternalService = (ILoginInternalService)ctx.getBean("zshLoginInternalService");
//			String hostType = "zsh";//中石化
//			
//			//1.获取验证码图片与cookie
//			loginInternalService.getLoginCookiesAndCheckCode(hostType);
//			String username="zj2493297";
//			String password="zj995228";
//			String code;
//			try {
//				InputStream in = System.in;
//				log.info("开始输入验证码...");
//				BufferedReader br = new BufferedReader(new InputStreamReader(in));
//				code = br.readLine();
//				log.info("验证码："+code);
//				String result =loginInternalService.login(hostType, "福建", username, password, code);
//				log.info("结果："+result);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//	}
//}
