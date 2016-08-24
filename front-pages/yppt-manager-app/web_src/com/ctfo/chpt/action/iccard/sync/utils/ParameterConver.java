package com.ctfo.chpt.action.iccard.sync.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ctfo.common.models.DynamicSqlParameter;


public class ParameterConver {
  
  private static final Log logger = LogFactory.getLog(ParameterConver.class);
  
  private static Map<Class, Map<String, Method>> cachedM = Collections
      .synchronizedMap(new HashMap<Class, Map<String, Method>>());
  
  /**
   * 将对象转化成为字符串：key=value&key=value......
   * 
   * @param object
   * @return
   * @throws Exception
   */
  public static String getParameterString(Object object) throws Exception {
    if (object == null) {
      return "";
    }
    String contents = "";
    List<Field> fields = new ArrayList<Field>();
    fields.addAll(Arrays.asList(object.getClass().getDeclaredFields()));
    for (Field f : fields) {
      if (!f.getName().equals("serialVersionUID")) {
        PropertyDescriptor pd = new PropertyDescriptor(f.getName(), object.getClass());
        Method getMethod = pd.getReadMethod();
        Object o = getMethod.invoke(object);
        if (o == null || (String.valueOf(o)).equals("")) {
          contents += f.getName() + "=&";
        }
        else {
          contents += f.getName() + "=" + (URLEncoder.encode(String.valueOf(o), "GBK")) + "&";
        }
      }
    }
    contents = contents.substring(0, contents.length() - 1);
    return contents;
  }
  
  /**
   * 根据声明的配置，将一个对象的属性配置给另外一个对象
   * 
   * @param source
   *        取值对象
   * @param dest
   *        获取值对象
   * @throws Exception
   */
  public static void copyObjectToObject(Object source, Object dest, Class claz) throws Exception {
    List<Field> fields = new ArrayList<Field>();
    fields.addAll(Arrays.asList(source.getClass().getDeclaredFields()));
    for (Field f : fields) {
      try {
        if (f.getAnnotation(claz) != null) {
          Field destField = dest.getClass().getDeclaredField(
              String.valueOf(f.getAnnotation(claz).getClass().getMethod("value", null)
                  .invoke(f.getAnnotation(claz), null)));
          if (destField != null) {
            PropertyDescriptor sourcePd = new PropertyDescriptor(f.getName(), source.getClass());
            Method sourceMethod = sourcePd.getReadMethod();// 获得get方法
            
            PropertyDescriptor destPd = new PropertyDescriptor(destField.getName(), dest.getClass());
            Method destMethod = destPd.getWriteMethod();// 获得set方法
            Object sourceValue = getValByAttr(f, destField, source, sourceMethod);
            if (sourceValue != null) {
              destMethod.invoke(dest, sourceValue);
            }
          }
        }
      }
      catch (Exception e) {
        System.err.println("****************源对象的属性和目标对象不匹配：" + f + "");
        throw e;
      }
    }
  }
  
  /**
   * 复制对象时，如果源属性和目标属性类型不同，强制转换源属性
   * 
   * @param sourceField
   *        源字段
   * @param targetField
   *        目标字段
   * @param sourceObj
   *        源对象
   * @param sourceMethod
   *        源方法
   * @return
   * @throws Exception
   */
  private static Object getValByAttr(Field sourceField, Field targetField, Object sourceObj,
                                     Method sourceMethod) throws Exception {
    Object result = sourceMethod.invoke(sourceObj);
    if (result == null || StringUtils.isEmpty(result.toString())) {
      return null;
    }
    
    if (targetField.getType() == BigDecimal.class) {
      return new BigDecimal(result.toString());
    }
    else if (targetField.getType() == Long.class) {
      return new Long(result.toString());
    }
    else if (targetField.getType() == Short.class) {
      return new Short(result.toString());
    }
    return result;
  }
  
  /**
   * 根据声明的配置，从另外一个对象获取值赋予自身
   * 
   * @param source
   *        自身对象
   * @param dest
   *        取值对象
   * @throws Exception
   */
  public static void getValueFromObjectToSelf(Object source, Object dest, Class claz)
      throws Exception {
    List<Field> fields = new ArrayList<Field>();
    fields.addAll(Arrays.asList(source.getClass().getDeclaredFields()));
    for (Field f : fields) {
      try {
        if (f.getAnnotation(claz) != null) {
          Field destField = dest.getClass().getDeclaredField(
              String.valueOf(f.getAnnotation(claz).getClass().getMethod("value", null)
                  .invoke(f.getAnnotation(claz), null)));
          if (destField != null) {
            PropertyDescriptor destPd = new PropertyDescriptor(destField.getName(), dest.getClass());
            Method destMethod = destPd.getReadMethod();// 获得get方法
            Object r = destMethod.invoke(dest);
            
            if (r != null) {
              PropertyDescriptor sourcePd = new PropertyDescriptor(f.getName(), source.getClass());
              Method sourceMethod = sourcePd.getWriteMethod();// 获得set方法
              
              sourceMethod.invoke(source, String.valueOf(destMethod.invoke(dest)));
            }
          }
        }
      }
      catch (Exception e) {
        System.err.println("****************源对象的属性和目标对象不匹配：" + f + "");
        throw e;
      }
    }
  }
  
  /**
   * 获取对象某个字段的值
   * 
   * @param object
   * @param fieldName
   * @return
   * @throws Exception
   */
  public static Object getValueOfField(Object object, String fieldName) throws Exception {
    PropertyDescriptor pd = new PropertyDescriptor(fieldName, object.getClass());
    Method method = pd.getReadMethod();// 获得get方法
    return method.invoke(object);
  }
  
  /**
   * 设置对象某个字段的值
   * 
   * @param object
   * @param fieldName
   * @param value
   * @throws Exception
   */
  public static void setValueOfField(Object object, String fieldName, Object value)
      throws Exception {
    PropertyDescriptor pd = new PropertyDescriptor(fieldName, object.getClass());
    Method method = pd.getWriteMethod();// 获得set方法
    if (value != null) {
      method.invoke(object, value);
    }
  }
  
  /**
   * 将Map转化成为String
   * 
   * @param map
   * @return
   */
  public static String mapConvertString(Map<String, String> map) {
    String result = "";
    try {
      Set<String> set = map.keySet();
      Iterator<String> it = set.iterator();
      while (it.hasNext()) {
        String key = it.next();
        String value = map.get(key);
        if (it.hasNext()) {
          result += key + "=" + value + "&";
        }
        else {
          result += key + "=" + value;
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      result = "";
    }
    return result;
  }
  
  public static Map<String, String> convertStringToMap(String content) throws Exception {
    String[] contents = content.split("&");
    Map<String, String> map = new HashMap<String, String>();
    for (int i = 0; i < contents.length; i++) {
      map.put(contents[i].split("=")[0], contents[i].split("=")[1]);
    }
    return map;
  }
  
  public static final Object paramToExampleExtended(DynamicSqlParameter dsp, Object newExample)
      throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException,
      InvocationTargetException {
    paramToExampleExtendedCriteria(dsp, newExample);
    return newExample;
  }
  
  public static final Object paramToExampleExtendedCriteria(DynamicSqlParameter dsp,
                                                            Object newExample)
      throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException,
      InvocationTargetException {
    if (dsp == null && newExample == null)
      return null;
    
    Object criteria = ParameterConver.publicCall(newExample, "createCriteria", new Class[] {},
        new Object[] {});
    if (criteria == null)
      throw new IllegalArgumentException("newExample is not Ibatis Example parameter.");
    if (dsp == null)
      return criteria;
    
    String exaName = newExample.getClass().getName();
    String beanName = (exaName.endsWith("Example")) ? exaName.substring(0, exaName.length() - 7)
        : ((exaName.endsWith("ExampleExtended")) ? exaName.substring(0, exaName.length() - 15)
            : null);
    if (beanName == null)
      throw new IllegalArgumentException("newExample :" + exaName
          + " , cann't found bean  XXXExampleExtended.");
    
    Class beanClass = Class.forName(beanName);
    
    if (beanClass == null)
      throw new IllegalArgumentException("newExample :" + exaName + " , cann't found bean");
    
    /**
     * detail query by malq 2012-12-18
     */
    Map<String, String> params = null;
    // // handle EqualTo
    params = dsp.getEqual();
    if (params != null)
      andCriteria(criteria, beanClass, params, "EqualTo");
    
    params = dsp.getNotequal();
    if (params != null)
      andCriteria(criteria, beanClass, params, "NotEqualTo");
    
    params = dsp.getLike();
    if (params != null)
      andCriteria(criteria, beanClass, params, "Like");
    
    Map<String, Object> temp = dsp.getInMap();
    if (temp != null) {
      params = new HashMap<String, String>();
      for (String key : temp.keySet()) {
        Object tempv = temp.get(key);
        if (tempv instanceof Object[]) {
          tempv = Arrays.asList((Object[]) tempv);
        }
        if (tempv instanceof List) {
          List invals = (List) tempv;
          for (int i = 0; i < invals.size(); i++) {
            if (i == 0) {
              tempv = "" + invals.get(i);
            }
            else {
              tempv = tempv + "," + invals.get(i);
            }
          }
        }
        
        params.put(key, tempv.toString());
      }
      andCriteria(criteria, beanClass, params, "In");
    }
    
    Map<String, Object> nottemp = dsp.getNotInMap();
    if (nottemp != null) {
      params = new HashMap<String, String>();
      for (String key : nottemp.keySet()) {
        Object tempv = nottemp.get(key);
        if (tempv instanceof Object[]) {
          tempv = Arrays.asList((Object[]) tempv);
        }
        if (tempv instanceof List) {
          List invals = (List) tempv;
          for (int i = 0; i < invals.size(); i++) {
            if (i == 0) {
              tempv = "" + invals.get(i);
            }
            else {
              tempv = tempv + "," + invals.get(i);
            }
          }
        }
        
        params.put(key, tempv.toString());
      }
      andCriteria(criteria, beanClass, params, "NotIn");
    }
    
    params = dsp.getStartwith();
    if (params != null)
      andCriteria(criteria, beanClass, params, "GreaterThanOrEqualTo");
    
    params = dsp.getEndwith();
    if (params != null) {
      andCriteria(criteria, beanClass, params, "LessThanOrEqualTo");
      // andCriteria(criteria, beanClass, params, "LessThan");
    }
    
    // //handle orderBy
    String orderField = dsp.getOrder();
    String orderSort = dsp.getSort();
    if (orderField != null && !"".equals(orderField)) {
      Method m = getMethod(beanClass, "field" + orderField, true);
      
      String field = orderField;
      if (m != null) {
        Object ofield = m.invoke(null, new Object[] {});
        field = ofield.toString();
        ParameterConver.publicCall(newExample, "setOrderByClause", new Class[] {String.class},
            new Object[] {field + " " + ((orderSort != null) ? orderSort : " ")});
      }
      
    }
    
    // handle pagination
    int start = dsp.getStartNum();
    int end = dsp.getEndNum();
    if (end != 0) {
      ParameterConver.publicCall(newExample, "setSkipNum", new Class[] {int.class},
          new Object[] {Integer.valueOf(start)});
      ParameterConver.publicCall(newExample, "setEndNum", new Class[] {int.class},
          new Object[] {Integer.valueOf(end + 1)});
    }
    
    return criteria;
  }
  
  public static final Object publicCall(Object This, String method, Class[] ptypes, Object[] params) {
    try {
      Method m = This.getClass().getMethod(method, ptypes);
      if (Modifier.isPublic(m.getModifiers()) && !Modifier.isStatic(m.getModifiers())) {
        return m.invoke(This, params);
      }
    }
    catch (SecurityException e) {
      logger.warn("SYS_ERROR!", e);
    }
    catch (NoSuchMethodException e) {
      logger.warn("SYS_ERROR!", e);
    }
    catch (IllegalArgumentException e) {
      logger.warn("SYS_ERROR!", e);
    }
    catch (IllegalAccessException e) {
      logger.warn("SYS_ERROR!", e);
    }
    catch (InvocationTargetException e) {
      logger.warn("SYS_ERROR!", e);
    }
    return null;
  }
  
  public static final Method getMethod(Class claz, String methodname, boolean isStatic) {
    if (methodname == null)
      return null;
    if (cachedM.containsKey(claz) && cachedM.get(claz).containsKey(methodname.toLowerCase())) {
      return cachedM.get(claz).get(methodname.toLowerCase());
    }
    
    Map<String, Method> methods = cachedM.get(claz);
    if (methods == null) {
      methods = new HashMap<String, Method>();
      cachedM.put(claz, methods);
    }
    
    for (Method m : claz.getMethods()) {
      if (!Modifier.isPublic(m.getModifiers()) || isStatic && !Modifier.isStatic(m.getModifiers())
          || !isStatic && Modifier.isStatic(m.getModifiers())) {
        continue;
      }
      if (m.getName().equalsIgnoreCase(methodname)) {
        methods.put(m.getName().toLowerCase(), m);
        return m;
      }
    }
    
    return null;
  }
  
  private static void andCriteria(Object criteria, Class beanClass, Map<String, String> params,
                                  String operator) throws IllegalArgumentException,
      IllegalAccessException, InvocationTargetException {
    if (params != null) {
      for (String key : params.keySet()) {
        // ignore the "" string
        String v = params.get(key);
        if (v == null || "".equals(v.trim()))
          continue;
        
        String mkey = (key.charAt(0) + "").toUpperCase() + key.substring(1);
        Method getM = ParameterConver.getMethod(beanClass, "get" + mkey, false);
        if (getM == null) {
          LogFactory.getLog(ParameterConver.class).warn("当拼写查询条件时有无效属性",
              new IllegalArgumentException("没有找到对应的GET方法：" + key));
          continue;
        }
        Class fieldClaz = getM.getReturnType();
        
        Method m = ParameterConver.getMethod(criteria.getClass(), "and" + mkey + operator, false);
        if (m == null) {
          continue;
        }
        
        Class[] pts = m.getParameterTypes();
        Object[] vals = new Object[pts.length];
        
        int i = 0;
        for (Class pt : pts) {
          
          vals[i] = convertType(pt, fieldClaz, params.get(key));
          i++;
        }
        
        m.invoke(criteria, vals);
      }
    }
  }
  
  private static Object convertType(Class claz, Class fieldclaz, String v) {
    if (Long.class.equals(claz)) {
      return Long.valueOf(v);
    }
    else if (Integer.class.equals(claz)) {
      return Integer.valueOf(v);
    }
    else if (Short.class.equals(claz)) {
      return Short.valueOf(v);
      
    }
    else if (BigDecimal.class.equals(claz)) {
      return new BigDecimal(v);
      
    }
    else if (List.class.equals(claz)) {
      StringTokenizer stoken = new StringTokenizer(v, ",");
      List ret = new ArrayList();
      while (stoken.hasMoreTokens()) {
        ret.add(convertType(fieldclaz, fieldclaz, stoken.nextToken()));
      }
      return ret;
    }
    else {
      return v;
    }
  }
  
}
