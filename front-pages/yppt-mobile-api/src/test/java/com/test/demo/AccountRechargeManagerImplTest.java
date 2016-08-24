package com.test.demo;

import java.io.File;

import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.AccountRechargeReq;
import com.test.util.BaseTestUnti;

public class AccountRechargeManagerImplTest {
	
    public static void main(String[] args) {
        try {
        	AccountRechargeReq body = new AccountRechargeReq();     
        	body.setId("");
//        	body.setPayProof(new File("C:/Users/admin/Desktop/1.jpg"));
            body.setUserId("ea32affa-3ef1-41c7-962d-9addd9d3e7fe");
            body.setPayer("ea32affa-3ef1-41c7-962d-9addd9d3e7fe");
            body.setPayerName("malqtest");
            body.setPayAccount("1234566778");
            body.setRemittance("0.1");
            body.setPayTime("1423403802000");
            body.setPayee("3100027019200129468");
            body.setPayeeName("中交支出账户");
            
//            body.setUserId("c2f805be-17ff-43e8-83c1-604d5b20f93b");
//            body.setUserId("8d567213-e577-48a7-baae-93e59ecc5774");
//            body.setUserId("077cd6b9-89b4-4cde-ac2e-978a8466fcde");
            
            System.out.println("http请求开始>>>>>>>>>>>>>>>");
            String url = TestConfig.url + BaseTestUnti.getParam("Y300017", body);
            String result = BaseTestUnti.getRetureFromUrl(url);
            System.out.println("http返回结果：" + result);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
