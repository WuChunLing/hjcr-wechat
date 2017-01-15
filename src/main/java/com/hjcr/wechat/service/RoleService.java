package com.hjcr.wechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.entity.Role;
import com.hjcr.wechat.impl.PrivilegeImpl;
import com.hjcr.wechat.impl.RoleImpl;

@Service(value = "roleService")
public class RoleService {

	@Autowired
	private RoleImpl roleImpl;
	
	@Autowired
	private PrivilegeImpl privilegeImpl;
	
//	@Autowired
//	private SystemUserImpl systemUserImpl;

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
		List<Role> list = roleImpl.findAll();
		for(Role role : list) {
			List<String> privilegeList = privilegeImpl.getPrivilegeNameByRoleId(role.getId());
			if (!(privilegeList.size() == 0)) {
				role.setPrivileges(privilegeList);
			}
		}
		return list;
	}

	/**
	 * 根据用户id获取用户角色
	 * @author Kellan
	 * @param userId
	 * @return
	 */
	public Role getRoleByUser(Integer userId) {
		return roleImpl.getRoleByUser(userId);
	}

	/**
	 * 更新系统用户角色名称.
	 * @author Kellan
	 * @param rolename
	 * @return
	 */
	public Role updateRoleName(Role role) {
		Role old_role = roleImpl.findOne(role.getId());
		old_role.setRolename(role.getRolename());
		return roleImpl.saveAndFlush(old_role);
	}
	
	
	
	
}
