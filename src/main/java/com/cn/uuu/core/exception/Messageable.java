package com.cn.uuu.core.exception;

import java.io.Serializable;

public interface Messageable extends Serializable {

    public abstract String getDefaultMessage();

    public abstract boolean hasDefaultMessage();

    public abstract String getMessageKey();

    public abstract Object[] getArgs();
}
