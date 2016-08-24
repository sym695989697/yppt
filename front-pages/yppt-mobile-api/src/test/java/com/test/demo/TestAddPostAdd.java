package com.test.demo;

import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.AddPostAddressReq;
import com.test.util.BaseTestUnti;

public class TestAddPostAdd {
	
	public static void main(String [] args){
		AddPostAddressReq body = new AddPostAddressReq();
		
		body.setUserId("123345");
		body.setName("zhangSan");
		body.setTelNum("110");
		body.setPostcode("433325");
		body.setProvince("hebei");
		body.setCity("shijiazhuang");
		body.setCounty("beijing");
		body.setAddress("xiangxidzhi");
		
		System.out.println("http请求开始>>>>>>>>>>>>>>>");
		String url = TestConfig.url + BaseTestUnti.getParam("S200011", body);
		String result;
		try {
			result = BaseTestUnti.getRetureFromUrl(url);
			System.out.println("http返回结果：" + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.exit(0);
	}
}
