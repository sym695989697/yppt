package com.ctfo.chpt.service.iccard.cardmanager;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.ctfo.base.util.ExcelPutIntoMongoDBImpl;
import com.ctfo.catchservice.bean.JResult;
import com.ctfo.catchservice.interfaces.external.ICatchService;
import com.ctfo.chpt.bean.iccard.vo.CardDetail;
import com.ctfo.chpt.bean.iccard.vo.ICCardApplyVO;
import com.ctfo.chpt.bean.iccard.vo.ICCardVO;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Converter;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.uaa.dao.beans.UAAUser;
import com.ctfo.csm.uaa.intf.support.Operator;
import com.ctfo.excel.inter.ExcelUtilImpl;
import com.ctfo.excel.inter.ExcelUtilInter;
import com.ctfo.excel.parse.ExpObj;
import com.ctfo.excel.util.ExcelUtil;
import com.ctfo.file.bean.ExcelBean;
import com.ctfo.gatewayservice.interfaces.bean.user.CRMUserBean;
import com.ctfo.gatewayservice.interfaces.service.IUserService;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.beans.ICCardApply;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyDetail;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyProcessLog;
import com.ctfo.yppt.baseservice.card.beans.ICCardExampleExtended;
import com.ctfo.yppt.baseservice.card.beans.ICCardMain;
import com.ctfo.yppt.baseservice.card.beans.ICCardMainExampleExtended;
import com.ctfo.yppt.baseservice.card.cons.ICCardCons;
import com.ctfo.yppt.baseservice.card.intf.IICCardApplyManager;
import com.ctfo.yppt.baseservice.card.intf.IICCardMainManager;
import com.ctfo.yppt.baseservice.card.intf.IICCardManager;
import com.ctfo.yppt.bean.ICCardApplyMetaBean;

@Service(value = "iCCardService")
public class ICCardServiceImpl extends ServiceImpl implements IICCardService {
	private static Log logger = LogFactory.getLog(ICCardServiceImpl.class);

	private IICCardManager iccardManager = null;
	private IICCardMainManager iccardMainManager = null;
	private IICCardApplyManager iccardApplyManager = null;
	private IUserService userService = null;

	@Resource(name = "codeService", description = "副卡管理service")
	private ICodeService codeService;

	/**
	 * 数据抓取接口
	 */
	private ICatchService iCatchService;

	public ICatchService getICatchService() {
		if (iCatchService != null)
			iCatchService = (ICatchService) ServiceFactory.getFactory()
					.getService(ICatchService.class);
		return iCatchService;
	}

	@Override
	public PaginationResult<?> queryViceCardListPage(
			DynamicSqlParameter requestParam) {
		PaginationResult result = new PaginationResult();
		try {
			String isBanding = null;
			if (requestParam != null && requestParam.getEqual() != null) {
				isBanding = requestParam.getEqual().get("isBanding");
				requestParam.getEqual().remove("isBanding");
			}

			ICCardExampleExtended example = new ICCardExampleExtended();
			example.setOrderByClause("open_card_time desc nulls last,created_time desc nulls last");
			ICCardExampleExtended.Criteria criteria = (ICCardExampleExtended.Criteria) Converter
					.paramToExampleExtendedCriteriaNoException(requestParam,
							example);
			if (StringUtils.isNotBlank(isBanding)) {
				if ("1".equals(isBanding)) {
					criteria.andUserIdIsNotNull();
				} else {
					criteria.andUserIdIsNull();
				}
			}
			result = getICCardManager().paginate(example);
			for (int i = 0; result != null && result.getData() != null
					&& i < result.getData().size(); i++) {
				ICCard card = (ICCard) result.getData().get(i);
				if(StringUtils.isBlank(card.getParentCardNumber())){
					ICCardMain main = new ICCardMain();
					main.setId(card.getParentId());
					main = this.getICCardMainManager().getById(main);
					if (main != null) {
						card.setParentCardNumber(main.getCardNumber());
					}
				}
				UAAUser user = null;
				UAAUser modifyUser = null;

				// modify by wangpeng 20150203 减少调用统一认证次数
				if (card.getCtrdituser() == null
						|| card.getCtrdituser().equalsIgnoreCase("default")) {
				} else {
					try {
						user = getUserService().queryUaaUserById(
								card.getCtrdituser(), new Operator());
					} catch (Exception e) {
						// TODO: handle exception
					} finally {
						card.setCtrdituser(user == null ? null : user
								.getUserName());
					}

				}
				if (card.getModifinguser() == null) {
				} else {
					try {
						modifyUser = getUserService().queryUaaUserById(
								card.getModifinguser(), new Operator());
					} catch (Exception e) {
						// TODO: handle exception
					} finally {
						card.setModifinguser(modifyUser == null ? null
								: modifyUser.getUserName());
					}
				}

			}
		} catch (Exception e) {
			logger.error("分页查询ic卡列表异常", e);
		}
		return result;
	}

	@Override
	public ICCard queryViceCardById(Object model) {
		ICCard icCard = null;
		UAAUser user = null;
		UAAUser modifyUser = null;
		try {
			icCard = getICCardManager().getById((ICCard) model);
			if(StringUtils.isBlank(icCard.getParentCardNumber())){
				ICCardMain main = new ICCardMain();
				main.setId(icCard.getParentId());
				main = this.getICCardMainManager().getById(main);
				if (main != null) {
					icCard.setParentCardNumber(main.getCardNumber());
				}
			}
			user = getUserService().queryUaaUserById(icCard.getCtrdituser(),
					new Operator());
			modifyUser = getUserService().queryUaaUserById(
					icCard.getModifinguser(), new Operator());
		} catch (Exception e) {
			logger.error("根据id查询ic卡异常", e);
		} finally {
			icCard.setCtrdituser(user == null ? null : user.getUserName());
			icCard.setModifinguser(modifyUser == null ? null : modifyUser
					.getUserName());
		}
		return icCard;
	}

	@Override
	public List queryMainCardByCardAreaCode(DynamicSqlParameter requestParam) {
		List list = new ArrayList();
		try {
			ICCardMainExampleExtended example = new ICCardMainExampleExtended();
			ICCardMainExampleExtended.Criteria criteria = (ICCardMainExampleExtended.Criteria) Converter
					.paramToExampleExtendedCriteriaNoException(requestParam,
							example);
			list = getICCardMainManager().getList(example);
		} catch (Exception e) {
			logger.error("查询ic卡主卡列表异常", e);
		}
		return list;
	}

	@Override
	public void updateViceCard(Object model) {
		try {
			ICCard icCard = (ICCard) model;
			getICCardManager().update(icCard);
		} catch (Exception e) {
			logger.info("修改副卡信息异常！");
			e.printStackTrace();
		}
	}

	@Override
	public List queryViceCardList(DynamicSqlParameter requestParam) {
		List list = new ArrayList();
		try {
			ICCardExampleExtended example = new ICCardExampleExtended();
			ICCardExampleExtended.Criteria criteria = (ICCardExampleExtended.Criteria) Converter
					.paramToExampleExtendedCriteriaNoException(requestParam,
							example);
			list = getICCardManager().getList(example);
		} catch (Exception e) {
			logger.error("查询ic卡列表异常", e);
		}
		return list;
	}

	@Override
	public String importIcCard(InputStream excelFile, String fileName,
			final HttpServletRequest request) {
		File file = new File(fileName);
		ExcelDownLoadOrUpLoadUtil.inputstreamtofile(excelFile, file);
		String result = "0";
		try {
			logger.debug("开始导入IC卡副卡...");
			ExcelUtilInter<ICCardVO> imp = new ExcelUtilImpl<ICCardVO>(
					ICCardVO.class);

			List<ExpObj> expObjs = new ExcelUtil().getExpObjs(ICCardVO.class,
					2, "IMP");
			// 排序定义
			List<Integer> sort = null;
			// 定义的方法
			List<Method> methods = new ArrayList<Method>(expObjs.size());
			// 定义执行方法的对象
			List<Object> objs = new ArrayList<Object>(expObjs.size());
			for (int i = 0; i < expObjs.size(); i++) {
				objs.add(null);
				methods.add(null);
			}
			// 设置
			expObjs = new ExcelUtil().setExpObjMethodObjects(expObjs, methods,
					objs, sort);
			boolean check = imp.checkExcelModel(ICCardVO.class, expObjs, file);// 检测导入模版
			final HttpSession finalSession = request.getSession();
			finalSession.setAttribute(ICCardCons.BLANK_CARD_IMPORT_PROCESS_KEY,
					0);
			if (!check) {
				finalSession
						.removeAttribute(ICCardCons.BLANK_CARD_IMPORT_PROCESS_KEY);// 移除导入进度
				finalSession.setAttribute(
						ICCardCons.BLANK_CARD_IMPORT_MESSAGE_KEY,
						"导入模版不正确，请重新下载导入模版！");
				logger.warn("导入模版不正确，请重新下载导入模版！");
				return result;
			}
			Map<Collection<ICCardVO>, String> coll = imp.importExcel(file,
					expObjs, 2);
			Set<Collection<ICCardVO>> set = coll.keySet();
			final String cardType = request.getParameter("cardType_val");// 卡类型（码表）
			final String cardTypeName = request.getParameter("cardType");// 卡类型名称
			final String opencardofficecode = request
					.getParameter("opencardofficecode_val");// 发卡机构
															// （码表值）
			final String opencardofficeName = request
					.getParameter("opencardofficecode");// 发卡机构
														// 名称
			final String cardAreaCode = request.getParameter("cardAreaCode");// 发卡地区（码表值）
			final String cardAreaName = request.getParameter("cardAreaName");// 发卡地区
																				// 名称
			final String parentId = request.getParameter("mainCard_val");// 父卡ID
			final String parentCard = request.getParameter("mainCard");// 父卡卡号
			final String user = ((Index) request.getSession().getAttribute(
					Converter.SESSION_INDEX)).getUserId();
			for (Collection<ICCardVO> object : set) {
				final List<ICCardVO> cardVoList = (List<ICCardVO>) object;
				finalSession.setAttribute(
						ICCardCons.BLANK_CARD_IMPORT_PROCESS_KEY + "_total",
						new Double(cardVoList.size()));
				// 开启异步线程调用后台导入接口
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						StringBuffer sb_begin = new StringBuffer("<br/>");
						StringBuffer sb = new StringBuffer();
						StringBuffer sb_temp = new StringBuffer("");
						int successCount = 0;
						int failCount = 0;
						try {
							ExcelPutIntoMongoDBImpl<ICCardVO> exportMongoDB = new ExcelPutIntoMongoDBImpl<ICCardVO>(
									ICCardVO.class);
							ExcelBean bean = exportMongoDB.addBean(user,
									String.valueOf(System.currentTimeMillis()),
									"IC卡副卡空卡导入", "1", "2");
							List<ICCard> cardList = new ArrayList<ICCard>();
							for (int i = 0; i < cardVoList.size(); i++) {
								try {
									Double totalSize = (Double) finalSession
											.getAttribute(ICCardCons.BLANK_CARD_IMPORT_PROCESS_KEY
													+ "_total");
									Double process = new Double(i) / totalSize
											* 100;
									finalSession
											.setAttribute(
													ICCardCons.BLANK_CARD_IMPORT_PROCESS_KEY,
													process);// session存储导入进度
									Pattern pattern = Pattern
											.compile("[0-9]{10,30}");
									Matcher isNum = pattern.matcher(cardVoList
											.get(i).getCardNumber());
									if (StringUtils.isBlank(cardVoList.get(i)
											.getCardNumber())
											|| !isNum.matches()) {
										sb_begin.append("卡号：")
												.append(cardVoList.get(i)
														.getCardNumber())
												.append("卡号格式不正确（格式：10~30位数字组合）<br/>");
										continue;
									}
									ICCard icCard = new ICCard();
									icCard.setCardNumber(cardVoList.get(i)
											.getCardNumber());
									icCard.setCardType(cardType);
									icCard.setOpencardofficeid(opencardofficecode);
									icCard.setParentId(parentId);
									icCard.setParentCardNumber(parentCard);
									icCard.setCtrdituser(user);
									icCard.setCardAreaCode(cardAreaCode);
									icCard.setCardAreaName(cardAreaName);
									icCard.setDataSource(ICCardCons.DATA_SOURCE_APP);
									icCard.setModifinguser(user);
									cardList.add(icCard);
								} catch (Exception e) {
									logger.error("ICCard对象拷贝异常", e);
								}
							}
							Map<String, String> resultMap = getICCardManager()
									.addEmptyCardBatch(cardList);
							List<SimpleCode> simpleCodeList = codeService
									.querySimpleCodeByTypeCode(ICCardCons.CARD_TYPE_CODE);
							Map<String, String> cardTypeMap = new HashMap<String, String>();
							for (SimpleCode simpcode : simpleCodeList) {
								cardTypeMap.put(simpcode.getCode(),
										simpcode.getName());
							}
							if(resultMap!=null){
								Set<String> keySet = resultMap.keySet();
								for (String key : keySet) {
									if ("success".equalsIgnoreCase(resultMap
											.get(key))) {
										successCount++;
									} else {// 失败的
										sb_temp.append(
												cardTypeMap.get(key
														.split(ICCardCons.SEPERATOR)[0]))
												.append("卡号："
														+ key.split(ICCardCons.SEPERATOR)[1])
												.append("导入失败!"
														+ resultMap.get(key)
														+ "<br/>");
										failCount++;
									}
								}
							}
							sb.append(sb_begin).append("共导入")
									.append(successCount + failCount)
									.append("条，成功").append(successCount)
									.append("条，失败").append(failCount)
									.append("条!<br/>").append(sb_temp);
							exportMongoDB.updateBean(bean, sb.toString());
							logger.info(sb.toString().replaceAll("<br/>",
									"\r\n"));
						} catch (Exception e) {
							sb.append("<br/>导入异常。");
							logger.error("调用后台导入ICCard接口异常", e);
						} finally {
							finalSession.setAttribute(
									ICCardCons.BLANK_CARD_IMPORT_MESSAGE_KEY,
									sb.toString());
							finalSession
									.removeAttribute(ICCardCons.BLANK_CARD_IMPORT_PROCESS_KEY);// 移除导入进度
							finalSession
									.removeAttribute(ICCardCons.BLANK_CARD_IMPORT_PROCESS_KEY
											+ "_total");// 移除导入进度
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
	public String icCardOpen(InputStream excelFile, String fileName,
			HttpServletRequest request) {
		File file = new File(fileName);
		ExcelDownLoadOrUpLoadUtil.inputstreamtofile(excelFile, file);
		String result = "0";
		Map<String, Map<String, String>> map = this.getOpenCardModelMap();
		try {
			logger.debug("批量开卡IC卡副卡...");
			ExcelUtilInter<ICCardApplyVO> imp = new ExcelUtilImpl<ICCardApplyVO>(
					ICCardApplyVO.class);

			List<ExpObj> expObjs = new ExcelUtil().getExpObjs(
					ICCardApplyVO.class, 2, "IMP");
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
			expObjs = new ExcelUtil().setExpObjMethodObjects(expObjs, methods,
					objs, sort);
			boolean check = imp.checkExcelModel(ICCardApplyVO.class, expObjs,
					file);// 检测导入模版
			final HttpSession finalSession = request.getSession();
			finalSession
					.setAttribute(ICCardCons.OPEN_PATCH_CARD_PROCESS_KEY, 0);
			if (!check) {
				finalSession
				.removeAttribute(ICCardCons.OPEN_PATCH_CARD_PROCESS_KEY);// 移除导入进度
		finalSession.setAttribute(
				ICCardCons.OPEN_PATCH_CARD_MESSAGE_KEY,
				"导入模版不正确，请重新下载导入模版！");
		logger.warn("导入模版不正确，请重新下载导入模版！");
		return result;
			}
			Map<Collection<ICCardApplyVO>, String> coll = imp.importExcel(file,
					expObjs, 2);
			Set<Collection<ICCardApplyVO>> set = coll.keySet();
			final Index index = ((Index) request.getSession().getAttribute(
					Converter.SESSION_INDEX));
			for (Collection<ICCardApplyVO> object : set) {
				final List<ICCardApplyVO> cardApplyVoList = (List<ICCardApplyVO>) object;

				/*****
				 * 导入前先校验必填项
				 */
				StringBuffer validateString = new StringBuffer("");
				for (int j = 0; j < cardApplyVoList.size(); j++) {
					for (int i = 0; i < expObjs.size(); i++) {
						ExpObj exObj = expObjs.get(i);
						String title = exObj.getTitle();
						if (title.indexOf("[M]") != -1) {
							Object value = ExcelUtil.invokeMethod(
									exObj.getAllgetPathMethod(),
									cardApplyVoList.get(j));
							if (value == null) {
								if (StringUtils.isBlank(validateString
										.toString())) {
									validateString.append("<br/>导入前校验：<br/>");
								}
								validateString
										.append("[注册手机号]：")
										.append(cardApplyVoList.get(j)
												.getMobile() == null ? "[空]"
												: cardApplyVoList.get(j)
														.getMobile())
										.append("第 ")
										.append(ExcelUtil
												.getExcelColumnLabel(exObj
														.getIndex()))
										.append(" 列为[空]，标题“").append(title)
										.append("”带“[M]”必须填写才能继续进行导入！<br/>");
							} else if (value instanceof List) {
								List t = (List) value;
								for (int k = 0; k < t.size(); k++) {
									if (t.get(k) == null) {
										if (StringUtils.isBlank(validateString
												.toString())) {
											validateString
													.append("<br/>导入前校验：<br/>");
										}
										validateString
												.append("[注册手机号]：")
												.append(cardApplyVoList.get(j)
														.getMobile() == null ? "[空]"
														: cardApplyVoList
																.get(j)
																.getMobile())
												.append("第 ")
												.append(ExcelUtil
														.getExcelColumnLabel(exObj
																.getIndex()))
												.append(" 列存在[空]，标题“")
												.append(title)
												.append("”带“[M]”必须填写才能继续进行导入！<br/>");
									}
								}
							}
						}
					}
				}
				if (StringUtils.isNotBlank(validateString.toString())) {
					finalSession
							.removeAttribute(ICCardCons.OPEN_PATCH_CARD_PROCESS_KEY);// 移除导入进度
					finalSession.setAttribute(
							ICCardCons.OPEN_PATCH_CARD_MESSAGE_KEY,
							validateString.toString());
					logger.warn(validateString.toString().replaceAll("<br/>",
							"\r\n"));
					return result;
				}
				finalSession.setAttribute(
						ICCardCons.OPEN_PATCH_CARD_PROCESS_KEY + "_total",
						new Double(cardApplyVoList.size()));
				// 开启异步线程调用后台导入接口
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						StringBuffer sb_begin = new StringBuffer("<br/>");
						StringBuffer sb = new StringBuffer();
						int successCount = 0;
						int failCount = 0;
						StringBuffer sb_temp = new StringBuffer("");
						try {
							ExcelPutIntoMongoDBImpl<ICCardApplyVO> exportMongoDB = new ExcelPutIntoMongoDBImpl<ICCardApplyVO>(
									ICCardApplyVO.class);
							ExcelBean bean = exportMongoDB.addBean(
									index.getUserName(),
									String.valueOf(System.currentTimeMillis()),
									"IC卡副卡批量开卡", "1", "2");
							for (int i = 0; i < cardApplyVoList.size(); i++) {

								Double totalSize = (Double) finalSession
										.getAttribute(ICCardCons.OPEN_PATCH_CARD_PROCESS_KEY
												+ "_total");
								Double process = new Double(i) / totalSize
										* 100;
								finalSession.setAttribute(
										ICCardCons.OPEN_PATCH_CARD_PROCESS_KEY,
										process);// session存储导入进度

								try {
									// excel Bean
									ICCardApplyVO excelObj = cardApplyVoList
											.get(i);
									String oftenArea = (StringUtils
											.isBlank(excelObj
													.getOftenAddress1()) ? ""
											: excelObj.getOftenAddress1() + ",")
											+ (StringUtils.isBlank(excelObj
													.getOftenAddress2()) ? ""
													: excelObj
															.getOftenAddress2()
															+ ",")
											+ (StringUtils.isBlank(excelObj
													.getOftenAddress3()) ? ""
													: excelObj
															.getOftenAddress3()
															+ ",");
									if (StringUtils.isNotBlank(oftenArea))
										oftenArea = oftenArea.substring(0,
												oftenArea.length() - 1);
									if (StringUtils.isEmpty(excelObj
											.getMobile())) {
										sb_begin.append(
												"[注册手机号]："
														+ excelObj.getMobile())
												.append(",已被忽略导入。<br/>");
										continue;
									}
									if (StringUtils.isEmpty(excelObj.getName())) {
										sb_begin.append(
												"注册手机号：" + excelObj.getMobile())
												.append("[姓名]为空,已被忽略导入。<br/>");
										continue;
									}

									if (StringUtils.isEmpty(excelObj
											.getIdCardNo())) {
										sb_begin.append(
												"注册手机号：" + excelObj.getMobile())
												.append("[身份证号]为空,已被忽略导入。<br/>");
										continue;
									}

									if (StringUtils.isEmpty(excelObj
											.getCardType())) {
										sb_begin.append(
												"注册手机号：" + excelObj.getMobile())
												.append("[油卡类型]为空,已被忽略导入。<br/>");
										continue;
									}

									if (StringUtils.isEmpty(excelObj
											.getReceiverPhoneNum())) {
										sb_begin.append(
												"注册手机号：" + excelObj.getMobile())
												.append("[收件人手机号]为空,已被忽略导入。<br/>");
										continue;
									}

									if (StringUtils.isEmpty(excelObj
											.getAddressee())) {
										sb_begin.append(
												"注册手机号：" + excelObj.getMobile())
												.append("[收件人]为空,已被忽略导入。<br/>");
										continue;
									}

									if (StringUtils.isEmpty(excelObj
											.getProvince())) {
										sb_begin.append(
												"注册手机号：" + excelObj.getMobile())
												.append("[收件省份]为空,已被忽略导入。<br/>");
										continue;
									}

									if (StringUtils.isEmpty(excelObj.getCity())) {
										sb_begin.append(
												"注册手机号：" + excelObj.getMobile())
												.append("[收件城市]为空,已被忽略导入。<br/>");
										continue;
									}

									if (StringUtils.isEmpty(excelObj
											.getDistrict())) {
										sb_begin.append(
												"注册手机号：" + excelObj.getMobile())
												.append("[收件区县]为空,已被忽略导入。<br/>");
										continue;
									}

									if (StringUtils.isEmpty(excelObj
											.getAddress())) {
										sb_begin.append(
												"注册手机号：" + excelObj.getMobile())
												.append("[收件详细地址]为空,已被忽略导入。<br/>");
										continue;
									}
									if (StringUtils.isEmpty(excelObj
											.getZipCode())) {
										sb_begin.append(
												"注册手机号：" + excelObj.getMobile())
												.append("[邮编]为空,已被忽略导入。<br/>");
										continue;
									}
									if (StringUtils.isEmpty(oftenArea)) {
										sb_begin.append(
												"注册手机号：" + excelObj.getMobile())
												.append("[常用地区]为空,已被忽略导入。<br/>");
										continue;
									}
									if (StringUtils.isEmpty(excelObj
											.getExpressCompany())) {
										sb_begin.append(
												"注册手机号：" + excelObj.getMobile())
												.append("[快递公司]为空,已被忽略导入。<br/>");
										continue;
									}
									if (StringUtils.isEmpty(excelObj
											.getExpress())) {
										sb_begin.append(
												"注册手机号：" + excelObj.getMobile())
												.append("[快递信息（单号）]为空,已被忽略导入。<br/>");
										continue;
									}
									// 验证注册手机号是否存在
									UAAUser uaaUser = getUserService()
											.queryUserByUserLoginNeedCRMInfo(
													excelObj.getMobile(), true,
													new Operator());
									if (uaaUser == null
											|| !(uaaUser instanceof CRMUserBean)) {
										sb_begin.append(
												"注册手机号：" + excelObj.getMobile())
												.append("不存在统一身份认证或找不到客户资料信息,已被忽略导入。<br/>");
										continue;
									}
									// db Bean
									ICCardApplyMetaBean metaBean = new ICCardApplyMetaBean();
									ICCardApply apply = new ICCardApply();
									apply.setUserRegPhone(excelObj.getMobile());// 注册手机号
									apply.setUserName(((CRMUserBean) uaaUser)
											.getCrmName());// 姓名
									apply.setApplyUserName(excelObj.getName());// 申请人姓名
									apply.setCardType(excelObj.getCardType());// 油卡类型
									apply.setCreator(index.getUserId());// 创建人ID
									apply.setModifierName(index.getUserName());// 审核人姓名
									apply.setOftenArea(oftenArea);// 常用地区
									apply.setIdNo(excelObj.getIdCardNo());// 身份证号码
									apply.setReceiverName(excelObj
											.getAddressee());// 收件人姓名
									apply.setProvince(excelObj.getProvince());// 邮寄省
									apply.setCity(excelObj.getCity());// 邮寄市
									apply.setDistrict(excelObj.getDistrict());// 邮寄区县
									apply.setAddress(excelObj.getAddress());// 详细地址
									apply.setReceiverPhoneNum(excelObj
											.getReceiverPhoneNum());// 收件人手机号
									apply.setZipCode(excelObj.getZipCode());// 邮编
									apply.setExpressCompany(excelObj
											.getExpressCompany());// 快递公司
									apply.setExpressInfo(excelObj.getExpress());// 快递信息单号
									apply.setDataSource(ICCardCons.DATA_SOURCE_APP);// 数据来源

									List<ICCardApplyDetail> detailList = new ArrayList<ICCardApplyDetail>();
									for (int j = 0; j < excelObj.getCardList()
											.size(); j++) {
										CardDetail excelDetail = excelObj
												.getCardList().get(j);
										ICCardApplyDetail detail = new ICCardApplyDetail();
										detail.setVehicleNo(excelDetail
												.getVehicleNo());// 车牌号
										detail.setPhoneNum(excelDetail
												.getBindMobile());// 绑定手机
										detail.setAcceptMessage(excelDetail
												.getMessageRemind());// 短信提醒
										detail.setCardNum(excelObj.getName());// 卡类型
										detail.setCardNum(excelDetail
												.getCardNumber());// 卡号
										// detail.setCardNum(excelDetail.getMainCardNumber());//主卡卡号
										detailList.add(detail);

									}

									metaBean.setiCCardApply(apply);
									metaBean.setiCCardApplyDetail(detailList);
									// log表
									ICCardApplyProcessLog applyLog = new ICCardApplyProcessLog();
									applyLog.setSuggestion("系统导入");// 审核意见
									applyLog.setRemark("系统导入");// 备注
									applyLog.setApprovalRoleId(index
											.getRoleId());// 角色ID
									applyLog.setApprovalRole(index
											.getRoleName());// 角色名称
									applyLog.setApprovalPersonId(index
											.getUserId());// 处理人ID
									applyLog.setApprovalPerson(index
											.getUserName());// 处理人名称

									// 调用开卡接口
									Map<String, String> resultMap = getIccardApplyManager()
											.addCardImportBatch(metaBean,
													applyLog);
									Set<String> keySet = resultMap.keySet();
									List<SimpleCode> simpleCodeList = codeService
											.querySimpleCodeByTypeCode(ICCardCons.CARD_TYPE_CODE);
									Map<String, String> cardTypeMap = new HashMap<String, String>();
									for (SimpleCode simpcode : simpleCodeList) {
										cardTypeMap.put(simpcode.getCode(),
												simpcode.getName());
									}
									for (String key : keySet) {
										if ("success"
												.equalsIgnoreCase(resultMap
														.get(key))) {
											successCount++;
										} else {// 失败的
											sb_temp.append(
													cardTypeMap.get(key
															.split(ICCardCons.SEPERATOR)[0]))
													.append("卡号："
															+ key.split(ICCardCons.SEPERATOR)[1])
													.append("导入失败!"
															+ resultMap
																	.get(key)
															+ "<br/>");
											failCount++;
										}
									}
								} catch (Exception e) {
									logger.error("批量开卡异常", e);
								}
							}
							sb.append(sb_begin).append("共开卡")
									.append(successCount + failCount)
									.append("条，成功").append(successCount)
									.append("条，失败").append(failCount)
									.append("条!<br/>").append(sb_temp);
							sb = new StringBuffer(sb.toString().replaceAll(
									"null", "[空]"));
							exportMongoDB.updateBean(bean, sb.toString());
							logger.info(sb.toString().replaceAll("<br/>",
									"\r\n"));
						} catch (Exception e) {
							sb.append("<br/>导入异常。");
							logger.error("调用后台导入ICCard接口异常", e);
						} finally {
							finalSession.setAttribute(
									ICCardCons.OPEN_PATCH_CARD_MESSAGE_KEY,
									sb.toString());
							finalSession
									.removeAttribute(ICCardCons.OPEN_PATCH_CARD_PROCESS_KEY);// 移除导入进度
							finalSession
									.removeAttribute(ICCardCons.OPEN_PATCH_CARD_PROCESS_KEY
											+ "_total");// 移除导入进度
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

	/**
	 * 获取批量开卡Map_enum
	 * 
	 * @return
	 */
	@Override
	public Map<String, Map<String, String>> getOpenCardModelMap() {
		Map<String, Map<String, String>> mapEnum = new HashMap<String, Map<String, String>>();
		try {

			List<SimpleCode> list = codeService
					.querySimpleCodeByTypeCode("IC-CARD-TYPE");
			Map<String, String> cardTypeMap = new HashMap<String, String>();
			for (int i = 0; list != null && i < list.size(); i++) {
				cardTypeMap.put(list.get(i).getCode(), list.get(i).getName());
			}
			mapEnum.put("cardType", cardTypeMap);
			list = codeService.querySimpleCodeByTypeCode("IS-SEND-MESSAGE");
			Map<String, String> messageModelMap = new HashMap<String, String>();
			for (int i = 0; list != null && i < list.size(); i++) {
				messageModelMap.put(list.get(i).getCode(), list.get(i)
						.getName());
			}
			mapEnum.put("messageRemind", messageModelMap);
		} catch (Exception e) {
			logger.error("模版map查询异常!", e);
		}
		return mapEnum;
	}

	/**
	 * 更新余额
	 */
	@Override
	public String updateBlance(Object model) {
		String flag = "-1";
		try {
			ICCard card = (ICCard) model;
			if (card != null && card.getId() != null
					&& !"".equals(card.getId())) {
				ICCard mcard = iccardManager.getById(card);
				JResult result = iCatchService.getOilCardBalance(
						mcard.getCardType(), mcard.getCardNumber(), "");
				if (result.isSuccess()) {
					flag = "0";
				}
				card.setBalanceModifiedTime(System.currentTimeMillis());
				getICCardManager().update(card);
			}
		} catch (Exception e) {
			logger.error("更新余额异常!", e);
		}
		return flag;
	}

	public IICCardManager getICCardManager() {
		if (iccardManager == null)
			iccardManager = (IICCardManager) ServiceFactory.getFactory()
					.getService(IICCardManager.class);
		return iccardManager;
	}

	public IICCardMainManager getICCardMainManager() {
		if (iccardMainManager == null)
			iccardMainManager = (IICCardMainManager) ServiceFactory
					.getFactory().getService(IICCardMainManager.class);
		return iccardMainManager;
	}

	public IICCardApplyManager getIccardApplyManager() {
		if (iccardApplyManager == null)
			iccardApplyManager = (IICCardApplyManager) ServiceFactory
					.getFactory().getService(IICCardApplyManager.class);
		return iccardApplyManager;
	}

	public IUserService getUserService() {
		if (userService == null)
			userService = (IUserService) ServiceFactory.getFactory()
					.getService(IUserService.class);
		return userService;
	}

}
