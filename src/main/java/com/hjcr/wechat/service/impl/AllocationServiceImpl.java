package com.hjcr.wechat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.dao.AllocationDao;
import com.hjcr.wechat.entity.Allocation;
import com.hjcr.wechat.service.AllocationService;

@Service("allocationService")
public class AllocationServiceImpl implements AllocationService{

	@Autowired
	private AllocationDao allocationDao;

	/*
	 * 所有的分配比例信息
	 */
	public Allocation getAllocation() {
		return allocationDao.findOne(1);
	}

	/*
	 * 更新分配比例信息
	 */
	public void updataAllocation(Allocation allocation) {
		allocationDao.saveAndFlush(allocation);
	}


	
}
