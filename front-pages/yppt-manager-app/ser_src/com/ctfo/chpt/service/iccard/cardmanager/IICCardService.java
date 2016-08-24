package com.ctfo.chpt.service.iccard.cardmanager;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ctfo.base.service.IService;
import com.ctfo.base.service.beans.SimpleCode;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.yppt.baseservice.card.beans.ICCard;

public interface IICCardService extends IService {
	/**
	 * 分页查询IC卡副卡
	 * @param requestParam
	 * @return 分页对象
	 */
	public PaginationResult<?> queryViceCardListPage(DynamicSqlParameter requestParam);
	
	/**
	 * 按条件查询IC卡副卡集合
	 * @param requestParam
	 * @return List
	 */
	public List queryViceCardList(DynamicSqlParameter requestParam);
	/**
	 * 导入IC卡副卡
	 * @param excelFile 文件流
	 * @param fileName  文件名
	 * @param request  请求对象
	 * @return 0 正在导入；1导入失败
	 */
	public String importIcCard(InputStream excelFile, String fileName,HttpServletRequest request);
	/***
	 * 根据id查询副卡
	 * @param model 实体对象
	 * @return 
	 */
	public ICCard queryViceCardById(Object model);
	/**
	 * 修改副卡信息
	 * @param model 实体对象
	 */
	public void updateViceCard(Object model);
	/**
	 * 根据条件查询主卡列表
	 * @param requestParam 查询条件
	 * @return 主卡集合
	 */
	public List queryMainCardByCardAreaCode(DynamicSqlParameter requestParam);
	
	/**
	 * 批量开卡
	 * @param excelFile 文件流
	 * @param fileName  文件名
	 * @param request  请求对象
	 * @return 0 正在导入；1导入失败
	 */
	public String icCardOpen(InputStream excelFile, String fileName,
			HttpServletRequest request);
	
	/**
	 * 获取导入模板Map_enum
	 * @return
	 */
	public Map<String, Map<String, String>> getOpenCardModelMap();
	/**
	 * 更新余额
	 * @param model 
	 * @return 0 成功；-1失败
	 */
	public String updateBlance(Object model);
	
	
	
	
}
