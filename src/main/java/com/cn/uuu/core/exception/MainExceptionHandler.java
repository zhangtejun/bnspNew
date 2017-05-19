package com.cn.uuu.core.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * 统一异常处理 
 * @author 10539
 *
 */
public class MainExceptionHandler implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();  
        model.put("ex", ex);  
        // 根据不同错误转向不同页面  
        if(ex instanceof ValidationMessage) {  
            return new ModelAndView("error", model);  
        }else if(ex instanceof MainException) { 
        	String de = ((MainException) ex).getDefaultMessage();
        	String a = ((MainException) ex).getMessage();
        	String b = ((MainException) ex).getMessageKey();
        	System.err.println(de+a+b);
            return new ModelAndView("error", model);  
        } else {  
            return new ModelAndView("error", model);  
        }  
	}

}
