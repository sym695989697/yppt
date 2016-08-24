package com.ctfo.chpt.action.iccard.recharge;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.chpt.service.iccard.recharge.IICRechargeApplyService;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApply;
import com.ctfo.yppt.baseservice.recharge.cons.ICCardRechargeCons;
import com.ctfo.yppt.bean.ICCardRechageApplyMetaBean;

@Controller("iCRechargeApplyController")
@Scope("prototype")
public class ICRechargeApplyController extends BaseControler {
	private static Log logger = LogFactory.getLog(ICRechargeApplyController.class);

	private IICRechargeApplyService iICRechargeApplyService;
	private ICCardRechageApplyMetaBean metaBean;
	public ICRechargeApplyController(){
		model=new ICRechargeApply();
		metaBean = new ICCardRechageApplyMetaBean();
	}
	
	@ResponseBody
	public PaginationResult getRechargeListPage() {
		try {

			if(requestParam == null){
				requestParam = new DynamicSqlParameter();
			}
			if(requestParam.getEqual() == null){
				requestParam.setEqual( new HashMap<String, String>());
			}
			result = iICRechargeApplyService.getListPage(requestParam);
		} catch (Exception e) {
			forwardError(((e.getMessage() == null
					|| e.getMessage().isEmpty() || e.getMessage().equals(
					EMPTY_STRING)) ? "查询信息集合异常!" : e.getMessage()));
			logger.error("查询信息集合异常!", e);
		}
		return result;
	}
	@ResponseBody
	public PaginationResult queryRechargeInfo(@RequestParam("applyId") String applyId){
		try {
			return iICRechargeApplyService.queryICCardRechageApplyInfoByApplyId(applyId);
		} catch (Exception e) {
			logger.error("查询充值申请信息失败！", e);
			return null;
		}
	}
	
	@ResponseBody
	public PaginationResult queryRechargeInfoWithHandler(@RequestParam("applyId") String applyId){
		try {
			return iICRechargeApplyService.getICCardRechageApplyMetaByApplyId(index, applyId);
		} catch (Exception e) {
			logger.error("查询充值申请信息失败！", e);
			return null;
		}
	}
	@ResponseBody
	public String handleRecharge(MultipartFile certFile,String applyId) throws Exception{
		String result = null;
		try {
			result = iICRechargeApplyService.checkRechargeApply(certFile, applyId, index);
			if(StringUtils.isNotBlank(result)){
				return result;
			}
		} catch (Exception e) {
			logger.error("处理充值申请信息失败！", e);
 			throw new Exception("处理充值申请信息失败！", e);
		}
		return null;

	}
	
	@ResponseBody
	public String addRechargeApplay(){
		String flag=null;
		try {
			Long systemTime=System.currentTimeMillis();
			metaBean.getRechargeApply().setApplyTime(systemTime);
			metaBean.getRechargeApply().setState(ICCardRechargeCons.RECHARGE_APPLY_STATE_DISTR_WAITING);
			metaBean.getRechargeApply().setApplyPersonId(index.getUserId());
			metaBean.getRechargeApply().setApplyPersonName(index.getUserName());
			flag =iICRechargeApplyService.applyManagerRecharge(metaBean, index);
		} catch (Exception e) {
			logger.error("查询充值申请信息失败！", e);
		}
		return flag;
	}
	
	public ICCardRechageApplyMetaBean getMetaBean() {
		return metaBean;
	}

	public void setMetaBean(ICCardRechageApplyMetaBean metaBean) {
		this.metaBean = metaBean;
	}

	@Resource(name="iICRechargeApplyService", description="覆盖父类方法，注入父类的私有属性")
	public void setiCRechargeApplyService(IICRechargeApplyService iICRechargeApplyService) {
		this.iICRechargeApplyService = iICRechargeApplyService;
		this.service = iICRechargeApplyService;
	}
	
	
}
