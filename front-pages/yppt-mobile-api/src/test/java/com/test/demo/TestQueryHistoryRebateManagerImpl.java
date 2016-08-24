/**
 * 
 */
package com.test.demo;

import junit.framework.TestCase;

import org.junit.Test;

import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryHistoryRebateListReq;
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
 * <td>2015年2月5日</td>
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
public class TestQueryHistoryRebateManagerImpl extends TestCase{
	
	/**
	 * <p>
	 * </p>
	 * @author yaoshenghua
	 * @date 2015年2月5日 下午9:47:55
	 * @see 
	 */
	@Test
	public void testExecute(){
		QueryHistoryRebateListReq req = new QueryHistoryRebateListReq();
		req.setUserId("68e497ff-1c73-4454-9cd3-952016bb8620");
		req.setPage(1);
		req.setPageSize(10);
		
		String url = TestConfig.url + BaseTestUnti.getParam("Y300002", req);
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
