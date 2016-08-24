package com.ctfo.catchservice.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 登录检测类
 * 
 * @author jichao
 */
public class LoginCheckUtils {
	/**
	 * 是否含有中文
	 */
	public static boolean isContainsChinese(String str) {
		String regEx = "[\u4e00-\u9fa5]";
		Pattern pat = Pattern.compile(regEx);

		Matcher matcher = pat.matcher(str);
		boolean flg = false;
		if (matcher.find()) {
			flg = true;
		}
		return flg;
	}
	
	/**
	 * 检测是否为中文
	 * @param name
	 * @return
	 */
	public static boolean ChineseNameTest(String name) {
        if (!name.matches("[\u4e00-\u9fa5]{2,4}")) {
            System.out.println("只能输入2到4个汉字");
            return false;
        }else return true;
    }
	
	/**
	 * 是否含有特殊字符,"@"除外
	 */
	public static boolean isContainsSpecialCharacters(String str){
		  //String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		  String regEx = "[`~!#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		  Pattern p = Pattern.compile(regEx);
		  Matcher m = p.matcher(str);
		  return m.find();
	}
		
	/**
	 * 检测验证码长度不能超过4位
	 * @param true : 正常  false：不正常
	 */
	public static boolean checkCodeLength(String str){
		if(str==null || str.trim().length()==0){
			return false;
		}
		if(str.length()>4){
			return false;
		}
		return true;
	}
	
	/**
	 * 检测密码是否合法
	 */
	public static boolean checkPasswordLength(String str){
		if(str==null || str.trim().length()==0){
			return false;
		}
		if(str.trim().length()<6 && str.trim().length()>50){
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		String input = "Hell world!";
		System.out.println(isContainsChinese(input));
		input = "输入";
		System.out.println(isContainsChinese(input));
		
		String str = "@fsdf";
		boolean r = isContainsSpecialCharacters(str);
		System.out.println(r);
		
	}
}
