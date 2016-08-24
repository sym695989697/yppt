package com.ctfo.chpt.action.iccard.cardmanager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.base.util.ExcelDownLoadOrUpLoadUtil;
import com.ctfo.chpt.bean.iccard.vo.CardDetail;
import com.ctfo.chpt.bean.iccard.vo.ICCardApplyVO;
import com.ctfo.chpt.bean.iccard.vo.ICCardVO;
import com.ctfo.chpt.service.iccard.cardmanager.IICCardService;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.excel.inter.ExcelUtilImpl;
import com.ctfo.excel.inter.ExcelUtilInter;
import com.ctfo.excel.parse.ExpObj;
import com.ctfo.excel.util.ExcelVersion;
import com.ctfo.file.bean.AttachmentBean;
import com.ctfo.file.bean.ImageSizeBean;
import com.ctfo.file.boss.IFileService;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.intf.IICCardMainManager;
@Controller
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/viceCard")
public class ViceCardController extends BaseControler{
	private static Log logger = LogFactory.getLog(ViceCardController.class);
	
	@Resource(name="iCCardService",description="副卡管理service")
	private IICCardService iCCardService;
	
	private ImageSizeBean bean = new ImageSizeBean();
	private IFileService fileService=null;
	
	public ViceCardController(){
		model=new ICCard();
		bean.setBigWidth(1100);
		bean.setBigHeight(550);
		bean.setMaxWater(true);
		bean.setMinWidth(110);
		bean.setMinHeight(55);
	}
	
	public IFileService getFileService() {
		if(fileService==null){
			fileService=(IFileService) ServiceFactory.getFactory().getService(IFileService.class);
		}
		return fileService;
	}

	

	/**
	 * 分页查询副卡列表
	 * @return
	 */
	@RequestMapping(value="/queryViceCardListPage" ,produces = "application/json")
	@ResponseBody
	public PaginationResult queryViceCardListPage(){
		//return super.queryListPage();
		return iCCardService.queryViceCardListPage(requestParam);
	}
	/**
	 * 根据id查询副卡
	 * @return
	 */
	@RequestMapping(value="/queryViceCardById" ,produces = "application/json")
	@ResponseBody
	public ICCard queryViceCardById(){
		ICCard card = null;
		try {
			card = iCCardService.queryViceCardById(model);
			card.setVehicleLicense(getFileService().getFileURL(card.getVehicleLicense(),IFileService.IMAGE_MIN));
		} catch (Exception e) {
			logger.error("根据ID查询IC卡异常！",e);
		}
		
		return card;
		
	}
	/**
	 * 修改副卡信息
	 */
	@RequestMapping(value="/updateViceCard")
	@ResponseBody
	public String updateViceCard(MultipartFile uploadFile) {
		String flag="0";
		try {
			InputStream excelFile = uploadFile.getInputStream();
			String fileName = uploadFile.getOriginalFilename();
			ICCard card=new ICCard();
			card.setId(((ICCard)model).getId());
			card.setTelephoneNumber(((ICCard)model).getTelephoneNumber());
			String messageOrNot_val = request.getParameter("messageOrNot_val");
			card.setMessageOrNot(messageOrNot_val);
			String vehicleColor = request.getParameter("vehicleColor_val");
			card.setVehicleColor(vehicleColor);
			card.setVehicleNo(((ICCard)model).getVehicleNo());
			card.setModifinguser(index.getUserId());
			String filePath=null;
			if(StringUtils.isNotBlank(fileName)){
				AttachmentBean attBean=new AttachmentBean();
				File file=new File(fileName);
				ExcelDownLoadOrUpLoadUtil.inputstreamtofile(excelFile, file);
				attBean.setFile(file);
				attBean.setFileFileName(fileName);
				filePath = getFileService().addFile(attBean, bean);
				file.delete();
			}
			card.setVehicleLicense(filePath);
			iCCardService.updateViceCard(card);
			flag="1";
		} catch (Exception e) {
			logger.error("修改IC卡副卡异常！",e);
		}
		try {
			response.getWriter().write(flag);
		} catch (IOException e) {
			logger.error("上传文件异常！");
		}
		return null;
	}
	/**
	 * 根据条件查询主卡列表
	 * @return
	 */
	@RequestMapping(value="/queryMainCardByCardAreaCode" ,produces = "application/json")
	@ResponseBody
	public List queryMainCardByCardAreaCode(){
		return iCCardService.queryMainCardByCardAreaCode(requestParam);
	}
	
	/**
	 * 空卡导入模版下载
	 * @return
	 */
	@RequestMapping(value="/downLoadModel")
	public String downLoadModel(){
		try {
			ExcelUtilInter<ICCardVO> imp = new ExcelUtilImpl<ICCardVO>(ICCardVO.class);
			response.setContentType("application/vnd.ms-excel;charset=GBK");
			response.addHeader("Content-Disposition", "attachment;filename=ICCardModel.xlsx");
			OutputStream out = response.getOutputStream();
			List<ExpObj> expObjs=ExcelDownLoadOrUpLoadUtil.downLoadModel(ICCardVO.class,null);
			imp.importExcelModel(new ICCardVO(), out, expObjs, 2, ExcelVersion.VERSION_2007);
			out.close();
		} catch (Exception e) {
			logger.error("下载副卡导入模版异常!", e);
		}
		return null;
	}
	/**
	 * 批量开卡模版下载
	 * @return
	 */
	@RequestMapping(value="/downLoadOpenPatchModel")
	public String downLoadOpenPatchModel(){
		try {
			ExcelUtilInter<ICCardApplyVO> imp = new ExcelUtilImpl<ICCardApplyVO>(ICCardApplyVO.class);
			response.setContentType("application/vnd.ms-excel;charset=GBK");
			response.addHeader("Content-Disposition", "attachment;filename=openPatchCardModel.xlsx");
			OutputStream out = response.getOutputStream();
			Map<String, Map<String, String>> map=iCCardService.getOpenCardModelMap();
			List<ExpObj> expObjs=ExcelDownLoadOrUpLoadUtil.downLoadModel(ICCardApplyVO.class,map);
			ICCardApplyVO vo=new ICCardApplyVO();
			CardDetail detail = new CardDetail();
			vo.getCardList().add(detail);
			vo.getCardList().add(detail);
			imp.importExcelModel(vo, out, expObjs, 2, ExcelVersion.VERSION_2007);
			out.close();
		} catch (Exception e) {
			logger.error("下载副卡导入模版异常!", e);
		}
		return null;
	}
	
	
	/**
	 * 导入IC卡副卡
	 * @param uploadFile 上传文件对象 MultipartFile
	 * @return
	 */
	@RequestMapping(value="/icCardImport")
	@ResponseBody
	public String icCardImport(MultipartFile uploadFile){
		String fileName = null;
		InputStream excelFile = null;
		String importResultStr=null;
			try {
				excelFile = uploadFile.getInputStream();
				fileName = uploadFile.getOriginalFilename();
				importResultStr=iCCardService.importIcCard(excelFile,fileName,request);
			} catch (Exception e) {
				logger.error("上传文件异常！");
			}
			try {
				response.getWriter().write(importResultStr);
			} catch (IOException e) {
				logger.error("上传文件异常！");
			}
		return null;
	}
	
	@RequestMapping(value="/icCardOpen")
	@ResponseBody
	public String icCardOpen(MultipartFile uploadFile){
		String fileName = null;
		InputStream excelFile = null;
		String importResultStr=null;
			try {
				excelFile = uploadFile.getInputStream();
				fileName = uploadFile.getOriginalFilename();
				importResultStr=iCCardService.icCardOpen(excelFile,fileName,request);
			} catch (Exception e) {
				logger.error("上传文件异常！");
			}
			try {
				response.getWriter().write(importResultStr);
			} catch (IOException e) {
				logger.error("上传文件异常！");
			}
		return null;
	}
	
	/**
	 * 获取进度
	 * @return
	 */
	@RequestMapping(value="/getProcessByKey" ,produces = "application/json")
	@ResponseBody
	public String getProcessByKey(String processKey){
			String process="";
			try {
				if(session.getAttribute(processKey)!=null){
					process=session.getAttribute(processKey)+"";
				}else{
					process=session.getAttribute(processKey.replace("process", "message"))+"";
					session.removeAttribute(processKey.replace("process", "message"));
				}
				
			} catch (Exception e) {
				logger.error("上传文件异常！");
			}
		return process;
	}
	/**
	 * 更新余额
	 * @return
	 */
	@RequestMapping(value="/updateBlance" ,produces = "application/json")
	@ResponseBody
	public String updateBlance(){
		String flag = null;
		try {
			((ICCard)model).setModifinguser(index.getUserId());
			flag = iCCardService.updateBlance(model);
		} catch (Exception e) {
			logger.error("上传文件异常！");
		}
		return flag;
	}
	
}
