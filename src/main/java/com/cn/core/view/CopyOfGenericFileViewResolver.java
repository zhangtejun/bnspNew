package com.cn.core.view;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

public class CopyOfGenericFileViewResolver extends AbstractCachingViewResolver {
	/**
	 * 需要注意的是，在找不到请求文件的时候，需要返回 null，这样 Spring 容器中所注册的其他低优先级的视图解析器才能被调用。
	 */

	private Logger logger = Logger.getLogger(CopyOfGenericFileViewResolver.class
			.getName());

	private int order = Integer.MIN_VALUE;

	// requested file location under web app
	private String location;

	// View
	private String viewName;

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getOrder() {
		return this.order;
	}

	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {{
        if (location == null) {
            throw new Exception(
                    "No location specified for GenericFileViewResolver.");
        }
        String requestedFilePath = location + viewName;
        Resource resource = null;
        //String url = "";
        try {
            logger.finest(requestedFilePath);
            resource = getApplicationContext().getResource(requestedFilePath);
            //url = resource.getURI().toString();
        } catch (Exception e) {
            // this exception should be catched and return null in order to call
            // next view resolver
            logger.finest("No file found for file: " + requestedFilePath);
            return null;
        }
        logger.fine("Requested file found: " + requestedFilePath + ", viewName:" + viewName);
        //get view from application content, scope=prototype
        GenericFileView view = this.getApplicationContext().getBean(this.viewName, GenericFileView.class);
        view.setUrl(requestedFilePath);
        view.setResponseContent(inputStreamTOString(resource.getInputStream(), "ISO-8859-1"));
        return view;
        }
	}
	final static int BUFFER_SIZE = 4096;

    /**
     * Convert Input to String based on specific encoding
     *
     * @param in
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String inputStreamTOString(InputStream in, String encoding)
            throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return new String(outStream.toByteArray(), encoding);
    }
    public static String getCurrentDateString() {

        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);
        return "" + calendar.get(Calendar.YEAR) + (month < 10 ? "0" + month : "" + month) + (date < 10 ? "0" + date : "" + date);

    }
    @Test
    public void name() throws DocumentException, IOException {
    	
    	
    	
    	
    	String fileName = "C:\\Users\\10539\\Downloads\\temp.pdf";
    	PdfReader reader = new PdfReader(fileName);
    	ByteArrayOutputStream bos =  new ByteArrayOutputStream();
    	PdfStamper ps = new PdfStamper(reader, bos);
    	AcroFields fi = ps.getAcroFields();
    	fillData(fi,data());
    	ps.setFormFlattening(true);
    	ps.close();
    	OutputStream fos = new FileOutputStream("C:\\Users\\10539\\Downloads\\temp1.pdf");
    	   fos.write(bos.toByteArray());
	}
    
    public void fillData(AcroFields acroFields,Map<String,Object> map) throws IOException, DocumentException {
		for(String key : map.keySet()){
			String value = (String) map.get(key);
			acroFields.setField(key, value);
		}
	}
    public Map data() {
		Map<String,Object> data = new HashMap<>();
		data.put("aa", "啊啊");
		data.put("111", "aaa");
    	return data;
	}
}
