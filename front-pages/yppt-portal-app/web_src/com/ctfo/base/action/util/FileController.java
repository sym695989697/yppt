package com.ctfo.base.action.util;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Constants;
import com.ctfo.common.utils.FileUtil;
import com.ctfo.common.utils.GlobalMessage;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.file.bean.AttachmentBean;
import com.ctfo.file.boss.IFileService;


/**
 * 文件相关业务控制类
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午04:32:56
 *
 */
@Controller
@Scope("prototype")
public class FileController extends BaseControler{
    private static Log logger = LogFactory.getLog(FileController.class);
	@Resource(name="fileService" ,description= "文件管理service")
    IFileService fileService;
	
    public void upload(MultipartFile file){
    	File destFile = null;
        try {
            AttachmentBean attachmentBean = new AttachmentBean();
            CommonsMultipartFile cf= (CommonsMultipartFile)file; 
            DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
            destFile = new File(fi.getStoreLocation().getParentFile().getAbsolutePath(), file.getOriginalFilename()); 
            file.transferTo(destFile);
            attachmentBean.setFileName(file.getOriginalFilename());
            attachmentBean.setFile(destFile);
            long maxLength = 3 * 1024 * 1024;//3M
            logger.debug("=======开始上传文件======");
            long fileLength = attachmentBean.getFile().length();
            logger.debug("上传文件大小："+fileLength);
            PrintWriter out = response.getWriter();
            if (fileLength <= maxLength) {
                String url = FileUtil.uploadImage(attachmentBean, fileService);
                logger.debug("上传文件成功："+url);
                out.print(url);
                out.close();
            } else {
                out.print("-1");
                out.close();
            }
        } catch (Exception e) {
            logger.debug("上传文件 失败!" + e);
        }finally{
        	if(destFile!=null){
        		try {
					destFile.delete();
				} catch (Exception e2) {
                   logger.error("删除本地附件失败",e2);
				}
        	}
        }
    }
    
    /**
     * 通过图片ID查询图片URL
     * @return
     */
    @ResponseBody
    public PaginationResult<?> queryImgUrlByImgId(@RequestParam("imgId") String imgId) {
        if (requestParam == null) {
            requestParam = new DynamicSqlParameter();
        }
        if (requestParam.getEqual() == null) {
            requestParam.setEqual(new HashMap<String, String>());
        }
        try {
            String imgUrl = fileService.getFileURL(imgId, IFileService.IMAGE_MID);
            result.setDataObject(imgUrl);
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (AAException e) {
            logger.error("======controller层======查询我的油卡列表数据异常：======", e);
            result.setMessage(GlobalMessage.FAIL);
            result.setDataObject(Constants.OPER_ERROR);
        }
        return result;
    }
}