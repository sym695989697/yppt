package com.ctfo.base.action.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.base.service.IService;
import com.ctfo.base.service.beans.SimpleCode;
import com.ctfo.base.service.simplecode.ICodeService;
import com.ctfo.common.models.PaginationResult;

@Controller
@Scope("prototype")
public class CodeController extends BaseControler {

	private static Log logger = LogFactory.getLog(CodeController.class);

	private ICodeService codeService;
	
	private CodeController(){
		
		model = new SimpleCode();
		
	}
	
	//获取全部码表数据
		@ResponseBody
		public PaginationResult getAllCodeList(){
			try{
				String modelName = model.getClass().getName();
				Map<String,String> equal = requestParam.getEqual()==null?new HashMap<String,String>():requestParam.getEqual();
				equal.put("modelName", modelName);//key与service层的key保持一致
				requestParam.setEqual(equal);
				result.setData(codeService.queryList(requestParam));
				
			}catch(Exception e){
				forwardError((e.getMessage()==null||e.getMessage().isEmpty()||e.getMessage().equals(EMPTY_STRING))
				?"查询代码信息异常!":e.getMessage());
				logger.error("查询代码信息异常",e);
			}
			return result;
		}
		@ResponseBody
		public String updateCode(){
			String flag="1";
			try{
				flag = codeService.updateCode(model);
				
			}catch(Exception e){
				logger.error("修改代码信息异常",e);
			}
			return flag;
		}
		//获取有效码表数据
		@ResponseBody
		public PaginationResult getEnableCodeList(){
			try{
				String modelName = model.getClass().getName();
				Map<String,String> equal = requestParam.getEqual()==null?new HashMap<String,String>():requestParam.getEqual();
				equal.put("modelName", modelName);//key与service层的key保持一致
				requestParam.setEqual(equal);
				//添加过滤条件
				Map map = new HashMap();
				requestParam.setInMap(map);
				
				result.setData(codeService.queryList(requestParam));
				
			}catch(Exception e){
				forwardError((e.getMessage()==null||e.getMessage().isEmpty()||
						e.getMessage().equals(EMPTY_STRING))?"查询代码信息异常!":e.getMessage());
				logger.error("查询代码信息异常",e);
			}
			return result;
		}
		
		/**
		 * 修改编码状态
		 */
		public PaginationResult modifyStatus(String id, String stats){
			try{
				codeService.modifyStatus(id, stats, op);
				result.setDataObject(IService.OPER_SUCCESS);
				result.setMessage("操作成功!");
			}catch(Exception e){
				result.setDataObject(IService.OPER_ERROR);
				logger.error("修改状态异常!",e);
			}
			return result;
		}
		
	
	@Resource(name="codeService", description="码表管理service")
	public void setCodeService(ICodeService codeService) {
		this.codeService = codeService;
		this.service = codeService;
	}
	
	/**
	 * 查询编码
	 */
	@ResponseBody
	public SimpleCode queryByCode(String code) {
		SimpleCode simpleCode = codeService.queryByCode(code);
		return simpleCode;
	}
}
