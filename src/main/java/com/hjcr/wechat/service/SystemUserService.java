package com.hjcr.wechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.entity.SystemUser;
import com.hjcr.wechat.impl.SystemUserImpl;

@Service(value = "systemUserService")
public class SystemUserService {

	@Autowired
	private SystemUserImpl systemUserImpl;

	public SystemUser findOneByName(String username) {
		return systemUserImpl.findOneByName(username);
	}

	public List<String> findPrivilegeCode(int id) {
		return systemUserImpl.findPrivilegeCode(id);
	}

	public String findRoleName(int id) {
		return systemUserImpl.findRoleName(id);
	}

	
}
