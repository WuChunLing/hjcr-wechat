package com.hjcr.wechat.service;

import com.hjcr.wechat.entity.Allocation;

public interface AllocationService {

	/*
	 * 所有的分配比例信息
	 */
	public Allocation getAllocation();

	/*
	 * 更新分配比例信息
	 */
	public void updataAllocation(Allocation allocation);


	
}
