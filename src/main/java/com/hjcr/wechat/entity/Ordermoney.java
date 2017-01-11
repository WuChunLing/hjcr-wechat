package com.hjcr.wechat.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Cacheable
@Table(name="ordermoney")
@Entity
public class Ordermoney {

	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int OrderMoneyId;  //订单类别id
	private String OrderMoneyName; //订单类别名称
	private float orderMoneyDistribution; //订单类别分配比例
	public int getOrderMoneyId() {
		return OrderMoneyId;
	}
	public void setOrderMoneyId(int orderMoneyId) {
		OrderMoneyId = orderMoneyId;
	}
	public String getOrderMoneyName() {
		return OrderMoneyName;
	}
	public void setOrderMoneyName(String orderMoneyName) {
		OrderMoneyName = orderMoneyName;
	}
	public float getOrderMoneyDistribution() {
		return orderMoneyDistribution;
	}
	public void setOrderMoneyDistribution(float orderMoneyDistribution) {
		this.orderMoneyDistribution = orderMoneyDistribution;
	}
	@Override
	public String toString() {
		return "Ordermoney [OrderMoneyId=" + OrderMoneyId + ", OrderMoneyName=" + OrderMoneyName
				+ ", orderMoneyDistribution=" + orderMoneyDistribution + "]";
	}
	
	
	
	
}
