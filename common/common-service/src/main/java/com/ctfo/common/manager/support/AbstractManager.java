package com.ctfo.common.manager.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.ctfo.common.exception.BusinessException;

public abstract class AbstractManager {
	
	/**
	 * 判断参数是否为空
	 */
	protected void notNull(Object ...model)throws BusinessException{
		
		if(model == null) {
			throw new BusinessException("非法输入空bean");
		}		
		for(Object obj : model){
			if(obj==null)
				throw new BusinessException("非法输入空bean");
			if(obj instanceof String && !StringUtils.hasText((String)obj))
				throw new BusinessException("参数["+obj.getClass().getName()+"]为 [\"\"]或[\" \"]");
			//if(obj instanceof Collection && ((Collection<?>)obj).size()>LIMIT_NUM)
			//	throw new UPPException("参数["+obj.getClass().getName()+"]集合数量["+LIMIT_NUM+"]超过系统规定的上限!");
		}
	}
	
	/**
	 * 根据参数集合返回两个集合的比较结果:新增集合和删除集合
	 * @param ids 传入的参数集合
	 * @param data 从数据库中查出的数据集合
	 * @return map : 基本key为add代表新增加的集合,del代表删除的集合
	 * @throws Exception
	 */
	protected Map<String, List<String>> compareList(List<String> ids, List<String> data)throws Exception{
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> tem = new ArrayList<String>();		
		for(String id : ids){
			for(String dbid : data){
				if(dbid.equals(id)){
					tem.add(id);break;
				}
			}
		}
		//由于Arrays.asList() 返回java.util.Arrays$ArrayList， 而不是java.util.ArrayList, 所以需要下面的转换
		ids = new ArrayList<String>(ids);
		data = new ArrayList<String>(data);
		ids.removeAll(tem);		
		data.removeAll(tem);
		
		map.put("add", ids);//add	
		map.put("del", data);//del
			
		return map;
	}

}
