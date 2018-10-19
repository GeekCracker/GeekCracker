package com.edu.dao;

import com.edu.domain.entity.User;

public interface UserMapper extends BaseMapper<User>{

	/**
	 * �����û�����ѯ�û�
	 * @param userName ����һ���û���
	 * @return �����û���Ϣ
	 */
	public User queryByName(String userName);
}
