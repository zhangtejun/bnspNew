package com.cn.core.view;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;



public class PdfView extends BaseAbstractPDFView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//获取pdf 文件名
        setResponseHeader(model, response);
        if(!noFileName){
        	model.remove("fileName");
        }
		PdfPTable table = new PdfPTable(model.size()); 
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);  
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);  
        table.getDefaultCell().setBackgroundColor(Color.lightGray);  
        
        
    
        
        for(String key : model.keySet()){
        	Object value = model.get(key);
        	table.addCell(key);
        }
        for(String key : model.keySet()){
        	String value = (String) model.get(key);
        	table.addCell(new Paragraph(value, new Font(setBaseFont())));
        }
        document.add(table); 
	}
	

}
