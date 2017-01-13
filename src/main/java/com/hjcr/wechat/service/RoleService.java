package com.hjcr.wechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.entity.Role;
import com.hjcr.wechat.impl.RoleImpl;

@Service(value = "roleService")
public class RoleService {

	@Autowired
	private RoleImpl roleImpl;

	/**
	 * 添加角色信息
	 * @author 宋
	 * @param role
	 * @return
	 */
	public Role addRole(Role role) {
		return roleImpl.save(role);
	}

	/**
	 * 获取所有角色
	 * @author Kellan
	 * @return
	 */
	public List<Role> getAllRole() {
		return roleImpl.findAll();
	}
	
	
	
	
}
