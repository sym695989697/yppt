//package com.ypptcatch.test;
//
//import java.util.List;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.junit.Before;
//import org.junit.Test;
//
//import com.ctfo.catchservice.bean.AccountTradeVo;
//import com.ctfo.catchservice.bean.MainCardAndSubcardVo;
//import com.ctfo.catchservice.bean.OilCardBalanceVo;
//import com.ctfo.catchservice.bean.TradeByConsumeVo;
//import com.ctfo.catchservice.interfaces.internal.ICatchInternalService;
//import com.ctfo.catchservice.util.LoginCode;
//
//public class ZSHCatchInternalServiceImplTest extends ZSHLoginTest {
//	private static final Log logger = LogFactory.getLog(ZSHCatchInternalServiceImplTest.class);
//
//	private ICatchInternalService catchInternalService;
//
//	@Before
//	public void setUp() throws Exception {
//		catchInternalService = (ICatchInternalService) ctx.getBean("catchInternalService");
//	}
//	public void testAllMethod(){
//		testGetMainCardAndSubcard();
//		testGetAccountTrade();
//		testGetTradeByConsume();
//		testGetOilCardBalance();
//	}
//	
//	
//	
//	// 测试抓取余额
//	public void testGetOilCardBalance() {
//		try {
//			//testGetMainCardAndSubcard();
//			List<OilCardBalanceVo> result=	catchInternalService.getOilCardBalance(LoginCode.ZSH, "");
//			catchInternalService.updateBalance(result, LoginCode.ZSH);
//			System.out.println("查询结果数："+result.size());
//			if(!CollectionUtils.isEmpty(result)){
//				for(int i=0;i<result.size();i++){
//					OilCardBalanceVo ocb = result.get(i);
//					System.out.println("卡号："+ocb.getAsn()+"卡余额："+ocb.getBalance());
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	// 账户交易查询
//	public void testGetAccountTrade() {
//		try{
//			//testGetMainCardAndSubcard();
//			List<AccountTradeVo> result =catchInternalService.getAccountTrade(LoginCode.ZSH, "", "2015-01-27","2015-01-27", "1");
//			catchInternalService.addAccountTradeData(result, LoginCode.ZSH);
//			logger.info("抓取数据条数为:"+result.size());
//			if(!CollectionUtils.isEmpty(result)){
//				for(int i=0;i<result.size();i++){
//					AccountTradeVo ocb = result.get(i);
//					System.out.println("卡号："+ocb.getAsn());
//				}
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//
//	// 消费交易查询
//	public void testGetTradeByConsume() {
//		try{
//			//testGetMainCardAndSubcard();
//			List<TradeByConsumeVo> result = catchInternalService.getTradeByConsume(LoginCode.ZSH,"","2015-01-27","2015-01-27");
//			catchInternalService.addTradeByConsumeData(result, LoginCode.ZSH);
//			logger.info("抓取到数据为："+result.size());
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	};
//	// 获取主卡子卡
//	@Test
//	public void testGetMainCardAndSubcard() {
//		try {
//			// 获取中主卡及主卡下的子卡接口
//			List<MainCardAndSubcardVo> queryResult = (List<MainCardAndSubcardVo>) catchInternalService.getMainCardAndSubcard(LoginCode.ZSH, "", "");
//			//catchInternalService.addMainCardAndSubcardByHostType(LoginCode.ZSH);
//			//System.out.println("主卡子卡数量："+queryResult.size());
////			int i = 0;
////			for (MainCardAndSubcardVo m : queryResult) {
////				String isMaster = m.getIsMaster();
////				ICCardMain icCardMain = new ICCardMain();
////				ICCard icCard = new ICCard();
////				if ("是".equals(isMaster)) {
////					logger.info("主卡卡号：" + m.getAsn());
////				} else {
////					i++;
////					logger.info("子卡卡号：" + m.getAsn() + "----" + i);
////				}
////			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}
