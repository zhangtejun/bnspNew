package com.cn.core.view;

import java.util.Locale;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

public class MultipleViewResolver extends AbstractCachingViewResolver implements Ordered {

	private static final String DEFAULT = "default";
	private int order = Integer.MIN_VALUE;
	private View defaultViews;

	public void setDefaultViews(View defaultViews) {
		this.defaultViews = defaultViews;
	}
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	private Map<String, ViewResolver> viewResolvers;

	public void setViewResolvers(Map<String, ViewResolver> viewResolvers) {
		this.viewResolvers = viewResolvers;
	}
	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {
		/**
		 * 取后缀（sample.swf）swf
		 */
		String fileExtension = StringUtils.getFilenameExtension(viewName);
		// return null to invoke next resolver if no extension found
		if (fileExtension == null && viewName.equals("json")) {
//			defaultViews.render(model, request, response);
		}else{
			if(!("pdf").equals(fileExtension)&&!("xls").equals(fileExtension)){
				fileExtension = DEFAULT;//默认无分隔符
			}
		}
		// get resolver by extension
		ViewResolver resolver = viewResolvers.get(fileExtension);
		int index =viewName.indexOf('.');
		if(index > 0){
			viewName = viewName.substring(0,index);
		}
		//return null to invoke next resolver if no resolver found
		return resolver == null ? null : resolver.resolveViewName(viewName,locale);
	}
	
	@Override
	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
		// TODO Auto-generated method stub
		return super.resolveViewName(viewName, locale);
	}
}
