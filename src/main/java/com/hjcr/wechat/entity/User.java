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
@Table(name="user")
@Entity
public class User {

	
	private int userId;
	private String headImgUrl;  //微信头像url
	private String userName;   //微信名
	private String userOpenid;   //微信用户在公众号的id
	private String userHierarchy;  //微信用户的祖辈关系
	private Integer userForeignkey; //app用户id
	private String userMobiphone;  //手机
	private String unionId;   //微信用户在公众号与app上的共同id
	private Float balance=(float) 0;  //用户余额
	
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserOpenid() {
		return userOpenid;
	}
	public void setUserOpenid(String userOpenid) {
		this.userOpenid = userOpenid;
	}
	public String getUserHierarchy() {
		return userHierarchy;
	}
	public void setUserHierarchy(String userHierarchy) {
		this.userHierarchy = userHierarchy;
	}
	public Integer getUserForeignkey() {
		return userForeignkey;
	}
	public void setUserForeignkey(Integer userForeignkey) {
		this.userForeignkey = userForeignkey;
	}
	public String getUserMobiphone() {
		return userMobiphone;
	}
	public void setUserMobiphone(String userMobiphone) {
		this.userMobiphone = userMobiphone;
	}

	
	
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}
	
	public User(int userId, String headImgUrl, String userName, String userOpenid, String userHierarchy,
			Integer userForeignkey, String userMobiphone) {
		super();
		this.userId = userId;
		this.headImgUrl = headImgUrl;
		this.userName = userName;
		this.userOpenid = userOpenid;
		this.userHierarchy = userHierarchy;
		this.userForeignkey = userForeignkey;
		this.userMobiphone = userMobiphone;
	}
	public User() {
		super();
	}
	
	public User(String headImgUrl, String userName, String userOpenid) {
		this.headImgUrl = headImgUrl;
		this.userName = userName;
		this.userOpenid = userOpenid;
		
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", headImgUrl=" + headImgUrl
				+ ", userName=" + userName + ", userOpenid=" + userOpenid
				+ ", userHierarchy=" + userHierarchy + ", userForeignkey="
				+ userForeignkey + ", userMobiphone=" + userMobiphone
				+ ", UnionID=" + unionId + ", balance=" + balance + "]";
	}
}