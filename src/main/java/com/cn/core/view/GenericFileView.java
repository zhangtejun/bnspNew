package com.cn.core.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractUrlBasedView;
/**
 * 自定义通用视图类
 * @author 10539
 *
 */
public class GenericFileView extends AbstractUrlBasedView{
	 // 默认内容类型 text/plain的意思是将文件设置为纯文本的形式，浏览器在获取到这种文件时并不会对其进行处理。
	 private final static String CONTENT_TYPE = "text/plain"; 

	 //http response conent 
	 private String responseContent;
	 
	 public void setResponseContent(String responseContent) { 
	     this.responseContent = responseContent; 
	 } 
	 
	 public GenericFileView() { 
	     super(); 
	     setContentType(CONTENT_TYPE); 
	 }
	 
	
	@Override
	public void setContentType(String contentType) {
		// TODO Auto-generated method stub
		super.setContentType(contentType);
	}
	 
	 
	@Override
	protected void renderMergedOutputModel(Map<String, Object> map,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
	     // 设置 content type 
		httpservletresponse.setContentType(getContentType()); 
	     // 写入 response 内容
		httpservletresponse.getWriter().write(this.responseContent); 
		httpservletresponse.getWriter().close();
	}

}
