package com.ctfo.chpt.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelExportUtil {

	public static Workbook createExcel(List<?> list, Class<?> clazz) {
		List<String> titles = new ArrayList<String>();
		clazz.getDeclaredAnnotations();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			 XmlElement e = field.getAnnotation(XmlElement.class);
			 titles.add(e.name());
		}
		// 取得实际泛型类
		Workbook workbook = new SXSSFWorkbook();;
		// 生成一个表格
		Sheet sheet = workbook.createSheet(clazz.getSimpleName());
//
//		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(15);
		//标题行
		Row row = sheet.createRow(0);
		Cell cell;
		row.setHeight((short) 500);
		for (int i = 0; i < titles.size(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(titles.get(i));
		}
		//body
		for (int i = 0; i < list.size(); i++) {
			Object o = list.get(i);
			row = sheet.createRow(i+1);
			for (int j = 0; j < fields.length; j++) {
				Field field = fields[j];
				String methdName = String.valueOf(field.getName().charAt(0)).toUpperCase()+field.getName().substring(1);
				Method method;
				try {
					method = clazz.getDeclaredMethod("get"+methdName, new Class[]{});
					cell = row.createCell(j);
					cell.setCellValue(method.invoke(o, new Object[]{})!=null?String.valueOf(method.invoke(o, new Object[]{})):"");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return workbook;
	}
}
