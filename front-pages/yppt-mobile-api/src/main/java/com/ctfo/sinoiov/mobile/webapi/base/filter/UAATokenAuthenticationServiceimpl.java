package com.ctfo.sinoiov.mobile.webapi.base.filter;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.csm.uaa.dao.beans.UAAUser;
import com.ctfo.gatewayservice.interfaces.bean.user.CRMUserBean;
import com.ctfo.notification.util.PropertyUtils;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Index;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants;
import com.ctfo.sinoiov.mobile.webapi.util.HttpUtil;

/**
 * 统一Token
 * @author 徐宝
 *
 */
@Service("tokenAuthenticationService")
public class UAATokenAuthenticationServiceimpl implements IUAATokenAuthenticationService {
	
	private static Log logger = LogFactory.getLog(UAATokenAuthenticationServiceimpl.class);
	
	//@Value("#{configProperties['uaa.provingTokenIdURL']}")
	private String uaaURL;

	
	

	public Index queryUserInfoByUserLogin(String userLogin) throws Exception {
		Index index = new Index();
		
		//用户
		try {
			UAAUser user = ImplementationSupport.getUserManager().queryUserByUserLoginNeedCRMInfo(userLogin,Boolean.TRUE , null );
			if(user==null){
				throw new Exception("获取用户信息为空");
			}
			String userId = user.getId();
			logger.info("登录用户id为:" + userId);
			
			index.setUserId(userId);
			index.setUserLogin(userLogin);
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
			
			//查询车后会员信息
			/*MemberOrg member = ImplementationSupport.getUserManager().queryChptMemberInfoByUserId(userId, null);
			if(member != null){
				index.setMemberId(member.getId());
				index.setMemberName(member.getOrgName());
				index.setMemberType(member.getMemberType());
				index.setStaffCode(member.getStaffCode());
			}*/
			
			
		} catch (Exception e) {
			logger.error("获取用户信息异常!", e);
			throw new Exception("获取用户信息异常");
		}
		return index;
	}

	public String validateTokenId(String tokenId) {
		uaaURL = PropertyUtils.getValueByKey("uaa.provingTokenIdURL");
		String res = HttpUtil.sendHttpPostResquest(uaaURL, "tokenId="+ tokenId);
		return parseUserName(res);
		//return "";
	}

	private String parseUserName(String res) {
		if(StringUtils.isBlank(res)){
			return null;
		}
//		String userName = "chpt_3001002080";//TODO 登陆人信息, 写死,待统一TOKEN 解析解决后变更
		String userName = null;//TODO 登陆人信息, 写死,待统一TOKEN 解析解决后变更
		try {
			UaaTokenRsult uaaTokenRsult = (UaaTokenRsult) JSONObject.toBean(JSONObject.fromObject(res),UaaTokenRsult.class);
			if(uaaTokenRsult != null){
				userName = uaaTokenRsult.getUserName();
			}
		} catch (Exception e) {
			logger.warn("解析统一认证返回tokenId发生异常, 统一认证返回结果为:"+ res, e);
		}
		return userName;
	}

//	public String getUaaURL() {
//		return uaaURL;
//	}
//
//	public void setUaaURL(String uaaURL) {
//		this.uaaURL = uaaURL;
//	}
	
	
	public static void main(String[] args) {
		UAATokenAuthenticationServiceimpl tokenAuthenticationService = new UAATokenAuthenticationServiceimpl();
//		tokenAuthenticationService.setUaaURL("http://uaa.tyrzpt.com/mobile/provingTokenId");
		String tokenId = "";
		String  userLoginName = tokenAuthenticationService.validateTokenId(tokenId );
		if(StringUtils.isBlank(userLoginName)){
			throw new ClientException(ErrorMsgConstants.Common.E000013, "tokenId校验失败,请重新登录!");
		}
		//获取用户数据
		Index index = null;
		try {
			index = tokenAuthenticationService.queryUserInfoByUserLogin(userLoginName);
		} catch (Exception e) {
			//获取用户数据异常，返回到客户端，服务器获取用户数据异常
			if(StringUtils.isBlank(userLoginName)){
				throw new ClientException(ErrorMsgConstants.Common.E000013, "获取用户登录信息异常!");
			}
		}
	}
}
