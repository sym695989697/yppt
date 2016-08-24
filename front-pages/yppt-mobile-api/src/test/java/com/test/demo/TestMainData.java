/*package com.test.demo;

import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.MainDataReq;
import com.test.util.BaseTestUnti;

public class TestMainData {
	public static void main(String[] args) {
		try {
			MainDataReq body = new MainDataReq();

			body.setUserId("79855cf9-0e5a-4455-a3dd-390e5f34af32");
			System.out.println("http请求开始>>>>>>>>>>>>>>>");
			String url = TestConfig.url
					+ BaseTestUnti.getParam("Y300001", body);
			String result = BaseTestUnti.getRetureFromUrl(url);
			System.out.println("http返回结果：" + result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
*/