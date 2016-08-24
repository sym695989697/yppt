package com.ctfo.base.service.simplecode;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.ServiceImpl;
import com.ctfo.base.service.beans.SimpleCode;
import com.ctfo.base.service.beans.SimpleCodeExampleExtended;
import com.ctfo.base.service.intf.ISimpleCodeManager;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.uaa.intf.support.Operator;


@Service("codeService")
public class CodeServiceImpl extends ServiceImpl implements ICodeService{
		
	private static Log logger = LogFactory.getLog(CodeServiceImpl.class);
	
	private ISimpleCodeManager codeManager=null;
	
	private CodeServiceImpl(){
	}
	
	public List<SimpleCode> getAllCodeList(){
		final ISimpleCodeManager manager = getRemoManager(ISimpleCodeManager.class);
		Operator op = new Operator();
		SimpleCodeExampleExtended exampleExtended = new SimpleCodeExampleExtended();
		
		try {
			return manager.getList(exampleExtended);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void modifyStatus(String id, String status, Operator op) {
		try{
			final ISimpleCodeManager manager = getRemoManager(ISimpleCodeManager.class);
			SimpleCode code = new SimpleCode();
			code.setId(id);
//			code.setid);
			manager.modifySimpleCodeStatus(code);
			
		} catch (Exception e) {			
			logger.error("修改码表状态异常", e);
		}
	}

	@Override
	public boolean checkExist(Object model, String fieldName) {
		try{
			ISimpleCodeManager manager = getRemoManager(ISimpleCodeManager.class);
			SimpleCode simple = (SimpleCode)model;
			String check = simple.getId();
			String code = simple.getCode();
			String pid = simple.getPid();
			if (StringUtils.isNotBlank(check) && check.equals(code))
				return true;// 修改的时候，如果和修改前的值一样的话，跳过查重

			SimpleCodeExampleExtended codeEE = new SimpleCodeExampleExtended();
			codeEE.createCriteria().andCodeEqualTo(code).andPidEqualTo(pid);	
			int count = manager.count(codeEE);
			
			codeEE.clear();
			codeEE.createCriteria().andCodeEqualTo(code).andPidEqualTo("0");//根类型下唯一
			int count2 = manager.count(codeEE);
			
			if(count>0 || count2>0)
				return false;
	
		} catch (Exception e) {			
			logger.error("验证码表唯一性异常", e);
		}		
		return true;
	}

	@Override
	public List<SimpleCode> querySimpleCodeByTypeCode(String string) {
		List<SimpleCode> list=new ArrayList();
		try {
			SimpleCodeExampleExtended example=new SimpleCodeExampleExtended();
			example.createCriteria().andTypeCodeEqualTo(string);
			list = getCodeManager().getList(example);
		} catch (Exception e) {
			logger.error("根据typeCode查询码表异常", e);
		}
		return list;
	}
	

	@Override
	public String updateCode(Object model) {
		String flag="1";
		try {
			SimpleCode code = (SimpleCode)model;
			getCodeManager().update(code);
			SimpleCodeExampleExtended example=new SimpleCodeExampleExtended();
			example.createCriteria().andPidEqualTo(code.getId());
			List<SimpleCode> list = getCodeManager().getList(example);
			for (int i = 0; list!=null && i < list.size(); i++) {
				list.get(i).setTypeCode(code.getCode());
				list.get(i).setTypeName(code.getName());
				getCodeManager().update(list.get(i));
			}
			flag="0";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public ISimpleCodeManager getCodeManager() {
		if(codeManager==null)
			codeManager=(ISimpleCodeManager) ServiceFactory.getFactory().getService(ISimpleCodeManager.class);
		return codeManager;
	}
	
	
	@Override
	public SimpleCode queryByCode(String code) {
		final ISimpleCodeManager manager = getRemoManager(ISimpleCodeManager.class);
		Operator op = new Operator();
		SimpleCode simpleCode = null;
		try {
			simpleCode = manager.getSimpleCodeByCode(code);
		} catch (BusinessException e) {
			logger.error("获取码表信息异常.",e);
		}
		return simpleCode;
	}
}
