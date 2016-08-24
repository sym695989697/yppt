package spring.mvc.extended.handlerMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.condition.ConsumesRequestCondition;
import org.springframework.web.servlet.mvc.condition.HeadersRequestCondition;
import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * spring扩展类,继承{@link RequestMappingHandlerMapping @RequestMappingHandlerMapping } classes.
 * 采用约定大于配置的习惯,对spring进行扩展.
 * 首先使用Spring默认规则.如果默认规则为空,采用约定方式生成默认的请求访问路径
 * 扩展后默认访问路径为:类名.toLowerCase/方法名.toLowerCase
 * 该方法默认为
 * @author xubao
 *
 */
public class RequestMappingHandlerMappingExtended extends
		RequestMappingHandlerMapping {
	private static Log logger = LogFactory.getLog(RequestMappingHandlerMappingExtended.class);
	
	private static String DEFULT_CONTROLLER_CLASS_END_NAME= "Controller";
	
	@Override
	protected RequestMappingInfo getMappingForMethod(final Method method,
			Class<?> handlerType) {
		//首先使用Spring默认规则.
		RequestMappingInfo info = super.getMappingForMethod(method, handlerType);
		if(info != null){
			return info;
		}
		//如果默认规则为空,采用约定方式生成默认的RequestMappingInfo;
		//默认规则为 requestMapping value =  类名 /方法名; 不对参数受限
		//not support set/get method
		logger.info("装载类:" + handlerType.getName() + "\t方法:" + method.getName() + "\t 启用约定的spring配置");
		if(!Modifier.isPublic(method.getModifiers())){
			return info;
		}
		
		String pattern =  "/" + method.getName();
		String[] patterns = this.resolveEmbeddedValuesInPatterns(new String[]{ pattern });
		
		info = new RequestMappingInfo(
				new PatternsRequestCondition(patterns , getUrlPathHelper(), getPathMatcher(), true, true , null),
				new RequestMethodsRequestCondition(new RequestMethod[]{}),
				new ParamsRequestCondition(new String[]{}),
				new HeadersRequestCondition(new String[]{}),
				new ConsumesRequestCondition(new String[]{}, new String[]{}),
				new ProducesRequestCondition(new String[]{}, new String[]{}, getContentNegotiationManager()),
				null);
		//默认没有以下规则
		RequestCondition<?> typeCondition = getCustomTypeCondition(handlerType);
		RequestMapping typeAnnotation = findResquestAnnotationForHandlerType(handlerType);
		info = createRequestMappingInfo(typeAnnotation, typeCondition).combine(info);
		return info;
	}

	/**
	 * 先获取类的注释,如果类没有注释,则启用默认注释
	 * @param method
	 * @param handlerType 
	 * @return RequestMapping
	 */
	private RequestMapping findResquestAnnotationForHandlerType(final Class<?> handlerType) {
		RequestMapping typeAnnotation = AnnotationUtils.findAnnotation(handlerType, RequestMapping.class);
		//
		if (typeAnnotation == null) {
			//使用默认注释
			typeAnnotation = new RequestMapping() {
				public Class<? extends Annotation> annotationType() {
					return RequestMapping.class;
				}
				
				public String[] value() {
					// 默认去掉类名结尾的"Controller"单词, 访问路径为类名的小写单词
					//访问路径为类名的小写单词
					return  new String[]{"/" + bulidDefultClassRequestPath(handlerType)};
				}
				
				public String[] produces() {
					return new String[]{};
				}
				
				public String[] params() {
					return new String[]{};
				}
				
				public RequestMethod[] method() {
					return new RequestMethod[]{};
				}
				
				public String[] headers() {
					return new String[]{};
				}
				
				public String[] consumes() {
					return new String[]{};
				}
			};
		}
		return typeAnnotation;
	}
	
	/**
	 * 构建默认类的请求访问路径; 默认访问路径为类的SimpleName的小写单词;当类以"Controller"单词结尾时,则访问路径去掉"Controller"
	 * @param contrallerClass
	 * @return
	 */
	protected String bulidDefultClassRequestPath(Class<?> contrallerClass){
		// 默认去掉类名结尾的"Controller"单词, 访问路径为类名的小写单词
		String className = contrallerClass.getSimpleName();
		if(className.endsWith(DEFULT_CONTROLLER_CLASS_END_NAME)){
			int index = className.lastIndexOf(DEFULT_CONTROLLER_CLASS_END_NAME);
			if(index != 0){//当类名为"Controller"时,则返回
				className = className.substring(0, index);
			}else{
				return contrallerClass.getSimpleName();
			}
		}
		return className.toLowerCase();
	}
}
