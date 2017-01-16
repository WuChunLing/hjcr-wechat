package com.hjcr.wechat.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.entity.Role;
import com.hjcr.wechat.entity.RolePrivilege;
import com.hjcr.wechat.impl.PrivilegeImpl;
import com.hjcr.wechat.impl.RoleImpl;
import com.hjcr.wechat.impl.RolePrivilegeImpl;

@Service(value = "roleService")
public class RoleService {

	@Autowired
	private RoleImpl roleImpl;

	@Autowired
	private PrivilegeImpl privilegeImpl;
	
	@Autowired
	private RolePrivilegeImpl rolePrivilegeImpl;

	// @Autowired
	// private SystemUserImpl systemUserImpl;

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
			return roleImpl.save(role);
		} else {
			role = roleImpl.save(role);
			RolePrivilege rp = null;
			for(Integer id : list){
				rp = new RolePrivilege();
				rp.setRoleId(role.getId());
				rp.setPrivilegeId(id);
				rolePrivilegeImpl.save(rp);
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
		List<Role> list = roleImpl.findAll();
		for (Role role : list) {
			List<String> privilegeList = privilegeImpl
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
		return roleImpl.getRoleByUser(userId);
	}

	/**
	 * 更新系统用户角色名称.
	 * 
	 * @author Kellan
	 * @param rolename
	 * @return
	 */
	public Role updateRoleName(Role role) {
		Role old_role = roleImpl.findOne(role.getId());
		old_role.setRolename(role.getRolename());
		return roleImpl.saveAndFlush(old_role);
	}

	public void delete(Role role) {
		roleImpl.delete(role.getId());
	}

}
