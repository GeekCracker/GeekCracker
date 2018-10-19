package com.edu.service;

import java.util.List;

public interface BaseService<T> {
	
	/**
	 * 添加数据的方法
	 * @param t 传入一个数据对象
	 */
	public void save(T t);
	/**
	 * 添加数据列表的方法
	 * @param list 传入一个数据列表
	 */
	public void saveAll(List<T> list);
	/**
	 * 根据id删除数据（逻辑删除）
	 * @param id 传入一个id
	 */
	public void deleteById(String id);
	/**
	 * 根据id删除数据（真实删除）
	 * @param id 传入一个id
	 */
	public void deleteByIdReal(String id);
	/**
	 * 根据id修改
	 * @param t 传入一个需要被修改的对象
	 */
	public void editById(T t);
	/**
	 * 根据id查询数据
	 * @param id 传入一个id
	 * @return 返回一个数据对象
	 */
	public T queryById(String id);
}
