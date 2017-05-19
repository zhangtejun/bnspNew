package com.cn.uuu.core.exception;

public class MessageSupport implements Messageable {
	private static final long serialVersionUID = -4112462286744437725L;
	private String defaultMessage;
    private String messageKey;
    private Object Args[];
    public MessageSupport() {
    	defaultMessage = null;
    	messageKey = null;
    	Args = new String[0];
	}
    
	public String getDefaultMessage() {
		return defaultMessage;
	}
	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}
	public String getMessageKey() {
		return messageKey;
	}
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	public Object[] getArgs() {
		return Args;
	}
	public void setArgs(Object[] args) {
		Args = args;
	}
	@Override
	public boolean hasDefaultMessage() {
		return defaultMessage!=null;
	}
    
}
