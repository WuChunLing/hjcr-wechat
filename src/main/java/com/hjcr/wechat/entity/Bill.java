package com.hjcr.wechat.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonInclude;

//用户账单记录表
@JsonInclude(JsonInclude.Include.NON_NULL)
@Cacheable
@Table(name = "bill")
@Entity
public class Bill {

	private Integer id; // id主键
	private Integer billId; // 订单id
	private Float billMoney; // 订单金额
	private Float billProfit; // 分润金额
	private int typeid;// 类别id
	private Integer userId;// 消费者id
	private int userFirstId;// 代理一级id
	private int userSecondId;// 代理二级id
	private Float userFirstProfit;// 代理一级的分润金额
	private Float userSecondProfit;// 代理二级的分润金额
	private User user; // 消费者用户信息
	private User userFirst; // 一级代理用户信息
	private User userSecond; // 二级代理用户信息
	private String billDate; // 创建时间

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public Float getBillMoney() {
		return billMoney;
	}

	public void setBillMoney(Float billMoney) {
		this.billMoney = billMoney;
	}

	public Float getBillProfit() {
		return billProfit;
	}

	public void setBillProfit(Float billProfit) {
		this.billProfit = billProfit;
	}

	

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public int getUserFirstId() {
		return userFirstId;
	}

	public void setUserFirstId(int userFirstId) {
		this.userFirstId = userFirstId;
	}

	public int getUserSecondId() {
		return userSecondId;
	}

	public void setUserSecondId(int userSecondId) {
		this.userSecondId = userSecondId;
	}

	public Float getUserFirstProfit() {
		return userFirstProfit;
	}

	public void setUserFirstProfit(Float userFirstProfit) {
		this.userFirstProfit = userFirstProfit;
	}

	public Float getUserSecondProfit() {
		return userSecondProfit;
	}

	public void setUserSecondProfit(Float userSecondProfit) {
		this.userSecondProfit = userSecondProfit;
	}

	@Transient
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Transient
	public User getUserFirst() {
		return userFirst;
	}

	public void setUserFirst(User userFirst) {
		this.userFirst = userFirst;
	}

	@Transient
	public User getUserSecond() {
		return userSecond;
	}

	public void setUserSecond(User userSecond) {
		this.userSecond = userSecond;
	}

	@Generated(GenerationTime.INSERT)
	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public Bill(Integer id, Integer billId, Float billMoney, Float billProfit, int typeid, Integer userId,
			int userFirstId, int userSecondId, Float userFirstProfit, Float userSecondProfit, User user, User userFirst,
			User userSecond, String billDate) {
		super();
		this.id = id;
		this.billId = billId;
		this.billMoney = billMoney;
		this.billProfit = billProfit;
		this.typeid = typeid;
		this.userId = userId;
		this.userFirstId = userFirstId;
		this.userSecondId = userSecondId;
		this.userFirstProfit = userFirstProfit;
		this.userSecondProfit = userSecondProfit;
		this.user = user;
		this.userFirst = userFirst;
		this.userSecond = userSecond;
		this.billDate = billDate;
	}

	public Bill() {
		super();
	}

}
