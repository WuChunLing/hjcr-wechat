package com.hjcr.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.entity.Allocation;
import com.hjcr.wechat.impl.AllocationImpl;

@Service
public class AllocationService {

	@Autowired
	private AllocationImpl allocationImpl;

	/*
	 * 所有的分配比例信息
	 */
	public Allocation getAllocation() {
		return allocationImpl.findOne(1);
	}

	/*
	 * 更新分配比例信息
	 */
	public void updataAllocation(Allocation allocation) {
		allocationImpl.saveAndFlush(allocation);
	}

}
