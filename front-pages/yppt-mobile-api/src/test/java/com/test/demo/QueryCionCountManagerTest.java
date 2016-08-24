package com.test.demo;

import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryCionCountReq;
import com.test.util.BaseTestUnti;

public class QueryCionCountManagerTest {
	
    public static void main(String[] args) {
        try {
            QueryCionCountReq body = new QueryCionCountReq();     
            
            body.setUserId("7aa34f25-f313-4dc3-b948-0e658ea2831a");
            
            System.out.println("http请求开始>>>>>>>>>>>>>>>");
            String url = TestConfig.url + BaseTestUnti.getParam("Y300013", body);
            String result = BaseTestUnti.getRetureFromUrl(url);
            System.out.println("http返回结果：" + result);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
