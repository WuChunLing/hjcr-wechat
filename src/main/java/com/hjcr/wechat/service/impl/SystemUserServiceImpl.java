package com.hjcr.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.dao.RoleDao;
import com.hjcr.wechat.dao.SystemUserDao;
import com.hjcr.wechat.entity.Role;
import com.hjcr.wechat.entity.SystemUser;
import com.hjcr.wechat.service.SystemUserService;
import com.hjcr.wechat.tools.MD5Util;

@Service("systemUserService")
public class SystemUserServiceImpl implements SystemUserService{

	@Autowired
	private SystemUserDao systemUserDao;
	
	@Autowired
	private RoleDao roleDao;

	/**
	 * 根据系统用户名获取用户信息
	 */
	public SystemUser findOneByName(String username) {
		return systemUserDao.findOneByName(username);
	}

	/**
	 * 根据系统用户Id获取用户权限
	 * @author 宋
	 */
	public List<String> findPrivilegeCode(int id) {
		return systemUserDao.findPrivilegeCode(id);
	}

	/**
	 * 根据系统用户id获取用户角色.
	 * @author 宋
	 */
	public String findRoleName(int id) {
		return systemUserDao.findRoleName(id);
	}

	/**
	 * 添加系统用户.
	 * @author 宋
	 */
	public SystemUser addUser(SystemUser user) {
		SystemUser tem_user = systemUserDao.findOneByName(user.getUsername());
		Role role = roleDao.findOne(user.getRoleId());
		if (role == null) {
			throw new SecurityException("不存在该角色");
		}
		if (tem_user != null) {
			throw new SecurityException("该用户已存在");
		}
		String MD5Password = MD5Util.md5(user.getPassword()+user.getUsername() );
		user.setPassword(MD5Password);
		return systemUserDao.saveAndFlush(user);
	}

	/**
	 * 删除用户
	 * @author Kellan
	 * @param user
	 */
	public void delete(SystemUser user) {
		SystemUser systemUser = systemUserDao.findOne(user.getId());
		if (systemUser == null) {
			throw new SecurityException("该用户不存在");
		}
		systemUserDao.delete(user);
		systemUser = systemUserDao.findOne(user.getId());
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
		SystemUser dbUser = systemUserDao.findOneByName(username);
		if (!dbUser.getPassword().equals(md5OldPassword)) {
			throw new SecurityException("旧密码错误");
		}
		String md5NewPassword = MD5Util.md5(newPassword+username);
		dbUser.setPassword(md5NewPassword);
		systemUserDao.saveAndFlush(dbUser);
	}

	/**
	 * 修改用户角色
	 * @author Kellan
	 * @param user
	 */
	public void updateUserRole(SystemUser user) {
		Role role = roleDao.findOne(user.getRoleId());
		if (role == null) {
			throw new SecurityException("不存在该角色");
		}
		SystemUser dbUser = systemUserDao.findOne(user.getId());
		if (dbUser == null) {
			throw new SecurityException("不存在该用户");
		}
		dbUser.setRoleId(user.getRoleId());
		systemUserDao.saveAndFlush(dbUser);
	}

	/**
	 * 获取所有系统用户
	 * @author Kellan
	 * @return
	 */
	public List<SystemUser> getAllSystemUser() {
		List<Role> roleList = roleDao.findAll();
		List<SystemUser> userList = systemUserDao.findAll();
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
