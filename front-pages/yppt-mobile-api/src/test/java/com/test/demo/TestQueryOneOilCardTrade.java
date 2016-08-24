package com.test.demo;

import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryOneOilCardTradeReq;
import com.test.util.BaseTestUnti;

public class TestQueryOneOilCardTrade {
	public static void main(String[] args) {
		try {
			QueryOneOilCardTradeReq body = new QueryOneOilCardTradeReq();	  
			
			body.setUserId("1a347f0e-803f-48b2-a6ce-ff91d456b8ed");
			body.setCardNum("1000113500003019045");
			body.setPage(3);
			body.setPageSize(10);
			body.setImageClass("1111111");
			body.setTokenId("123123123");
			body.setUserName("zhangsan");
			body.setUserType("VIP");
			body.setVersion("3.0");
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300005", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
	        System.out.println("http返回结果：" + result);
	       
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
