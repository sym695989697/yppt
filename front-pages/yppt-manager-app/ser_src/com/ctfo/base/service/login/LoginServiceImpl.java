package com.ctfo.base.service.login;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.common.models.Index;
import com.ctfo.csm.annotations.AnnotationName;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.uaa.dao.beans.UAAOrg;
import com.ctfo.csm.uaa.dao.beans.UAAOrgRole;
import com.ctfo.csm.uaa.dao.beans.UAAPermission;
import com.ctfo.csm.uaa.dao.beans.UAASystem;
import com.ctfo.csm.uaa.dao.beans.UAAUser;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.csm.uaa.intf.support.Operator;
import com.ctfo.csm.uaa.meta.beans.UAARoleMeta;
import com.ctfo.csm.utils.Encoder;
import com.ctfo.gatewayservice.interfaces.bean.user.CRMUserBean;
import com.ctfo.gatewayservice.interfaces.service.IUserService;

/**
 * 
 * 登录管理实现类
 *
 * @version 1.0
 * @author malongqing
 * 
 * @date    2013-12-26
 * @since JDK1.6
 */

@AnnotationName(name = "登录管理")
@Service("loginService")
public class LoginServiceImpl implements ILoginService{
	private static Log logger = LogFactory.getLog(LoginServiceImpl.class);


	private IUserService loginManager = null;

	LoginServiceImpl() {
		loginManager = (IUserService) ServiceFactory.soaService(IUserService.class);
	}

	@Override
	public List<UAAPermission> queryPermissionList(String roleId,
			String systemId, Operator op) {
		List<UAAPermission> result = null;
		try {
			result = loginManager.queryPermissionList(roleId, systemId, op);

		} catch (Exception e) {
			logger.error("app service add model object fail", e);
		}
		return result;
	}

	@Override
	public List<UAARoleMeta> queryOrgRoleList(String systemSign, String userId,
			final Operator op) {
		List<UAARoleMeta> result = new ArrayList<UAARoleMeta>();
		try {
			result = loginManager.queryOrgRoleList(systemSign, userId, op);
		} catch (Exception e) {
			logger.error("app service query model object fail", e);
		}
		return result;
	}

	@Override
	public UAAUser queryUserByUserLogin(String userLogin, Operator op) {
		UAAUser user = null;
		try {
			user = loginManager.queryUserByUserLogin(userLogin, op);
		} catch (Exception e) {
			logger.error("app service query model object fail", e);
		}
		return user;
	}

	@Override
	public UAASystem querySystemBySystemSign(String systemSign, Operator op) {
		try {
			return loginManager.querySystemBySystemSign(systemSign, op);
		} catch (Exception e) {
			logger.error("app service query UAASystem fail", e);
		}
		return null;
	}

	@Override
	public UAAOrg queryOrgById(String orgId, Operator op) {
		UAAOrg org = null;
		try {
			org = loginManager.queryOrgById(orgId, op);
		} catch (Exception e) {
			logger.error("app service query model object fail", e);
		}
		return org;
	}

	@Override
	public UAAOrgRole queryOrgRoleById(String roleId, Operator op) {
		UAAOrgRole role = null;
		try {
			role = loginManager.queryOrgRoleById(roleId, op);
		} catch (Exception e) {
			logger.error("app service query model object fail", e);
		}
		return role;
	}

	/**
	 * modify password
	 */
	@Override
	public String modifyPassword(String userId,String userLogin, String oldPasswd,
			String newPasswd, Operator op) {

		//0初始值 1修改成功 2 原始密码错误 3修改失败
		String isOk = "0";
		
		try {
			
			String oldPs = Encoder.getMD5String(oldPasswd);
			String newPs = Encoder.getMD5String(newPasswd);
			
			if(loginManager.authenLogin(userLogin, oldPs)){
				if(loginManager.modifyUserPassword(userId, oldPs,newPs, op)){
					isOk = "1";
				}else{
					isOk = "3";
				}
			 } else {
			     isOk = "2";
			 }
		
		} catch (Exception e) {
			logger.error("modify password failed the log is:" + e);
		}

		return isOk;
	}
	public boolean checkPassWord(String userId,String userLogin, String oldPasswd, Operator op){
		
		boolean flag = false;
		try {
			flag =  loginManager.authenLogin(userLogin, Encoder.getMD5String(oldPasswd));
		} catch (Exception e) {
			logger.error("查询密码错误："+e);
		}
	
		return flag;
	}

	@Override
	public boolean isModifyPassWord(String id, Operator op) {
	    boolean ret = false;
	    try {
            ret = loginManager.isModifyPassWord(id, op);
        } catch (Exception e) {
            logger.error("判断是否需要修改密码时异常："+e);
        }
		return ret;
	}

	@Override
	public String queryLoginUserInfo(Index index) {
		
		String resultMsg = null;
		Operator op = new Operator();
		op.setSystemsign(index.getSystemSign());
		//用户
		try {
			logger.info("登录用户名为:" + index.getUserLogin());
		//  UAAUser user = loginManager.queryUserByUserLoginNeedCRMInfo(index.getUserLogin(),Boolean.TRUE, op );
			UAAUser user = loginManager.queryUserByUserLogin(index.getUserLogin(),op );
			if(user==null){
				throw new AAException("获取用户信息为空");
			}
			String userId = user.getId();
			logger.info("登录用户id为:" + userId);
			index.setUserId(userId);
			index.setUserName(user.getUserName());
			index.setUserType(user.getUserType());
			index.setUserOwningOrgId(user.getOwningOrgid());
			if(user instanceof CRMUserBean){
				CRMUserBean crmUser = (CRMUserBean)user;
				index.setUserName(crmUser.getCrmName());
				index.setUserType(crmUser.getCrmCustomerType());
				index.setCheckStatus(crmUser.getCrmCheckStatus());
				index.setMobile(crmUser.getCrmMobile());
			}
			List<UAARoleMeta> roleList = null;
			String systemSgin = index.getSystemSign();
			// 角色
			try {
				roleList = queryOrgRoleList(systemSgin ,index.getUserId(),op);
				if(roleList!=null && !roleList.isEmpty()){
					index.setRoleList(roleList);
				}
				
				//默认取第一个角色
				if(roleList==null || roleList.size() ==0){
					throw new AAException("获取用户角色信息为空");
				}
				index.setRoleId(roleList.get(0).getUaaOrgRole().getId());
				index.setRoleName(roleList.get(0).getUaaOrgRole().getName());
				op.setRoleId(roleList.get(0).getUaaOrgRole().getId());   
			} catch (Exception e) {
				logger.error("获取用户角色异常!", e);
				resultMsg = "获取用户角色信息失败，请确认拥有呼叫中心系统角色,请稍后再试或联系系统管理员";
				return resultMsg;
			}
			
			// 组织
			try {
				UAAOrg org = queryOrgById(roleList.get(0).getUaaOrgRole().getOrgid(), op);
				if(org==null){
					throw new AAException("获取用户组织信息为空");
				}
				index.setOrgId(org.getId());
				index.setOrgName(org.getOrgShortName());
			} catch (Exception e) {
				logger.error("获取组织信息异常!", e);
				resultMsg = "获取组织信息失败，请稍后再试或联系系统管理员";
				return resultMsg;
			}

			// 系统
			try {
				UAASystem system = querySystemBySystemSign(systemSgin, op);
				if(system==null){
					throw new AAException("获取系统信息为空");
				}
				index.setSystemId(system.getId());
				index.setSystemName(system.getName());
				index.setSystemSign(system.getSystemUri());
				index.setPlatform(system.getPlatform());
				index.setPlatformName("油品管理系统");
			} catch (Exception e) {
				logger.error("获取系统信息异常!", e);
				resultMsg = "获取系统信息失败，请稍后再试或联系系统管理员";
			}
			
			
		} catch (Exception e) {
			logger.error("获取用户信息异常!", e);
			resultMsg = "获取用户信息失败，请稍后再试或联系系统管理员";
			return resultMsg;
		}
		
		return resultMsg;
	}
	
}