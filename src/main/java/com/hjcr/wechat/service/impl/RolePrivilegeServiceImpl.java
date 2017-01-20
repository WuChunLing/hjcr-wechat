package com.hjcr.wechat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.dao.RolePrivilegeDao;
import com.hjcr.wechat.entity.RolePrivilege;
import com.hjcr.wechat.service.RolePrivilegeService;

@Service("rolePrivilegeService")
public class RolePrivilegeServiceImpl implements RolePrivilegeService{

	@Autowired
	private RolePrivilegeDao rolePrivilegeDao;

	public synchronized Boolean updateRolePrivilege(RolePrivilege rp) {
		RolePrivilege db_rp = rolePrivilegeDao.findOneByRoleAndPrivilege(rp.getRoleId(),rp.getPrivilegeId());
		if (db_rp == null) {//如何数据库没有，则添加
			rolePrivilegeDao.save(rp);
			return true;
		} else if (db_rp.getId() != null){ //如何数据库存在，则删除
			rolePrivilegeDao.delete(db_rp.getId());
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
}
