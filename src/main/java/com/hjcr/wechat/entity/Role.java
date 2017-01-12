package com.hjcr.wechat.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Cacheable
@Table(name="role")
@Entity
public class Role implements Serializable {
	
	 private int id;
	
    private String describe;

    private String rolename;
    
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)
	@Id
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Override
	public String toString() {
		return "Role [describe=" + describe + ", rolename=" + rolename + "]";
	}

}