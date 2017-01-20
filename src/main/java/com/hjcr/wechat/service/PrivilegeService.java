package com.hjcr.wechat.service;

import java.util.List;

import com.hjcr.wechat.entity.Privilege;

public interface PrivilegeService {

	/**
	 * 获取所有权限列表.
	 * @author Kellan
	 * @return
	 */
	public List<Privilege> getAllPrivilege();

	/**
	 * 获取某角色所拥有的权限列表.
	 * @author Kellan
	 * @param roleId
	 * @return
	 */
	public List<Privilege> getPrivilegeByRoleId(Integer roleId);
	
}
