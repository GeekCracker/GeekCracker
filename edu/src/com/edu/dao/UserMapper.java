package com.edu.dao;

import com.edu.domain.entity.User;

public interface UserMapper extends BaseMapper<User>{

	/**
	 * 根据用户名查询用户
	 * @param userName 传入一个用户名
	 * @return 返回用户信息
	 */
	public User queryByName(String userName);
}
