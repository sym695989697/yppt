package com.test.demo;

import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.QueryPayCenterTradeInfoReq;
import com.test.util.BaseTestUnti;

public class QueryPayeeListListTest {
	
    public static void main(String[] args) {
        try {
            QueryPayCenterTradeInfoReq body = new QueryPayCenterTradeInfoReq();     
            body.setId("067bc84c-8661-4e99-ae4e-00fafcd4ebb4");
            
            body.setUserId("c2f805be-17ff-43e8-83c1-604d5b20f93b");
//            body.setUserId("8d567213-e577-48a7-baae-93e59ecc5774");
//            body.setUserId("077cd6b9-89b4-4cde-ac2e-978a8466fcde");
            
            System.out.println("http请求开始>>>>>>>>>>>>>>>");
            String url = TestConfig.url + BaseTestUnti.getParam("Y300010", body);
            String result = BaseTestUnti.getRetureFromUrl(url);
            System.out.println("http返回结果：" + result);
           
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
