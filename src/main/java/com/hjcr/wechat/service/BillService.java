package com.hjcr.wechat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hjcr.wechat.entity.Bill;

public interface BillService {

	/*
	 * 增加用户订单分润信息
	 */
	public void addBill(int billId,Float billMoney,String unionId,int OrderMoneyId);

	/*
	 * 删除用户订单分润信息
	 */
	public void deleteBill(Integer billId);

	/*
	 * 获取所有订单分润信息
	 */
	public Page<Bill> getAllBill(Pageable pageable, String startDate, String endDate);
	
	
	/*
	 * 获取用户订单分润信息
	 */
	public Page<Bill> getBillByUser(Pageable pageable, String startDate, String endDate,Integer UserId);
	
	
	
	/*
	 * 根据订单id获取订单信息
	 */
	public Bill getBill(Integer BillId);
	

	/*
	 * 获取订单总金额
	 */
	public Double getSumConsume(String startDate, String endDate);
	
	
	/*
	 * 获取分润总金额
	 */
	public Double getSumFeeSplitting(String startDate, String endDate);
}
