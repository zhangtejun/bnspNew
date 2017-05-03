package com.cn.core.view;
import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;


/**
 * Pdf 视图解析器
 * @author 10539
 * 相当于beaname视图解析器
 */
public class PdfViewResolver extends WebApplicationObjectSupport implements ViewResolver {

	public View resolveViewName(String viewName, Locale locale){
		View view = null;
		try {
			view=this.getApplicationContext().getBean(viewName,View.class);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			logger.error("PdfViewResolver resolve View error!##########");
			view = null;
		}
		
		return view;
	}
}
