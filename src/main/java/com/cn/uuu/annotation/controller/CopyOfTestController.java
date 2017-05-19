package com.cn.uuu.annotation.controller;

import java.util.List;
import java.util.Map;

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
		user.setPassword("zha");
		User baseUser =	userBaseDao.login(user);
		System.out.println(baseUser.toString());
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list =  userBaseDao.allList();
		System.out.println(list.get(1).get("username"));
		
	}
}
