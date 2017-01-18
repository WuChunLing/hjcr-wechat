package com.hjcr.wechat.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Cacheable
@Table(name = "allocation")
@Entity
public class Allocation {

	// 分配的比例
	private int allocationId;
	private float orderMoneyFirst; // 一級代理
	private float orderMoneySecond;// 二級代理

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	public int getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(int allocationId) {
		this.allocationId = allocationId;
	}

	public float getOrderMoneyFirst() {
		return orderMoneyFirst;
	}

	public void setOrderMoneyFirst(float orderMoneyFirst) {
		this.orderMoneyFirst = orderMoneyFirst;
	}

	public float getOrderMoneySecond() {
		return orderMoneySecond;
	}

	public void setOrderMoneySecond(float orderMoneySecond) {
		this.orderMoneySecond = orderMoneySecond;
	}

}
