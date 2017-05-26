package com.cn.core.token;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SimpleTokenManager implements TokenManager {
	public int getMaxEntryNumber() {
		return maxEntryNumber;
	}

	public void setMaxEntryNumber(int maxEntryNumber) {
		this.maxEntryNumber = maxEntryNumber;
	}

	public int getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}

	public String getTokenListName() {
		return tokenListName;
	}

	public void setTokenListName(String tokenListName) {
		this.tokenListName = tokenListName;
	}

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public int getTokenLength() {
		return tokenLength;
	}

	public void setTokenLength(int tokenLength) {
		this.tokenLength = tokenLength;
	}

	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	private int maxEntryNumber;
	private int delayTime;
	private String tokenListName;
	private String tokenName;
	private int tokenLength;
	private boolean ignoreCase;
	protected Log log;

	public SimpleTokenManager() {
		maxEntryNumber = 3;
		delayTime = 15;
		tokenListName = "_DCTOKENLIST";
		tokenName = "_tokenName";
		tokenLength = 8;
		ignoreCase = false;
		log = LogFactory.getLog(getClass());
	}

	@Override
	public Token createToken(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String tokenListNameF = tokenListName;
        String _pageIdName = (String) request.getSession().getAttribute("_pageIdName");
        if(_pageIdName !=null && !"".equals(_pageIdName)){
        	tokenListNameF = (new StringBuilder()).append(tokenListName).append("_").append(_pageIdName).toString();
        	request.getSession().removeAttribute("_pageIdName");
        }
        TokenList tokenList = (TokenList) request.getSession().getAttribute(tokenListNameF);
        
        if(tokenList == null){
        	tokenList = new TokenList(maxEntryNumber, delayTime, tokenLength);
        	request.getSession().setAttribute(tokenListNameF, tokenList);
        }
        String s = tokenList.getNextTokenId();
        if (ignoreCase) {
            s = s.replaceAll("0", "1");
            s = s.replaceAll("o", "p");
            s = s.replaceAll("1", "2");
            s = s.replaceAll("l", "m");
        }
        TokenImpl tokenimpl = new TokenImpl(s, System.currentTimeMillis());
        tokenList.add(tokenimpl);
        request.getSession().setAttribute(tokenName, tokenimpl.getUniqueId());
        if (log.isDebugEnabled()) log.debug((new StringBuilder()).append("Create Token").append(tokenListNameF).append(",").append(tokenName).append(" ").append(tokenimpl).toString());
        return tokenimpl;
	}

	@Override
	public int verifyToken(HttpServletRequest request) {

        String tokenListNameF = tokenListName;
        String _pageIdName = (String) request.getSession().getAttribute("_pageIdName");
        if (_pageIdName != null && !"".equals(_pageIdName)) tokenListNameF = (new StringBuilder()).append(tokenListName).append("_").append(_pageIdName).toString();
        TokenList tokenlist = (TokenList) request.getSession().getAttribute(tokenListNameF);
        log.info((new StringBuilder()).append("@@@@@@@:").append("#######").append(",pageToken is :").append((String) request.getSession().getAttribute(tokenName))
                .append(",TokenList is:").append(tokenlist).toString());
        if (tokenlist == null) return 0;
        String s = (String) request.getSession().getAttribute(tokenName);
        if (s == null) return 0;
        if (ignoreCase) s = s.toLowerCase();
        try {
            Token token = null;
            synchronized (tokenlist) {
                token = tokenlist.get(s);
            }
            return token != null ? 1 : 0;
        } catch (Exception exception) {
            return -1;
        }
    
	}

}
