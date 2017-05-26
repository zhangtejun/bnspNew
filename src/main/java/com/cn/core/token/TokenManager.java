package com.cn.core.token;

import javax.servlet.http.HttpServletRequest;

public interface TokenManager {
	/** createToken **/
	public abstract Token createToken(HttpServletRequest request);
	/** 验证 **/
	public abstract int verifyToken(HttpServletRequest request);
}
