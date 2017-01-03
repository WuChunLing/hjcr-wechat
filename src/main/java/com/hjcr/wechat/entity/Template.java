package com.hjcr.wechat.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Cacheable
@Table(name="template")
@Entity
public class Template {

	
	
	@GeneratedValue
	@Id
	private int templateId; 
	
	private String templateName;//模板名称
	
	private String templatePath;//模板路径
	
	private int templateQrcodeHigh;//二维码纵坐标
	
	private int templateQrcodeWide;//二维码横坐标
	
	private int templateQrcodeSize;//二维码大小
	
	private int templateHeadImgWide;//微信用户头像纵坐标
	
	private int templateHeadImgHigh;//微信用户头像横坐标
	
	private int templateHeadImgUrl; //微信用户头像路径
	
	private String templateConfirm; //是否确定的模板

	

	public int getTemplateId() {
		return templateId;
	}



	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}



	public String getTemplateName() {
		return templateName;
	}



	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}



	public String getTemplatePath() {
		return templatePath;
	}



	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}



	public int getTemplateQrcodeHigh() {
		return templateQrcodeHigh;
	}



	public void setTemplateQrcodeHigh(int templateQrcodeHigh) {
		this.templateQrcodeHigh = templateQrcodeHigh;
	}



	public int getTemplateQrcodeWide() {
		return templateQrcodeWide;
	}



	public void setTemplateQrcodeWide(int templateQrcodeWide) {
		this.templateQrcodeWide = templateQrcodeWide;
	}



	public int getTemplateQrcodeSize() {
		return templateQrcodeSize;
	}



	public void setTemplateQrcodeSize(int templateQrcodeSize) {
		this.templateQrcodeSize = templateQrcodeSize;
	}



	public int getTemplateHeadImgWide() {
		return templateHeadImgWide;
	}



	public void setTemplateHeadImgWide(int templateHeadImgWide) {
		this.templateHeadImgWide = templateHeadImgWide;
	}



	public int getTemplateHeadImgHigh() {
		return templateHeadImgHigh;
	}



	public void setTemplateHeadImgHigh(int templateHeadImgHigh) {
		this.templateHeadImgHigh = templateHeadImgHigh;
	}



	public int getTemplateHeadImgUrl() {
		return templateHeadImgUrl;
	}



	public void setTemplateHeadImgUrl(int templateHeadImgUrl) {
		this.templateHeadImgUrl = templateHeadImgUrl;
	}



	public String getTemplateConfirm() {
		return templateConfirm;
	}



	public void setTemplateConfirm(String templateConfirm) {
		this.templateConfirm = templateConfirm;
	}


	

	public Template(int templateId, String templateName, String templatePath, int templateQrcodeHigh,
			int templateQrcodeWide, int templateQrcodeSize, int templateHeadImgWide, int templateHeadImgHigh,
			int templateHeadImgUrl, String templateConfirm) {
		super();
		this.templateId = templateId;
		this.templateName = templateName;
		this.templatePath = templatePath;
		this.templateQrcodeHigh = templateQrcodeHigh;
		this.templateQrcodeWide = templateQrcodeWide;
		this.templateQrcodeSize = templateQrcodeSize;
		this.templateHeadImgWide = templateHeadImgWide;
		this.templateHeadImgHigh = templateHeadImgHigh;
		this.templateHeadImgUrl = templateHeadImgUrl;
		this.templateConfirm = templateConfirm;
	}



	public Template() {
		super();
	}
	

	
	
}
