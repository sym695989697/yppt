package com.ctfo.common.utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonSupport implements Serializable{

	 private transient ObjectMapper _om;
	 private ObjectMapper getOM(){
		 if( _om != null) return _om;
		 
		 ObjectMapper om = new ObjectMapper();
		 om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
			.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		 
		 this._om = om;
		 return om;
	 }
	 
	 public Object deserialize(String json,Class claz){
			
		try {
			Object v = getOM()
					.readValue(json, claz);
			
			return v;
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	 public Object deserialize(String json){
		
		 return this.deserialize(json, this.getClass());
	}
	 public String serialize(Object o){
		
		try {
			String v = getOM()
					.writeValueAsString(o);
			
			return v;
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	 
	public String toString(){
		return this.serialize(this);
	}
	 

	public static void main(String[] argv){
		System.out.println("System.class: " +  (new JsonSupport()).serialize(new HashMap())  );
	}
	
	
	
}

