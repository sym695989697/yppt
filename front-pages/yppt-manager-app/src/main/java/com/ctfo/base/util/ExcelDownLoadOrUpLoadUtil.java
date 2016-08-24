package com.ctfo.base.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.ctfo.excel.inter.ExcelUtilInter;
import com.ctfo.excel.parse.ExpObj;
import com.ctfo.excel.util.ExcelUtil;

public class ExcelDownLoadOrUpLoadUtil {
	/**
	 * 生成导入模版
	 * @param modelClass
	 * @return
	 * @throws Exception
	 */
	public static List<ExpObj> downLoadModel(Class modelClass,Map<String, Map<String, String>> map) throws Exception {
		List<ExpObj> expObjs = new ExcelUtil().getExpObjs(modelClass, 2, "IMP");
		// 排序定义
		List<Integer> sort = null;
		// 定义的方法
		List<Method> methods = new ArrayList<Method>(expObjs.size());
		// 定义执行方法的对象
		List<Object> objs = new ArrayList<Object>(expObjs.size());
		for (int i = 0; i < expObjs.size(); i++) {
			objs.add(map==null?null:map.get(expObjs.get(i).getField().getName()));
			methods.add(null);
		}
		// 设置
		expObjs = new ExcelUtil().setExpObjMethodObjects(expObjs, methods,
				objs, sort);
		return expObjs;
	}
	/***
	 * 输入流转文件 
	 * @param ins
	 * @param file
	 */
	public static void inputstreamtofile(InputStream ins, File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
