package com.ctfo.base.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.excel.inter.ExcelUtilImpl;
import com.ctfo.excel.inter.ExcelUtilInter;
import com.ctfo.excel.parse.ExpObj;
import com.ctfo.excel.util.ExcelVersion;
import com.ctfo.file.bean.AttachmentBean;
import com.ctfo.file.bean.ExcelBean;
import com.ctfo.file.boss.IFileService;
import com.ctfo.file.boss.IMongoService;

public class ExcelPutIntoMongoDBImpl<T> {
	
	private ExcelUtilInter ex;
	private Class<T> clazz;
	private IFileService iFileService;
	private IMongoService iMongoService;
	
	public ExcelPutIntoMongoDBImpl(Class<T> clazz){
		this.ex = new ExcelUtilImpl<T>(clazz);
		this.clazz = clazz;
		iFileService=(IFileService)ServiceFactory.getFactory().getService(IFileService.class);
		iMongoService=(IMongoService)ServiceFactory.getFactory().getService(IMongoService.class);
	}
	
	public void createWorkBook(List<ExpObj> expobj, int parent, boolean type) throws Exception{
		ex.createWorkbook(clazz, expobj, parent, ExcelVersion.VERSION_2007, type);
	}
	
	public void appendWorkbook(Collection<T> dataset) throws Exception{
		ex.appendWorkbook(dataset);
	}
	
	public void appendSheetTitle(Class<T> clazz, List<ExpObj> expobj, int parent, String sheetName, Collection<T> dataset) throws Exception{
		ex.appendSheetTitle(clazz, expobj, parent, sheetName, dataset);
	}
	
	public void appendWorkbookBySheet(Collection<T> dataset, List<ExpObj> expobj, String sheetName){
		ex.appendWorkbookBySheet(dataset, expobj, sheetName);
	}
	
	
	//往mongoDB插入bean，ie_type（1为导出，2为导入）
	public ExcelBean addBean(String operatorId, String time, String fileName, String type, String ie_type) throws Exception{
		ExcelBean bean = new ExcelBean();
		bean.setSubmiter(operatorId);
		bean.setTime(time);
		bean.setFileName(fileName);
		bean.setType(type);
		bean.setState("0");
		bean.setIe_type(ie_type);
		bean.setId(UUID.randomUUID().toString());
		iMongoService.save(bean);
		return bean;
	}
	
	//插入文件，并返回对应的url
	public String addFile() throws Exception{
		File file = File.createTempFile(String.valueOf(new Date().getTime()), ".xls");//根据时间创建临时文件
		OutputStream out = new FileOutputStream(file);
		
		ex.writerWorkbook(out);
		
		String url = getExcelUrl(file);
		
		file.delete();//删除临时文件
		return url;
	}
	
	//修改之前插入mongoDB的bean内容
	public void updateBean(ExcelBean bean, String url) throws Exception{
		if(StringUtils.isNotBlank(url)){
			if(url.substring((url.length() - 1), url.length()).equals(",")){//如果最后存在一个","号，则去除掉
				url = url.substring(0, (url.length() - 1));
			}
		}
		bean.setUrl(url);
		bean.setState("1");
		iMongoService.update(bean);
	}
	
	public String getExcelUrl(File file) throws Exception {
		
		AttachmentBean bean = new AttachmentBean();
		bean.setFile(file);
		bean.setFileName(file.getName());
		String filePath = iFileService.addFile(bean);
//		
//		
//		
//		
//		// TODO Auto-generated method stub
//		if(file==null || !file.exists()){
//			throw new Exception("Input file can not be a null value and it must be exist!");
//		}
//		//生成新的文件名
//		String newName = FileValidate.getNewFileName("xls");
//		String tableName = FileValidate.getTableNameByFileName(file.getName());
//		//将文件存入数据库
//		String fileName = fileDao.saveFile(file,newName, tableName);
		return filePath;
	}
	public ExcelUtilInter getEx() {
		return ex;
	}
}
