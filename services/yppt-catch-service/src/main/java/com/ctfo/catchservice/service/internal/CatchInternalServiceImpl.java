package com.ctfo.catchservice.service.internal;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.beans.SimpleCode;
import com.ctfo.base.service.intf.ISimpleCodeManager;
import com.ctfo.catchservice.bean.AccountTradeVo;
import com.ctfo.catchservice.bean.CatchDataVO;
import com.ctfo.catchservice.bean.ClientSession;
import com.ctfo.catchservice.bean.LoginCode;
import com.ctfo.catchservice.bean.MainCardAndSubcardVo;
import com.ctfo.catchservice.bean.OilCardBalanceVo;
import com.ctfo.catchservice.bean.StaticMap;
import com.ctfo.catchservice.bean.TradeByConsumeVo;
import com.ctfo.catchservice.interfaces.external.ICatchService;
import com.ctfo.catchservice.interfaces.internal.ICatchInternalService;
import com.ctfo.catchservice.service.internal.utils.zsh.ZSHCatchDataUtils;
import com.ctfo.catchservice.service.internal.utils.zsh.ZSYCatchDataUtils;
import com.ctfo.catchservice.util.CatchDataUtils;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.soa.intf.IServiceFactory;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.beans.ICCardExampleExtended;
import com.ctfo.yppt.baseservice.card.beans.ICCardMain;
import com.ctfo.yppt.baseservice.card.beans.ICCardMainExampleExtended;
import com.ctfo.yppt.baseservice.card.intf.IICCardMainManager;
import com.ctfo.yppt.baseservice.card.intf.IICCardManager;
import com.ctfo.yppt.baseservice.system.beans.ICCardAccountConfig;
import com.ctfo.yppt.baseservice.system.beans.ICCardAccountConfigExampleExtended;
import com.ctfo.yppt.baseservice.system.intf.IAccountManager;
import com.ctfo.yppt.baseservice.trade.beans.ICCardMainTradeInfo;
import com.ctfo.yppt.baseservice.trade.beans.ICCardMainTradeInfoExampleExtended;
import com.ctfo.yppt.baseservice.trade.beans.ICCardMainTradeInfoExampleExtended.Criteria;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfo;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoExampleExtended;
import com.ctfo.yppt.baseservice.trade.intf.IICCardMainTradeInfoManager;
import com.ctfo.yppt.baseservice.trade.intf.IICCardTradeInfoManager;

/**
 * 抓取数据实现类
 * 
 * @author jichao
 */
@Service(value = "catchInternalService")
public class CatchInternalServiceImpl implements ICatchInternalService {
	private static final Log logger = LogFactory.getLog(CatchInternalServiceImpl.class);
	private IICCardManager iCCardManager;// 子卡管理类
	private IICCardMainManager iCCardMainManager;// 主卡管理类
	private IICCardMainTradeInfoManager ICCardMainTradeInfoManager;// 主卡交易记录
	private IICCardTradeInfoManager ICCardTradeInfoManager;// 子卡交易记录
	private ISimpleCodeManager iSimpleCodeManager;// 码表管理
	private IAccountManager accountManager;
	private static ResourceBundle resource_system = ResourceBundle.getBundle("system");// 获取资源文件
	@Resource
	private ICatchService catchService;

	public CatchInternalServiceImpl() {
		IServiceFactory factory = ServiceFactory.getFactory();
		accountManager = (IAccountManager) ServiceFactory.getFactory().getService(IAccountManager.class);
		iCCardMainManager = (IICCardMainManager) factory.getService(IICCardMainManager.class);
		iCCardManager = (IICCardManager) factory.getService(IICCardManager.class);
		ICCardMainTradeInfoManager = (IICCardMainTradeInfoManager) factory.getService(IICCardMainTradeInfoManager.class);
		ICCardTradeInfoManager = (IICCardTradeInfoManager) factory.getService(IICCardTradeInfoManager.class);
		iSimpleCodeManager = (ISimpleCodeManager) factory.getService(ISimpleCodeManager.class);
	}

	/*
	 * 定时抓取任务调用:
	 * 
	 * 1.获取已登陆用户,
	 */
	public void timimgTaskCatchData() {
		logger.info("定时抓取交易记录任务启动,开始抓取交易记录.");
		Map<String, ClientSession> accountmap = StaticMap.clientMap;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Iterator<String> iter = accountmap.keySet().iterator();
		try {
			while (iter.hasNext()) {
				String userName = iter.next();
				ClientSession clientSession = accountmap.get(userName);
				boolean islogin = clientSession.isLogin();
				String[] hostTypeAndUserName = userName.split("_");// 此处保存数据格式为:hosttype_username
				String hostType = hostTypeAndUserName[0];
				String accountName = hostTypeAndUserName[1];
				logger.info("抓取用户:" + userName + "当前用户登陆状态(登陆(true)抓取,未登陆状态(false)无法抓取):" + islogin);
				if (islogin) {
					ICCardAccountConfigExampleExtended accountexd = new ICCardAccountConfigExampleExtended();
					accountexd.createCriteria().andUsenameEqualTo(accountName).andAccounttypeEqualTo(CatchDataUtils.getHost(hostType));
					List<ICCardAccountConfig> accountlist = accountManager.getList(accountexd);
					String mainStartDate = "";
					String mainEndDate = sf.format(new Date());
					String subStartDate = "";
					String subEndDate = sf.format(new Date());
					if (CollectionUtils.isNotEmpty(accountlist)) {
						ICCardAccountConfig account = accountlist.get(0);
						long maintrade = account.getMainCardTrade();// 获取用户最后同步主卡交易记录时间
						long subtrade = account.getSubCardTradeTime();// 获取用户最后同步副卡交易记录时间
						if (maintrade != 0L) {
							mainStartDate = sf.format(new Date(maintrade));
						} else {
							mainStartDate = sf.format(new Date());
						}
						if (subtrade != 0L) {
							subStartDate = sf.format(new Date(subtrade));
						} else {
							subStartDate = sf.format(new Date());
						}
						logger.info("定时任务抓取交易记录,主卡交易记录开始时间:" + mainStartDate + ";结束时间:" + mainEndDate + ";副卡交易记录开时间:" + subStartDate + ";结束时间:" + subEndDate);
						catchService.getMainCardAndSubcard(hostType, "", "", userName);
						catchService.getOilCardBalance(hostType, "", userName);
						catchService.getAccountTrade(hostType, "", mainStartDate, mainEndDate, "", userName);
						catchService.getTradeByConsume(hostType, "", subStartDate, subEndDate, userName);
					}
				}
			}
		} catch (Exception e) {
			logger.info("定时任务启动抓取交易记录出现异常:", e);
		}
		logger.info("定时抓取交易记录任务启动,结束抓取交易记录.");
	}

	// 刷新用户在线状态
	@Override
	public void updateAccountStatus() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
		try {
			ICCardAccountConfigExampleExtended accountexd = new ICCardAccountConfigExampleExtended();
			accountexd.createCriteria();
			List<ICCardAccountConfig> accountlist = accountManager.getList(accountexd);
			Map<String, ClientSession> cacheUser = StaticMap.clientMap;
			if (MapUtils.isNotEmpty(cacheUser)) {
				Map<String, ICCardAccountConfig> needSendMessageUser = new HashMap<String, ICCardAccountConfig>();// 需要发送短信的用户
				if (CollectionUtils.isNotEmpty(accountlist)) {
					for (ICCardAccountConfig accountconfig : accountlist) {
						String userName = accountconfig.getUsename();
						String hostType = accountconfig.getAccounttype();
						String userType = LoginCode.ZSH;
						if ("CARD-TYPE-01".equals(hostType)) {
							userType = LoginCode.ZSY;
						}
						String usersortname = CatchDataUtils.getUserStoreName(userType, userName);
						ClientSession clientSession = cacheUser.get(usersortname);
						if (clientSession != null) {
							boolean res = false;
							if (LoginCode.ZSY.equals(userType)) {// 如果是中石油
								res = ZSYCatchDataUtils.updateUserStatus(usersortname, userType);
								if (!res) {
									needSendMessageUser.put(usersortname, accountconfig);
								}
							} else if (LoginCode.ZSH.equals(userType)) {// 如果是中石化
								res = ZSHCatchDataUtils.updateUserStatus(usersortname, userType);
								if (!res) {
									needSendMessageUser.put(usersortname, accountconfig);
								}
							}
							clientSession.isLogin(res);
							StaticMap.clientMap.put(usersortname, clientSession);
						}
					}
					long lastSendMessage = StaticMap.getLastSendMessage;// 最后发送短信时间
					long currentTime = System.currentTimeMillis();
					long sendMessageInterval = Long.parseLong(resource_system.getString("sendMessageInterval"));
					// 刷新完成状态后,检查状态,检查需要发送短信的
					if (MapUtils.isNotEmpty(needSendMessageUser)) {
						Iterator<String> userIterator = needSendMessageUser.keySet().iterator();
						StringBuffer mobilelist = new StringBuffer();
						int i = 0;
						List<String> messageParamlist = new ArrayList<String>();
						while (userIterator.hasNext()) {
							String key = userIterator.next();
							ICCardAccountConfig accountconfig = needSendMessageUser.get(key);
							String webName = "中石化";
							if ("CARD-TYPE-01".equals(accountconfig.getAccounttype())) {
								webName = "中石油";
							}
							if (currentTime - lastSendMessage > sendMessageInterval) {
								mobilelist.append(accountconfig.getMobile());
								if (i > 0) {
									mobilelist.append(",");
								}
								messageParamlist.add(accountconfig.getUsename());
								messageParamlist.add(webName);
								messageParamlist.add(sf.format(new Date()));
								i++;
								logger.info("需要发送短信用户:" + key + "电话号码为:" + accountconfig.getMobile());
							}
						}
						if (CollectionUtils.isNotEmpty(messageParamlist)) {
							logger.info("调用远程发送短信接口");
							boolean sendmes = accountManager.sendMessage("CATCH_DATA_MESSAGE_OFFLINE", mobilelist.toString(), messageParamlist);
							logger.info("结束调用发送短信接口,返回结果为:" + sendmes);
							StaticMap.getLastSendMessage = System.currentTimeMillis();
							// StaticMap.userLastSendMessage.put(usersortname,
							// getLastSendMessage);
							needSendMessageUser.clear();
							messageParamlist.clear();
						}
					}
				}
			} else {
				logger.info("刷新用户在线状态功能已执行，暂未登陆任何用户.");
			}
		} catch (Exception e) {
			logger.info("刷新用户在线状态,发生异常:", e);
		}
	}

	// 查询余额
	@Override
	public void getOilCardBalance(String hostType, String cardNo, String userName) throws Exception {
		initMainCardByAccount(userName);
		logger.info("请求刷新卡余额,网站:" + hostType + ";卡号:" + cardNo);
		if (hostType.equals(LoginCode.ZSY)) {
			ZSYCatchDataUtils.catchMainBalance(hostType, userName);
			updateMainBalance(hostType);
			ZSYCatchDataUtils.catchCardBalance(hostType, userName);
			StaticMap.cardMainBalance.clear();
		}
		if (hostType.equals(LoginCode.ZSH)) {
			ZSHCatchDataUtils.catchMainBalance(hostType, userName);
			updateMainBalance(hostType);
			ZSHCatchDataUtils.catchCardBalance(hostType, userName);
			StaticMap.cardMainBalance.clear();
		}
		logger.info("结束处理刷新卡余额,网站:" + hostType + ";卡号:" + cardNo);
	}

	/**
	 * 
	 * @Description: 更新余额
	 * @param 参数
	 * @return 返回类型
	 * @throws
	 */
	@Override
	public void updateBalance(CatchDataVO catchdatavo) {
		OilCardBalanceVo oilCardBalance = (OilCardBalanceVo) catchdatavo;
		String hostType = oilCardBalance.getHostType();
		logger.info("开始处理刷新数据库卡余额,网站:" + hostType);
		try {
			String cardNo = oilCardBalance.getAsn();
			String cardType = "";
			if (cardNo.startsWith("9") || cardNo.startsWith("-")) {
				cardType = "CARD-TYPE-01";
			} else {
				cardType = "CARD-TYPE-02";
			}
			// 更新卡余额，首先检查该卡是否存在
			logger.info("调用远程baseservice接口,根据卡类型和卡号查询卡是否存在,卡号:" + cardNo);
			ICCard iccard = iCCardManager.getCardByCardNoAndType(cardType, cardNo);
			if (iccard != null) {
				BigDecimal balance = new BigDecimal(oilCardBalance.getBalance());// 备付金余额
				BigDecimal kaBalance = new BigDecimal(oilCardBalance.getCardBalance());// 卡余额
				if (hostType.equals(LoginCode.ZSY)) {
					balance = balance.multiply(new BigDecimal(100));
					kaBalance = kaBalance.multiply(new BigDecimal(100));
				}
				logger.info("调用远程baseservice接口刷新卡余额,卡类型:" + cardType + ";卡号:" + cardNo + ";卡余额:" + balance.add(kaBalance));
				iCCardManager.updateICCardBalance(cardType, cardNo, balance.add(kaBalance));
			}
		} catch (BusinessException e) {
			logger.error("调用远程baseservice刷新卡余额异常:" + e.getMessage());
		}
	}

	// 刷新主卡余额
	private void updateMainBalance(String hostType) {
		logger.info("开始处理刷新主卡余额,网站:" + hostType);
		String cardType = "";
		if (LoginCode.ZSH.equals(hostType)) {
			cardType = "CARD-TYPE-02";
		} else {
			cardType = "CARD-TYPE-01";
		}
		List<ICCardMain> cardMainBalance = StaticMap.cardMainBalance;
		if (CollectionUtils.isNotEmpty(cardMainBalance)) {
			for (int i = 0; i < cardMainBalance.size(); i++) {
				try {
					// 更新卡余额，首先检查该卡是否存在
					String cardNo = cardMainBalance.get(i).getCardNumber();
					BigDecimal balance = cardMainBalance.get(i).getBalance();
					logger.info("调用远程baseservice接口刷新主卡余额,卡类型:" + cardType + ";卡号:" + cardNo + ";卡余额:" + balance);
					iCCardMainManager.updateICCardMainBalance(cardType, cardNo, balance);
				} catch (BusinessException e) {
					logger.error("调用远程baseservice刷新主卡余额异常:" + e.getMessage());
				}
			}
		}
	}

	// 账户交易查询
	@Override
	public void getAccountTrade(String hostType, String driverCardNo, String startDate, String endDate, String tradeType, String userName) throws IOException {
		initMainCardByAccount(userName);
		logger.info("请求账户交易数据,网站:" + hostType + ";卡号:" + driverCardNo + ";开始日期:" + startDate + ";结束日期:" + endDate + ";交易类型:" + tradeType);
		if (hostType.equals(LoginCode.ZSY)) {
			ZSYCatchDataUtils.catchAccountTrad(hostType, userName, startDate);
		}
		if (hostType.equals(LoginCode.ZSH)) {
			// 中石化对应数据为：预分配及其充值数据
			ZSHCatchDataUtils.catchAllotData(hostType, startDate, endDate, userName);// 预分配
			ZSHCatchDataUtils.catchRechargeData(hostType, startDate, endDate, userName);// 充值
		}
	}

	// 添加账户交易信息 主卡数据
	@Override
	public synchronized void addAccountTradeData(CatchDataVO catchdatavo) {
		logger.info("添加账户交易数据,网站:" + catchdatavo.getHostType());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ICCardMainTradeInfo icmt = new ICCardMainTradeInfo();
		String id = UUID.randomUUID().toString();
		try {
			AccountTradeVo tbc = (AccountTradeVo) catchdatavo;
			String cardNo = tbc.getAsn();
			icmt.setCardNo(cardNo);
			icmt.setCardId(cardNo);
			icmt.setId(id);
			icmt.setCreateTime(System.currentTimeMillis());
			icmt.setTradeAdress(tbc.getOrg());
			BigDecimal cardbalance = new BigDecimal(0);
			if (StringUtils.isNotBlank(tbc.getLoyaltyAndBalance())) {
				cardbalance = new BigDecimal(tbc.getLoyaltyAndBalance());
			}
			BigDecimal tradeMoney = new BigDecimal(tbc.getAmount());
			String cardType = "";
			if (cardNo.startsWith("9") || cardNo.startsWith("-")) {
				tradeMoney = tradeMoney.multiply(new BigDecimal(100));
				cardbalance = cardbalance.multiply(new BigDecimal(100));
				String tradeName = tbc.getTradeType();
				String type = "IC-OTHER";
				if ("支票收款".equals(tradeName)) {
					type = "CHECK-COLLECTION";
				} else if ("银行卡收款".equals(tradeName)) {
					type = "BANK-CARD-RECE";
				} else if ("现金收款".equals(tradeName)) {
					type = "CASH-RECEIPTS";
				} else if ("圈存(出)".equals(tradeName)) {
					type = "IC-TRANSFER-O";
				} else if ("圈存(进)".equals(tradeName)) {
					type = "IC-TRANSFER-I";
				} else if ("单位分配(进)".equals(tradeName)) {
					type = "IC-DISTRIBUTION-I";
				} else if ("单位分配(出)".equals(tradeName)) {
					type = "IC-DISTRIBUTION-O";
				} else if ("单位汇总(出)".equals(tradeName)) {
					type = "UNIT-SUMMARY-O";
				} else if ("单位汇总(进)".equals(tradeName)) {
					type = "UNIT-SUMMARY-I";
				} else if ("IC卡消费".equals(tradeName)) {
					type = "ICCARD-CONSUME";
				}
				icmt.setTradeType(type);
				icmt.setTradeName(tradeName);
				String tradeStatus = tbc.getTradeStatus();
				String status = "";
				if ("撤销".equals(tradeStatus.trim())) {
					status = "IC-TRADE-REVOKE";
				} else if ("正常".equals(tradeStatus)) {
					status = "IC-TRADE-SUCCESS";
				} else {
					status = "IC-TRADE-OTHER";
				}
				icmt.setTradeState(status);
				cardType = "CARD-TYPE-01";
				icmt.setMerchantType(cardType);

			} else {
				cardType = "CARD-TYPE-02";
				icmt.setTradeType(tbc.getTradeType());
				icmt.setTradeName(tbc.getTradeName());
				icmt.setTradeState("IC-TRADE-SUCCESS");
			}
			// ------检查主卡是否存在
			ICCardMainExampleExtended cardmainexd = new ICCardMainExampleExtended();
			cardmainexd.createCriteria().andCardNumberEqualTo(cardNo).andCardTypeEqualTo(cardType);
			List<ICCardMain> mainlist = iCCardMainManager.getList(cardmainexd);
			String opencardofficecode = "";
			String ownOrg = "";
			// 检查副卡是否存在
			List<ICCard> cardlist = new ArrayList<ICCard>();// 先去检查主卡表是否为空才会检查副卡表查询卡是否存在
			if (CollectionUtils.isEmpty(mainlist)) {
				ICCardExampleExtended cardexd = new ICCardExampleExtended();
				cardexd.createCriteria().andCardNumberEqualTo(cardNo).andCardTypeEqualTo(cardType);
				cardlist = iCCardManager.getList(cardexd);
				if (CollectionUtils.isNotEmpty(cardlist)) {
					opencardofficecode = cardlist.get(0).getOpencardofficeid();
					ownOrg = cardlist.get(0).getOwnOrg();
				}
			} else {
				if (CollectionUtils.isNotEmpty(mainlist)) {
					opencardofficecode = mainlist.get(0).getOpencardofficecode();
					ownOrg = mainlist.get(0).getOwnOrg();
				}
			}
			icmt.setOpenCardOfficeCode(opencardofficecode);
			icmt.setOwnOrg(ownOrg);
			icmt.setCardType(cardType);
			icmt.setTradeMoney(tradeMoney);
			icmt.setCardBalance(cardbalance);
			long tradetime = sf.parse(tbc.getTime()).getTime();
			icmt.setTradeTime(tradetime);
			// ----------------------查询库中是否存在该记录，根据卡号，卡类型，交易时间 商户类型 进行判断
			// 不存在添加 存在忽略
			if (CollectionUtils.isNotEmpty(mainlist) || CollectionUtils.isNotEmpty(cardlist)) {
				String cardnum = tbc.getAsn();
				ICCardMainTradeInfoExampleExtended icmtie = new ICCardMainTradeInfoExampleExtended();
				Criteria criteria = icmtie.createCriteria();
				criteria.andCardNoEqualTo(cardnum).andCardTypeEqualTo(cardType).andTradeTimeEqualTo(tradetime).andTradeTypeEqualTo(icmt.getTradeType());
				if ("-".equals(cardnum)) {
					criteria.andCardBalanceEqualTo(cardbalance).andTradeMoneyEqualTo(tradeMoney);
				}
				List<ICCardMainTradeInfo> tempCardTradeInfo = ICCardMainTradeInfoManager.getList(icmtie);
				if (CollectionUtils.isEmpty(tempCardTradeInfo)) {
					try {
						ICCardMainTradeInfoManager.add(icmt);
						logger.info("卡号:[" + tbc.getAsn() + "]交易记录添加成功,卡类型:" + cardType + ";交易时间:" + tbc.getTime() + "交易类型为:" + icmt.getTradeName() + "交易状态:" + icmt.getTradeState() + "["
								+ tbc.getTradeStatus() + "]");
					} catch (Exception e) {
						logger.error("数据抓取主卡交易数据入库异常：" + e.getMessage());
					}
				} else {
					logger.info("卡号:[" + tbc.getAsn() + "]交易记录已存在,卡类型:" + cardType + ";交易时间:" + tbc.getTime() + "交易类型为:" + icmt.getTradeName());
				}
			} else {
				logger.info("添加账户交易记录,卡号不存在,不存在添加操作,卡号:" + tbc.getAsn() + ";卡类型:" + cardType);
			}
		} catch (Exception e) {
			logger.error("添加主卡交易记录发生异常:", e);
		}

	}

	@Override
	public void getTradeByConsume(String hostType, String driverCardNo, String startDate, String endDate, String userName) throws Exception {
		initCardByAccount(userName);
		logger.info("请求消费交易数据,网站:" + hostType + ";卡号:" + driverCardNo + ";开始日期:" + startDate + ";结束日期:" + endDate);
		if (hostType.equals(LoginCode.ZSY)) {
			// 参数
			ZSYCatchDataUtils.catchTradeByConsume(hostType, userName, startDate);
		}
		if (hostType.equals(LoginCode.ZSH)) {
			ZSHCatchDataUtils.catchTradeData(hostType, startDate, endDate, userName);
		}
		logger.info("结束抓取消费交易数据,网站:" + hostType + ";卡号:" + driverCardNo + ";开始日期:" + startDate + ";结束日期:" + endDate);
	}

	// 添加消费记录数据 子卡数据
	public synchronized void addTradeByConsumeData(CatchDataVO catchdatavo) {
		logger.info("开始入库消费记录数据,网站:" + catchdatavo.getHostType());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ICCardTradeInfo icti = new ICCardTradeInfo();
		try {
			String cardType = "";
			TradeByConsumeVo tbc = (TradeByConsumeVo) catchdatavo;
			String cardNum = tbc.getCardAsn();
			String id = UUID.randomUUID().toString();
			cardType = "CARD-TYPE-01";
			icti.setCardType(cardType);
			icti.setCardNo(cardNum);
			icti.setCardId(cardNum);
			icti.setId(id);
			icti.setCreateTime(System.currentTimeMillis());
			icti.setProductName(tbc.getGiftName());
			BigDecimal pronum = new BigDecimal(tbc.getVolumn());
			long tradetime = sf.parse(tbc.getOccurTime()).getTime();
			icti.setTradeTime(tradetime);
			icti.setMerchantType(cardType);
			icti.setTradeAdress(tbc.getOrgName());
			BigDecimal tradeMoney = new BigDecimal(tbc.getAmount());
			BigDecimal cardbalance = new BigDecimal(tbc.getBalance());
			if (cardNum.startsWith("9") || cardNum.equals("-")) {
				cardbalance = cardbalance.multiply(new BigDecimal(100));
				tradeMoney = tradeMoney.multiply(new BigDecimal(100));
				String tradeName = tbc.getTradeType();
				String type = "IC-OTHER";
				if ("圈存".equals(tradeName)) {
					type = "IC-TRANSFER";
				} else if ("加油".equals(tradeName)) {
					type = "IC-GAS";
				} else if ("充值".equals(tradeName)) {
					type = "IC-RECHARGE";
				} else if ("分配".equals(tradeName)) {
					type = "IC-DISTRIBUTION";
				} else if ("IC卡消费".equals(tradeName)) {
					if ("--".equals(tbc.getGiftName().trim())) {
						type = "IC-OTHER";
					} else {
						type = "IC-GAS";
					}
				}
				cardType = "CARD-TYPE-01";
				pronum = pronum.multiply(new BigDecimal(100));
				icti.setTradeType(type);
				icti.setTradeName(tradeName);

				if (pronum.intValue() != 0 && tradeMoney.intValue() != 0) {
					icti.setProductPrice(tradeMoney.divide(pronum, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
				}
			} else {
				cardType = "CARD-TYPE-02";
				icti.setTradeType(tbc.getTradeType());
				icti.setTradeName(tbc.getTradeName());
				icti.setProductPrice(new BigDecimal(tbc.getProductPrice()));
			}
			icti.setCardType(cardType);
			icti.setTradeState("IC-TRADE-SUCCESS");
			icti.setProductNum(pronum.longValue());
			icti.setCardBalance(cardbalance);
			icti.setTradeMoney(tradeMoney);
			// 查询卡数据---根据卡号卡类型
			ICCardExampleExtended icee = new ICCardExampleExtended();
			icee.createCriteria().andCardTypeEqualTo(cardType).andCardNumberEqualTo(cardNum);
			List<ICCard> icCardlist = iCCardManager.getList(icee);
			if (CollectionUtils.isNotEmpty(icCardlist)) {

				ICCard iccard = icCardlist.get(0);
				icti.setOpenCardOfficeCode(iccard.getOpencardofficeid());
				icti.setOwnOrg(iccard.getOwnOrg());
				icti.setMainCardId(iccard.getParentId());
				icti.setMainCardNum(iccard.getParentCardNumber());
				icti.setMainCardNum(iccard.getParentCardNumber());
				icti.setUserRegPhone(iccard.getUserRegPhone());
				if (StringUtils.isNotBlank(iccard.getUserId())) {// 如果副卡没有绑定用户，则不添加交易记录数据
					icti.setUserId(iccard.getUserId());
					icti.setUserName(iccard.getUserName());
					// -------查询库中是否存在该交易数据，根据卡号及其交易时间 卡类型 商户类型
					ICCardTradeInfoExampleExtended icte = new ICCardTradeInfoExampleExtended();
					icte.createCriteria().andCardNoEqualTo(cardNum).andTradeTimeEqualTo(tradetime).andCardTypeEqualTo(cardType).andTradeTypeEqualTo(icti.getTradeType());
					List<ICCardTradeInfo> icCardTradeInfoList = ICCardTradeInfoManager.getList(icte);
					if (CollectionUtils.isEmpty(icCardTradeInfoList)) {
						try {
							ICCardTradeInfoManager.add(icti);
							logger.info("消费记录添加成功,卡号:" + cardNum + ";卡类型:" + cardType);
						} catch (Exception e) {
							logger.error("数据抓取子卡交易数据入库异常:" + e.getMessage());
						}
					} else {
						logger.info("消费记录已存在,不做添加或更新操作,卡号:" + cardNum + ";卡类型:" + cardType + ";交易时间:" + tbc.getOccurTime() + "卡类型:" + icti.getTradeName());
					}
				} else {
					logger.info("消费记录未绑定用户,不进行入库操作,卡号为:" + cardNum + ";卡类型:" + cardType + ";交易时间:" + tbc.getOccurTime() + "卡类型:" + icti.getTradeName());
				}
			} else {
				logger.info("消费记录所属卡不存在,不进行入库操作,卡号:" + cardNum);
			}
		} catch (Exception e) {
			logger.error("添加消费交易记录数据发生异常:" + e.getMessage());
		}
	}

	@Override
	public void getMainCardAndSubcard(String hostType, String driverCardNo, String cardStatus, String username) throws Exception {
		logger.info("请求抓取主卡子卡数据,网站:" + hostType + ";卡号" + driverCardNo);
		if (hostType.equals(LoginCode.ZSY)) {
			// 参数
			ZSYCatchDataUtils.catchMainCardAndSubcard(hostType, username);
		}
		if (hostType.equals(LoginCode.ZSH)) {
			ZSHCatchDataUtils.getMainAndSubCard(hostType, username);
		}
		logger.info("结束抓取主卡子卡数据,网站:" + hostType);
	}

	@Override
	public void addMainCardAndSubcardByHostType(CatchDataVO catchdatavo) throws Exception {
		String hostType = catchdatavo.getHostType();
		String userName = catchdatavo.getUserName();
		logger.info("入库主卡子卡数据,卡数量: 网站:" + hostType);
		String cardAreaCode = "";// 发卡地区编码
		String ownOrg = "";// 所属组织
		String opencardofficecode = "";// 发卡机构
		String cardType = "";
		String usenamer = CatchDataUtils.getUserStoreName(hostType, userName);
		ClientSession clientSession = StaticMap.clientMap.get(usenamer);
		if (clientSession != null) {
			cardType = clientSession.getCardType();
			opencardofficecode = clientSession.getCardAreaCode();
			ownOrg = clientSession.getOwnOrg();
			List<SimpleCode> codelist = iSimpleCodeManager.getSimpleCodeByType(cardType);
			cardAreaCode = codelist.get(0).getDescription();
		}
		MainCardAndSubcardVo m = (MainCardAndSubcardVo) catchdatavo;
		String cardNumber = m.getAsn();
		// String state = m.getCardStatus();
		String isMaster = m.getIsMaster();
		ICCardMain icCardMain = new ICCardMain();
		ICCard icCard = new ICCard();
		// 存入主卡表
		if ("是".equals(isMaster)) {
			icCardMain.setId(cardNumber);
			icCardMain.setCardNumber(cardNumber);
			icCardMain.setCtrdituser("default");
			icCardMain.setCardType(cardType);
			icCardMain.setState("T");
			icCardMain.setOpencardofficecode(cardType);
			icCardMain.setCreatedTime(System.currentTimeMillis());
			icCardMain.setOpencardofficecode(opencardofficecode);
			icCardMain.setCardAreaCode(cardAreaCode);
			icCardMain.setOwnOrg(ownOrg);
			icCardMain.setAccountId(userName);
			icCardMain.setCreatedTime(System.currentTimeMillis());
			icCardMain.setModifiedTime(System.currentTimeMillis());
			ICCardMainExampleExtended icme = new ICCardMainExampleExtended();
			icme.createCriteria().andCardNumberEqualTo(cardNumber).andCardTypeEqualTo(cardType);
			List<ICCardMain> maincardList = iCCardMainManager.getList(icme);
			if (CollectionUtils.isEmpty(maincardList)) {
				try {
					iCCardMainManager.add(icCardMain);
					if (LoginCode.ZSY.equals(hostType)) {
						StaticMap.zsyUserMainCard.put(usenamer, cardNumber);
					}
					logger.info("主卡卡号：" + cardNumber + "添加成功");
				} catch (Exception e) {
					logger.error("主卡入库异常：" + e.getMessage());
				}
			} else {
				logger.info("主卡卡号" + cardNumber + "已存在");
			}
		} else {
			if (LoginCode.ZSY.equals(hostType)) {
				icCard.setCardNumber(cardNumber);
				icCard.setParentId(StaticMap.zsyUserMainCard.get(usenamer));// 主卡ID
				icCard.setParentCardNumber(StaticMap.zsyUserMainCard.get(usenamer));
			} else {
				icCard.setCardNumber(cardNumber);
				icCard.setParentId(m.getParentNumber());// 主卡ID
				icCard.setParentCardNumber(m.getParentNumber());
			}
			// 存入子卡表
			icCard.setId(cardNumber);
			icCard.setCtrdituser("default");
			icCard.setState("T");
			icCard.setCardType(cardType);
			icCard.setCreatedTime(System.currentTimeMillis());
			icCard.setCardAreaCode(cardAreaCode);
			icCard.setOwnOrg(ownOrg);
			icCard.setOpencardofficeid(opencardofficecode);
			icCard.setMessageOrNot("1");// 默认发送短信
			icCard.setDataSource("2");// 数据来源 后台
			ICCard tempIcCard = iCCardManager.getCardByCardNoAndType(cardType, cardNumber);
			if (tempIcCard == null) {
				try {
					iCCardManager.add(icCard);
					logger.info("子卡卡号[" + cardNumber + "]添加成功");
				} catch (Exception e) {
					logger.error("数据抓取子卡入库异常：" + e.getMessage());
				}
			} else {
				logger.info("子卡卡号[" + cardNumber + "]已存在");
			}
		}
	}

	private void initMainCardByAccount(String userName) {

		Map<String, ICCardMain> maincard = StaticMap.getlocalMainCard.get(userName);

		if (MapUtils.isEmpty(maincard)) {
			maincard = new HashMap<String, ICCardMain>();
		}
		try {
			ICCardMainExampleExtended maincardexd = new ICCardMainExampleExtended();
			maincardexd.createCriteria().andAccountIdEqualTo(userName);
			List<ICCardMain> mainlist = iCCardMainManager.getList(maincardexd);
			if (CollectionUtils.isNotEmpty(mainlist)) {

				for (int i = 0; i < mainlist.size(); i++) {
					ICCardMain temp = mainlist.get(i);
					maincard.put(temp.getCardNumber(), temp);
				}
				StaticMap.getlocalMainCard.put(userName, maincard);
			}
		} catch (Exception e) {
			logger.error("初始化用户名[" + userName + "]所属主卡出现异常" + e);
		}
	}

	@SuppressWarnings("rawtypes")
	private void initCardByAccount(String userName) {

		initMainCardByAccount(userName);// 初始化主卡
		Map<String, ICCardMain> maincard = StaticMap.getlocalMainCard.get(userName);// 根据用户名获取主卡信息
		Iterator<String> iteratorMain = maincard.keySet().iterator();
		try {
			while (iteratorMain.hasNext()) {
				String mainnum = iteratorMain.next();// 主卡卡号
				List<ICCard> cardlist = StaticMap.getLocalSubCard.get(mainnum);// 根据主卡卡号得到所属副卡,如果为空,new空对象
				if (CollectionUtils.isEmpty(cardlist)) {
					cardlist = new ArrayList<ICCard>();
				}
				ICCardMain iccardmain = maincard.get(mainnum);
				String sql = "select *  from tb_ic_card where parent_id='" + iccardmain.getId() + "'";
				List<Map> cards = iCCardManager.queryBySQL(sql);
				for (int i = 0; i < cards.size(); i++) {
					Map temp = cards.get(i);
					ICCard iccard = new ICCard();
					String card_number = String.valueOf(temp.get("CARD_NUMBER"));
					iccard.setCardNumber(card_number);
					iccard.setUserId(String.valueOf(temp.get("user_id")));
					iccard.setParentCardNumber(String.valueOf(temp.get("PARENT_CARD_NUMBER")));
					cardlist.add(iccard);
				}
				StaticMap.getLocalSubCard.put(mainnum, cardlist);// 存入该主卡下所有副卡
			}
		} catch (Exception e) {
			logger.error("初始化[" + userName + "]用户下主卡下所有副卡出现异常" + e);
		}
	}
}
