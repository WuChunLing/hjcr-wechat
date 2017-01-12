package com.hjcr.wechat.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Cacheable
@Table(name="allocation")
@Entity
public class Allocation {

//分配的比例
	private int allocationId; 
	private String allocationName;  // 
	private String orderMoneyFirst;  //一級代理
	private String orderMoneySecond;//二級代理
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	public int getAllocationId() {
		return allocationId;
	}
	public void setAllocationId(int allocationId) {
		this.allocationId = allocationId;
	}
	public String getAllocationName() {
		return allocationName;
	}
	public void setAllocationName(String allocationName) {
		this.allocationName = allocationName;
	}
	public String getOrderMoneyFirst() {
		return orderMoneyFirst;
	}
	public void setOrderMoneyFirst(String orderMoneyFirst) {
		this.orderMoneyFirst = orderMoneyFirst;
	}
	public String getOrderMoneySecond() {
		return orderMoneySecond;
	}
	public void setOrderMoneySecond(String orderMoneySecond) {
		this.orderMoneySecond = orderMoneySecond;
	}
	
	
	
	
}
