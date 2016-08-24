package com.ctfo.chpt.service.iccard.recharge;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ctfo.base.service.ServiceImpl;
import com.ctfo.base.util.ExcelDownLoadOrUpLoadUtil;
import com.ctfo.chpt.service.iccard.apply.ICCardApplyServiceImpl;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Converter;
import com.ctfo.common.utils.FileUtil;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.soa.support.JsonSupport;
import com.ctfo.file.bean.AttachmentBean;
import com.ctfo.file.bean.ImageSizeBean;
import com.ctfo.file.boss.IFileService;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.beans.ICCardExampleExtended;
import com.ctfo.yppt.baseservice.card.intf.IICCardManager;
import com.ctfo.yppt.baseservice.recharge.beans.ICCardRechangeProcessLog;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApply;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyDetail;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyExampleExtended;
import com.ctfo.yppt.baseservice.recharge.cons.ICCardRechargeCons;
import com.ctfo.yppt.baseservice.recharge.intf.IICRechargeApplyManager;
import com.ctfo.yppt.bean.ICCardRechageApplyMetaBean;
import com.ctfo.yppt.bean.ICardRechageMetaBean;
@SuppressWarnings("rawtypes")
@Service(value = "iICRechargeApplyService")
public class ICRechargeApplyServiceImpl extends ServiceImpl implements IICRechargeApplyService {
	private static Log logger = LogFactory.getLog(ICCardApplyServiceImpl.class);
	
	private IICRechargeApplyManager iRechargeApplyManager;
	private IICCardManager iicCardManager;//卡接口
	private IFileService  fileService;
	public ICRechargeApplyServiceImpl(){
		iRechargeApplyManager = (IICRechargeApplyManager) ServiceFactory.getFactory().getService(IICRechargeApplyManager.class);
		fileService=(IFileService) ServiceFactory.getFactory().getService(IFileService.class);
		iicCardManager = (IICCardManager) ServiceFactory.getFactory().getService(IICCardManager.class);
	}
	
	
	
	@Override
	public PaginationResult<?> getListPage(DynamicSqlParameter requestParam)throws Exception {
		PaginationResult result = new PaginationResult();
		logger.info("调用查询接口...");
		try {
			ICRechargeApplyExampleExtended exampleExtended = (ICRechargeApplyExampleExtended) Converter.paramToExampleExtendedNoException(requestParam, new ICRechargeApplyExampleExtended());
			exampleExtended.setOrderByClause("apply_time desc nulls last");
			result = iRechargeApplyManager.paginate(exampleExtended);
		} catch (Exception e) {
			logger.error("调用IICRechargeApplyManager服务出现异常",e);
			throw new Exception("调用IICRechargeApplyManager服务出现异常",e);
		}
		return result;
	}


	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public PaginationResult getICCardRechageApplyMetaByApplyId(Index index,String applyId) throws Exception {
		
		PaginationResult result = new PaginationResult();
		ICardRechageMetaBean bean = null;
		
		String approvalPersonId = isUpdateApplyStatus(applyId);//如果为null需更改状态，否则为有申请人
		if(StringUtils.isBlank(approvalPersonId)){
			approvalPersonId = updateRechargeState(index,applyId);
		}
		if(approvalPersonId.equals(index.getUserId())){
			try {
				PaginationResult resultBean = this.queryICCardRechageApplyInfoByApplyId(applyId);
				bean = (ICardRechageMetaBean) resultBean.getDataObject();
			} catch (BusinessException e) {
				logger.error("调用IRechargeApplyManager服务的getICardRechageMetaBeanByapplyId方法出现异常",e);
				throw new Exception("调用IRechargeApplyManager服务的getICardRechageMetaBeanByapplyId方法出现异常",e);
			}
			if(bean == null){
				logger.error("获取大bean为null");
				throw new Exception("获取大bean为null");
			}
			List<ICRechargeApplyDetail> details = bean.getIcRechargeApplyDetails();
			Map<String, Object> map = setApplyMoneyToActualDivMoney(details);
			BigDecimal actualDivMoney = null;
			List<ICRechargeApplyDetail> applyDetails = null;
			if(!map.isEmpty()){
				 actualDivMoney = (BigDecimal) map.get("actualDivMoney");
				 applyDetails = (List<ICRechargeApplyDetail>) map.get("details");
			}
			bean.setIcRechargeApplyDetails(applyDetails);
			bean.getRechargeApply().setActualDivMoney(actualDivMoney);
			result.setData(bean.getIcRechargeApplyDetails());
			result.setDataObject(bean);
			return result;
		}else{
			result.setData(Arrays.asList("0"));
			result.setDataObject("0");//代表有审核人
			return result;
		}
	}
	@Override
	public String checkRechargeApply(MultipartFile file,String applyId,Index index)throws Exception {
		String result = null;
		String fileId = null;
		try {
			ICardRechageMetaBean model = null;
			
			if(file != null){
				fileId = saveFile(file);
			}
			if(StringUtils.isNotBlank(applyId)){
				PaginationResult res = this.queryICCardRechageApplyInfoByApplyId(applyId);
				model = (ICardRechageMetaBean) res.getDataObject();
			}
			ICRechargeApply apply = model.getRechargeApply();
	
			if(apply != null){
				if(StringUtils.isNotBlank(fileId)){
					apply.setCertFile(fileId);
				}
				result = this.reviewRechargeApply(apply, index).toString();
			}
		} catch (Exception e) {
			result=e.getMessage();
			logger.error("处理充值出现异常！",e);
			throw new Exception("处理充值出现异常！",e);
		}
		return result;
	}
	//将充值凭证存入到mongodb中
	private String saveFile(MultipartFile file) throws Exception {
		InputStream excelFile = file.getInputStream();
		String fileName = file.getOriginalFilename();
		String filePath = null;
		if(StringUtils.isNotBlank(fileName)){
			logger.debug("=======开始上传文件======");
			AttachmentBean attBean=new AttachmentBean();
			File f=new File(fileName);
			long fileLength = f.length();
			logger.debug("文件大小["+fileLength+"],文件名["+fileName+"]");
			ExcelDownLoadOrUpLoadUtil.inputstreamtofile(excelFile, f);
			attBean.setFile(f);
			attBean.setFileFileName(fileName);
			filePath = fileService.addFile(attBean);
			logger.debug("上传文件成功："+filePath);
			f.delete();
		}
         return filePath;
	}



	@Override
	public String applyManagerRecharge(ICCardRechageApplyMetaBean model,Index index)throws Exception {
		String result = null;
		try {
			if(model != null){
				List<ICRechargeApplyDetail> newRechargeDeatils = checkNumber(model);
				model.setRechargeDeatils(newRechargeDeatils);
				logger.info("开始调用充值申请接口...");
				this.iRechargeApplyManager.applyRechargeForManagerApp(model);
				result = "1";
				logger.info("充值申请完成，返回数据为："+result);
			}else{
				logger.error("充值申请单为null");
				throw new Exception("充值申请单为空");
			}
		} catch (Exception e) {
			result=e.getMessage();
			logger.error("调用IRechargeApplyManager的充值方法出现异常！",e);
		}
		return result;
	}
	//检测卡号及卡金额
	private List<ICRechargeApplyDetail> checkNumber(ICCardRechageApplyMetaBean model) throws Exception {
		List<String> cardNums = new ArrayList<String>();
		BigDecimal totalMoney = BigDecimal.ZERO;
		BigDecimal oldActualDivMoney = model.getRechargeApply().getActualDivMoney();
		List<ICRechargeApplyDetail> oldRechargeDeatils = model.getRechargeDeatils();
		logger.info("开始检测卡号是否有重复...");
		for(int i=0;i<oldRechargeDeatils.size();i++){
			ICRechargeApplyDetail detail = oldRechargeDeatils.get(i);
			String cardNum = detail.getCardNo();
			BigDecimal chargetMoney = detail.getMoney();
			if(cardNums.contains(cardNum)){
				logger.info("卡号["+cardNum+"],充值金额["+chargetMoney.toString()+"],充值失败！卡号有重复！一次每张卡只能充值一次");
				oldRechargeDeatils.remove(i);
				i--;
			}else{
				logger.info("卡号["+cardNum+"],充值金额["+chargetMoney.toString()+"]>>>>>>>>>>符合充值要求！");
				cardNums.add(cardNum);
				totalMoney = totalMoney.add(chargetMoney);
			}
		}
		logger.info("开始校验充值金额...");
		if(oldActualDivMoney != null){
			if(oldActualDivMoney.compareTo(totalMoney)!=0){
				logger.info("卡统计金额["+totalMoney.toString()+"]与统计金额["+oldActualDivMoney.toString()+"]不匹配！");
				throw new Exception("卡统计金额["+totalMoney.toString()+"]与统计金额["+oldActualDivMoney.toString()+"]不匹配！");
			}else{
				logger.info("卡统计金额["+totalMoney.toString()+"]与统计金额["+oldActualDivMoney.toString()+"]匹配！满足充值要求");
			}
		}
		logger.info("检测完成");
		
		return oldRechargeDeatils;
	}


	@Override
	public PaginationResult queryICCardRechageApplyInfoByApplyId(String applyId) throws Exception {
		PaginationResult result = new PaginationResult();
		ICardRechageMetaBean bean = null;
		try {
			logger.info("开始查询充值申请大bean！");
			bean = iRechargeApplyManager.getICardRechageMetaBeanByapplyId(applyId);
			JsonSupport jsonObj = new JsonSupport();
			logger.info("查询完成！申请信息["+jsonObj.serialize(bean.getRechargeApply())+"]/r/n");
			logger.info("申请详情信息["+bean.getIcRechargeApplyDetails().size()+"]条！/r/n");
			if(bean!=null){
				ICardRechageMetaBean newBean = updateBigBeanInfo(bean);
				result.setData(newBean.getIcRechargeApplyDetails());
				result.setDataObject(newBean);
			}else{
				throw new Exception("获取大bean为Null!");
			}
			
		} catch (BusinessException e) {
			logger.error("调用IRechargeApplyManager服务的getICardRechageMetaBeanByapplyId方法出现异常",e);
			throw new Exception("调用IRechargeApplyManager服务的getICardRechageMetaBeanByapplyId方法出现异常",e);
		}
		return result;
	}
	//更新充值凭证图片路径
	private ICardRechageMetaBean updateBigBeanInfo(ICardRechageMetaBean bean) throws Exception {
		ICRechargeApply oldApply = bean.getRechargeApply();
		List<ICRechargeApplyDetail> oldDetails = bean.getIcRechargeApplyDetails();
		
		ICRechargeApply newApply = updateApplyInfo(oldApply);
		List<ICRechargeApplyDetail> newDetails = updateApplyDetailInfo(oldDetails);
		bean.setRechargeApply(newApply);
		bean.setIcRechargeApplyDetails(newDetails);
		return bean;
	}
	private List<ICRechargeApplyDetail> updateApplyDetailInfo(
			List<ICRechargeApplyDetail> oldDetails) throws Exception {
		List<ICRechargeApplyDetail> newDetails = new ArrayList<ICRechargeApplyDetail>();
		for(int i=0;i<oldDetails.size();i++){
			ICRechargeApplyDetail detail = oldDetails.get(i);
			String cardNumber = detail.getCardNo();
			String parentCardNumber = detail.getParentCardNumber();
			String cardAreaCode = detail.getCardAreaCode();
			String cardType = detail.getCardType();
			if(StringUtils.isNotBlank(cardNumber)){
				ICCard iccard = getICCardByCardNum(cardNumber);
				if(iccard==null){
					logger.info("卡号为["+cardNumber+"]的卡信息没有查到！");
					return oldDetails;
				}
				if(StringUtils.isBlank(cardAreaCode)){
					logger.info("卡号为["+cardNumber+"]的发卡地区为空，需设置发卡地区！");
					detail.setCardAreaCode(iccard.getOpencardofficeid());
					logger.info("卡号为["+cardNumber+"]的发卡地区为["+iccard.getOpencardofficeid()+"]");
				}
				if(StringUtils.isBlank(cardType)){
					logger.info("卡号为["+cardNumber+"]的卡类型为空，需设置卡类型");
					detail.setCardType(iccard.getCardType());
					logger.info("卡号为["+cardNumber+"]的卡类型为["+iccard.getCardType()+"]");
				}
				if(StringUtils.isBlank(parentCardNumber)){
					logger.info("卡号为["+cardNumber+"]的主卡卡号为空，需设置主卡卡号！");
					detail.setParentCardNumber(iccard.getParentCardNumber());
					logger.info("卡号为["+cardNumber+"]的主卡号为["+iccard.getParentCardNumber()+"]");
				}
			}
			newDetails.add(detail);
		}
		return newDetails;
	}



	private ICCard getICCardByCardNum(String cardNumber) throws Exception {
		ICCardExampleExtended exampleExtended = new ICCardExampleExtended();
		exampleExtended.createCriteria().andCardNumberEqualTo(cardNumber);
		ICCard iccard = null;
		try {
			logger.info(">>>>>>>>调用IC卡查询接口！卡号为："+cardNumber);
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



	private ICRechargeApply updateApplyInfo(ICRechargeApply oldApply) {
		String certFile = oldApply.getCertFile();
		String applyName = oldApply.getApplyPersonName();
		if(StringUtils.isNotBlank(certFile)){
			logger.info("开始查找文件名为["+certFile+"]的全路径！");
			String path = this.fileService.getFileURL(certFile);
			logger.info("查找完成，图片名为["+certFile+"]的全路径为["+path+"]");
			oldApply.setCertFile(path);
		}else{
			logger.error("文件地址为空");
		}
		if(StringUtils.isBlank(applyName)){
			logger.info("没有申请人姓名开始更新申请人姓名");
			oldApply.setApplyPersonName(oldApply.getUserName());
		}
		return oldApply;
	}



	//更新充值
	private String updateRechargeState(Index index, String applyId) throws Exception {
		ICRechargeApply apply = new ICRechargeApply();
		String userId = index.getUserId();
		String userName = index.getUserName();
		logger.info("开始绑定处理人：处理人id["+userId+"],处理人姓名：["+userName+"]");
		int flag = iRechargeApplyManager.auditRechargeLock(applyId, userId, userName);
		logger.info("绑定完成，绑定结果为：["+flag+"]");
		return userId;
	}
	private Integer reviewRechargeApply(ICRechargeApply apply, Index index) throws Exception {
		String status = apply.getState();
//		String userId = index.getUserId();
//		String userName = index.getUserName();
		String applyId = apply.getId();
		String certFile = apply.getCertFile();
		Integer result = null;
		ICardRechageMetaBean bean = null;
		ICCardRechangeProcessLog log = null;
		if(StringUtils.isNotBlank(applyId)){
				bean = this.iRechargeApplyManager.getICardRechageMetaBeanByapplyId(applyId);
			
				if(bean != null){
					if(StringUtils.isNotBlank(certFile)){
						logger.info("更新充值申请id为["+applyId+"]充值凭证文件名为：["+certFile+"]");
						bean.getRechargeApply().setCertFile(certFile);
					}
					List<ICRechargeApplyDetail> details = bean.getIcRechargeApplyDetails();
					Map<String, Object> map = setApplyMoneyToActualDivMoney(details);
					BigDecimal actualDivMoney = null;
					List<ICRechargeApplyDetail> applyDetails = null;
					if(!map.isEmpty()){
						 actualDivMoney = (BigDecimal) map.get("actualDivMoney");
						 applyDetails = (List<ICRechargeApplyDetail>) map.get("details");
					}
					if(actualDivMoney!=null && actualDivMoney.compareTo(BigDecimal.ZERO)>0){
						bean.setIcRechargeApplyDetails(applyDetails);
						bean.getRechargeApply().setActualDivMoney(actualDivMoney);
					}
				}else{
					logger.info("获取大bean为null");
					throw new Exception("获取大bean为null");
				}
				logger.info("开始创建充值申请流程日志...充值申请id为：["+applyId+"]");
				log = addRechangeProcessLog(index, apply);
				JsonSupport js = new JsonSupport();
				logger.info("创建充值申请流程日志完成："+js.serialize(log));
				logger.info(">>>>>>>>>>开始充值审核...");
				result = this.iRechargeApplyManager.auditRecharge(bean, log);
				logger.info("充值审核完成，审核状态码为："+result);
			
		}
		return result;
		
	}


	private Map<String,Object> setApplyMoneyToActualDivMoney(List<ICRechargeApplyDetail> details) {
		BigDecimal actualDivMoney = new BigDecimal("0.00");
		Map<String,Object> map = new HashMap<String, Object>();
		logger.info("开始将申请金额转换成实际金额...");
		for(int i=0;i<details.size();i++){
			ICRechargeApplyDetail detail = details.get(i);
			String cardNum = detail.getCardNo();
			BigDecimal applyMoney = detail.getMoney();
			logger.info("卡号为["+cardNum+"]的申请金额为["+applyMoney.toString()+"]");
			detail.setActualDivMoney(applyMoney);
			actualDivMoney = actualDivMoney.add(applyMoney);
		}
		logger.info("最终实际充值金额为["+actualDivMoney.toString()+"]");
		map.put("actualDivMoney", actualDivMoney);
		map.put("details", details);
		return map;
	}



	private String isUpdateApplyStatus(String applyId) throws Exception {
		ICRechargeApply apply = new ICRechargeApply();
		apply.setId(applyId);
		try {
			logger.info("开始调用查询服务接口...查询时否有审核人！申请id["+applyId+"]");
			ICRechargeApply applyOld = iRechargeApplyManager.getById(apply);
			if(applyOld!=null){
				String approvalPersonId = applyOld.getApprovalPersonId();
				if(StringUtils.isNotBlank(approvalPersonId)){
					logger.info("查询完成,查询的审核人id为:"+approvalPersonId);
					return approvalPersonId;
				}
			}
		} catch (Exception e) {
			logger.info("调用iRechargeApplyManager服务的getById方法出现异常！",e);
			throw new Exception("调用iRechargeApplyManager服务的getById方法出现异常！",e);
		}
		return null;
		
	}
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
	private ICCardRechangeProcessLog addRechangeProcessLog(Index index,ICRechargeApply apply) {
		ICCardRechangeProcessLog log = new ICCardRechangeProcessLog();
		log.setApplyId(apply.getId());
		log.setApprovalPerson(index.getUserName());
		log.setApprovalPersonId(index.getUserId());
		log.setApprovalRoleId(index.getRoleId());
		log.setApprovalRole(index.getRoleName());
		log.setApprovalState(apply.getState());
		log.setApprovalTime(System.currentTimeMillis());
		log.setStepNo("1");
		return log;
	}
}
