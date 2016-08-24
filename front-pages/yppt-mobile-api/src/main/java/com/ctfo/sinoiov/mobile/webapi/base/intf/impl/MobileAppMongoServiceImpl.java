package com.ctfo.sinoiov.mobile.webapi.base.intf.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctfo.file.bean.AttachmentBean;
import com.ctfo.file.bean.ImageSizeBean;
import com.ctfo.file.boss.IFileService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IMobileAppMongoService;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.CommonUtil;
import com.ctfo.sinoiov.mobile.webapi.util.TaskPool;

/**
 * 
 *@Title:mongo  
 *@Description:  用于客户端上传图片保存
 *@Author:fx  
 *@Since:2015-1-20  
 *@Version:1.1.0
 */
@Service
public class MobileAppMongoServiceImpl implements IMobileAppMongoService, IJsonService{

	protected static final Log logger = LogFactory.getLog(MobileAppMongoServiceImpl.class);
	@Autowired(required=false)
	private  IFileService fileService;
	
	private static ImageSizeBean bean = new ImageSizeBean();
	
	public MobileAppMongoServiceImpl() {

		bean.setBigWidth(700);
		bean.setBigHeight(500);
		bean.setMaxWater(true);
		bean.setMinWidth(110);
		bean.setMinHeight(55);
	}
	/**
	 * 多线程上传图片
	 * @param list
	 * @param filesName
	 */
	public  void setFiles(List<AttachmentBean> list,String[] filesName,ImageSizeBean imageSizeBean){
		ArrayList tasks = new ArrayList();
		if(imageSizeBean != null){
			bean = imageSizeBean;
		}
		if(list != null && list.size()==filesName.length){
			for(int i =0;i<list.size();i++){
				final AttachmentBean fileBean = list.get(i);
				final String _fileName = filesName[i];
				Runnable task = new Runnable() {
					@Override
					public void run() {
						
						try {
							fileService.addFile(fileBean, bean,_fileName);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				tasks.add(task);
			}
		}
		if(tasks != null && tasks.size()>0){
			TaskPool.completeTask(tasks);
		}
	}
	/**
	 * 上传图片
	 */
	@Override
	public String[] saveFiles(Object... obj) {
		String[] fileNames = null;		//新生成的文件名
		File[] files = null;			//上传的图片
		String[] _fileNames = null;		//上传图片的原文件名
		List<AttachmentBean> mentBeanList = null;
		if(obj != null){
			files = (File[]) obj[0];
			_fileNames = (String[]) obj[1];
			if(files != null && files.length == _fileNames.length){
				fileNames = new String[files.length];
				mentBeanList = new ArrayList<AttachmentBean>();
				for(int i = 0;i<files.length;i++){
					AttachmentBean mentBean = new AttachmentBean();
					mentBean.setFile(files[i]);
					mentBean.setFileFileName(_fileNames[i]);
					mentBeanList.add(mentBean);
					fileNames[i] = CommonUtil.getNewFileName(CommonUtil.getExtensionName(_fileNames[i]));
				}
			}
		}
		if(mentBeanList != null && mentBeanList.size()>0){
			
			setFiles(mentBeanList, fileNames, null);
		}
		return fileNames;
		
	}
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void validate(Parameter request) throws ClientException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Parameter convertParameter(String request) {
		// TODO Auto-generated method stub
		return null;
	}

}
