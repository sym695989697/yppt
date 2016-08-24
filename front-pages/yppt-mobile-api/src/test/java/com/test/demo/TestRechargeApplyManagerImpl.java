/**
 * 
 */
package com.test.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.RechargeApplyReq;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyDetail;
import com.test.util.BaseTestUnti;


/**
 * 
 * 
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi <br>
 * 功能： <br>
 * 描述： <br>
 * 授权 : (C) Copyright (c) 2011 <br>
 * 公司 : 北京中交兴路车联网科技有限公司 <br>
 * -----------------------------------------------------------------------------
 * <br>
 * 修改历史 <br>
 * <table width="432" border="1">
 * <tr>
 * <td>版本</td>
 * <td>时间</td>
 * <td>作者</td>
 * <td>改变</td>
 * </tr>
 * <tr>
 * <td>1.0</td>
 * <td>2015年2月4日</td>
 * <td>yaoshenghua</td>
 * <td>创建</td>
 * </tr>
 * </table>
 * <br>
 * <font color="#FF0000">注意: 本内容仅限于[北京中交兴路车联网科技有限公司]内部使用，禁止转发</font> <br>
 * 
 * @version 
 * 
 * @author yaoshenghua
 * 
 * @since JDK1.6
 * 
 */
public class TestRechargeApplyManagerImpl extends TestCase{
	
	/**
	 * <p>
	 * </p>
	 * @author yaoshenghua
	 * @date 2015年2月4日 上午4:47:49
	 * @see 
	 */
	@Test
	public void testExecute(){
		
		RechargeApplyReq req = new RechargeApplyReq();//页面请求充值的bean
		req.setCardNum("2");
		req.setMoney("0.00");
		req.setUserId("fcc3cd9e-0511-41b0-86f8-302b4fe6d59a");
		req.setUserLogin("18910368325");
		req.setMobile("18910368325");
		req.setTotalMoney("200");
		req.setCoinCount("20000");
		req.setMoney("18000");
		req.setUserId("f7a8fc18-2fad-4c51-bae8-9bf80c681327");
		req.setUserLogin("13200011112");
		req.setMobile("13200011112");
		req.setTotalMoney("18000");
//		req.setCoinCount(50000000);
		req.setUserName("清洁工");
		
		ICRechargeApplyDetail details = new ICRechargeApplyDetail();
		details.setCardId("1000113500002700612");
		details.setCardNo("1000113500002700612");
		details.setCardType("01");
		details.setVehicleNo("京A012G");
		details.setVehicleColor("4");
		details.setMoney(new BigDecimal(100));
		details.setCardAreaCode("410000-ZSY");
		details.setParentCardNumber("1000113500002493297");
		List<ICRechargeApplyDetail> items = new ArrayList<ICRechargeApplyDetail>();
		items.add(details);
		ICRechargeApplyDetail details2 = new ICRechargeApplyDetail();
		details2.setCardId("1000113500002700616");
		details2.setCardNo("1000113500002700616");
		details2.setCardType("01");
		details2.setVehicleNo("京N012F");
		details2.setVehicleColor("2");
		details2.setMoney(new BigDecimal(100));
		details2.setCardAreaCode("410000-ZSY");
		details2.setParentCardNumber("1000113500002493297");
		items.add(details2);
		req.setRechargeApplyDetails(items);
		
		
		String url = TestConfig.url + BaseTestUnti.getParam("Y300011", req);
		String result;
		try {
			System.out.println("************url:"+url);
			result = BaseTestUnti.getRetureFromUrl(url);
			System.out.println("***********"+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
