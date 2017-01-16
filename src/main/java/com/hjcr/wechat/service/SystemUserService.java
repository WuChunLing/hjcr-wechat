package com.hjcr.wechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.entity.Role;
import com.hjcr.wechat.entity.SystemUser;
import com.hjcr.wechat.impl.RoleImpl;
import com.hjcr.wechat.impl.SystemUserImpl;
import com.hjcr.wechat.tools.MD5Util;

@Service(value = "systemUserService")
public class SystemUserService {

	@Autowired
	private SystemUserImpl systemUserImpl;
	
	@Autowired
	private RoleImpl roleImpl;

	/**
	 * 根据系统用户名获取用户信息
	 */
	public SystemUser findOneByName(String username) {
		return systemUserImpl.findOneByName(username);
	}

	/**
	 * 根据系统用户Id获取用户权限
	 * @author 宋
	 */
	public List<String> findPrivilegeCode(int id) {
		return systemUserImpl.findPrivilegeCode(id);
	}

	/**
	 * 根据系统用户id获取用户角色.
	 * @author 宋
	 */
	public String findRoleName(int id) {
		return systemUserImpl.findRoleName(id);
	}

	/**
	 * 添加系统用户.
	 * @author 宋
	 */
	public SystemUser addUser(SystemUser user) {
		SystemUser tem_user = systemUserImpl.findOneByName(user.getUsername());
		if (tem_user != null) {
			throw new SecurityException("该用户已存在");
		}
		String MD5Password = MD5Util.md5(user.getPassword()+user.getUsername() );
		user.setPassword(MD5Password);
		return systemUserImpl.saveAndFlush(user);
	}

	/**
	 * 删除用户
	 * @author Kellan
	 * @param user
	 */
	public void delete(SystemUser user) {
		SystemUser systemUser = systemUserImpl.findOne(user.getId());
		if (systemUser == null) {
			throw new SecurityException("该用户不存在");
		}
		systemUserImpl.delete(user);
		systemUser = systemUserImpl.findOne(user.getId());
		if (systemUser != null) {
			throw new SecurityException("删除失败");
		}
	}

	/**
	 * 修改用户密码
	 * @author Kellan
	 * @param username
	 * @param oldPassword
	 * @param newPassword
	 */
	public void updatePassword(String username, String oldPassword,
			String newPassword) {
		String md5OldPassword = MD5Util.md5(oldPassword+username);
		SystemUser dbUser = systemUserImpl.findOneByName(username);
		if (!dbUser.getPassword().equals(md5OldPassword)) {
			throw new SecurityException("旧密码错误");
		}
		String md5NewPassword = MD5Util.md5(newPassword+username);
		dbUser.setPassword(md5NewPassword);
		systemUserImpl.saveAndFlush(dbUser);
	}

	/**
	 * 修改用户角色
	 * @author Kellan
	 * @param user
	 */
	public void updateUserRole(SystemUser user) {
		SystemUser dbUser = systemUserImpl.findOne(user.getId());
		dbUser.setRoleId(user.getRoleId());
		systemUserImpl.saveAndFlush(dbUser);
	}

	/**
	 * 获取所有系统用户
	 * @author Kellan
	 * @return
	 */
	public List<SystemUser> getAllSystemUser() {
		List<Role> roleList = roleImpl.findAll();
		List<SystemUser> userList = systemUserImpl.findAll();
		for(SystemUser user : userList){
			for(Role role : roleList){
				if (user.getRoleId().equals(role.getId())) {
					user.setRolename(role.getRolename());
				}
			}
		}
		return userList;
	}

	
}
