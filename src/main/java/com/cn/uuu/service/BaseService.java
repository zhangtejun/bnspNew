package com.cn.uuu.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.context.WebApplicationContext;

import com.cn.core.token.SimpleTokenManager;


/**
 * 基础服务类  针对方式一，方式二需要注释掉@Resource(name="oneSqlMapExecutorTemplate")
 * @author 10539
 *
 */
public class BaseService {
	/**
	 * 获取token
	 */
	@Autowired
	protected SimpleTokenManager tokenManager;
	/**
	 * 提供userService
	 */
//	@Resource(name="userServiceImpl")
	public UserService userService;
	
	/**
	 * 提供sqlSession
	 */
//	@Resource(name="oneSqlMapExecutorTemplate")
	public SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 提供资源
	 */
	@Autowired
	public MessageSource messageSource;
	@Autowired
	public WebApplicationContext webApplicationContext;
}
