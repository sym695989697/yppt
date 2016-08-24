package com.ctfo.gatewayservice.interfaces.service;

import java.util.List;

import com.ctfo.csm.uaa.dao.beans.UAAOrg;
import com.ctfo.csm.uaa.dao.beans.UAAOrgRole;
import com.ctfo.csm.uaa.dao.beans.UAAPermission;
import com.ctfo.csm.uaa.dao.beans.UAASystem;
import com.ctfo.csm.uaa.dao.beans.UAAUser;
import com.ctfo.csm.uaa.intf.support.Operator;
import com.ctfo.csm.uaa.meta.beans.UAARoleMeta;
import com.ctfo.gatewayservice.interfaces.bean.user.CRMUserBean;

/**
 * 
 * 用户管理接口类
 *
 * @version 1.0
 * @author xubao
 * @date 2013-12-26
 * @since JDK1.6
 */
public interface IUserService {

	/**
	 * 根据当前角色,获取该角色下的所有权限
	 * 
	 * @param roleId
	 * @param systemId
	 * @param op
	 * @return List<UAAPermission>
	 * @author malongqing
	 * @date 2014-03-28
	 */
	public List<UAAPermission> queryPermissionList(String roleId,
			String systemId, Operator op);

	/**
	 * 获取当前用户所有角色集合
	 * 
	 * @param systemSign
	 * @param userId
	 * @param op
	 * @return List<UAAOrgRole>
	 * @author xubao
	 * @date 2014-07-01
	 */
	List<UAARoleMeta> queryOrgRoleList(String systemSign, String userId,
			Operator op);

	/**
	 * 根据登录用户名获取用户信息
	 * 
	 * @param loginName
	 * @param op
	 * @return UAAUser
	 * @author xubao
	 * @date 2014-07-01
	 */
	UAAUser queryUserByUserLogin(String userLogin, Operator op);

	/**
	 * 根据组织Id获取组织信息
	 * 
	 * @param orgId
	 * @param op
	 * @return UaaOrg
	 * @author xubao
	 * @date 2014-07-01
	 */
	UAAOrg queryOrgById(String orgId, Operator op);

	/**
	 * 根据角色Id获取角色信息
	 * 
	 * @param roleId
	 * @param op
	 * @return UAAOrgRole
	 * @author xubao
	 * @date 2014-07-01
	 */
	UAAOrgRole queryOrgRoleById(String roleId, Operator op);

	/**
	 * 
	 * @Title: getUAASystemBySystemSign
	 * @Description: 通过系统标识获取系统信息
	 * @param: systemSign 系统标识
	 * @param: op 操作人员信息 private String userId; 用户Id private String roleId; 角色Id
	 *         private String systemsign; 系统标识 根据前面接口获取这些信息
	 * @return: UAASystem 系统信息
	 * @throws
	 */
	UAASystem querySystemBySystemSign(String systemSign, Operator op);

	/**
	 * 获取系统下所有权限
	 * 
	 * @param systemId
	 *            系统标识
	 * @param op
	 *            操作员
	 * @return
	 */
	List<UAAPermission> querySysytemPermissionList(String systemId, Operator op);

	/**
	 * 验证用户用户名/密码是否通过
	 * 
	 * @param userAlias
	 *            用户名
	 * @param md5password
	 *            md5加密密码
	 * @return true:成功
	 * @throws Exception
	 *             调用服务异常
	 */
	boolean authenLogin(String userAlias, String md5password) throws Exception;

	/**
	 * 修改用户密码
	 * 
	 * @param userId
	 *            登录用户统一认证UserId
	 * @param oldMD5
	 *            md5旧密码
	 * @param newMD5
	 *            md5新密码
	 * @param op
	 *            操作人
	 * @return
	 * @throws Exception
	 */
	boolean modifyUserPassword(String userId, String oldMD5, String newMD5,
			Operator op) throws Exception;

	/**
	 * 判断是否需要修改密码
	 * 
	 * @param userId
	 * @param op
	 * @return
	 * @throws Exception
	 */
	public boolean isModifyPassWord(String userId, Operator op)
			throws Exception;

	/**
	 * 根据用户查询crm数据
	 * 
	 * @param userLogin
	 * @param needQueryCrm
	 * @param op
	 * @return
	 */
	public UAAUser queryUserByUserLoginNeedCRMInfo(String userLogin,
			Boolean needQueryCrm, Operator op);
	/**
	 * 根据Id查询UaaUser
	 * 
	 * @param id uaaUserId
	 * @param op
	 * @return 
	 */
	public UAAUser queryUaaUserById(String id, Operator op);
	
	/**
	 * 根据Id查询CRMUserBean
	 * @param userId
	 * @param needQueryCrm
	 * @param op
	 * @return
	 */
	public CRMUserBean queryCRMUserByUserId(String userId, Operator op);
	/**
	 * 根据Id查询CRMUserBean
	 * @param userLogin
	 * @param needQueryCrm
	 * @param op
	 * @return
	 */
	public CRMUserBean queryCRMUserByUserLogin(String userLogin, Operator op);

}
