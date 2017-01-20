package com.hjcr.wechat.service;

import java.util.List;

import com.hjcr.wechat.entity.Role;

public interface RoleService {


	/**
	 * 添加角色信息
	 * 
	 * @author 宋
	 * @param role
	 * @param list
	 * @return
	 */
	public Role addRole(Role role, Integer[] list);

	/**
	 * 获取所有角色
	 * 
	 * @author Kellan
	 * @return
	 */
	public List<Role> getAllRole();

	/**
	 * 根据用户id获取用户角色
	 * 
	 * @author Kellan
	 * @param userId
	 * @return
	 */
	public Role getRoleByUser(Integer userId);

	/**
	 * 更新系统用户角色名称.
	 * 
	 * @author Kellan
	 * @param rolename
	 * @return
	 */
	public Role updateRoleName(Role role);

	/*
	 * 删除角色
	 */
	public void delete(Role role);

}
