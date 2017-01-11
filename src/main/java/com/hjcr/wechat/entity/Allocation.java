package com.hjcr.wechat.entity;

public class Allocation {

//分配的比例
	private int allocationId; 
	private String allocationName;  // 
	private String orderMoneyFirst;  //一級代理
	private String orderMoneySecond;//二級代理
	private int orderConfirm;       //是否先用該模板
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
	public int getOrderConfirm() {
		return orderConfirm;
	}
	public void setOrderConfirm(int orderConfirm) {
		this.orderConfirm = orderConfirm;
	}
	
	
	
}
