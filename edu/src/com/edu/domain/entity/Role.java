package com.edu.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.edu.annotation.Field;
import com.edu.annotation.Table;


/**
 * ��ɫʵ����
 */
@Table(name="t_role")
public class Role extends BaseEntity implements Serializable{
	private static final long serialVersionUID = -2515547719130415512L;
	/**����id*/
	@Field(name="id")
	private String id;
	/**��ɫ����*/
	@Field(name="role_name")
	private String roleName;
	/**��ɫ������Ϣ*/
	@Field(name="memo")
	private String memo;
	/**����ʱ��*/
	@Field(name="created_time")
	private Date createdTime;
	/**�޸�ʱ��*/
	@Field(name="updated_time")
	private Date updatedTime;
	/**�Ƿ�ɾ����true������ʾ��falseɾ��*/
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
