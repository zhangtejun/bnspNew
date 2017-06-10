package com.cn.uuu.core.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.classic.Logger;
/**
 * 统一异常处理 
 * @author 10539
 * 添加不打印堆栈的错误码列表 ignorePrintStackList
 *
 */
public class MainExceptionHandler implements HandlerExceptionResolver{
	Log logger = LogFactory.getLog(getClass());
	@Autowired 
	WebApplicationContext webApplicationContext;
	
	Log j = LogFactory.getLog(getClass());
	private List ignorePrintStackList = new ArrayList();

    public List getIgnorePrintStackList() {
        return ignorePrintStackList;
    }

    public void setIgnorePrintStackList(List ignorePrintStackList) {
        this.ignorePrintStackList = ignorePrintStackList;
    }
	
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {
		Map<String, Object> model = new HashMap<String, Object>();  
        model.put("ex", exception);  
        
        
        if (j.isDebugEnabled()) j.debug(exception.getMessage(), exception);
        
        
     // 如果是非交易业务报错代码手动抛出的异常，打印异常堆栈
        if (!(exception instanceof TransactionException)) {
            resolverDetailMainErrorInfo(exception);
            logException(exception);
        } else {
            resolverDetailMainErrorInfo(exception);
            logException(exception);
        }
        
        Map  map = resolverRejectMessages(webApplicationContext, request, exception);
        
        model.put("_exceptionMessage", map.get("_exceptionMessage"));
        // 根据不同错误转向不同页面  
        if(exception instanceof ValidationMessage) {  
            return new ModelAndView("index", model);  
        }else if(exception instanceof MainException) { 
        	String de = ((MainException) exception).getDefaultMessage();
        	String a = ((MainException) exception).getMessage();
        	String b = ((MainException) exception).getMessageKey();
        	System.err.println(de+a+b);
            return new ModelAndView("index", model);  
        } else {  
            return new ModelAndView("index", model);  
        }  
	}
	
	
	
	
    private Map resolverRejectMessages(
			WebApplicationContext webApplicationContext2,
			HttpServletRequest request, Exception exception) {
    	
    	HashMap hashMap = new HashMap(2);
    	Locale locale = request.getLocale();
    	String errorKey = exception.getMessage();
    	if(((Messageable)exception).getMessageKey() != null){
    		errorKey = ((Messageable)exception).getMessageKey();
    	}
    	String errorMsg = null;
    	try{
    		if(errorKey != null && errorKey.length()>= 1){
    			errorMsg = webApplicationContext2.getMessage(errorKey, null, locale);
    		}
    	}catch(Exception e){
    		//不处理
    		logger.error("webApplicationContext getMessage error @@@@@@@ errorMsg used exception.getMessage()");
    		errorMsg = exception.getMessage();
    	}
    	hashMap.put("_exceptionMessage", errorMsg);
		return hashMap;
    	
	}

	/**
     * 指定错误集不打印错误堆栈
     * @param exception
     */
    private void logException(Exception exception) {
        if (exception instanceof MainException) {
            if (!ignorePrintStackList.contains(((MainException) exception).getMessageKey())) {
                j.error(exception.getMessage(), exception);
            }
            return;
        }
        j.error(exception.getMessage(), exception);
        return;
    }
	
	private void resolverDetailMainErrorInfo(Exception e) {
        StringBuffer exceptionDetailMain = new StringBuffer();
        try {
            // 1.defaultMessage
            exceptionDetailMain.append(e.getMessage()).append("\t");
            // 2.对异常有catch重新处理转换并抛出的情况，定位到问题根源
            exceptionDetailMain.append(e.getClass()).append(":");
            // 3.添加堆栈顶信息
            if (e.getCause() != null && e.getCause().getStackTrace() != null && e.getCause().getStackTrace().length >= 0) {
                exceptionDetailMain.append(e.getCause().getStackTrace()[0].toString());
            } else {
                if (e.getStackTrace() != null && e.getStackTrace().length >= 0) {
                    exceptionDetailMain.append(e.getStackTrace()[0].toString());
                }
            }
        } catch (Exception excep) {
            j.error("resolverDetailMainErrorInfo:" + excep);
        }
        j.error(exceptionDetailMain.toString());
    }

}
