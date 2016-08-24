//package com.ypptcatch.test;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.junit.Test;
//
//import com.ctfo.catchservice.bean.ClientSession;
//import com.ctfo.catchservice.bean.MainCardAndSubcardVo;
//import com.ctfo.catchservice.bean.StaticMap;
//import com.ctfo.catchservice.interfaces.internal.ICatchInternalService;
//import com.ctfo.catchservice.interfaces.internal.ILoginInternalService;
//import com.ctfo.catchservice.util.LoginCode;
//import com.ctfo.csm.soa.ServiceFactory;
//import com.ctfo.csm.soa.intf.IServiceFactory;
//import com.ctfo.yppt.baseservice.card.intf.IICCardMainManager;
//import com.ctfo.yppt.baseservice.card.intf.IICCardManager;
//
//public class LoginTest extends BaseTest{
//
//	private static final Log log = LogFactory.getLog(LoginTest.class);
//	 
//	/**
//	 * 测试单用户登录
//	 */
//	@Test
//	public void userLoginTest() {
//		IServiceFactory factory = ServiceFactory.getFactory();
//		IICCardMainManager iCCardMainManager = (IICCardMainManager) factory.getService(IICCardMainManager.class);
//		IICCardManager iCCardManager = (IICCardManager) factory.getService(IICCardManager.class);
//		
//		ILoginInternalService loginInternalService = (ILoginInternalService)ctx.getBean("loginInternalService");
//		ICatchInternalService catchInternalService = (ICatchInternalService)ctx.getBean("catchInternalService");
//		try {
//			
//			String hostType = "zsy";//中石油
//			String province = "河南";
//			
//			//1.获取验证码图片与cookie
//			String[] cookieAndCheckCode = loginInternalService.getLoginCookiesAndCheckCode(hostType);
//			InputStream in = System.in;
//			log.info("开始输入验证码...");
//			BufferedReader br = new BufferedReader(new InputStreamReader(in));
//			String code = br.readLine();
//			log.info("验证码："+code);
//			
//			String username = "zjxlhn";
//			String password = "hn2014";
//			
//			//2.登录
//			String retVal = loginInternalService.login(hostType,province,username,password,code);
//			log.info("结果："+retVal);
//			
//			//3.从存储用户中读取用户信息
//			ClientSession clientSession = StaticMap.clientMap.get(LoginCode.USERPRE_ZSY + username);
//			String cookieStr = clientSession.getCookies();
//			log.info("Cookie2:"+cookieStr);
//			
//			try {
//				//参数很重要
//				//1.查询卡内余额接口测试
//				//List<OilCardBalanceVo> queryResult = inCatchService.getOilCardBalance(LoginCode.ZSY,"");//"9130290000414843"
//				
//				//2.账户交易查询(只填起始时间)
//				//List<AccountTradeVo> queryResult = inCatchService.getAccountTrade(LoginCode.ZSY,"","2015-01-01","","1");
//				
//				//3.消费交易查询(只填起始时间)
//				//List<TradeByConsumeVo> queryResult = inCatchService.getTradeByConsume(LoginCode.ZSY,"","2015-01-01","");
//				
//				//4.获取中主卡及主卡下的子卡接口
//				List<MainCardAndSubcardVo> queryResult = (List<MainCardAndSubcardVo>) catchInternalService.getMainCardAndSubcard(LoginCode.ZSY,"","");
//				
//				log.info("result:" + queryResult.size());
//				log.info(queryResult.get(0).getAsn());
//				
//				//添加数据 (含有主卡与子卡的信息)
//				/*String mainCardId = "";//暂存主卡ID
//				for(MainCardAndSubcardVo m : queryResult){
//					String id = UUID.randomUUID().toString();
//					String cardNumber = m.getAsn();
//					String state = m.getCardStatus();
//					String ctrdituser = m.getDriverName();
//					String isMaster = m.getIsMaster();
//					ICCardMain icCardMain = new ICCardMain();
//					ICCard icCard = new ICCard();
//					
//					//存入主卡表
//					if(!"".equals(isMaster)&& "是".equals(isMaster)){
//						mainCardId = id;
//						icCardMain.setId(id);
//						icCardMain.setAccountId("jc-xyz");//数据来源于TB_IC_CARD_SYSTEM_CONFIG表
//						icCardMain.setCardNumber(cardNumber);
//						icCardMain.setCtrdituser("".equals(ctrdituser)?"--":ctrdituser);
//						icCardMain.setCardType("中石油卡");
//						icCardMain.setState(state.equals("正常状态")?"T":"F");
//						icCardMain.setOpencardofficename("中石油");
//						icCardMain.setOpencardofficecode("CARD-TYPE-01");//中石化：CARD-TYPE-02
//						iCCardMainManager.add(icCardMain);
//						log.info("主卡卡号："+icCardMain.getCardNumber());
//					}else{
//						//存入子卡表
//						icCard.setId(id);
//						icCard.setCardNumber(cardNumber);
//						icCard.setParentId(mainCardId);
//						icCard.setCtrdituser("".equals(ctrdituser)?"--":ctrdituser);
//						icCard.setState(state.equals("正常状态")?"T":"F");
//						icCard.setCardType("中石油");
//						iCCardManager.add(icCard);
//						log.info("子卡卡号："+icCard.getCardNumber());
//					}
//				} */
//				
//				//catchInternalService.addMainCardAndSubcardByHostType(LoginCode.ZSY, "中石油", "CARD-TYPE-01");
//				
//				
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	/**
//	 * 测试多用户登录
//	 */
//	@Test
//	public void testMutiUserLogin(){
//		final ILoginInternalService loginService = (ILoginInternalService)ctx.getBean("loginInternalService");
//		final String userType = "zsy";//中石油
//		final String province = "河南";
//		
//		final LinkedList<String> user = new LinkedList<String>();
//		
//		user.add("zjxlhn,hn2014");
//		user.add("cqzjcl,cqzjcl");
//		
//		
//		//2.登录
//		for(int i=0;i<user.size()+1;i++){
//			//1.获取验证码图片与cookie
//			String[] cookieAndCheckCode = loginService.getLoginCookiesAndCheckCode(userType);
//			InputStream in = System.in;
//			log.info("开始输入验证码...");
//			BufferedReader br = new BufferedReader(new InputStreamReader(in));
//			String code="";
//			try {
//				code = br.readLine();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			log.info("验证码："+code);
//		
//		
//			String username = user.pop();
//			String[] u = username.split(",");
//			
//			String retVal = loginService.login(userType,province,u[0],u[1],code);
//			log.info("结果："+retVal);
//			
//			//3.从存储用户中读取用户信息
//			ClientSession clientSession = StaticMap.clientMap.get(LoginCode.USERPRE_ZSY + u[0]);
//			String cookieStr = clientSession.getCookies();
//			log.info("Cookie2:"+cookieStr); 
//		}
//		
//		for(Iterator<String> itr = StaticMap.clientMap.keySet().iterator(); itr.hasNext();){
//			String key = itr.next();
//			System.out.println("username:"+key);
//		} 	
//	}
//}
