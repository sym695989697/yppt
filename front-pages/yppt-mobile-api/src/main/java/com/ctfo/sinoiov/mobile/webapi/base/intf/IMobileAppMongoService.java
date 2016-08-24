package com.ctfo.sinoiov.mobile.webapi.base.intf;

import java.util.List;


import com.ctfo.file.bean.AttachmentBean;
import com.ctfo.file.bean.ImageSizeBean;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;

/**
 * 手机上传附件接口
 * @author wangpeng
 *
 */
public interface IMobileAppMongoService {

	public  void setFiles(List<AttachmentBean> list,String[] filesName,ImageSizeBean imageSizeBean) throws ClientException;
	
	public String[] saveFiles(Object... obj);
	
}
