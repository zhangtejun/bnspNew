package com.cn.uuu.core.dao;

import com.cn.uuu.user.entity.User;

/**
 * 用户基础dao接口
 * @author 10539
 *
 */
public interface UserBaseDao extends BaseDao<User> {
	User login(User user);
}
