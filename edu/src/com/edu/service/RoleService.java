package com.edu.service;

import com.edu.domain.entity.Role;

public interface RoleService extends BaseService<Role>{

	/**
	 * ���ݽ�ɫ���ƻ�ȡ��ɫ
	 * @param roleName ����һ����ɫ����
	 * @return ���ؽ�ɫ����
	 */
	public Role queryByName(String roleName);
}
