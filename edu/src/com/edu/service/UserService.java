package com.edu.service;

import com.edu.domain.entity.User;

public interface UserService extends BaseService<User>{

	/**
	 * �����û�����ѯ�û�
	 * @param userName ����һ���û���
	 * @return �����û���Ϣ
	 */
	public User queryByName(String userName);
}
