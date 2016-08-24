package com.ctfo.chpt.service.icard.reconciliation;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ctfo.base.service.IService;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoHistory;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoHistoryExampleExtended;

public interface IReconciliationShService extends IService {
	/**
	 * 分页查询石化对账数据
	 * 
	 * @param requestParam
	 * @return 分页对象
	 */
	public PaginationResult<?> queryReconciliationListPage(DynamicSqlParameter requestParam);

	/**
	 * 导入石化对账文件
	 * 
	 * @param excelFile
	 *            文件流
	 * @param fileName
	 *            文件名
	 * @param request
	 *            请求对象
	 * @return 导入结果字符串说明
	 */
	public String importReconciliationFile(InputStream excelFile, String fileName, HttpServletRequest request);
	/**
	 * 获取模板对应Map
	 * @return
	 */
	public Map<String, Map<String, String>> getImportReconciliationModelMap();

	/**
	 * 获取对账数据集合
	 */
	public List<ICCardTradeInfoHistory> getIcCardTradeInfoHistorieList(String cardNo,Long tradeTime)throws Exception;

}
