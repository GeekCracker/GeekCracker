package com.edu.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.edu.annotation.Field;
import com.edu.annotation.Table;

@Table(name="t_user")
public class User implements Serializable{
	private static final long serialVersionUID = 4356540346223338217L;
	/**主键id*/
	@Field(name="id")
	private String id;
	/**用户名*/
	@Field(name="user_name")
	private String userName;
	/**用户密码*/
	@Field(name="user_password")
	private String userPassword;
	/**用户联系方式*/
	@Field(name="user_phone")
	private String userPhone;
	/**用户邮箱*/
	@Field(name="user_mail")
	private String userMail;
	/**角色id*/
	@Field(name="role_id")
	private String roleId;
	/**创建时间*/
	@Field(name="created_time")
	private Date createdTime;
	/**修改时间*/
	@Field(name="updated_time")
	private Date updatedTime;
	/**是否删除，true表示正常显示，false表示删除*/
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
