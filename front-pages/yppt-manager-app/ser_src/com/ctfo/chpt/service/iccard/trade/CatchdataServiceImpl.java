package com.ctfo.chpt.service.iccard.trade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.ServiceImpl;
import com.ctfo.catchservice.bean.JResult;
import com.ctfo.catchservice.bean.UserStatus;
import com.ctfo.catchservice.interfaces.external.ICatchService;
import com.ctfo.catchservice.interfaces.external.IServerService;
import com.ctfo.chpt.bean.iccard.catchdata.vo.CatchdataGetYZMVO;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.yppt.baseservice.system.beans.ICCardAccountConfig;
import com.ctfo.yppt.baseservice.system.beans.ICCardAccountConfigExampleExtended;
import com.ctfo.yppt.baseservice.system.intf.IAccountManager;

/**
 * 
 * @ClassName: CatchdataServiceImpl
 * @Description: 抓取交易记录账号管理 service实现
 * @author yuguangyang
 * @date 2015年1月22日 下午2:08:22
 *
 */
@Service(value = "catchdataServiceImpl")
public class CatchdataServiceImpl extends ServiceImpl implements ICatchdataService {
	private static Log logger = LogFactory.getLog(CatchdataServiceImpl.class);

	private IAccountManager accountManager;
	ICatchService catchService;
	IServerService serverService;

	public CatchdataServiceImpl() {
		accountManager = (IAccountManager) ServiceFactory.getFactory().getService(IAccountManager.class);
		catchService = (ICatchService) ServiceFactory.getFactory().getService(ICatchService.class);
		serverService = (IServerService) ServiceFactory.getFactory().getService(IServerService.class);
	}

	// 抓取消费记录数据
	@Override
	public String catchTradeByConsume(String hostType, String startDate, String endDate, String userName) {
		String type = getHostType(hostType);
		logger.info("调用远程catchService,抓取消费记录数据,网站:" + type + ";开始日期" + startDate + ";结束日期" + endDate + ";用户名" + userName);
		String success = "";
		try {
			JResult jresult = catchService.getTradeByConsume(type, "", startDate, endDate, userName);
			success = String.valueOf(jresult.isSuccess());

			// 更新抓取消费记录最后时间
			ICCardAccountConfigExampleExtended account = new ICCardAccountConfigExampleExtended();
			account.createCriteria().andUsenameEqualTo(userName).andAccounttypeEqualTo(hostType);
			List<ICCardAccountConfig> accountlist = accountManager.getList(account);
			if (CollectionUtils.isNotEmpty(accountlist)) {
				ICCardAccountConfig accountConfig = accountlist.get(0);
				accountConfig.setMainCardTrade(System.currentTimeMillis());
				accountManager.update(accountConfig);
			}
		} catch (Exception e) {
			logger.error("调用远程catchServie抓取消费记录交易数据发生异常:" + e.getMessage());
		}
		return success;
	}

	// 抓取账户交易数据
	@Override
	public String catchAccountTrade(String hostType, String startDate, String endDate, String userName) {
		String type = getHostType(hostType);
		logger.info("调用远程catchService,抓取账户交易数据,网站:" + type + ";开始日期" + startDate + ";结束日期" + endDate + ";用户名" + userName);
		String success = "";
		try {
			JResult jresult = catchService.getAccountTrade(type, "", startDate, endDate, "", userName);
			success = String.valueOf(jresult.isSuccess());

			// 更新抓取账户交易最后抓取时间
			ICCardAccountConfigExampleExtended account = new ICCardAccountConfigExampleExtended();
			account.createCriteria().andUsenameEqualTo(userName).andAccounttypeEqualTo(hostType);
			List<ICCardAccountConfig> accountlist = accountManager.getList(account);
			if (CollectionUtils.isNotEmpty(accountlist)) {
				ICCardAccountConfig accountConfig = accountlist.get(0);
				accountConfig.setSubCardTradeTime(System.currentTimeMillis());
				accountManager.update(accountConfig);
			}
		} catch (Exception e) {
			logger.error("调用远程catchServie抓取账户交易数据发生异常:" + e.getMessage());
		}
		return success;
	}

	// 刷新余额
	@Override
	public String catchCardBalance(String hostType, String userName) {
		String type = getHostType(hostType);
		logger.info("调用远程刷新卡余额,网站:" + userName);
		String success = "";
		try {
			JResult jresult = catchService.getOilCardBalance(type, "", userName);
			success = String.valueOf(jresult.isSuccess());
		} catch (Exception e) {
			logger.error("远程调用刷新卡余额发生异常:" + e.getMessage());
		}
		return success;

	}

	// 抓取主副卡数据
	@Override
	public String catchMainAndSubCard(String hostType, String userName) {
		String successs = "";
		try {
			String type = getHostType(hostType);
			logger.info("开始调用远程抓取主副卡数据,网站类型:" + type + ";用户名userName:" + userName);
			JResult jresult = catchService.getMainCardAndSubcard(type, "", "", userName);
			successs = String.valueOf(jresult.isSuccess());
			// 更新主副卡最后抓取时间
			ICCardAccountConfigExampleExtended account = new ICCardAccountConfigExampleExtended();
			account.createCriteria().andUsenameEqualTo(userName).andAccounttypeEqualTo(hostType);
			List<ICCardAccountConfig> accountlist = accountManager.getList(account);
			if (CollectionUtils.isNotEmpty(accountlist)) {
				ICCardAccountConfig accountConfig = accountlist.get(0);
				accountConfig.setSyncMainCardTime(System.currentTimeMillis());
				accountConfig.setSyncSubCardTime(System.currentTimeMillis());
				accountManager.update(accountConfig);
			}
			logger.info("结束调用远程抓取主副卡数据,网站类型:" + type + ";用户名userName:" + userName);
		} catch (Exception e) {
			logger.error("调用远程catchService抓取主副卡数据异常:" + e.getMessage());
		}
		return successs;
	}

	// 获取验证码
	@Override
	public CatchdataGetYZMVO getYZM(String hostType, String userName,Map<String,String> browerInfo) {
		CatchdataGetYZMVO catchdataYZM = new CatchdataGetYZMVO();
		String type = getHostType(hostType);
		logger.info("调用远程catchservice获取验证码,网站:" + type + ";请求的用户名:" + userName);
		String[] result = serverService.getLoginCookiesAndCheckCode(type,browerInfo);
		logger.info("完成请求验证码,cookies:" + result[0] + "验证码路径:" + result[1]);
		catchdataYZM.setUsername(userName);
		catchdataYZM.setCookies(result[0]);
		catchdataYZM.setYzmUrl(result[1]);
		return catchdataYZM;
	}
	
	// 登陆
	@Override
	public String login(String userType, String cookies, String username, String code) {
		String success = "";
		String type = getHostType(userType);
		logger.info("开始调用远程登陆,网站类型hostType:" + type + ";请求验证码时cookeis:" + userType + ";用户名username:" + username + ";验证码code" + code);
		try {
			success = serverService.login(type, cookies, username, code);
			// 更新用户最后登陆时间
			ICCardAccountConfigExampleExtended account = new ICCardAccountConfigExampleExtended();
			account.createCriteria().andUsenameEqualTo(username).andAccounttypeEqualTo(userType);
			List<ICCardAccountConfig> accountlist;
			accountlist = accountManager.getList(account);
			if (CollectionUtils.isNotEmpty(accountlist)) {
				ICCardAccountConfig accountConfig = accountlist.get(0);
				accountConfig.setLastLoginTime(System.currentTimeMillis());
				accountManager.update(accountConfig);
			}
			logger.info("调用远程登陆结束,返回码为:" + success);
		} catch (Exception e) {
			logger.info("用户登陆及其更新用户最后登陆时间发生异常.",e);
		}
		return success;
	}

	@Override
	public ICCardAccountConfig queryAccountByID(Object model) {

		ICCardAccountConfigExampleExtended accountConfigExtended = new ICCardAccountConfigExampleExtended();
		ICCardAccountConfig accountConfig = (ICCardAccountConfig) model;
		ICCardAccountConfig account = new ICCardAccountConfig();
		String id = accountConfig.getId();
		if (StringUtils.isNotBlank(id)) {
			accountConfigExtended.createCriteria().andIdEqualTo(id);
		}
		try {
			account = accountManager.getById(accountConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}

	
	
	
	/**
	 * 查询账户数据，需要调用接口，查询用户状态等数据
	 */
	@Override
	public PaginationResult<?> queryListPage(DynamicSqlParameter requestParam) {

		logger.info("查询账户信息数据开始");

		PaginationResult<ICCardAccountConfig> result = new PaginationResult<ICCardAccountConfig>();
		try {
			String username = "";
			ICCardAccountConfigExampleExtended accountConfigExtended = new ICCardAccountConfigExampleExtended();
			Map<String, String> params = requestParam.getEqual();
			if (params != null && !params.isEmpty()) {
				username = params.get("username");
				if (StringUtils.isNotBlank(username)) {
					accountConfigExtended.createCriteria().andUsenameLike("%" + username + "%");
				}
			}
			int offset = (requestParam.getPage() - 1) * requestParam.getRows();
			int limit = requestParam.getRows();
			accountConfigExtended.setOrderByClause("creat_time desc");
			accountConfigExtended.setSkipNum(offset);
			accountConfigExtended.setLimitNum(limit);
			logger.info("调用远程查询账户数据:username:" + username + ";offset:" + offset + ";limit:" + limit);
			result = accountManager.paginate(accountConfigExtended);

			List<ICCardAccountConfig> accountlist = result.getData();
			List<ICCardAccountConfig> resultdata = new ArrayList<ICCardAccountConfig>();
			Map<String, UserStatus> userstatus = serverService.getAllUserStatus("");
			Map<String, UserStatus> userstatus2 = new HashMap<String, UserStatus>();

			Iterator<String> iter = userstatus.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				UserStatus status = userstatus.get(key);
				String name=key.substring(4);
				userstatus2.put(name,status);
			}
			for (int i = 0; i < accountlist.size(); i++) {
				ICCardAccountConfig temp = accountlist.get(i);
				UserStatus ustatus = userstatus2.get(temp.getUsename());
				String status="";
				if(ustatus != null){
					status = ustatus.getStatus();
				}
				if (StringUtils.isBlank(status)) {
					status = "0";
				}
				temp.setRemark(status);
				resultdata.add(temp);
			}
			result.setData(resultdata);
		} catch (Exception e) {
			logger.error("分页查询数据抓取账户异常", e);
		}
		logger.info("查询账户信息数据结束");
		return result;
	}

	@Override
	public PaginationResult<?> add(Object model) throws BusinessException {
		PaginationResult<ICCardAccountConfig> result = new PaginationResult<ICCardAccountConfig>();
		try {
			if (model == null) {
				return null;
			}
			ICCardAccountConfig accountConfig = (ICCardAccountConfig) model;
			accountConfig.setCreatTime(System.currentTimeMillis());
			accountManager.add(accountConfig);
		} catch (Exception e) {
			throw new BusinessException("UC_E0002:调用通用add方法异常", e);
		}
		return result;
	}

	@Override
	public String updateAccount(Object model) {
		String flag = "0";
		ICCardAccountConfig accountConfig = (ICCardAccountConfig) model;
		String id = accountConfig.getId();
		if (StringUtils.isBlank(id)) {
			logger.info("更新账户信息ID为空.");
			return null;
		}
		try {
			logger.info("开始更新账户信息,账户ID:" + id);
			accountConfig.setModifiedTime(System.currentTimeMillis());
			accountManager.update(accountConfig);
		} catch (Exception e) {
			logger.error("更新账户信息出现异常", e);
			return flag;
		}
		return flag;
	}

	@Override
	public String deleteAccount(Object model) {

		ICCardAccountConfig accountConfig = (ICCardAccountConfig) model;
		String flag = "";
		try {
			flag = String.valueOf(accountManager.remove(accountConfig));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// 根据传入type值返回所需网站类型
	private String getHostType(String type) {
		String hostType = "";
		if ("CARD-TYPE-01".equals(type)) {
			hostType = "ZSY";
		} else if ("CARD-TYPE-02".equals(type)) {
			hostType = "ZSH";
		}
		return hostType;
	}

}
