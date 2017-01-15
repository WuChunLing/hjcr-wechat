package com.hjcr.wechat.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hjcr.wechat.entity.RolePrivilege;

public interface RolePrivilegeImpl extends JpaRepository<RolePrivilege, Integer>{

	/*
	 * 查询某角色是否拥有某权限
	 */
	@Query("SELECT rp FROM RolePrivilege rp WHERE rp.roleId = ?1 and rp.privilegeId = ?2")
	RolePrivilege findOneByRoleAndPrivilege(Integer roleId,Integer privilegeId);

	
	
}
