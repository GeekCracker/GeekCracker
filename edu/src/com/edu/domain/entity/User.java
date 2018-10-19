package com.edu.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.edu.annotation.Field;
import com.edu.annotation.Table;

@Table(name="t_user")
public class User implements Serializable{
	private static final long serialVersionUID = 4356540346223338217L;
	/**����id*/
	@Field(name="id")
	private String id;
	/**�û���*/
	@Field(name="user_name")
	private String userName;
	/**�û�����*/
	@Field(name="user_password")
	private String userPassword;
	/**�û���ϵ��ʽ*/
	@Field(name="user_phone")
	private String userPhone;
	/**�û�����*/
	@Field(name="user_mail")
	private String userMail;
	/**��ɫid*/
	@Field(name="role_id")
	private String roleId;
	/**����ʱ��*/
	@Field(name="created_time")
	private Date createdTime;
	/**�޸�ʱ��*/
	@Field(name="updated_time")
	private Date updatedTime;
	/**�Ƿ�ɾ����true��ʾ������ʾ��false��ʾɾ��*/
	@Field(name="deleted")
	private Boolean deleted;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
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
