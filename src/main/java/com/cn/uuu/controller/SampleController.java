package com.cn.uuu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cn.uuu.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Controller
public class SampleController {

	 @RequestMapping("/index")
	 public ModelAndView getSampleJsp() {
		   //1、收集参数、验证参数  
	       //2、绑定参数到命令对象  
	       //3、将命令对象传入业务对象进行业务处理  
	       //4、选择下一个页面  
	       ModelAndView mv = new ModelAndView();  
	       //添加模型数据 可以是任意的POJO对象  
	       mv.addObject("Name", "Hello World!");  
	       //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面  
	       mv.setViewName("index");  
	       return mv; 
//		 return "index";
	 }
	 
	 @RequestMapping("/config/sample.config")
	 public String getSampleConfig() {
		 return "sample.config";
	 }
	 
	 /**
	  * @param request
	  * @param model
	  * @return 
	  * 4种写法返回数据
	  * 1，request.setAttribute("Name", "value");
	  * 2，return new ModelAndView("Name", "value");
	  * 3，map.put(""Name", "value");
	  * 4，model.addAttribute("Name", "value");
	  */
	 @RequestMapping("/zh_CN/swf/sample.swf")
	 public String getSampleSwf(HttpServletRequest request,Model model) {
		 String result = request.getParameter("name");  
		 System.out.println(result);
		 model.addAttribute("Name", "二欢");
		 return "sample.swf";
	 }
	 
	 /**
	  * freemarker 模板
	  * @param request
	  * @param model
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/freemarker.do")
	 public String getSampleFtl(HttpServletRequest request,Model model) throws Exception {
		 String result = request.getParameter("name");  
		 System.out.println(result);
		 model.addAttribute("Name", "二欢");  
	     return "freemarker.ftl"; 
	 }
	/**
	 * velocity 模板引擎
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	 @RequestMapping("/velocity")
	 public String getSampleVelocity(HttpServletRequest request,Model model) throws Exception {
		 String result = request.getParameter("name");  
		 System.out.println(result);
		 model.addAttribute("Name", "二欢");  
	     return "index.vm"; 
	 }
	 
	 /**
	  * @RequestMapping(value = "/produces", produces = "application/json")：
	  * 表示将功能处理方法将生产json格式的数据，此时根据请求头中的Accept进行匹配，
	  * 如请求头“Accept:application/json”时即可匹配;
	  * @param request
	  * @param response
	  * @param model
	  * @return
	  * @throws JsonProcessingException
	  */
	 @RequestMapping(value="/jsons",produces=MediaType.APPLICATION_JSON_VALUE)
	 @ResponseBody
	 public String getSampleJson(HttpServletRequest request, HttpServletResponse response,Model model) throws JsonProcessingException{
		 	Map<String,Object> dataMap = new HashMap<String,Object>();
		 	dataMap.put("AA", "aa");
		 	dataMap.put("BB", "bb");
		 	dataMap.put("user", new User());
	        ObjectMapper objectMapper = new ObjectMapper();
	        String jsonString=objectMapper.writeValueAsString(dataMap);
	        System.out.println(jsonString);
	        return jsonString;
	 }
	 @RequestMapping("/pdf")
	 public String getSamplePdf(HttpServletRequest request,Model model) throws Exception {
		 String result = request.getParameter("name");  
		 System.out.println(result);
		 model.addAttribute("Name", "二欢");
		 model.addAttribute("zhazha", "渣渣");
		 model.addAttribute("fileName", request.getParameter("fileName"));
	     return "pdfView"; 
	 }
	 /**
	  * 使用pdf模板
	  * @param request
	  * @param model
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/pdfs")
	 public String getSamplePdfS(HttpServletRequest request,Model model) throws Exception {
		 String result = request.getParameter("name");  
		 System.out.println(result);
		 
		 Map map=new HashMap();
		 map.put("Name", "二欢");
		 map.put("ABC", "渣渣");
		 model.addAttribute("DATA",map);
//		 model.addAttribute("fileName", "test.pdf");
	     return "pdfStamperView.pdf"; 
	 }
	 /**
	  * Excel
	  * @param request
	  * @param model
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/excel")
	 public String getSampleExcel(HttpServletRequest request,Model model) throws Exception {
		 String result = request.getParameter("name");  
		 System.out.println(result);
		 model.addAttribute("Name", "二欢");
		 model.addAttribute("zhazha", "渣渣");
//		 model.addAttribute("fileName", "test.pdf");
	     return "excelView"; 
	 }
}
