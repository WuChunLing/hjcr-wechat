package com.hjcr.wechat.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

//后台管理系统用户
@JsonInclude(JsonInclude.Include.NON_NULL)
@Cacheable
@Table(name = "system_user")
@Entity
public class SystemUser implements Serializable {
	private Integer id; //主键
 
	private String username; //登录账号

	private String password;  //登录密码

	private Integer roleId;  //角色id
	
	private String rolename;  //角色名

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "SystemUser [id=" + id + ", username=" + username + ", password="
				+ password + ", roleId=" + roleId + "]";
	}

	@Transient 
	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}