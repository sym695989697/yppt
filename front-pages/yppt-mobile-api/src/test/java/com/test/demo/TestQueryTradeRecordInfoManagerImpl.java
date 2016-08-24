/**
 * 
 */
package com.test.demo;

import junit.framework.TestCase;

import org.junit.Test;

import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryTradeRecordInfoReq;
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
 * <td>2015年2月10日</td>
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
public class TestQueryTradeRecordInfoManagerImpl extends TestCase {

	@Test
	public void testExecute(){
		QueryTradeRecordInfoReq req = new QueryTradeRecordInfoReq();
		req.setUserId("03f7ba41-9294-4b1d-862c-345b3b59d6cd");
		req.setTradeType("1");
		req.setTradeId("bcd8b579-ee86-4314-b2f6-18916b222b08");
		
		String url = TestConfig.url + BaseTestUnti.getParam("Y300015", req);
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
