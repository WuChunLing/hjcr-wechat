package com.hjcr.wechat.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Cacheable
@Table(name = "allocation")
@Entity
public class Allocation {

	// 分配的比例
	private int allocationId;
	private double orderMoneyFirst; // 一級代理
	private double orderMoneySecond;// 二級代理

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	public int getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(int allocationId) {
		this.allocationId = allocationId;
	}

	public double getOrderMoneyFirst() {
		return orderMoneyFirst;
	}

	public void setOrderMoneyFirst(double orderMoneyFirst) {
		this.orderMoneyFirst = orderMoneyFirst;
	}

	public double getOrderMoneySecond() {
		return orderMoneySecond;
	}

	public void setOrderMoneySecond(double orderMoneySecond) {
		this.orderMoneySecond = orderMoneySecond;
	}

}
