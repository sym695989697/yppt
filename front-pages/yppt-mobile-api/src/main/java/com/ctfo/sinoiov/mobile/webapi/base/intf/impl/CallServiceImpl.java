package com.ctfo.sinoiov.mobile.webapi.base.intf.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctfo.file.boss.IMongoService;
import com.ctfo.sinoiov.mobile.webapi.base.filter.IUAATokenAuthenticationService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.ICallService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.plugin.WebApiServiceLog;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Index;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.BaseBeanReq;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.BeanUtil;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants;
import com.ctfo.sinoiov.mobile.webapi.util.MD5Utils;
import com.ctfo.sinoiov.mobile.webapi.util.PropertyUtils;
import com.ctfo.util.DateUtil;
/**
 * 
 *@Title:基础类  
 *@Description:用于用户登陆信息校验
 *@Author:fx  
 *@Since:2015-1-20  
 *@Version:1.1.0
 */
@Service
public class CallServiceImpl implements ICallService {
	
	protected static final Log log = LogFactory.getLog(CallServiceImpl.class);
	@Autowired(required=false)
	private IMongoService<WebApiServiceLog> mongoService;
	
	@Autowired(required=false)
	private IUAATokenAuthenticationService tokenAuthenticationService;

	private Map<String, IJsonService> services;		//接口映射
	
//	private static String RESULT_SUCCESS = "0";	//成功
//	private static String RESULT_FAIL = "1";	//失败
	
	/**
	 * 接口调用和响应入口方法
	 */
	@Override
	public String call(String args, Object... obj) {
		//1.参数转换,获取服务编码
		//2.token校验是否登录
		//3.具体接口的参数转换
		//4.接口服务参数校验
		//5.服务调用，记录日志，返回结果
		String request = "";
		String response = "";
		String commond = "";
		String status = "failed";
		Parameter result = new Parameter();//加密前的对象
		Head head = new Head();
		Body body = null;
		
		try{
			if(StringUtils.isEmpty(args)){
				request = "";
				throw new ClientException("E000001", ErrorMsgConstants.Common.E000001);
			}
			request = args.trim();//带签名的Json字符串
			Parameter param = convertJsonToParameter(request.trim());//转化为Json对象
			commond = param.getHead().getServCode();//服务编码
			head.setSequenceNum(param.getHead().getSequenceNum());
			/*if(!this.validateSign(request, param.getSign())){//验签失败
				throw new ClientException("E000002", ErrorMsgConstants.Common.E000002);
			}*/
			//验签成功，调用业务接口
			IJsonService service = services.get(commond);
			if (service == null) {
				throw new ClientException("E000003", ErrorMsgConstants.Common.E000003);
			}
			param = service.convertParameter(request);
//			//tokenId 校验
			BaseBeanReq tokenIdReq = (BaseBeanReq)param.getBody();
			//tokenIdReq.setUserId("33830fe7-25ba-4bb3-8187-079989ac0b28");
			//tokenIdReq.setMemberId("f575a230-f5ed-4693-a574-c112ea1baaf3");
			if(StringUtils.isNotBlank(tokenIdReq.getTokenId())){
				validateTokenId(tokenIdReq);
			}
			service.validate(param);
			//调用后台业务接口，得到Parameter对象
			body = service.execute(param, obj);
			result.setBody(body);
			head.setResult(PublicStaticParam.RESULT_SUCCESS);
			head.setSequenceNum(param.getHead().getSequenceNum());
			status = "success";
		}catch (NullPointerException e) {
			log.error(e.getMessage(), e);
			head.setResult(PublicStaticParam.RESULT_FAIL);
			head.setErrorMessage(ErrorMsgConstants.Common.E000007);
		} catch (ClientException e) {
			log.error(e.getMessage(), e);
			head.setResult(PublicStaticParam.RESULT_FAIL);
			head.setErrorMessage(e.getMessage());
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
			head.setResult(PublicStaticParam.RESULT_FAIL);
			head.setErrorMessage(ErrorMsgConstants.Common.E000008);
		}finally{
			//设置sign为签名KEY
			head.setCallTime(String.valueOf(System.currentTimeMillis()));
			result.setHead(head);
			result.setSign(PropertyUtils.getValueByKey("SIGN_KEY"));
			JsonConfig jc = new JsonConfig();
			if(status.equalsIgnoreCase("failed") || body == null){
				jc.setExcludes(new String[]{"servCode", "body"});
			}
			if(status.equalsIgnoreCase("success")){
				jc.setExcludes(body == null?new String[]{"servCode", "errorMessage", "body"}:new String[]{"servCode", "errorMessage"});
			}
			jc.setIgnoreDefaultExcludes(false); 
			//转化为Json字符串
			response = JSONObject.fromObject(result, jc).toString();
			//将Json字符串MD5加密
			String newSign = MD5Utils.getDefaultMd5String(response);
			//设置sign为MD5字符串
			result.setSign(newSign);
			//转化为Json字符串
			response = JSONObject.fromObject(result, jc).toString();
			
			WebApiServiceLog serviceLog = new WebApiServiceLog();
			serviceLog.setId(UUID.randomUUID().toString());
			serviceLog.setAction(commond);
			serviceLog.setHandleTime(DateUtil.getDateStr(DateUtil.DEFAULT_FORMATSTR, new Date()));
			serviceLog.setRequest(request);
			serviceLog.setResponse(response);
			serviceLog.setState(status);
			serviceLog.setChannelCode("Mobile");
			//this.logService.saveLog(serviceLog);
			try {
				mongoService.setDatasource("LOGS");
				mongoService.save(serviceLog);
			} catch (Exception e) {
				log.error("保存调用日志至mongoDB时发生异常：" + e);
			}
		}
		return response;
	}
	
	/**
	 * tokenId有效性校验
	 * @param tokenIdReq  
	 * @Description:
	 */
	private void validateTokenId(BaseBeanReq tokenIdReq) {
		try {
			String tokenId = tokenIdReq.getTokenId();
			if(StringUtils.isBlank(tokenId )){
				throw new ClientException(ErrorMsgConstants.Common.E000013, "tokenId不能为空!");
			}
			//验证tokenId 有效性
			log.info(">>>>>>>>>>>>>>>>>根据tokenId验证用户数据<<<<<<<<<<<<<<");
			log.info(">>>>>>>>>>>>>>>>>调用服务开始,开始时间["+new Date()+"]<<<<<<<<<<<<<<");
			String  userLoginName = tokenAuthenticationService.validateTokenId(tokenId);
			log.info(">>>>>>>>>>>>>>>>>调用服务开始,结束时间["+new Date()+"],用户登录名["+userLoginName+"]<<<<<<<<<<<<<<");
			if(StringUtils.isBlank(userLoginName)){
				log.info(">>>>>>>>>>>>>>>>>用户登录名为空<<<<<<<<<<<<<<");
				throw new ClientException(ErrorMsgConstants.Common.E000013, "E000013:获取用户登录信息异常");
			}else{
				log.info(">>>>>>>>>>>>>>>>>用户登录名不为空,验证有效<<<<<<<<<<<<<<");
			}
			//获取用户数据
			Index index = null;
			/*try {
				index = tokenAuthenticationService.queryUserInfoByUserLogin(userLoginName);
//				將用戶信息登錄信息与请求线程关联
//				TokenAuthenticationUtil.put(index);
				BeanUtil.copyPropertiesWithTypeCast(index, tokenIdReq);
				tokenIdReq.setUserId(index.getUserId());
				tokenIdReq.setUserName(index.getUserName());
				tokenIdReq.setUserType(index.getUserType());
				tokenIdReq.setMemberId(index.getMemberId());
				tokenIdReq.setMemberName(index.getMemberName());
				tokenIdReq.setMemberType(index.getMemberType());
				tokenIdReq.setCheckStatus(index.getCheckStatus());
				tokenIdReq.setMobile(index.getMobile());
			} catch (Exception e) {
				//获取用户数据异常，返回到客户端，服务器获取用户数据异常
				if(StringUtils.isBlank(userLoginName)){
					throw new ClientException(ErrorMsgConstants.Common.E000013, "E000013:获取用户登录信息异常");
				}
			}*/
		} catch (ClientException e) {
			throw e;
		}catch(Exception e){
			throw new ClientException(ErrorMsgConstants.Common.E000013, "E000013:获取用户登录信息异常");
		}
	}

	/**
	 * 将Json字符串转化为对象
	 * @param json
	 * @return
	 */
	private Parameter convertJsonToParameter(String json){
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(json),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005", ErrorMsgConstants.Common.E000005);
		}
	}

	/**
	 * 验证MD5
	 * @param json Json字符串
	 * @param signValue 从Parameter对象中取出的签名字符串
	 * @return
	 */
	private boolean validateSign(String json, String signValue){
		try{
			//将签名字符串替换为签名KEY
			String sourceStr = json.replaceAll(signValue, PropertyUtils.getValueByKey("SIGN_KEY"));
			//MD5加密
			String md5Str = MD5Utils.getDefaultMd5String(sourceStr);
			//比较MD5字符串
			if(md5Str.equalsIgnoreCase(signValue)){
				return true;
			}else{
				throw new ClientException("E000002", ErrorMsgConstants.Common.E000002);
			}
		}catch(Exception e){
			throw new ClientException("E000002", ErrorMsgConstants.Common.E000002);
		}
	}

	public Map<String, IJsonService> getServices() {
		return services;
	}

	public void setServices(Map<String, IJsonService> services) {
		this.services = services;
	}


}
