package com.hjcr.wechat.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hjcr.wechat.entity.SystemUser;

public interface SystemUserImpl extends JpaRepository<SystemUser, Integer>{

	/*
	 * 根据系统用户名（账号）查询系统用户.
	 */
	@Query("SELECT u FROM SystemUser u WHERE u.username = ?1 ")
	SystemUser findOneByName(String username);

	@Query("select p.privilegeCode from  Privilege p, RolePrivilege rp,SystemUser u where u.id = ?1 and rp.roleId = u.roleId and p.id = rp.privilegeId")
	List<String> findPrivilegeCode(Integer userId);

	@Query("select r.rolename from Role r ,SystemUser u where u.id = ?1 and u.roleId = r.id")
	String findRoleName(Integer userId);

	
}
