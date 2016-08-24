package com.ctfo.chpt.service.iccard.rebate;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.ctfo.base.service.IService;

import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;



public interface IRebateHistoryService extends IService {
	/**
	 * 导入人工返利
	 * @param excelFile 文件流
	 * @param fileName  文件名
	 * @param request  请求对象
	 * @return 0 正在导入；1导入失败
	 */
	public String importRebate(InputStream excelFile, String fileName,HttpServletRequest request);
	/**
	 * 分页查询人工返利记录
	 * @param requestParam 查询参数
	 * @return 分页对象
	 */
	public PaginationResult queryRebateHistoryListPage(DynamicSqlParameter requestParam);
	/**
	 * 结算到旺金币
	 * @param ids 返利id  ","分隔 ; 结尾无","
	 * @return 0:成功；1：失败;2:部分失败
	 */
	public String changePatchCurrency(String ids,String opId);
}
