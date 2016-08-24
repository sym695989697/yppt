package com.ctfo.chpt.service.iccard.apply;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.ServiceImpl;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Converter;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.soa.support.JsonSupport;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.file.boss.IFileService;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.beans.ICCardApply;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyDetail;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyExampleExtended;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyProcessLog;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyProcessLogExampleExtended;
import com.ctfo.yppt.baseservice.card.beans.ICCardExampleExtended;
import com.ctfo.yppt.baseservice.card.cons.ICCardCons;
import com.ctfo.yppt.baseservice.card.intf.IICCardApplyManager;
import com.ctfo.yppt.baseservice.card.intf.IICCardManager;
import com.ctfo.yppt.bean.ICCardApplyMetaBean;


@Service(value = "iCCardApplyService")
public class ICCardApplyServiceImpl extends ServiceImpl implements IICCardApplyService {

	private static Log logger = LogFactory.getLog(ICCardApplyServiceImpl.class);
	
	private IICCardApplyManager iICCardApplyManager;//卡申请接口
	private IICCardManager iicCardManager;//卡接口
	private IFileService  fileService;
	public ICCardApplyServiceImpl() {
		
		logger.info("初始化IC卡申请服务！");
		iICCardApplyManager = (IICCardApplyManager) ServiceFactory.getFactory().getService(IICCardApplyManager.class);
		iicCardManager = (IICCardManager) ServiceFactory.getFactory().getService(IICCardManager.class);
		fileService=(IFileService) ServiceFactory.getFactory().getService(IFileService.class);
	}

	@Override
	public PaginationResult<?> getListPage(DynamicSqlParameter requestParam) throws AAException {
		PaginationResult<?> result = new PaginationResult<ICCardApply>();
		try {
			ICCardApplyExampleExtended exampleExtended = (ICCardApplyExampleExtended) Converter.paramToExampleExtendedNoException(requestParam, new ICCardApplyExampleExtended());
			result = iICCardApplyManager.paginate(exampleExtended);
			logger.info("查询结果数量："+result.getData().size());
		} catch (Exception e) {
			logger.error("调用卡申请记录失败！", e);
			throw new AAException("调用卡申请记录失败！", e);
		}
		return result;
	}

	@Override
	public PaginationResult check(ICCardApply model,Index index,String cards) throws Exception {
		PaginationResult pResult = new PaginationResult();
		Integer result = null;
		String cardNums[] = null;
		if(StringUtils.isNotBlank(cards)){
			cardNums = cards.split(",");
		}
		String status = model.getStatus();
		if(ICCardCons.APPLY_AUDIT_STATE_CALL_CENTER_YET.equals(status)
				|| ICCardCons.APPLY_AUDIT_STATE_CALL_CENTER_ING.equals(status)){
			
			logger.info(">>>>呼叫中心审核通过！开始更新状态...");
			model.setStatus(ICCardCons.APPLY_AUDIT_STATE_CALL_CENTER_PASS);
			result = this.addApplyProcessLog(model, index,cardNums);
			logger.info(">>>>更新完成！");
		}else if(ICCardCons.APPLY_AUDIT_STATE_CARD_BUSINESS_ING.equals(status) 
				|| ICCardCons.APPLY_AUDIT_STATE_CARD_BUSINESS_YET.equals(status)
				|| ICCardCons.APPLY_AUDIT_STATE_CALL_CENTER_PASS.equals(status)){
			
			logger.info(">>>>卡务审核通过！开始更新状态...");
			model.setStatus(ICCardCons.APPLY_AUDIT_STATE_CARD_BUSINESS_PASS);
			result = this.addApplyProcessLog(model, index,cardNums);
			logger.info(">>>>更新完成！");
		}else if(ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_ING.equals(status) 
				|| ICCardCons.APPLY_AUDIT_STATE_CARD_BUSINESS_PASS.equals(status)
				|| ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_YET.equals(status)){
			
			logger.info(">>>>行政审核通过！开始更新状态...");
			model.setStatus(ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_PASS);
			result = addApplyProcessLog(model,index,cardNums);
			if(result == null || result != 0){
				throw new Exception("审核失败！审核结果:"+result);
			}
		}else{
			logger.info(">>>>审核不通过！开始更新状态...");
			model.setStatus(ICCardCons.APPLY_AUDIT_STATE_NOT_PASS);
			result = this.addApplyProcessLog(model, index,cardNums);
			logger.info(">>>>更新完成！");
				
		}
		pResult.setDataObject(result);
		logger.info(">>>>更新完成！");
		return pResult;
	}
	

	/**
	 * 
	 * @Description 取得卡申请详情
	 * @param model
	 * @param op
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public PaginationResult<?> queryApplyMeta(ICCardApply model,Index index) throws Exception {
		PaginationResult result = new PaginationResult();
		String applyId = null;
		boolean check = false;
		String modifier=null;
		if(model != null){
			modifier = chectAppyUser(model);//对点击的人进行校验是否没有审核人 null代表没有
			check = isUpdateUser(model,index);//如果check是true的话证明需要进行更新
			if(StringUtils.isBlank(modifier) || check){
				//更新审核人
				modifier = updateUser(model,index);
			}
		}else{
			logger.error("申请实例为null");
			throw new Exception("申请实例为null");
		}
		if(index.getUserId().equals(modifier)){
			applyId = model.getId();
			ICCardApplyMetaBean iCardApplyMetaBean = null;
			//通过申请id查询申请详情
				if(StringUtils.isNotBlank(applyId)){
					try {
						iCardApplyMetaBean = getICardRechageMetaBeanById(applyId);
						//更新行驶证的图片路径
						List<ICCardApplyDetail> oldDetails = iCardApplyMetaBean.getiCCardApplyDetail();
						if(oldDetails.size()>0 && oldDetails!=null){
							List<ICCardApplyDetail> newDetails = updateVehicleLicensePicePath(oldDetails);
							iCardApplyMetaBean.setiCCardApplyDetail(newDetails);
						}
						result.setDataObject(iCardApplyMetaBean);
					} catch (Exception e) {
						logger.error("获取大Bean出现异常！", e);
						throw new Exception("获取大Bean出现异常！", e);
					}
				}
			return result;
		}else{
			result.setDataObject("0");//已有审核人
			return result;
		}
		
	}
	private List<ICCardApplyDetail> updateVehicleLicensePicePath(
		List<ICCardApplyDetail> oldDetails) {
		List<ICCardApplyDetail> newDetails = new ArrayList<ICCardApplyDetail>();
		for(ICCardApplyDetail detail:oldDetails){
			if(StringUtils.isNotBlank(detail.getVehicleLicense())){
				String minPicPath = getPicPath(detail.getVehicleLicense(),IFileService.IMAGE_MIN);
				String maxPicPath = getPicPath(detail.getVehicleLicense(),IFileService.IMAGE_MAX);
				if(StringUtils.isNotBlank(minPicPath) && StringUtils.isNotBlank(maxPicPath)){
					detail.setVehicleLicense(minPicPath+","+maxPicPath);
				}
			}
			newDetails.add(detail);
		}
		return newDetails;
}
	@Override
	public String getPicPath(String imageName,String size){
		String path = null;
		if(IFileService.IMAGE_MIN.equals(size)){
			 logger.info("开始查找图片名称为:["+imageName+"]小图的全路径");
			 path = this.fileService.getFileURL(imageName,IFileService.IMAGE_MIN);
			 logger.info("查找完成名称为:["+imageName+"]小图的全路径是["+path+"]");
		}else{
			logger.info("开始查找图片名称为:["+imageName+"]大图的全路径");
			 path = this.fileService.getFileURL(imageName,IFileService.IMAGE_MAX);
			 logger.info("查找完成名称为:["+imageName+"]大图的全路径是["+path+"]");
		}
		return path;
		
	}

	private boolean isUpdateUser(ICCardApply model, Index index) throws Exception {
		ICCardApply apply = null;
		if(model != null){
			try {
				logger.info(">>>>开始查询是否需要更改审核人！申请id为："+model.getId());
				apply = iICCardApplyManager.getById(model);
			} catch (Exception e) {
				logger.error("调用iICCardApplyManager出现异常！", e);
				throw new Exception("调用iICCardApplyManager出现异常！", e);
			}
		}
		String status = null;
		if(apply != null){
			status = apply.getStatus();
			logger.info(">>>>该审核的审核状态为："+status);
		}else{
			logger.info(">>>>没有找到申请实例");
		}
		if(StringUtils.isNotBlank(status)){
			if(ICCardCons.APPLY_AUDIT_STATE_CARD_BUSINESS_PASS.equals(status)){
				logger.info(">>>>查询完成：卡务审核通过！需更改审核人！");
				return true;
			}else if(ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_PASS.equals(status)){
				logger.info(">>>>查询完成：行政审核通过！需更改审核人！");
				return true;
			}else if(ICCardCons.APPLY_AUDIT_STATE_CALL_CENTER_YET.equals(status)){
				logger.info(">>>>查询完成：呼叫中心待审核！需更改状态！");
				return true;
			}else if(ICCardCons.APPLY_AUDIT_STATE_CARD_BUSINESS_YET.equals(status)){
				logger.info(">>>>查询完成：卡务待审核！需更改状态！");
				return true;
			}else if(ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_YET.equals(status)){
				logger.info(">>>>查询完成：行政待审核！需更改状态！");
				return true;
			}else if(ICCardCons.APPLY_AUDIT_STATE_CALL_CENTER_PASS.equals(status)){
				logger.info(">>>>查询完成：呼叫中心审核通过！需更改状态！");
				return true;
			}else{
				logger.info(">>>>查询完成：不需要更改！");
				return false;
			}
		}else{
			logger.error("获取申请状态异常！");
			throw new Exception("获取申请状态异常");
		}
	}

	private String chectAppyUser(ICCardApply model) throws Exception {
		String applyId = model.getId();
		ICCardApply iccardApply = null;
		try {
			if(StringUtils.isNotBlank(applyId)){
				logger.info("正在查询申请单是否有人审核！申请id为:"+applyId);
				iccardApply = iICCardApplyManager.getById(model);
				JsonSupport jsonObj = new JsonSupport();
				logger.info("查询完成："+jsonObj.serialize(iccardApply));
			}
		} catch (Exception e) {
			logger.info("调用iICCardApplyManager出现异常",e);
			throw new Exception("调用iICCardApplyManager出现异常",e);
		}
		if(iccardApply != null){
			String modifier = iccardApply.getModifier();
			if(StringUtils.isNotBlank(modifier)){
				logger.info("查询完成！该申请有审核人！审核人id为："+modifier);
				return modifier;
			}else{
				logger.info("查询完成！该申请没有审核人");
				return null;
			}
		}else{
			logger.error("查询申请信息为null！");
			throw new Exception("查询申请信息为null！");
		}
	}

	//通过申请id查询大bean
	@SuppressWarnings("unused")
	private ICCardApplyMetaBean getICardRechageMetaBeanById(String applyId)throws Exception{
		ICCardApplyMetaBean iCardApplyMetaBean = null;
		try {
			logger.info("调用查询申请单接口,申请id为："+applyId);
			iCardApplyMetaBean = iICCardApplyManager.getIcCardApplyMetaBean(applyId);
			logger.info("调用查询申请接口成功,申请id为："+iCardApplyMetaBean.getiCCardApply().getId()+"，申请详情数量："+iCardApplyMetaBean.getiCCardApplyDetail().size());
		} catch (Exception e) {
			logger.info("调用iICCardApplyManager接口发生异常！", e);
			throw new Exception("调用iICCardApplyManager接口发生异常！", e);
		} 
		if(iCardApplyMetaBean == null){
			logger.info("返回申请大bean为null");
			throw new Exception("返回申请大bean为null");
		}else{
			logger.info("获取大bean成功！");
			return iCardApplyMetaBean;
		}
	}
	/**
	 * 更新审核人
	 * @param model
	 * @param index
	 * @return
	 * @throws Exception
	 */
	private String updateUser(ICCardApply model, Index index) throws Exception {
		String userId = index.getUserId();
		String status = model.getStatus();
			try {
				if(StringUtils.isNotBlank(status)){
					if(ICCardCons.APPLY_AUDIT_STATE_CALL_CENTER_PASS.equals(status)){
						logger.info(">>>>改状态：卡务待审核！");
						model.setStatus(ICCardCons.APPLY_AUDIT_STATE_CARD_BUSINESS_YET);
					}else if(ICCardCons.APPLY_AUDIT_STATE_CARD_BUSINESS_PASS.equals(status)){
						logger.info(">>>>改状态：行政待审核！");
						model.setStatus(ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_YET);
					}else if(ICCardCons.APPLY_AUDIT_STATE_CALL_CENTER_YET.equals(status)){
						logger.info(">>>>改状态：呼叫中心审核中！");
						model.setStatus(ICCardCons.APPLY_AUDIT_STATE_CALL_CENTER_ING);
					}else if(ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_YET.equals(status)){
						logger.info(">>>>改状态：行政审核中！");
						model.setStatus(ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_ING);
					}else if(ICCardCons.APPLY_AUDIT_STATE_CARD_BUSINESS_YET.equals(status)){
						logger.info(">>>>改状态：卡务审核中！");
						model.setStatus(ICCardCons.APPLY_AUDIT_STATE_CARD_BUSINESS_ING);
					}else{
						logger.info(">>>>不应更改状态!状态为："+status);
					}
				}else{
					logger.error("获取申请状态异常！状态为："+status);
					throw new Exception("获取申请状态异常");
				}
				Integer result = addApplyProcessLog(model,index,null);
				if(result == null || result != 0){
					throw new Exception("审核失败！审核结果:"+result);
				}
				return userId;
			} catch (Exception e) {
				logger.error("更新状态失败！", e);
				throw new Exception("更新状态失败！", e);
			}
	}
	/**
	 * 添加并更新审核记录及状态 
	 * @param model
	 * @param index
	 * @param suggestion
	 * @throws Exception 
	 */
	private Integer addApplyProcessLog(ICCardApply model, Index index,String[] cardNums) throws Exception {
		List<ICCardApplyProcessLog> list = null;
		Integer maxStepNo = 1;
		ICCardApplyProcessLog log = null;
		String status = model.getStatus();
		String modifier = index.getUserId();
		String modifierName = index.getUserName();
		String openCardCode = model.getOpenCardOrgCode();
		String expressInfo = model.getExpressInfo();
		String mark = model.getMark();
		String expressCompany = model.getExpressCompany();
		try {
			logger.info(">>>>>开始查询流程记录！申请id为："+model.getId());
			list = iICCardApplyManager.queryICCardApplyProcessLogByApplyID(model.getId());
			logger.info(">>>>>查询结束！共查出"+list.size()+"条申请记录！");
			ICCardApplyMetaBean bean = getICardRechageMetaBeanById(model.getId());
			if(bean != null && StringUtils.isNotBlank(status)){
				logger.info("开始更新状态...状态为"+status+"审核人id为："+modifier+"，审核人姓名为："+modifierName);
				bean.getiCCardApply().setStatus(status);
				bean.getiCCardApply().setModifier(modifier);
				bean.getiCCardApply().setModifierName(modifierName);
				if(StringUtils.isNotBlank(openCardCode)){
					logger.info("添加发卡地区code:"+openCardCode);
					bean.getiCCardApply().setOpenCardOrgCode(openCardCode);
				}
				
				if(StringUtils.isNotBlank(expressInfo)){
					logger.info("添加快递单号:"+expressInfo);
					bean.getiCCardApply().setExpressInfo(expressInfo);
				}
				if(StringUtils.isNotBlank(expressCompany)){
					logger.info("添加快递公司:"+expressCompany);
					bean.getiCCardApply().setExpressCompany(expressCompany);
				}
				if(StringUtils.isNotBlank(mark)){
					logger.info("添加备注信息:"+mark);
					bean.getiCCardApply().setMark(mark);
				}
				if(cardNums != null && cardNums.length>0){
					List<ICCardApplyDetail> details = new ArrayList<ICCardApplyDetail>();
					ICCard iccard = null;
					for(int i=0;i<cardNums.length;i++){
						String cardNum = cardNums[i].split("_")[0];
						String infoId = cardNums[i].split("_")[1];
						iccard = getICCardByCardNum(cardNum);
						String cardType = iccard.getCardType();
						String cardNumber = iccard.getCardNumber();
						String cardId = iccard.getId();
						logger.info("更新申请详情信息：卡类型："+cardType+",卡号："+cardNumber+",卡详情id:"+infoId+",卡id为："+cardId);
						ICCardApplyDetail detail = updateCardICCardApplyDetail(cardNumber,infoId,cardType,cardId);
						details.add(detail);
					}
					bean.setiCCardApplyDetail(details);
				}
			}else{
				logger.info("获取大bean为null!");
				return null;
			}
			if(list != null && list.size()>0 && !list.isEmpty()){
				for (int i=0;i<list.size();i++) {
					ICCardApplyProcessLog processLog = list.get(i);
					Integer stepNo = Integer.parseInt(processLog.getStepNo());
					if(maxStepNo < stepNo){
						maxStepNo = stepNo+maxStepNo;
					}
				}
				logger.info(">>>>>>>开始创建流程记录...步骤编号："+maxStepNo);
				JsonSupport js = new JsonSupport();
				log = createICCardApplyProcessLog(model,maxStepNo,index);
				logger.info(js.serialize(log));
				logger.info(">>>>>>>创建记录完成！");
			}else{
				logger.info(">>>>>>>开始创建流程记录...步骤编号："+maxStepNo);
				log = createICCardApplyProcessLog(model,maxStepNo,index);
				logger.info(">>>>>>>创建记录完成！");
			}
			logger.info(">>>>>>>>>>开始更新审核状态...");
			Integer result = iICCardApplyManager.auditingIcCardApply(bean, log);
			logger.info(">>>>>>>>>>更新完成！结果为"+(result==0?"审核成功！":"审核失败"));
			return result;
		} catch (Exception e) {
			logger.error("调用iICCardApplyManager服务出现异常",e);
			throw new Exception("调用iICCardApplyManager服务出现异常",e);
		}
	}
	private ICCardApplyDetail updateCardICCardApplyDetail(String cardNum, String infoId,String cardType,String cardId) {
		if(StringUtils.isNotBlank(infoId) && StringUtils.isNotBlank(cardNum) && StringUtils.isNotBlank(cardType)){
			ICCardApplyDetail detail= new ICCardApplyDetail();
			logger.info("查询完成功！设置bean的卡号："+cardNum+",设置申请详情id："+infoId);
			detail.setId(infoId);
			detail.setCardNum(cardNum);
			detail.setCardType(cardType);
			return detail;
		}else{
			logger.info("更新申请详情失败！,卡号："+cardNum+",详情id："+infoId);
			return null;
		}
		
		
	}

	private ICCard getICCardByCardNum(String cardNum) throws Exception {
		ICCardExampleExtended exampleExtended = new ICCardExampleExtended();
		exampleExtended.createCriteria().andCardNumberEqualTo(cardNum);
		ICCard iccard = null;
		try {
			logger.info(">>>>>>>>调用IC卡查询接口！卡号为："+cardNum);
			List cards = iicCardManager.getList(exampleExtended);
			logger.info(">>>>>>>>查询完成！共查出："+cards.size()+"张卡");
			if(cards!=null && cards.size()>0 && !cards.isEmpty()){
				iccard = (ICCard) cards.get(0);
				logger.info(new JsonSupport().serialize(iccard));
			}
		} catch (Exception e) {
			logger.info("调用iicCardManager出现异常",e);
			throw new Exception("调用iicCardManager出现异常",e);
		}
		return iccard;
	}

	/**
	 * 创建流程记录
	 * @param model
	 * @param maxStepNo
	 * @param index
	 * @param suggestion
	 * @return
	 */
	private ICCardApplyProcessLog createICCardApplyProcessLog(ICCardApply model, Integer maxStepNo, Index index) {
		ICCardApplyProcessLog processLog = new ICCardApplyProcessLog();
		String applyId = model.getId();
		String approvalRoleId = index.getRoleId();
		String approvalRole = index.getRoleName();
		String approvalPersonId = index.getUserId();
		String approvalPerson = index.getUserName();
		String approvalState = model.getStatus();
		String mark = model.getMark();
		
		processLog.setApplyId(applyId);
		processLog.setApprovalRoleId(approvalRoleId);
		processLog.setApprovalRole(approvalRole);
		processLog.setApprovalPersonId(approvalPersonId);
		processLog.setApprovalPerson(approvalPerson);
		processLog.setApprovalState(approvalState);
		processLog.setStepNo(maxStepNo.toString());
		processLog.setState(approvalState);
		if(StringUtils.isNotBlank(mark)){
			logger.info("备注信息:"+mark);
			processLog.setSuggestion(mark);
		}
		logger.info("无备注信息");
		return processLog;
	}

	/**
	 * 
	 * @Description 查询卡是否存在
	 * @param model
	 * @param op
	 * @return
	 */
	public PaginationResult<?> checkICCardNum(String cardNo,String cardType) {
		PaginationResult<?> result = new PaginationResult<Object>();
		try {
			if (StringUtils.isNotBlank(cardNo)) {
				logger.info("开始检测卡号..."+cardNo+"卡类型CODE："+cardType);
				ICCardExampleExtended exampleExtended = new ICCardExampleExtended();
				exampleExtended.createCriteria().andCardNumberEqualTo(cardNo).andCardTypeEqualTo(cardType);
				List cards = iicCardManager.getList(exampleExtended);
				ICCard icard = null;
				if (cards != null && !cards.isEmpty() && cards.size() > 0) {
					icard = (ICCard) cards.get(0);
					logger.info("查询完成！"+new JsonSupport().serialize(icard));
					String oldCardType = icard.getCardType();
					logger.info("查询的卡类型是："+oldCardType);
					
					if(StringUtils.isNotBlank(oldCardType) && cardType.equals(oldCardType)){
						logger.info("开始查询开号是否被导入过！");
						String resultFlag = checkAppLyCardNum(cardNo);
						if("0".equals(resultFlag)){
							logger.info("该卡满足申请需求！");
							result.setDataObject(resultFlag);//卡没有问题
						}else{
							logger.info("此卡被审核过！");
							result.setDataObject(resultFlag);
						}
					}else{
						logger.info("卡和卡类型不匹配");
						result.setDataObject("1");//卡和卡类型没有绑定
					}
				} else {
					logger.info("卡信息不存在");
					result.setDataObject("2");//卡信息不存在
				}
			}
		} catch (Exception e) {
			logger.error("app service query object fail", e);
			result.setDataObject("4");//服务异常
		}
		return result;
	}

	private String checkAppLyCardNum(String cardNo) {
		ICCardExampleExtended exampleExtended = new ICCardExampleExtended();
		exampleExtended.createCriteria().andCardNumberEqualTo(cardNo);
		try {
			logger.info("开始检测开号是否被审核...开号为："+cardNo);
			List list = this.iicCardManager.getList(exampleExtended);
			ICCard iccard = null;
			logger.info("查出结果"+list);
			if(list==null && list.size()<=0 && list.isEmpty()){
				return "3";//卡号被审核过
			}else{
				iccard = (ICCard) list.get(0);
				logger.info("查询完成！此卡"+new JsonSupport().serialize(iccard));
				String userId = iccard.getUserId();
				
				if(StringUtils.isBlank(userId)){
					logger.info("卡号：["+iccard.getCardNumber()+"],未绑定用户！可以申请！");
					return "0";
				}else{
					logger.info("卡号：["+iccard.getCardNumber()+"]已经被用户:["+userId+"]绑定，不能再次重复申请!");
					return "3";
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PaginationResult<?> getApplyInfo(ICCardApply apply) throws Exception {
		PaginationResult result = new PaginationResult();
		String applyId = apply.getId();
		if(StringUtils.isBlank(applyId)){
			logger.info("没有获取到申请id");
			throw new Exception("没有获取到申请id");
		}
		ICCardApplyMetaBean bean = this.getICardRechageMetaBeanById(applyId);
		
		List<ICCardApplyProcessLog> logs = getICCardApplyProcessLogsByApplyId(applyId);
		if(bean!=null){
			logger.info("大bean信息："+new JsonSupport().serialize(bean));
			//更新行驶证的图片路径
			List<ICCardApplyDetail> oldDetails = bean.getiCCardApplyDetail();
			if(oldDetails.size()>0 && oldDetails!=null){
				List<ICCardApplyDetail> newDetails = updateVehicleLicensePicePath(oldDetails);
				bean.setiCCardApplyDetail(newDetails);
			}
			result.setDataObject(bean);
		}
		if(logs!=null){
			for(ICCardApplyProcessLog log:logs){
				logger.info(new JsonSupport().serialize(log)+"/r/n");
			}
			result.setData(logs);
		}
		return result;
	}

	private List<ICCardApplyProcessLog> getICCardApplyProcessLogsByApplyId(String applyId)throws Exception {
		ICCardApplyProcessLogExampleExtended exampleExtended = new ICCardApplyProcessLogExampleExtended();
		exampleExtended.createCriteria().andApplyIdEqualTo(applyId);
		try {
			logger.info("开始查询审核日志...申请id为："+applyId);
			List logs = iICCardApplyManager.queryICCardApplyProcessLogByApplyID(applyId);
			if(logs != null && logs.size()>0 && !logs.isEmpty()){
				logger.info(String.format("查询IC卡申请%s操作日志记录结果：%s条数", applyId,logs.size()));
				return logs;
			}else{
				logger.info(String.format("查询IC卡申请操作日志记录结为：null"));
				return null;
			}
		} catch (Exception e) {
			logger.error("查询审核日志异常！", e);
			throw new Exception("查询审核日志异常！", e);
		}
	}


}
