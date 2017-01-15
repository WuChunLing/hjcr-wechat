package com.hjcr.wechat.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Cacheable
@Table(name = "role")
@Entity
public class Role implements Serializable {

	private Integer id;

	private String note;

	private String rolename;
	
	private Set<Object> privileges = new HashSet<Object>();

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
	@Override
	public String toString() {
		return "Role [id=" + id + ", note=" + note + ", rolename=" + rolename
				+ "]";
	}

	@Transient 
	public Set<Object> getPrivileges() {
		return privileges;
	}

//	public void setPrivileges(Set<Object> privileges) {
//		this.privileges = privileges;
//	}
	
	public void setPrivileges(List<String> privileges) {
		this.privileges.addAll(privileges);
	}

}