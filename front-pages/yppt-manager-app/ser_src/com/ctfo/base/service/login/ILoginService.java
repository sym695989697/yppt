package com.ctfo.base.service.login;


import java.util.List;

import com.ctfo.common.models.Index;
import com.ctfo.csm.annotations.AnnotationName;
import com.ctfo.csm.uaa.dao.beans.UAAOrg;
import com.ctfo.csm.uaa.dao.beans.UAAOrgRole;
import com.ctfo.csm.uaa.dao.beans.UAAPermission;
import com.ctfo.csm.uaa.dao.beans.UAASystem;
import com.ctfo.csm.uaa.dao.beans.UAAUser;
import com.ctfo.csm.uaa.intf.support.Operator;
import com.ctfo.csm.uaa.meta.beans.UAARoleMeta;
/**
 * 
 * 登录管理接口类
 *
 * @version 1.0
 * @author XUBAO
 * @date    2013-12-26
 * @since JDK1.6
 */
@AnnotationName(name = "登陆管理")
public interface ILoginService {

	/**
	 * 根据当前角色,获取该角色下的所有权限
	 * @param roleId
	 * @param systemId
	 * @param op
	 * @return List<UAAPermission>
	 * @author XUBAO
	 * @date  2014-03-28
	 */
	List<UAAPermission> queryPermissionList(String roleId,String systemId, Operator op);
	
	
	/**
	 * 获取当前用户所有角色集合
	 * @param systemSign
	 * @param userId
	 * @param op
	 * @return List<UAAOrgRole>
	 * @author XUBAO
	 * @date  2014-04-01
	 */
	List<UAARoleMeta> queryOrgRoleList(String systemSign,String userId,Operator op);
	
	
	/**
	 * 根据登录用户名获取用户信息
	 * @param loginName
	 * @param op
	 * @return UAAUser
	 * @author XUBAO
	 * @date  2014-04-01
	 */
	UAAUser queryUserByUserLogin(String userLogin,Operator op);
	
	
	/**
	 * 根据组织Id获取组织信息
	 * @param orgId
	 * @param op
	 * @return UaaOrg
	 * @author XUBAO
	 * @date  2014-04-01
	 */
	UAAOrg queryOrgById(String orgId,Operator op);
	
	/**
	 * 根据角色Id获取角色信息
	 * @param roleId
	 * @param op
	 * @return UAAOrgRole
	 * @author XUBAO
	 * @date  2014-04-01
	 */
	UAAOrgRole queryOrgRoleById(String roleId,Operator op);

	UAASystem querySystemBySystemSign(String systemSgin, Operator op);

    /**
     * 检查原密码是否正确
     * @param userId
     * @param userLogin
     * @param oldPasswd
     * @param newPasswd
     * @param op
     * @return
     */
	boolean checkPassWord(String userId,String userLogin, String oldPasswd,Operator op);
    /**
     * 修改密码
     * @param userId
     * @param userLogin
     * @param oldPasswd
     * @param newPasswd
     * @param op
     * @return
     */
	String modifyPassword(String userId,String userLogin, String oldPasswd, String newPasswd,Operator op);


	boolean isModifyPassWord(String id, Operator op);
	
	/**
	 * 
	 * @param index 传入新创建的对象
	 * @return 
	 */
	public String queryLoginUserInfo(Index index);
}
