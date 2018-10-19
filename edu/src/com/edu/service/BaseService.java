package com.edu.service;

import java.util.List;

public interface BaseService<T> {
	
	/**
	 * ������ݵķ���
	 * @param t ����һ�����ݶ���
	 */
	public void save(T t);
	/**
	 * ��������б�ķ���
	 * @param list ����һ�������б�
	 */
	public void saveAll(List<T> list);
	/**
	 * ����idɾ�����ݣ��߼�ɾ����
	 * @param id ����һ��id
	 */
	public void deleteById(String id);
	/**
	 * ����idɾ�����ݣ���ʵɾ����
	 * @param id ����һ��id
	 */
	public void deleteByIdReal(String id);
	/**
	 * ����id�޸�
	 * @param t ����һ����Ҫ���޸ĵĶ���
	 */
	public void editById(T t);
	/**
	 * ����id��ѯ����
	 * @param id ����һ��id
	 * @return ����һ�����ݶ���
	 */
	public T queryById(String id);
}
