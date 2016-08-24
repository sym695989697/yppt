package com.ctfo.common.manager.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ctfo.base.baseservice.IBaseManager;
import com.ctfo.common.dao.IDao;
import com.ctfo.common.models.PaginationResult;

public class GenericManagerImpl<T, Texample> extends AbstractManager {
	
	@Autowired  
	protected IDao idao;
	
	
	/**
	 * 添加单个对象
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public String add(T bean) throws Exception{
		
		return idao.addModel(bean);
		
	}
	
	/**
	 * 批量添加对象
	 * @param beans
	 * @return
	 * @throws Exception
	 */
	public List<String> addBatch(List<T> beans) throws Exception{
		return idao.addModelBatch(beans);
	}
	
	/**
	 * 删除单个对象
	 * @param bean
	 * @return 删除的数量
	 * @throws Exception
	 */
	public int remove(T bean) throws Exception{
		
		return idao.removeModel(bean);
		
	}
	
	/**
	 * 批量删除对象
	 * @param beans
	 * @return 删除的数量
	 * @throws Exception
	 */
	public int removeBatch(List<T> beans) throws Exception{
		
		return idao.removeModelBatch(beans);
		
	}

	/**
	 * 修改对象（字段为空的部分不修改）
	 * @param bean
	 * @return 修改的数量
	 * @throws Exception
	 */
	public int update(T bean) throws Exception{
		
		return this.updatePart(bean);
		
	}
	
	/**
	 * 修改对象（字段为空的部分也修改）
	 * @param bean
	 * @return 修改的数量
	 * @throws Exception
	 */
	public int updateAll(T bean) throws Exception{
		
		return idao.updateModelAll(bean);
		
	}
	
	/**
	 * 修改对象（字段为空的部分不修改）
	 * @param bean
	 * @return 修改的数量
	 * @throws Exception
	 */
	public int updatePart(T bean) throws Exception{
		
		return idao.updateModelPart(bean);
		
	}
	
	/**
	 * 根据bean2作为where条件，来修改bean1（字段为空的部分不修改）
	 * @param bean
	 * @return 修改的数量
	 * @throws Exception
	 */
	public int updateByOther(T bean1, T bean2) throws Exception{
		
		return idao.updateModelByOtherModel(bean1, bean2);
		
	}
	
	/**
	 * 根据id获取单个对象
	 * @param bean
	 * @return 
	 * @return
	 * @throws Exception
	 */
	public T getById(T bean) throws Exception{
		return (T) idao.getModelById(bean);
	}
	
	/**
	 * 根据属性获取对象集
	 * @param exampleExtended
	 * @return
	 * @throws Exception
	 */
	public List<T> getList(Texample exampleExtended) throws Exception{
		return (List<T>)idao.getModels(exampleExtended);
	}
	
	/**
	 * 查询符合条件的对象集的大小
	 * @param exampleExtended
	 * @return
	 * @throws Exception
	 */
	public int count(Texample exampleExtended) throws Exception{
		return idao.countModels(exampleExtended);
	}
	
	/**
	 * 分页查询对象集
	 * @param exampleExtended
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public PaginationResult<T> paginate(Texample exampleExtended) throws Exception{
		return idao.paginateModels(exampleExtended);
	}
	
	/**
	 * 根据sql语句查询数据库
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<Map> queryBySQL(String sql) throws Exception{
		return idao.queryBySQL(sql);
	}
	
	public Object queryObjectBySQL(String sqlMapName , Map<String, Object> parmaObjMap) throws Exception {
		return idao.queryObjectBySQL(sqlMapName, parmaObjMap);
	}

	public List<Object> queryListBySQL(String sqlMapName , Map<String, Object> parmaObjMap) throws Exception {
		return idao.queryListBySQL(sqlMapName, parmaObjMap);
	}
}
