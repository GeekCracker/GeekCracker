package com.edu.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.edu.dao.RoleMapper;
import com.edu.domain.entity.Role;
import com.edu.domain.response.StatusCode;
import com.edu.exception.BizException;
import com.edu.utils.sql.JDBCUtils;

public class RoleMapperImpl extends BaseMapperImpl<Role> implements RoleMapper{

	@Override
	public Role queryByName(String roleName) {
		String tableName = getTableNameByEntity(this.entityClass);
		String sql = "select * from "+tableName + " where role_name = '"+roleName+"'";
		try {
			 List<Map<String,Object>> list = JDBCUtils.doQuery(sql);
			 List<Role> listObj = getObjects(list);
			 if(listObj.size() > 0){
				 return listObj.get(0);
			 }else {
				 throw new BizException(StatusCode.BASE_NOT_FOUND);
			 }
		} catch (SQLException e) {
			throw new BizException(StatusCode.QUERY_BY_ID_EXCEPTION);
		} 
	}
}
