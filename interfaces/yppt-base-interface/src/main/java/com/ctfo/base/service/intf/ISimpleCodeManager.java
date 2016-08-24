package com.ctfo.base.service.intf;

import java.util.List;

import com.ctfo.base.baseservice.IBaseManager;
import com.ctfo.base.service.beans.SimpleCode;
import com.ctfo.base.service.beans.SimpleCodeExampleExtended;
import com.ctfo.common.exception.BusinessException;

/**
* 
* 
* <p>
* -----------------------------------------------------------------------------
* <br>
* 工程名 ： yppt-base-interface <br>
* 功能：码表接口类 <br>
* 描述：码表接口类 <br>
* 授权 : (C) Copyright (c) 2011 <br>
* 公司 : 北京中交兴路车联网科技有限公司 <br>
* -----------------------------------------------------------------------------
* <br>
* 修改历史 <br>
* <table width="432" border="1">
* <tr>
* <td>版本</td>
* <td>时间</td>
* <td>作者</td>
* <td>改变</td>
* </tr>
* <tr>
* <td>3.0</td>
* <td>2014-11-4</td>
* <td>malongqing</td>
* <td>创建</td>
* </tr>
* <tr>
* <td>3.0</td>
* <td>2014-11-4</td>
* <td>malongqing</td>
* <td>接口实现</td>
* </tr>
* </table>
* <br>
* <font color="#FF0000">注意: 本内容仅限于[北京中交兴路车联网科技有限公司]内部使用，禁止转发</font> <br>
* 
* @version 3.0
* 
* @author malongqing
* 
* @since JDK1.6
* 
*/
public interface ISimpleCodeManager extends IBaseManager<SimpleCode, SimpleCodeExampleExtended>{
	
	/**
	 * 根据类型编码和编码获取单个码表对象
	 * 
	 * @param SimpleCode.typeCode
	 * 			类型编码
	 * @param SimpleCode.code
	 * 			编码
	 * @return SimpleCode
	 *			 获取的码表对象
	 * @throws BusinessException
	 *			获取过程中出现的异常信息
	 */
	public SimpleCode getSimpleCodeByTypeAndCode(String typeCode, String code) throws BusinessException;
	/**
	 * 根据编码获取单个码表对象
	 * @param code
	 * @return
	 * @throws BusinessException
	 */
	public SimpleCode getSimpleCodeByCode(String code) throws BusinessException;
	/**
	 * 根据类型编码码表对象集合
	 * 
	 * @param SimpleCode.typeCode
	 * 			类型编码
	 * @return List<SimpleCode>
	 *			 获取的码表对象集合
	 * @throws BusinessException
	 *			获取过程中出现的异常信息
	 */
	public List<SimpleCode> getSimpleCodeByType(String typeCode) throws BusinessException;

	/**
	 * 修改码表数据状态
	 * @param simpleCode
	 * @return
	 * @throws BusinessException
	 */
	public int modifySimpleCodeStatus(SimpleCode simpleCode) throws BusinessException;
	
	/**
	 * 检查码表对象在根节点和同一类型下唯一
	 * 
	 * @param type
	 * @param code
	 * @param op
	 */
	public boolean checkLegal(String type, String code) throws BusinessException;
	
	/**
	 * 复杂sql查询demo
	 */
	public void querySimpleCodeDemo() throws BusinessException;

}
