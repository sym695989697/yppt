package com.ctfo.chpt.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertConfigUtils{

	private static Log logger = LogFactory.getLog(PropertConfigUtils.class);
	
	private static Properties properties= new Properties();

	public static String getPropertiesValue(String key) throws IOException {
        return loadProperties().getProperty(key);
    }
	
    public static Properties loadProperties() throws IOException {
    	if(properties !=null){
    		return properties;
    	}
        InputStream input = null;
        try {
            input = PropertConfigUtils.class.getResourceAsStream("system.properties");
            properties.load(input);
        } catch (IOException e) {
        	logger.error("load 配置文件异常!", e);
            throw e;
        } finally {
            try {
                input.close();
            } catch (IOException ioe) {
                logger.error(ioe.getMessage(), ioe);
            }
        }
        return properties;
    }
}
