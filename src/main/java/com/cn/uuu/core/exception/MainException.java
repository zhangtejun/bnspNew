package com.cn.uuu.core.exception;

import org.springframework.core.NestedCheckedException;

public class MainException extends NestedCheckedException implements Messageable{
	private Object args[];
    private MessageSupport messageSupport;
    private static final String DEFAULT = "main.error.undefined";
    
    public MainException() {
    	super("");
    	messageSupport = new MessageSupport();
    	messageSupport.setDefaultMessage(DEFAULT);
    	
	}
    
	public MainException(String msg) {
		super(msg);
		messageSupport = new MessageSupport();
		if(msg == null || msg.trim().length()==0){
			messageSupport.setMessageKey(DEFAULT);
		}else{
			messageSupport.setMessageKey(msg);
		}
	}

	public MainException(String s, Throwable throwable){
		super(s, throwable);
		messageSupport = new MessageSupport();
        if(s == null || s.trim().length() == 0)
        	messageSupport.setMessageKey("pe.error.undefined");
        else
        	messageSupport.setMessageKey(s);
	}
	
	
	
	public MainException(String messageKey, Object[] objects) {
		super(messageKey);
		messageSupport = new MessageSupport();
		messageSupport.setArgs(objects);
	}

	@Override
	public String getDefaultMessage() {
        String s = messageSupport.getDefaultMessage();
        if(s == null)
        {
            StringBuffer stringbuffer = (new StringBuffer(getClass().getName())).append(" MessageCode: ").append(getMessageKey());
            if(messageSupport.getArgs() != null)
            {
                stringbuffer.append(" Args: ");
                Object aobj[] = messageSupport.getArgs();
                for(int i = 0; i < aobj.length; i++)
                    stringbuffer.append(aobj[i]).append(" ");

            }
            if(getCause() != null)
            {
                stringbuffer.append(" nested exception is: ");
                stringbuffer.append(getCause());
            }
            return stringbuffer.toString();
        } else
        {
            return s;
        }
    
	}

	@Override
	public boolean hasDefaultMessage() {
		return messageSupport.hasDefaultMessage();
	}

	public void setDefaultMessage(String msg){
		messageSupport.setDefaultMessage(msg);
	}
	
	@Override
	public String getMessageKey() {
		return messageSupport.getMessageKey();
	}

	@Override
	public Object[] getArgs() {
		// TODO Auto-generated method stub
		return messageSupport.getArgs();
	}
    public void setArgs(Object obj[])
    {
        messageSupport.setArgs(obj);
    }
	
	@Override
	public String toString() {

        StringBuffer stringbuffer = new StringBuffer(super.toString());
        if(messageSupport.getArgs() != null)
        {
            stringbuffer.append(" Args:");
            Object aobj[] = messageSupport.getArgs();
            for(int i = 0; i < aobj.length; i++)
                stringbuffer.append(aobj[i]).append(" ");

        }
        return stringbuffer.toString();
    }
}
