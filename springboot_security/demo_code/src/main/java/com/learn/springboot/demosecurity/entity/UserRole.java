package com.learn.springboot.demosecurity.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "real_user_roles")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	private int userId;

	@Id
	@Column(name = "role_id")
	private int roleId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
