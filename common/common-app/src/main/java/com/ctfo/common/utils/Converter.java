package com.ctfo.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Rule;



public class Converter {
	private static Log logger = LogFactory.getLog(Converter.class);
	
	public static final String OP_SUCCESS="操作成功";
	public static final String OP_FAILED="操作失败";
	public static final String SYS_SIGN="system.code";
	
	public static final String SESSION_INDEX="session-index";
	public static final String SESSION_OIL_CARD_CHARGE_DATA="session-oil-card-charge-data";
	public static final String SESSION_REMOTE_USER="session-remote-user";
	public static final String SESSION_TICKET_ID="session-ticket-id";
	
	public static final String SESSION_MENU_LIST="session-menuList";
	public static final String SESSION_FUNC_LIST="session-funList";
	public static final String CONTEXT_FULL_PERMISSION="context-fullList";
	
	//存储session，为切换角色准备数据
	public static final String SESSION_ROLE_LIST="session-roleList";
	
	
	

	public static String str(String ins){
		if( ins == null || "".equals(ins.trim())) return "";
		return ins;
	}
	
	
	/**
	 * 将model对象转化成Extended对象，用于构造查询条件
	 * @param objExtended
	 * @param model
	 * @return Extended对象
	 * @throws Exception
	 */
	public static final Object copyModelToExtended(Object objExtended, Object model)throws Exception{
		
		Object criteria =   Converter.publicCall(objExtended, "createCriteria", new Class[]{}, new Object[]{});
		if( criteria == null) throw new IllegalArgumentException("newExample is not Ibatis Example parameter.");
		
		Method[] methods = model.getClass().getMethods();
		String methodName ="";
		Object valObj;
		Method andMethod;
		for(Method met : methods){
			if(!"getClass".equals(met.getName()) && "get".equals(met.getName().substring(0, 3))){
				if(StringUtils.hasText((String)met.invoke(model)))continue;
				methodName = met.getName();				
				valObj = met.invoke(model);
				andMethod = Converter.getMethod(criteria.getClass(), "and"+methodName.substring(3,methodName.length())+"EqualTo", false);				
				if( andMethod == null)continue;				
				andMethod.invoke(criteria, new Object[]{valObj});		
			}
		}
		
		return objExtended;
	}

	/**
	 * 转化 DynamicSqlParameter 参数 为 ibatis example 对象，用于构造查询条件
	 * @param dsp 原有查询条件
	 * @param newExample 创建新的查询对象
	 * @return  返回相应查询条件的查询对象
	 * @throws ClassNotFoundException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static final Object paramToExampleExtendedNoException(DynamicSqlParameter dsp, Object newExample){
		try{
			return paramToExampleExtended( dsp,  newExample);
		}catch(Exception e){ 
			logger.warn("转化 DynamicSqlParameter 参数 为example 对象失败!", e);
		}
		return null;
	}
	public static final Object paramToExampleExtendedCriteriaNoException(DynamicSqlParameter dsp, Object newExample){
		try{
			return paramToExampleExtendedCriteria( dsp,  newExample);
		}catch(Exception e){ 
			logger.warn("转化 DynamicSqlParameter 参数 为example 对象失败!", e);
		}
		return null;
	}
	public static final Object paramToExampleExtended(DynamicSqlParameter dsp, Object newExample) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		paramToExampleExtendedCriteria(dsp,newExample);
		return newExample;
	}
	public static final Object paramToExampleExtendedCriteria(DynamicSqlParameter dsp, Object newExample) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		if( dsp == null && newExample == null) return null;
		
		if(dsp.getEqual()!=null && dsp.getEqual().get("modelName")!=null)//删除之前所需的参数
			dsp.getEqual().remove("modelName");
		
		Object criteria =   Converter.publicCall(newExample, "createCriteria", new Class[]{}, new Object[]{});
		if( criteria == null) throw new IllegalArgumentException("newExample is not Ibatis Example parameter.");
		if( dsp == null) return criteria;
		
		String exaName = newExample.getClass().getName();
		String beanName = ( exaName.endsWith("Example"))? exaName.substring(0, exaName.length()-7)   :
			( ( exaName.endsWith("ExampleExtended"))?  exaName.substring(0, exaName.length()-15)  : null ) ;
		if( beanName == null) throw new IllegalArgumentException("newExample :" + exaName + " , cann't found bean  XXXExampleExtended.");
		
		Class beanClass =  Class.forName(beanName) ;
		
		if( beanClass == null) throw new IllegalArgumentException("newExample :" + exaName + " , cann't found bean");
		

		/**
		 * detail query 
		 * by malq 2012-12-18 
		 */
		if(dsp.getRules()!=null && dsp.getRules().size()>0){
			andCriteria(criteria, beanClass, dsp.getRules());
		}
		
		Map<String, String> params = null;
		//// handle  EqualTo
		params = dsp.getEqual();
		if( params != null )
			andCriteria(criteria, beanClass, params, "EqualTo");
		
		params = dsp.getNotequal();
		if( params != null )
			andCriteria(criteria, beanClass, params, "NotEqualTo");
		
		params = dsp.getLike();
		if( params != null )
			andCriteria(criteria, beanClass, params, "Like");
		
		Map<String,Object> temp = dsp.getInMap();
		if( temp != null ){
			params = new HashMap<String,String>();
			for( String key: temp.keySet()){
				Object tempv = temp.get(key);
				if( tempv instanceof Object[]){
					tempv = Arrays.asList((Object[])tempv);
				}
				if( tempv instanceof List){
					List invals = (List) tempv;
					for(int i=0; i<invals.size(); i++){
						if( i==0){
							tempv =  ""+ invals.get(i);
						}else{
							tempv = tempv + "," + invals.get(i) ;
						}
					}
				}
				
				params.put(key, tempv.toString());
			}
			andCriteria(criteria, beanClass, params, "In");
		}
		
		Map<String,Object> nottemp = dsp.getNotInMap();
		if( nottemp != null ){
			params = new HashMap<String,String>();
			for( String key: nottemp.keySet()){
				Object tempv = nottemp.get(key);
				if( tempv instanceof Object[]){
					tempv = Arrays.asList((Object[])tempv);
				}
				if( tempv instanceof List){
					List invals = (List) tempv;
					for(int i=0; i<invals.size(); i++){
						if( i==0){
							tempv =  ""+ invals.get(i);
						}else{
							tempv = tempv + "," + invals.get(i) ;
						}
					}
				}
				
				params.put(key, tempv.toString());
			}
			andCriteria(criteria, beanClass, params, "NotIn");
		}
		
		params = dsp.getStartwith();
		if( params != null )
			andCriteria(criteria, beanClass, params, "GreaterThanOrEqualTo");
		
		params = dsp.getEndwith();
		if( params != null ){
			andCriteria(criteria, beanClass, params, "LessThanOrEqualTo");
			//andCriteria(criteria, beanClass, params, "LessThan");
		}
		
		////handle orderBy
		String orderField = dsp.getOrder();
		String orderSort = dsp.getSort();
		if( orderField != null && !"".equals(orderField)){
			Method m = getMethod(beanClass, "field" + orderField, true);
			
			String field = orderField;
			if( m != null){
				Object ofield = m.invoke(null , new Object[]{}	);
				 field = ofield.toString();
				 Converter.publicCall(newExample, "setOrderByClause", new Class[]{String.class}, new Object[]{ field + " " + ((orderSort!=null)?orderSort : " ") } ) ;
			}
			
			
		}
		
		//handle pagination
		int start = dsp.getStartNum();
		int end = dsp.getEndNum();
		if( end != 0 ){
			Converter.publicCall(newExample, "setSkipNum", new Class[]{int.class}, new Object[]{ Integer.valueOf(start) } ) ;
			Converter.publicCall(newExample, "setEndNum", new Class[]{int.class}, new Object[]{ Integer.valueOf(end+1) } ) ;
		}
		
		
		return criteria;
	}
	
	
	private static void andCriteria(Object criteria, Class beanClass, Map<String, String> params , String operator ) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		if( params !=null){
			for( String key : params.keySet() ){
				//ignore the "" string
				String v = params.get(key);
				if( v == null || "".equals(v.trim())) continue;
				
				String mkey = (key.charAt(0)+"").toUpperCase() + key.substring(1);
				Method getM = Converter.getMethod(beanClass, "get"+mkey, false);
				if( getM == null){
					LogFactory.getLog(Converter.class).warn("当拼写查询条件时有无效属性", new IllegalArgumentException("没有找到对应的GET方法："+key)) ;
					continue;
				}
				Class fieldClaz = getM.getReturnType();
				
				Method m = Converter.getMethod(criteria.getClass(), "and"+mkey+operator , false);
				if( m == null){
					continue;
				}
				
				Class[] pts = m.getParameterTypes();
				Object[] vals = new Object[pts.length];
				
				
				int i =0;
				for( Class pt : pts){
					
					vals[i] = convertType( pt, fieldClaz, params.get(key) ); 
					i++;
				}
				
				m.invoke(criteria, vals);
				
			}
			
		}
	}
	
	/**
	 * 拼写查询条件方法，此方法是重载方法
	 * @param criteria
	 * @param beanClass
	 * @param ruleList
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @author malq
	 * @date 2012-12-18
	 */
	private static void andCriteria(Object criteria, Class beanClass, List<Rule> ruleList) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		
			for(Rule rule : ruleList){
				if(rule.getValue()==null || "".equals(rule.getValue())) continue;
				
				String mkey = (rule.getField().charAt(0)+"").toUpperCase() + rule.getField().substring(1);
				Method getM = Converter.getMethod(beanClass, "get"+mkey, false);
				if( getM == null){
					LogFactory.getLog(Converter.class).warn("当拼写查询条件时有无效属性", new IllegalArgumentException("没有找到对应的GET方法："+rule.getField())) ;
					continue;
				}
				Class fieldClaz = getM.getReturnType();
				
				Method m = Converter.getMethod(criteria.getClass(), "and"+mkey+rule.getOp() , false);
				if( m == null){
					continue;
				}
				
				Class[] pts = m.getParameterTypes();
				Object[] vals = new Object[pts.length];
								
				int i =0;
				for( Class pt : pts){					
					vals[i] = convertType( pt, fieldClaz, rule.getValue()); 
					i++;
				}				
				m.invoke(criteria, vals);				
			}		
	}
	
	
	private static Object convertType(Class claz, Class fieldclaz,  String v){
		if( Long.class.equals(claz)){
			return Long.valueOf( v);
		}else if( Integer.class.equals(claz)){
			return Integer.valueOf(v);
		}else if( Short.class.equals(claz)){
			return Short.valueOf(v);
			
		}else if( BigDecimal.class.equals(claz)){
			return new BigDecimal(v);
			
		}else if( List.class.equals(claz)){
			StringTokenizer stoken = new StringTokenizer( v , ",");
			List ret = new ArrayList();
			while( stoken.hasMoreTokens()){
				ret.add( convertType(fieldclaz, fieldclaz, stoken.nextToken()));
			}
			return ret;
		}else{
			return v;
		}
	}
	
	private static Map<Class, Map<String,Method>> cachedM = Collections.synchronizedMap(new HashMap<Class,Map<String,Method>>());
	
	public static final Method getMethod(Class claz, String methodname, boolean isStatic){
		if( methodname == null) return null;
		if( cachedM.containsKey(claz) && cachedM.get(claz).containsKey(methodname.toLowerCase())){
			return cachedM.get(claz).get(methodname.toLowerCase());
		}
		
		Map<String,Method> methods = cachedM.get(claz);
		if( methods == null){
			methods = new HashMap<String,Method>();
			cachedM.put( claz, methods );
		}
		
		for( Method m : claz.getMethods() ){
			if( !Modifier.isPublic(m.getModifiers()) || isStatic && !Modifier.isStatic(m.getModifiers())  || !isStatic && Modifier.isStatic(m.getModifiers())){
				continue;
			}
			
			if( m.getName().equalsIgnoreCase(methodname)){
				methods.put(m.getName().toLowerCase(), m);
				return m;
			}
			
		}
		
		return null;
	}
	
	public static final Object staticCall(Class claz, String method, Class[] ptypes, Object[] params){
		
		try {
			Method m = claz.getMethod(method, ptypes);
			if( Modifier.isPublic(m.getModifiers()) && Modifier.isStatic(m.getModifiers()) ){
				return m.invoke(null, params);
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			logger.warn("反射方法值失败!", e);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			logger.warn("反射方法值失败!", e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			logger.warn("反射方法值失败!", e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.warn("反射方法值失败!", e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			logger.warn("反射方法值失败!", e);
		}
		return null;
	}
	
	//(newExample, "createCriteria", new Class[]{}, new Object[]{});
	public static final Object publicCall(Object This, String method, Class[] ptypes, Object[] params){
		
		try {
			Method m = This.getClass().getMethod(method, ptypes);
			if( Modifier.isPublic(m.getModifiers()) && !Modifier.isStatic(m.getModifiers()) ){
				return m.invoke(This, params);
			}
		} catch (SecurityException e) {
			logger.warn("反射方法值失败!", e);
		} catch (NoSuchMethodException e) {
			logger.warn("反射方法值失败!", e);
		} catch (IllegalArgumentException e) {
			logger.warn("反射方法值失败!", e);
		} catch (IllegalAccessException e) {
			logger.warn("反射方法值失败!", e);
		} catch (InvocationTargetException e) {
			logger.warn("反射方法值失败!", e);
		}
		return null;
	}
	
	
}
