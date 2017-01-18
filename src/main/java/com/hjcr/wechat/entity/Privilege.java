package com.hjcr.wechat.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Cacheable
@Table(name = "privilege")
@Entity
public class Privilege implements Serializable {
	private int id;

	private String privilegeName; //权限名

	
	// 权限编号. user:*,user:delete,user:create,user:update
	private String privilegeCode;

	private String matchUrl;  //权限url
	
	private String note;  //权限描述

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrivilegeCode() {
		return privilegeCode;
	}

	public void setPrivilegeCode(String privilegeCode) {
		this.privilegeCode = privilegeCode;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public String getMatchUrl() {
		return matchUrl;
	}

	public void setMatchUrl(String matchUrl) {
		this.matchUrl = matchUrl;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Privilege [id=" + id + ", note=" + note + ", privilegeName="
				+ privilegeName + ", privilegeCode=" + privilegeCode
				+ ", matchUrl=" + matchUrl + "]";
	}

}