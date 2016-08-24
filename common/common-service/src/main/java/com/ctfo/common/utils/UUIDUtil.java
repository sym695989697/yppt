package com.ctfo.common.utils;

public class UUIDUtil {
	/***
	 * 生成一个新的UUID
	 * @return
	 */
	public static String newUUID() {
		return java.util.UUID.randomUUID().toString();
	}
	/***
	 * 生成一个UUID，去除掉其中的“-”
	 * @return
	 */
	public static String newUUID2() {
		return java.util.UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
}
