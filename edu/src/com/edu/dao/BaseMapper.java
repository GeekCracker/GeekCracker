package com.edu.dao;

public interface BaseMapper<T> {

	/**
	 * ���һ������
	 * @param t ����һ����Ҫ����ӵĶ���
	 * @return ������Ӱ������
	 */
	public Integer insert(T t);
	public Integer deleteById(String id);
	public Integer deleteByIdReal(String id);
	public Integer editById(T t);
	public T queryById(String id);
}
