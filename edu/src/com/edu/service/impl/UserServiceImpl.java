package com.edu.service.impl;

import com.edu.dao.BaseMapper;
import com.edu.dao.UserMapper;
import com.edu.dao.impl.UserMapperImpl;
import com.edu.domain.entity.User;
import com.edu.service.UserService;

public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	/**初始化一个用户mapper对象*/
	private UserMapper userMapper = new UserMapperImpl();
	
	
	@Override
	public User queryByName(String userName) {
		return userMapper.queryByName(userName);
	}
	// ====================================
	@Override
	protected String getFields() {
		return " * ";
	}
	@Override
	protected BaseMapper<User> getMapper() {
		return userMapper;
	}
}
