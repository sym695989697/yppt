package com.ctfo.gatewayservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ctfo.crm.boss.beans.Customer;
import com.ctfo.crm.intf.ICustomerService;
import com.ctfo.crm.meta.beans.CustomerMetaBean;
import com.ctfo.csm.impl.multithread.TaskPool;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.uaa.dao.beans.UAAOrg;
import com.ctfo.csm.uaa.dao.beans.UAAOrgRole;
import com.ctfo.csm.uaa.dao.beans.UAAPermission;
import com.ctfo.csm.uaa.dao.beans.UAAPermissionExampleExtended;
import com.ctfo.csm.uaa.dao.beans.UAASystem;
import com.ctfo.csm.uaa.dao.beans.UAASystemExampleExtended;
import com.ctfo.csm.uaa.dao.beans.UAAUser;
import com.ctfo.csm.uaa.dao.beans.UAA_Relation_OrgRole_PermGroup;
import com.ctfo.csm.uaa.dao.beans.UAA_Relation_OrgRole_PermGroupExampleExtended;
import com.ctfo.csm.uaa.dao.beans.UAA_Relation_PermGroup_Perm;
import com.ctfo.csm.uaa.dao.beans.UAA_Relation_PermGroup_PermExampleExtended;
import com.ctfo.csm.uaa.external.intf.IUAABusinessManager;
import com.ctfo.csm.uaa.external.intf.IUAASystemManager;
import com.ctfo.csm.uaa.intf.authentication.IAuthentication;
import com.ctfo.csm.uaa.intf.support.Operator;
import com.ctfo.csm.uaa.meta.beans.UAAOrgMeta;
import com.ctfo.csm.uaa.meta.beans.UAARoleMeta;
import com.ctfo.csm.uaa.meta.beans.UAAUserMeta;
import com.ctfo.file.boss.IFileService;
import com.ctfo.gatewayservice.interfaces.bean.YpptGatewayException;
import com.ctfo.gatewayservice.interfaces.bean.user.CRMUserBean;
import com.ctfo.gatewayservice.interfaces.service.IUserService;

/**
 * 登录管理实现类
 */
@Service("IUserService")
public class UserServiceImpl implements IUserService {

	private static Log logger = LogFactory.getLog(UserServiceImpl.class);

	public List<UAAPermission> queryPermissionList(String roleId,
			String systemId, Operator op) {
		List<UAAPermission> result = null;
		try {
			// 根据roleId查权限组，根据权限组查权限
			IUAASystemManager manager = (IUAASystemManager)ServiceFactory.soaService(IUAASystemManager.class);

			// 设置角色与权限组的关联查询条件
			UAA_Relation_OrgRole_PermGroupExampleExtended roleGroupEE = new UAA_Relation_OrgRole_PermGroupExampleExtended();
			roleGroupEE.setSelectedField(UAA_Relation_OrgRole_PermGroup
					.fieldGroupId());
			roleGroupEE.createCriteria().andRoleIdEqualTo(roleId);

			// 设置权限组与权限的关联查询条件
			UAA_Relation_PermGroup_PermExampleExtended groupPermEE = new UAA_Relation_PermGroup_PermExampleExtended();
			groupPermEE.setSelectedField(UAA_Relation_PermGroup_Perm
					.fieldPermId());
			groupPermEE.createCriteria().andGroupIdIn(
					Arrays.asList(roleGroupEE));

			// 设置权限的查询条件
			UAAPermissionExampleExtended perEE = new UAAPermissionExampleExtended();
			perEE.createCriteria().andIdIn(Arrays.asList(groupPermEE))
					.andSystemIdEqualTo(systemId).andStatusEqualTo("1");

			result = manager.getUAAPermissionByExampleExtended(perEE, op);

			// 排序
			Collections.sort(result, new Comparator<Object>() {
				@Override
				public int compare(Object o1, Object o2) {
					return ((UAAPermission) o1).getOrderNum().compareTo(
							((UAAPermission) o2).getOrderNum());
				}
			});

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
			IUAABusinessManager manager = (IUAABusinessManager) ServiceFactory
					.soaService(IUAABusinessManager.class);
			List<UAAOrgRole> list = manager.getRolesBySystemSignAndUserId(
					systemSign, userId, op);
			if (list != null && list.size() > 0) {
				ArrayList<Runnable> tasks = new ArrayList<Runnable>();
				for (UAAOrgRole role : list) {
					final UAARoleMeta roleMeta = new UAARoleMeta();
					roleMeta.setUaaOrgRole(role);
					result.add(roleMeta);
					Runnable task = new Runnable() {
						@Override
						public void run() {
							IUAABusinessManager manager = (IUAABusinessManager) ServiceFactory
									.soaService(IUAABusinessManager.class);
							UAAOrgMeta orgMeta = manager.getUAAOrgById(roleMeta
									.getUaaOrgRole().getOrgid(), op);
							roleMeta.setOrgName(orgMeta.getUaaOrg()
									.getOrgShortName());
						}
					};
					tasks.add(task);
				}
				TaskPool.completeTask(tasks);
			}

		} catch (Exception e) {
			logger.error("app service query model object fail", e);
		}
		return result;
	}

	@Override
	public UAAUser queryUserByUserLoginNeedCRMInfo(String userLogin,
			Boolean needQueryCrm, Operator op) {
		UAAUser user = null;
		try {
			logger.info("-------------------开始查询用户登录信息----userLogin：" + userLogin);
			IAuthentication manager = (IAuthentication) ServiceFactory
					.soaService(IAuthentication.class);
			user = manager.getUserByAlias(userLogin);
			logger.info("-------------------查出用户登录信息userLogin：" + userLogin + "----");
			if (user == null){
				logger.info("---------------没有查出用户登录信息userLogin：" + userLogin + "----");
				return null;
			}
			if (!needQueryCrm)
				return user;
			try {
				long startTime = System.currentTimeMillis();
				ICustomerService crmManager = (ICustomerService) ServiceFactory
						.soaService(ICustomerService.class);
				CustomerMetaBean customerBean = crmManager.getCustomerById(
						user.getId(), "ICCARD");
				CRMUserBean crmUser = new CRMUserBean();
				BeanUtils.copyProperties(user, crmUser);
				if (customerBean != null
						&& customerBean.getCustomerInfo() != null) {
					Customer customer = customerBean.getCustomerInfo();
					crmUser.setCrmName(customer.getName());
					crmUser.setCrmMobile(customer.getMobile());
					crmUser.setCrmCustomerType(customer.getCustomerType());
					if ("1".equals(customer.getCustomerType())) {
						crmUser.setCrmCheckStatus(""
								+ customer.getPltpComCheckStatus());
					} else if ("2".equals(customer.getCustomerType())) {
						crmUser.setCrmCheckStatus(""
								+ customer.getPltpPerCheckStatus());
					}
					if (StringUtils.isBlank(customer.getPltpHeadPicId())) {
						logger.info("---------获取用户头像URL:");
						IFileService fileService = ServiceFactory.soaService(IFileService.class);
						crmUser.setCrmHeadPicID(customer.getPltpHeadPicId());
						crmUser.setCrmHeadPicURL(fileService.getFileURL(customer.getPltpHeadPicId(), IFileService.IMAGE_MIN));
						logger.info("---------获取用户头像URL:" + crmUser.getCrmHeadPicURL() + "-----------------");
					}
					user = crmUser;
				}
				long endTime = System.currentTimeMillis();
				logger.info("------------------------------查询用户Crm数据userLogin："
						+ userLogin + "；花费时间为：----" + (endTime - startTime));
			} catch (Exception e) {
				logger.error("查询crm用户数据异常", e);
			}
		} catch (Exception e) {
			logger.error("app service query model object fail", e);
		}
		return user;
	}

	@Override
	public UAAUser queryUserByUserLogin(String userLogin, Operator op) {
		return this.queryUserByUserLoginNeedCRMInfo(userLogin, false, op);
	}

	@Override
	public UAASystem querySystemBySystemSign(String systemSign, Operator op) {
		try {
			IUAASystemManager manager = (IUAASystemManager) ServiceFactory
					.soaService(IUAASystemManager.class);
			UAASystemExampleExtended usee = new UAASystemExampleExtended();
			usee.createCriteria().andSystemUriEqualTo(systemSign);
			List<UAASystem> usList = manager.getUAASystemByExampleExtended(
					usee, op);
			if (usList != null && usList.size() > 0) {
				return usList.get(0);
			}
		} catch (Exception e) {
			logger.error("app service query UAASystem fail", e);
		}
		return null;
	}

	@Override
	public UAAOrg queryOrgById(String orgId, Operator op) {
		UAAOrg org = null;
		try {
			IUAABusinessManager manager = (IUAABusinessManager) ServiceFactory
					.soaService(IUAABusinessManager.class);
			UAAOrgMeta orgMeta = manager.getUAAOrgById(orgId, op);
			if (orgMeta != null) {
				org = orgMeta.getUaaOrg();
			}
		} catch (Exception e) {
			logger.error("app service query model object fail", e);
		}
		return org;
	}

	@Override
	public UAAOrgRole queryOrgRoleById(String roleId, Operator op) {
		UAAOrgRole role = null;
		try {
			IUAABusinessManager manager = (IUAABusinessManager) ServiceFactory
					.soaService(IUAABusinessManager.class);
			role = manager.getUAAOrgRoleById(roleId, op);
		} catch (Exception e) {
			logger.error("app service query model object fail", e);
		}
		return role;
	}

	@Override
	public List<UAAPermission> querySysytemPermissionList(String systemId,
			Operator op) {
		List<UAAPermission> permList = null;
		try {
			IUAASystemManager manage = (IUAASystemManager) ServiceFactory
					.soaService(IUAASystemManager.class);
			UAAPermissionExampleExtended exampleExtended = new UAAPermissionExampleExtended();
			exampleExtended.createCriteria().andSystemIdEqualTo(systemId)
					.andStatusEqualTo("1");
			permList = manage.getUAAPermissionByExampleExtended(
					exampleExtended, op);
		} catch (Exception e) {
			permList = new ArrayList<UAAPermission>();
			logger.error("查询 系统标识为:" + systemId + "的系统权限异常!", e);
		}

		return permList;
	}

	@Override
	public boolean authenLogin(String userAlias, String md5password)
			throws Exception {
		boolean ret = false;
		try {
			IAuthentication authManage = (IAuthentication) ServiceFactory
					.soaService(IAuthentication.class);
			ret = authManage.authen(userAlias, md5password);
		} catch (Exception e) {
			logger.warn("调用统一认证验证用户服务异常!", e);
			throw new Exception("验证用户异常!", e);
		}
		return ret;
	}

	@Override
	public boolean modifyUserPassword(String userId, String oldMD5,
			String newMD5, Operator op) throws Exception {
		boolean ret = false;
		try {
			IUAABusinessManager userManage = (IUAABusinessManager) ServiceFactory
					.soaService(IUAABusinessManager.class);
			userManage.modifyPassword(userId, oldMD5, newMD5, op);
			ret = true;
		} catch (Exception e) {
			logger.warn("调用统一认证修改密码服务异常!", e);
			throw new Exception("修改用户密码异常!", e);
		}
		return ret;
	}

	public boolean isModifyPassWord(String userId, Operator op)
			throws Exception {
		boolean ret = false;
		try {
			IUAABusinessManager userManage = (IUAABusinessManager) ServiceFactory
					.soaService(IUAABusinessManager.class);
			ret = userManage.isUAAPassWord(userId, op);
		} catch (Exception e) {
			logger.warn("调用统一认证判断是否需要修改密码服务异常!", e);
			throw new Exception("判断是否需要修改密码异常!", e);
		}
		return ret;
	}

	@Override
	public UAAUser queryUaaUserById(String id,Operator op) {
		try {
			logger.info("调用统一认证根据ID查询UaaUser! userId : " + id);
			if(StringUtils.isBlank(id)){
				throw new YpptGatewayException("调用统一认证根据ID查询UaaUser异常:userId 为空");
			}
			IUAABusinessManager userUAAManage = (IUAABusinessManager) ServiceFactory
			.soaService(IUAABusinessManager.class);
			if(op == null){
				op = new Operator();
			}
			UAAUserMeta userMeta = userUAAManage.getUAAUserById(id,  op);
			if(userMeta != null){
				logger.info("调用统一认证根据ID查询UaaUser! 结果为 : " + (userMeta.getUaaUser() == null ? "空": userMeta.getUaaUser()));
				return userMeta.getUaaUser();
			}else{
				logger.info("调用统一认证根据ID查询UaaUser! 结果为空");
			}
		} catch (Exception e) {
			logger.error("调用统一认证根据ID查询UaaUser异常!", e);
			throw new YpptGatewayException("调用统一认证根据ID查询UaaUser异常!",e);
		}
		return null;
	}

	@Override
	public CRMUserBean queryCRMUserByUserLogin(String userLogin, Operator op) {
		CRMUserBean crmUser = new CRMUserBean();
		UAAUser uaaUser = null;
		try {
			logger.info("-------------------开始查询用户登录信息----userLogin：" + userLogin);
			IAuthentication manager = (IAuthentication) ServiceFactory.soaService(IAuthentication.class);
			uaaUser = manager.getUserByAlias(userLogin);
			logger.info("-------------------查出用户登录信息userLogin：" + userLogin + "----");
			if (uaaUser == null){
				logger.info("---------------没有查出用户登录信息userLogin：" + userLogin + "----");
				return null;
			}
			try {
				long startTime = System.currentTimeMillis();
				ICustomerService crmManager = (ICustomerService) ServiceFactory.soaService(ICustomerService.class);
				CustomerMetaBean customerBean = crmManager.getCustomerById(uaaUser.getId(), "ICCARD");
				
				BeanUtils.copyProperties(uaaUser, crmUser);
				if (customerBean != null && customerBean.getCustomerInfo() != null) {
					Customer customer = customerBean.getCustomerInfo();
					crmUser.setCrmName(customer.getName());
					crmUser.setCrmMobile(customer.getMobile());
					crmUser.setCrmCustomerType(customer.getCustomerType());
					crmUser.setCrmIdcardNo(customer.getPltpIdcardNo());
					if ("1".equals(customer.getCustomerType())) {
						crmUser.setCrmCheckStatus(""
								+ customer.getPltpComCheckStatus());
					} else if ("2".equals(customer.getCustomerType())) {
						crmUser.setCrmCheckStatus(""
								+ customer.getPltpPerCheckStatus());
					}
					if (StringUtils.isBlank(customer.getPltpHeadPicId())) {
						logger.info("---------获取用户头像URL:");
						IFileService fileService = ServiceFactory.soaService(IFileService.class);
						crmUser.setCrmHeadPicID(customer.getPltpHeadPicId());
						crmUser.setCrmHeadPicURL(fileService.getFileURL(customer.getPltpHeadPicId(), IFileService.IMAGE_MIN));
						logger.info("---------获取用户头像URL:" + crmUser.getCrmHeadPicURL() + "-----------------");
					}
					
				}
				long endTime = System.currentTimeMillis();
				logger.info("------------------------------查询用户Crm数据userLogin："
						+ userLogin + "；花费时间为：----" + (endTime - startTime));
			} catch (Exception e) {
				logger.error("查询crm用户数据异常", e);
			}
		} catch (Exception e) {
			logger.error("app service query model object fail", e);
		}
		return crmUser;
		
	}

	@Override
	public CRMUserBean queryCRMUserByUserId(String userId, Operator op) {
		CRMUserBean crmUser = null;
		UAAUser uaaUser = null;
		try {
			logger.info("调用统一认证根据ID查询UaaUser! userId : " + userId);
			if(StringUtils.isBlank(userId)){
				throw new YpptGatewayException("调用统一认证根据ID查询UaaUser异常:userId 为空");
			}
			IUAABusinessManager userUAAManage = (IUAABusinessManager) ServiceFactory
			.soaService(IUAABusinessManager.class);
			if(op == null){
				op = new Operator();
			}
			UAAUserMeta userMeta = userUAAManage.getUAAUserById(userId,  op);
			if(userMeta != null){
				logger.info("调用统一认证根据ID查询UaaUser! 结果为 : " + (userMeta.getUaaUser() == null ? "空": userMeta.getUaaUser()));
				uaaUser = userMeta.getUaaUser();
			}else{
				logger.info("调用统一认证根据ID查询UaaUser! 结果为空");
				return crmUser;
			}
			logger.info("-------------------查出用户登录信息userLogin：" + userId + "----");
			if (uaaUser == null){
				logger.info("---------------没有查出用户登录信息userLogin：" + userId + "----");
				return null;
			}
			try {
				long startTime = System.currentTimeMillis();
				ICustomerService crmManager = (ICustomerService) ServiceFactory.soaService(ICustomerService.class);
				CustomerMetaBean customerBean = crmManager.getCustomerById(uaaUser.getId(), "ICCARD");
				if (customerBean != null && customerBean.getCustomerInfo() != null) {
					crmUser=new CRMUserBean();
					BeanUtils.copyProperties(uaaUser, crmUser);
					Customer customer = customerBean.getCustomerInfo();
					crmUser.setCrmName(customer.getName());
					crmUser.setCrmMobile(customer.getMobile());
					crmUser.setCrmCustomerType(customer.getCustomerType());
					crmUser.setCrmIdcardNo(customer.getPltpIdcardNo());
					if ("1".equals(customer.getCustomerType())) {
						crmUser.setCrmCheckStatus(""
								+ customer.getPltpComCheckStatus());
					} else if ("2".equals(customer.getCustomerType())) {
						crmUser.setCrmCheckStatus(""
								+ customer.getPltpPerCheckStatus());
					}
					if (StringUtils.isBlank(customer.getPltpHeadPicId())) {
						logger.info("---------获取用户头像URL:");
						IFileService fileService = ServiceFactory.soaService(IFileService.class);
						crmUser.setCrmHeadPicID(customer.getPltpHeadPicId());
						crmUser.setCrmHeadPicURL(fileService.getFileURL(customer.getPltpHeadPicId(), IFileService.IMAGE_MIN));
						logger.info("---------获取用户头像URL:" + crmUser.getCrmHeadPicURL() + "-----------------");
					}
					
				}
				long endTime = System.currentTimeMillis();
				logger.info("------------------------------查询用户Crm数据userId："
						+ userId + "；花费时间为：----" + (endTime - startTime));
			} catch (Exception e) {
				logger.error("查询crm用户数据异常", e);
			}
		} catch (Exception e) {
			logger.error("app service query model object fail", e);
		}
		return crmUser;
	}

}