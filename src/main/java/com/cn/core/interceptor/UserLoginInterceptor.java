package com.cn.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cn.core.utils.UserUtils;
import com.cn.core.utils.Util;
import com.cn.uuu.user.entity.User;

/**
 * @author zhangtejun
 * @date 2017年5月17日 下午3:38:17
 */
public class UserLoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		User user = UserUtils.getLoginUser(request);
		if(Util.isNullOrEmpty(user)||user.getId()==null){
			/** 跳转登陆页面 **/
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}else{
			/** 已登陆用户的其他验证操作 **/
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("post");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.err.println("after");
	}}
