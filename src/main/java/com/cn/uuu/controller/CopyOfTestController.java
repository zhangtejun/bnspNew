package com.cn.uuu.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.cn.uuu.dao.UserDao;
import com.cn.uuu.pojo.User;
import com.cn.uuu.service.BaseService;
/**
 * mybatis注解的sql映射文件配置
 * @author 10539
 *
 */
@Controller
public class CopyOfTestController {
//	@Autowired
	public UserDao userDao;
	@RequestMapping(value="/hello1.do",method=RequestMethod.GET)
	public void testBaseService(HttpServletRequest request,String name) {
		
		User user = new User();
		user.setId(11);
		User u =userDao.getUserById(user);
		System.err.println(userDao.getClass());
	}
}
