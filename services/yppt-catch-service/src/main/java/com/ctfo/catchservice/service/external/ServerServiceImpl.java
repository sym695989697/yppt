package com.ctfo.catchservice.service.external;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ctfo.catchservice.bean.JResult;
import com.ctfo.catchservice.bean.LoginCode;
import com.ctfo.catchservice.bean.UserStatus;
import com.ctfo.catchservice.exception.CatchException;
import com.ctfo.catchservice.interfaces.external.IServerService;
import com.ctfo.catchservice.interfaces.internal.ILoginInternalService;

/**
 * 服务器相关信息服务接口实现
 * 
 * @author jichao
 */
@Service(value = "serverService")
public class ServerServiceImpl implements IServerService {

	private static final Log logger = LogFactory.getLog(ServerServiceImpl.class);

	@Autowired
	@Qualifier("zsyLoginInternalServiceImpl")
	private ILoginInternalService zsyLoginInternalServiceImpl;

	@Autowired
	@Qualifier("zshLoginInternalService")
	private ILoginInternalService zshLoginInternalService;

	@Override
	public JResult getServiceStatus(String hostType) throws CatchException {
		return null;
	}

	@Override
	public UserStatus getUserStatus(String hostType, String userName) throws CatchException {
		logger.info("请求用户状态,网站类型:" + hostType + ";用户名username:" + userName);
		UserStatus userStatus = null;
		if (LoginCode.ZSH.equals(hostType)) {
			userStatus = zshLoginInternalService.getUserStatus(hostType, userName);
		} else {
			userStatus = zsyLoginInternalServiceImpl.getUserStatus(hostType, userName);
		}
		return userStatus;
	}

	@Override
	public Map<String, UserStatus> getAllUserStatus(String hostType) {
		logger.info("网站:" + hostType + "请求该网站下所有用户登陆状态");
		Map<String, UserStatus> result = Collections.emptyMap();
		result = zsyLoginInternalServiceImpl.getAllUserStatus(hostType);
		return result;
	}

	@Override
	public String login(String userType, String cookies, String userName, String code) throws CatchException {
		String result = "";
		logger.info("请求登陆,网站:" + userType + ";用户名:" + userName);
		if (LoginCode.ZSH.equals(userType)) {
			result = zshLoginInternalService.login(userType, cookies, userName, code);
		} else {
			result = zsyLoginInternalServiceImpl.login(userType, cookies, userName, code);
		}
		return result;
	}

	@Override
	public String[] getLoginCookiesAndCheckCode(String hostType,Map<String,String> browerInfo) throws CatchException {
		logger.info("请求网站验证码:" + hostType);
		String[] result = null;
		if (LoginCode.ZSH.equals(hostType)) {
			result = zshLoginInternalService.getLoginCookiesAndCheckCode(hostType,browerInfo);
		} else {
			result = zsyLoginInternalServiceImpl.getLoginCookiesAndCheckCode(hostType,browerInfo);
		}
		return result;
	}

}
