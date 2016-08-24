package com.test.demo;

import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryOilCardListReq;
import com.test.util.BaseTestUnti;

public class TestQueryOilCardList {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//有bug

//		try {
//			QueryOilCardListReq body = new QueryOilCardListReq();	  
//
//			body.setUserId("79855cf9-0e5a-4455-a3dd-390e5f34af32");
//			body.setCardNum("12345678");
//			body.setVehicleNum("44444422223333313422");
//			body.setPage(3);
//			body.setPageSize(10);
//			
//			System.out.println("http请求开始>>>>>>>>>>>>>>>");
//			String url = TestConfig.url + BaseTestUnti.getParam("Y300003", body);
//			String result = BaseTestUnti.getRetureFromUrl(url);
//	        System.out.println("http返回结果：" + result);
//	       
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		
		try {
			QueryOilCardListReq body = new QueryOilCardListReq();	  

		    //body.setUserId("79855cf9-0e5a-4455-a3dd-390e5f34af32");
		       body.setUserId("33830fe7-25ba-4bb3-8187-079989ac0b28");
			//	body.setCardNum("12345678");
		///	body.setVehicleNum("44444422223333313422");
			body.setPage(3);
			body.setPageSize(10);
			
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300003", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
	        System.out.println("http返回结果：" + result);
	       
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
