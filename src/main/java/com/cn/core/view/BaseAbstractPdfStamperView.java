package com.cn.core.view;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfStamperView;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfStamper;
public class BaseAbstractPdfStamperView extends AbstractPdfStamperView {
public static final String DATA = "DATA";
public static final String FILENAME = "pdfFileName";
	@Override
	protected void mergePdfDocument(Map<String, Object> model,
			PdfStamper stamper, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setHeader("Content-Disposition","attachment; filename="+java.net.URLEncoder.encode(FILENAME, "UTF-8")+".pdf");
		
		AcroFields fields = stamper.getAcroFields();
		fillData(fields,model);
		stamper.setFormFlattening(true);
		
	}
	private static void fillData(AcroFields fields, Map<String,Object> data) throws IOException, DocumentException {
		// TODO Auto-generated method stub
		BaseFont font = BaseFont.createFont("/config/font/SIMYOU.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		Map<String,String> dataMap = (Map) data.get(DATA);
		for(String key : dataMap.keySet()){
			String value = (String) dataMap.get(key);
			fields.setFieldProperty(key, "textfont", font, null);
			fields.setField(key, value);
		}
	}

}
