package com.test.demo;

import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryOilCardInfoReq;
import com.test.util.BaseTestUnti;

public class TestQueryOilCardInfo {
	public static void main(String[] args) {
		try {
			QueryOilCardInfoReq body = new QueryOilCardInfoReq();	
			
			body.setCardId("2a514408-860f-451f-b19e-834684e1e984");
			body.setUserId("e4abe197-4750-4b3e-a558-78a9dfd6ed91");
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300004", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
	        System.out.println("http返回结果：" + result);
	       
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
