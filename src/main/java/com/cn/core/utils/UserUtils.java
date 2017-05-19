package com.cn.core.utils;


import javax.servlet.http.HttpServletRequest;

import com.cn.uuu.user.entity.User;

public class UserUtils {
	public static User getLoginUser(HttpServletRequest request){
		User loginUserId = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        return loginUserId;
    }

    public static void setLoginUser(HttpServletRequest request,User user){
        request.getSession().setAttribute(Constants.SESSION_USER,user);
    }
}
