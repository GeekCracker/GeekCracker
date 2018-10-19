package com.edu.dao.impl;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.edu.annotation.Table;
import com.edu.dao.BaseMapper;
import com.edu.domain.handler.IdTypeHandler;
import com.edu.domain.response.StatusCode;
import com.edu.exception.BizException;
import com.edu.utils.GenericsUtils;
import com.edu.utils.sql.JDBCUtils;

public abstract class BaseMapperImpl<T> implements BaseMapper<T> {

	// ��ȡʵ��
	protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public Integer insert(T t) {
		// ͨ��sql��乹������ȡsql���
		String sql = insertMapperProvider(t);
		try {
			return JDBCUtils.doUpdate(sql);
		} catch (SQLException e) {
			throw new BizException(StatusCode.INSERT_ENTITY_EXCEPTION);
		}
	}

	@Override
	public Integer deleteById(String id) {
		String sql = "update " + getTableNameByEntity(this.entityClass) + " set deleted = 0 where id = "
				+ IdTypeHandler.decode(id);
		try {
			return JDBCUtils.doUpdate(sql);
		} catch (SQLException e) {
			throw new BizException(StatusCode.DELETE_ENTITY_EXCEPTION);
		}
	}

	@Override
	public Integer deleteByIdReal(String id) {
		String sql = "delete from " + getTableNameByEntity(this.entityClass) + " where id = " + IdTypeHandler.decode(id);
		try {
			return JDBCUtils.doUpdate(sql);
		} catch (SQLException e) {
			throw new BizException(StatusCode.DELETE_ENTITY_EXCEPTION);
		}
	}

	@Override
	public Integer editById(T t) {
		String sql = updateByIdMapperProvider(t);
		try {
			return JDBCUtils.doUpdate(sql);
		} catch (SQLException e) {
			throw new BizException(StatusCode.UPDATE_ENTITY_EXCEPTION);
		}
	}

	@Override
	public T queryById(String id) {
		if(StringUtils.isBlank(id)){
			throw new BizException(StatusCode.ID_BLANK);
		}
		String tableName = getTableNameByEntity(this.entityClass);
		String sql = "select * from "+tableName + " where id = "+IdTypeHandler.decode(id);
		try {
			 List<Map<String,Object>> list = JDBCUtils.doQuery(sql);
			 List<T> listObj = getObjects(list);
			 if(listObj.size() > 0){
				 return listObj.get(0);
			 }else {
				 throw new BizException(StatusCode.BASE_NOT_FOUND);
			 }
		} catch (SQLException e) {
			throw new BizException(StatusCode.QUERY_BY_ID_EXCEPTION);
		} 
	}
	// =====================================

	protected List<T> getObjects(List<Map<String,Object>> list){
		List<T> listObj = new ArrayList<T>();
		try {
			if(list != null && list.size() > 0){
				//ͨ����ǰ��ʵ���ȡ�����б�
				Field [] fields = this.entityClass.getDeclaredFields();
				// ��������
				for(Map<String,Object> map : list){
					//���ݵ�ǰʵ����ֽ��봴��һ��ʵ�����
					Object obj = this.entityClass.newInstance();
					//���������б�
					for(Field field : fields){
						//���������ǿ��Է��ʵ�
						field.setAccessible(true);
						if("serialVersionUID".equals(field.getName())){
							continue;
						}
						//��ȡ�����ϵ�Fieldע��nameֵ�����name����ֵ��ŵ������ݿ��ֶ���
						com.edu.annotation.Field fieldName = field.getAnnotation(com.edu.annotation.Field.class);
						//��ȡʵ�������������
						String type = field.getGenericType().toString();
						String typeReal = "";
						if (type.contains("<") && type.contains(">")) {
							typeReal = type.substring(type.lastIndexOf(".", type.indexOf("<")) + 1,
									type.indexOf("<"));
						} else {
							typeReal = type.substring(type.lastIndexOf(".") + 1, type.length());
						}
						Object value = map.get(fieldName.name());
						if(value != null){
							//����nameֵ�����ݿ��ֶ�������ȡ���ݿ⵱ǰ����¼��ָ���ֶ�ֵ�����ŵ�ָ����obj������
							if ("date".equalsIgnoreCase(typeReal)) {
								SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								sdf1.setLenient(false);
								try {
									field.set(obj, sdf1.parse(value.toString()));
								} catch (ParseException e) {
									SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
									sdf2.setLenient(false);
									try {
										field.set(obj,sdf2.parse(value.toString()));
									} catch (ParseException e1) {
										SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss");
										sdf3.setLenient(false);
										try {
											field.set(obj, sdf3.parse(value.toString()));
										} catch (ParseException e2) {
											SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
											sdf4.setLenient(false);
											try {
												field.set(obj, sdf4.parse(value.toString()));
											} catch (ParseException e3) {
												SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
												sdf5.setLenient(false);
												try {
													field.set(obj, sdf5.parse(value.toString()));
												} catch (ParseException e4) {
													e4.printStackTrace();
												}
											}
										}
									}
								}
							}else if("boolean".equalsIgnoreCase(typeReal)){
								if("1".equals(value)){
									field.set(obj, true);
								}else if("0".equals(value)){
									field.set(obj, false);
								}
							}else if("integer".equalsIgnoreCase(typeReal)){
								if(!StringUtils.isBlank(value.toString())){
									field.set(obj, Integer.parseInt(value.toString()));
								}
							}else if(field.getName().toLowerCase().contains("id")){
								field.set(obj, IdTypeHandler.encode(Long.parseLong(value.toString())));
							}else {
								field.set(obj,value.toString());
							}
						}
					}
					listObj.add((T)obj);
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return listObj;
	}
	/**
	 * ��Ӷ����sql������
	 * 
	 * @param obj
	 *            ����һ��ʵ�����
	 * @return ����sql���
	 */
	private String insertMapperProvider(Object obj) {
		// �õ������
		Class<Object> stuCla = (Class<Object>) obj.getClass();
		// �õ����Լ���
		Field[] fields = stuCla.getDeclaredFields();
		StringBuilder sb = new StringBuilder("");
		sb.append("insert into ");
		// ��ȡ����
		Table table = stuCla.getAnnotation(Table.class);
		if (table == null) {
			throw new RuntimeException("ʵ����" + stuCla.getSimpleName() + "��ȱ��tableע��");
		}
		sb.append(table.name());
		try {
			// ����ֶ���
			List<String> params = new ArrayList<String>();
			// ���valueֵ
			List<String> values = new ArrayList<String>();
			// ��������
			for (Field f : fields) {
				// ���������ǿ��Է��ʵ�(˽�е�Ҳ����)
				f.setAccessible(true);
				// �õ������Ե�ֵ
				Object val = f.get(obj);
				// �������ֵ��Ϊ��ִ�еĲ���
				if (val != null && !"".equals(val.toString()) && !"serialVersionUID".equals(f.getName())) {
					// ���ֶ����͸��ֶε�ֵ�ŵ�������
					com.edu.annotation.Field fieldName = f.getAnnotation(com.edu.annotation.Field.class);
					if (fieldName == null) {
						throw new RuntimeException("���" + stuCla.getSimpleName() + "ʱ");
					}
					params.add(fieldName.name());
					String type = f.getGenericType().toString();
					// �����ӵ�ʱ�����ͣ���ת��һ��ʱ���ʽ
					if ("date".equalsIgnoreCase(type.substring(type.lastIndexOf(".") + 1, type.length()))) {
						Date date = (Date) val;
						values.add("'" + sdf.format(date) + "'");
					} else if ("boolean".equalsIgnoreCase(type.substring(type.lastIndexOf(".") + 1, type.length()))) {
						if ((boolean) val) {
							values.add(1 + "");
						} else {
							values.add(0 + "");
						}
					} else if (f.getName().toLowerCase().contains("id")) {
						values.add("'" + IdTypeHandler.decode(val.toString()) + "'");
					} else {
						try {
							values.add("'" + val.toString() + "'");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			// ƴ���ֶ���sql
			if (params.size() > 0) {
				sb.append("(");
				for (String param : params) {
					sb.append(param);
					sb.append(",");
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.append(")");
			} else {
				throw new BizException(StatusCode.INSERT_ENTITY_FIELD_NULL_EXCEPTION);
			}
			// ƴ��valueֵsql
			if (params.size() > 0) {
				sb.append(" value(");
				for (String value : values) {
					sb.append(value);
					sb.append(",");
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.append(");");
			} else {
				throw new BizException(StatusCode.INSERT_ENTITY_VALUE_NULL_EXCEPTION);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	/**
	 * ����id�޸����ݶ����sql��乹����
	 * @param obj ����һ�����ݶ���
	 * @return ����sql���
	 */
	private String updateByIdMapperProvider(Object obj) {
		// �õ������
		Class<Object> stuCla = (Class<Object>) obj.getClass();
		// �õ����Լ���
		Field[] fs = stuCla.getDeclaredFields();
		StringBuilder sb = new StringBuilder("");
		sb.append("update ");
		// ��ȡ����
		Table table = stuCla.getAnnotation(Table.class);
		String tableName = table.name();
		if (tableName == null | "".equals(tableName)) {
			throw new RuntimeException("ʵ����" + stuCla.getSimpleName() + "��ȱ��tableע��");
		}
		sb.append(tableName);
		sb.append(" set ");
		// ��������
		try {
			for (Field f : fs) {
				// ���������ǿ��Է��ʵ�(˽�е�Ҳ����)
				f.setAccessible(true);
				// �õ������Ե�ֵ
				Object val = f.get(obj);
				// �������ֵ��Ϊ��ִ�еĲ���
				if (val != null && !"".equals(val.toString()) && !"serialVersionUID".equals(f.getName())) {
					String type = f.getGenericType().toString();
					com.edu.annotation.Field fieldName = f.getAnnotation(com.edu.annotation.Field.class);
					if (fieldName == null) {
						throw new RuntimeException("ִ���޸Ĳ���ʱ��Ҫ���޸ĵ��ֶ�ȱ�����ݿ��ֶ�ע��");
					}
					String name = fieldName.name();
					if (name == null | "".equals(name)) {
						throw new RuntimeException("ִ���޸Ĳ���ʱ��Ҫ���޸ĵ��ֶε��ֶ�ע��name����ֵΪ��");
					}
					// �����ӵ�ʱ�����ͣ���ת��һ��ʱ���ʽ
					if ("date".equalsIgnoreCase(type.substring(type.lastIndexOf(".") + 1, type.length()))) {
						Date date = (Date) val;
						sb.append(name + "='" + sdf.format(date) + "'");
					} else if ("boolean".equalsIgnoreCase(type.substring(type.lastIndexOf(".") + 1, type.length()))) {
						if ((boolean) val) {
							sb.append(name + "=1");
						} else {
							sb.append(name + "=0");
						}
					} else if (f.getName().toLowerCase().contains("id")) {
						sb.append(name + "='" + IdTypeHandler.decode(val.toString()) + "'");
					} else {
						sb.append(name + "='" + val.toString() + "'");
					}
					sb.append(",");
				}
			}
			sb.deleteCharAt(sb.length() - 1);
			try {
				Field fieldId = stuCla.getDeclaredField("id");
				fieldId.setAccessible(true);
				sb.append(" where id = '"+IdTypeHandler.decode(fieldId.get(obj).toString())+"'");
			} catch (Exception e) {
				throw new BizException(StatusCode.UPDATE_ENTITY_ID_BLANK);
			}
			return sb.toString();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return "select -1";
	}

	// =======================================
	protected String getTableNameByEntity(Class<T> clazz) {
		Table table = clazz.getAnnotation(Table.class);
		if (table == null || "".equals(table.name().trim())) {
			throw new BizException(StatusCode.TABLE_ANNOTATION_NOT_FOUND);
		}
		return table.name();
	}
}
