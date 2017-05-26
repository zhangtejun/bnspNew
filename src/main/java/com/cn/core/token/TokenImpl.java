package com.cn.core.token;

public class TokenImpl implements Token{
	private String uniqueId;
    private long accessDate;

    public TokenImpl() {
    }

    public TokenImpl(String var1, long var2) {
        this.uniqueId = var1;
        this.accessDate = var2;
    }

    public String toString() {
        return this.getClass().getName() + " :" + this.uniqueId + " " + this.accessDate;
    }

    public long getAccessDate() {
        return this.accessDate;
    }

    public void setAccessDate(long var1) {
        this.accessDate = var1;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    public void setUniqueId(String var1) {
        this.uniqueId = var1;
    }
}
