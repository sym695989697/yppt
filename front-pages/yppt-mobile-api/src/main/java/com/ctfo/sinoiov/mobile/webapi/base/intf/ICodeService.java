package com.ctfo.sinoiov.mobile.webapi.base.intf;


import java.util.Map;

import com.ctfo.base.service.beans.SimpleCode;


public interface ICodeService {
	
	/**
	 * 根据code 查询codeName
	 * @param typeCode 编码类型
	 * @param code 编码
	 */
	public SimpleCode querySimpleCodeByCode(String typeCode, String code);
	/**
	 * 根据code 查询codeName
	 * @param typeCode 编码类型
	 * @param code 编码
	 */
	public String queryCodeNameByCode(String typeCode, String code);
	
	/**
	 * 根据typecode编码类型 查询code 的map对象; key: code ; value name
	 * @param typeCode 编码类型
	 * @return
	 */
	public Map<String, String> queryCodeAndNameMapByTypeCode(String typeCode);
	
	/**
	 * 根据typecode编码类型 查询code 的map对象; key: code ; value SimpleCode
	 * @param typeCode 编码类型
	 * @return
	 */
	public Map<String, SimpleCode> queryCodeMapByTypeCode(String typeCode);
}
