package com.xumengba.domain;

import java.io.Serializable;

/**
 * 实体类基类
 * */
public class BaseEntity implements Serializable{
	private static final long serialVersionUID = -5627862510903201983L;
	/**主键id*/
	protected String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
} 

