package com.ctfo.catchservice.service.internal.utils.zsh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ctfo.catchservice.bean.AccountTradeVo;
import com.ctfo.catchservice.bean.ClientSession;
import com.ctfo.catchservice.bean.LoginCode;
import com.ctfo.catchservice.bean.MainCardAndSubcardVo;
import com.ctfo.catchservice.bean.OilCardBalanceVo;
import com.ctfo.catchservice.bean.StaticMap;
import com.ctfo.catchservice.bean.TradeByConsumeVo;
import com.ctfo.catchservice.bean.WorkerTypes;
import com.ctfo.catchservice.service.queue.dataprocessing.DataProcessingQueue;
import com.ctfo.catchservice.util.CatchDataUtils;
import com.ctfo.yppt.baseservice.card.beans.ICCardMain;

/**
 * 
 * @ClassName: ZSYCatchDataUtils
 * @Description: 中石油数据抓取处理类
 * @author yuguangyang
 * @date 2015年2月4日 上午1:07:19
 *
 */
public class ZSYCatchDataUtils {

	private static final Log logger = LogFactory.getLog(ZSYCatchDataUtils.class);
	private static ResourceBundle resource = ResourceBundle.getBundle("syscatch");// 获取资源文件

	/**
	 * 
	 * @Description: 抓取卡余额
	 * @param 参数
	 * @return 返回类型
	 * @throws
	 */
	public static void catchCardBalance(String hostType, String userName) {
		String url = resource.getString("zsy.getOilCardBalance");
		String cardAsn = "", deptNo = "", currPage = "1", date = "", date1 = "";
		String requestUrl = url + "?" + "cardAsn=" + cardAsn + "&deptNo=" + deptNo + "&date=" + date + "&date1=" + date1;
		String pageSizeParam = "&currPage=";// 当前页数
		logger.info("开始抓取中石油卡余额数据,用户:" + userName);
		try {
			resultByZSY(hostType, requestUrl, currPage, pageSizeParam, OilCardBalanceVo.class, WorkerTypes.CATCH_CARDBALANCE, userName);
		} catch (IOException e) {
			logger.info("中石油抓取卡余额数据发生IOException异常", e);
		}
	}

	public static void catchMainBalance(String hostType, String userName) {
		String url = resource.getString("zsy.getMainBalance");
		logger.info("中石油开始抓取主卡余额,用户名:" + userName);
		try {
			Map<String, ICCardMain> cardmain = StaticMap.getlocalMainCard.get(userName);
			String usersortname = CatchDataUtils.getUserStoreName(hostType, userName);
			Iterator<String> iteratormain = cardmain.keySet().iterator();
			while (iteratormain.hasNext()) {
				String mainNum = iteratormain.next();
				String data = responseData(usersortname, url);
				// { userType:'2',result:'1',errorMsg:'',
				// data:',155945.44,1001228688,重庆中交兴路车联网科技有限公司,普通单位客户,,,统一开增值税发票,',
				// idAndAsn:''}
				String balance = data.split(",")[4];
				ICCardMain icCardMain = new ICCardMain();
				icCardMain.setBalance(new BigDecimal(balance).multiply(new BigDecimal(100)));
				icCardMain.setCardNumber(mainNum);
				StaticMap.cardMainBalance.add(icCardMain);
				logger.info("抓取主卡余额:卡号:" + mainNum + "余额:" + balance + "元");
			}
		} catch (IOException e) {
			logger.info("抓取主卡余额发生IOException异常", e);
		}

	}

	/**
	 * 
	 * @Description: 抓取账户交易数据
	 * @param 参数
	 * @return 返回类型
	 * @throws
	 */
	public static void catchAccountTrad(String hostType, String userName, String startDate) {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		String stdate = "";
		try {
			stdate = sf.format(sf.parse(startDate));
		} catch (ParseException e1) {
			logger.error("中石油抓取主卡交易记录数据,发生日期转化异常ParseException:", e1);
		}
		// 1：充值 2：圈存圈提 3:分配汇总 4：其它
		for (int i = 1; i < 5; i++) {
			logger.info("中石油,开始抓取主卡交易记录数据,用户名:" + userName + ";开始日期:" + stdate + "-01" + ";交易类型(1：充值 2：圈存圈提 3:分配汇总 4：其它):" + i);
			String url = resource.getString("zsy.getAccountTrade");
			String cardAsn = "", timeGeren = "", currPage = "1", date = "", date1 = "", ownId = "", Card_No = "", typeDanwei = String.valueOf(i), typeGeren = "1", timeDanwei = stdate + "-01";
			String requestUrl = url + "?" + "timeDanwei=" + timeDanwei + "&timeGeren=" + timeGeren + "&cardAsn=" + cardAsn + "&ownId=" + ownId + "&Card_No=" + Card_No + "&typeDanwei=" + typeDanwei
					+ "&typeGeren=" + typeGeren + "&date=" + date + "&date1=" + date1;
			String pageSizeParam = "&currPage=";// 当前页数
			try {
				resultByZSY(hostType, requestUrl, currPage, pageSizeParam, AccountTradeVo.class, WorkerTypes.CATCH_MAINCARD_TRADEINFO, userName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @Description:抓取消费记录数据
	 * @param 参数
	 * @return 返回类型
	 * @throws
	 */
	public static void catchTradeByConsume(String hostType, String userName, String startDate) {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		String stdate = "";
		try {
			stdate = sf.format(sf.parse(startDate));
		} catch (ParseException e1) {
			logger.error("中石油抓取副卡交易记录数据,发生日期转化异常ParseException:", e1);
		}
		logger.info("中石油,开始抓取副卡交易记录数据,用户名:" + userName + ";开始日期:" + stdate + "-01");
		String url = resource.getString("zsy.getTradeByConsume");
		String Card_No = "", currPage = "1", date = "", date1 = "", OWNER_ID = "", Data_DanWei = stdate + "-01";// Card_No（司机卡号）
		String requestUrl = url + "?" + "Card_No=" + Card_No + "&Data_DanWei=" + Data_DanWei + "&date=" + date + "&date1=" + date1 + "&OWNER_ID=" + OWNER_ID;
		String pageSizeParam = "&currPage=";// 当前页数
		try {
			resultByZSY(hostType, requestUrl, currPage, pageSizeParam, TradeByConsumeVo.class, WorkerTypes.CATCH_SUBCARD_TRADEINFO, userName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Description: 抓取主副卡数据
	 * @param 参数
	 * @return 返回类型
	 * @throws
	 */
	public static void catchMainCardAndSubcard(String hostType, String userName) {
		String url = resource.getString("zsy.getMainCardAndSubcard");
		String cardAsn = "", currPage = "1", InfoType = "C_Cardlist", fill = "0.5665796231478453", pagesize = "9999";
		String requestUrl = url + "?cardAsn=" + cardAsn + "&cardStatus=" + "" + "&InfoType=" + InfoType + "&fill=" + fill + "&pagesize=" + pagesize;
		String pageSizeParam = "&currPage=";// 当前页数
		logger.info("中石油，抓取主副卡数据,用户名:" + userName);
		try {
			resultByZSY(hostType, requestUrl, currPage, pageSizeParam, MainCardAndSubcardVo.class, WorkerTypes.CATCH_MAINANDSUBCARD, userName);
		} catch (IOException e) {
			logger.info("抓取主副卡发生异常：", e);
		}
	}

	@SuppressWarnings({ "rawtypes" })
	public static boolean updateUserStatus(String userName, String hostType) {
		boolean islogin = false;
		String url = resource.getString("zsy.getMainCardAndSubcard");
		String cardAsn = "", currPage = "1", InfoType = "C_Cardlist", fill = "0.5665796231478453", pagesize = "15";
		String requestUrl = url + "?cardAsn=" + cardAsn + "&cardStatus=" + "" + "&InfoType=" + InfoType + "&fill=" + fill + "&pagesize=" + pagesize;
		String pageSizeParam = "&currPage=";// 当前页数
		try {
			String response = responseData(userName, requestUrl + pageSizeParam + currPage);
			// {issuccessed:'false',errmsg:'登录超时，请重新登录'}
			JSONObject jsonObject = JSONObject.fromObject(response);
			String issuccessed = jsonObject.getString("issuccessed");
			if (!"false".equals(issuccessed)) {
				String data = jsonObject.get("data").toString();
				Collection c = JSONArray.toCollection(JSONArray.fromObject(data), MainCardAndSubcardVo.class);
				if (CollectionUtils.isNotEmpty(c)) {
					logger.info("刷新中石油用户在线状态，请求卡信息列表,用户为"+userName);
					islogin = true;
				}
			}else{
				logger.info("刷新中石油用户在线状态，请求卡信息列表,用户为"+userName+";返回数据:"+response);
			}
		} catch (IOException e) {
			logger.info("刷新中石油用户在线状态，请求卡信息列表,发生异常" + e);
		} catch (Exception e) {
			logger.info("刷新中石油用户在线状态，发生异常", e);
		}

		return islogin;

	}

	/**
	 * 获取全部请求的JSON结果(中石油)
	 * 
	 * @param hostType
	 *            主机名
	 * @param requestUrl
	 *            请求地址
	 * @param currPage
	 *            当前页码
	 * @param pageSizeParam
	 *            当前页参数 ：&currPage=
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void resultByZSY(String hostType, String requestUrl, String currPage, String pageSizeParam, Class<?> clazz, int workerType, String userName) throws IOException {

		String usersortname = CatchDataUtils.getUserStoreName(hostType, userName);
		String response = responseData(usersortname, requestUrl + pageSizeParam + currPage);
		if (!response.startsWith("<!DOCTYPE")) {
			// 获取总页数
			try {
				JSONObject jsonObject = JSONObject.fromObject(response);
				String errorMsg=jsonObject.getString("errorMsg");
				String issuccessed ="true";
				if(StringUtils.isNotBlank(errorMsg)){
					issuccessed = jsonObject.getString("issuccessed");
				}
				if (!"false".equals(issuccessed)) {
					int pageCount = Integer.parseInt(jsonObject.get("pageCount").toString());
					String data = jsonObject.get("data").toString();
					data = data.replace("LoyaltyAndBalance", "loyaltyAndBalance");
					Collection c = JSONArray.toCollection(JSONArray.fromObject(data), clazz);
					List templist = new ArrayList(c);
					for (int i = 0; i < templist.size(); i++) {
					
						if (workerType == 0) {// 主副卡
							MainCardAndSubcardVo mainAndSubCard = (MainCardAndSubcardVo) templist.get(i);
							mainAndSubCard.setUserName(userName);
							mainAndSubCard.setHostType(LoginCode.ZSY);
							DataProcessingQueue.getInstance().queue.put(mainAndSubCard);
						} else if (workerType == 1) {// 余额
							OilCardBalanceVo cardBalance = 	(OilCardBalanceVo) templist.get(i);
							cardBalance.setUserName(userName);
							cardBalance.setHostType(LoginCode.ZSY);
							cardBalance.setBalance(cardBalance.getBalance());
							cardBalance.setAsn(cardBalance.getAsn());
							DataProcessingQueue.getInstance().queue.put(cardBalance);
						} else if (workerType == 2) {// 主卡交易记录
							AccountTradeVo accountTrade =(AccountTradeVo) templist.get(i);
							accountTrade.setUserName(userName);
							accountTrade.setHostType(LoginCode.ZSY);
							DataProcessingQueue.getInstance().queue.put(accountTrade);
						} else if (workerType == 3) {// 副卡交易记录
							TradeByConsumeVo accountTrade = (TradeByConsumeVo) templist.get(i);
							accountTrade.setUserName(userName);
							accountTrade.setHostType(LoginCode.ZSY);
							DataProcessingQueue.getInstance().queue.put(accountTrade);
						}
					}
					// 循环请求,累加数据
					for (int j = 1; j < pageCount; j++) {
						currPage = j + 1 + "";// 当前页
						String nextResponse = responseData(usersortname, requestUrl + pageSizeParam + currPage);
						nextResponse = nextResponse.replace("LoyaltyAndBalance", "loyaltyAndBalance");
						JSONObject jsonObject1 = JSONObject.fromObject(nextResponse);
						errorMsg=jsonObject.getString("errorMsg");
						issuccessed ="true";
						if(StringUtils.isNotBlank(errorMsg)){
							issuccessed = jsonObject.getString("issuccessed");
						}
						if (!"false".equals(issuccessed)) {
							String nextData = jsonObject1.get("data").toString();
							Collection c1 = JSONArray.toCollection(JSONArray.fromObject(nextData), clazz);
							templist = new ArrayList(c1);
							for (int i = 0; i < templist.size(); i++) {
								if (workerType == 0) {// 主副卡
									MainCardAndSubcardVo mainAndSubCard = (MainCardAndSubcardVo) templist.get(i);
									mainAndSubCard.setUserName(userName);
									mainAndSubCard.setHostType(LoginCode.ZSY);
									DataProcessingQueue.getInstance().queue.put(mainAndSubCard);
								} else if (workerType == 1) {// 余额
									OilCardBalanceVo cardBalance = (OilCardBalanceVo) templist.get(i);
									cardBalance.setUserName(userName);
									cardBalance.setHostType(LoginCode.ZSY);
									DataProcessingQueue.getInstance().queue.put(cardBalance);
								} else if (workerType == 2) {// 主卡交易记录
									AccountTradeVo accountTrade = (AccountTradeVo) templist.get(i);
									accountTrade.setUserName(userName);
									accountTrade.setHostType(LoginCode.ZSY);
									DataProcessingQueue.getInstance().queue.put(accountTrade);
								} else if (workerType == 3) {// 副卡交易记录
									TradeByConsumeVo accountTrade = (TradeByConsumeVo) templist.get(i);
									accountTrade.setUserName(userName);
									accountTrade.setHostType(LoginCode.ZSY);
									DataProcessingQueue.getInstance().queue.put(accountTrade);
								}
							}
						}
					}
				}
			} catch (InterruptedException e) {
				logger.error("抓取中石油数据发生InterruptedException异常," + e);
			}
		}
	}

	/**
	 * 获取响应数据
	 * 
	 * @param username
	 *            登录用户名称（类似于：ZSY_zjxlhn）
	 * @param requestUrl
	 *            请求地址
	 * @return
	 * 
	 */
	public static String responseData(String username, String requestUrl) throws IOException {
		ClientSession clientSession = StaticMap.clientMap.get(username);
		HttpClient httpClient = clientSession.getClient();
		GetMethod getMethod = new GetMethod(requestUrl);
		String response = "";
		try {
			getMethod.setRequestHeader("Connection", "keep-alive");
			getMethod.setRequestHeader("Cookie", clientSession.getCookies());
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("Method failed: " + getMethod.getStatusLine());
				return "";
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsStream(), "utf8"));
			// BufferedReader reader = new BufferedReader(new
			// InputStreamReader));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str = reader.readLine()) != null) {
				stringBuffer.append(str);
			}
			response = stringBuffer.toString();
			//response = getMethod.getResponseBodyAsString();
			Thread.sleep(2000);
		} catch (HttpException e) {
			logger.error("抓取中石油数据发生HttpException异常,", e);
		} catch (InterruptedException e) {
			logger.info("抓取中石油数据发生InterruptedException异常:", e);
		} finally {
			getMethod.releaseConnection();
		}
		return response;
	}
}
