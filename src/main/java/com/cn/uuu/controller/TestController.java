package com.cn.uuu.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.cn.uuu.service.BaseService;
/**
 * mybatis非注解的sql映射文件配置
 * @author 10539
 *
 */
@Controller
public class TestController extends BaseService {
	@RequestMapping(value="/hello.do",method=RequestMethod.GET)
	public void testBaseService(HttpServletRequest request,String name) {
		System.out.println("ok"+userService.getClass()+": "+sqlSessionTemplate.getClass());
		//*************************************
		/** 获取        WebApplicationContext
    	 *  方式1，  ServletContext servletContext=request.getSession().getServletContext();  
		 *	  	  WebApplicationContext webApplicationContext = 
		 *		  (WebApplicationContext)servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		 *  方式2(有问题，拿到的MessageSource可能和messageSource的实例不一样)， WebApplicationContext webApplicationContext =ContextLoader.getCurrentWebApplicationContext();
    	 *  方式3 自动注入@Autowired webApplicationContext
    	 */
    	WebApplicationContext webApplicationContext1 =ContextLoader.getCurrentWebApplicationContext();
    	/**
    	 * 国际化
    	 * 方式1，	@Autowired
		 *			private MessageSource msg; msg.getMessage()
		 * 方式2，          （自动注入@Autowired webApplicationContext）webApplicationContext.getMessage()
    	 */
    	String srt =name;
    	webApplicationContext.getMessage("aa", null, Locale.CHINA);
    	String s2 = messageSource.getMessage("aa", new Object[]{}, Locale.CHINA); 
    	String abc =webApplicationContext1.getMessage("aa", null, Locale.CHINA);
//    	request.setAttribute("abc", abc);
	}
}
