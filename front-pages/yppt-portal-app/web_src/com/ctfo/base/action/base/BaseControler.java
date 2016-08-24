package com.ctfo.base.action.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ctfo.base.service.IService;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Converter;
import com.ctfo.common.utils.RequestParamsUtil;
import com.ctfo.csm.uaa.intf.support.Operator;

/**
 * 基础控制类
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午04:36:10
 *
 */
public abstract class BaseControler {
	private static Log logger = LogFactory.getLog(BaseControler.class);

	protected HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest() ;
	protected HttpServletResponse response;
	protected HttpSession session=request.getSession() ;
	protected Index index = (Index)session.getAttribute(Converter.SESSION_INDEX);
//	protected Operator op = Converter.getOperator(request);
	protected Operator op = null;
	
	private final int INTERVAL_SERVER_ERROR_CODE=500;
	protected final String EMPTY_STRING="";
	private static final String REDIRECT ="redirect:";
	private static final String SUFFIX =".jsp";

	protected IService service;
	
	protected Object model;
	
	protected String ids;
		
	protected DynamicSqlParameter requestParam = new DynamicSqlParameter();
	
	protected String message;
	
	protected PaginationResult<?> result = new PaginationResult<Object>();
	
	public BaseControler() {
		if(index == null){
			index = new Index();
		}
	}
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		RequestParamsUtil.bindParams(this,this.request,null);
	}
	
	protected String forward(String uri){
		return uri;
	}
	
	protected String forwardToJsp(String uri){
		return forward(uri)+SUFFIX;
	}
	
	public String redirect(String uri){
		return REDIRECT+uri;
	}
	
	public String redirectJsp(String uri){
		return redirect(uri)+SUFFIX;
	}
	
	final protected void setMessage(String errorMessage){
		response.setStatus(INTERVAL_SERVER_ERROR_CODE);
    	request.setAttribute("message", (errorMessage==null?"":errorMessage));
	}
	final protected String forwardError()
    {
    	return forwardToJsp("/pages/error");
    }

	final protected String forwardError(String errorMessage)
    {
		setMessage(errorMessage);
    	return forwardToJsp("/pages/error");
    }
	
	/**
	 * 添加对象
	 * @return
	 * @author  XUBAO
	 * @date    2013-12-26
	 */
	@ResponseBody
	public PaginationResult add(){
		try {			
			result = service.add(model);
		} catch (Exception e) {
			logger.error("添加信息异常!",e);
			result.setDataObject(IService.OPER_ERROR);
		}
		return result;
	}

	/**
	 * 修改对象
	 * @return
	 * @author  XUBAO
	 * @date    2013-12-26
	 */
	@ResponseBody
	public PaginationResult<?> update(){
		try {			
			result = service.update(model);
		} catch (Exception e) {
			logger.error("修改信息异常!",e);
			result.setDataObject(IService.OPER_ERROR);
		}
		return result;
	}	
	
	/**
	 * 删除对象
	 * @return
	 * @author  XUBAO
	 * @date    2013-12-26
	 */
	@ResponseBody
	public PaginationResult<?> delete(){
		try {			
			result = service.delete(model);
		} catch (Exception e) {
			logger.error("删除信息异常!", e);
			result.setDataObject(IService.OPER_ERROR);
		}
		return result;
	}

	/**
	 * 根据条件查询信息集合
	 * @return
	 * @author  XUBAO
	 * @date    2013-12-26
	 */
	@ResponseBody
	public List getList(){
		try {	
			//设置当前对象名称，用于service实现层自动化查询
			String modelName = model.getClass().getName();
			Map<String,String> equal = requestParam.getEqual()==null?new HashMap<String,String>():requestParam.getEqual();
			equal.put("modelName", modelName);//key与service层的key保持一致
			requestParam.setEqual(equal);
				
			List list = service.queryList(requestParam);
			result.setData(list);
		} catch (Exception e) {
			logger.error("查询信息集合异常!",e);
		}
		return result == null? new ArrayList() :result.getData();
	}
	
	/**
	 * 根据条件查询信息集合
	 * @return
	 * @author  XUBAO
	 * @date    2013-12-26
	 */
	@ResponseBody
	public PaginationResult<?> getListPage(){
		try {	
			
			//设置当前对象名称，用于service实现层自动化查询
			String modelName = model.getClass().getName();
			Map<String,String> equal = requestParam.getEqual()==null?new HashMap<String,String>():requestParam.getEqual();
			equal.put("modelName", modelName);//key与service层的key保持一致
			requestParam.setEqual(equal);
			result = service.queryListPage(requestParam);
		} catch (Exception e) {
			logger.error("查询信息集合异常!",e);
			result.setDataObject(IService.OPER_ERROR);
		}
		return result;
	}
	
	/**
	 * 根据主键查询信息对象
	 * @return
	 * @author  XUBAO
	 * @date    2013-12-26
	 */
	@ResponseBody
	public Object getObjectById(){
		try {			
			result = service.queryObjectById(model);
		} catch (Exception e) {
			logger.error("查询信息异常!",e);
		}
		return result == null ? null : result.getDataObject();
	}
	
	/**
	 * 以子类的配置文件中必须注入service
	 * 才能调用此类中基本增加，修改，删除，查询对象，查询分布集合的方法
	 * @param service
	 * @author  XUBAO
	 * @date    2013-12-26
	 */
	public void setService(IService service){
		this.service = service;
	}

	public PaginationResult<?> getResult() {
		return result;
	}

	public void setResult(PaginationResult<?> result) {
		this.result = result;
	}
	
	public Object getModel() {
		return model;
	}
	public void setModel(Object model) {
		this.model = model;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public DynamicSqlParameter getRequestParam() {
		return requestParam;
	}
	public void setRequestParam(DynamicSqlParameter requestParam) {
		this.requestParam = requestParam;
	}
	public String getMessage() {
		return message;
	}

}
