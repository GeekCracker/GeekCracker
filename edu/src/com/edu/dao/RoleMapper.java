package com.edu.dao;

import com.edu.domain.entity.Role;

public interface RoleMapper extends BaseMapper<Role>{

	/**
	 * ���ݽ�ɫ���ƻ�ȡ��ɫ
	 * @param roleName ����һ����ɫ����
	 * @return ���ؽ�ɫ����
	 */
	public Role queryByName(String roleName);
}
