package com.hjcr.wechat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hjcr.wechat.entity.Bill;

public interface BillService {

	/*
	 * 增加用户订单分润信息
	 */
	public void addBill(Bill bill);

	/*
	 * 删除用户订单分润信息
	 */
	public void deleteBill(Integer billId);

	/*
	 * 获取用户订单分润信息
	 */
	public Page<Bill> getAllBill(Pageable pageable);
	
	
	/*
	 * 根据订单id获取订单信息
	 */
	public Bill getBill(Integer BillId);
	
}
