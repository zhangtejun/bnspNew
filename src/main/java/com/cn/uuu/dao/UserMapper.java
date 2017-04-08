package com.cn.uuu.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cn.uuu.pojo.User;

public interface UserMapper {
	@Select("SELECT * FROM user WHERE id = #{userId}")   
    User getUser(@Param("userId") long id); 
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectId(int id);

	User selectLogin(String username);

}