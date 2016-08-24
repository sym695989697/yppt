package com.ypptcatch.test;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTest {
	public static ApplicationContext ctx;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ctx = new ClassPathXmlApplicationContext(new String[] { "classpath:spring-all.xml" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
