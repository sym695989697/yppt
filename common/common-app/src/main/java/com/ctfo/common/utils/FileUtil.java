package com.ctfo.common.utils;

import com.ctfo.file.bean.AttachmentBean;
import com.ctfo.file.bean.ImageSizeBean;
import com.ctfo.file.boss.IFileService;

public class FileUtil {
	public static final int BIG_HEIGHT = 600;
	public static final int BIG_WIDTH = 600;
	public static final int MID_HEIGHT = 160;
	public static final int MID_WIDTH = 160;
	public static final int MIN_HEIGHT = 60;
	public static final int MIN_WIDTH = 60;

	/* (non-Javadoc)
	 * <p>Title: uploadImage</p>
	 * @author S.Wang  2014年3月12日 下午1:40:28
	 * @see com.ctfo.pltp.api.info.FileService#uploadImage(com.ctfo.file.bean.AttachmentBean, com.ctfo.file.bean.ImageSizeBean)
	 */
	public static String uploadImage(AttachmentBean attachmentBean, IFileService fileService) throws Exception {
		ImageSizeBean imageSizeBean = new ImageSizeBean();
		//大图分辨率
		imageSizeBean.setBigHeight(BIG_HEIGHT);
		imageSizeBean.setBigWidth(BIG_WIDTH);
		imageSizeBean.setMaxWater(true);
		//中图分辨率
		imageSizeBean.setMidHeight(MID_HEIGHT);
		imageSizeBean.setMidWidth(MID_WIDTH);
		//小图分辨率
		imageSizeBean.setMinHeight(MIN_HEIGHT);
		imageSizeBean.setMinWidth(MIN_WIDTH);
		String fileName = fileService.addFile(attachmentBean, imageSizeBean);
		return fileService.getFileURL(fileName, IFileService.IMAGE_MIN);
	}
}
