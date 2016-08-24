package com.ctfo.base.baseservice;

import java.util.List;
import java.util.Map;

import com.ctfo.common.models.PaginationResult;


public interface IBaseManager<T,Texample> {
	
	/**
	 * 添加单个对象
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public String add(T bean) throws Exception;
	
	/**
	 * 批量添加对象
	 * @param beans
	 * @return
	 * @throws Exception
	 */
	public List<String> addBatch(List<T> beans) throws Exception;
	
	/**
	 * 删除单个对象
	 * @param bean
	 * @return 删除的数量
	 * @throws Exception
	 */
	public int remove(T bean) throws Exception;
	
	/**
	 * 批量删除对象
	 * @param beans
	 * @return 删除的数量
	 * @throws Exception
	 */
	public int removeBatch(List<T> beans) throws Exception;
	
	/**
	 * 修改对象（字段为空的部分也修改）
	 * @param bean
	 * @return 修改的数量
	 * @throws Exception
	 */
	public int update(T bean) throws Exception;
	
	
	/**
	 * 根据id获取单个对象
	 * @param bean
	 * @return 
	 * @return
	 * @throws Exception
	 */
	public T getById(T bean) throws Exception;
	
	/**
	 * 根据属性获取对象集
	 * @param exampleExtended
	 * @return
	 * @throws Exception
	 */
	public List<T> getList(Texample exampleExtended) throws Exception;
	
	/**
	 * 查询符合条件的对象集的大小
	 * @param exampleExtended
	 * @return
	 * @throws Exception
	 */
	public int count(Texample exampleExtended) throws Exception;
	
	/**
	 * 分页查询对象集
	 * @param exampleExtended
	 * @return
	 * @throws Exception
	 */
	public PaginationResult<T> paginate(Texample exampleExtended) throws Exception;
	
	/**
	 * 根据sql语句查询数据库
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryBySQL(String sql) throws Exception;
}
