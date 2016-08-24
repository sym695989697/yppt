package com.ctfo.base.service;

import java.util.List;

import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;


public interface IService {
	
	//操作结果
	public static final String OPER_SUCCESS = "0";//操作成功
	public static final String OPER_FAIL = "1";//失败
	public static final String OPER_ERROR = "2";//发生异常

	
	/**
	 * 增加一个对象
	 * @param model
	 * @param operatorid ： 当前用户
	 * @return
	 */
	public PaginationResult<?> add(Object model)throws BusinessException;
	
	/**
	 * 修改一个对象
	 * @param model
	 * @param operatorid ： 当前用户
	 * @return
	 */
	public PaginationResult<?> update(Object model)throws BusinessException;
	
	/**
	 * 删除一个对象
	 * @param id
	 * @param operatorid 当前用户
	 * @return
	 */
	public PaginationResult<?> delete(Object model)throws BusinessException;
	
	/**
	 * 根据参数查询，返回一个对象
	 * @param paramObj
	 * @return
	 */
	public PaginationResult<?> queryObjectById(Object model)throws BusinessException;
	
	/**
	 * 根据参数查询信息，返回集合信息对象
	 * @param requestParam
	 * @return
	 */
	public List queryList(DynamicSqlParameter requestParam)throws BusinessException;
	
	/**
	 * 根据参数查询信息，返回包含页面信息和集合信息的综合对象
	 * @param requestParam
	 * @return
	 */
	public PaginationResult<?> queryListPage(DynamicSqlParameter requestParam)throws BusinessException;
	/**
	 * 验证对象属性是否唯一
	 * @param model
	 * @param fieldName
	 * @param op
	 * @return
	 */
	public boolean checkExist(Object model, String fieldName);
	
}
