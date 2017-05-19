package com.cn.core.policy;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cn.uuu.core.exception.MainException;

public interface Policy {
    public abstract void permit(HttpServletRequest request)
            throws MainException;

        public abstract Map getSetting();

        public abstract void setSetting(Map map);
}
