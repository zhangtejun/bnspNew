package com.cn.uuu.dao.impl;

import com.cn.uuu.dao.UserDao;
import com.cn.uuu.dao.UserMapper;
import com.cn.uuu.pojo.User;

public class UserDaoImpl implements UserDao{

	 private UserMapper userMapper;   
	  
     public void setUserMapper(UserMapper userMapper) {   
         this.userMapper = userMapper;   
     }    
  
     public User getUserById(User user) {  
        return userMapper.getUser(user.getId());   
     }  

}
