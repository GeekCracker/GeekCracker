package com.edu.dao;

public interface BaseMapper<T> {

	/**
	 * 添加一个对象
	 * @param t 传入一个需要被添加的对象
	 * @return 返回受影响行数
	 */
	public Integer insert(T t);
	public Integer deleteById(String id);
	public Integer deleteByIdReal(String id);
	public Integer editById(T t);
	public T queryById(String id);
}
