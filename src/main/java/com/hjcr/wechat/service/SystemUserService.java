package com.hjcr.wechat.service;

import java.util.List;

import com.hjcr.wechat.entity.SystemUser;

public interface SystemUserService {

	/**
	 * 根据系统用户名获取用户信息
	 */
	public SystemUser findOneByName(String username);

	/**
	 * 根据系统用户Id获取用户权限
	 * @author 宋
	 */
	public List<String> findPrivilegeCode(int id);

	/**
	 * 根据系统用户id获取用户角色.
	 * @author 宋
	 */
	public String findRoleName(int id);

	/**
	 * 添加系统用户.
	 * @author 宋
	 */
	public SystemUser addUser(SystemUser user);

	/**
	 * 删除用户
	 * @author Kellan
	 * @param user
	 */
	public void delete(SystemUser user);

	/**
	 * 修改用户密码
	 * @author Kellan
	 * @param username
	 * @param oldPassword
	 * @param newPassword
	 */
	public void updatePassword(String username, String oldPassword,
			String newPassword);

	/**
	 * 修改用户角色
	 * @author Kellan
	 * @param user
	 */
	public void updateUserRole(SystemUser user);

	/**
	 * 获取所有系统用户
	 * @author Kellan
	 * @return
	 */
	public List<SystemUser> getAllSystemUser();

	
}
