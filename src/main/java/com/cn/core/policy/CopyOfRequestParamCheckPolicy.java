package com.cn.core.policy;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.cn.uuu.core.exception.MainException;
/**
 * 跨站脚本请求参数检查
 */
@Component
public class CopyOfRequestParamCheckPolicy implements Policy{
    private Map<String, String> denyPattern;
    public Map<String, String> getDenyPattern() {
		return denyPattern;
	}

	public void setDenyPattern(Map<String, String> denyPattern) {
		this.denyPattern = denyPattern;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	private String messageKey;
	@Override
	public void permit(HttpServletRequest request) throws MainException {
		if (denyPattern == null || denyPattern.isEmpty()) {
            return;
        }
		Map map = getRequestData(request);
		
        for (Iterator<String> it = map.values().iterator(); it.hasNext();) {
            Object obj = it.next();
            if (obj != null && obj instanceof String) {
                match(request, obj);
            }
        }		
	}
	/**
	 * 请求参数转为Map
	 * @param request
	 * @return
	 */
	private Map getRequestData(HttpServletRequest request) {
		HashMap hashMap = new HashMap();
		for(Enumeration names = request.getParameterNames();names.hasMoreElements();){
			String s = (String)names.nextElement();
			// name对应的所有请求参数value
            String values[] = request.getParameterValues(s);
            if(values.length == 1)
            	hashMap.put(s, values[0]);
            else
            	hashMap.put(s, values);
		}
		
		return hashMap;
	}

	private void match(HttpServletRequest request, Object obj) throws MainException {
		String value = obj.toString().toLowerCase().replaceAll(" ", "");
		for (Iterator iter = denyPattern.values().iterator(); iter.hasNext();) {
            Pattern pattern = PatternPool.getPattern((String) iter.next());
            Matcher matcher = pattern.matcher(value);
            //find()对字符串进行匹配,匹配到的字符串可以在任何位置.只要包含value就返回true
            if (matcher.find()) {
                throw new MainException(messageKey, new Object[] {obj});
            }
        }
		
	}

	@Override
	public Map getSetting() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSetting(Map map) {
		// TODO Auto-generated method stub
		
	}

}
