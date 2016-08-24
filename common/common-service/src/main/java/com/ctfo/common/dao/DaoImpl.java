package com.ctfo.common.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ctfo.common.dao.support.ImplementationSupport;
import com.ctfo.common.models.PaginationResult;

@Repository(value="idao")
public class DaoImpl<T, Texample> implements IDao<T, Texample> {

	@Override
	public String addModel(T bean) throws Exception {
		if(bean == null){
			throw new Exception("非法输入空bean");
		}
		return ImplementationSupport.addObj(bean);
	}

	@Override
	public List addModelBatch(List<T> beans) throws Exception {
		if(beans == null){
			throw new Exception("非法输入空beans");
		}
		List ids = new ArrayList();
		for(T bean : beans) {
			ids.add(this.addModel(bean));
		}
		return ids;
	}

	@Override
	public int removeModel(T bean) throws Exception {
		if(bean == null){
			throw new Exception("非法输入空bean");
		}
		return ImplementationSupport.removeObj(bean);
	}
	
	@Override
	public int removeModelBatch(List<T> beans) throws Exception {
		if(beans == null){
			throw new Exception("非法输入空beans");
		}
		int num = 0;
		for(T bean : beans) {
			num += this.removeModel(bean);
		}
		return num;
	}

	@Override
	public int updateModelAll(T bean) throws Exception {
		if(bean == null){
			throw new Exception("非法输入空bean");
		}
		return ImplementationSupport.updateObjAll(bean);
	}
	
	@Override
	public int updateModelPart(T bean) throws Exception {
		if(bean == null){
			throw new Exception("非法输入空bean");
		}
		return ImplementationSupport.updateObjPart(bean);
	}
	
	@Override
	public int updateModelByOtherModel(T bean1, T bean2) throws Exception {
		if(bean1 == null && bean2 == null){
			throw new Exception("非法输入空bean");
		}
		return ImplementationSupport.modifyObjByExample(bean1, bean2);
	}

	@Override
	public Object getModelById(T bean) throws Exception {
		if(bean == null){
			throw new Exception("非法输入空bean");
		}
		String uuid = null;
		try{
			uuid = (String) ImplementationSupport.publicCall(bean, "getId", new Class[]{}, new Object[]{});
		}catch(Exception e){
			throw new Exception("这个对象没有getId方法，不是合法的model", e);
		}
		return ImplementationSupport.getObjectById(bean, uuid); 
	}

	@Override
	public List getModels(Texample exampleExtended) throws Exception {
		if(exampleExtended == null){
			throw new Exception("非法输入空exampleExtended");
		}
		return ImplementationSupport.getObjectsByExampleExtended(exampleExtended);
	}

	@Override
	public int countModels(Texample exampleExtended) throws Exception {
		if(exampleExtended == null){
			throw new Exception("非法输入空exampleExtended");
		}
		return ImplementationSupport.countByExampleExtended(exampleExtended);
	}

	@Override
	public <T> PaginationResult<T> paginateModels(Texample exampleExtended) throws Exception {
		if(exampleExtended == null){
			throw new Exception("非法输入空exampleExtended");
		}
		return ImplementationSupport.paginate(exampleExtended);
	}

	@Override
	public List<Map> queryBySQL(String sql) throws Exception {
		return ImplementationSupport.queryBySQL(sql);
	}

	@Override
	public Object queryObjectBySQL(String sqlMapName,
			Map<String, Object> parmaObjMap) throws Exception {
		if(StringUtils.isBlank(sqlMapName)){
			throw new Exception("sqlMapName为空!请输入正确的sqlMap 名称");
		}
		return ImplementationSupport.queryObjectBySQL(sqlMapName, parmaObjMap);
	}

	@Override
	public List<Object> queryListBySQL(String sqlMapName,
			Map<String, Object> parmaObjMap) throws Exception {
		if(StringUtils.isBlank(sqlMapName)){
			throw new Exception("sqlMapName为空!请输入正确的sqlMap 名称");
		}
		return ImplementationSupport.queryListBySQL(sqlMapName, parmaObjMap);
	}
}
