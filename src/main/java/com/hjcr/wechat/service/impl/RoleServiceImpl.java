package com.hjcr.wechat.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.dao.PrivilegeDao;
import com.hjcr.wechat.dao.RoleDao;
import com.hjcr.wechat.dao.RolePrivilegeDao;
import com.hjcr.wechat.dao.SystemUserDao;
import com.hjcr.wechat.entity.Role;
import com.hjcr.wechat.entity.RolePrivilege;
import com.hjcr.wechat.entity.SystemUser;
import com.hjcr.wechat.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PrivilegeDao privilegeDao;
	
	@Autowired
	private RolePrivilegeDao rolePrivilegeDao;
	
	@Autowired
	private SystemUserDao systemUserDao;

	/**
	 * 添加角色信息
	 * 
	 * @author 宋
	 * @param role
	 * @param list
	 * @return
	 */
	public Role addRole(Role role, Integer[] list) {
		List<Integer> lis = new ArrayList<Integer>(Arrays.asList(list));
		if (lis.isEmpty()) {
			return roleDao.save(role);
		} else {
			role = roleDao.save(role);
			RolePrivilege rp = null;
			for(Integer id : list){
				rp = new RolePrivilege();
				rp.setRoleId(role.getId());
				rp.setPrivilegeId(id);
				rolePrivilegeDao.save(rp);
			}
			return role;
		}
	}

	/**
	 * 获取所有角色
	 * 
	 * @author Kellan
	 * @return
	 */
	public List<Role> getAllRole() {
		List<Role> list = roleDao.findAll();
		for (Role role : list) {
			List<String> privilegeList = privilegeDao
					.getPrivilegeNameByRoleId(role.getId());
			if (!(privilegeList.size() == 0)) {
				role.setPrivileges(privilegeList);
			}
		}
		return list;
	}

	/**
	 * 根据用户id获取用户角色
	 * 
	 * @author Kellan
	 * @param userId
	 * @return
	 */
	public Role getRoleByUser(Integer userId) {
		return roleDao.getRoleByUser(userId);
	}

	/**
	 * 更新系统用户角色名称.
	 * 
	 * @author Kellan
	 * @param rolename
	 * @return
	 */
	public Role updateRoleName(Role role) {
		Role old_role = roleDao.findOne(role.getId());
		old_role.setRolename(role.getRolename());
		return roleDao.saveAndFlush(old_role);
	}

	public void delete(Role role) {
		List<SystemUser> userList = systemUserDao.fingUserByRole(role.getId());
		if (userList.size() != 0) {
			throw new SecurityException("某用户拥有该角色，不能删除；请先修改用户角色");
		}
		roleDao.delete(role.getId());
	}

}
