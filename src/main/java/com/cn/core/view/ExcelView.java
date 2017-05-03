package com.cn.core.view;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.cn.common.util.Util;

public class ExcelView  extends AbstractExcelView{

	@SuppressWarnings("rawtypes")
	@Override
	protected void buildExcelDocument(Map<String, Object> arg0,
			HSSFWorkbook workbook, HttpServletRequest arg2, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		String fileN = arg2.getParameter("fileName");
		String path = arg2.getRequestURI();
		path = path.substring(path.lastIndexOf('/')+1, path.indexOf('.'));
		if(!Util.isNullOrEmpty(fileN)){
			 path = fileN;
		}
		String fileName = path+"_"+getCurrentDateString()+".xls";  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/ms-excel");  
        response.setHeader("Content-Disposition", "inline; filename="+fileName);  
        OutputStream outputStream = response.getOutputStream();
        
        List listResult = new ArrayList();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("ID","1231");
        map.put("name","dsf");
        map.put("email","105@qq.com");
        map.put("password","*****");
        listResult.add(map);
        // 产生Excel表头
        HSSFSheet sheet = workbook.createSheet("基本信息");
        HSSFRow header = sheet.createRow(0);
        // 产生标题列
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("名字");
        header.createCell(2).setCellValue("邮箱");
        header.createCell(3).setCellValue("密码");
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("mm/dd/yyyy"));
        int rowNumber = 1;
            HSSFRow row = sheet.createRow(rowNumber++);
            
            // 产生标题列
            row.createCell(0).setCellValue((String)map.get("ID"));
            row.createCell(1).setCellValue((String)map.get("name"));
            row.createCell(2).setCellValue((String)map.get("email"));
            row.createCell(3).setCellValue((String)map.get("password"));
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
	}

    public static String getCurrentDateString() {

        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);
        return "" + calendar.get(Calendar.YEAR) + (month < 10 ? "0" + month : "" + month) + (date < 10 ? "0" + date : "" + date);

    }
	
	
	public static Workbook createWorkBook(List<Map<String, Object>> list,String []keys,String columnNames[]) {  
        // 创建excel工作簿  
        Workbook wb = new HSSFWorkbook();  
        // 创建第一个sheet（页），并命名  
        Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());  
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。  
        for(int i=0;i<keys.length;i++){  
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));  
        }  
  
        // 创建第一行  
        Row row = sheet.createRow((short) 0);  
  
        // 创建两种单元格格式  
        CellStyle cs = wb.createCellStyle();  
        CellStyle cs2 = wb.createCellStyle();  
  
        // 创建两种字体  
        Font f = wb.createFont();  
        Font f2 = wb.createFont();  
  
        // 创建第一种字体样式（用于列名）  
        f.setFontHeightInPoints((short) 10);  
        f.setColor(IndexedColors.BLACK.getIndex());  
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);  
  
        // 创建第二种字体样式（用于值）  
        f2.setFontHeightInPoints((short) 10);  
        f2.setColor(IndexedColors.BLACK.getIndex());  
  
//        Font f3=wb.createFont();  
//        f3.setFontHeightInPoints((short) 10);  
//        f3.setColor(IndexedColors.RED.getIndex());  
  
        // 设置第一种单元格的样式（用于列名）  
        cs.setFont(f);  
        cs.setBorderLeft(CellStyle.BORDER_THIN);  
        cs.setBorderRight(CellStyle.BORDER_THIN);  
        cs.setBorderTop(CellStyle.BORDER_THIN);  
        cs.setBorderBottom(CellStyle.BORDER_THIN);  
        cs.setAlignment(CellStyle.ALIGN_CENTER);  
  
        // 设置第二种单元格的样式（用于值）  
        cs2.setFont(f2);  
        cs2.setBorderLeft(CellStyle.BORDER_THIN);  
        cs2.setBorderRight(CellStyle.BORDER_THIN);  
        cs2.setBorderTop(CellStyle.BORDER_THIN);  
        cs2.setBorderBottom(CellStyle.BORDER_THIN);  
        cs2.setAlignment(CellStyle.ALIGN_CENTER);  
        //设置列名  
        for(int i=0;i<columnNames.length;i++){  
            Cell cell = row.createCell(i);  
            cell.setCellValue(columnNames[i]);  
            cell.setCellStyle(cs);  
        }  
        //设置每行每列的值  
        for (short i = 1; i < list.size(); i++) {  
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的  
            // 创建一行，在页sheet上  
            Row row1 = sheet.createRow((short) i);  
            // 在row行上创建一个方格  
            for(short j=0;j<keys.length;j++){  
                Cell cell = row1.createCell(j);  
                cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());  
                cell.setCellStyle(cs2);  
            }  
        }  
        return wb;  
    } 
}
