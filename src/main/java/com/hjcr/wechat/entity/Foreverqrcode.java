package com.hjcr.wechat.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Cacheable
@Table(name="foreverqrcode")
@Entity
public class Foreverqrcode {

	private int qrcodeId;     //二维码id
	private String qrcodeUrl; //二维码地址
	private String userTelephone; //二维码使用者电话
	
	
	@GeneratedValue
	@Id
	public int getQrcodeId() {
		return qrcodeId;
	}
	public void setQrcodeId(int qrcodeId) {
		this.qrcodeId = qrcodeId;
	}
	
	public String getQrcodeUrl() {
		return qrcodeUrl;
	}
	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}
	public String getUserTelephone() {
		return userTelephone;
	}
	public void setUserTelephone(String userTelephone) {
		this.userTelephone = userTelephone;
	}
	
	public Foreverqrcode(int qrcodeId, String qrcodeUrl, String userTelephone) {
		super();
		this.qrcodeId = qrcodeId;
		this.qrcodeUrl = qrcodeUrl;
		this.userTelephone = userTelephone;
	}
	public Foreverqrcode() {
		super();
	}

	
	
	
}
