package com.hjcr.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.dao.PrivilegeDao;
import com.hjcr.wechat.entity.Privilege;
import com.hjcr.wechat.service.PrivilegeService;

@Service("privilegeService")
public class PrivilegeServiceImpl implements PrivilegeService{

	@Autowired
	private PrivilegeDao privilegeDao;

	/**
	 * 获取所有权限列表.
	 * @author Kellan
	 * @return
	 */
	public List<Privilege> getAllPrivilege() {
		return privilegeDao.findAll();
	}

	/**
	 * 获取某角色所拥有的权限列表.
	 * @author Kellan
	 * @param roleId
	 * @return
	 */
	public List<Privilege> getPrivilegeByRoleId(Integer roleId) {
		return privilegeDao.getPrivilegeByRoleId(roleId);
	}
	
	
	
	
}
