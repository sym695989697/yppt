package com.ctfo.chpt.action.icard.reconciliation;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

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
import com.ctfo.chpt.bean.icard.reconciliation.vo.ICCardReconciliationByShVO;
import com.ctfo.chpt.bean.icard.reconciliation.vo.ICCardReconciliationVO;
import com.ctfo.chpt.service.icard.reconciliation.IReconciliationShService;
import com.ctfo.chpt.service.icard.reconciliation.IReconciliationSyService;
import com.ctfo.chpt.service.icard.reconciliation.IReconciliationSyServiceImpl;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.excel.inter.ExcelUtilImpl;
import com.ctfo.excel.inter.ExcelUtilInter;
import com.ctfo.excel.parse.ExpObj;
import com.ctfo.excel.util.ExcelVersion;
import com.ctfo.file.boss.IFileService;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoHistory;

/**
 * 
 * 项目名称：yppt-manager-app<br>
 * *********************************<br>
 * <P>
 * 类名称：MainCardController
 * </P>
 * *********************************<br>
 * <P>
 * 类描述：主卡功能逻辑Controller：ReconciliationController
 * </P>
 * 创建人：李飞<br>
 * 创建时间：2015年1月21日 下午1:12:15<br>
 * 修改人：李飞<br>
 * 修改时间：2015年1月21日 下午1:12:15<br>
 * 修改备注：<br>
 * 
 * @version 1.0<br>
 */

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/reconciliation")
public class ReconciliationController extends BaseControler {

	private static Log logger = LogFactory.getLog(ReconciliationController.class);
	private IFileService fileService = null;

	@Resource(name = "iReconciliationSyService", description = "中石油-对账文件管理service")
	private IReconciliationSyService iReconciliationSyService;

	@Resource(name = "iReconciliationShService", description = "中石化-对账文件管理service")
	private IReconciliationShService iReconciliationShService;

	public ReconciliationController() {
		model = new ICCardTradeInfoHistory();
		service = new IReconciliationSyServiceImpl();
	}

	public IFileService getFileService() {
		if (fileService == null) {
			fileService = (IFileService) ServiceFactory.getFactory().getService(IFileService.class);
		}
		return fileService;
	}

	/**
	 * 分页查询对账列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryReconciliationListPage", produces = "application/json")
	@ResponseBody
	public PaginationResult<?> queryReconciliationListPage() {
		requestParam = requestParam == null ? new DynamicSqlParameter() : requestParam;
		result = iReconciliationSyService.queryReconciliationListPage(requestParam);
		return result;
	}
	
	/**
	 * 对账导入模版下载
	 * 
	 * @return
	 */
	@RequestMapping(value = "/downLoadModel")
	public String downLoadModel() {
		try {
			ExcelUtilInter<ICCardReconciliationVO> imp = new ExcelUtilImpl<ICCardReconciliationVO>(ICCardReconciliationVO.class);
			response.setContentType("application/vnd.ms-excel;charset=GBK");
			response.addHeader("Content-Disposition", "attachment;filename=ICCardReconciliationModel-SY.xlsx");
			OutputStream out = response.getOutputStream();
			Map<String, Map<String, String>> map = iReconciliationSyService.getImportReconciliationModelMap();
			List<ExpObj> expObjs = ExcelDownLoadOrUpLoadUtil.downLoadModel(ICCardReconciliationVO.class, map);
			imp.importExcelModel(new ICCardReconciliationVO(), out, expObjs, 2, ExcelVersion.VERSION_2007);
			out.close();
		} catch (Exception e) {
			logger.error("下载副卡导入模版异常!", e);
		}
		return null;
	}

	/**
	 * 对账导入模版下载
	 * 
	 * @return
	 */
	@RequestMapping(value = "/downLoadModelForSh")
	public String downLoadModelForSh() {
		try {
			ExcelUtilInter<ICCardReconciliationByShVO> imp = new ExcelUtilImpl<ICCardReconciliationByShVO>(ICCardReconciliationByShVO.class);
			response.setContentType("application/vnd.ms-excel;charset=GBK");
			response.addHeader("Content-Disposition", "attachment;filename=ICCardReconciliationModel-SH.xlsx");
			OutputStream out = response.getOutputStream();
			Map<String, Map<String, String>> map = iReconciliationShService.getImportReconciliationModelMap();
			List<ExpObj> expObjs = ExcelDownLoadOrUpLoadUtil.downLoadModel(ICCardReconciliationByShVO.class, map);
			imp.importExcelModel(new ICCardReconciliationByShVO(), out, expObjs, 2, ExcelVersion.VERSION_2007);
			out.close();
		} catch (Exception e) {
			logger.error("下载副卡导入模版异常!", e);
		}
		return null;
	}

	/**
	 * 导入对账文件
	 * 
	 * @param uploadFile
	 *            上传文件对象 MultipartFile
	 * @return
	 */
	@RequestMapping(value = "/importReconciliationFile")
	@ResponseBody
	public String importReconciliationFile(MultipartFile uploadFile) {
		String fileName = null;
		InputStream excelFile = null;
		String importResultStr = null;
		try {
			excelFile = uploadFile.getInputStream();
			fileName = uploadFile.getOriginalFilename();
			importResultStr = iReconciliationSyService.importReconciliationFile(excelFile, fileName, request);
		} catch (Exception e) {
			logger.error("上传文件异常！");
		}
		return importResultStr;
	}

	/**
	 * 导入对账文件
	 * 
	 * @param uploadFile
	 *            上传文件对象 MultipartFile
	 * @return
	 */
	@RequestMapping(value = "/importReconciliationFileSh")
	@ResponseBody
	public String importReconciliationFileSh(MultipartFile uploadFile) {
		String fileName = null;
		InputStream excelFile = null;
		String importResultStr = null;
		try {
			excelFile = uploadFile.getInputStream();
			fileName = uploadFile.getOriginalFilename();
			importResultStr = iReconciliationShService.importReconciliationFile(excelFile, fileName, request);
		} catch (Exception e) {
			logger.error("上传文件异常！");
		}
		return importResultStr;
	}
	/**
	 * 获取进度
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getProcessByKey", produces = "application/json")
	@ResponseBody
	public String getProcessByKey(String processKey) {
		String process = "";
		try {
			if (session.getAttribute(processKey) != null) {
				process = session.getAttribute(processKey) + "";
			} else {
				process = session.getAttribute(processKey.replace("process", "message")) + "";
				session.removeAttribute(processKey.replace("process", "message"));
			}

		} catch (Exception e) {
			logger.error("上传文件异常！");
		}
		return process;
	}
}
