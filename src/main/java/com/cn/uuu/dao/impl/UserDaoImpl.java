package com.cn.uuu.dao.impl;

import org.springframework.beans.factory.InitializingBean;

import com.cn.uuu.dao.UserDao;
import com.cn.uuu.dao.UserMapper;
import com.cn.uuu.pojo.User;

public class UserDaoImpl implements UserDao,InitializingBean{

	 private UserMapper userMapper;   
	  
     public void setUserMapper(UserMapper userMapper) {   
         this.userMapper = userMapper;   
     }    
  
     public User getUserById(User user) {  
        return userMapper.getUser(user.getId());   
     }

	public void afterPropertiesSet() throws Exception {
		System.err.println("*****************************InitializingBean**********");
		
	}  

}
