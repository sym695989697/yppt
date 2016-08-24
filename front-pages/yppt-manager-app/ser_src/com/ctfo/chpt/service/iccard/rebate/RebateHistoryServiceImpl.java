package com.ctfo.chpt.service.iccard.rebate;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.ServiceImpl;
import com.ctfo.base.util.ExcelDownLoadOrUpLoadUtil;
import com.ctfo.base.util.ExcelPutIntoMongoDBImpl;
import com.ctfo.chpt.bean.iccard.rebate.vo.RebateImportInfoVo;
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
import com.ctfo.file.boss.IMongoService;
import com.ctfo.gatewayservice.interfaces.bean.user.CRMUserBean;
import com.ctfo.gatewayservice.interfaces.service.IUserService;
import com.ctfo.util.JsonUtil;
import com.ctfo.yppt.baseservice.card.cons.ICCardCons;
import com.ctfo.yppt.baseservice.rebate.intf.IRebateSettleCurrencyManager;
import com.ctfo.yppt.bean.RebateImportBean;
import com.google.code.morphia.query.Query;

@Service("rebateHistoryService")
public class RebateHistoryServiceImpl extends ServiceImpl implements IRebateHistoryService {

	private static Log logger = LogFactory.getLog(RebateHistoryServiceImpl.class);

	private IMongoService<RebateImportInfoVo> mongoService = null;
	private IUserService userService = null;
	private IRebateSettleCurrencyManager rebateSettleCurrencyManager=null;

	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	@Override
	public String importRebate(InputStream excelFile, String fileName,
			HttpServletRequest request) {
		File file=new File(fileName);
		ExcelDownLoadOrUpLoadUtil.inputstreamtofile(excelFile, file);
		String result="0";
		try {
			logger.debug("开始导入人工返利...");
			ExcelUtilInter<RebateImportInfoVo> imp = new ExcelUtilImpl<RebateImportInfoVo>(RebateImportInfoVo.class);
			
			List<ExpObj> expObjs = new ExcelUtil().getExpObjs(RebateImportInfoVo.class, 2,"IMP");
			//排序定义
			List<Integer> sort =  null;
			//定义的方法
			List<Method> methods = new ArrayList<Method>(expObjs.size());
			//定义执行方法的对象
			List<Object> objs = new ArrayList<Object>(expObjs.size());
			for (int i = 0 ; i <  expObjs.size() ;i++) {
				objs.add(null);
				methods.add(null);
			}
			//设置
			expObjs = new ExcelUtil().setExpObjMethodObjects(expObjs, methods, objs, sort);	
			boolean check=imp.checkExcelModel(RebateImportInfoVo.class, expObjs, file);//检测导入模版
			final HttpSession finalSession=request.getSession();
			finalSession.setAttribute(ICCardCons.REBATE_HISTORY_IMPORT_PROCESS_KEY, 0);
			if(!check){
				finalSession
						.removeAttribute(ICCardCons.REBATE_HISTORY_IMPORT_PROCESS_KEY);// 移除导入进度
				finalSession.setAttribute(
						ICCardCons.REBATE_HISTORY_IMPORT_MESSAGE_KEY,
						"导入模版不正确，请重新下载导入模版！");
				logger.warn("导入模版不正确，请重新下载导入模版！");
				return result;
			}
			Map<Collection<RebateImportInfoVo>,String> coll = imp.importExcel(file, expObjs, 2);
			Set<Collection<RebateImportInfoVo>> set=coll.keySet();
			final String user=((Index)request.getSession().getAttribute(Converter.SESSION_INDEX)).getUserId();
			final String year_month=request.getParameter("dateYearAndMonth");
			final String year=year_month.split("-")[0];
			final String month=year_month.split("-")[1];
			final String opencardofficecode=request.getParameter("opencardofficecode_val");
			for (Collection<RebateImportInfoVo> object : set) {
				result+=coll.get(object);
				final List<RebateImportInfoVo> cardVoList=(List<RebateImportInfoVo>)object;
				finalSession.setAttribute(ICCardCons.REBATE_HISTORY_IMPORT_PROCESS_KEY+"_total", new Double(cardVoList.size()));
				//开启异步线程调用后台导入接口
				Thread thread=new Thread(new Runnable() {
					@Override
					public void run() {
						StringBuffer sb_begin=new StringBuffer("<span style=\"color:red;font-weight: bold;\">(注：同会员同账单期同发卡地区的返利重复导入时会进行覆盖，已结算的返利不能再次导入(覆盖)。)</span><br/>");
						StringBuffer sb=new StringBuffer();
						int successCount=0;
						int failCount=0;
						StringBuffer sb_temp=new StringBuffer("");
						try {
							ExcelPutIntoMongoDBImpl<RebateImportInfoVo> exportMongoDB = new ExcelPutIntoMongoDBImpl<RebateImportInfoVo>(RebateImportInfoVo.class);
							ExcelBean bean = exportMongoDB.addBean(user, String.valueOf(System.currentTimeMillis()), "人工返利导入", "1", "2");
							Long createTime=System.currentTimeMillis();
							for (int i = 0; i < cardVoList.size(); i++) {
								try {
									Double totalSize=(Double)finalSession.getAttribute(ICCardCons.REBATE_HISTORY_IMPORT_PROCESS_KEY+"_total");
									Double process=new Double(i)/totalSize*100;
									finalSession.setAttribute(ICCardCons.REBATE_HISTORY_IMPORT_PROCESS_KEY,process);//session存储导入进度
									//验证会员当月是否结算过旺金币
									Query<RebateImportInfoVo> query = getMongoService().getQuery(RebateImportInfoVo.class);
									query.field("registerMobile").equal(cardVoList.get(i).getRegisterMobile())
										 .field("importYear").equal(year)
										 .field("importMonth").equal(month)
										 .field("grantAreaCode").equal(opencardofficecode);
									List<RebateImportInfoVo> mgList = getMongoService().query(RebateImportInfoVo.class, query);
									boolean isSettle=false;//是否已结算
									for (int j = 0; j < mgList.size(); j++) {
										if(ICCardCons.CURRECTY_STATUS_YES.equals(mgList.get(j).getStatus())){
											isSettle=true;
											break;
										}else{
											String id= mgList.get(0).getId();
											 getMongoService().delete(RebateImportInfoVo.class, id);
										}
									}
									if(isSettle){
										sb_begin.append("注册手机号："+cardVoList.get(i).getRegisterMobile()).append("在账单期").append(year_month).append("已结算过返利到旺金币，已被忽略导入。<br/>");
										continue ;
									}
									//验证注册手机号是否存在
									UAAUser uaaUser  =  getUserService().queryUserByUserLoginNeedCRMInfo(cardVoList.get(i).getRegisterMobile(), true, new Operator());
									if(uaaUser==null||!(uaaUser instanceof CRMUserBean)){
										sb_begin.append("注册手机号："+cardVoList.get(i).getRegisterMobile()).append("不存在统一身份认证或找不到客户资料信息,已被忽略导入。<br/>");
										continue ;
									}
									cardVoList.get(i).setId(UUID.randomUUID().toString());//UUID
									cardVoList.get(i).setOwnerId(uaaUser.getId());//会员UAAID
									cardVoList.get(i).setOwnerName(((CRMUserBean)uaaUser).getCrmName());//会员CrmName
									cardVoList.get(i).setGrantAreaCode(opencardofficecode);//发卡地区
									cardVoList.get(i).setImportYear(year);//年份
									cardVoList.get(i).setImportMonth(month);//月份
									Double money = new Double(cardVoList.get(i).getConsumeMoney());
									Double consumerMoney=Math.floor(money*100);
									cardVoList.get(i).setConsumeMoney(consumerMoney.longValue()+"");//消费金额 --excel中已赋值
									money = new Double(cardVoList.get(i).getChangeMoney());
									Double changeMoney=Math.floor(money*100);
									cardVoList.get(i).setChangeMoney(changeMoney.longValue()+"");//可兑换金额 --excel中已赋值
									cardVoList.get(i).setCurrencyCount(cardVoList.get(i).getChangeMoney());//返利旺金币数量 
									cardVoList.get(i).setStatus(ICCardCons.CURRECTY_STATUS_NO);//旺金币结算状态 0：未结算 1：已结算
									cardVoList.get(i).setOperatorId(user);//操作人id
									cardVoList.get(i).setCreateTime(createTime);//操作时间
									getMongoService().save(cardVoList.get(i));
									successCount++;
								} catch (Exception e) {
									failCount++;
									sb_temp.append("注册手机号为：").append(cardVoList.get(i).getRegisterMobile()).append("的返利导入失败!数据填写不正确<br/>");
									logger.error("保存人工返利异常", e);
								}
							}
							sb.append(sb_begin).append("共").append(successCount+failCount).append("条，成功").append(successCount).append("条，失败").append(failCount).append("条!<br/>").append(sb_temp);
							exportMongoDB.updateBean(bean, sb.toString());
							logger.info(sb.toString().replaceAll("<br/>", "\r\n"));
						} catch (Exception e) {
							sb.append("<br/>导入异常。");
							logger.error("调用后台导入人工返利接口异常", e);
						}finally{
							finalSession.setAttribute(ICCardCons.REBATE_HISTORY_IMPORT_MESSAGE_KEY, sb.toString());
							finalSession.removeAttribute(ICCardCons.REBATE_HISTORY_IMPORT_PROCESS_KEY);//移除导入进度
							finalSession.removeAttribute(ICCardCons.REBATE_HISTORY_IMPORT_PROCESS_KEY+"_total");//移除导入进度
						}
					}
				});
				thread.start();
			}
			file.delete();
		}catch (Exception e) {
			result="1";
			logger.error("导入人工返利异常",e);
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PaginationResult queryRebateHistoryListPage(DynamicSqlParameter requestParam) {
		PaginationResult result= new PaginationResult();
		try {
			List<RebateImportInfoVo> list=new ArrayList<RebateImportInfoVo>();
			Query<RebateImportInfoVo> query = getMongoService().getQuery(RebateImportInfoVo.class);
			this.convertParam(query,requestParam);
			query.order("-importYear,-importMonth");
			list = getMongoService().query(RebateImportInfoVo.class, query);
			for (int i = 0; i < list.size(); i++) {
				RebateImportInfoVo rebate = list.get(i);
				UAAUser user = getUserService().queryUaaUserById(rebate.getOperatorId(), new Operator());
				rebate.setOperatorId(user==null?null:user.getUserName());
			}
			result.setData(list);
			result.setStart(requestParam.getStartNum());
			Long resultCount = getMongoService().getCount();
			result.setTotal(resultCount.intValue());
		} catch (Exception e) {
			logger.error("分页查询ic卡列表异常", e);
		}
		return result;
	}
	
	@Override
	public String changePatchCurrency(String ids,String opId) {
		String flag="1";
		try {
			String[] arrays=ids.split(",");
			int failCount=0;
			for (int i = 0; i < arrays.length; i++) {
				List<RebateImportInfoVo> rebateVo = getMongoService().query(RebateImportInfoVo.class,getMongoService().getQuery(RebateImportInfoVo.class).field("id").equal(arrays[i]));
				if(rebateVo!=null && rebateVo.size()>0 && ICCardCons.CURRECTY_STATUS_NO.equals(rebateVo.get(0).getStatus())){
					try {
						RebateImportBean bean = new RebateImportBean();
						BeanUtils.copyProperties(rebateVo.get(0), bean);
						//调用结算旺金币接口
						getRebateSettleCurrencyManager().settleCurrency(bean, opId);
						rebateVo.get(0).setStatus(ICCardCons.CURRECTY_STATUS_YES);
						getMongoService().update(rebateVo.get(0));
					} catch (Exception e) {
						failCount++;
						flag="2";//部分失败
						logger.error("返利ID:"+rebateVo.get(0).getId()+"结算失败!", e);
					}
				}
			}
			if("1".equals(flag)){
				flag="0";//全部成功
			}else if(arrays.length==failCount){
				flag="1";//全部失败
			}
		} catch (Exception e) {
			logger.error("结算返利到旺金币异常", e);
		}
		return flag;
	}
	
	
	
	/***
	 * 扩展FileService jar包中参数转换功能
	 * 
	 * @param query
	 * @param param
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void convertParam(Query<?> query,DynamicSqlParameter requestParam) {
		if (requestParam.getEqual() != null) {
			Map map = requestParam.getEqual();
			Iterator it = map.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				if ((key != null) && (map.get(key) != null))
					query = (Query) query.field(key).equal(map.get(key));
			}
		}
		if (requestParam.getLike() != null) {
			Map map = requestParam.getLike();
			Iterator it = map.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				if ((key != null) && (map.get(key) != null)) {
					String value = (String) map.get(key);
					value = StringUtils.removeEnd(value, "%");
					value = StringUtils.removeStart(value, "%");
					query = (Query) query.field(key).contains(
							JsonUtil.jsonCharFormat(value));
				}
			}
		}
		if (requestParam.getInMap() != null) {
			Map map = requestParam.getInMap();
			Iterator it = map.keySet().iterator();

			while (it.hasNext()) {
				String key = (String) it.next();
				if ((key != null) && (map.get(key) != null)) {
					Object value = map.get(key);
					if ((value instanceof Iterable)) {
						if (((Iterable) map.get(key)).iterator().hasNext())
							query = (Query) query.field(key).in(
									(Iterable) value);
					} else if ((value instanceof String[])) {
						List temList = new ArrayList();
						for (String val : ((String[]) (String[]) value)[0]
								.split(",")) {
							temList.add(val);
						}
						query = (Query) query.field(key).in(temList);
					}
				}
			}
		}
		if (requestParam.getStartwith() != null) {
			Map map = requestParam.getStartwith();
			Iterator it = map.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				if ((key != null) && (map.get(key) != null))
					query = (Query) query.field(key).greaterThanOrEq(
							map.get(key));
			}
		}
		if (requestParam.getEndwith() != null) {
			Map map = requestParam.getEndwith();
			Iterator it = map.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				if ((key != null) && (map.get(key) != null))
					query = (Query) query.field(key).lessThan(map.get(key));
			}
		}
		if (requestParam.getOrder() != null) {
			if ((requestParam.getSort() != null)
					&& (requestParam.getSort().equalsIgnoreCase("desc"))
					&& (!StringUtils.startsWith(requestParam.getOrder(), "-"))) {
				query.order("-" + requestParam.getOrder());
			} else
				query.order(requestParam.getOrder());
		}
		if ((requestParam.getRows() != 1) && (requestParam.getPage() != 0)) {
			query.offset((requestParam.getPage() - 1) * requestParam.getRows());
			query.limit(requestParam.getRows());
		}
	}
	@SuppressWarnings("unchecked")
	public IMongoService<RebateImportInfoVo> getMongoService() {
		if(mongoService==null)
			mongoService = (IMongoService<RebateImportInfoVo>) ServiceFactory.getFactory().getService(IMongoService.class);
		return mongoService;
	}
	public IUserService getUserService() {
		if(userService==null)
			userService=(IUserService) ServiceFactory.getFactory().getService(IUserService.class);
		return userService;
	}
	public IRebateSettleCurrencyManager getRebateSettleCurrencyManager() {
		if(rebateSettleCurrencyManager==null)
			rebateSettleCurrencyManager=(IRebateSettleCurrencyManager) ServiceFactory.getFactory().getService(IRebateSettleCurrencyManager.class);
		return rebateSettleCurrencyManager;
	}

	


}
