package com.cn.core.policy;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cn.core.token.TokenManager;
import com.cn.uuu.core.exception.MainException;

/**
 * 防止页面请求伪造策略
 * @author zhangtejun
 * @date 2017年5月19日 下午1:19:37
 */
public class TokenControlPolicy implements Policy{
	TokenManager tokenManager;
	String messageKey;
	String timeoutMessageKey;
	public TokenManager getTokenManager() {
		return tokenManager;
	}
	public void setTokenManager(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getTimeoutMessageKey() {
		return timeoutMessageKey;
	}

	public void setTimeoutMessageKey(String timeoutMessageKey) {
		this.timeoutMessageKey = timeoutMessageKey;
	}

	public TokenControlPolicy() {
		this.messageKey = "validation.invalid_token";
		this.timeoutMessageKey = "validation.resubmit_dynamicPass";
	}
	
	@Override
	public void permit(HttpServletRequest request) throws MainException {
		int i = tokenManager.verifyToken(request);
		if(i==1)return;
		if(i==-1){
			if(timeoutMessageKey !=null){
				throw new MainException(timeoutMessageKey, new Object[]{new Integer(i)});
			}else{
				throw new MainException(messageKey, new Object[]{new Integer(i)});
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
