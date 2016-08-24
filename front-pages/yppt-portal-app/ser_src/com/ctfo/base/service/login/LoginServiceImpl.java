package com.ctfo.base.service.login;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.ServiceImpl;
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
 * 登陆相关业务服务
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午05:01:13
 *
 */
@AnnotationName(name = "登陆相关业务服务")
@Service("loginService")
public class LoginServiceImpl extends ServiceImpl implements ILoginService{
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
			UAAUser user = loginManager.queryUserByUserLoginNeedCRMInfo(index.getUserLogin(),Boolean.TRUE, op );
//			UAAUser user = loginManager.queryUserByUserLogin(index.getUserLogin(),op );
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
				logger.info("登录用户名为:" + index.getUserLogin() + "的用户在crm系统中存在登录资料!");
				CRMUserBean crmUser = (CRMUserBean)user;
				index.setUserName(crmUser.getCrmName());
				index.setUserType(crmUser.getCrmCustomerType());
				index.setCheckStatus(crmUser.getCrmCheckStatus());
				index.setMobile(crmUser.getCrmMobile());
				index.setHeadPicURL(crmUser.getCrmHeadPicURL());
				index.setIdcardNo(crmUser.getCrmIdcardNo());
			}else{
				logger.info("Warning: 登录用户名为:" + index.getUserLogin() + "的用户不存在crm系统中!");
			}
			
		} catch (Exception e) {
			logger.error("获取用户信息异常!", e);
			resultMsg = "获取用户信息失败，请稍后再试或联系系统管理员";
			return resultMsg;
		}
		return resultMsg;
	}
	
}