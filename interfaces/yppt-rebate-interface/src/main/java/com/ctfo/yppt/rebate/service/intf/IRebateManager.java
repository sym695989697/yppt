package com.ctfo.yppt.rebate.service.intf;

/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-rebate-interface
 * <br>
 * 功能：IC卡返利
 * <br>
 * 描述：
 * <br>
 * 授权 : (C) Copyright (c) 2011
 * <br>
 * 公司 : 北京中交车联科技服务有限公司
 * <br>
 * -----------------------------------------------------------------------------
 * <br>
 * 修改历史
 * <br>
 * <table width="432" border="1">
 * <tr><td>版本</td><td>时间</td><td>作者</td><td>改变</td></tr>
 * <tr><td>1.0</td><td>2015-1-20</td><td>JiangXF</td><td>创建</td></tr>
 * </table>
 * <br>
 * <font color="#FF0000">注意: 本内容仅限于[北京中交兴路车联网科技有限公司]内部使用，禁止转发</font>
 * <br>
 * 
 * @version 1.0
 * 
 * @author JiangXF
 * @since JDK1.6
 */
public interface IRebateManager {
	/**
	 * 自动生成上月返利
	 * @throws Exception
	 */
	public void autoCreateMonthRebate() throws Exception;

	/**
	 * 根据时间创建返利
	 * @param startTime 开始时间（毫秒）
	 * @param endTime 结束时间（毫秒）
	 * @param type 类型
	 * @throws Exception
	 */
	public void createTimeRebate(long startTime,long endTime,String type) throws Exception;
	
	
}
