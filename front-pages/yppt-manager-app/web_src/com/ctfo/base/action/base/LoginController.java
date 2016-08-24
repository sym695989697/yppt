package com.ctfo.base.action.base;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ctfo.base.service.login.ILoginService;
import com.ctfo.common.models.Index;
import com.ctfo.common.utils.Converter;
import com.ctfo.csm.uaa.dao.beans.UAAOrg;
import com.ctfo.csm.uaa.dao.beans.UAAPermission;
import com.ctfo.csm.uaa.dao.beans.UAASystem;
import com.ctfo.csm.uaa.dao.beans.UAAUser;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.csm.uaa.intf.support.Operator;
import com.ctfo.csm.uaa.meta.beans.UAARoleMeta;

@Component
@Controller
@Scope("prototype")
@RequestMapping("/login")
public class LoginController extends BaseControler {
	private static Log logger = LogFactory.getLog(LoginController.class);
	
	@Resource(name = "loginService", description = "service登录接口")
	private ILoginService loginService;

	private Operator op = new Operator();

	@RequestMapping("/login")
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
		request.getSession().setAttribute(Converter.SESSION_REMOTE_USER,
				userLogin);
		return this.redirectJsp("/pages/index");
	}

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
		System.out.println(type);
		try {

			List<UAAPermission> menuList = new ArrayList<UAAPermission>();
			List<UAAPermission> funList = new ArrayList<UAAPermission>();

			Index index = (Index) session.getAttribute(Converter.SESSION_INDEX);

			List<UAAPermission> list = loginService.queryPermissionList(
					index.getRoleId(), index.getSystemId(), op);
			System.out.println("["+list.size()+"]");
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

	private List<UAARoleMeta> queryRoles(String systemSign, String userId) {

		HttpSession session = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest().getSession();
		List<UAARoleMeta> roleList = loginService.queryOrgRoleList(systemSign,
				userId, op);
		if (roleList != null) {
			session.setAttribute(Converter.SESSION_ROLE_LIST, roleList);
		}
		return roleList;
	}

}
