package com.edu.service;

import com.edu.domain.entity.Role;

public interface RoleService extends BaseService<Role>{

	/**
	 * 根据角色名称获取角色
	 * @param roleName 传入一个角色名称
	 * @return 返回角色对象
	 */
	public Role queryByName(String roleName);
}
