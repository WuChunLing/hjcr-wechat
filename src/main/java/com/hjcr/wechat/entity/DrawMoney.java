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

//用户提现方式表
@JsonInclude(JsonInclude.Include.NON_NULL)
@Cacheable
@Table(name="draw_money")
@Entity
public class DrawMoney {

	
	private Integer id;    //id主键
	private Integer userId;  //用户id
	private String realName;  //真实姓名
	private String phone;  //手机号码
	private Integer way;  //体现方式  （1：银行卡  2：支付宝）
	private String number;  //体现号码
	private String creatTime;  //创建时间
	
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getWay() {
		return way;
	}
	public void setWay(Integer way) {
		this.way = way;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Generated(GenerationTime.INSERT)
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	@Override
	public String toString() {
		return "DrawMoney [id=" + id + ", userId=" + userId + ", realName="
				+ realName + ", phone=" + phone + ", way=" + way + ", number="
				+ number + ", creatTime=" + creatTime + "]";
	}
	
}