package com.ctfo.base.service.simplecode;

import java.util.List;

import com.ctfo.base.service.IService;
import com.ctfo.base.service.beans.SimpleCode;
import com.ctfo.csm.uaa.intf.support.Operator;

public interface ICodeService extends IService{
	/**
	 * 修改码表状态
	 * @param id
	 * @param status
	 * @param op
	 */
	public void modifyStatus(String id, String status,  Operator op);
	
	/**
	 *根据typeCode查询码表
	 * @param string
	 * @return 
	 */
	public List<SimpleCode> querySimpleCodeByTypeCode(String string);
	/**
	 * 修改码表
	 * @param model
	 * @return 0：成功 ，1：失败
	 */
	public String updateCode(Object model);
	
	/**
	 * 获取码表信息
	 * @param code
	 * @return
	 */
	public SimpleCode queryByCode(String code);
}
