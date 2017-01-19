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

//用户提现记录表
@JsonInclude(JsonInclude.Include.NON_NULL)
@Cacheable
@Table(name="draw_money_record")
@Entity
public class DrawMoneyRecord {

	
	private Integer id;    //id主键
	private Integer userId;  //用户id
	private Integer wayId; //提现方式id
	private Float money;   //提现金额
	private String creatTime;  //创建时间
	private Integer status; //提现状态 （1：待审核  2：提现成功  3：提现失败）
	private DrawMoneyWay drawMoneyWay;
	
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
	public Integer getWayId() {
		return wayId;
	}
	public void setWayId(Integer wayId) {
		this.wayId = wayId;
	}
	@Generated(GenerationTime.INSERT)
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	@Transient
	public DrawMoneyWay getDrawMoneyWay() {
		return drawMoneyWay;
	}
	public void setDrawMoneyWay(DrawMoneyWay drawMoneyWay) {
		this.drawMoneyWay = drawMoneyWay;
	}
	@Override
	public String toString() {
		return "DrawMoneyRecord [id=" + id + ", userId=" + userId + ", wayId="
				+ wayId + ", money=" + money + ", creatTime=" + creatTime
				+ ", status=" + status + "]";
	}
	
}