package com.test.demo;

import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryAcountBalanceReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.QueryPayCenterTradeListReq;
import com.test.util.BaseTestUnti;

public class QueryPayCenterTradeListTest {
	
    public static void main(String[] args) {
        try {
            QueryPayCenterTradeListReq body = new QueryPayCenterTradeListReq();     
            
            body.setUserId("c2f805be-17ff-43e8-83c1-604d5b20f93b");
//            body.setUserId("8d567213-e577-48a7-baae-93e59ecc5774");
//            body.setUserId("077cd6b9-89b4-4cde-ac2e-978a8466fcde");
            body.setAccountNo("MA1423029991424554");
            body.setPage("2");
            System.out.println("http请求开始>>>>>>>>>>>>>>>");
            String url = TestConfig.url + BaseTestUnti.getParam("Z000009", body);
            String result = BaseTestUnti.getRetureFromUrl(url);
            System.out.println("http返回结果：" + result);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
