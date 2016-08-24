package com.ctfo.catchservice.service.internal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.helper.StringUtil;
import org.springframework.stereotype.Service;

import com.ctfo.catchservice.bean.ClientSession;
import com.ctfo.catchservice.bean.LoginCode;
import com.ctfo.catchservice.bean.StaticMap;
import com.ctfo.catchservice.bean.UserStatus;
import com.ctfo.catchservice.exception.CatchException;
import com.ctfo.catchservice.interfaces.internal.ILoginInternalService;
import com.ctfo.catchservice.util.CatchDataUtils;
import com.ctfo.catchservice.util.HttpClientUtils;
import com.ctfo.catchservice.util.LoginCheckUtils;
import com.ctfo.catchservice.util.SinopecSHA1;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.yppt.baseservice.system.beans.ICCardAccountConfig;
import com.ctfo.yppt.baseservice.system.beans.ICCardAccountConfigExampleExtended;
import com.ctfo.yppt.baseservice.system.intf.IAccountManager;

/**
 * 
 * @ClassName: ZSHLoginInternalServiceImpl
 * @Description: 采集中石化数据，用户登陆实现
 * @author yuguangyang
 * @date 2015年1月27日 上午10:26:03
 *
 */
@Service(value = "zshLoginInternalService")
public class ZSHLoginInternalServiceImpl implements ILoginInternalService {

	private static final Log logger = LogFactory.getLog(ZSHLoginInternalServiceImpl.class);
	private static ResourceBundle resource = ResourceBundle.getBundle("syscatch");// 获取资源文件
	protected boolean isLogin = false;// 是否登录
	private static ResourceBundle resource_system = ResourceBundle.getBundle("system");// 获取资源文件
	private IAccountManager accountManager;

	public ZSHLoginInternalServiceImpl() {
		accountManager = (IAccountManager) ServiceFactory.getFactory().getService(IAccountManager.class);
	}

	// 中石化获取验证码
	@Override
	public String[] getLoginCookiesAndCheckCode(String hostType,Map<String,String> browerInfo) throws CatchException {
		HttpClient httpClient = HttpClientUtils.getHttpClient();
		StringBuffer cookie = new StringBuffer();
		OutputStream os = null;
		InputStream is = null;
		String pathStr = "checkCode_zsh.jpg";// 验证码图片名称地址
		GetMethod getMethod = null;
		String filepath = "";
		try {
			// GET请求验证码
		getMethod = new GetMethod(CatchDataUtils.getCheckCodeUrl(hostType) + "?" + Math.random());
//			Iterator<String> brower = browerInfo.keySet().iterator();
//			while (brower.hasNext()) {
//				String key = brower.next();
//				if ("host".equals(key) || "origin".equals(key)) {
//					getMethod.setRequestHeader("Host", CatchDataUtils.getHost(hostType));
//				} else if ("referer".equals(key)) {
//					getMethod.setRequestHeader("Referer", CatchDataUtils.getHost(hostType) + CatchDataUtils.getPosturi(hostType));
//				} else {
//					String newStr = key.substring(0, 1).toUpperCase() + key.replaceFirst("\\w","");
//					getMethod.setRequestHeader(newStr, browerInfo.get(key));
//				}
//			}
			CatchDataUtils.setRequestHeaderByGet(getMethod, hostType);// 通用GET头部信息
			getMethod.setRequestHeader("Referer", resource.getString("zsh.login_ref"));
			int statusCode = httpClient.executeMethod(getMethod);// 返回状态
			if (statusCode == HttpStatus.SC_OK) {
				Cookie[] cookies3 = httpClient.getState().getCookies();
				if (cookies3.length == 0) {
					logger.info("zsh cookies is null");
				} else {
					for (int i = 0; i < cookies3.length; i++) {
						cookie.append(cookies3[i].toString()).append(";");
					}
					httpClient.getState().addCookies(cookies3);
				}
				String path = ZSHLoginInternalServiceImpl.class.getResource("/").getPath();
				filepath = path.replace("/WEB-INF/classes", "") + "/images/" + pathStr;
				File f = new File(filepath);
				// File f = new File("D:/" + pathStr);
				os = new FileOutputStream(f);
				is = getMethod.getResponseBodyAsStream();
				int ii = -1;
				while ((ii = is.read()) != -1) {
					os.write(ii);
				}
			}
		} catch (HttpException e) {
			logger.error("中石化请求验证码发生HttpException异常,",e);
		} catch (IOException e) {
			logger.error("中石化请求验证码发生IOException异常,",e);
		} finally {
			getMethod.releaseConnection();
			try {
				os.close();
				is.close();
			} catch (IOException e) {
				logger.error("中石化请求验证码关闭输入输出流异常" + e);
			}
		}
		String[] resultArr = new String[] { cookie.toString(), resource_system.getString("local_url") + "/images/" + pathStr };
		return resultArr;
	}

	@Override
	public String login(String hostType, String cookies, String username, String code) throws CatchException {
		HttpClient httpClient = HttpClientUtils.getHttpClient();
		String resultCode = "";
		// 检查用户类型、用户名、密码、验证码是否正常
		if (StringUtil.isBlank(hostType.trim())) {
			logger.error("用户类型为空.");
			return LoginCode.USERTYPE_NULL;
		}

		if (!hostType.trim().toUpperCase().equals("ZSY") && !hostType.trim().toUpperCase().equals("ZSH")) {
			logger.error("用户类型不合法.");
			return LoginCode.USERTYPE_ERROR;
		}

		if (StringUtil.isBlank(username.trim())) {
			logger.error("登录用户名为空.");
			return LoginCode.USERNAME_NULL;
		}

		if (StringUtil.isBlank(code)) {
			logger.error("登录验证码为空.");
			return LoginCode.CHECKCODE_NULL;
		}

		if (LoginCheckUtils.isContainsChinese(username) || LoginCheckUtils.isContainsSpecialCharacters(username)) {
			logger.error("登录用户名不合法.");
			return LoginCode.USERNAME_ERROR;
		}
		if (!LoginCheckUtils.checkCodeLength(code)) {
			logger.error("登录验证码不合法.");
			return LoginCode.CHECKCODE_ERROR;
		}
		PostMethod postMethod = null;
		try {
			String password = "";
			// 根据用户名，userType查询用户信息
			ICCardAccountConfigExampleExtended accountconfigexted = new ICCardAccountConfigExampleExtended();
			accountconfigexted.createCriteria().andUsenameEqualTo(username).andAccounttypeEqualTo(CatchDataUtils.getHostType(hostType));
			List<ICCardAccountConfig> accountConfigList = accountManager.getList(accountconfigexted);
			String cardAreaCode = "";// 发卡地区
			String ownOrg = "";// 所属组织
			String cardType;// 卡类型
			if (CollectionUtils.isNotEmpty(accountConfigList)) {
				password = accountConfigList.get(0).getPassword();
				cardAreaCode = accountConfigList.get(0).getCardAreaCode();
				ownOrg = accountConfigList.get(0).getOwnOrg();
				cardType = accountConfigList.get(0).getAccounttype();
			} else {
				resultCode = LoginCode.LOGIN_FAILURE;
				return resultCode;
			}

			String userStoreName = CatchDataUtils.getUserStoreName(hostType, username);
			String login = CatchDataUtils.getHost(hostType) + CatchDataUtils.getPosturi(hostType);
			postMethod = new PostMethod(login);
			postMethod.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
			NameValuePair param1 = new NameValuePair("memberAccount", username);
			NameValuePair param2 = new NameValuePair("memberUmm", SinopecSHA1.SHA1(password));
			NameValuePair param3 = new NameValuePair("check", code);
			NameValuePair param4 = new NameValuePair("rememberMe", "on");
			NameValuePair paramValue[] = { param1, param2, param3, param4 };
			postMethod.addParameters(paramValue);
			postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.143 Safari/537.36");
			postMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			postMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8");
			postMethod.setRequestHeader("Cache-Control", "max-age=0");
			postMethod.setRequestHeader("Connection", "keep-alive");
			postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			postMethod.setRequestHeader("Referer", resource.getString("zsh.login_ref"));
			postMethod.setRequestHeader("Host", CatchDataUtils.getHost(hostType));
			postMethod.setRequestHeader("Origin", CatchDataUtils.getHost(hostType));
			postMethod.setRequestHeader("Cookie", cookies);
			ClientSession session = null;
			httpClient.executeMethod(postMethod);
			String result = postMethod.getResponseBodyAsString();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(result);
			switch (node.get("success").asInt()) {
			case 0:
				isLogin = true;
				logger.info("登录成功");
				resultCode = LoginCode.LOGIN_SUCESS;
				Header header = postMethod.getResponseHeader("Set-cookie");
				String headerValue = header.getValue();
				String ticket = headerValue.substring(headerValue.indexOf("ticket"), headerValue.lastIndexOf("\"") + 1);
				cookies = cookies + ticket;
				logger.info("中石化登录cookies:"+cookies);
				session = new ClientSession(httpClient, cookies, isLogin);
				break;
			case 1:
				isLogin = false;
				resultCode = LoginCode.LOGIN_FAILURE;
				session = new ClientSession(httpClient, cookies, false);
				logger.error("用户名密码错误");
				break;
			case 2:
				isLogin = false;
				resultCode = LoginCode.LOGIN_FAILURE;
				session = new ClientSession(httpClient, cookies, false);
				logger.error("重新获取验证码");
				break;
			case 3:
				isLogin = false;
				resultCode = LoginCode.LOGIN_FAILURE;
				session = new ClientSession(httpClient, cookies, false);
				logger.error("验证码错误");
				break;
			default:
				isLogin = false;
				resultCode = LoginCode.LOGIN_FAILURE;
				session = new ClientSession(httpClient, cookies, false);
				logger.error("未知类型错误");
				break;
			}
			session.setCardAreaCode(cardAreaCode);
			session.setOwnOrg(ownOrg);
			session.setCardType(cardType);
			StaticMap.clientMap.put(userStoreName, session);

		} catch (HttpException e) {
			logger.error("中石化登录发生HttpException异常,",e);
		} catch (IOException e) {
			logger.error("中石化登录发生IOException异常,",e);
		} catch (Exception e) {
			logger.error("中石化登录发生Exception异常,",e);
		} finally {
			postMethod.releaseConnection();
		}
		return resultCode;
	}

	@Override
	public UserStatus getUserStatus(String hostType, String username) throws CatchException {
		return null;
	}

	@Override
	public Map<String, UserStatus> getAllUserStatus(String hostType) throws CatchException {
		return null;
	}

}
