package com.ctfo.base.action.login;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.base.service.login.ILoginService;
import com.ctfo.common.models.Index;
import com.ctfo.common.utils.Converter;
import com.ctfo.csm.uaa.dao.beans.UAAPermission;
import com.ctfo.csm.uaa.intf.support.Operator;
import com.ctfo.pltp.common.redis.CacheSuffixInfo;
import com.ctfo.pltp.common.redis.CacheTableInfo;
import com.ctfo.pltp.common.redis.CommonCache;
import com.ctfo.util.EnvironmentUtil;

/**
 * 登录相关业务控制类
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午04:35:13
 *
 */
@Component
@Controller
@Scope("prototype")
public class LoginController extends BaseControler {
	private static Log logger = LogFactory.getLog(LoginController.class);
	
	@Resource(name = "loginService", description = "service登录接口")
	private ILoginService loginService;

	private Operator op = new Operator();

	
	public String login(String systemSgin) {
		String userLogin = request.getRemoteUser();
		logger.debug("login system----->>>" + systemSgin);
		Index index = new Index();
		// 用户
		try {
			index.setUserLogin(userLogin);
			index.setSystemSign(systemSgin);
			loginService.queryLoginUserInfo(index);
		// 系统
		} catch (Exception e) {
			logger.error("获取系统信息异常!", e);
			return forwardError("获取系统信息失败，请稍后再试或联系系统管理员");
		}
		request.getSession().setAttribute(Converter.SESSION_INDEX, index);
		request.getSession().setAttribute(Converter.SESSION_REMOTE_USER, userLogin);
		return this.redirectJsp("/pages/home");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getMenuList", produces = "application/json")
	@ResponseBody
	public List<UAAPermission> queryMenuList() {
		try {
			List<UAAPermission> menuList = session
					.getAttribute(Converter.SESSION_MENU_LIST) == null ? this
					.queryPermission("menu") : (List<UAAPermission>) session
					.getAttribute(Converter.SESSION_MENU_LIST);
			return menuList;
		} catch (Exception e) {
			logger.error("查询用户菜单异常!", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getMyPageFunction", produces = "application/json")
	@ResponseBody
	public List<UAAPermission> queryFunList(String systemId, String roleId) {
		List<UAAPermission> funList = null;
		try {
			if (StringUtils.isBlank(systemId)) {
				funList = session.getAttribute(Converter.SESSION_FUNC_LIST) == null ? this
						.queryPermission("func")
						: (List<UAAPermission>) session
								.getAttribute(Converter.SESSION_FUNC_LIST);
			} else {
				funList = this.queryPermission("func", systemId, roleId);
			}

		} catch (Exception e) {
			logger.error("查询用户权限异常!", e);
		}
		return funList;
	}
	
	@ResponseBody
	public void redirectHomePage(){
		try {
			String ksessionId = null;
			if(request.getCookies() != null) {
				for(Cookie c : request.getCookies()) {
					if("KSESSIONID".equalsIgnoreCase(c.getName())){
						ksessionId = c.getValue();
						break;
					}
				}
			}
			CommonCache.insertToRedis(CacheTableInfo.REGISTER_SOURCE_TABLE, CacheSuffixInfo.REG_SOURCE_SUFFIX + ksessionId , 
					EnvironmentUtil.getInstance().getPropertyValue("yp.loginHomePage"),5 * 60);
		} catch (Exception e) {
			logger.error("查询yp.loginHomePage:", e);
		}
	}

	private List<UAAPermission> queryPermission(String type, String systemId,
			String roleId) {

		try {
			List<UAAPermission> menuList = new ArrayList<UAAPermission>();
			List<UAAPermission> funList = new ArrayList<UAAPermission>();
			menuList = new ArrayList<UAAPermission>();
			funList = new ArrayList<UAAPermission>();
			List<UAAPermission> list = loginService.queryPermissionList(roleId,
					systemId, op);
			for (UAAPermission per : list) {
				if ("menu".equals(per.getType()))
					menuList.add(per);
				else
					funList.add(per);
			}
			if (menuList.size() > 0)
				session.setAttribute(Converter.SESSION_MENU_LIST, menuList);
			if (funList.size() > 0)
				session.setAttribute(Converter.SESSION_FUNC_LIST, funList);
			return "menu".equals(type) ? menuList : funList;

		} catch (Exception e) {
			logger.error("查询权限异常!", e);
		}

		return null;
	}

	private List<UAAPermission> queryPermission(String type) {

		try {

			List<UAAPermission> menuList = new ArrayList<UAAPermission>();
			List<UAAPermission> funList = new ArrayList<UAAPermission>();

			Index index = (Index) session.getAttribute(Converter.SESSION_INDEX);

			List<UAAPermission> list = loginService.queryPermissionList(
					index.getRoleId(), index.getSystemId(), op);
			for (UAAPermission per : list) {
				if ("menu".equals(per.getType()))
					menuList.add(per);
				else
					funList.add(per);
			}

			if (menuList.size() > 0)
				session.setAttribute(Converter.SESSION_MENU_LIST, menuList);
			if (funList.size() > 0)
				session.setAttribute(Converter.SESSION_FUNC_LIST, funList);

			return "menu".equals(type) ? menuList : funList;

		} catch (Exception e) {
			logger.error("查询权限异常!", e);
		}

		return null;
	}
	
	
}
