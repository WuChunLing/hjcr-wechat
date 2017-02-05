package com.hjcr.wechat.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Cacheable
@Table(name = "userbill")
@Entity
public class UserBill {

	private Integer id; // id主键
	private Integer userId; // 分润的用户id
	private Integer customerId; // 消费者Id
	private Float billMoney; // 订单金额
	private Float profit;// 分润金额
	private String creatTime;// 时间

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Float getBillMoney() {
		return billMoney;
	}

	public void setBillMoney(Float billMoney) {
		this.billMoney = billMoney;
	}

	public Float getProfit() {
		return profit;
	}

	public void setProfit(Float profit) {
		this.profit = profit;
	}

	@Generated(GenerationTime.INSERT)
	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	public UserBill(Integer id, Integer userId, Integer customerId, Float billMoney, Float profit, String creatTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.customerId = customerId;
		this.billMoney = billMoney;
		this.profit = profit;
		this.creatTime = creatTime;
	}

	public UserBill() {
		super();
	}

}
