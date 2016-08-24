package com.ctfo.chpt.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;

import com.ctfo.base.service.login.ILoginService;
import com.ctfo.common.models.Index;
import com.ctfo.common.utils.Converter;
import com.ctfo.csm.utils.SpringBUtils;
import com.ctfo.pltp.common.redis.CacheSuffixInfo;
import com.ctfo.pltp.common.redis.CacheTableInfo;
import com.ctfo.pltp.common.redis.CommonCache;
import com.ctfo.pltp.model.system.supports.UserVO;
import com.ctfo.util.EnvironmentUtil;
import com.ctfo.yppt.portal.service.oilcard.IOilCardsService;

public class CheckLoginFilter implements Filter{

	private static Log logger = LogFactory.getLog(CheckLoginFilter.class);
	private String timeoutURL;
	private String dataCompletionURL;
	private String[] filterURLMatchArray;
	private String unFilterUrlMatch;
	private String systemHomePage;
	private String[] unFilterJSP;
	private String[] unRedirectURL;//需要过滤,但不需要跳转的URL
	public CheckLoginFilter() {
		dataCompletionURL="";
		timeoutURL = "";
		systemHomePage = "";
		filterURLMatchArray = null;
		unFilterUrlMatch = "";
		unFilterJSP = null;
		unRedirectURL = null;
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		timeoutURL = filterConfig.getInitParameter("timeoutURL") != null ? filterConfig
				.getInitParameter("timeoutURL") : "";
		dataCompletionURL = filterConfig.getInitParameter("dataCompletionURL") != null ? filterConfig
				.getInitParameter("dataCompletionURL") : "";
		filterURLMatchArray = filterConfig.getInitParameter("filterURLMatch") != null ? filterConfig
				.getInitParameter("filterURLMatch").split(",") : new String[] {};
		unFilterUrlMatch = filterConfig.getInitParameter("unFilterUrlMatch") != null ? filterConfig
		.getInitParameter("unFilterUrlMatch") : "";
		unFilterJSP = filterConfig.getInitParameter("unFilterJSP") != null ? filterConfig
                .getInitParameter("unFilterJSP").split(";") : new String[] {};		
        unRedirectURL = filterConfig.getInitParameter("unRedirectURL") != null ? filterConfig
	    		.getInitParameter("unRedirectURL").split(";") : new String[] {};		
		systemHomePage = filterConfig.getInitParameter("systemHomePage") != null ? filterConfig
		.getInitParameter("systemHomePage") : null;		
	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		String isPath = request.getContextPath();
		String login95155 = getPropertiesValue("yp.serverName");
		String isUri = request.getRequestURI();
		boolean needFilter = false;
		boolean isLogined = false;
		try {
			for (String filterURLMatch : filterURLMatchArray) {
				if (isUri.contains(filterURLMatch) && !isUri.endsWith(unFilterUrlMatch)) {
					needFilter = true;
					break;
				}
			}
			for (String filterURLMatch : unFilterJSP) {
				if (isUri.contains(filterURLMatch)) {
					needFilter = false;
					break;
				}
			}
			/**************************获取ksessionId ticketID*************************************/
			String ksessionId = null;
			if(request.getCookies() != null) {
				for(Cookie c : request.getCookies()) {
					if("KSESSIONID".equalsIgnoreCase(c.getName())){
						ksessionId = c.getValue();
						break;
					}
				}
			}
			logger.info("----------------从Cookie中取到的ksessionId为-------------------：" + ksessionId);
			String ticket = null;
			String loginName = null;
			if(ksessionId != null) {
				try {
					ticket = CommonCache.getFromRedis(CacheTableInfo.UAA_CASE_LOGIN_4TOKEN, ksessionId);
					String ticketOld = (String) session.getAttribute(Converter.SESSION_TICKET_ID);
					if(!(ticketOld != null && ticketOld.equalsIgnoreCase(ticket))) {
						logger.info("ticketOld与ticket不同,将使用新的ticket; ticketOld:" + ticketOld + "\t ticket:" + ticket);
						session.setAttribute(Converter.SESSION_TICKET_ID, ticket);
						session.removeAttribute(Converter.SESSION_REMOTE_USER);
						session.removeAttribute(Converter.SESSION_INDEX);
						session.removeAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
						Object o = null;
						if(ticket != null) {
							o = CommonCache.getFromRedis(CacheTableInfo.UAA_CASE_LOGIN_4TOKEN, ticket, UserVO.class);
						}
						if(o != null && o instanceof UserVO) {
							UserVO user = (UserVO)o;
							loginName = user.getLoginName();
						}else{
							logger.info("获取用户信息失败!从物流的redis里获取用户数据:" + (o==null? "空" : o.getClass()));
						}
					} 
				} catch (Exception e) {
					logger.warn("调用物流交易平台获取用户信息服务发生异常!", e);
				}
				
			}
			logger.info("-------------------从redis中取到的ticket为---------------------：" + ticket);
			
			/***************************************获取登录信息************************************/
			
			//String loginName = request.getRemoteUser();
			if(loginName == null){
				loginName = request.getRemoteUser();
				if(loginName == null) {
					//从session中获取
					loginName = (String)session.getAttribute(Converter.SESSION_REMOTE_USER);
					if(loginName ==null){//第一次登录,需从cas中获取
						Object object = session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
						if(object != null){
							session.removeAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
							Assertion assertion = (Assertion)object;
							if(assertion != null && assertion.getPrincipal() != null){
								loginName = assertion.getPrincipal().getName();
							}
						}
					}
				}
			}
			logger.info("-------------------登录用户名loginName：" + loginName);
			/*****************************************保存登录信息start**************************************************/
			Index indexOld = (Index) session.getAttribute(Converter.SESSION_INDEX);
			Index index = indexOld;
			if(ticket != null && loginName != null && (indexOld == null || indexOld.getUserLogin() == null)){//统一认证用户，系统中不存在，跳转到资料补全
				logger.debug(new StringBuffer("not exist user in System for uaaUuid:")
				.append(session.getAttribute(Converter.SESSION_REMOTE_USER)));
				logger.debug(new StringBuffer("sendRedirect Uri:")
				.append(dataCompletionURL.trim()));
				//设置到session中
				session.setAttribute(Converter.SESSION_REMOTE_USER, loginName);
				index = new Index();
				index.setUserLogin(loginName);
				index.setSystemSign(getPropertiesValue("system.code"));
				ILoginService loginService = (ILoginService) SpringBUtils.getBean("loginService");
				logger.info("-------------------开始查询登录用户名loginName：" + loginName +"的登录信息");
				loginService.queryLoginUserInfo(index);
				session.setAttribute(Converter.SESSION_INDEX, index);
			}
			if(index != null && StringUtils.isNotBlank(index.getUserId())){
				isLogined = true;
			}
			/************************************保存登录信息end***************************************/
			if(isYPPTHomePage(isUri)){
				logger.info("-----------------首页登录状态检测--------------------");
				//业务跳转
				if(!isLogined){//首页未登录
					logger.info("-----------------首页登录状态检测:未登录!将跳转到登录页面首页--------------------");
					filterChain.doFilter(request, response);
					return;

				}
				try {
					IOilCardsService oilCardService = (IOilCardsService) SpringBUtils.getBean("oilCardService");
					int countCard = oilCardService.countOilCardByUserId(index.getUserId());
					if(countCard > 0){
						logger.info("-----------------首页登录状态检测:未登录!将跳转到登录后油品页面首页: /oil/oil_home.jsp--------------------");
						response.sendRedirect("/pages/oil/oil_home.jsp");
					}else{
						logger.info("-----------------首页登录状态检测:已登录,但没有卡!将跳转到登录页面首页--------------------");
						filterChain.doFilter(request, response);
						return;
					}
				} catch (Exception e) {
					logger.error("获取首页登录状态检测!", e);
				}
			}
			
			/***************************************************************************/
			if (!isUri.equals((new StringBuilder()).append(isPath).append("/").toString())
					&& !isUri.equals((new StringBuilder()).append(login95155).append(timeoutURL.trim()).toString())
					&& needFilter
					&& (session.isNew() || loginName == null || ticket == null)) {
				/*******************************************************************************************/
				if(ticket == null) {
					session.removeAttribute(Converter.SESSION_REMOTE_USER);
					session.removeAttribute(Converter.SESSION_INDEX);
					session.removeAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
					logger.info("-------------------清除SESSION_REMOTE_USER和SESSION_INDEX成功！---------------------：");
				}
				/***************************************************************************/
				logger.debug((new StringBuilder()).append("isUri:").append(isUri).toString());
				logger.debug((new StringBuilder()).append("isNew():").append(session.isNew()).append("::userName:")
						.append(session.getAttribute("userName")).toString());
				logger.debug((new StringBuilder())
						.append(".............to logout:").append(login95155).append(timeoutURL.trim()).toString());
				if(checkURLRedirect(isUri)){
					CommonCache.insertToRedis(CacheTableInfo.REGISTER_SOURCE_TABLE, CacheSuffixInfo.REG_SOURCE_SUFFIX + ksessionId , 
							EnvironmentUtil.getInstance().getPropertyValue("yp.loginHomePage"),5 * 60);
					response.sendRedirect((new StringBuilder()).append(login95155)
							.append(timeoutURL.trim()).toString());
				}
				return;
			} else {
				filterChain.doFilter(request, response);
				return;
			}
		} catch (Exception e) {
			logger.error("校验用户是否登录发生异常!------->", e);
		}
	}

	private boolean isYPPTHomePage(String uri) {
		if(systemHomePage != null && systemHomePage.equals(uri)){
			return true;
		}
		return false;
	}

	/**
	 * 校验URL是否需要跳转
	 * @param isUri
	 * @return
	 */
	private boolean checkURLRedirect(String isUri) {
		boolean redirect = true;
		if(StringUtils.isBlank(isUri)){
			return redirect;
		}
		for (int i = 0; i < unRedirectURL.length; i++) {
			if(isUri.equals(unRedirectURL[i])){
				redirect = false;
			}
		}
		return redirect;
	}

	public String getPropertiesValue(String key) throws IOException {
        return loadProperties().getProperty(key);
    }
	
    public Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = this.getClass().getResourceAsStream("/system.properties");
            properties.load(input);
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                input.close();
            } catch (IOException ioe) {
                logger.error(ioe.getMessage(), ioe);
            }
        }
        return properties;
    }
	
	@Override
	public void destroy() {
		dataCompletionURL=null;
		timeoutURL = null;
		filterURLMatchArray = null;
		unFilterUrlMatch = null;
		unFilterJSP = null;
		unRedirectURL = null;
	}
}
