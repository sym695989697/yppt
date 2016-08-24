package com.ctfo.catchservice.service.internal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.helper.StringUtil;
import org.springframework.stereotype.Service;

import com.ctfo.catchservice.bean.ClientSession;
import com.ctfo.catchservice.bean.LoginCode;
import com.ctfo.catchservice.bean.StaticMap;
import com.ctfo.catchservice.bean.UserStatus;
import com.ctfo.catchservice.exception.CatchException;
import com.ctfo.catchservice.interfaces.internal.ILoginInternalService;
import com.ctfo.catchservice.util.CatchDataUtils;
import com.ctfo.catchservice.util.DateUtils;
import com.ctfo.catchservice.util.HttpClientUtils;
import com.ctfo.catchservice.util.LoginCheckUtils;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.yppt.baseservice.system.beans.ICCardAccountConfig;
import com.ctfo.yppt.baseservice.system.beans.ICCardAccountConfigExampleExtended;
import com.ctfo.yppt.baseservice.system.intf.IAccountManager;

/**
 * 登录服务--中石油
 * 
 * @author jichao
 */
@Service(value = "zsyLoginInternalServiceImpl")
public class ZHYLoginInternalServiceImpl implements ILoginInternalService {
	private static final Log log = LogFactory.getLog(ZHYLoginInternalServiceImpl.class);
	protected boolean isLogin = false;// 是否登录
	private static ResourceBundle resource = ResourceBundle.getBundle("system");// 获取资源文件
	private IAccountManager accountManager;

	public ZHYLoginInternalServiceImpl() {
		accountManager = (IAccountManager) ServiceFactory.getFactory().getService(IAccountManager.class);
	}

	/**
	 * 设置POST头信息（通用头部信息）
	 */
	private void setRequestHeaderByPost(PostMethod postMethod, String userType) {
		postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.143 Safari/537.36");
		postMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		postMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8");
		postMethod.setRequestHeader("Cache-Control", "max-age=0");
		postMethod.setRequestHeader("Connection", "keep-alive");
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		postMethod.setRequestHeader("Host", CatchDataUtils.getHost(userType));
		postMethod.setRequestHeader("Origin", CatchDataUtils.getHost(userType));// 来源于哪个网站
	}

	@SuppressWarnings("deprecation")
	@Override
	public String[] getLoginCookiesAndCheckCode(String userType, Map<String, String> browerInfo) throws CatchException {
		HttpClient httpClient = HttpClientUtils.getHttpClient();
		StringBuffer cookie = new StringBuffer();
		OutputStream os = null;
		InputStream is = null;
		String pathStr = "checkCode_zsy.jpg";// 验证码图片名称地址
		String filepath = "";
		try {
			// GET请求验证码
			GetMethod getMethod = new GetMethod(CatchDataUtils.getCheckCodeUrl(userType) + "&" + URLEncoder.encode(DateUtils.dateFormatCN(new Date())));

//			Iterator<String> brower = browerInfo.keySet().iterator();
//			while (brower.hasNext()) {
//				String key = brower.next();
//				System.out.println("key:" + key + ";value:" + browerInfo.get(key));
//				if ("host".equals(key) || "origin".equals(key)) {
//					getMethod.setRequestHeader("Host", CatchDataUtils.getHost(userType));
//				} else if ("referer".equals(key)) {
//					getMethod.setRequestHeader("Referer", CatchDataUtils.getHost(userType) + CatchDataUtils.getPosturi(userType));
//				} else {
//
//					String newStr = key.substring(0, 1).toUpperCase() + key.replaceFirst("\\w","");
//					getMethod.setRequestHeader(newStr, browerInfo.get(key));
//				}
//				getMethod.setRequestHeader("Accept", "image/png,image/*;q=0.8,*/*;q=0.5");
//			}
			CatchDataUtils.setRequestHeaderByGet(getMethod, userType);//
			// 通用GET头部信息
			CatchDataUtils.setRequestHeaderByGet(getMethod, userType);// 通用GET头部信息
			httpClient.executeMethod(getMethod);
			Cookie[] cookies3 = httpClient.getState().getCookies();
			if (cookies3.length == 0) {
				log.info("Cookies is None.");
			} else {
				for (int i = 0; i < cookies3.length; i++) {
					cookie.append(cookies3[i].toString()).append(";");
				}
				httpClient.getState().addCookies(cookies3);
			}
			String path = ZSHLoginInternalServiceImpl.class.getResource("/").getPath();
			filepath = path.replace("/WEB-INF/classes", "") + "/images/" + pathStr;
			File f = new File(filepath);
			// File f = new File("D:/"+pathStr);
			os = new FileOutputStream(f);
			is = getMethod.getResponseBodyAsStream();
			int flag = -1;
			while ((flag = is.read()) != -1) {
				os.write(flag);
			}

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String result = cookie.toString();
		log.info("登录Cookie:" + result);
		//
		String[] resultArr = new String[] { result, resource.getString("local_url") + "/images/" + pathStr };
		return resultArr;
	}

	@Override
	public String login(String userType, String cookies, String username, String code) throws CatchException {
		HttpClient httpClient = HttpClientUtils.getHttpClient();
		// 检查用户类型、用户名、密码、验证码是否正常
		if (StringUtil.isBlank(userType.trim())) {
			log.error("用户类型为空.");
			return LoginCode.USERTYPE_NULL;
		}

		if (!userType.trim().toUpperCase().equals("ZSY") && !userType.trim().toUpperCase().equals("ZSH")) {
			log.error("用户类型不合法.");
			return LoginCode.USERTYPE_ERROR;
		}

		if (StringUtil.isBlank(username.trim())) {
			log.error("登录用户名为空.");
			return LoginCode.USERNAME_NULL;
		}
		if (StringUtil.isBlank(code)) {
			log.error("登录验证码为空.");
			return LoginCode.CHECKCODE_NULL;
		}

		if (LoginCheckUtils.isContainsChinese(username) || LoginCheckUtils.isContainsSpecialCharacters(username)) {
			log.error("登录用户名不合法.");
			return LoginCode.USERNAME_ERROR;
		}
		if (!LoginCheckUtils.checkCodeLength(code)) {
			log.error("登录验证码不合法.");
			return LoginCode.CHECKCODE_ERROR;
		}

		String userStoreName = CatchDataUtils.getUserStoreName(userType, username);
		String resultCode = "";
		try {
			String password = "";
			// 根据用户名，userType查询用户信息
			ICCardAccountConfigExampleExtended accountconfigexted = new ICCardAccountConfigExampleExtended();
			accountconfigexted.createCriteria().andUsenameEqualTo(username).andAccounttypeEqualTo(CatchDataUtils.getHostType(userType));
			List<ICCardAccountConfig> accountConfigList = accountManager.getList(accountconfigexted);
			String cardAreaCode = "";// 发卡地区
			String ownOrg = "";
			String cardType = "";
			if (CollectionUtils.isNotEmpty(accountConfigList)) {
				password = accountConfigList.get(0).getPassword();
				cardAreaCode = accountConfigList.get(0).getCardAreaCode();
				ownOrg = accountConfigList.get(0).getOwnOrg();
				cardType = accountConfigList.get(0).getAccounttype();
			} else {
				resultCode = LoginCode.LOGIN_FAILURE;
				return resultCode;
			}
			// 登录提交URL地址
			String login = CatchDataUtils.getHost(userType) + CatchDataUtils.getPosturi(userType);
			PostMethod postMethod = new PostMethod(login);
			postMethod.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
			// 登录参数
			NameValuePair param0 = new NameValuePair("UserLogin1$btnLogin", "登录");
			NameValuePair param1 = new NameValuePair("UserLogin1$txtuserid", username);
			NameValuePair param2 = new NameValuePair("UserLogin1$txtpwd", password);
			NameValuePair param3 = new NameValuePair("UserLogin1$txtcode", code);
			NameValuePair param4 = new NameValuePair("__EVENTVALIDATION",
					"5Kr2xs+I5xawItvDNWj87xYnLXFY8QKYt73+r3hvIx4No8zdJUc9LREryh1hIyiOHfazc0klcJemxJ5C6j+hcBAcTQ4mOdJTK4UHTY0enNKOc6drPWB34jdTbTPYRTj6B+N993UwyC8=");
			NameValuePair param5 = new NameValuePair(
					"__VIEWSTATE",
					"2q+sKUAXMkxveXXC9XP5faZGSJDubJReXvM47rY0tgKcwyByfavJmNS6znftInDeA+RRD4C9tWHn21G8oXHoD/aXvq+TgZOVz2R/gYWO29ZcJbKcQ7/zQqPsIMv2JWTtSgWBROuH7uq3msrATXE97stiEXd2F1T7BIlL+oACq0bs8YDfKTLCx3784JL6RBDrZFo8U0WGnudArduH7Jm6rgdXuWBj5T82QG6SSig0zLHjJr6oh0gBY0gOr8RT4Nc8gs9cBtCpeD3G7oLXQeQW/I0gkVMFRQ7ruvofNaCWR8BsgkdTf/pvRm9URmVgWj55V5VVfU+NiBEbjZhS76HroEJd1Go0UnwV26U+3vqNeKOoPk767CmSvH7oI8wHBRvgd2j91VYs18zY4b/SbG/MGMImJJCX7BaXcUJMCTHy1M7gz+RwXbBdvggovv5ez52VvTc32zc69JFWA8ekbXJwObMN8e2Abfp/FFfPAOtvAGx5RedgXaJKROfRIFfInst60DzclOASMiy0yO7ANEOOMQQUS/6c8EQXkJ+QPSWo+nTJy+kOxqZErFMKLnfa2DdL4J9L+sH7LRfwzAxkZMZVulOwwe93tfbI82qlkmN2IQd3mzc35328/8f5zCY9J95Cb7gFm0aJ4mFJgkfuc9fFyWCSmM4Z1R6eZgTJO0kMH0YOT9EJyOWDnc1tIF9GHoxLTBcJ4I0Ya8r1vO9NAxrPtvfj7u8pNV+XSCb6r22Hte7DBA6jzGAKk+N8MaAIGZXjdvqz6cIU4T2cV7mYtSjKVBS1z/+wdiyO");
			NameValuePair param6 = new NameValuePair("searchRadio", "on");
			NameValuePair param7 = new NameValuePair("statementH", "show");
			NameValuePair param8 = new NameValuePair("tipH", "show");
			NameValuePair param9 = new NameValuePair("WebSearch1$txtSearchText", "站内关键词搜索");

			NameValuePair paramValue[] = { param0, param1, param2, param3, param4, param5, param6, param7, param8, param9 };
			postMethod.addParameters(paramValue);// 向post中添加参数
			setRequestHeaderByPost(postMethod, userType);// 通用POST头部信息
			postMethod.setRequestHeader("Referer", CatchDataUtils.getHost(userType) + CatchDataUtils.getPosturi(userType));// 提交方
			postMethod.setRequestHeader("Cookie", cookies);// 设置COOKIES

			int statucode = httpClient.executeMethod(postMethod);// 提交登录
			String result = postMethod.getResponseBodyAsString();
			// 查找返回结果中是否含有“您好！欢迎您登录”字样，说明已经登录
			if (result.contains("您好！欢迎您登录")) {
				resultCode = LoginCode.LOGIN_SUCESS;
				// 将登录信息保存到静态类MAP中
				ClientSession clientSession = new ClientSession(httpClient, cookies, true);
				clientSession.setCardAreaCode(cardAreaCode);
				clientSession.setOwnOrg(ownOrg);
				clientSession.setCardType(cardType);
				StaticMap.clientMap.put(userStoreName, clientSession);

			} else {
				resultCode = LoginCode.LOGIN_FAILURE;
			}
			postMethod.releaseConnection();
		} catch (HttpException e) {
			resultCode = LoginCode.LOGIN_FAILURE;
			log.error("登录失败！", e);
			e.printStackTrace();
		} catch (IOException e) {
			resultCode = LoginCode.LOGIN_FAILURE;
			log.error("登陆过程中发生异常:" + e.getMessage());
		} catch (Exception e) {
			resultCode = LoginCode.LOGIN_FAILURE;
			log.error("登陆过程中发生异常:" + e.getMessage());
		}
		return resultCode;
	}

	@Override
	public UserStatus getUserStatus(String userType, String username) throws CatchException {
		if (StringUtil.isBlank(userType.trim())) {
			log.error("用户类型为空.");
			throw new CatchException("用户类型为空.");
		}

		if (!userType.trim().toUpperCase().equals("ZSY") && !userType.trim().toUpperCase().equals("ZSH")) {
			log.error("用户类型不合法.");
			throw new CatchException("用户类型不合法.");
		}

		if (StringUtil.isBlank(username)) {
			log.error("用户名称为空.");
			throw new CatchException("用户名称为空.");
		}

		if (LoginCheckUtils.isContainsChinese(username) || LoginCheckUtils.isContainsSpecialCharacters(username)) {
			log.error("用户名不合法.");
			throw new CatchException("用户名不合法.");
		}
		String userStoreName = CatchDataUtils.getUserStoreName(userType, username);
		ClientSession clientSession = StaticMap.clientMap.get(userStoreName);
		UserStatus userstatus = new UserStatus();
		userstatus.setStatus(String.valueOf(clientSession.isLogin()));
		userstatus.setUserName(username);
		return userstatus;
	}

	@Override
	public Map<String, UserStatus> getAllUserStatus(String userType) throws CatchException {
		// 可以单独某网站类型的用户状态，也可查询全部的网站用户登陆状态，如果usertype为空，则查询全部用户登陆状态
		Map<String, UserStatus> result = new HashMap<String, UserStatus>();
		if (StringUtils.isBlank(userType)) {
			for (Iterator<String> itr = StaticMap.clientMap.keySet().iterator(); itr.hasNext();) {
				String key = itr.next();
				ClientSession value = StaticMap.clientMap.get(key);
				UserStatus userstatus = new UserStatus();
				userstatus.setStatus(String.valueOf(value.isLogin()));
				userstatus.setUserName(key);
				result.put(key, userstatus);
			}
			return result;
		} else {
			// 中石油用户
			Map<String, UserStatus> zsyMap = new HashMap<String, UserStatus>();
			// 中石化用户
			Map<String, UserStatus> zshMap = new HashMap<String, UserStatus>();

			for (Iterator<String> itr = StaticMap.clientMap.keySet().iterator(); itr.hasNext();) {
				String key = itr.next();
				ClientSession value = StaticMap.clientMap.get(key);
				UserStatus userstatus = new UserStatus();
				userstatus.setStatus(String.valueOf(value.isLogin()));
				userstatus.setUserName(key);
				if (key.contains(LoginCode.USERPRE_ZSY)) {
					zsyMap.put(key, userstatus);
				}
				if (key.contains(LoginCode.USERPRE_ZSH)) {
					zshMap.put(key, userstatus);
				}
			}
			if (userType.toUpperCase().equals("ZSY")) {
				return zsyMap;
			}
			if (userType.toUpperCase().equals("ZSH")) {
				return zshMap;
			}
		}
		return null;
	}
}
