package com.hjcr.wechat.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hjcr.wechat.entity.Privilege;

public interface PrivilegeImpl extends JpaRepository<Privilege, Integer>{

	/**
	 * 获取角色拥有的权限
	 * @author Kellan
	 * @param roleId
	 * @return
	 */
	@Query("SELECT p FROM Privilege p, RolePrivilege rp WHERE rp.roleId = ?1 and p.id = rp.privilegeId")
	List<Privilege> getPrivilegeByRoleId(Integer roleId);

	
	
}
