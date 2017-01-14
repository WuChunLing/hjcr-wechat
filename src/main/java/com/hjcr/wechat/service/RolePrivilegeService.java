package com.hjcr.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.entity.RolePrivilege;
import com.hjcr.wechat.impl.RolePrivilegeImpl;

@Service(value = "rolePrivilegeService")
public class RolePrivilegeService {

	@Autowired
	private RolePrivilegeImpl rolePrivilegeImpl;

	public RolePrivilege addPrivilegeToRole(RolePrivilege rp) {
		return rolePrivilegeImpl.save(rp);
	}
	
	
	
	
}
