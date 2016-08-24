package com.ctfo.base.service.simplecode;

import com.ctfo.base.service.IService;
import com.ctfo.csm.annotations.AnnotationName;
import com.ctfo.csm.uaa.intf.support.Operator;
/**
 * 码表相关业务服务接口
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午05:09:21
 *
 */
@AnnotationName(name = "码表相关业务服务接口")
public interface ICodeService extends IService{
	/**
	 * 修改码表状态
	 * @param id
	 * @param status
	 * @param op
	 */
	public void modifyStatus(String id, String status,  Operator op);
}
