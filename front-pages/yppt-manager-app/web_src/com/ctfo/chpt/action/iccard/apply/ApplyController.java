package com.ctfo.chpt.action.iccard.apply;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.chpt.service.iccard.apply.IICCardApplyService;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.file.boss.IFileService;
import com.ctfo.yppt.baseservice.card.beans.ICCardApply;
@Controller
@Scope("prototype")
public class ApplyController extends BaseControler{
	private static Log logger = LogFactory.getLog(ApplyController.class);

	private IICCardApplyService icCardApplyService;
	public ApplyController(){
		model=new ICCardApply();
	}
	/**
	 * 
	 * @Description IC卡申请记录
	 * @return
	 */
	@ResponseBody
	public PaginationResult getApplyListPage() {
		try {

			if(requestParam == null){
				requestParam = new DynamicSqlParameter();
			}
			if(requestParam.getEqual() == null){
				requestParam.setEqual( new HashMap<String, String>());
			}
			result = icCardApplyService.getListPage(requestParam);
		} catch (Exception e) {
			forwardError(((e.getMessage() == null
					|| e.getMessage().isEmpty() || e.getMessage().equals(
					EMPTY_STRING)) ? "查询信息集合异常!" : e.getMessage()));
			logger.error("查询信息集合异常!", e);
		}
		return result;
	}
	
	/**
	 * @Description IC卡审核
	 * @return
	 */
	@ResponseBody
	public PaginationResult check(String cards) {
		try {
			result = icCardApplyService.check((ICCardApply) model,index,cards);
		} catch (Exception e) {
			logger.error("审核异常！", e);
		}
		return result;
	}
	
	/**
	 * 查询申请卡详情
	 * @return
	 */
	@ResponseBody
	public PaginationResult getApplyMeta(){
		try {
			result = icCardApplyService.queryApplyMeta((ICCardApply) model, index);
		} catch (Exception e) {
			result.setDataObject("1");//出现异常
			forwardError(((e.getMessage() == null || e.getMessage().isEmpty() || e.getMessage().equals(
					EMPTY_STRING)) ? "查询信息异常!" : e.getMessage()));
			logger.error("查询信息异常!", e);
			return result;
		}
		return result;
	}
	/**
	 * 查询卡是否存在 
	 * @return
	 */
	@ResponseBody
	public PaginationResult checkICCardNum(String cardNum,String cardType){
		try {
			result = icCardApplyService.checkICCardNum(cardNum,cardType);
		} catch (Exception e) {
			forwardError(((e.getMessage() == null
					|| e.getMessage().isEmpty() || e.getMessage().equals(
					EMPTY_STRING)) ? "查询信息异常!" : e.getMessage()));
			logger.error("查询信息异常!", e);
		}
		return result;
	}
	/**
	 * 查询审核详情
	 * @return
	 */
	@ResponseBody
	public PaginationResult getApplyInfo(){
		try {
			result = icCardApplyService.getApplyInfo((ICCardApply)model);
		} catch (Exception e) {
			forwardError(((e.getMessage() == null
					|| e.getMessage().isEmpty() || e.getMessage().equals(
					EMPTY_STRING)) ? "查询信息异常!" : e.getMessage()));
			logger.error("查询信息异常!", e);
		}
		return result;
	}
	@ResponseBody
	public String getMaxImpagePath(String imageName){
		String path = this.icCardApplyService.getPicPath(imageName, IFileService.IMAGE_MAX);
		return path;
	}
	@Resource(name="iCCardApplyService", description="覆盖父类方法，注入父类的私有属性")
	public void setIcCardApplyService(IICCardApplyService icCardApplyService) {
		this.icCardApplyService = icCardApplyService;
		this.service = icCardApplyService;
	}
	
	
}
