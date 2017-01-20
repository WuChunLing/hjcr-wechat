package com.hjcr.wechat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hjcr.wechat.entity.Role;

public interface RoleDao extends JpaRepository<Role, Integer>{

	/**
	 * 根据用户Id获取用具角色
	 * @author Kellan
	 * @param userId
	 * @return
	 */
	@Query("SELECT r FROM Role r, SystemUser u WHERE u.id = ?1 and r.id = u.roleId")
	Role getRoleByUser(Integer userId);

	
	
}
