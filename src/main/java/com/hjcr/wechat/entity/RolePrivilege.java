package com.hjcr.wechat.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Cacheable
@Table(name="roleprivilege")
@Entity
public class RolePrivilege implements Serializable {
	
    private int id;

    private int roleId;

    private int privilegeId;

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)
	@Id
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}

	@Override
	public String toString() {
		return "RolePrivilege [roleId=" + roleId + ", privilegeId=" + privilegeId + "]";
	}
}