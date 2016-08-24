package com.ctfo.catchservice.util;

import java.security.*;

public class SinopecSHA1 {
	/**
	 * 加密登陆密码
	 * 
	 * @param decript
	 * @return
	 */
	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) {
		// /loginAction_login.json?memberAccount=jxf000&memberUmm=26e7ed44a60ddf99a6a4b8479d958a4d06f73749&check=0&rememberMe=on
		// http://www.sinopecsales.com/websso/YanZhengMaServlet?0.795402830337268
		System.out.println(SHA1("zj995228").equals("26e7ed44a60ddf99a6a4b8479d958a4d06f73749"));
		System.out.println(SHA1("zj995228").equals("26e7ed44a60ddf99a6a4b8479d958a4d06f73749"));
	}
}
