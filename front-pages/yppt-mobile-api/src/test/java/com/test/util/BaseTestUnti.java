package com.test.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Random;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.util.MD5Utils;
import com.ctfo.sinoiov.mobile.webapi.util.PropertyUtils;
import com.ctfo.util.DateUtil;

public class BaseTestUnti {
	
	public static String getParam(String servCode, Object body) {
		Parameter param = new Parameter();
		Head head = new Head();
		
		head.setServCode(servCode);
		head.setCallTime(String.valueOf(System.currentTimeMillis()));
		Random r = new Random();
		head.setSequenceNum(String.valueOf(r.nextLong()));


		param.setBody(body);
		param.setHead(head);
		param.setSign(PropertyUtils.getValueByKey("SIGN_KEY"));

		JsonConfig jf = getconfigJson(
				new String[] { "result", "errorMessage" },
				DateUtil.DEFAULT_FORMATSHORT);

		JSONObject obj = JSONObject.fromObject(param, jf);
		String jsonStr = obj.toString();
		param.setSign(MD5Utils.getDefaultMd5String(jsonStr));
		obj = JSONObject.fromObject(param, jf);
		System.out.println("请求参数：" + obj.toString());
		
		//return Base64.encodeBase64String(obj.toString().getBytes(Charset.forName("UTF-8")));
		/*String str = null;
		try{
			str = ru.publicEnrypt(obj.toString(), PropertyUtils.getValueByKey("PUBLIC_KEY"));
		}catch(Exception e){e.printStackTrace();}*/
		return new String(obj.toString().getBytes(Charset.forName("UTF-8")));
	}
	
	private static JsonConfig getconfigJson(String[] excludes,
			String datePattern) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);

		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

		return jsonConfig;
	}
	
	public static String getRetureFromUrl(String url) throws Exception{
		String result = "";
		URL U = new URL(url);
		
		URLConnection connection = U.openConnection();
		connection.setRequestProperty("Accept-Charset", "UTF-8");
		
		
		
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		if(in != null){
			while ((line = in.readLine()) != null) {
				result += line;
			}
		}
		connection.connect();
//		result = new String(Base64.decodeBase64(result), "UTF-8");
		result = new String(result.toString().getBytes(Charset.forName("UTF-8")));
		in.close();
		return result;
	}
}
