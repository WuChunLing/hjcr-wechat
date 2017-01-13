package com.hjcr.wechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.entity.SystemUser;
import com.hjcr.wechat.impl.SystemUserImpl;
import com.hjcr.wechat.tools.MD5Util;

@Service(value = "systemUserService")
public class SystemUserService {

	@Autowired
	private SystemUserImpl systemUserImpl;

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
		String MD5Password = MD5Util.md5(user.getPassword());
		user.setPassword(MD5Password);
		return systemUserImpl.saveAndFlush(user);
	}

	
}
