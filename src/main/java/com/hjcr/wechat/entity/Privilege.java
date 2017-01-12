package com.hjcr.wechat.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Cacheable
@Table(name="privilege")
@Entity
public class Privilege implements Serializable {
	private int id;
	
    private String describe;

    private String privilegeName;
    
    /**
     * 权限编号. user:*,user:delete,user:create,user:update
     */
    private String privilegeCode;
    
    private String matchUrl;

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

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
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

	@Override
	public String toString() {
		return "Privilege [describe=" + describe + ", privilegeName="
				+ privilegeName + ", matchUrl=" + matchUrl + "]";
	}

}