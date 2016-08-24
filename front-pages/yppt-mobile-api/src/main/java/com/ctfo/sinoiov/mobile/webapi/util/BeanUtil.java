package com.ctfo.sinoiov.mobile.webapi.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.ctfo.csm.intf.support.CSMException;

/**
 * webapi内部用Bean工具
 * @author 徐宝
 *
 */
public class BeanUtil {
	//弱引用缓存,当垃圾回收时,不影响无用的数据回收
    private static Map<String, Map<String,Method>> setMethodCatchMap = new WeakHashMap<String, Map<String,Method>>();
    //保存原始类属性要拷贝至目的对象的属性集合.key:origClassName-destClassName; value : origfieldName-destField Map集合
    private static Map<String, Map<String,Field>> origCopyToDestFieldCatchMap = new WeakHashMap<String, Map<String,Field>>();
    
    private static Logger logger = Logger.getLogger(BeanUtil.class);
    
    private static SimpleDateFormat DEAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static Object copyPropertiesWithTypeCastNoException( Object orig, Object dest){
        try {
            return copyPropertiesWithTypeCast(orig, dest);
        } catch (Exception e) {//忽略所有异常
          logger.warn( "拷贝属性出现异常", e);
        }
        return dest;
    }
    
    /**
     * 拷贝属性,带有类型转换的属性拷贝
     * @param dest
     * @param orig
     */
    public static Object copyPropertiesWithTypeCast(Object orig , Object dest){
        if( dest == null || orig == null){
            return dest;
        }
        //copy通用属性
        Class<?> destClass = dest.getClass();
        Class<?> origClass = orig.getClass();
        //如果是原始类型，则直接进行复制
        if(destClass.isPrimitive() || origClass.isPrimitive()){
        	try {
        		Method method = ReflectUtil.findMethod(destClass, "valueOf", new Class[]{origClass});
        		if(method == null)
        			method = ReflectUtil.findMethod(destClass, "valueOf", new Class[]{Object.class});
        		
				dest = method.invoke(dest, new Object[]{dest});
			} catch (Exception e) {
				logger.warn("基本类型数据拷贝异常! destClass :" + destClass + "\t destObject: " +  dest
						+ "origClass :" + origClass + "\t origObject: " +  orig, e);
 			}
        	return dest;
        }
        Map<String, Method> destSetMethodMap = getClassSetMethodMap(destClass);
        if(destSetMethodMap.isEmpty()){
        	return dest;
        }

    	
        Map<String,Field>  origToDestFieldMap = getOrigFiledToDestFieldMap(origClass, destClass); 
        
        for(Method m : origClass.getMethods()){
            try {
            	//非get方法不拷贝
                if(!isPublicGetMethod(m)) continue;
                Object origValue =null;
                origValue = m.invoke(orig, new Object[]{});
                if(origValue == null)  continue;
                //获取需要拷贝的属性
                Field origField = findFiledByGetOrSetMethod(m);
            	if(origField ==null) continue;
            	//获取需要目标拷贝属性
            	Field destField = origToDestFieldMap.get(origField.getName());
            	if(destField == null) continue;
                //转换目标属性值
            	Object destValue = convertValueByAnnocation(origField, origValue, destField);
                //空值不进行拷贝
            	if((destValue ==  null) ||
                		(destValue instanceof String && StringUtils.isBlank((String) destValue))){
//                	return dest;
            		continue;
                }
                
                //设置目标属性值
                Method destMethod = destSetMethodMap.get(destField.getName());
                if(destMethod != null){
//                	if(logger.isDebugEnabled()){
//                		StringBuffer sb = new StringBuffer();
//                		sb.append("原始属性:").append(origField.getName()).append("\t value:").append(origValue).append("\t原目标类型：").append(origField.getClass());
//                		sb.append("方法名").append(m.getName()).append("\t paramType：").append(destMethod.getParameterTypes()[0]);
//                		sb.append("目标属性:").append(destField.getName()).append("目标属性值类型：").append(destValue.getClass()).append("\t value：").append(destValue.toString());
//                		logger.info(sb.toString());
//                	}
                	if(!destMethod.getParameterTypes()[0].equals(destValue.getClass())){
//                		StringBuffer sb = new StringBuffer();
//                		sb.append("原始属性:").append(origField.getName()).append("\t value:").append(origValue).append("\t原目标类型：").append(origField.getClass());
//                		sb.append("方法名").append(m.getName()).append("\t paramType：").append(destMethod.getParameterTypes()[0]);
//                		sb.append("目标属性:").append(destField.getName()).append("目标属性值类型：").append(destValue.getClass()).append("\t value：").append(destValue.toString());
//                		logger.warn("拷贝目标属性方法与类型不一致!" + sb.toString());
                		continue;
                	}
                	try {
                		destMethod.invoke(dest, destValue);
					} catch (Exception e) {
						logger.warn("拷贝目标属性发生异常!");
					}
                }
            }catch (Exception e) {
                logger.warn("拷贝属性出现异常 原始类:" + origClass + " \t 方法:" + m.getName() + " \t 目标类:" + destClass ,e);
            }
        }
        return dest;
    }
    
    /**
	 * 判断参数是否为空
	 */
	public static void notNull(Object ...model){
		
		if(model == null) {
			throw new CSMException("非法输入空bean");
		}		
		for(Object obj : model){
			if(obj==null)
				throw new CSMException("非法输入空bean");
			if(obj instanceof String && !StringUtils.isNotBlank((String)obj))
				throw new CSMException("参数["+obj.getClass().getName()+"]为 空");
			if(obj instanceof Collection && ((Collection<?>)obj).size()>1000)
				throw new CSMException("参数["+obj.getClass().getName()+"]集合数量["+1000+"]超过系统规定的上限!");
		}
	}
	

	private static Map<String, Field> getOrigFiledToDestFieldMap(
			Class<?> origClass, Class<?> destClass) {
        String origToDestFieldCatchMap_Key = origClass.getName() + "-"+ destClass.getName();
        Map<String, Field> origToDestFieldMap = origCopyToDestFieldCatchMap.get(origToDestFieldCatchMap_Key);
		if(origToDestFieldMap == null || origToDestFieldMap.isEmpty()){
			loadOrigClassFiledAndDestClassFieldRelationIntoMap(origClass, destClass);
			origToDestFieldMap = origCopyToDestFieldCatchMap.get(origToDestFieldCatchMap_Key);
		}
		return origToDestFieldMap;
	}

	private static void loadOrigClassFiledAndDestClassFieldRelationIntoMap(
			Class<?> origClass, Class<?> destClass) {
		Map<String, Field> origToDestFieldMap = new HashMap<String, Field>();
		Map<String, String> destFiledOrigMap = new HashMap<String, String>();
		  for(Method m : origClass.getMethods()){
	            try {
	            	//非get方法不拷贝
	                if(!isPublicGetMethod(m)) continue;
	                //获取需要拷贝的属性
	                Field origField = findFiledByGetOrSetMethod(m);
	            	if(origField ==null) continue;
	                //转换目标属性值
	                Field destFiled = loadDestField(origToDestFieldMap,destFiledOrigMap,origField, destClass);
	                
	            } catch (Exception e) {
	                logger.warn("拷贝属性出现异常 原始类:" + origClass + " \t 方法:" + m.getName() + " \t 目标类:" + destClass ,e);
	            }
	       }
	      String origToDestFieldCatchMap_Key = origClass.getName() + "-"+ destClass.getName();
	      origCopyToDestFieldCatchMap.put(origToDestFieldCatchMap_Key,origToDestFieldMap);
	}

	private static Field loadDestField(Map<String, Field> origToDestFieldMap, Map<String, String> destFiledOrigMap, Field origField, Class<?> destClass) throws Exception {
		boolean origAnnotation = false;
		boolean destAnnotation = false;
		//目标属性名称
        String destFieldName = origField.getName();
        PropertityCopyAnnotation propertityAnno = origField.getAnnotation(PropertityCopyAnnotation.class);
        //copy特殊属性
        if(propertityAnno != null && !PropertityCopyAnnotation.STR_DEFAULT.equals(propertityAnno.destFieldName())){
        	destFieldName = propertityAnno.destFieldName();
        	origAnnotation = true;
        }
        Field field = null;
    	for(Method m : destClass.getMethods()){
    		if(!isPublicSetMethod(m)) continue;
    		Field f = findFiledByGetOrSetMethod(m);
    		if(f == null) continue;
    		if(origAnnotation){//原始属性上有注解的
    			if(destFieldName.equals(f.getName())){
    				field = f;break;
    			}
    		}else{//原始属性没有注解，需在目标属性上找到注解，若没有，则属性名相同的属性返回
    			if(destFieldName.equals(f.getName())){
    				field = f;break;
    			}
    			propertityAnno = f.getAnnotation(PropertityCopyAnnotation.class);
    			if(propertityAnno != null && propertityAnno.needCopy() 
    					&& ( destFieldName.equals(propertityAnno.destFieldName())
    							|| destFieldName.equals(f.getName())) ){
    				destAnnotation = true;
    				field = f;break;
    			}
    		}
    	}
    	if(field != null){
    		destFiledOrigMap.put(field.getName()+origAnnotation+destAnnotation, origField.getName());
    		if(origAnnotation || destAnnotation){
    			origToDestFieldMap.remove(destFiledOrigMap.get(field.getName()+"false"+"false"));
    		}
        	origToDestFieldMap.put(origField.getName(), field);
        }
		return field;
	}

	/**
     * 获取class 的 set Method Map集合
     * @param destClass
     * @return
     */
    private static Map<String, Method> getClassSetMethodMap(Class<?> destClass) {
    	 Map<String, Method> destSetMethodMap = setMethodCatchMap.get(destClass.getName());
         if(destSetMethodMap == null || destSetMethodMap.isEmpty()){
        	 loadClassSetMethod(destClass);
        	 destSetMethodMap = setMethodCatchMap.get(destClass.getName());
         }
		return destSetMethodMap;
	}
    
    /**
     * load class set method in catchMap
     * @param destClass
     * @return
     */
    private synchronized static void loadClassSetMethod(Class<?> cl){
    	Map<String, Method> destSetMethodMap = setMethodCatchMap.get(cl.getName());
        if(destSetMethodMap == null){
        	destSetMethodMap = new HashMap<String, Method>();
	        for(Method m : cl.getMethods()){
	        	//set方法
	            if(isPublicSetMethod(m)){
	                destSetMethodMap.put(m.getName().substring(3,4).toLowerCase() + m.getName().substring(4), m);
	            }
	        }
	        setMethodCatchMap.put(cl.getName(), destSetMethodMap);
        }
    }
    
    private static boolean isPublicSetMethod(Method m){
    	if( m != null && m.getName().startsWith("set") 
    			&& m.getName().length()>3 && m.getParameterTypes().length == 1) 
    		return true;
    	return false;
    }
    
    private static boolean isPublicGetMethod(Method m){
    	if( m != null && m.getName().startsWith("get") 
    			&& m.getName().length() > 3 && m.getParameterTypes().length == 0) 
    		return true;
    	return false;
    }
    
    private static Field findFiledByGetOrSetMethod(Method m){
    	Field origField = null;
    	if(isPublicGetMethod(m) || isPublicSetMethod(m)){
    		String filedName = m.getName().substring(3,4).toLowerCase() + m.getName().substring(4);
    		try {
    			//set or get method declare into field class on same level
    			origField = m.getDeclaringClass().getDeclaredField(filedName);
    		} catch (NoSuchFieldException e) {
    			//ignore exception
    		}
    	}
        return origField;
    }

    private static Object convertValueByAnnocation(Field origField,
            Object origValue, Field destField) {
		//目标值
		Object destValue =origValue;
    	if(origField == null || origValue == null){
    		return origValue;
    	}
    	
    	try {
    		PropertityCopyAnnotation propertityAnno = origField.getAnnotation(PropertityCopyAnnotation.class);
    		if(propertityAnno == null){
    			propertityAnno = destField.getAnnotation(PropertityCopyAnnotation.class);
    			if(propertityAnno == null)
    			//设置目标属性
    			return origValue;
    		}
    		//目标类型
    		Class<?> destType = null;
    		//原始属性类型
    		Class<?> origType = null;
    		//原始属性类型描述
    		String origTypeDesc = null;
    		//原始属性类型格式
    		String format = null;
    		if(!PropertityCopyAnnotation.DEFAULT.class.equals(propertityAnno.destFieldType())){
    			destType = propertityAnno.destFieldType();
    		}
    		if(!PropertityCopyAnnotation.DEFAULT.class.equals(propertityAnno.destFieldType())){
    			origType = propertityAnno.origFieldType();
    		}
    		if(!PropertityCopyAnnotation.STR_DEFAULT.equals(propertityAnno.origFieldTypeDesc())){
    			origTypeDesc = propertityAnno.origFieldTypeDesc();
    		}
    		if(!PropertityCopyAnnotation.STR_DEFAULT.equals(propertityAnno.origFieldFormat())){
    			format = propertityAnno.origFieldFormat();
    		}
    		if(origType != null || origTypeDesc != null){//原始属性转换
    			if(java.util.Date.class.equals(origType)){//时间格式
    				if(java.lang.Long.class.equals(destType)){//转换成时间毫秒数
    					destValue = transferStringToUTCDateNoException((String)origValue, format);
    				}
    			}else if("Time".equals(origTypeDesc)){
    				if(java.lang.Long.class.equals(destType) && ( format == null || "hh:mm:ss".equals(format))){//转换成时间毫秒数
    					destValue = parseTimeIntervalNoException((String)origValue);
    				}
    			}else if("Money".equals(origTypeDesc)){//金额格式
    				if(format==null){
    					format= "1";
    				}
    				if(Number.class.isAssignableFrom(destType)){//转换成数据格式,金额以整数转换,单位进行换算
						BigDecimal value = new BigDecimal(origValue.toString())
								.multiply(new BigDecimal(format));
						destValue = converterNumberValue(value, destType);
					}
				} else if ("AotuCost".equals(origTypeDesc)) {// 类型自动转换
					if(Number.class.isAssignableFrom(destType)){//转换成数据格式,金额以整数转换,单位进行换算
						if (StringUtils.isBlank(format)) {
							format = "1";
						}
						BigDecimal value = new BigDecimal(origValue.toString())
						.multiply(new BigDecimal(format));
						destValue = converterNumberValue(value, destType);
					}
				}
			}
		} catch (Exception e) {
			//ignore exception
			logger.warn("转换属性"+ origField + "属性值：" 
					+ origValue + " 至目标属性" + destField + "发生异常！", e);
		}
        return destValue;
    }
	
	private static Object converterNumberValue(Number value,
			Class<?> destType) {
		if(value ==null) value = BigDecimal.ZERO;
		if(Short.class.equals(destType)){
			return value.intValue();
		} else if(Integer.class.equals(destType)){
			return value.intValue();
		} else if(Long.class.equals(destType)){
			return value.longValue();
		} else if(Float.class.equals(destType)){
			return value.floatValue();
		} else if(Double.class.equals(destType)){
			return value.doubleValue();
		} else if(BigDecimal.class.equals(destType)){
			return BigDecimal.valueOf(value.doubleValue());
		} else if(BigInteger.class.equals(destType)){
			return BigInteger.valueOf(value.longValue());
		}
		return value;
	}
	
    private static Long transferStringToUTCDateNoException(String timeStr,
            String formatStr) {
        try {
        	SimpleDateFormat format  = DEAULT_DATE_FORMAT;
            if(formatStr != null) {
            	format  = new SimpleDateFormat(formatStr);
            }
            //处理无法类型转换错误的属性
            if(StringUtils.isNotBlank(timeStr)){
                return format.parse(timeStr).getTime();
            }
        } catch (Exception e) {
        }
        return null;
    }

	public static Long transferStringToUTCDateNoException(String timeStr) {
        return transferStringToUTCDateNoException(timeStr,null);
    }
    
    /**
     * 解析时间间隔
     * @param timeInterval 时间间隔字符串,格式如: HH:mm:ss
     * @return 返回毫秒数
     */
    public static Long parseTimeIntervalNoException(String timeInterval){
        try {
            if(StringUtils.isNotBlank(timeInterval) && timeInterval.contains(":")){//过滤无效数据
                int secondIndex = timeInterval.lastIndexOf(":");
                int minuteIndex = timeInterval.lastIndexOf(":", secondIndex-1);
                long hour = 0;
                long minute = 0;
                long second = 0;
                if(minuteIndex > 0){
                    hour = Long.parseLong(timeInterval.substring(0,minuteIndex)) * 60 * 60 * 1000;
                }
                if (secondIndex > 0) {
                    minute = Long.parseLong(timeInterval.substring(minuteIndex + 1, secondIndex)) * 60 * 1000;
                    second = Long.parseLong(timeInterval.substring(secondIndex + 1)) * 1000;
                }else {
                    second = Long.parseLong(timeInterval) * 1000;
                }
                Long result = hour + minute + second;
                if(result <=0) result = null;//置为null,避免更新数据库该字段
                return result;
            }else{
            	return 0L;
            }
        } catch (Exception e) {
            logger.warn( "SYS_ERE", e);
        }
        return null;
    }
}
