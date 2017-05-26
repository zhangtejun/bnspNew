package com.cn.core.token;

public interface Token {
	public abstract void setAccessDate(long l);
	public abstract long getAccessDate();
	public abstract void setUniqueId(String s);
	public abstract String getUniqueId();
}
