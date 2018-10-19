package com.edu.service.impl;

import java.util.List;

import com.edu.annotation.Table;
import com.edu.dao.BaseMapper;
import com.edu.domain.response.StatusCode;
import com.edu.exception.BizException;
import com.edu.service.BaseService;
import com.edu.utils.GenericsUtils;

public abstract class BaseServiceImpl <T> implements BaseService<T>{

	// ����Ϊ�����,�����Ҫ������д,�˷����Ĺ����Ƕ���Ҫ��ѯ���ֶ�
	protected abstract String getFields();
	// �˷���֮���Զ���Ϊ�����,�Ǳ���Ҫ��������ȥ��д
	protected abstract BaseMapper<T> getMapper();
	// ��ȡʵ��
	protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());
	@Override
	public void save(T t) {
		getMapper().insert(t);
	}
	@Override
	public void saveAll(List<T> list) {
	}
	@Override
	public void deleteById(String id) {
		getMapper().deleteById(id);
	}
	@Override
	public void deleteByIdReal(String id) {
		getMapper().deleteByIdReal(id);
	}
	@Override
	public void editById(T t) {
		getMapper().editById(t);
	}
	@Override
	public T queryById(String id) {
		return getMapper().queryById(id);
	}
}
