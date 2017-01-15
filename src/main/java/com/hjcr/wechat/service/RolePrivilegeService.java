package com.hjcr.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.entity.RolePrivilege;
import com.hjcr.wechat.impl.RolePrivilegeImpl;

@Service(value = "rolePrivilegeService")
public class RolePrivilegeService {

	@Autowired
	private RolePrivilegeImpl rolePrivilegeImpl;

	public Boolean updateRolePrivilege(RolePrivilege rp) {
		RolePrivilege db_rp = rolePrivilegeImpl.findOneByRoleAndPrivilege(rp.getRoleId(),rp.getPrivilegeId());
		if (db_rp == null) {//如何数据库没有，则添加
			rolePrivilegeImpl.save(rp);
			return true;
		} else if (db_rp.getId() != null){ //如何数据库存在，则删除
			rolePrivilegeImpl.delete(db_rp.getId());
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
}
