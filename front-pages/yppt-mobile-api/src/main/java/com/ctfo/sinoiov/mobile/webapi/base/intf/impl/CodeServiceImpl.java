package com.ctfo.sinoiov.mobile.webapi.base.intf.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.beans.SimpleCode;
import com.ctfo.base.service.beans.SimpleCodeExampleExtended;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.sinoiov.mobile.webapi.base.intf.ICodeService;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：编码查询
 * <br>
 * 描述：
 * <br>
 * 授权 : (C) Copyright (c) 2011
 * <br>
 * 公司 : 北京中交车联科技服务有限公司
 * <br>
 * -----------------------------------------------------------------------------
 * <br>
 * 修改历史
 * <br>
 * <table width="432" border="1">
 * <tr><td>版本</td><td>时间</td><td>作者</td><td>改变</td></tr>
 * <tr><td>1.0</td><td>2015-1-23</td><td>JiangXF</td><td>创建</td></tr>
 * </table>
 * <br>
 * <font color="#FF0000">注意: 本内容仅限于[北京中交兴路车联网科技有限公司]内部使用，禁止转发</font>
 * <br>
 * 
 * @version 1.0
 * 
 * @author JiangXF
 * @since JDK1.6
 */
@Service("codeService")
public class CodeServiceImpl implements ICodeService{
		
	private static Log logger = LogFactory.getLog(CodeServiceImpl.class);
	
	@Override
	public String queryCodeNameByCode(String typeCode, String code) {
		String codeName = null;
		logger.info(">>>>>>>>>>根据编码类型、编码查询编码名称<<<<<<<<<<<<<<<");
		try {
			if(StringUtils.isBlank(typeCode) || StringUtils.isBlank(code)){
				logger.info(">>>>>>>参数为空：>>>>>>编号["+code+"];编码类型["+typeCode+"];<<<<<<<<<<<<<<<");
				return codeName;
			}
			//获取后台服务
			logger.info(">>>>>>>>>>>>>根据编号["+code+"];编码类型["+typeCode+"];<<<<<<<<<<<<<<");
			SimpleCodeExampleExtended exampleExtended = new SimpleCodeExampleExtended();
			exampleExtended.createCriteria().andTypeCodeEqualTo(typeCode).andCodeEqualTo(code);
			
			logger.info(">>>>>>>>>>>>>开始查询时间<<<<<<<<<<<<<<"+new Date());
			List list = ImplementationSupport.getSimpleCodeManager().getList(exampleExtended);
			logger.info(">>>>>>>>>>>>>结束查询时间<<<<<<<<<<<<<<"+new Date());
			if(list != null && !list.isEmpty()){
				logger.info(">>>>>>>>>>>>>查询列表条数为["+list.size()+"]<<<<<<<<<<<<<<");
				SimpleCode scode = (SimpleCode) list.get(0);
				logger.info(">>>>>>>>>>>>>编码名称为["+scode.getName()+"]<<<<<<<<<<<<<<");
				codeName = scode.getName();
			}else{
				logger.info(">>>>>>>>>>>>>查询结果为空<<<<<<<<<<<<<<");
			}
		} catch (Exception e) {
			logger.error("查询所有codelist 发生异常！", e);
		}
		return codeName;
	}

	@Override
	public Map<String, String> queryCodeAndNameMapByTypeCode(String typeCode) {
		Map<String, String> codeNameMap = null;
		try {
			//获取后台服务
			SimpleCodeExampleExtended exampleExtended = new SimpleCodeExampleExtended();
			exampleExtended.createCriteria().andTypeCodeEqualTo(typeCode);
			List list = ImplementationSupport.getSimpleCodeManager().getList(exampleExtended);
			if(list != null && !list.isEmpty()){
				for (int i = 0; i < list.size(); i++) {
					SimpleCode sc = (SimpleCode)list.get(0);
					codeNameMap.put(sc.getCode(), sc.getName());
				}
			}
		} catch (Exception e) {
			logger.error("查询所有codelist 发生异常！", e);
		}
		return codeNameMap;
	}

	@Override
	public Map<String, SimpleCode> queryCodeMapByTypeCode(String typeCode) {
		Map<String, SimpleCode> codeMap = null;
		try {
			//获取后台服务
			SimpleCodeExampleExtended exampleExtended = new SimpleCodeExampleExtended();
			exampleExtended.createCriteria().andTypeCodeEqualTo(typeCode);
			List list = ImplementationSupport.getSimpleCodeManager().getList(exampleExtended);
			if(list != null && !list.isEmpty()){
				for (int i = 0; i < list.size(); i++) {
					SimpleCode sc = (SimpleCode)list.get(0);
					codeMap.put(sc.getCode(), sc);
				}
			}
		} catch (Exception e) {
			logger.error("查询所有codelist 发生异常！", e);
		}
		return codeMap;
	}

	@Override
	public SimpleCode querySimpleCodeByCode(String typeCode, String code) {
		SimpleCode codeName = null;
		try {
			if(StringUtils.isBlank(typeCode) || StringUtils.isBlank(code)){
				return codeName;
			}
			//获取后台服务
			SimpleCodeExampleExtended exampleExtended = new SimpleCodeExampleExtended();
			exampleExtended.createCriteria().andTypeCodeEqualTo(typeCode).andCodeEqualTo(code);
			List list = ImplementationSupport.getSimpleCodeManager().getList(exampleExtended);
			if(list != null && !list.isEmpty()){
				codeName = (SimpleCode)list.get(0);
			}
		} catch (Exception e) {
			logger.error("查询所有codelist 发生异常！", e);
		}
		return codeName;
	}
	
}
