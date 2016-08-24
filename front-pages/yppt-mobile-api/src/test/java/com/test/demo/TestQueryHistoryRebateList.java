package com.test.demo;

import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryHistoryRebateListReq;
import com.test.util.BaseTestUnti;

public class TestQueryHistoryRebateList {
	public static void main(String[] args) {
		try {
			QueryHistoryRebateListReq body = new QueryHistoryRebateListReq();	  
			
			body.setUserId("12345UserID");
			body.setPage(3);
			body.setPageSize(10);
			
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url + BaseTestUnti.getParam("Y300002", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
	        System.out.println("http返回结果：" + result);
	       
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
