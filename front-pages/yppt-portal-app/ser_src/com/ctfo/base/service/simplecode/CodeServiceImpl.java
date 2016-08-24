package com.ctfo.base.service.simplecode;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.ServiceImpl;
import com.ctfo.base.service.beans.SimpleCode;
import com.ctfo.base.service.beans.SimpleCodeExampleExtended;
import com.ctfo.base.service.intf.ISimpleCodeManager;
import com.ctfo.csm.annotations.AnnotationName;
import com.ctfo.csm.uaa.intf.support.Operator;

/**
 * 码表相关业务服务
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午05:10:12
 *
 */
@Service("codeService")
@AnnotationName(name = "码表相关业务服务")
public class CodeServiceImpl extends ServiceImpl implements ICodeService{
		
	private static Log logger = LogFactory.getLog(CodeServiceImpl.class);
	
	private CodeServiceImpl(){
	}
	
	public List<SimpleCode> getAllCodeList(){
		final ISimpleCodeManager manager = getRemoManager(ISimpleCodeManager.class);
		Operator op = new Operator();
		SimpleCodeExampleExtended exampleExtended = new SimpleCodeExampleExtended();
		
		try {
			return manager.getList(exampleExtended);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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


	public boolean checkExist(Object model, String fieldName, Operator op) {
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
	
	
}
