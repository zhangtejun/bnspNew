package com.cn.uuu.annotation.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import com.cn.uuu.service.BaseService;
/**
 * mybatis非注解的sql映射文件配置
 * @author 10539
 *  Spring 容器中的 Bean 是有生命周期的，Spring 允许在 Bean 在初始化完成后以及 Bean 销毁前执行特定的操作，常用的设定方式有以下三种：
	通过实现 InitializingBean/DisposableBean 接口来定制初始化之后/销毁之前的操作方法；
	通过 元素的 init-method/destroy-method属性指定初始化之后 /销毁之前调用的操作方法；
	在指定方法上加上@PostConstruct 或@PreDestroy注解来制定该方法是在初始化之后还是销毁之前调用。
	Constructor > @PostConstruct > InitializingBean > init-method
 */

@Controller
public class TestController extends BaseService implements InitializingBean,ApplicationContextAware{
	@Transactional
	@RequestMapping(value="/home/hello.do",method=RequestMethod.GET)
	public void testBaseService(HttpServletRequest request,String name) {
		System.out.println("ok"+userService.getClass()+": "+sqlSessionTemplate.getClass());
		
		Map map = new HashMap<String,Object>();
		map.put("title", "intitle");
		map.put("author", "author");
		sqlSessionTemplate.insert("mybatis.in", map);
		map.put("name", "intitleo");
		sqlSessionTemplate.insert("mybatis.inOther", map);
		System.err.println("11");
		
		
		//*************************************
		/** 获取        WebApplicationContext
    	 *  方式1，  ServletContext servletContext=request.getSession().getServletContext();  
		 *	  	  WebApplicationContext webApplicationContext = 
		 *		  (WebApplicationContext)servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		 *  方式2(有问题，拿到的MessageSource可能和messageSource的实例不一样)， WebApplicationContext webApplicationContext =ContextLoader.getCurrentWebApplicationContext();
    	 *  方式3 自动注入@Autowired webApplicationContext
    	 */
//    	WebApplicationContext webApplicationContext1 =ContextLoader.getCurrentWebApplicationContext();
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

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.err.println("11111111111111");
		System.err.println("11111111111111");
	}
	WebApplicationContext webApplicationContext1;
	/**
	 * 实现ApplicationContextAware，spring能够为我们自动地执行了setApplicationContext。
	 */
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		webApplicationContext1=(WebApplicationContext) arg0;
		
	}
	public TestController() {
		// TODO Auto-generated constructor stub
		
		System.out.println("****************************TestController");
	}
	@Test
	public void name() {
		String[] a = new String[]{"123,1,ds"};
		System.err.println();
	}
	/**
	 * token 获取
	 * @param request
	 */
	@RequestMapping(value="/public/getToken.do")
	public void getToken(HttpServletRequest request) {
		tokenManager.createToken(request);
	}
	
	@RequestMapping(value="/index.do")
	public String index(HttpServletRequest request,Model model) {
		String  url  =  "http://"  +  request.getServerName()  +  ":"  +  request.getServerPort()  +  request.getContextPath();
		model.addAttribute("UrlPath", url);
		return "index";
	}
	
}
