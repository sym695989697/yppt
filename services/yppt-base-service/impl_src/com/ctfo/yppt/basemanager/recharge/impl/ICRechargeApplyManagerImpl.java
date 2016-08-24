package com.ctfo.yppt.basemanager.recharge.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.mamager.impl.MessageUtil;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.JsonSupport;
import com.ctfo.common.utils.ResourceBundleUtil;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.file.boss.IMongoService;
import com.ctfo.gatewayservice.interfaces.bean.YpptGatewayException;
import com.ctfo.gatewayservice.interfaces.bean.pay.Account;
import com.ctfo.gatewayservice.interfaces.bean.pay.CreateAccountParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.DeductMoneyParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.DeductMoneySureParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.DeductMoneySureResponse;
import com.ctfo.gatewayservice.interfaces.bean.pay.FreezeBalanceParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.GetCashierDeductUrlParams;
import com.ctfo.gatewayservice.interfaces.service.ICreditService;
import com.ctfo.gatewayservice.interfaces.service.IPayService;
import com.ctfo.util.DateUtil;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.beans.ICCardCreditAccountIO;
import com.ctfo.yppt.baseservice.card.beans.ICCardExampleExtended;
import com.ctfo.yppt.baseservice.recharge.beans.ICCardCreditOrder;
import com.ctfo.yppt.baseservice.recharge.beans.ICCardPaymentOrder;
import com.ctfo.yppt.baseservice.recharge.beans.ICCardPaymentOrderExampleExtended;
import com.ctfo.yppt.baseservice.recharge.beans.ICCardRechangeProcessLog;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApply;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyDetail;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyDetailExampleExtended;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyExampleExtended;
import com.ctfo.yppt.baseservice.recharge.cons.ICCardRechargeCons;
import com.ctfo.yppt.baseservice.recharge.intf.IICRechargeApplyManager;
import com.ctfo.yppt.baseservice.system.beans.UserStat;
import com.ctfo.yppt.baseservice.system.beans.UserStatExampleExtended;
import com.ctfo.yppt.bean.ICCardCreditOrderMongoDBBean;
import com.ctfo.yppt.bean.ICCardRechageApplyExtend;
import com.ctfo.yppt.bean.ICCardRechageApplyMetaBean;
import com.ctfo.yppt.bean.ICPaymentOrderMongoDBBean;
import com.ctfo.yppt.bean.ICardRechageMetaBean;
import com.ctfo.yppt.bean.RechargeApplyMongoDBBean;
import com.ctfo.yppt.bean.RechargeDetailMongoDBbean;
import com.mongodb.WriteConcern;

@Service("ICRechargeApplyManagerImpl")
public class ICRechargeApplyManagerImpl extends
		GenericManagerImpl<ICRechargeApply, ICRechargeApplyExampleExtended>
		implements IICRechargeApplyManager {

	private static Log logger = LogFactory
			.getLog(ICRechargeApplyManagerImpl.class);

	private ICreditService creditService;
	private IPayService payService;
	private IMongoService mongoService;

	private static BigDecimal MONEY_VALUE = new BigDecimal(100);

	public ICRechargeApplyManagerImpl() throws BusinessException {
		try {
			creditService = (ICreditService) ServiceFactory.getFactory()
					.getService(ICreditService.class);
			payService = (IPayService) ServiceFactory.getFactory().getService(
					IPayService.class);
			mongoService = (IMongoService) ServiceFactory.getFactory()
					.getService(IMongoService.class);
		} catch (Exception e) {
			logger.error("初始化远程接口发生异常！", e);
			throw new BusinessException("初始化远程接口发生异常！", e);
		}

	}

	@Override
	public Long changeUselessRechargeApplyTask(String exptime)
			throws BusinessException {
		logger.info("================>开始  取消无效订单<==============");
		Long tato = 0l;
		try {
			logger.info("系统当前时间为： " + DateUtil.getDateStr(DateUtil.DEFAULT_FORMATSTR, new Date()) 
					+ "   将查询超时时间为:" + exptime + "毫秒之前的订单!");
			ICRechargeApplyExampleExtended exampleExtended = new ICRechargeApplyExampleExtended();
			exampleExtended.createCriteria()
					.andStateEqualTo(
							ICCardRechargeCons.RECHARGE_APPLY_STATE_PAY_WAITING)
					.andApplyTimeBetween(startTime(), endTime());

			exampleExtended.setOrderByClause(ICRechargeApply.fieldApplyTime()
					+ " desc");
			List<ICRechargeApply> applys = idao.getModels(exampleExtended);

			long currentTime = System.currentTimeMillis();
			long exptimeL = Long.valueOf(exptime);
			logger.info("查询出的充值订单数量为： " + applys.size());
			for (ICRechargeApply icRechargeApply : applys) {
				
				if (currentTime - icRechargeApply.getApplyTime() > exptimeL) {
					logger.info("订单编号为： " + icRechargeApply.getOrderNo() + "支付已经超时，将取消订单");
					icRechargeApply
							.setState(ICCardRechargeCons.RECHARGE_APPLY_STATE_PAY_FAIL);
					icRechargeApply.setRemark("支付超时!,系统定时任务自动执行,修改订单状态!");
					icRechargeApply.setApprovalTime(currentTime);
					int num = idao.updateModelPart(icRechargeApply);
					if (StringUtils.isEmpty(icRechargeApply.getUserId())) {
						logger.info("================》userId不能为空！《================");
						throw new BusinessException("userId不能为空");
					}
					// 解冻旺金币
					if (icRechargeApply.getCreditNum() != null
							&& BigDecimal.ZERO.compareTo(icRechargeApply
									.getCreditNum()) < 0) {
						logger.info("订单编号为： " + icRechargeApply.getOrderNo() + "支付已经超时，将取消订单！进行解冻旺金币！解冻数量为:" + icRechargeApply.getCreditNum());
						creditService.unfreeze(icRechargeApply.getUserId(),
								icRechargeApply.getCreditNum().longValue(),
								icRechargeApply.getOrderNo());
					}
					tato = tato + Long.valueOf(num);
				}
			}
			logger.info("================>本次任务一共取消 " + tato +"  条无效订单<==============");
		} catch (BusinessException e) {
			logger.error("订单失效操作证失败！", e);
			throw e;
		} catch (Exception e) {
			logger.error("订单失效操作证失败！", e);
			throw new BusinessException("订单失效操作证失败！", e);
		}

		return tato;
	}

	public String createAccount(CreateAccountParams model)
			throws BusinessException {
		logger.info("================>开始  创建资金账户<==============");
		String result = "";
		try {
			CreateAccountParams accountParams = new CreateAccountParams();
			if (model == null) {
				logger.info("================》model( 充值参数对象)不能为空！《================");
				throw new BusinessException("model(审核对象)不能为空");
			}
			if (StringUtils.isEmpty(model.getMobileNo())) {
				logger.info("================》手机号不能为空！《================");
				throw new BusinessException("手机号不能为空");
			} else {
				accountParams.setMobileNo(model.getMobileNo());
			}
			if (StringUtils.isEmpty(model.getOwnerLoginName())) {
				logger.info("================》登录名不能为空！《================");
				throw new BusinessException("登录名不能为空");
			} else {
				accountParams.setOwnerLoginName(model.getOwnerLoginName());
			}
			if (StringUtils.isEmpty(model.getOwnerUserId())) {
				logger.info("================》用户id不能为空！《================");
				throw new BusinessException("用户id不能为空");
			} else {
				accountParams.setOwnerUserId(model.getOwnerUserId());
			}
			if (StringUtils.isNotEmpty(model.getPayPassword())) {
				accountParams.setPayPassword(model.getPayPassword());
			}

			result = payService.createAccount(accountParams);
			logger.info("================>结束  创建资金账户<==============");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("创建资金账户异常！", e);
			throw new BusinessException("创建资金账户异常！", e);
		}

		return result;
	}

	@Override
	public int auditRechargeLock(String applyId, String pid, String pname)
			throws BusinessException {
		logger.info("================>开始 锁着申请处理<==============");
		int result = 0;
		try {
			if (StringUtils.isEmpty(applyId)) {
				logger.info("================》申请记录id不能为空！《================");
				throw new BusinessException("申请记录id不能为空");
			}

			if (StringUtils.isEmpty(pid)) {
				logger.info("================》审核人id不能为空！《================");
				throw new BusinessException("审核人id不能为空");
			}

			if (StringUtils.isEmpty(pname)) {
				logger.info("================》审核人名字不能为空！《================");
				throw new BusinessException("审核人名字不能为空");
			}
			// 查询申请记录是否存在
			ICRechargeApply bean = new ICRechargeApply();
			bean.setId(applyId);
			ICRechargeApply icRechargeApply = (ICRechargeApply) idao
					.getModelById(bean);
			if (icRechargeApply == null) {
				logger.info("================》rechargeApply(审核对象)不能为空！《================");
				throw new BusinessException("rechargeApply(审核对象)不能为空");
			}

			// 修改申请状态
			icRechargeApply
					.setState(ICCardRechargeCons.RECHARGE_APPLY_STATE_DISTR_DOING);
			icRechargeApply.setApprovalPersonId(pid);
			icRechargeApply.setApprovalPersonName(pname);
			result = update(icRechargeApply);
			logger.info("================>结束 锁着申请处理<==============");
		} catch (Exception e) {
			logger.error("锁着申请处理异常！", e);
			throw new BusinessException("锁着申请处理异常！", e);
		}
		return result;
	}

	private int applyUnFreeze(ICRechargeApply rechargeApply, String userId)
			throws BusinessException {
		logger.info("================>开始  解冻<==============");
		int result = 0;

		try {
			if (rechargeApply == null) {
				logger.info("================》rechargeApply(审核对象)不能为空！《================");
				throw new BusinessException("rechargeApply(审核对象)不能为空");
			}
			if (StringUtils.isEmpty(rechargeApply.getRechargeOrderNo())) {
				logger.info("================》定单号不能为空！《================");
				throw new BusinessException("定单号不能为空");
			}

			// 解冻旺金币
			if (rechargeApply.getCreditNum() != null
					&& rechargeApply.getCreditNum().compareTo(BigDecimal.ZERO) > 0) {
				creditService.unfreeze(userId, rechargeApply.getCreditNum()
						.longValue(), rechargeApply.getOrderNo());
			}

			// 解冻资金帐户
			if (rechargeApply.getCaptialMoney() != null
					&& rechargeApply.getCaptialMoney().compareTo(
							BigDecimal.ZERO) > 0) {

				if (StringUtils.isEmpty(userId)) {
					logger.info("================》userId不能为空！《================");
					throw new BusinessException("userId不能为空");
				}

				Account account = checkAccount(userId);

				FreezeBalanceParams freezeBalanceParams = new FreezeBalanceParams();
				freezeBalanceParams.setAccountNo(account.getAccountNo());
				freezeBalanceParams.setOrderAmount(rechargeApply
						.getCaptialMoney().divide(MONEY_VALUE).toString());
				freezeBalanceParams.setWorkOrderNo(rechargeApply
						.getRechargeOrderNo());
				payService.unFreezeBalance(freezeBalanceParams);

			}

			result = 1;
			logger.info("================>结束  解冻<==============");
		} catch (BusinessException e) {
			throw e;
		} catch (YpptGatewayException e) {
			logger.error("网关异常：" + e.getErrorcode() + ":" + e.getMessage());
			throw new YpptGatewayException(e.getErrorcode(), e.getMessage());
		} catch (Exception e) {

			logger.error("解冻异常！", e);
			throw new BusinessException("解冻异常！", e);
		}

		return result;
	}

	private Account checkAccount(String userId) throws BusinessException {
		Account account = null;
		try {
			account = payService.queryAccountByUserId(userId);
			if (account == null) {
				logger.info("================>资金帐户信息不存在！<==============");
				throw new BusinessException("资金帐户信息不存在！");
			}
			if (StringUtils.isEmpty(account.getAccountNo())) {
				logger.info("================>资金帐号不存在！<==============");
				throw new BusinessException("资金帐号不存在！");
			}
			return account;
		} catch (YpptGatewayException e) {
			logger.error("网关异常：" + e.getErrorcode() + ":" + e.getMessage());
			throw new YpptGatewayException(e.getErrorcode(), e.getMessage());
		}

	};

	@SuppressWarnings("unchecked")
	@Override
	public int auditRecharge(ICardRechageMetaBean rechageMetaBean,
			ICCardRechangeProcessLog processLog) throws BusinessException {
		logger.info("================>开始  审核充值<==============");
		int result = 0;
		try {

			if (rechageMetaBean == null) {
				logger.info("================》rechageMetaBean(审核对象)不能为空！《================");
				throw new BusinessException("rechageMetaBean(审核对象)不能为空");
			}
			ICRechargeApply rechargeApply = rechageMetaBean.getRechargeApply();
			if (rechargeApply == null) {
				logger.info("================》申请信息不能为空！《================");
				throw new BusinessException("申请信息不能为空");
			}
			if (StringUtils.isEmpty(rechargeApply.getOrderNo())) {
				logger.info("================》业务订单号不能为空！《================");
				throw new BusinessException("业务订单号不能为空");
			}
			if (StringUtils.isEmpty(rechargeApply.getState())) {
				logger.info("================》申请订单状态不能为空！《================");
				throw new BusinessException("申请订单状态不能为空");
			}
			if (ICCardRechargeCons.RECHARGE_APPLY_STATE_DISTR_SUCC
					.equals(rechargeApply.getState())) {
				logger.info("================》申请订单已经被处理！《================");
				throw new BusinessException("申请订单已经被处理");
			}
			if (!validateICCardRechargeDeatisDist(
					rechargeApply.getActualDivMoney(),
					rechageMetaBean.getIcRechargeApplyDetails())) {
				logger.info("================》参数信息验证不通过！《================");
				throw new BusinessException("参数信息验证不通过");
			}

			if (StringUtils.isEmpty(rechargeApply.getDataSource())) {
				logger.info("================》数据来源不能为空！《================");
				throw new BusinessException("数据来源不能为空");
			}

			String status = null;
			if ("0".equals(rechargeApply.getDataSource())) {// web
				// 扣旺金币、增加旺金支出记录
				boolean isOnlyCredit = false;
				if (rechargeApply.getCreditNum() != null
						&& rechargeApply.getCreditNum().compareTo(
								BigDecimal.ZERO) > 0) {
					String deductNo = creditService.deduct(rechargeApply
							.getUserId(), rechargeApply.getCreditNum()
							.longValue(), rechargeApply.getOrderNo());
					addCreditIO(rechargeApply);
					addCreditOrder(rechargeApply, deductNo);
					isOnlyCredit = true;
				}

				// 扣资金帐户
				if (rechargeApply.getCaptialMoney() != null
						&& rechargeApply.getCaptialMoney().compareTo(
								BigDecimal.ZERO) > 0) {
					status = deductMoneyAndsavePaymentOrder(rechargeApply,
							deductMoneySureResponse(rechargeApply));
					isOnlyCredit = false;
				}
				if (isOnlyCredit) {
					status = ICCardRechargeCons.RECHARGE_APPLY_STATE_PAY_SUCC;
				}
			} else if ("1".equals(rechargeApply.getDataSource())) {// app
				// 扣旺金币、增加旺金支出记录
				boolean isOnlyCredit = false;
				if (rechargeApply.getCreditNum() != null
						&& rechargeApply.getCreditNum().compareTo(
								BigDecimal.ZERO) > 0) {
					String deductNo = creditService.deduct(rechargeApply
							.getUserId(), rechargeApply.getCreditNum()
							.longValue(), rechargeApply.getOrderNo());
					addCreditIO(rechargeApply);
					addCreditOrder(rechargeApply, deductNo);
					isOnlyCredit = true;
				}

				// 扣资金帐户
				if (rechargeApply.getCaptialMoney() != null
						&& rechargeApply.getCaptialMoney().compareTo(
								BigDecimal.ZERO) > 0) {
					status = deductMoneyAndsavePaymentOrder(rechargeApply,
							deductMoneySureResponse(rechargeApply));
					isOnlyCredit = false;
				}
				if (isOnlyCredit) {
					status = ICCardRechargeCons.RECHARGE_APPLY_STATE_PAY_SUCC;
				}
			} else if ("2".equals(rechargeApply.getDataSource())) {// 后台

				status = deductMoneyForManagerApp(rechargeApply);

			}

			// 扣款不成功不成功,直接返回
			if (!status
					.equals(ICCardRechargeCons.RECHARGE_APPLY_STATE_PAY_SUCC)) {
				processLog
						.setState(ICCardRechargeCons.RECHARGE_APPLY_STATE_DISTR_WAITING);
				idao.addModel(processLog);
				return 0;
			}

			for (ICRechargeApplyDetail applyDetail : rechageMetaBean
					.getIcRechargeApplyDetails()) {
				if (applyDetail == null) {
					continue;
				}
				idao.updateModelAll(applyDetail);
			}

			rechargeApply
					.setState(ICCardRechargeCons.RECHARGE_APPLY_STATE_DISTR_SUCC);
			result = idao.updateModelAll(rechargeApply);

			// 修改累计充值
			processLog
					.setState(ICCardRechargeCons.RECHARGE_APPLY_STATE_DISTR_SUCC);
			idao.addModel(processLog);

			// 更新UserStat 交易总额
			operateUserStat(rechargeApply);
			final List<ICRechargeApplyDetail> detailList = rechageMetaBean
					.getIcRechargeApplyDetails();
			// 充值申请审核成功后发送短信 start ; modify by zhujianbo 2015-02-06
			// Thread t1 = new Thread(new Runnable() {
			// @Override
			// public void run() {
			try {
				String templateCode = "RECHARGE_APPLY_SUCC";// 模板编码
				SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
				String time = df.format(new Date());
				for (int i = 0; i < detailList.size(); i++) {
					try {
						String sendCardNumber = detailList.get(i).getCardNo();
						ICCardExampleExtended iccardExample = new ICCardExampleExtended();
						iccardExample
								.createCriteria()
//								.andCardTypeEqualTo(
//										detailList.get(i).getCardType())
								.andCardNumberEqualTo(sendCardNumber);
						List cardList = idao.getModels(iccardExample);
						if (cardList != null && cardList.size() > 0) {
							ICCard card = (ICCard) cardList.get(0);
							if ("1".equals(card.getMessageOrNot())) {// 是否发送短信0：否；1：是
								List<String> messageParamslist = new ArrayList<String>();
								messageParamslist
										.add(sendCardNumber
												.substring(sendCardNumber
														.length() - 5));
								messageParamslist.add(time);
								messageParamslist.add(detailList.get(i)
										.getActualDivMoney()
										.divide(new BigDecimal("100.00"))
										.setScale(2, BigDecimal.ROUND_HALF_UP)
										+ "");
								
								
								MessageUtil.sendShortMessage(
										card.getTelephoneNumber(),
										templateCode, messageParamslist);
							}
						}
					} catch (Exception e) {
						logger.error("发送短信失败"+new JsonSupport().serialize(detailList.get(i)),e);
					}

				}
			} catch (Exception e) {
				logger.error("发生短信失败", e);
			}
			// 充值申请审核成功后发送短信 end; modify by zhujianbo 2015-02-06

			// }
			// });
			// t1.start();

			logger.info("================>结束 审核充值<==============");
//		} catch (BusinessException e) {
//			logger.error("油卡充值审核发送异常!", e);
//			throw e;
//		} catch (YpptGatewayException e) {
//			logger.error("网关异常：" + e.getErrorcode() + ":" + e.getMessage());
//			throw new YpptGatewayException(e.getErrorcode(), e.getMessage());
		} catch (Exception e) {
			logger.error("审核充值异常！", e);
			throw new BusinessException("审核充值异常！", e);

		}

		return result;
	}

	private String deductMoneyAndsavePaymentOrder(
			ICRechargeApply rechargeApply,
			DeductMoneySureResponse deductMoneySureResponse)
			throws BusinessException, Exception {

		if (deductMoneySureResponse == null) {
			logger.error("================》deductMoneySureResponse(扣款返回对象)不能为空！《================");
			throw new BusinessException("deductMoneySureResponse(扣款返回对象)不能为空");
		}
		if (StringUtils.isEmpty(deductMoneySureResponse.getOrderNo())) {
			logger.error("================》支付订单号不能为空！《================");
			throw new BusinessException("支付订单号不能为空");
		}

		if (StringUtils.isEmpty(deductMoneySureResponse.getResult())) {
			logger.error("================》支付结果不能为空！《================");
			throw new BusinessException("支付结果不能为空");
		}

		ICCardPaymentOrder payOrderModel = new ICCardPaymentOrder();
		payOrderModel.setId(UUID.randomUUID().toString());
		payOrderModel.setLocalOrderNo(rechargeApply.getOrderNo());
		payOrderModel.setPayOrderNo(deductMoneySureResponse.getOrderNo());
		payOrderModel.setTbaleName(ICCardRechargeCons.PAY_ORDER_TABLE_NAME);
		payOrderModel.setPayType(ICCardRechargeCons.RECHARGE_APPLY_DEDUCT);
		rechargeApply.setRechargeOrderNo(deductMoneySureResponse.getOrderNo());

		// 成功
		String status = "";
		if ("1".equals(deductMoneySureResponse.getResult())) {
			status = ICCardRechargeCons.RECHARGE_APPLY_STATE_PAY_SUCC;
		} else if ("-1".equals(deductMoneySureResponse.getResult())) {
			status = ICCardRechargeCons.RECHARGE_APPLY_STATE_PAY_FAIL;
		} else {
			logger.error("================》支付结果返回异常！《================");
			throw new BusinessException("支付结果返回异常");
		}
		payOrderModel.setStatus(status);
		idao.addModel(payOrderModel);
		return status;
	}

	private DeductMoneySureResponse deductMoneySureResponse(
			ICRechargeApply rechargeApply) throws BusinessException {
		DeductMoneySureParams deductMoneySureParams = new DeductMoneySureParams();
		deductMoneySureParams.setMerchantOrderNo(rechargeApply.getOrderNo());
		deductMoneySureParams.setOrderAmount(rechargeApply.getCaptialMoney()
				.divide(MONEY_VALUE).toString());
		deductMoneySureParams
				.setResult(ICCardRechargeCons.RECHARGE_APPLY_SURE_DIV);
		deductMoneySureParams.setOrderNo(rechargeApply.getRechargeOrderNo());
		deductMoneySureParams.setPayType(rechargeApply.getPayWay());

		Account account = checkAccount(rechargeApply.getUserId());
		if (account == null) {
			logger.info("================》资金账户不能为空！《================");
			throw new BusinessException("资金账户不能为空");
		}
		if (StringUtils.isEmpty(account.getAccountNo())) {
			logger.info("================》资金账户号不能为空！《================");
			throw new BusinessException("资金账户号不能为空");
		}

		deductMoneySureParams.setAccountNo(account.getAccountNo());
		DeductMoneySureResponse deductMoneySureResponse = payService
				.deductMoneySure(deductMoneySureParams);
		return deductMoneySureResponse;
	}

	private void addCreditOrder(ICRechargeApply rechargeApply, String deductNo)
			throws Exception {
		ICCardCreditOrder cardCreditOrder = new ICCardCreditOrder();
		cardCreditOrder.setId(UUID.randomUUID().toString());
		cardCreditOrder.setLocalOrderNo(rechargeApply.getOrderNo());
		cardCreditOrder.setPayOrderNo(deductNo);
		cardCreditOrder.setPayType(ICCardRechargeCons.RECHARGE_APPLY_DEDUCT);
		cardCreditOrder
				.setStatus(ICCardRechargeCons.RECHARGE_APPLY_STATE_PAY_SUCC);
		cardCreditOrder.setTbaleName("ICCardCreditOrder");
		idao.addModel(cardCreditOrder);
	}

	private String deductMoneyForManagerApp(ICRechargeApply rechargeApply)
			throws BusinessException, Exception {
		Account account = checkAccount(rechargeApply.getUserId());
		if (account == null) {
			logger.info("================》资金账户不能为空！《================");
			throw new BusinessException("资金账户不能为空");
		}
		if (StringUtils.isEmpty(account.getAccountNo())) {
			logger.info("================》资金账户号不能为空！《================");
			throw new BusinessException("资金账户号不能为空");
		}

		DeductMoneyParams params = new DeductMoneyParams();
		params.setAccountNo(account.getAccountNo());
		params.setOrderAmount(rechargeApply.getCaptialMoney()
				.divide(MONEY_VALUE).toString());
		params.setUserId(rechargeApply.getUserId());
		params.setWorkOrderNo(rechargeApply.getOrderNo());

		String payOrderNo = payService.deductMoney(params);

		String status = "";
		if (StringUtils.isEmpty(payOrderNo)) {
			status = ICCardRechargeCons.RECHARGE_APPLY_STATE_PAY_FAIL;
		} else {
			status = ICCardRechargeCons.RECHARGE_APPLY_STATE_PAY_SUCC;
		}

		ICCardPaymentOrder payOrderModel = new ICCardPaymentOrder();
		payOrderModel.setId(UUID.randomUUID().toString());
		payOrderModel.setLocalOrderNo(rechargeApply.getOrderNo());
		payOrderModel.setPayOrderNo(payOrderNo);
		payOrderModel.setTbaleName(ICCardRechargeCons.PAY_ORDER_TABLE_NAME);
		payOrderModel.setPayType(ICCardRechargeCons.RECHARGE_APPLY_DEDUCT);
		rechargeApply.setRechargeOrderNo(payOrderNo);

		payOrderModel.setStatus(status);
		idao.addModel(payOrderModel);
		return status;
	}

	private void addCreditIO(ICRechargeApply rechargeApply) throws Exception {
		ICCardCreditAccountIO accountIO = new ICCardCreditAccountIO();
		accountIO.setId(UUID.randomUUID().toString());
		// accountIO.setAccountId(accountId);
		accountIO.setBalance(creditService.queryBalance(rechargeApply
				.getUserId()));// 操作余额
		accountIO.setCreditNum(rechargeApply.getCreditNum().longValue());// 积分数量
		accountIO.setModel("2");// 1收入2支出
		accountIO.setOperateTime(System.currentTimeMillis());
		accountIO.setOperator(rechargeApply.getApprovalPersonId());//操作人
		accountIO.setReasonId(rechargeApply.getId());
		accountIO.setRemark("旺金币抵扣");
		accountIO.setState("0");// 0成功1失败
		accountIO.setType("recharge");// recharge积分充值 rebate积分返利
		accountIO.setUserId(rechargeApply.getUserId());
		accountIO.setUserName(rechargeApply.getUserName());
		accountIO.setUserRegPhone(rechargeApply.getUserRegPhone());
		accountIO.setUserType(rechargeApply.getUserType());
		accountIO.setTradeTime(System.currentTimeMillis());
		idao.addModel(accountIO);
	}

	@Override
	public PaginationResult<?> paginateRechargeAndTradeInfoForAPP(
			Map<String, Object> params) throws BusinessException {
		logger.info("================>开始查询充值和油品记录<==============");
		PaginationResult<?> result = new PaginationResult();
		try {

			if (params == null) {
				logger.info("================》params(查询参数)不能为空！《================");
				throw new BusinessException("params(查询参数)不能为空");
			}
			if (params.get("userId") == null || "".equals(params.get("userId"))) {
				logger.info("================》用户id不能为空！《================");
				throw new BusinessException("用户id不能为空");
			}

			int page = 1;
			if (params.get("page") != null) {
				page = (Integer) params.get("page");
			}

			int pageSize = 10;
			if (params.get("pageSize") != null) {
				pageSize = (Integer) params.get("pageSize");
			}

			Integer startRow = (page - 1) * pageSize;
			Integer endRow = page * pageSize;
			params.put("startRow", startRow);
			params.put("endRow", endRow);

			List list = queryListBySQL(
					"TB_IC_CARD_RECHARGE_APPLY_EXTEND.abatorgenerated_selectByPaginateRechargeAndTradeInfoForAPP",
					params);
			int total = (Integer) queryObjectBySQL(
					"TB_IC_CARD_RECHARGE_APPLY_EXTEND.abatorgenerated_countByPaginateRechargeAndTradeInfoForAPP",
					params);
			result.setData(list);
			result.setTotal(total);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("获取充值记录信息异常！", e);
			throw new BusinessException("获取充值记录信息异常！", e);

		}
		logger.info("================>结束查询充值和油品记录<==============");
		return result;
	}

	@Override
	public PaginationResult<?> paginateRecharge(Map<String, Object> params)
			throws BusinessException {
		logger.info("================>开始查询充值记录<==============");
		PaginationResult<?> result = new PaginationResult();
		try {

			if (params == null) {
				logger.info("================》params(查询参数)不能为空！《================");
				throw new BusinessException("params(查询参数)不能为空");
			}
			if (params.get("userId") == null || "".equals(params.get("userId"))) {
				logger.info("================》用户id不能为空！《================");
				throw new BusinessException("用户id不能为空");
			}

			int page = 1;
			if (params.get("page") != null) {
				page = (Integer) params.get("page");
			}

			int pageSize = 10;
			if (params.get("pageSize") != null) {
				pageSize = (Integer) params.get("pageSize");
			}

			Integer startRow = (page - 1) * pageSize;
			Integer endRow = page * pageSize;
			params.put("startRow", startRow);
			params.put("endRow", endRow);

			List list = queryListBySQL(
					"TB_IC_CARD_RECHARGE_APPLY_EXTEND.abatorgenerated_selectByPaginateRecharge",
					params);
			Integer total = (Integer) queryObjectBySQL(
					"TB_IC_CARD_RECHARGE_APPLY_EXTEND.abatorgenerated_countByPaginateRecharge",
					params);

			result.setData(list);
			result.setTotal(total);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("获取充值记录信息异常！", e);
			throw new BusinessException("获取充值记录信息异常！", e);

		}
		logger.info("================>结束查询充值记录<==============");
		return result;
	}

	@Override
	public BigDecimal countRecharge(Map<String, Object> params)
			throws BusinessException {
		logger.info("================>开始计算充值总额<==============");
		BigDecimal result = null;
		try {
			if (params == null) {
				logger.info("================》params(查询参数)不能为空！《================");
				throw new BusinessException("params(查询参数)不能为空");
			}
			if (params.get("userId") == null || "".equals(params.get("userId"))) {
				logger.info("================》用户id不能为空！《================");
				throw new BusinessException("用户id不能为空");
			}

			result = (BigDecimal) queryObjectBySQL(
					"TB_IC_CARD_RECHARGE_APPLY_EXTEND.abatorgenerated_countRecharge",
					params);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("获取充值记录信息总数异常！", e);
			throw new BusinessException("获取充值记录信息总数异常！", e);

		}
		logger.info("================>结束计算充值总额<==============");
		return result;

	}

	@Override
	public ICardRechageMetaBean getICardRechageMetaBeanByapplyId(String applyId)
			throws BusinessException {
		logger.info("================>开始 获取充值记录信息<==============");
		ICardRechageMetaBean result = null;
		ICRechargeApply model;
		try {
			if (StringUtils.isEmpty(applyId)) {
				logger.info("================》applyId不能为空！《================");
				throw new BusinessException("applyId不能为空");
			}
			result = new ICardRechageMetaBean();
			model = new ICRechargeApply();
			model.setId(applyId);
			result.setRechargeApply((ICRechargeApply) idao.getModelById(model));

			ICRechargeApplyDetailExampleExtended exampleExtended = new ICRechargeApplyDetailExampleExtended();
			exampleExtended.createCriteria().andApplyIdEqualTo(applyId);
			result.setIcRechargeApplyDetails(idao.getModels(exampleExtended));
			logger.info("================>结束 获取充值记录信息<==============");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("获取充值记录信息异常！", e);
			throw new BusinessException("获取充值记录信息异常！", e);
		}

		return result;
	}

	@Override
	public ICCardRechageApplyExtend applyRecharge(
			ICCardRechageApplyMetaBean model) throws BusinessException {
		logger.info("================>开始  生成业务订单号 <==============");
		ICCardRechageApplyExtend reuslt = new ICCardRechageApplyExtend();
		String freezeNo = null;
		// 订单
		String orderNo = null;
		String applyId = null;
		ICRechargeApply apply = model.getRechargeApply();
		List<ICRechargeApplyDetail> applyDList = model.getRechargeDeatils();
		try {
			// 参数校验判断
			if (!validateICCardRecharge(model)) {
				logger.info("================>输入model未校验通过！参数错误，请检查输入参数!<==============");
				throw new BusinessException("输入model未校验通过！参数错误，请检查输入参数!");
			}

			// 订单
			orderNo = buildRechargeOrderNo();
			apply.setState(ICCardRechargeCons.RECHARGE_APPLY_STATE_PAY_WAITING);
			apply.setOrderNo(orderNo);

			boolean isOnlyCredit = false;
			if (apply.getCreditNum() != null && apply.getTotalMoney() != null) {
				if (apply.getCreditMoney().compareTo(apply.getTotalMoney()) == 0) {
					isOnlyCredit = true;
					apply.setPayWay(ICCardRechargeCons.PAY_ORDER_PAY_WAY_CREDIT);
					apply.setState(ICCardRechargeCons.RECHARGE_APPLY_STATE_DISTR_WAITING);
					
				}
			}
			reuslt.setOnlyCredit(isOnlyCredit);

			if (StringUtils.isNotEmpty(apply.getId())) {
				ICRechargeApply bean = new ICRechargeApply();
				bean.setId(apply.getId());
				bean = getById(bean);
				reuslt.setRechargeApply(bean);

				return reuslt;
			}

			apply.setStepNo("1");

			applyId = idao.addModel(apply);

			for (ICRechargeApplyDetail applyDetail : applyDList) {
				applyDetail.setApplyId(applyId);
			}
			idao.addModelBatch(applyDList);

			// 旺进币
			if (apply.getCreditNum() != null
					&& apply.getCreditNum().compareTo(BigDecimal.ZERO) > 0) {

				Long creditBalance = creditService.queryBalance(model
						.getRechargeApply().getUserId());
				Long freezeMoney = apply.getCreditNum().longValue();
				if (creditBalance.compareTo(freezeMoney) < 0) {
					logger.info("================>输入的旺金币大于旺金币账户余额！<==============");
					throw new BusinessException("输入的旺金币大于旺金币账户余额！");
				}
				logger.info("开始冻结旺金币userId" + apply.getUserId() + "冻结金额"
						+ freezeMoney + "业务订单号" + apply.getOrderNo());
				freezeNo = creditService.freeze(apply.getUserId(), freezeMoney,
						apply.getOrderNo());
				apply.setCreditState(ICCardRechargeCons.RECHARGE_APPLY_FREEZE_SUCC);
				if (StringUtils.isEmpty(freezeNo)) {
					apply.setCreditState(ICCardRechargeCons.RECHARGE_APPLY_FREEZE_FAIL);
					logger.info("冻结旺金币失败" + "userid" + apply.getUserId()
							+ "冻结金额" + freezeMoney + "业务订单号"
							+ apply.getOrderNo());
				} else {
					logger.info("冻结旺金币成功订单号：" + freezeNo);
					apply.setCreditState(ICCardRechargeCons.RECHARGE_APPLY_FREEZE_SUCC);
				}

				ICCardCreditOrder cardCreditOrder = new ICCardCreditOrder();
				cardCreditOrder.setId(UUID.randomUUID().toString());
				cardCreditOrder.setLocalOrderNo(orderNo);
				cardCreditOrder.setPayOrderNo(freezeNo);
				cardCreditOrder
						.setPayType(ICCardRechargeCons.RECHARGE_APPLY_FREEZE);
				cardCreditOrder.setStatus(ICCardRechargeCons.FLAG_SUCC);
				cardCreditOrder.setTbaleName("ICCardCreditOrder");
				idao.addModel(cardCreditOrder);
			}

			reuslt.setRechargeApply(apply);

			if (!isOnlyCredit) {
				ICCardPaymentOrder payOrderModel = new ICCardPaymentOrder();
				payOrderModel.setLocalOrderNo(orderNo);
				payOrderModel
						.setTbaleName(ICCardRechargeCons.PAY_ORDER_TABLE_NAME);
				payOrderModel
						.setStatus(ICCardRechargeCons.RECHARGE_APPLY_STATE_PAY_WAITING);
				idao.addModel(payOrderModel);

				Account account = payService.queryAccountByUserId(model
						.getRechargeApply().getUserId());
				if (account == null) {
					// 创建资金账户
					CreateAccountParams createAccountParams = new CreateAccountParams();
					createAccountParams.setOwnerUserId(apply.getUserId());
					createAccountParams.setMobileNo(apply.getUserRegPhone());
					createAccountParams.setOwnerLoginName(apply
							.getUserLoginName());
					// if(StringUtils.isNotEmpty(apply.get))
					payService.createAccount(createAccountParams);
				}
			}

		} catch (BusinessException e) {
			throw e;
		} catch (YpptGatewayException e) {
			logger.error("网关异常：》》》》》》" + e.getErrorcode() + ":"
					+ e.getMessage());
			throw new YpptGatewayException(e.getErrorcode(), e.getMessage());
		} catch (Exception e) {
			logger.error("创建业务订单号信息异常！", e);
			throw new BusinessException("创建业务订单号信息异常！", e);
		} finally {

			ICCardCreditOrderMongoDBBean mongoDBBean = new ICCardCreditOrderMongoDBBean();
			mongoDBBean.setPayType("冻结");
			if (!StringUtils.isEmpty(orderNo)) {
				mongoDBBean.setLocalOrderNo(orderNo);
			}

			mongoDBBean.setCreditTime(System.currentTimeMillis());
			mongoDBBean.setTotalMoney(apply.getCreditMoney());
			// 积分冻结成功
			if (!StringUtils.isEmpty(freezeNo)) {
				mongoDBBean.setPayOrderNo(freezeNo);
				mongoDBBean.setStatus(ICCardRechargeCons.FLAG_SUCC);
			} else {
				mongoDBBean.setStatus(ICCardRechargeCons.FLAG_FAIL);
			}

			try {
				mongoService.save(mongoDBBean, WriteConcern.FSYNC_SAFE);
			} catch (Exception e) {
				logger.error("MongoDB保存旺金币冻结订单异常！", e);
				throw new BusinessException("MongoDB保存旺金币冻结订单异常！", e);
			}

			try {
				RechargeApplyMongoDBBean applyMongoDBBean = new RechargeApplyMongoDBBean();
				RechargeDetailMongoDBbean detailMongoDBbean = new RechargeDetailMongoDBbean();
				PropertyUtils.copyProperties(applyMongoDBBean, apply);
				mongoService.save(applyMongoDBBean, WriteConcern.FSYNC_SAFE);
				if (StringUtils.isEmpty(applyId)) {
					applyId = UUID.randomUUID().toString();
				}
				for (ICRechargeApplyDetail applyDetails : applyDList) {
					applyDetails.setApplyId(applyId);
					PropertyUtils.copyProperties(detailMongoDBbean, apply);
					mongoService.save(detailMongoDBbean,
							WriteConcern.FSYNC_SAFE);
				}
			} catch (Exception e) {
				logger.error("MongoDB保存单异常！", e);
				throw new BusinessException("MongoDB保存订单异常！", e);
			}

		}

		logger.info("================>结束 生成业务订单号<==============");
		return reuslt;
	}

	@Override
	public String buildRequestContent(String applyId, String userId,
			String userLoginName, String orderNo, String mark,
			BigDecimal captMoney) throws BusinessException {

		logger.info("================>开始 调用网关，构造请求内容<==============");

		String result = "";
		try {
			// 添加userId
			if (StringUtils.isEmpty(userId)) {
				logger.info("================>userId不能为空！<==============");
				throw new BusinessException("userId不能为空！");
			}
			if (StringUtils.isEmpty(orderNo)) {
				logger.info("================>业务订单号不能为空！<==============");
				throw new BusinessException("业务订单号不能为空！");
			}
			if (captMoney == null && captMoney.compareTo(BigDecimal.ZERO) < 0) {
				logger.info("================>充值金额不合法！<==============");
				throw new BusinessException("充值金额不合法！");
			}
			Account account = payService.queryAccountByUserId(userId);
			if (account == null) {
				logger.info("================>资金帐户信息不存在！<==============");
				throw new BusinessException("资金帐户信息不存在！");
			}
			if (StringUtils.isEmpty(account.getAccountNo())) {
				logger.info("================>资金帐号不存在！<==============");
				throw new BusinessException("资金帐号不存在！");
			}

			GetCashierDeductUrlParams cashierDeductUrlParams = new GetCashierDeductUrlParams();
			cashierDeductUrlParams.setMerchantOrderNo(orderNo);
			cashierDeductUrlParams.setMerchantOrderAmount(captMoney.divide(
					MONEY_VALUE).toString());
			cashierDeductUrlParams.setAccountNo(account.getAccountNo());
			if (StringUtils.isNotEmpty(mark)) {
				cashierDeductUrlParams.setMerchantOrderRemark(mark);
			}
			// TODO 与支付中心待确定
			String url = null;
			url = ResourceBundleUtil.getSystemString("payCallBackUrl");
			url += "?id=" + applyId;
			if (url != null && !"".equals(url)) {
				cashierDeductUrlParams.setFcallbackURL(url);
			}

			cashierDeductUrlParams.setUserId(userId);
			cashierDeductUrlParams
					.setProductName(ICCardRechargeCons.PRODUCT_NAME);
			cashierDeductUrlParams
					.setProductCatalog(ICCardRechargeCons.PRODUCT_CATALOG);
			cashierDeductUrlParams
					.setIdentityType(ICCardRechargeCons.IDENTITY_TYPE);
			cashierDeductUrlParams.setIdentityId(userId);
			cashierDeductUrlParams.setClentType(ICCardRechargeCons.CLENT_TYPE);
			cashierDeductUrlParams.setClentId(userId);
			cashierDeductUrlParams.setUserLoginName(userLoginName);
			result = payService
					.getCashierDeductMoneyUrl(cashierDeductUrlParams);
			if (StringUtils.isEmpty(result)) {
				logger.info("调研网关、支付中心异常");
			}
		} catch (BusinessException e) {
			throw e;
		} catch (YpptGatewayException e) {
			logger.error("网关异常：" + e.getErrorcode() + ":" + e.getMessage());
			throw new YpptGatewayException(e.getErrorcode(), e.getMessage());
		} catch (Exception e) {
			logger.error("调用网关，构造请求内容异常！", e);
			throw new BusinessException(e.getMessage());
		}
		logger.info("================>结束 调用网关，构造请求内容<==============");
		return result;
	}

	@Override
	public String callBackPay(String orderNo, String rslt, BigDecimal money,
			String payOrderNo, String payWay) throws BusinessException {
		logger.info("================>开始 调用网关，修改订单状态<==============");

		String result = "";
		try {
			if (StringUtils.isEmpty(orderNo)) {
				logger.info("================>业务订单号为空！<==============");
				throw new BusinessException("业务订单号为空！");
			}
			if (StringUtils.isEmpty(rslt)) {
				logger.info("================>支付结果不存在！<==============");
				throw new BusinessException("支付结果不存在！");
			}
			if (money == null) {
				logger.info("================>金额为空<==============");
				throw new BusinessException("金额为空！");
			}
			if (StringUtils.isEmpty(payOrderNo)) {
				logger.info("================>支付订单号为空！<==============");
				throw new BusinessException("支付订单号为空！");
			}
			if (StringUtils.isEmpty(payWay)) {
				logger.info("================>支付方式为空！<==============");
				throw new BusinessException("支付方式为空！");
			}
			ICRechargeApplyExampleExtended applyExampleExtended = new ICRechargeApplyExampleExtended();
			applyExampleExtended.createCriteria().andOrderNoEqualTo(orderNo);
			List<ICRechargeApply> applys = idao.getModels(applyExampleExtended);
			if (applys == null || applys.isEmpty()) {
				logger.error("(申请)订单不存在！");
				throw new BusinessException("(申请)订单不存在！");
			}

			ICRechargeApply apply = applys.get(0);
			ICCardPaymentOrderExampleExtended paymentExampleExtended = new ICCardPaymentOrderExampleExtended();
			paymentExampleExtended.createCriteria().andLocalOrderNoEqualTo(
					orderNo);

			List<ICCardPaymentOrder> cardPaymentOrders = idao
					.getModels(paymentExampleExtended);
			ICCardPaymentOrder cardPaymentOrder = cardPaymentOrders.get(0);
			cardPaymentOrder.setPayOrderNo(payOrderNo);
			cardPaymentOrder
					.setPayType(ICCardRechargeCons.RECHARGE_APPLY_FREEZE);

			String applyStat = "";
			String payOrderStat = "";

			
			String catState="";//充值订单表，资金支付的状态

			String captialState = "";

			if (ICCardRechargeCons.RECHARGE_CALL_BACK_SUCC.equals(rslt)) {
				applyStat = ICCardRechargeCons.RECHARGE_APPLY_STATE_DISTR_WAITING;
				payOrderStat = ICCardRechargeCons.FLAG_SUCC;
				catState=ICCardRechargeCons.RECHARGE_APPLY_DEDUCT_SUCC;
			} else {
				applyStat = ICCardRechargeCons.RECHARGE_APPLY_STATE_PAY_FAIL;
				payOrderStat = ICCardRechargeCons.FLAG_FAIL;
				catState=ICCardRechargeCons.RECHARGE_APPLY_DEDUCT_FAIL;

			}
			cardPaymentOrder.setStatus(payOrderStat);
			apply.setState(applyStat);
			apply.setCaptialState(catState);

			idao.updateModelAll(cardPaymentOrder);
			apply.setPayWay(payWay);
			apply.setRechargeOrderNo(payOrderNo);
			update(apply);

			if (cardPaymentOrders == null || cardPaymentOrders.isEmpty()) {
				logger.error("(支付)订单不存在！");
				throw new BusinessException("(支付)订单不存在！");
			}

			result = payOrderNo;
		} catch (BusinessException e) {
			throw e;
		} catch (YpptGatewayException e) {
			logger.error("网关异常：" + e.getErrorcode() + ":" + e.getMessage());
			throw new YpptGatewayException(e.getErrorcode(), e.getMessage());
		} catch (Exception e) {
			logger.error("调用网关，修改订单状态异常！", e);
			throw new BusinessException("调用网关，修改订单状态异常！", e);
		} finally {
			// ICCardCreditOrderMongoDBBean mongoDBBean = new
			// ICCardCreditOrderMongoDBBean();
			// mongoDBBean.setPayType("冻结");
			// if (!StringUtils.isEmpty(orderNo)) {
			// mongoDBBean.setLocalOrderNo(orderNo);
			// }
			//
			// mongoDBBean.setCreditTime(System.currentTimeMillis());
			// mongoDBBean.setTotalMoney(apply.getCreditMoney());
			// // 积分冻结成功
			// if (!StringUtils.isEmpty(freezeNo)) {
			// mongoDBBean.setPayOrderNo(freezeNo);
			// mongoDBBean.setStatus(ICCardRechargeCons.FLAG_SUCC);
			// } else {
			// mongoDBBean.setStatus(ICCardRechargeCons.FLAG_FAIL);
			// }
			//
			// try {
			// mongoService.save(mongoDBBean, WriteConcern.FSYNC_SAFE);
			// } catch (Exception e) {
			// logger.error("MongoDB保存旺金币冻结订单异常！", e);
			// throw new BusinessException("MongoDB保存旺金币冻结订单异常！", e);
			// }
			//
			// try {
			// RechargeApplyMongoDBBean applyMongoDBBean = new
			// RechargeApplyMongoDBBean();
			// RechargeDetailMongoDBbean detailMongoDBbean = new
			// RechargeDetailMongoDBbean();
			// PropertyUtils.copyProperties(applyMongoDBBean, apply);
			// mongoService.save(applyMongoDBBean, WriteConcern.FSYNC_SAFE);
			// if (StringUtils.isEmpty(applyId)) {
			// applyId = UUID.randomUUID().toString();
			// }
			// for (ICRechargeApplyDetail applyDetails : applyDList) {
			// applyDetails.setApplyId(applyId);
			// PropertyUtils.copyProperties(detailMongoDBbean, apply);
			// mongoService.save(detailMongoDBbean,
			// WriteConcern.FSYNC_SAFE);
			// }
			// } catch (Exception e) {
			// logger.error("MongoDB保存单异常！", e);
			// throw new BusinessException("MongoDB保存订单异常！", e);
			// }
		}
		logger.info("================>结束 调用网关，修改订单状态<==============");
		return result;
	}

	@Override
	public BigDecimal countSingleRecharge(Map<String, Object> params)
			throws BusinessException {
		logger.info("================>开始 统计单张卡充值总额 <==============");
		BigDecimal result = null;
		try {
			if (params == null) {
				logger.info("================》params(查询参数)不能为空！《================");
				throw new BusinessException("params(查询参数)不能为空");
			}
			if (params.get("cardId") == null) {
				logger.info("================》cardid不能为空！《================");
				throw new BusinessException("cardid不能为空");
			}
			if (params.get("startTime") == null) {
				logger.info("================》开始时间不能为空！《================");
				throw new BusinessException("开始时间不能为空");
			}
			if (params.get("endTime") == null) {
				logger.info("================》结束时间不能为空！《================");
				throw new BusinessException("结束时间不能为空");
			}

			params.put("status",
					ICCardRechargeCons.INVOICE_APPLY_STATE_OPENING_SUCC);

			result = (BigDecimal) queryObjectBySQL(
					"TB_IC_CARD_RECHARGE_APPLY_EXTEND.countSingleRecharge",
					params);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("获取统计单张卡充值总额异常！", e);
			throw new BusinessException("获取 统计单张卡充值总额异常！", e);

		}
		logger.info("================>结束 统计单张卡充值总额<==============");
		return result;

	}

	private String buildRechargeOrderNo() throws Exception {
		String orderNo = "rc";
		orderNo += new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		orderNo += String.valueOf(Math.random()).substring(2).substring(0, 3);
		return orderNo;
	}

	private boolean validateICCardRechargeDeatisDist(BigDecimal totalMoneyDist,
			List<ICRechargeApplyDetail> applyDetails) throws Exception {
		if (totalMoneyDist == null) {
			logger.info("================》实际分配总额不能为空！《================");
			throw new BusinessException("实际分配总额不能为空");
		}

		if (applyDetails == null || applyDetails.isEmpty()) {
			logger.info("================》分配详情不能为空！《================");
			throw new BusinessException("分配详情不能为空");
		}
		BigDecimal rechargeDetailTotalDist = BigDecimal.ZERO;
		for (ICRechargeApplyDetail applyDetail : applyDetails) {
			super.notNull(applyDetail.getCardNo(), applyDetail.getCardId(),
					applyDetail.getActualDivMoney());
			if (BigDecimal.ZERO.compareTo(applyDetail.getActualDivMoney()) >= 0) {
				throw new BusinessException("卡号为: " + applyDetail.getCardNo()
						+ " 分配金额非正整数；请确认参数是否正确！");
			}
			rechargeDetailTotalDist = rechargeDetailTotalDist.add(applyDetail
					.getActualDivMoney());
		}
		// 判断充值总数与充值明细总数是否相等
		if (totalMoneyDist.compareTo(rechargeDetailTotalDist) != 0) {
			throw new BusinessException(
					"充值申请（rechargeDetailTotal）中的分配总金额与充值申请明细（ICRechargeApplyDetail）分配总金额不相符！");
		}

		return true;

	}

	private boolean validateICCardRecharge(
			ICCardRechageApplyMetaBean rechargeMetaBean) throws Exception {
		// 验证bean 是否为空
		if (rechargeMetaBean == null
				|| rechargeMetaBean.getRechargeApply() == null
				|| rechargeMetaBean.getRechargeDeatils() == null
				|| rechargeMetaBean.getRechargeDeatils().isEmpty()) {
			return false;
		}
		ICRechargeApply rechargeApply = rechargeMetaBean.getRechargeApply();

		super.notNull(rechargeApply.getApplyPersonId(),
				rechargeApply.getUserId(), rechargeApply.getDataSource(),
				rechargeApply.getCardNum(), rechargeApply.getCaptialMoney(),
				rechargeApply.getTotalMoney(), rechargeApply.getUserRegPhone());
		if (BigDecimal.ZERO.compareTo(rechargeApply.getTotalMoney()) >= 0) {
			throw new BusinessException("充值申请 充值总金额(totalMoney)非正整数；请确认参数是否正确！");
		}
		// 判断申请总金额是否= 每张卡充值金额
		BigDecimal rechargeTotal = rechargeApply.getTotalMoney();
		BigDecimal rechargeDetailTotal = BigDecimal.ZERO;
		for (ICRechargeApplyDetail applyDetail : rechargeMetaBean
				.getRechargeDeatils()) {
			super.notNull(applyDetail.getCardNo(), applyDetail.getCardId(),
					applyDetail.getMoney());
			if (BigDecimal.ZERO.compareTo(applyDetail.getMoney()) >= 0) {
				throw new BusinessException("卡号为: " + applyDetail.getCardNo()
						+ " 充值金额非正整数；请确认参数是否正确！");
			}
			rechargeDetailTotal = rechargeDetailTotal.add(applyDetail
					.getMoney());
		}
		// 判断充值总数与充值明细总数是否相等
		if (rechargeTotal.compareTo(rechargeDetailTotal) != 0) {
			throw new BusinessException(
					"充值申请（rechargeDetailTotal）中的总金额与充值申请明细（ICRechargeApplyDetail）总金额不相符！");
		}

		if (rechargeApply.getCreditNum() != null) {
			if (BigDecimal.ZERO.compareTo(rechargeApply.getCreditNum()) > 0) {
				throw new BusinessException(
						"充值申请 充值使用积分(creditNum)非正整数；请确认参数是否正确！");
			}

		}

		if (rechargeApply.getCreditNum() != null) {
			if (BigDecimal.ZERO.compareTo(rechargeApply.getCreditNum()) > 0) {
				throw new BusinessException(
						"充值申请 充值使用积分(creditNum)非正整数；请确认参数是否正确！");
			}

		}
		BigDecimal allMoney = new BigDecimal("0.00");
		if (rechargeApply.getCreditMoney() != null) {
			allMoney = allMoney.add(rechargeApply.getCreditMoney());
		}

		if (rechargeApply.getCaptialMoney() != null) {
			allMoney = allMoney.add(rechargeApply.getCaptialMoney());
		}

		if (allMoney.compareTo(rechargeApply.getTotalMoney()) != 0) {
			throw new BusinessException("充值申请金额不相等；请确认输入的金额是否正确！");
		}
		return true;
	}

	@Override
	public String applyRechargeForManagerApp(ICCardRechageApplyMetaBean model)
			throws BusinessException {
		logger.info("================>开始  生成业务订单号 <==============");
		String reuslt = "";
		String applyId = null;
		String orderNo = null;
		ICRechargeApply rechargeApply = model.getRechargeApply();
		List<ICRechargeApplyDetail> applyDetails = model.getRechargeDeatils();
		String payOrderNo = null;
		try {
			// 参数校验判断
			if (!validateICCardRecharge(model)) {
				logger.info("================>输入model未校验通过！参数错误，请检查输入参数!<==============");
				throw new BusinessException("输入model未校验通过！参数错误，请检查输入参数!");
			}
			// 订单
			orderNo = buildRechargeOrderNo();

			rechargeApply
					.setPayWay(ICCardRechargeCons.PAY_ORDER_PAY_WAY_ACCOUNT);
			rechargeApply
					.setState(ICCardRechargeCons.RECHARGE_APPLY_STATE_DISTR_WAITING);
			rechargeApply.setOrderNo(orderNo);

			if (StringUtils.isNotEmpty(rechargeApply.getId())) {
				return rechargeApply.getId();
			}

			// model.getRechargeApply().setId(applyId);
			rechargeApply.setStepNo("1");

			Account account = payService.queryAccountByUserId(model
					.getRechargeApply().getUserId());
			String accountNo = "";
			if (account == null) {
				logger.info("================》账户可用余额不足！《================");
				throw new BusinessException("请联系客户，开通资金账户");

			} else {
				accountNo = account.getAccountNo();
			}

			if (account.getUsableBalance() == null
					|| (rechargeApply.getCaptialMoney().divide(new BigDecimal(
							100))).compareTo(new BigDecimal(account
							.getUsableBalance())) > 0) {

				logger.info("================》账户可用余额不足！《================");
				throw new BusinessException("账户可用余额不足");
			}

			// DeductMoneyParams params = new DeductMoneyParams();
			// params.setAccountNo(accountNo);
			// params.setOrderAmount(rechargeApply.getCaptialMoney().toString());
			// params.setUserId(rechargeApply.getUserId());
			// params.setWorkOrderNo(orderNo);
			// String payOrderNo = payService.deductMoney(params);
			FreezeBalanceParams freezeBalanceParams = new FreezeBalanceParams();
			freezeBalanceParams.setAccountNo(accountNo);
			freezeBalanceParams.setOrderAmount(rechargeApply.getCaptialMoney()
					.divide(new BigDecimal(100)).toString());
			freezeBalanceParams.setWorkOrderNo(orderNo);

			payOrderNo = payService.freezeBalance(freezeBalanceParams);
			ICCardPaymentOrder payOrderModel = new ICCardPaymentOrder();
			payOrderModel.setLocalOrderNo(orderNo);
			payOrderModel.setTbaleName(ICCardRechargeCons.PAY_ORDER_TABLE_NAME);
			payOrderModel
					.setStatus(ICCardRechargeCons.RECHARGE_APPLY_STATE_PAY_WAITING);
			payOrderModel.setPayOrderNo(payOrderNo);
			payOrderModel.setPayType(ICCardRechargeCons.RECHARGE_APPLY_FREEZE);
			payOrderModel.setStatus(ICCardRechargeCons.FLAG_SUCC);
			idao.addModel(payOrderModel);

			rechargeApply.setRechargeOrderNo(payOrderNo);
			reuslt = idao.addModel(rechargeApply);

			for (ICRechargeApplyDetail applyDetail : applyDetails) {
				applyDetail.setApplyId(reuslt);
			}

			idao.addModelBatch(applyDetails);

		} catch (BusinessException e) {
			throw e;
		} catch (YpptGatewayException e) {
			logger.error("网关异常：" + e.getErrorcode() + ":" + e.getMessage());
			throw new YpptGatewayException(e.getErrorcode(), e.getMessage());
		} catch (Exception e) {
			logger.error("提交充值申请异常！", e);
			throw new BusinessException("提交充值申请异常！", e);
		} finally {

			ICPaymentOrderMongoDBBean mongoDBBean = new ICPaymentOrderMongoDBBean();
			mongoDBBean.setPayType("冻结");
			if (!StringUtils.isEmpty(orderNo)) {
				mongoDBBean.setLocalOrderNo(orderNo);
			}

			mongoDBBean.setCreditTime(System.currentTimeMillis());
			mongoDBBean.setTradeMoney(rechargeApply.getCaptialMoney());
			// 积分冻结成功
			if (!StringUtils.isEmpty(payOrderNo)) {
				mongoDBBean.setPayOrderNo(payOrderNo);
				mongoDBBean.setStatus(ICCardRechargeCons.FLAG_SUCC);
			} else {
				mongoDBBean.setStatus(ICCardRechargeCons.FLAG_FAIL);
			}

			try {
				mongoService.save(mongoDBBean, WriteConcern.FSYNC_SAFE);
			} catch (Exception e) {
				logger.error("MongoDB保存旺金币冻结订单异常！", e);
				throw new BusinessException("MongoDB保存旺金币冻结订单异常！", e);
			}

			try {
				RechargeApplyMongoDBBean applyMongoDBBean = new RechargeApplyMongoDBBean();
				RechargeDetailMongoDBbean detailMongoDBbean = new RechargeDetailMongoDBbean();
				PropertyUtils.copyProperties(applyMongoDBBean, rechargeApply);
				mongoService.save(applyMongoDBBean, WriteConcern.FSYNC_SAFE);

				if (StringUtils.isEmpty(rechargeApply.getId())) {
					applyId = UUID.randomUUID().toString();
				}
				for (ICRechargeApplyDetail applyDetail : model
						.getRechargeDeatils()) {
					applyDetail.setApplyId(applyId);
					PropertyUtils
							.copyProperties(detailMongoDBbean, applyDetail);
					mongoService.save(detailMongoDBbean,
							WriteConcern.FSYNC_SAFE);
				}
			} catch (Exception e) {
				logger.error("MongoDB保存单异常！", e);
				throw new BusinessException("MongoDB保存订单异常！", e);
			}
		}
		logger.info("================>结束后台卡申请<==============");
		return reuslt;
	}

	private void operateUserStat(ICRechargeApply model) throws Exception {
		// 判断用户统计信息是否存在
		UserStatExampleExtended userStatExampleExtended = new UserStatExampleExtended();
		userStatExampleExtended.createCriteria().andUserIdEqualTo(
				model.getUserId());
		List<UserStat> userStats = idao.getModels(userStatExampleExtended);

		// 用户统计信息不存在，创新用户统计信息
		if (userStats == null || userStats.isEmpty()) {
			addUserStat(model);
		} else {

			// 用户信息存在，更新用户信息表
			updateUserStat(model, userStats.get(0));
		}
	}

	private void updateUserStat(ICRechargeApply model, UserStat userStatModel)
			throws Exception {

		if (model.getTotalMoney() != null) {
			BigDecimal rechargeMoney = model.getTotalMoney();
			if (userStatModel.getRechargeMoney() != null) {
				rechargeMoney = userStatModel.getRechargeMoney().add(
						model.getTotalMoney());
			}
			userStatModel.setRechargeMoney(rechargeMoney);
		}

		idao.updateModelPart(userStatModel);
	}

	private void addUserStat(ICRechargeApply model) throws Exception {
		UserStat userStat = new UserStat();
		userStat.setId(UUID.randomUUID().toString());
		userStat.setUserId(model.getUserId());
		userStat.setUpdateTime(System.currentTimeMillis());

		if (model.getTotalMoney() != null) {
			userStat.setRechargeMoney(model.getTotalMoney());
		}

		idao.addModel(userStat);
	}

	@Override
	public ICCardRechageApplyExtend applyRechargeForApp(
			ICCardRechageApplyMetaBean model) throws BusinessException {
		logger.info("================>开始  生成业务订单号 <==============");
		ICCardRechageApplyExtend reuslt = new ICCardRechageApplyExtend();
		String freezeNo = null;
		String applyId = null;
		// 订单
		String orderNo = null;
		ICRechargeApply apply = model.getRechargeApply();
		List<ICRechargeApplyDetail> applyDList = model.getRechargeDeatils();
		try {
			// 参数校验判断
			if (!validateICCardRecharge(model)) {
				logger.info("================>输入model未校验通过！参数错误，请检查输入参数!<==============");
				throw new BusinessException("输入model未校验通过！参数错误，请检查输入参数!");
			}

			// 订单
			orderNo = buildRechargeOrderNo();
			apply.setState(ICCardRechargeCons.RECHARGE_APPLY_STATE_PAY_WAITING);
			apply.setOrderNo(orderNo);

			boolean isOnlyCredit = false;
			if (apply.getCreditNum() != null && apply.getTotalMoney() != null) {
				if (apply.getCreditMoney().compareTo(apply.getTotalMoney()) == 0) {
					isOnlyCredit = true;
					apply.setPayWay(ICCardRechargeCons.PAY_ORDER_PAY_WAY_CREDIT);
					apply.setState(ICCardRechargeCons.RECHARGE_APPLY_STATE_DISTR_WAITING);
				}
			}
			reuslt.setOnlyCredit(isOnlyCredit);

			if (StringUtils.isNotEmpty(apply.getId())) {
				ICRechargeApply bean = new ICRechargeApply();
				bean.setId(apply.getId());
				bean = getById(bean);
				reuslt.setRechargeApply(bean);

				return reuslt;
			}

			apply.setStepNo("1");

			applyId = idao.addModel(apply);

			for (ICRechargeApplyDetail applyDetail : applyDList) {
				applyDetail.setApplyId(applyId);
			}
			idao.addModelBatch(applyDList);

			// 旺进币
			if (apply.getCreditNum() != null
					&& apply.getCreditNum().compareTo(BigDecimal.ZERO) > 0) {

				Long creditBalance = creditService.queryBalance(model
						.getRechargeApply().getUserId());
				Long freezeMoney = apply.getCreditNum().longValue();
				if (creditBalance.compareTo(freezeMoney) < 0) {
					logger.info("================>输入的旺金币大于旺金币账户余额！<==============");
					throw new BusinessException("输入的旺金币大于旺金币账户余额！");
				}

				freezeNo = creditService.freeze(apply.getUserId(), freezeMoney,
						apply.getOrderNo());

				ICCardCreditOrder cardCreditOrder = new ICCardCreditOrder();
				cardCreditOrder.setId(UUID.randomUUID().toString());
				cardCreditOrder.setLocalOrderNo(orderNo);
				cardCreditOrder.setPayOrderNo(freezeNo);
				cardCreditOrder
						.setPayType(ICCardRechargeCons.RECHARGE_APPLY_FREEZE);
				cardCreditOrder.setStatus(ICCardRechargeCons.FLAG_SUCC);
				cardCreditOrder.setTbaleName("ICCardCreditOrder");
				idao.addModel(cardCreditOrder);
			}

			reuslt.setRechargeApply(apply);

			if (!isOnlyCredit) {
				ICCardPaymentOrder payOrderModel = new ICCardPaymentOrder();
				payOrderModel.setLocalOrderNo(orderNo);
				payOrderModel
						.setTbaleName(ICCardRechargeCons.PAY_ORDER_TABLE_NAME);
				payOrderModel
						.setStatus(ICCardRechargeCons.RECHARGE_APPLY_STATE_PAY_WAITING);
				idao.addModel(payOrderModel);

				Account account = payService.queryAccountByUserId(model
						.getRechargeApply().getUserId());
				if (account == null) {
					// 创建资金账户
					CreateAccountParams createAccountParams = new CreateAccountParams();
					createAccountParams.setOwnerUserId(apply.getUserId());
					createAccountParams.setMobileNo(apply.getUserRegPhone());
					createAccountParams.setOwnerLoginName(apply
							.getUserLoginName());
					// if(StringUtils.isNotEmpty(apply.get))
					payService.createAccount(createAccountParams);
				}
			}

		} catch (BusinessException e) {
			throw e;
		} catch (YpptGatewayException e) {
			logger.error("网关异常：》》》》》》" + e.getErrorcode() + ":"
					+ e.getMessage());
			throw new YpptGatewayException(e.getErrorcode(), e.getMessage());
		} catch (Exception e) {
			logger.error("创建业务订单号信息异常！", e);
			throw new BusinessException("创建业务订单号信息异常！", e);
		} finally {
			try {

				ICCardCreditOrderMongoDBBean mongoDBBean = new ICCardCreditOrderMongoDBBean();
				mongoDBBean.setPayType("冻结");
				if (!StringUtils.isEmpty(orderNo)) {
					mongoDBBean.setLocalOrderNo(orderNo);
				}

				mongoDBBean.setCreditTime(System.currentTimeMillis());
				mongoDBBean.setTotalMoney(apply.getCreditMoney());
				// 积分冻结成功
				if (!StringUtils.isEmpty(freezeNo)) {
					mongoDBBean.setPayOrderNo(freezeNo);
				} else {
					mongoDBBean.setStatus(ICCardRechargeCons.FLAG_FAIL);
				}

				RechargeApplyMongoDBBean applyMongoDBBean = new RechargeApplyMongoDBBean();
				try {
					PropertyUtils.copyProperties(applyMongoDBBean, apply);
				} catch (Exception e) {
					logger.warn("记录日志时拷贝日志bean发送异常!", e);
				}

				try {
					mongoService.save(mongoDBBean, WriteConcern.FSYNC_SAFE);
				} catch (Exception e) {
					logger.error("MongoDB保存旺金币冻结订单异常！", e);
					throw new BusinessException("MongoDB保存旺金币冻结订单异常！", e);
				}
			} catch (Exception e2) {
				logger.error("MongoDB保存旺金币冻结订单异常！", e2);
			}

		}
		logger.info("================>结束 生成业务订单号<==============");
		return reuslt;
	}

	@Override
	public String callBackPayForApp(String orderNo, String rslt,
			BigDecimal money, String payOrderNo, String payWay)
			throws BusinessException {
		logger.info("================>开始 调用网关，修改订单状态<==============");
		String result = "";
		try {
			if (StringUtils.isEmpty(orderNo)) {
				logger.info("================>业务订单号为空！<==============");
				throw new BusinessException("业务订单号为空！");
			}
			if (StringUtils.isEmpty(rslt)) {
				logger.info("================>支付结果不存在！<==============");
				throw new BusinessException("支付结果不存在！");
			}
			if (money == null) {
				logger.info("================>金额为空<==============");
				throw new BusinessException("金额为空！");
			}
			if (StringUtils.isEmpty(payOrderNo)) {
				logger.info("================>支付订单号为空！<==============");
				throw new BusinessException("支付订单号为空！");
			}
			if (StringUtils.isEmpty(payWay)) {
				logger.info("================>支付方式为空！<==============");
				throw new BusinessException("支付方式为空！");
			}
			ICRechargeApplyExampleExtended applyExampleExtended = new ICRechargeApplyExampleExtended();
			applyExampleExtended.createCriteria().andOrderNoEqualTo(orderNo);
			List<ICRechargeApply> applys = idao.getModels(applyExampleExtended);
			if (applys == null || applys.isEmpty()) {
				logger.error("(申请)订单不存在！");
				throw new BusinessException("(申请)订单不存在！");
			}

			ICRechargeApply apply = applys.get(0);
			ICCardPaymentOrderExampleExtended paymentExampleExtended = new ICCardPaymentOrderExampleExtended();
			paymentExampleExtended.createCriteria().andLocalOrderNoEqualTo(
					orderNo);

			List<ICCardPaymentOrder> cardPaymentOrders = idao
					.getModels(paymentExampleExtended);
			ICCardPaymentOrder cardPaymentOrder = cardPaymentOrders.get(0);
			cardPaymentOrder.setPayOrderNo(payOrderNo);
			cardPaymentOrder
					.setPayType(ICCardRechargeCons.RECHARGE_APPLY_FREEZE);

			String applyStat = "";
			String payOrderStat = "";
			String captialState = "";
			if (ICCardRechargeCons.RECHARGE_CALL_BACK_SUCC.equals(rslt)) {
				applyStat = ICCardRechargeCons.RECHARGE_APPLY_STATE_DISTR_WAITING;
				payOrderStat = ICCardRechargeCons.FLAG_SUCC;
				captialState = ICCardRechargeCons.RECHARGE_APPLY_FREEZE_SUCC;
			} else {
				applyStat = ICCardRechargeCons.RECHARGE_APPLY_STATE_PAY_FAIL;
				payOrderStat = ICCardRechargeCons.FLAG_FAIL;
				captialState = ICCardRechargeCons.RECHARGE_APPLY_FREEZE_FAIL;

			}
			cardPaymentOrder.setStatus(payOrderStat);
			apply.setCaptialState(captialState);
			apply.setState(applyStat);

			idao.updateModelAll(cardPaymentOrder);
			apply.setPayWay(payWay);
			apply.setRechargeOrderNo(payOrderNo);
			update(apply);

			if (cardPaymentOrders == null || cardPaymentOrders.isEmpty()) {
				logger.error("(支付)订单不存在！");
				throw new BusinessException("(支付)订单不存在！");
			}

			result = payOrderNo;
		} catch (BusinessException e) {
			throw e;
		} catch (YpptGatewayException e) {
			logger.error("网关异常：" + e.getErrorcode() + ":" + e.getMessage());
			throw new YpptGatewayException(e.getErrorcode(), e.getMessage());
		} catch (Exception e) {
			logger.error("调用网关，修改订单状态异常！", e);
			throw new BusinessException("调用网关，修改订单状态异常！", e);
		}
		logger.info("================>结束 调用网关，修改订单状态<==============");
		return result;
	}

	public Long startTime() {
		Calendar c1 = new GregorianCalendar();
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		return c1.getTime().getTime();

	}

	public Long endTime() {

		Calendar c2 = new GregorianCalendar();
		c2.set(Calendar.HOUR_OF_DAY, 23);
		c2.set(Calendar.MINUTE, 59);
		c2.set(Calendar.SECOND, 59);
		return c2.getTime().getTime();

	}

	@Override
	public BigDecimal sumRecharge(Map<String, Object> params)
			throws BusinessException {
		logger.info("================>开始计算充值总额<==============");
		BigDecimal result = null;
		try {
			if (params == null) {
				logger.info("================》params(查询参数)不能为空！《================");
				throw new BusinessException("params(查询参数)不能为空");
			}
			if (params.get("userId") == null || "".equals(params.get("userId"))) {
				logger.info("================》用户id不能为空！《================");
				throw new BusinessException("用户id不能为空");
			}

			result = (BigDecimal) queryObjectBySQL(
					"TB_IC_CARD_RECHARGE_APPLY_EXTEND.abatorgenerated_sumRecharge",
					params);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("获取充值记录信息总数异常！", e);
			throw new BusinessException("获取充值记录信息总数异常！", e);

		}
		logger.info("================>结束计算充值总额<==============");
		return result;

	}
}
