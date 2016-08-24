package com.ctfo.catchservice.service.internal.utils.zsh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ctfo.catchservice.bean.AccountTradeVo;
import com.ctfo.catchservice.bean.ClientSession;
import com.ctfo.catchservice.bean.InfoResultDto;
import com.ctfo.catchservice.bean.LoginCode;
import com.ctfo.catchservice.bean.MainCardAndSubcardVo;
import com.ctfo.catchservice.bean.OilCardBalanceVo;
import com.ctfo.catchservice.bean.StaticMap;
import com.ctfo.catchservice.bean.TradeByConsumeVo;
import com.ctfo.catchservice.service.queue.dataprocessing.DataProcessingQueue;
import com.ctfo.catchservice.util.CatchDataUtils;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.beans.ICCardMain;

/**
 * 
 * @ClassName: ZSHCatchDataUtils
 * @Description: 中石化抓取数据帮助类
 * @author yuguangyang
 * @date 2015年1月27日 下午2:05:30
 *
 */
public class ZSHCatchDataUtils {
	private static final Log logger = LogFactory.getLog(ZSHCatchDataUtils.class);
	private static ResourceBundle resource = ResourceBundle.getBundle("syscatch");// 获取资源文件

	// <<<<<<<<<<<<<<<<<<<<<<<<<<开始抓取交易记录数据<<<<<<<<<<<<<<<<<<<<
	/**
	 * 
	 * @Description: 抓取交易记录
	 * @param hostType
	 *            网站类型
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param userName
	 *            用户名 直接调用传来的username
	 *            如：zj2493297，需要做处理，调用CatchDataUtils.getUserStoreName()方法
	 * @param
	 * @return 返回类型
	 * @throws
	 */
	public static void catchTradeData(String hostType, String startDate, String endDate, String userName) {
		String usersortname = CatchDataUtils.getUserStoreName(hostType, userName);
		Iterator<String> maincard = StaticMap.getlocalMainCard.get(userName).keySet().iterator();// 获取该用户下所有主卡
		String vaildCard = resource.getString("zsh.vaildcardstatus");
		String subcardinfo = resource.getString("zhs.subcardinfo");
		String url = resource.getString("zsh.qiehuan");
		while (maincard.hasNext()) {
			String currentMainNum = userCurrentMainCard(hostType, usersortname);// 当前主卡
			String mainnum = maincard.next();// 主卡卡号
			List<ICCard> cardlist = StaticMap.getLocalSubCard.get(mainnum);
			if (currentMainNum.equals(mainnum)) {
				logger.info("网站用户当前主卡为:" + currentMainNum + ";本地循环主卡为:" + mainnum);
			} else {
				NameValuePair mainNumber = new NameValuePair("num", mainnum);
				NameValuePair[] params = { mainNumber };
				catchMainBalancePostRequst(usersortname, url, params, hostType, url + "?num=" + currentMainNum);
			}
			if (CollectionUtils.isNotEmpty(cardlist)) {
				for (int i = 0; i < cardlist.size(); i++) {
					String  cardNo= cardlist.get(i).getCardNumber();
					String parentCardNo= cardlist.get(i).getParentCardNumber();
					NameValuePair holderCardNo = new NameValuePair("holderCardNo",cardNo);
					NameValuePair priCardNo = new NameValuePair("priCardNo",parentCardNo);
					NameValuePair vaildparams[] = { holderCardNo, priCardNo };
					String first=getPostRequst(usersortname, vaildCard, vaildparams, hostType);
					logger.info("抓取副卡交易记录请求第一步完成,验证是否为该主卡下副卡，副卡卡号"+cardNo+";主卡卡号"+parentCardNo+";返回数据:"+first);
					NameValuePair sunnum = new NameValuePair("viceCard", cardNo);
					NameValuePair mnum = new NameValuePair("cardMember.cardNo", parentCardNo);
					NameValuePair cardifoparams[] = { sunnum, mnum };
					String second = getPostRequst(usersortname, subcardinfo, cardifoparams, hostType);
					logger.info("抓取副卡交易记录请求第二步完成,获取副卡信息,副卡卡号:"+cardNo+";主卡卡号:"+parentCardNo+";返回数据:"+second);
					NameValuePair cardNumber = new NameValuePair("cardMember.cardNo", cardNo);
					NameValuePair startTime = new NameValuePair("startTime", startDate);
					NameValuePair endTime = new NameValuePair("endTime", endDate);
					NameValuePair traType = new NameValuePair("traType", "false");
					NameValuePair dateFlag = new NameValuePair("dateFlag", "true");
					NameValuePair params[] = { cardNumber, startTime, endTime, traType, dateFlag };
					String cardinfourl = resource.getString("zsh.get_card_tran_info") + "?sjs=" + String.valueOf(new Date().getTime());
					String data = getPostRequst(usersortname, cardinfourl, params, hostType);
					logger.info("抓取副卡交易记录第三步完成,开始调用处理数据方法");
					handleTradeInfo(data, userName);
				}
			}
		}
	}

	// 获取用户当前主卡
	public static String userCurrentMainCard(String hostType, String userName) {
		String currentCard = "";// 网站当前用户主卡
		String mainCardUrl = resource.getString("zsh.main_card_list");
		String data = getPostRequst(userName, mainCardUrl, null, hostType);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(data);
			currentCard = node.get("cardNo").asText();
		} catch (JsonProcessingException e) {
			logger.info("获取用户当前主卡异常:" + e);
		} catch (IOException e) {
			logger.info("获取用户当前主卡发生异常:" + e);
		}
		return currentCard;
	}

	private static void handleTradeInfo(String data, String userName) {

		try {
			if (data.startsWith("<html>")) {
				logger.info("抓取副卡交易记录,请求连接返回数据错误，404");
				return;
			}
			if (data.equals("")) {
				logger.info("抓取副卡交易记录,返回数据为空");
				return;
			}
			ObjectMapper mapper = new ObjectMapper();
			// 用此类构造字符串
			JsonNode node = mapper.readTree(data);
			if (node.get("msg") != null) {
				logger.info("抓取交易记录返回错误信息，详细信息:"+node.get("msg").toString());
				return;
			}
			Iterator<JsonNode> iterator = node.get("list").getElements();
			// {"amount":"950000","balance":"950000","litre":"0","oilName":"*","price":"0","opeTime":"2015-01-14 19:58:26","reward":"0",
			// "nodeTag":"南京六合长江四桥东圈","traName":"圈存"}
			for (; iterator.hasNext();) {
				JsonNode n = iterator.next();
				String cardNo = node.get("no").asText();
				String amount = n.get("amount").asText();
				String cardBalance = n.get("balance").asText();
				String oilNum = n.get("litre").asText();
				String oilName = n.get("oilName").asText();
				String opeTime = n.get("opeTime").asText();
				String nodeTag = n.get("nodeTag").asText();
				String traName = n.get("traName").asText();
				String price = n.get("price").asText();
				// //(1:圈存，2:加油,3:充值,4:分配)
				String type = "IC-OTHER";
				if ("圈存".equals(traName)) {
					type = "IC-TRANSFER";
				} else if ("加油".equals(traName)) {// IC卡消费
					type = "IC-GAS";
				} else if ("充值".equals(traName)) {
					type = "IC-RECHARGE";
				} else if ("分配".equals(traName)) {
					type = "IC-DISTRIBUTION";
				}
				TradeByConsumeVo tradeByConsume = new TradeByConsumeVo();
				tradeByConsume.setAmount(amount);
				tradeByConsume.setBalance(cardBalance);
				tradeByConsume.setCardAsn(cardNo);
				tradeByConsume.setGiftName(oilName);
				tradeByConsume.setTradeType(type);
				tradeByConsume.setTradeName(traName);
				tradeByConsume.setVolumn(oilNum);// 默认取得值为乘以100后的
				tradeByConsume.setOrgName(nodeTag);
				tradeByConsume.setOccurTime(opeTime);
				tradeByConsume.setProductPrice(price);
				tradeByConsume.setHostType(LoginCode.ZSH);
				tradeByConsume.setUserName(userName);
				DataProcessingQueue.getInstance().queue.put(tradeByConsume);
				logger.info("解析副卡交易记录数据成功,卡号为：" + cardNo + "----抓取数据为：" + n.toString());
			}
		} catch (JsonProcessingException e) {
			logger.error("抓取交易数据JsonProcessingException异常", e);
		} catch (IOException e) {
			logger.error("抓取交易数据IOException异常", e);
		} catch (Exception e) {
			logger.error("抓取交易数据，返回数据异常", e);
		}
	}

	// <<<<<<<<<<<<<<<<<<<<<<<<<<结束抓取交易记录数据<<<<<<<<<<<<<<<<<<<<
	// <<<<<<<<<<<<<<<<<<<<<<<<<<开始抓取充值数据<<<<<<<<<<<<<<<<<<<<
	/**
	 * 
	 * @Description:抓取充值数据
	 * @param hostType
	 *            网站类型
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return List<AccountTradeVo>
	 * @throws
	 */
	public static void catchRechargeData(String hostType, String startDate, String endDate, String userName) {
		String usersortname = CatchDataUtils.getUserStoreName(hostType, userName);
		InfoResultDto ird = new InfoResultDto();
		ird.setResultEndTime("");
		ird.setRetFlag("0");
		Iterator<String> iter = StaticMap.getlocalMainCard.get(userName).keySet().iterator();
		logger.info("处理充值数据开始.其中，主卡数据个数:"+ StaticMap.getlocalMainCard.get(userName).size());
		while (iter.hasNext()) {
			String key = iter.next();
			getRechargeDatas(usersortname, key, ird, hostType, startDate, endDate);
		}
		logger.info("处理充值数据结束.");
	}

	/**
	 * 
	 * @Description: 循环获取充值数据，根据 retFlag和endTimeString判断
	 * @param 参数
	 * @return 返回类型
	 * @throws
	 */
	private static void getRechargeDatas(String username, String mainCardNumber, InfoResultDto ird, String hosttype, String startDate, String endDate) {
		String url = resource.getString("zsh.get_pay_detail");
		NameValuePair cardMember = new NameValuePair("cardMember.cardNo", mainCardNumber);
		NameValuePair startTime = new NameValuePair("startTime", startDate);
		NameValuePair endTime = new NameValuePair("endTime", endDate);
		NameValuePair endTimeString = new NameValuePair("endTimeString", ird.getResultEndTime());
		NameValuePair retFlag1 = new NameValuePair("retFlag", ird.getRetFlag());
		NameValuePair params[] = { cardMember, startTime, endTime, endTimeString, retFlag1 };
		String data = getPostRequst(username, url, params, hosttype);
		if ("".equals(data) || data != null) {
			ird = handleRechargeInfo(data, mainCardNumber, username);
			String retFlag = ird.getRetFlag();
			if (!"".equals(retFlag) && retFlag != null) {
				if (!"0".equals(retFlag)) {
					getRechargeDatas(username, mainCardNumber, ird, hosttype, startDate, endDate);
				}
			}
		}
	}

	/**
	 * 
	 * @Description:处理充值数据
	 * @param 参数
	 * @return 返回类型
	 * @throws
	 */
	private static InfoResultDto handleRechargeInfo(String data, String mainCardNumber, String userName) {
		InfoResultDto ird = new InfoResultDto();
		if (data.startsWith("<html>")) {
			logger.info("请求连接返回数据错误，404");
			return ird;
		}
		ObjectMapper mapper = new ObjectMapper();
		// 用此类构造字符串
		try {
			JsonNode node = mapper.readTree(data);
			if (node.get("msg") != null) {
				logger.info("抓取主卡充值记录返回错误信息，详细信息:"+node.get("msg").toString());
				return ird;
			}
			Iterator<JsonNode> iterator = node.get("list").getElements();
			// {"num":1.2E7,"list":[{"amount":"12000000","opeTime":"2015-01-13 16:47:23","nodeTag":"中石化网上营业厅","advpayType":"邮储网银"}],
			// "resultEndTime":"397","retFlag":1}
			for (; iterator.hasNext();) {
				JsonNode n = iterator.next();
				AccountTradeVo accountTradeVo = new AccountTradeVo();
				String amount = n.get("amount").toString().replace("\"", "");
				String optTime = n.get("opeTime").toString().replace("\"", "");
				String nodeTag = n.get("nodeTag").toString().replace("\"", "");
				String tradeType = "IC-RECHARGE";
				String advpayType = n.get("advpayType").toString().replace("\"", "");
				accountTradeVo.setTradeType(tradeType);
				accountTradeVo.setTradeName(advpayType);
				accountTradeVo.setAmount(amount);
				accountTradeVo.setAsn(mainCardNumber);
				accountTradeVo.setOrg(nodeTag);
				accountTradeVo.setTime(optTime);
				accountTradeVo.setTradeStatus("IC-TRADE-SUCCESS");// 交易状态
				accountTradeVo.setUserName(userName);
				accountTradeVo.setHostType(LoginCode.ZSH);
				DataProcessingQueue.getInstance().queue.put(accountTradeVo);
				logger.info("解析中石化充值数据，卡号:" + mainCardNumber + "; 解析数据为:" + n.toString());
			}
			String retFlag = node.get("retFlag").asText();
			String resultEndTime = node.get("resultEndTime").asText();
			ird.setResultEndTime(resultEndTime);
			ird.setRetFlag(retFlag);
		} catch (JsonProcessingException e) {
			logger.error("JsonProcessingException异常", e);
		} catch (IOException e) {
			logger.error("IOException异常", e);
		} catch (InterruptedException e) {
			logger.error("程序发生InterruptedException异常", e);
			Thread.currentThread().interrupt();
		}
		return ird;
	}

	// <<<<<<<<<<<<<<<<<<<<<<<<<<结束抓取充值数据<<<<<<<<<<<<<<<<<<<<
	// <<<<<<<<<<<<<<<<<<<<<<<<<<开始处理预分配数据<<<<<<<<<<<<<<<<<<<<
	/**
	 * 
	 * @Description:中石化查询预分配数据
	 * @param hostType
	 *            网站类型 中石化/中石油
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return List<AccountTradeVo>
	 * @throws
	 */
	public static void catchAllotData(String hostType, String startDate, String endDate, String userName) {

		String usersortname = CatchDataUtils.getUserStoreName(hostType, userName);
		logger.info("处理预分配数据开始.");
		InfoResultDto ird = new InfoResultDto();
		ird.setResultEndTime("");
		ird.setRetFlag("0");
		Iterator<String> iter = StaticMap.getlocalMainCard.get(userName).keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			getAllotDatas(usersortname, key, ird, hostType, startDate, endDate);
		}

		logger.info("处理预分配数据结束.");
	}

	/**
	 * 抓取预分配数据，抓取数据存在分页数据，其中网站实现分页为“更多”点击，每次点击更多进行数据取出，这里主要为根据retFlag来做判断 ，其中
	 * retFlag如果不为0，则要不间断抓取，一直到返回0为止
	 * 
	 * @param session
	 * @param userMainCard
	 *            主卡对象
	 * @param ird
	 *            抓取预分配数据 返回数据 主要记录 endTimeString及其retFlag
	 */
	private static void getAllotDatas(String username, String mainCardNumber, InfoResultDto ird, String hosttype, String startDate, String endDate) {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isBlank(startDate)) {
			startDate = sf.format(new Date());
		}
		if (StringUtils.isBlank(endDate)) {
			startDate = sf.format(new Date());
		}
		String url = resource.getString("zsh.get_auto_child_card");
		NameValuePair cardMember = new NameValuePair("cardMember.cardNo", mainCardNumber);
		NameValuePair startTime = new NameValuePair("startTime", startDate);
		NameValuePair endTime = new NameValuePair("endTime", endDate);
		NameValuePair endTimeString = new NameValuePair("endTimeString", ird.getResultEndTime());
		NameValuePair retFlag2 = new NameValuePair("retFlag", ird.getRetFlag());
		NameValuePair viceCard = new NameValuePair("viceCard:", "");
		NameValuePair searchFlag = new NameValuePair("searchFlag", "0");
		NameValuePair dateFlag = new NameValuePair("dateFlag", "true");
		NameValuePair params[] = { cardMember, startTime, endTime, endTimeString, retFlag2, viceCard, searchFlag, dateFlag };
		String data = getPostRequst(username, url, params, hosttype);
		if ("".equals(data) || data != null) {
			ird = handleAllotInfo(data, username);
			String retFlag = ird.getRetFlag();
			if (!"".equals(retFlag) && retFlag != null) {
				if (!"0".equals(retFlag)) {
					getAllotDatas(username, mainCardNumber, ird, hosttype, startDate, endDate);
				}
			}
		}
	}

	/**
	 * 
	 * @Description:处理预分配数据
	 * @param 参数
	 * @return 返回类型
	 * @throws
	 */
	private static InfoResultDto handleAllotInfo(String data, String userName) {
		InfoResultDto ird = new InfoResultDto();
		try {
			if (data.startsWith("<html>")) {
				logger.info("请求连接返回数据错误");
				return ird;
			}
			ObjectMapper mapper = new ObjectMapper();
			// 用此类构造字符串
			JsonNode node = mapper.readTree(data);
			if (node.get("msg") != null) {
				logger.info("抓取预分配数据返回错误信息，详细信息:"+node.get("msg").toString());
				ird.setResultEndTime("");
				ird.setRetFlag("0");
				return ird;
			}
			Iterator<JsonNode> iterator = node.get("list").getElements();
			for (; iterator.hasNext();) {
				JsonNode n = iterator.next();
				String cardNo = n.get("cardNo").toString().replace("\"", "");
				AccountTradeVo accountTradeVo = new AccountTradeVo();
				String amount = n.get("amount").toString().replace("\"", "");
				String opeTime = n.get("opeTime").toString().replace("\"", "");
				String nodeTag = n.get("nodeTag").toString().replace("\"", "");
				String traName = n.get("traName").toString().replace("\"", "");
				String tradeType = "";// 额度预分配00，反额度预分配10，充值20
				if ("额度预分配".equals(traName)) {
					tradeType = "IC-DISTRIBUTION";
				} else if ("反额度预分配".equals(traName)) {
					tradeType = "IC-DISTRIBUTION";
				} else if ("充值".equals(traName)) {
					tradeType = "IC-RECHARGE";
				} else {
					tradeType = "IC-OTHER";// 无法匹配类型
				}
				// {"amount":"650000","cardNo":"1000113500002969329","opeTime":"2015-01-27
				// 09:12:01
				// ","cardHolder":"张豪杰","nodeTag":"福州客服发卡点","cardIntegral":"0","traName":"额度预分配"},
				accountTradeVo.setTradeType(tradeType);
				accountTradeVo.setTradeName(traName);
				accountTradeVo.setAmount(amount);
				accountTradeVo.setAsn(cardNo);
				accountTradeVo.setOrg(nodeTag);
				accountTradeVo.setTime(opeTime);
				accountTradeVo.setTradeStatus("IC-TRADE-SUCCESS");// 交易状态
				accountTradeVo.setHostType(LoginCode.ZSH);
				accountTradeVo.setUserName(userName);
				DataProcessingQueue.getInstance().queue.put(accountTradeVo);
				logger.info("抓取到主卡预分配数据，卡号为：" + cardNo + ";解析数据为:" + n.toString());
			}
			String retFlag = node.get("retFlag").asText();
			String resultEndTime = node.get("resultEndTime").asText();
			ird.setResultEndTime(resultEndTime);
			ird.setRetFlag(retFlag);
		} catch (JsonProcessingException e) {
			logger.error("抓取预分配数据,JsonProcessingException异常", e);
		} catch (IOException e) {
			logger.error("抓取预分配数据,IOException异常", e);
		} catch (InterruptedException e) {
			logger.error("抓取预分配数据发生InterruptedException异常,",e);
		}
		return ird;
	}

	// <<<<<<<<<<<<<<<<<<<<<<<<<<结束处理预分配数据<<<<<<<<<<<<<<<<<<<<
	// <<<<<<<<<<<<<<<<<<<<<<<<<<刷新卡余额<<<<<<<<<<<<<<<<<<<<

	/**
	 * 
	 * @Description: 抓取主卡余额
	 * @param 参数
	 * @return 返回类型
	 * @throws
	 */
	public static void catchMainBalance(String hostType, String userName) {

		Iterator<String> iter = StaticMap.getlocalMainCard.get(userName).keySet().iterator();
		String usersortname = CatchDataUtils.getUserStoreName(hostType, userName);
		String url = resource.getString("zsh.qiehuan");
		int i = 0;
		String prmainnum = "";
		while (iter.hasNext()) {
			String key = iter.next();
			if (i > 0) {
				NameValuePair mainNumber = new NameValuePair("num", key);
				NameValuePair[] params = { mainNumber };
				catchMainBalancePostRequst(usersortname, url, params, hostType, url + "?num=" + prmainnum);
			}
			getMainBalance(usersortname, hostType, key);
			prmainnum = key;
			i++;
		}
	}

	/**
	 * 
	 * @Description: 处理主卡余额数据
	 * @param 参数
	 * @return 返回类型
	 * @throws
	 */
	private static void getMainBalance(String username, String hostType, String mainnum) {
		String zhuye = resource.getString("zsh.get_user_info");
		String url = resource.getString("zsh.qiehuan");
		String data = catchMainBalancePostRequst(username, zhuye, null, hostType, url + "?num=" + mainnum);
		// {"dayNum":341,"cardInfo":{"cardNo":"1000113500002703822","balance":"11135691","startDate":"2014-03-04",
		// "compType":"02","compNo":"350200108588","cardHolder":"张豪杰","compName":"*北京中交兴路车联网科技有限公司*","preBalance":"0"},"success":true}
		// 用此类构造字符串
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node;
		try {
			node = mapper.readTree(data);
			if (node.get("msg") != null) {
				logger.error(node.get("msg").toString());
			}
			JsonNode cardInfo = node.get("cardInfo");
			String cardNo = cardInfo.get("cardNo").asText();
			String balance = cardInfo.get("balance").asText();
			ICCardMain icCardMain = new ICCardMain();
			icCardMain.setCardNumber(cardNo);
			icCardMain.setBalance(new BigDecimal(balance));
			StaticMap.cardMainBalance.add(icCardMain);
			logger.info("抓取主卡余额:卡号:" + cardNo + ";卡余额:" + balance + ";解析数据为:" + cardInfo.toString());
		} catch (JsonProcessingException e) {
			logger.error("抓取主卡余额数据发生JsonProcessingException异常,",e);
		} catch (IOException e) {
			logger.error("抓取主卡余额发生IOException异常,",e);
		}
	}

	/**
	 * 
	 * @Description: 抓取卡余额数据
	 * @param 参数
	 * @return 返回类型
	 * @throws
	 */
	public static void catchCardBalance(String hostType, String userName) {
		final String usersortname = CatchDataUtils.getUserStoreName(hostType, userName);
		catchMainBalance(hostType, userName);
		logger.info("开始抓取卡余额数据....");
		InfoResultDto ird = new InfoResultDto();
		ird.setCardNum("0");
		Iterator<String> iter = StaticMap.getlocalMainCard.get(userName).keySet().iterator();
		String maininfourl = resource.getString("zsh.get_maincard_info");
		while (iter.hasNext()) {
			String key = iter.next();
			NameValuePair cardNo = new NameValuePair("cardMember.cardNo", key);
			NameValuePair vaildparams[] = { cardNo };
			getPostRequst(usersortname, maininfourl, vaildparams, hostType);
			getBalance(usersortname, key, hostType, ird);
		}
		logger.info("结束抓取卡余额数据....");
	}

	/**
	 * 
	 * @Description: 循环获取卡余额数据
	 * @param 参数
	 * @return 返回类型
	 * @throws
	 */
	private static void getBalance(String username, String mainCardNumber, String hostType, InfoResultDto ird) {

		String url = resource.getString("zsh.get_card_balance");
		NameValuePair mainNumber = new NameValuePair("cardMember.cardNo", mainCardNumber);
		NameValuePair cardNum1 = new NameValuePair("cardNum", ird.getCardNum());// 每次请求条数，默认为每次返回99条
		NameValuePair params[] = { mainNumber, cardNum1 };
		String data = getPostRequst(username, url, params, hostType);
		if (StringUtils.isNotBlank(data)) {
			ird = handleBalanceInfo(data, username);
			String cardNum = ird.getCardNum();
			String resultNum = ird.getResultNum();
			if (!"".equals(cardNum) || cardNum != null) {
				if (!"0".equals(resultNum)) {
					getBalance(username, mainCardNumber, hostType, ird);
				}
			}
		}
	}

	/**
	 * 
	 * @Description:处理卡余额数据
	 * @param 参数
	 * @return 返回类型
	 * @throws
	 */
	public static int total = 0;

	private static InfoResultDto handleBalanceInfo(String data, String userName) {
		InfoResultDto ird = new InfoResultDto();
		String cardNum = "";
		// 用此类构造字符串
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(data);
			if (node.get("msg") != null) {
				logger.info("抓取卡余额返回错误信息，详细信息:"+node.get("msg").toString());
				ird.setCardNum("0");
				ird.setResultNum("0");
				return ird;
			}
			Iterator<JsonNode> iterator = node.get("list").getElements();
			int i = 0;
			for (; iterator.hasNext();) {
				JsonNode n = iterator.next();
				String cardNo = n.get("cardNo").asText();
				OilCardBalanceVo card = new OilCardBalanceVo();
				JsonNode cardbal = n.get("cardBalance");
				String balance = "";
				if (cardbal != null) {
					balance = cardbal.asText();// 卡余额
				}
				String preBalance = n.get("preBalance").asText();// 备付金余额
				if (StringUtils.isNotBlank(cardNo)) {
					card.setBalance(preBalance);
					card.setCardBalance(balance);
					card.setAsn(cardNo);
					card.setLastCardTime(String.valueOf(System.currentTimeMillis()));
					card.setHostType(LoginCode.ZSH);
					card.setUserName(userName);
					DataProcessingQueue.getInstance().queue.put(card);
					total++;
				}
				i++;
				logger.info("抓取卡余额数据，卡号：" + cardNo);
			}
			cardNum = node.get("cardNum").asText();
			ird.setCardNum(cardNum);
			ird.setResultNum(String.valueOf(i));
		} catch (JsonProcessingException e) {
			logger.info("查询卡余额，JsonProcessingException异常:" + e.getMessage());
		} catch (IOException e) {
			logger.info("查询卡余额，IOException异常:" + e.getMessage());
		} catch (InterruptedException e) {
			logger.error("抓取卡余额发生InterruptedException异常:",e);
		}
		return ird;
	}

	// <<<<<<<<<<<<<<<<<<<<<<<<<<结束处理刷新卡余额<<<<
	// <<<<<<<<<<<<<<抓取主副卡<<<<<<<<<<<<<<<<<<<<<
	/**
	 * 
	 * @Description:抓取主副卡数据
	 * @param 参数
	 * @return 返回类型
	 * @throws
	 */
	public static Map<String, MainCardAndSubcardVo> tempmain = new HashMap<String, MainCardAndSubcardVo>();

	public static void getMainAndSubCard(String hostType, String userName) {
		String usersortname = CatchDataUtils.getUserStoreName(hostType, userName);
		logger.info("开始抓取主副卡数据....");
		List<MainCardAndSubcardVo> result = new ArrayList<MainCardAndSubcardVo>();
		String mainCardUrl = resource.getString("zsh.main_card_list");
		List<MainCardAndSubcardVo> mainCard = new ArrayList<MainCardAndSubcardVo>();

		String data = getPostRequst(usersortname, mainCardUrl, null, hostType);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(data);
			Iterator<JsonNode> iterator = node.get("list").getElements();
			for (; iterator.hasNext();) {
				MainCardAndSubcardVo mcas = new MainCardAndSubcardVo();
				JsonNode n = iterator.next();
				String mainCardNo = n.get("cardNo").getTextValue();
				mcas.setAsn(mainCardNo);
				mcas.setIsMaster("是");
				mcas.setDriverName(n.get("cardHolder").asText());
				result.add(mcas);
				tempmain.put(mainCardNo, mcas);
				mcas.setHostType(LoginCode.ZSH);
				mcas.setUserName(userName);
				DataProcessingQueue.getInstance().queue.put(mcas);
				logger.info("抓取到中石化主卡，卡号:" + mainCardNo);
			}
		} catch (JsonProcessingException e) {
			logger.error("抓取主卡数据发生JsonProcessingException异常：" + e.getMessage());
		} catch (IOException e) {
			logger.error("抓取主卡数据发生IOException异常：" + e.getMessage());
		} catch (InterruptedException e) {
			logger.error("抓取主卡数据发生InterruptedException异常:", e);
		}
		mainCard.addAll(result);
		for (int j = 0; j < result.size(); j++) {
			getSubCards(userName, result.get(j).getAsn(), "", "0", hostType);// 获取主卡下所有子卡
		}
		logger.info("结束抓取主副卡数据....");
	}

	/*
	 * 获取主卡下所有子卡： state 返回0有数据 1为没有数据
	 * 
	 * 递归
	 */

	private static InfoResultDto getSubCards(String username, String mainCard, String lastCardNo, String state, String hostType) {
		InfoResultDto ird = new InfoResultDto();
		String subCardUrl = resource.getString("zsh.get_vicecard_list");
		NameValuePair cardNo = new NameValuePair("cardMember.cardNo", mainCard);
		NameValuePair cardsType = new NameValuePair("cardsType", "-1");
		NameValuePair lastno = new NameValuePair("lastCardNo", lastCardNo);
		NameValuePair params[] = { cardNo, cardsType, lastno };
		String usersortname = CatchDataUtils.getUserStoreName(hostType, username);
		ClientSession session = StaticMap.clientMap.get(usersortname);
		ird = getCard(session.getClient(), subCardUrl, session.getCookies(), params, hostType, mainCard, username);
		// List<MainCardAndSubcardVo> list = ird.getSubCardList();
		// if (list != null) {
		// if (list.size() != 0) {
		// StaticValue.mainAndSubCards.addAll(list);
		// }
		// }
		state = ird.getState();
		if (!"".equals(state) && state != null) {
			if ("0".equals(state)) {
				getSubCards(username, mainCard, ird.getLastCardNo(), state, hostType);
			}
		}
		return ird;
	}

	/**
	 * 获取副卡信息
	 */
	protected static InfoResultDto getCard(HttpClient client, String url, String cookies, NameValuePair params[], String hostType, String parentNumber, String userName) {
		InfoResultDto ird = new InfoResultDto();
		PostMethod postMethod = new PostMethod(url);
		try {
			postMethod.addParameters(params);
			postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.143 Safari/537.36");
			postMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			postMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8");
			postMethod.setRequestHeader("Cache-Control", "max-age=0");
			postMethod.setRequestHeader("Connection", "keep-alive");
			postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			postMethod.setRequestHeader("Referer", resource.getString("zsh.get_data_ref"));
			postMethod.setRequestHeader("Cookie", cookies);
			postMethod.setRequestHeader("Host", CatchDataUtils.getHost(hostType));
			postMethod.setRequestHeader("Origin", CatchDataUtils.getHost(hostType));
			client.executeMethod(postMethod);
			String data = postMethod.getResponseBodyAsString();
			if (data.startsWith("<html>")) {
				logger.info("请求连接返回数据错误，404");
				return ird;
			}
			// 用此类构造字符串
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(data);
			if (node.get("msg") != null) {
				logger.info("抓取副卡返回错误信息，详细信息:"+node.get("msg").toString());
				return ird;
			}
			Iterator<JsonNode> iterator = node.get("list").getElements();
			String state = node.get("state").asText();
			String lastCardNo = node.get("lastCardNo").asText();
			for (; iterator.hasNext();) {
				MainCardAndSubcardVo mcas = new MainCardAndSubcardVo();
				JsonNode n = iterator.next();
				String cardNo = n.get("cardNo").asText();
				if (tempmain.get(cardNo) == null) {
					mcas.setAsn(cardNo);
					mcas.setDriverName(n.get("cardHolder").asText());
					mcas.setIsMaster("否");
					mcas.setCardStatus(n.get("cardStatus").asText());
					mcas.setParentNumber(parentNumber);
					mcas.setUserName(userName);
					mcas.setHostType(LoginCode.ZSH);
					DataProcessingQueue.getInstance().queue.put(mcas);
					logger.info("抓取到中石化副卡,主卡为:[" + parentNumber + "],副卡卡号:[" + cardNo + "]" + ";解析数据为:" + n.toString());
				}
			}
			ird.setState(state);
			ird.setLastCardNo(lastCardNo);
		} catch (HttpException e) {
			logger.error("抓取中石化副卡发生HttpException异常,", e);
		} catch (IOException e) {
			logger.error("抓取中石化副卡发生IOException异常,", e);
		} catch (InterruptedException e) {
			logger.error("抓取中石化副卡发生InterruptedException异常,", e);
		} finally {
			postMethod.releaseConnection();
		}
		return ird;
	}

	// <<<<<<<<<<<<<<结束处理抓取主副卡<<<<<<<<<<<<<<<<<<<<<

	/**
	 * 
	 * @Description:刷新用户在线功能，请求主卡列表
	 * @param 参数
	 * @return 返回类型
	 * @throws
	 */
	public static boolean updateUserStatus(String userName, String hostType) {
		boolean islogin = false;
		String mainCardUrl = resource.getString("zsh.main_card_list");
		String data = getPostRequst(userName, mainCardUrl, null, hostType);
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (StringUtils.isNotBlank(data)) {
				JsonNode node = mapper.readTree(data);
				String currentCard = node.get("cardNo").asText();
				logger.info("刷新用户在线状态，刷新的是主卡列表，用户当前主卡为:" + currentCard);
				islogin = true;
			}
		} catch (JsonProcessingException e) {
			logger.info("抓取主卡数据发生异常：" + e.getMessage());
		} catch (IOException e) {
			logger.info("抓取主卡数据发生异常：" + e.getMessage());
		}
		return islogin;
	}

	/**
	 * 发送获取数据请求
	 */
	protected static String getPostRequst(String username, String url, NameValuePair paramValue[], String hostType) {
		ClientSession clientSession = StaticMap.clientMap.get(username);
		HttpClient client = clientSession.getClient();
		String cookies = clientSession.getCookies();
		String result = "";
		if (clientSession.isLogin()) {
			PostMethod postMethod = new PostMethod(url);
			try {
				if (paramValue != null) {
					postMethod.addParameters(paramValue);
				}
				postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.143 Safari/537.36");
				postMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
				postMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8");
				postMethod.setRequestHeader("Cache-Control", "max-age=0");
				postMethod.setRequestHeader("Connection", "keep-alive");
				postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
				postMethod.setRequestHeader("Referer", resource.getString("zsh.get_data_ref"));
				postMethod.setRequestHeader("Cookie", cookies);
				postMethod.setRequestHeader("Host", CatchDataUtils.getHost(hostType));
				postMethod.setRequestHeader("Origin", CatchDataUtils.getHost(hostType));
				int statusCode = client.executeMethod(postMethod);
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("\n\nMethod failed: " + postMethod.getStatusLine() + "\n\n");
					return "";
				}
				result = postMethod.getResponseBodyAsString();
				Thread.sleep(2000);
			} catch (HttpException e) {
				logger.error("抓取中石化数据,http请求发生异常", e);
			} catch (IOException e) {
				logger.error("中石化，读取请求返回数据异常", e);
			} catch (InterruptedException e) {
				logger.error("线程休眠异常:", e);
			} catch (Exception e) {
				logger.error("抓取数据发生未知异常:", e);
			} finally {
				postMethod.releaseConnection();
			}
		}
		return result;
	}

	/**
	 * 发送获取数据请求
	 */
	protected static String catchMainBalancePostRequst(String username, String url, NameValuePair paramValue[], String hostType, String Referer) {
		ClientSession clientSession = StaticMap.clientMap.get(username);
		HttpClient httpClient = clientSession.getClient();
		String cookies = clientSession.getCookies();
		String result = "";
		if (clientSession.isLogin()) {
			PostMethod postMethod = new PostMethod(url);
			try {
				if (paramValue != null) {
					postMethod.addParameters(paramValue);
				}
				postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.143 Safari/537.36");
				postMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
				postMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8");
				postMethod.setRequestHeader("Cache-Control", "max-age=0");
				postMethod.setRequestHeader("Connection", "keep-alive");
				postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
				postMethod.setRequestHeader("Referer", Referer);
				postMethod.setRequestHeader("Cookie", cookies);
				postMethod.setRequestHeader("Host", CatchDataUtils.getHost(hostType));
				postMethod.setRequestHeader("Origin", CatchDataUtils.getHost(hostType));
				int statusCode = httpClient.executeMethod(postMethod);
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("\n\nMethod failed: " + postMethod.getStatusLine() + "\n\n");
					return "";
				}
				BufferedReader reader = new BufferedReader(new InputStreamReader(postMethod.getResponseBodyAsStream(), "GBK"));
				// BufferedReader reader = new BufferedReader(new
				// InputStreamReader));
				StringBuffer stringBuffer = new StringBuffer();
				String str = "";
				while ((str = reader.readLine()) != null) {
					stringBuffer.append(str);
				}
				result = stringBuffer.toString();
				Thread.sleep(2000);
			} catch (HttpException e) {
				logger.error("抓取中石化数据,http请求发生异常", e);
			} catch (IOException e) {
				logger.error("抓取中石化数据发生IOException异常", e);
			} catch (InterruptedException e) {
				logger.info("抓取中石化数据发生InterruptedException异常", e);
			} finally {
				postMethod.releaseConnection();
			}
		}
		return result;
	}
}
