package com.ctfo.chpt.action.iccard.rebate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

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
import com.ctfo.chpt.bean.iccard.rebate.vo.RebateImportInfoVo;
import com.ctfo.chpt.bean.iccard.vo.ICCardVO;
import com.ctfo.chpt.service.iccard.rebate.IRebateHistoryService;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.excel.inter.ExcelUtilImpl;
import com.ctfo.excel.inter.ExcelUtilInter;
import com.ctfo.excel.parse.ExpObj;
import com.ctfo.excel.util.ExcelVersion;


@Controller
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/rebateHistory")
public class RebateHistoryController extends BaseControler{
	private static Log logger = LogFactory.getLog(RebateHistoryController.class);
	
	@Resource(name="rebateHistoryService",description="返利管理service")
	private IRebateHistoryService rebateHistoryService;
	
	private RebateHistoryController(){
		model = new RebateImportInfoVo();
	}
	
	/**
	 * 人工返利模版下载
	 * @return
	 */
	@RequestMapping(value="/downLoadModel")
	public String downLoadModel(){
		try {
			
			ExcelUtilInter<RebateImportInfoVo> imp = new ExcelUtilImpl<RebateImportInfoVo>(RebateImportInfoVo.class);
			response.setContentType("application/vnd.ms-excel;charset=GBK");
			response.addHeader("Content-Disposition", "attachment;filename=rebateHistory.xlsx");
			OutputStream out = response.getOutputStream();
			List<ExpObj> expObjs=ExcelDownLoadOrUpLoadUtil.downLoadModel(RebateImportInfoVo.class,null);
			imp.importExcelModel(new RebateImportInfoVo(), out, expObjs, 2, ExcelVersion.VERSION_2007);
			out.close();
		} catch (Exception e) {
			logger.error("下载人工返利导入模版异常!", e);
		}
		return null;
	}
	
	/**
	 * 人工返利导入
	 * @param uploadFile 上传文件对象 MultipartFile
	 * @return
	 */
	@RequestMapping(value="/rebateImport")
	@ResponseBody
	public String rebateImport(MultipartFile uploadFile){
		String fileName = null;
		InputStream excelFile = null;
		String importResultStr=null;
			try {
				excelFile = uploadFile.getInputStream();
				fileName = uploadFile.getOriginalFilename();
				importResultStr=rebateHistoryService.importRebate(excelFile,fileName,request);
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
	 * 分页查询副卡列表
	 * @return
	 */
	@RequestMapping(value="/queryRebateHistoryListPage" ,produces = "application/json")
	@ResponseBody
	public PaginationResult queryRebateHistoryListPage(){
		return rebateHistoryService.queryRebateHistoryListPage(requestParam);
	}
	/**
	 * 批量结算返利到旺金币
	 * @return
	 */
	@RequestMapping(value="/changePatchCurrency" ,produces = "application/json")
	@ResponseBody
	public String changePatchCurrency(){
		return rebateHistoryService.changePatchCurrency(ids,index.getUserId());
	}
	
}
