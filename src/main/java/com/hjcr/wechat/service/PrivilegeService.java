package com.hjcr.wechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.entity.Privilege;
import com.hjcr.wechat.impl.PrivilegeImpl;

@Service(value = "privilegeService")
public class PrivilegeService {

	@Autowired
	private PrivilegeImpl privilegeImpl;

	public List<Privilege> getAllPrivilege() {
		return privilegeImpl.findAll();
	}

	public List<Privilege> getPrivilegeByRoleId(Integer roleId) {
		return privilegeImpl.getPrivilegeByRoleId(roleId);
	}
	
	
	
	
}
