package com.test.demo;

//import java.io.File;

import org.junit.Test;

import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.AccountRechargeReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.AccountToOilCardReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.ApplyCreateOilCardReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.ICCardInfoModifyReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.OilIntroductionReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.PostAddressAddReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryAccountTradeInfoReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryAcountBalanceReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryCionCountReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryCreateCardDeptListReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryInAndOutListReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryOilCardListReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryOilCardTypeReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryPayeeListReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryPostAddressListReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryTradeRecordInfoReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryTradeRecordListReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryVehicleListReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.RechargeApplyReq;
import com.test.util.BaseTestUnti;

public class TestData {
	
	//查询油卡交易记录列表(ok)
	@Test
	public void testQueryOilCardTradeRecordList(){
		try {
			QueryTradeRecordListReq body = new QueryTradeRecordListReq();	  
			
			body.setUserId("1a347f0e-803f-48b2-a6ce-ff91d456b8ed");
			body.setCardNum("1000113500003019045");
			body.setPage(3);
			body.setPageSize(10);
			body.setType(0);
			body.setUserName("lisi");
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300005", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
	        System.out.println("http返回结果：" + result);
	       
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//开卡申请(ok)
	@Test
	public void testApplyCreateOilCard(){
		try {
			ApplyCreateOilCardReq body = new ApplyCreateOilCardReq();	
			
			body.setUserId("12345678");
			body.setAcceptMessage(0);
			body.setProvince("101");
			body.setCity("10101");
			body.setCounty("1010123");
			body.setAddress("beijing");
			body.setCardType("0");
			body.setTelNum("13260407984");
			body.setVehicleNum("A123123");
			body.setCardCreateDept("beijing");
			body.setUserName("abc");
			body.setIdentityCard("410482196908305913");
			body.setReceiverPhoneNum("13800138000");
			//body.setReceiverAreaId("11111");
			body.setReceivePerson("XXXX");
			body.setCommonArea("110000,120000,140000");
			
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300006", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
	        System.out.println("http返回结果：" + result);
	       
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	//查询油卡类型列表(ok)
	@Test
	public void testQueryOilCardList(){
		try {
			QueryOilCardListReq body = new QueryOilCardListReq();
			body.setUserId("e4abe197-4750-4b3e-a558-78a9dfd6ed91");
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300003", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
	        System.out.println("http返回结果：" + result);
	       
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//查询油卡类型列表(ok)
	@Test
	public void testQueryOilCardType(){
		try {
			QueryOilCardTypeReq body = new QueryOilCardTypeReq();	  
			body.setVersion("3.0");
			body.setImageClass("1111");
			body.setTokenId("123123123");
			body.setUserId("e4abe197-4750-4b3e-a558-78a9dfd6ed91");
			body.setId("2a514408-860f-451f-b19e-834684e1e984");
			body.setUserId("123456userID");
			body.setUserName("zhangsan");
			body.setUserType("VIP");
			body.setVersion("3.0");
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300007", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
			System.out.println("http返回结果：" + result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//修改IC卡绑定手机号、车牌号(ok)
	@Test
	public void testOilCardModify(){
		try {
			ICCardInfoModifyReq body = new ICCardInfoModifyReq();
			body.setUserId("e4abe197-4750-4b3e-a558-78a9dfd6ed91");
			body.setId("2a514408-860f-451f-b19e-834684e1e984");
			body.setVehicleNo("京AX0001");//
			body.setPhone("13800138000");
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300009", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
			System.out.println("http返回结果：" + result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//查询发卡机构
	@Test
	public void testQueryCreateCardDept(){
		try {
			QueryCreateCardDeptListReq body = new QueryCreateCardDeptListReq();	  
			body.setVersion("3.0");
			body.setImageClass("1111");
			body.setTokenId("123123123");
			body.setUserId("123456userID");
			body.setUserName("zhangsan");
			body.setUserType("VIP");
			body.setVersion("3.0");
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300008", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
	        System.out.println("http返回结果：" + result);
	       
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	//查询未绑定车辆信息(ok)
	@Test
	public void testQueryVehicleList(){
		try {
			QueryVehicleListReq body = new QueryVehicleListReq();	 
			body.setImageClass("11111");
			body.setPage(3);
			body.setPageSize(10);
			body.setTokenId("22222");
			body.setUserId("7fc8052f-8164-4666-b99b-0ef2fdd7edfa");
			body.setUserName("555555");
			body.setUserType("666666");
			body.setVersion("777777");
			
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300009", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
	        System.out.println("http返回结果：" + result);
	       
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	
	//查询收款人列表(ok)
	@Test
	public void testQueryPayeeList(){
		try {
			
			QueryPayeeListReq body = new QueryPayeeListReq();	
			body.setPage(0);
			body.setPageSize(10);
			body.setImageClass("11111");
			body.setTokenId("11123");
			body.setUserId("2168");
			body.setUserName("zhangsan");
			body.setUserType("vip");
			body.setVersion("3.0");
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300010", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
	        System.out.println("http返回结果：" + result);
	       
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//充值申请
	@Test
	public void testRechargeApply(){
		try {
			
			RechargeApplyReq body = new RechargeApplyReq();	
//			body.setCardId("5d194e7c-c7a1-4644-a6ac-7465c9b1a37d,20962974-f7c5-4fc8-8c3a-d973f4510c13");
			body.setUserId("a6196b85-ca90-424c-8c0e-ecb4fed92390");
			body.setCardNum("1000113500111222333");
			body.setMoney("3333,2222");
//			body.setCoinCount(4444444);
//			body.setBalance(10000);
//			body.setDGrecharge(1);
			body.setPayer("zhangsan");
			body.setPayAccountNumber("5555555555");
			body.setPayee("BossAndBank");
			body.setRemitAmount("10000");
			body.setPayTime("2014-12-02 12:12:12");
//			body.setUpload(new File("c://1.txt"));
			body.setUserName("lisi");
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300011", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
	        System.out.println("http返回结果：" + result);
	       
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	//查询账户余额(ok)
	@Test
	public void testQueryAcountBalance(){
		try {
			
			QueryAcountBalanceReq body = new QueryAcountBalanceReq();	
			
			body.setCardNum("1234567");
			body.setImageClass("111111imageclass");
			body.setTokenId("123tokenID");
			body.setUserId("f575a230-f5ed-4693-a574-c112ea1baaf3");
			body.setUserName("zhangsan");
			body.setUserType("vip");
			body.setVersion("3.0");
			
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300012", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
	        System.out.println("http返回结果：" + result);
	       
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	//查询旺金币余额(ok)
	@Test
	public void testQueryCionCount(){
		try {
			
			QueryCionCountReq body = new QueryCionCountReq();	
			body.setImageClass("111111imageclass");
			body.setTokenId("123tokenID");
			body.setUserId("f575a230-f5ed-4693-a574-c112ea1baaf3");
			body.setUserName("zhangsan");
			body.setUserType("vip");
			body.setVersion("3.0");
			
			
			
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300013", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
	        System.out.println("http返回结果：" + result);
	       
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//查询交易列表（暂行）(ok)
	@Test
	public void testQueryTradeRecordList(){
		try {
			QueryTradeRecordListReq body = new QueryTradeRecordListReq();	  
			
			body.setUserId("ac789a9b-8274-43c1-aa01-29977628cd74");
			body.setCardNum("1000113500003019045");
			body.setPage(0);
			body.setPageSize(0);
			body.setType(0);
			body.setUserName("lisi");
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300014", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
	        System.out.println("http返回结果：" + result);
	       
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	//查询交易详情(ok)
	@Test
	public void testQueryTradeRecordInfo(){
		try {
			QueryTradeRecordInfoReq body = new QueryTradeRecordInfoReq();	  
			body.setImageClass("11111imageclass");
			body.setTokenId("123123123");
			body.setTradeId("02a05e096c0041cbbe8e0e09c80b3769");
			body.setUserId("1111");
			body.setUserName("1234");
			body.setUserType("vip");
			body.setVersion("3.0");
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300015", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
	        System.out.println("http返回结果：" + result);
	       
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//资金账户交易明细(ok)
	@Test
	public void testQueryAccountTradeInfo(){
		try {
			QueryAccountTradeInfoReq body = new QueryAccountTradeInfoReq();	 
			body.setTradeId("0d1232939a8e4d5186922e27a01a0b5e");
			body.setUserId("1a347f0e-803f-48b2-a6ce-ff91d456b8ed");
			System.out.println("http请求开始>>>>>>>>>>>>>>>");

//			String url = TestConfig.url + BaseTestUnti.getParam("Y300006", body);

			String url = TestConfig.url + BaseTestUnti.getParam("Y300018", body);

			String result = BaseTestUnti.getRetureFromUrl(url);
	        System.out.println("http返回结果：" + result);
	       
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//账户充值(ok)
	@Test
	public void testAccountRechargeManager(){
		try {
			AccountRechargeReq body = new AccountRechargeReq();	 
			
			body.setUserId("1a347f0e-803f-48b2-a6ce-ff91d456b8ed");
//			body.setCardNum("123456789");
			body.setPayer("test007");
			body.setPayee("test008");
			body.setPayAccount("test@163");
			body.setRemittance("123456788");
			body.setPayTime("2014-12-13 23:11:21");
			
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300017", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
	        System.out.println("http返回结果：" + result);
	       
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//查询收支明细列表
	@Test
	public void testQueryInAndOutListManager(){
		try {
			QueryInAndOutListReq body = new QueryInAndOutListReq();	 
			
			body.setUserId("2be8ccb8-9ae7-4ec0-8ee1-3d12c7405370");
			body.setPage(0);
			body.setPageSize(5);
			body.setTradeType(0);
			
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300016", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
	        System.out.println("http返回结果：" + result);
	       
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 邮寄地址
	 */
	@Test
	public void testQueryPostAddressListManager(){
		try {
			QueryPostAddressListReq body = new QueryPostAddressListReq();	 
			
			body.setUserId("9c036e32-9d82-48b2-8641-28e81b5cab7a");
			
			
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300031", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
			System.out.println("http返回结果：" + result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddPostAddressManager(){
		try {
			PostAddressAddReq body = new PostAddressAddReq();	 
			
			body.setUserId("9c036e32-9d82-48b2-8641-28e81b5cab7a");
			
			body.setAddress("abcccc");
			body.setProvince("110000");
			body.setCity("1101000");
			body.setDistrict("1101001");
			body.setReceivePerson("abc");
			body.setPhone("13800138000");
			body.setPost("111111");
			
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300028", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
			System.out.println("http返回结果：" + result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDelPostAddressListManager(){
		try {
			QueryPostAddressListReq body = new QueryPostAddressListReq();	 
			
			body.setUserId("9c036e32-9d82-48b2-8641-28e81b5cab7a");
			
			
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300031", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
			System.out.println("http返回结果：" + result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * m
	 */
	@Test
	public void testAccountToOilCard(){
		try{
			AccountToOilCardReq body = new AccountToOilCardReq();
			body.setUserId("68e497ff-1c73-4454-9cd3-952016bb8620");
			body.setPayPwd("123456");
			body.setPhone("13800138000");
			body.setMessageCode("");
			body.setId("070e56ee-7545-4190-bb49-347f3a27bb93");
			body.setOrderNo("rc20150205104637946");
			body.setAccountNo("");
			
			
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300032", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
			System.out.println("http返回结果：" + result);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDefaultUrl(){
		try{
			OilIntroductionReq body = new OilIntroductionReq();
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300000", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
			System.out.println("http返回结果：" + result);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
