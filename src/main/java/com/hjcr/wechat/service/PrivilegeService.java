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

	/**
	 * 获取所有权限列表.
	 * @author Kellan
	 * @return
	 */
	public List<Privilege> getAllPrivilege() {
		return privilegeImpl.findAll();
	}

	/**
	 * 获取某角色所拥有的权限列表.
	 * @author Kellan
	 * @param roleId
	 * @return
	 */
	public List<Privilege> getPrivilegeByRoleId(Integer roleId) {
		return privilegeImpl.getPrivilegeByRoleId(roleId);
	}
	
	
	
	
}
