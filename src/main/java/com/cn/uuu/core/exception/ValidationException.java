package com.cn.uuu.core.exception;

public class ValidationException extends MainException implements ValidationMessage{

	public ValidationException()
    {
    }

    public ValidationException(String s)
    {
        super(s);
    }


    public ValidationException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

}
