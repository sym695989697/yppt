package com.ctfo.chpt.action.iccard.trade;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.chpt.bean.iccard.catchdata.vo.CatchdataGetYZMVO;
import com.ctfo.chpt.service.iccard.trade.CatchdataServiceImpl;
import com.ctfo.chpt.service.iccard.trade.ICatchdataService;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.yppt.baseservice.system.beans.ICCardAccountConfig;

/**
 * 
 * @ClassName: CatchdataController
 * @Description: 数据抓去账户管理
 * @author yuguangyang
 * @date 2015年1月22日 下午1:59:06
 *
 */
@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/catchdata")
public class CatchdataController extends BaseControler {
	private static Log logger = LogFactory.getLog(CatchdataController.class);
	@Resource(name = "catchdataServiceImpl", description = "抓取交易记录管理 service")
	ICatchdataService catchdataService;
	private static Map<String, String> loginCode = null;
	static {
		loginCode = new HashMap<String, String>();
		loginCode.put("A0000", "登陆失败");
		loginCode.put("A0001", "登陆成功");
		loginCode.put("C0000", "验证码为空");
		loginCode.put("C0001", "验证码错误");
		loginCode.put("P0000", "密码为空");
		loginCode.put("P0001", "密码不合法");
	}

	public CatchdataController() {
		model = new ICCardAccountConfig();
		service = new CatchdataServiceImpl();
	}
	
	// 抓取消费交易数据
	@RequestMapping(value = "/catchTradeByConsume", produces = "application/json")
	@ResponseBody
	public String catchTradeByConsume(@RequestParam("hostType") String hostType, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam("userName") String userName) {
		String success = catchdataService.catchTradeByConsume(hostType, startDate, endDate, userName);
		return success;
	}

	// 抓取账户交易数据 主卡交易
	@RequestMapping(value = "/catchAccountTrade", produces = "application/json")
	@ResponseBody
	public String catchAccountTrade(@RequestParam("hostType") String hostType, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam("userName") String userName) {
		String success = catchdataService.catchAccountTrade(hostType, startDate, endDate, userName);
		return success;
	}

	// 抓取并更新卡余额
	@RequestMapping(value = "/catchCardBalance", produces = "application/json")
	@ResponseBody
	public String catchCardBalance(@RequestParam("hostType") String hostType, @RequestParam("userName") String userName) {
		String success = catchdataService.catchCardBalance(hostType, userName);
		return success;
	}

	@RequestMapping(value = "/catchMainAndSubCard", produces = "application/json")
	@ResponseBody
	public String catchMainAndSubCard(@RequestParam("hostType") String hostType, @RequestParam("userName") String userName) {
		String success = catchdataService.catchMainAndSubCard(hostType, userName);
		return success;
	}

	// 获取验证码
	@RequestMapping(value = "/getyzm", produces = "application/json")
	@ResponseBody
	public CatchdataGetYZMVO getYZM(HttpServletRequest request, @RequestParam("hostType") String hostType, @RequestParam("userName") String userName) {
		Map<String,String> browserInfo = new HashMap<String,String>();
		Enumeration<?> headers = request.getHeaderNames();
		String head = "";
		while (headers.hasMoreElements()) {
			head = headers.nextElement().toString();
			browserInfo.put(head, request.getHeader(head));
		}
		CatchdataGetYZMVO yzmVO = new CatchdataGetYZMVO();
		try {
			yzmVO = catchdataService.getYZM(hostType, userName,browserInfo);
		} catch (Exception e) {
			logger.error("获取验证码发生异常,",e);
		}
		return yzmVO;
	}

	// 登陆
	@RequestMapping(value = "/login", produces = "application/json")
	@ResponseBody
	public String login(@RequestParam("hostType") String hostType, @RequestParam("cookies") String cookies, @RequestParam("userName") String userName, @RequestParam("code") String code) {
		String result = "";
		try {
			result = catchdataService.login(hostType, cookies, userName, code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginCode.get(result);
	}

	/**
	 * 
	 * @Description: 列表页面
	 * @return PaginationResult
	 * @throws
	 */
	@RequestMapping(value = "/queryCatchdataListPage", produces = "application/json")
	@ResponseBody
	public PaginationResult<?> queryCatchdataListPage() {
		try {
			result = catchdataService.queryListPage(requestParam);
		} catch (BusinessException e) {
			logger.info("查询列表数据异常", e);
		}
		return result;
	}

	@RequestMapping(value = "/queryCatchdataUserById", produces = "application/json")
	@ResponseBody
	public ICCardAccountConfig queryCatchdataUserById() {
		ICCardAccountConfig account = catchdataService.queryAccountByID(model);
		return account;
	}

	@RequestMapping(value = "/addAccount", produces = "application/json")
	@ResponseBody
	public PaginationResult<?> addAccount() {
		try {
			result = catchdataService.add(model);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/updateAccount", produces = "application/json")
	@ResponseBody
	public String updateAccount() {
		String flag = catchdataService.updateAccount(model);
		return flag;
	}

	@RequestMapping(value = "/deleteAccount", produces = "application/json")
	@ResponseBody
	public String deleteAccount() throws BusinessException {
		return catchdataService.deleteAccount(model);
	}

}
