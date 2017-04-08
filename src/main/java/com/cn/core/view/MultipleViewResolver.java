package com.cn.core.view;

import java.util.Locale;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

public class MultipleViewResolver extends AbstractCachingViewResolver implements Ordered {

	private int order = Integer.MIN_VALUE;


	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	private Map<String, ViewResolver> resolvers;

	public void setResolvers(Map<String, ViewResolver> resolvers) {
		this.resolvers = resolvers;
	}

	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {
		/**
		 * 取后缀（sample.swf）swf
		 */
		String fileExtension = StringUtils.getFilenameExtension(viewName);
		// return null to invoke next resolver if no extension found
		if (fileExtension == null) {
			return null;
		}
		// get resolver by extension
		ViewResolver resolver = resolvers.get(fileExtension);
		//return null to invoke next resolver if no resolver found
		return resolver == null ? null : resolver.resolveViewName(viewName,locale);

	}

}
