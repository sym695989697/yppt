package com.ctfo.sinoiov.mobile.webapi.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ctfo.chpt.boss.beans.MemberOrg;
//import com.ctfo.chpt.iccard.intf.IUserManager;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.uaa.dao.beans.UAAUser;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Index;
import com.ctfo.sinoiov.mobile.webapi.util.HttpUtil;

/**
 * 统一Token
 * @author 徐宝
 *
 */
public class UAATokenAuthenticationFilter implements Filter {
	
	private static Log logger = LogFactory.getLog(UAATokenAuthenticationFilter.class);
	
	private String uaaURL;

//	private IUserManager userManager;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("IUAATokenAuthenticationFilter init startting..............");
		uaaURL = filterConfig.getInitParameter("uaaUrl");
		if(StringUtils.isBlank(uaaURL)){
			throw new ServletException("IUAATokenAuthenticationFilter init fail! uaaUrl");
		}
		logger.info("IUAATokenAuthenticationFilter init start success..............");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String tokenId  = request.getParameter("tokenId");
		if(tokenId == null){
			
		}
		//验证tokenId 有效性
		String  userLoginName = validateTokenId(tokenId);
		if(StringUtils.isBlank(userLoginName)){
			
		}
		//获取用户数据
		Index index = null;
		try {
			index = queryUserInfoByUserLogin(userLoginName);
			//將用戶信息登錄信息与请求线程关联
			TokenAuthenticationUtil.put(index);
		} catch (Exception e) {
			//获取用户数据异常，返回到客户端，服务器获取用户数据异常
			
		}
		chain.doFilter(request, response);
		TokenAuthenticationUtil.removeUserLogin();
	}


	private Index queryUserInfoByUserLogin(String userLogin) throws Exception {
//		Index index = new Index();
//		userManager = (IUserManager) ServiceFactory.getFactory().getService(IUserManager.class);
//		//用户
//		try {
//			UAAUser user = userManager.queryUserByUserLogin(userLogin, null );
//			if(user==null){
//				throw new AAException("获取用户信息为空");
//			}
//			String userId = user.getId();
//			logger.info("登录用户id为:" + userId);
//			
//			index.setUserId(userId);
//			index.setUserLogin(userLogin);
//			index.setUserName(user.getUserName());
//			index.setUserType(user.getUserType());
//			index.setUserOwningOrgId(user.getOwningOrgid());
//			//查询车后会员信息
//			MemberOrg member = userManager.queryChptMemberInfoByUserId(userId, null);
//			if(member != null){
//				index.setMemberId(member.getId());
//				index.setMemberName(member.getOrgName());
//				index.setMemberType(member.getMemberType());
//				index.setStaffCode(member.getStaffCode());
//			}
//			
//		} catch (Exception e) {
//			logger.error("获取用户信息异常!", e);
//			throw new Exception("获取用户信息异常");
//		}
//		return index;
		return null;
	}

	private String validateTokenId(String tokenId) {
		String res = HttpUtil.sendHttpPostResquest(uaaURL, "tokenId="+ tokenId);
		return parseUserName(res);
	}

	private String parseUserName(String res) {
//		String userName = "chpt_3001002080";//TODO 登陆人信息, 写死,待统一TOKEN 解析解决后变更
//		return userName;
		return "";
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		logger.info("IUAATokenAuthenticationFilter destroy ..............");
		uaaURL = null;
//		userManager = null;
		logger.info("IUAATokenAuthenticationFilter destroy success..............");
	}

}
