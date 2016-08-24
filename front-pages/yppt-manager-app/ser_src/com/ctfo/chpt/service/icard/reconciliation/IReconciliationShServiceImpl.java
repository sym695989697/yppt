package com.ctfo.chpt.service.icard.reconciliation;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.ServiceImpl;
import com.ctfo.base.service.beans.SimpleCode;
import com.ctfo.base.service.simplecode.ICodeService;
import com.ctfo.base.util.ExcelDownLoadOrUpLoadUtil;
import com.ctfo.chpt.bean.icard.reconciliation.vo.ICCardReconciliationByShVO;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Converter;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.excel.inter.ExcelUtilImpl;
import com.ctfo.excel.inter.ExcelUtilInter;
import com.ctfo.excel.parse.ExpObj;
import com.ctfo.excel.util.ExcelUtil;
import com.ctfo.gatewayservice.interfaces.service.IUserService;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.beans.ICCardExampleExtended;
import com.ctfo.yppt.baseservice.card.cons.ICCardCons;
import com.ctfo.yppt.baseservice.card.intf.IICCardManager;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoHistory;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoHistoryExampleExtended;
import com.ctfo.yppt.baseservice.trade.intf.IICCardTradeInfoHistoryManager;

@Service(value = "iReconciliationShService")
public class IReconciliationShServiceImpl extends ServiceImpl implements IReconciliationShService {
	private static Log logger = LogFactory.getLog(IReconciliationShServiceImpl.class);

	private IICCardTradeInfoHistoryManager iICCardTradeInfoHistoryManager = null;
	private IICCardManager iICCardManager = null;
	private IUserService userService = null;
	@Resource(name = "codeService", description = "副卡管理service")
	private ICodeService codeService;

	@Override
	public PaginationResult<?> queryReconciliationListPage(DynamicSqlParameter requestParam) {
		PaginationResult<?> result = new PaginationResult<ICCardTradeInfoHistory>();
		try {
			ICCardTradeInfoHistoryExampleExtended example = new ICCardTradeInfoHistoryExampleExtended();
			ICCardTradeInfoHistoryExampleExtended.Criteria criteria = (ICCardTradeInfoHistoryExampleExtended.Criteria) Converter.paramToExampleExtendedCriteriaNoException(requestParam, example);
			Map<String, String> equal = requestParam.getEqual();
			Map<String, String> like = requestParam.getLike();
			Map<String, String> endWith = requestParam.getEndwith();
			Map<String, String> startwith = requestParam.getStartwith();
			if (equal != null) {
				String tradeType = equal.get("tradeType");
				if (vailidateParams(tradeType)) {
					criteria.andTradeTypeEqualTo(tradeType);
				}
				String cardType = equal.get("cardType");
				if (vailidateParams(cardType)) {
					criteria.andCardTypeEqualTo(cardType);
				}
			}
			if (like != null) {
				// 交易地址模糊查询
				String address = like.get("tradeAdress");
				if (vailidateParams(address)) {
					criteria.andTradeAdressLike(address);
				}

				// 注册手机号模糊查询
				String userRegPhone = like.get("userRegPhone");
				if (vailidateParams(userRegPhone)) {
					criteria.andUserRegPhoneLike(userRegPhone);
				}

				// 主卡卡号模糊查询
				String mainCardNum = like.get("mainCardNum");
				if (vailidateParams(mainCardNum)) {
					criteria.andMainCardNumLike(mainCardNum);
				}
				// 会员名称模糊查询
				String userName = like.get("userName");
				if (vailidateParams(userName)) {
					criteria.andUserNameLike(userName);
				}

			}
			if (startwith != null && endWith != null) {
				String start = startwith.get("tradeTime");
				String end = startwith.get("tradeTime");
				if (vailidateParams(start) && vailidateParams(end)) {
					criteria.andTradeTimeBetween(Long.parseLong(start), Long.parseLong(end));
				}
			}

			result = getIICCardTradeInfoHistoryManager().paginate(example);
		} catch (Exception e) {
			logger.error("分页查询对账列表异常", e);
		}
		return result;
	}

	@Override
	public String importReconciliationFile(InputStream excelFile, String fileName, final HttpServletRequest request) {
		File file = new File(fileName);
		ExcelDownLoadOrUpLoadUtil.inputstreamtofile(excelFile, file);
		String result = "0";
		try {
			logger.debug("开始导入对账文件...");
			ExcelUtilInter<ICCardReconciliationByShVO> imp = new ExcelUtilImpl<ICCardReconciliationByShVO>(ICCardReconciliationByShVO.class);
			Map<String, Map<String, String>> map = this.getImportReconciliationModelMap();
			List<ExpObj> expObjs = new ExcelUtil().getExpObjs(ICCardReconciliationByShVO.class, 2, "IMP");
			// 排序定义
			List<Integer> sort = null;
			// 定义的方法
			List<Method> methods = new ArrayList<Method>(expObjs.size());
			// 定义执行方法的对象
			List<Object> objs = new ArrayList<Object>(expObjs.size());
			for (int i = 0; i < expObjs.size(); i++) {
				objs.add(map.get(expObjs.get(i).getField().getName()));
				methods.add(null);
			}
			// 设置
			expObjs = new ExcelUtil().setExpObjMethodObjects(expObjs, methods, objs, sort);
			boolean check = imp.checkExcelModel(ICCardReconciliationByShVO.class, expObjs, file);// 检测导入模版
			final HttpSession finalSession = request.getSession();
			finalSession.setAttribute(ICCardCons.RECONCILIATION_SH_PROCESS_KEY, 0);
			if (!check) {
				finalSession.removeAttribute(ICCardCons.RECONCILIATION_SH_PROCESS_KEY);// 移除导入进度
				finalSession.setAttribute(ICCardCons.RECONCILIATION_SH_MESSAGE_KEY, "导入模版不正确，请重新下载导入模版！");
				logger.warn("导入模版不正确，请重新下载导入模版！");
				return result;
			}
			Map<Collection<ICCardReconciliationByShVO>, String> coll = imp.importExcel(file, expObjs, 2);
			Set<Collection<ICCardReconciliationByShVO>> set = coll.keySet();
			for (Collection<ICCardReconciliationByShVO> object : set) {
				final List<ICCardReconciliationByShVO> cardVoList = (List<ICCardReconciliationByShVO>) object;
				/*****
				 * 导入前先校验必填项
				 */
				StringBuffer validateString = new StringBuffer("");
				for (int j = 0; j < cardVoList.size(); j++) {
					for (int i = 0; i < expObjs.size(); i++) {
						ExpObj exObj = expObjs.get(i);
						String title = exObj.getTitle();
						if (title.indexOf("[M]") != -1) {
							Object value = ExcelUtil.invokeMethod(exObj.getAllgetPathMethod(), cardVoList.get(j));
							if (value == null) {
								if (StringUtils.isBlank(validateString.toString())) {
									validateString.append("<br/>导入前校验：<br/>");
								}
								validateString.append("[卡号]：").append(cardVoList.get(j).getCardNo() == null ? "[空]" : cardVoList.get(j).getCardNo()).append("第 ").append(ExcelUtil.getExcelColumnLabel(exObj.getIndex())).append(" 列为[空]，标题“").append(title).append("”带“[M]”必须填写才能继续进行导入！<br/>");
							} else if (value instanceof List) {
								List t = (List) value;
								for (int k = 0; k < t.size(); k++) {
									if (t.get(k) == null) {
										if (StringUtils.isBlank(validateString.toString())) {
											validateString.append("<br/>导入前校验：<br/>");
										}
										validateString.append("[卡号]：").append(cardVoList.get(j).getCardNo() == null ? "[空]" : cardVoList.get(j).getCardNo()).append("第 ").append(ExcelUtil.getExcelColumnLabel(exObj.getIndex())).append(" 列存在[空]，标题“").append(title).append("”带“[M]”必须填写才能继续进行导入！<br/>");
									}
								}
							}
						}
					}
				}
				if (StringUtils.isNotBlank(validateString.toString())) {
					finalSession.removeAttribute(ICCardCons.RECONCILIATION_SH_PROCESS_KEY);// 移除导入进度
					finalSession.setAttribute(ICCardCons.RECONCILIATION_SH_MESSAGE_KEY, validateString.toString());
					logger.warn(validateString.toString().replaceAll("<br/>", "\r\n"));
					return result;
				}
				finalSession.setAttribute(ICCardCons.RECONCILIATION_SH_PROCESS_KEY + "_total", new Double(cardVoList.size()));
				// 开启异步线程调用后台导入接口

				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						StringBuffer sb = new StringBuffer();
						StringBuffer sb_temp = new StringBuffer();

						try {
							int successCount = 0;
							int failCount = 0;
							for (int i = 0; i < cardVoList.size(); i++) {
								ICCardReconciliationByShVO vo = cardVoList.get(i);
								String cardNoStr = vo.getCardNo();
								SimpleCode typeCode = codeService.queryByCode("CARD-TYPE-02");
								try {
									Double totalSize = (Double) finalSession.getAttribute(ICCardCons.RECONCILIATION_SH_PROCESS_KEY + "_total");
									Double process = new Double(i) / totalSize * 100;
									finalSession.setAttribute(ICCardCons.RECONCILIATION_SH_PROCESS_KEY, process);// session存储导入进度

									// 卡号
									ICCardTradeInfoHistory iCCardTradeInfoHistory = new ICCardTradeInfoHistory();
									ICCardExampleExtended extended = new ICCardExampleExtended();
									ICCardExampleExtended.Criteria criteria = extended.createCriteria();

									criteria.andCardNumberEqualTo(cardNoStr);
									criteria.andCardTypeEqualTo("CARD-TYPE-02");
									List<ICCard> list = getiICCardManager().getList(extended);
									if (list != null && list.size() > 0) {
										ICCard iccard = list.get(0);
										// 导入记录未绑定用户,忽略
										if (StringUtils.isBlank(iccard.getUserId())) {
											sb_temp.append(typeCode.getName()).append("卡号：").append(cardNoStr).append("导入失败，错误信息：未开卡；</br>");
											failCount++;
											continue;
										}

										// 交易时间为空时或交易时间格式不正确，忽略
										String tradeTimeStr = vo.getTradeTime();
										// 交易时间为空，忽略
										if (StringUtils.isBlank(tradeTimeStr)) {
											sb_temp.append(typeCode.getName()).append("卡号：").append(cardNoStr).append("导入失败，错误信息：交易时间为空；</br>");
											failCount++;
											continue;
										}
										if ("".equals(tradeTimeStr) || "-".equals(tradeTimeStr)) {
											sb_temp.append(typeCode.getName()).append("卡号：").append(cardNoStr).append("导入失败，错误信息：交易时间格式不正确；</br>");
											failCount++;
											continue;
										}

										// 卡号和时间查询存在重复时，忽略
										long tradeTimeLong = com.ctfo.utils.DateUtils.date2TimeStamp(tradeTimeStr, com.ctfo.utils.DateUtils.FMT_DATETIME);
										ICCardTradeInfoHistoryExampleExtended example = new ICCardTradeInfoHistoryExampleExtended();
										ICCardTradeInfoHistoryExampleExtended.Criteria criteriaHis = example.createCriteria();
										criteriaHis.andCardNoEqualTo(iccard.getCardNumber());
										criteriaHis.andTradeTimeEqualTo(tradeTimeLong);
										List resultHis = getIICCardTradeInfoHistoryManager().getList(example);
										if (resultHis != null && resultHis.size() > 0) {
											sb_temp.append(typeCode.getName()).append("卡号：").append(cardNoStr).append("导入失败，错误信息：交易记录已存在；</br>");
											failCount++;
											continue;
										}

										// 金额为空时，忽略
										if (vo.getTradeMoney() == null) {
											sb_temp.append(typeCode.getName()).append("卡号：").append(cardNoStr).append("导入失败，错误信息：交易金额为空；</br>");
											failCount++;
											continue;
										}
										// 交易地点为空,忽略
										if (vo.getTradeAdress() == null) {
											sb_temp.append(typeCode.getName()).append("卡号：").append(cardNoStr).append("导入失败，错误信息：交易地点为空；</br>");
											failCount++;
											continue;
										}

										// 业务类型为空,忽略
										if (vo.getTradeType() == null) {
											sb_temp.append(typeCode.getName()).append("卡号：").append(cardNoStr).append("导入失败，错误信息：业务类型为空；</br>");
											failCount++;
											continue;
										}

										iCCardTradeInfoHistory.setTradeTime(tradeTimeLong);
										iCCardTradeInfoHistory.setCardNo(vo.getCardNo());
										iCCardTradeInfoHistory.setCardId(iccard.getId());
										iCCardTradeInfoHistory.setCardType(iccard.getCardType());
										iCCardTradeInfoHistory.setCreateTime(System.currentTimeMillis());
										iCCardTradeInfoHistory.setVehicleNo(iccard.getVehicleNo());
										iCCardTradeInfoHistory.setMainCardId(iccard.getParentId());
										iCCardTradeInfoHistory.setMainCardNum(iccard.getParentCardNumber());
										iCCardTradeInfoHistory.setModifiedTime(System.currentTimeMillis());
										String oilnumStr = vo.getOilNum() == null ? "0" : vo.getOilNum();
										iCCardTradeInfoHistory.setProductNum(Long.parseLong(new BigDecimal(oilnumStr).multiply(new BigDecimal(100)).toBigInteger().toString()));
										iCCardTradeInfoHistory.setOpenCardOfficeCode(iccard.getOpencardofficeid());
										BigDecimal price = new BigDecimal(vo.getProductPrice() == null ? "0" : vo.getProductPrice());
										iCCardTradeInfoHistory.setProductPrice(price.multiply(new BigDecimal(100)));
										iCCardTradeInfoHistory.setTradeAdress(vo.getTradeAdress());
										BigDecimal money = new BigDecimal(vo.getTradeMoney() == null ? "0" : vo.getTradeMoney());
										iCCardTradeInfoHistory.setTradeMoney(money.multiply(new BigDecimal(100)));
										iCCardTradeInfoHistory.setTradeType(vo.getTradeType());
										iCCardTradeInfoHistory.setUserId(iccard.getUserId());
										iCCardTradeInfoHistory.setUserName(iccard.getUserName());
										BigDecimal balance = new BigDecimal(vo.getBalance() == null ? "0" : vo.getBalance());
										iCCardTradeInfoHistory.setCardBalance(balance.multiply(new BigDecimal(100)));
										iCCardTradeInfoHistory.setUserRegPhone(iccard.getUserRegPhone());
										// iCCardTradeInfoHistory.setOilCode(vo.getProductType());
										iCCardTradeInfoHistory.setProductName(vo.getOilName());
										// iCCardTradeInfoHistory.setProductTotalPrice(productTotalPrice);
										iCCardTradeInfoHistory.setTradeState("IC-TRADE-SUCCESS");
										try {
											getIICCardTradeInfoHistoryManager().addTradeInfoHistory(iCCardTradeInfoHistory);
											successCount++;
										} catch (Exception e) {
											sb_temp.append(typeCode.getName()).append("卡号：").append(cardNoStr).append("导入失败，错误信息：调用后台接口添加交易记录异常；</br>");
											logger.error("导入出现异常", e);
											failCount++;
										}
									} else {
										sb_temp.append(typeCode.getName()).append("卡号：").append(cardNoStr).append("导入失败，错误信息：卡号不存在；</br>");
										failCount++;
										continue;
									}

								} catch (Exception e) {
									failCount++;
									sb_temp.append(typeCode.getName()).append("卡号：").append(cardNoStr).append("导入失败，错误信息：").append(e.getMessage()).append("；</br>");
									logger.error("对账数据拷贝异常", e);
								}
							}
							sb.append("共").append(failCount + successCount).append("条;").append("成功").append(successCount).append("条，失败").append(failCount).append("条!<br/>").append(sb_temp);
							logger.info(sb.toString().replaceAll("<br/>", "\r\n"));
						} catch (Exception e) {
							sb.append("<br/>导入异常。");
							logger.error("调用后台导入对账接口异常", e);
						} finally {
							finalSession.setAttribute(ICCardCons.RECONCILIATION_SH_MESSAGE_KEY, sb.toString());
							finalSession.removeAttribute(ICCardCons.RECONCILIATION_SH_PROCESS_KEY);// 移除导入进度
							finalSession.removeAttribute(ICCardCons.RECONCILIATION_SH_PROCESS_KEY + "_total");// 移除导入进度
						}
					}

				});
				thread.start();
			}

			file.delete();
		} catch (Exception e) {
			result = "1";
			logger.error("导入IC卡副卡异常", e);
		}
		return result;
	}

	@Override
	public Map<String, Map<String, String>> getImportReconciliationModelMap() {
		Map<String, Map<String, String>> mapEnum = new HashMap<String, Map<String, String>>();
		try {
			List<SimpleCode> list = codeService.querySimpleCodeByTypeCode("IC-TRADE-TYPE");
			Map<String, String> cardTypeMap = new HashMap<String, String>();
			for (int i = 0; list != null && i < list.size(); i++) {
				cardTypeMap.put(list.get(i).getCode(), list.get(i).getName());
			}
			mapEnum.put("tradeType", cardTypeMap);
		} catch (Exception e) {
			logger.error("模版map查询异常!", e);
		}
		return mapEnum;
	}

	public IICCardTradeInfoHistoryManager getIICCardTradeInfoHistoryManager() {
		if (iICCardTradeInfoHistoryManager == null)
			iICCardTradeInfoHistoryManager = (IICCardTradeInfoHistoryManager) ServiceFactory.getFactory().getService(IICCardTradeInfoHistoryManager.class);
		return iICCardTradeInfoHistoryManager;
	}

	public IUserService getUserService() {
		if (userService == null)
			userService = (IUserService) ServiceFactory.getFactory().getService(IUserService.class);
		return userService;
	}

	public IICCardTradeInfoHistoryManager getiICCardTradeInfoHistoryManager() {
		return iICCardTradeInfoHistoryManager;
	}

	public IICCardManager getiICCardManager() {
		if (iICCardManager == null)
			iICCardManager = (IICCardManager) ServiceFactory.getFactory().getService(IICCardManager.class);
		return iICCardManager;

	}

	@Override
	public List<ICCardTradeInfoHistory> getIcCardTradeInfoHistorieList(String cardNo, Long tradeTime) throws Exception {
		ICCardTradeInfoHistoryExampleExtended example = new ICCardTradeInfoHistoryExampleExtended();
		example.createCriteria().andCardNoEqualTo(cardNo).andTradeTimeEqualTo(tradeTime);
		return getIICCardTradeInfoHistoryManager().getList(example);
	}

	public static void main(String[] args) {
		double a = 1.01;
		long b = (int) a * 100;
		System.out.println(b);

		BigDecimal aa = new BigDecimal(1.01);

	}
}
