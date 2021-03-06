package com.cn.uuu.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cn.uuu.core.dao.UserBaseDao;
import com.cn.uuu.user.entity.User;

/**
 * mybatis注解的sql映射文件配置
 * 
 * @author 10539
 * 
 */
@Controller
public class CopyOfTestController {
	@Resource
	private UserBaseDao userBaseDao;

	@RequestMapping(value = "/hello1.do", method = RequestMethod.GET)
	public void testBaseService(HttpServletRequest request, String name) {

		com.cn.uuu.user.entity.User user = new User();
		user.setId(11);
		user.setUsername("zha");
		user.setUsername("zha");
		User baseUser =	userBaseDao.login(user);
		System.out.println(baseUser.toString());
		
		List list = userBaseDao.allList();
		System.out.println(list.size());
		
	}
}
