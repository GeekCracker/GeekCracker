package com.edu.service.impl;

import java.util.List;

import com.edu.annotation.Table;
import com.edu.dao.BaseMapper;
import com.edu.domain.response.StatusCode;
import com.edu.exception.BizException;
import com.edu.service.BaseService;
import com.edu.utils.GenericsUtils;

public abstract class BaseServiceImpl <T> implements BaseService<T>{

	// 定义为抽象的,则必须要子类重写,此方法的功能是定义要查询的字段
	protected abstract String getFields();
	// 此方法之所以定义为抽象的,是必须要交给子类去重写
	protected abstract BaseMapper<T> getMapper();
	// 获取实体
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
