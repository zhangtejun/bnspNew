package com.cn.core.view;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.cn.common.util.Util;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;

public abstract class BaseAbstractPDFView extends AbstractPdfView{

	protected static String fileName = "";
	public  boolean noFileName = true;
	public boolean isNoFileName() {
		return noFileName;
	}
	public void setNoFileName(boolean noFileName) {
		this.noFileName = noFileName;
	}
	public void setResponseHeader(Map<String, Object> model,HttpServletResponse response) throws UnsupportedEncodingException {
		//获取pdf 文件名
		fileName = (String) model.get("fileName");
		if(!Util.isNullOrEmpty(fileName)){
			//设置response  自动出现下载页面,非浏览器自动打开
//			response.setCharacterEncoding("UTF-8");
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition","attachment; filename="+java.net.URLEncoder.encode(fileName, "UTF-8")+".pdf");
	        this.noFileName=false;
		}
		if(noFileName){
		}else{
			model.remove(fileName);
		}
	}
	
	protected BaseFont setBaseFont() throws Exception {
		try {
//			return BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			return BaseFont.createFont("/config/font/SIMYOU.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("文件格式设置错误");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("文件格式设置错误");
		}
	}

}
