//package com.ypptcatch.test;
//
//import java.util.List;
//import java.util.UUID;
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
//import com.ctfo.yppt.baseservice.card.beans.ICCard;
//import com.ctfo.yppt.baseservice.card.beans.ICCardMain;
//
//public class CatchInternalServiceImplTest extends LoginTest {
//	private static final Log logger = LogFactory.getLog(CatchInternalServiceImplTest.class);
//
//	private ICatchInternalService catchInternalService;
//
//	@Before
//	public void setUp() throws Exception {
//		catchInternalService = (ICatchInternalService) ctx.getBean("catchInternalService");
//	}
//
//	// 测试抓取余额
//	public void testGetOilCardBalance() {
//		try {
//			List<OilCardBalanceVo> result=	catchInternalService.getOilCardBalance(LoginCode.ZSY, "");
//			
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
//	
//	public void testGetAccountTrade() {
//		try{
//			List<AccountTradeVo> result =catchInternalService.getAccountTrade(LoginCode.ZSY, "", "2015-01-20", "2015-01-26", "1");
//			catchInternalService.addAccountTradeData(result,LoginCode.ZSY);
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
//	@Test
//	public void testGetTradeByConsume() {
//		try{
//			List<TradeByConsumeVo> list =catchInternalService.getTradeByConsume(LoginCode.ZSY,"","2015-01-01","");
//			catchInternalService.addTradeByConsumeData(list,LoginCode.ZSY);
//			for(int i=0;i<list.size();i++){
//				System.out.println(list.get(i).getCardAsn());
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	};
//
//	// 获取主卡子卡
//	public void testGetMainCardAndSubcard() {
//		try {
//			// 获取中主卡及主卡下的子卡接口
//			List<MainCardAndSubcardVo> queryResult = (List<MainCardAndSubcardVo>) catchInternalService.getMainCardAndSubcard(LoginCode.ZSY, "", "");
//			// 添加数据 (含有主卡与子卡的信息)
//			String mainCardId = "";// 暂存主卡ID
//			int i = 0;
//			for (MainCardAndSubcardVo m : queryResult) {
//				String id = UUID.randomUUID().toString();
//				String cardNumber = m.getAsn();
//				String state = m.getCardStatus();
//				String ctrdituser = m.getDriverName();
//				String isMaster = m.getIsMaster();
//				ICCardMain icCardMain = new ICCardMain();
//				ICCard icCard = new ICCard();
//				// System.out.println("卡号："+m.getAsn()+"是否主卡："+m.getIsMaster()+"卡状态："+m.getCardStatus());
//				// 存入主卡表
//				if ("是".equals(isMaster)) {
//					icCardMain.setId(id);
//					icCardMain.setCardNumber(cardNumber);
//					icCardMain.setOpencardofficecode("CARD-TYPE-01");// 中石化：CARD-TYPE-02
//					icCardMain.setOpencardofficename("中石油");
//					icCardMain.setCreatedTime(System.currentTimeMillis());// 创建时间
//					icCardMain.setAccountId("sjzqyh");// 数据来源于TB_IC_CARD_SYSTEM_CONFIG表
//					icCardMain.setCtrdituser("".equals(ctrdituser) ? "--" : ctrdituser);
//					icCardMain.setCardType("CARD-TYPE-01");
//					icCardMain.setState(state.equals("正常状态") ? "T" : "F");
//					// mainCardId=iCCardMainManager.add(icCardMain);
//					// System.out.println("主卡ID："+mainCardId);
//					logger.info("主卡卡号：" + icCardMain.getCardNumber());
//				} else {
//					i++;
//					// 存入子卡表
//					icCard.setId(id);
//					icCard.setCardNumber(cardNumber);
//					icCard.setParentId(mainCardId);
//					icCard.setOpencardofficeid("CARD-TYPE-01");
//					icCard.setCtrdituser("".equals(ctrdituser) ? "--" : ctrdituser);
//					icCard.setState(state.equals("正常状态") ? "T" : "F");
//					icCard.setCardType("CARD-TYPE-01");
//					icCard.setCreatedTime(System.currentTimeMillis());
//					// iCCardManager.add(icCard);
//					logger.info("子卡卡号：" + icCard.getCardNumber() + "----" + i);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}
