package com.ctfo.chpt.action.demo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.base.service.IService;
import com.fasterxml.jackson.annotation.JsonView;

@Controller
@Scope("prototype")
//@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//@RequestMapping("/helloWorld")
public class HelloWorldController extends BaseControler{
//	@RequestMapping("/helloWorld")
	@RequestMapping("/helloWorld")
	@ResponseBody
	public String helloWorld(Model model) {
		model.addAttribute("message", "Hello World!");
		return "helloWorld";
	}
	
	public String helloSpringMvc(Model model) {
		model.addAttribute("message", "Hello SpringMvc!");
		return "helloSpringMvc";
	}
	/**
	 * spring 自动绑定 请求及响应参数到方法中
	 * @param request
	 * @param response
	 * @return
	 */
	public String bindParmaForServletType(HttpServletRequest request, HttpServletResponse response) {
		//不同的线程调用同一个的类(类的作用域由spring bean 的生命周期管理;可配置不同的生命周期;默认的为singleton)
		System.out.println( "thread id : " + Thread.currentThread() +  "\t class object :" + this);
		return "bindParmaSpringMvc";
	}
	
	/**
	 * spring 自动绑定Pojo类型参数;
	 * 传参方式如下:
	 * 私有属性
	 * name=1
	 * 私有属性的属性
	 * innerVo.innerName=innerName
	 * @param vo
	 * @return
	 */
	public String bindParmaForPOJOType(TestVO vo) {
		System.out.println( "thread id : " + Thread.currentThread() +  "\t class object :" + this);
		return "bindParmaSpringMvc";
	}
	
	/**
	 * Spring 自动绑定相同属性; 如若方法的arguments包含相同属性,当传入此属性时,都将被赋值
	 * like this :
	 * when transmit name, vo and cpVo will bind value in this example;
	 * @param vo
	 * @return
	 */
	public String bindParmaForPOJOTypeWhenMultiSameProperty(TestVO vo, CopyOfTestVO cpVo) {
		System.out.println( "thread id : " + Thread.currentThread() +  "\t class object :" + this);
		return "bindParmaSpringMvc";
	}
	
	@ResponseBody
	@JsonView
	public TestVO resJsonType () {
		System.out.println( "thread id : " + Thread.currentThread() +  "\t class object :" + this);
		TestVO vo = new TestVO();
		vo.setName("test response json");
		return vo;
	}
	
	public String testParmaSpringMvc(Model model) {
		model.addAttribute("message", "Hello SpringMvc!");
		return "helloSpringMvc";
	}
	
	//@ResponseBody
	public String testFileUpLoad(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		System.out.println(file ==null? "null" : file.getName());
		 Map<String, Object> jsonMap = new HashMap<String, Object>();
	        if(file.isEmpty()){
	            jsonMap.put("status", -1);
	            jsonMap.put("result", "【文件为空！】");
	            System.out.println("【文件为空！】");
	            //return jsonMap;
	            result.setDataObject(IService.OPER_ERROR);
	            return "0";
	        }
	        String fileName=file.getOriginalFilename();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	        String uploadPath = request.getSession().getServletContext().getRealPath("uploadfile"+sdf.format(new Date()));
	        System.out.println(uploadPath);
	        File uploadDir=new File(uploadPath);
	        if(!uploadDir.exists()){
	            uploadDir.mkdirs();
	        }
	        File uploadFile=new File(uploadPath+"/"+fileName);
	        file.transferTo(uploadFile);//上传
	        System.out.println("上传成功！");
	        result.setDataObject(sdf.format(new Date())+"/"+fileName);
//	        jsonMap.put("status", 1);
//	        jsonMap.put("result", "【上传成功！】");
	       // return jsonMap;
		
	        return sdf.format(new Date())+"/"+fileName.toString();
	}
	
	public String testResJsp() {
		return "/helloworld";
	}
}