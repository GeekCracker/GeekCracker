package com.edu.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.edu.annotation.Field;
import com.edu.annotation.Table;


/**
 * 角色实体类
 */
@Table(name="t_role")
public class Role extends BaseEntity implements Serializable{
	private static final long serialVersionUID = -2515547719130415512L;
	/**主键id*/
	@Field(name="id")
	private String id;
	/**角色名称*/
	@Field(name="role_name")
	private String roleName;
	/**角色描述信息*/
	@Field(name="memo")
	private String memo;
	/**创建时间*/
	@Field(name="created_time")
	private Date createdTime;
	/**修改时间*/
	@Field(name="updated_time")
	private Date updatedTime;
	/**是否删除，true正常显示，false删除*/
	@Field(name="deleted")
	private Boolean deleted;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
