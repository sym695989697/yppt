package com.ctfo.chpt.service.iccard.messagetemplate;

import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;


/**
 * 
 * 
 * 短信模板管理类
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年2月3日    dxs    新建
 * </pre>
 */
public interface IMessageTemplateService {
	
	/**
	 * 
	 *
	 * 创建短信模板
	 *
	 * @param param
	 * @return
	 * @throws Exception
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2015年2月3日    dxs    新建
	 * </pre>
	 */
	public String createMessageTemplate(DynamicSqlParameter param) throws Exception;
	
	/**
	 * 
	 *
	 * 初始化短信模板列表
	 *
	 * @return
	 * @throws Exception
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2015年2月3日    dxs    新建
	 * </pre>
	 */
	public PaginationResult<?> initMessageTemplateGrid() throws Exception;
	
	/**
	 * 
	 *
	 * 停止短信模板
	 *
	 * @param param
	 * @return
	 * @throws Exception
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2015年2月3日    dxs    新建
	 * </pre>
	 */
	public String stopMessageTemplate(DynamicSqlParameter param) throws Exception;
	
	/**
	 * 
	 *
	 * 启动短信模板
	 *
	 * @param param
	 * @return
	 * @throws Exception
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2015年2月3日    dxs    新建
	 * </pre>
	 */
	public String startMessageTemplate(DynamicSqlParameter param) throws Exception;
	
	/**
	 * 
	 *
	 * 删除短信模板
	 *
	 * @param param
	 * @return
	 * @throws Exception
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2015年2月3日    dxs    新建
	 * </pre>
	 */
	public String deleteMessageTemplate(DynamicSqlParameter param) throws Exception;
	
	/**
	 * 
	 *
	 * 修改短信模板
	 *
	 * @param param
	 * @return
	 * @throws Exception
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2015年2月3日    dxs    新建
	 * </pre>
	 */
	public String updateMessageTemplate(DynamicSqlParameter param) throws Exception;
	
	/**
	 * 
	 *
	 * 修改短信签名
	 *
	 * @param param
	 * @return
	 * @throws Exception
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2015年2月3日    dxs    新建
	 * </pre>
	 */
	public String modifySign(DynamicSqlParameter param) throws Exception;
	
	/**
	 * 
	 *
	 * 查询短信签名
	 *
	 * @return
	 * @throws Exception
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2015年2月3日    dxs    新建
	 * </pre>
	 */
	public PaginationResult<?> querySign() throws Exception;
}
