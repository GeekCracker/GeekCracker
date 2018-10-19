package com.edu.service.impl;

import com.edu.dao.BaseMapper;
import com.edu.dao.RoleMapper;
import com.edu.dao.impl.RoleMapperImpl;
import com.edu.domain.entity.Role;
import com.edu.service.RoleService;

public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{

	private RoleMapper roleMapper = new RoleMapperImpl();
	
	@Override
	public Role queryByName(String roleName) {
		return roleMapper.queryByName(roleName);
	}

	// ==============================
	@Override
	protected String getFields() {
		return "*";
	}

	@Override
	protected BaseMapper<Role> getMapper() {
		return roleMapper;
	}
}
