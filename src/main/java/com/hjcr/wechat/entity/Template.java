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
	
	private float templateQrcodeHigh;//二维码纵坐标
	
	private float templateQrcodeWide;//二维码横坐标
	
	private float templateQrcodeSize;//二维码大小
	
	private float templateHeadImgWide;//微信用户头像纵坐标
	
	private float templateHeadImgHigh;//微信用户头像横坐标
	
/*	private String templateHeadImgUrl; //微信用户头像路径
	*/

	private int templateConfirm; //是否确定的模板


	

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



	public float getTemplateQrcodeHigh() {
		return templateQrcodeHigh;
	}



	public void setTemplateQrcodeHigh(float templateQrcodeHigh) {
		this.templateQrcodeHigh = templateQrcodeHigh;
	}



	public float getTemplateQrcodeWide() {
		return templateQrcodeWide;
	}



	public void setTemplateQrcodeWide(float templateQrcodeWide) {
		this.templateQrcodeWide = templateQrcodeWide;
	}



	public float getTemplateQrcodeSize() {
		return templateQrcodeSize;
	}



	public void setTemplateQrcodeSize(float templateQrcodeSize) {
		this.templateQrcodeSize = templateQrcodeSize;
	}



	public float getTemplateHeadImgWide() {
		return templateHeadImgWide;
	}



	public void setTemplateHeadImgWide(float templateHeadImgWide) {
		this.templateHeadImgWide = templateHeadImgWide;
	}



	public float getTemplateHeadImgHigh() {
		return templateHeadImgHigh;
	}



	public void setTemplateHeadImgHigh(float templateHeadImgHigh) {
		this.templateHeadImgHigh = templateHeadImgHigh;
	}


	public int isTemplateConfirm() {
		return templateConfirm;
	}



	public void setTemplateConfirm(int templateConfirm) {
		this.templateConfirm = templateConfirm;
	}



	public int getTemplateConfirm() {
		return templateConfirm;
	}



	public Template(int templateId, String templateName, String templatePath, float templateQrcodeHigh,

			float templateQrcodeWide, float templateQrcodeSize, float templateHeadImgWide, float templateHeadImgHigh, int templateConfirm) {
		super();
		this.templateId = templateId;
		this.templateName = templateName;
		this.templatePath = templatePath;
		this.templateQrcodeHigh = templateQrcodeHigh;
		this.templateQrcodeWide = templateQrcodeWide;
		this.templateQrcodeSize = templateQrcodeSize;
		this.templateHeadImgWide = templateHeadImgWide;
		this.templateHeadImgHigh = templateHeadImgHigh;
			this.templateConfirm = templateConfirm;
	}



	public Template() {
		super();
	}



	@Override
	public String toString() {
		return "Template [templateId=" + templateId + ", templateName=" + templateName + ", templatePath="
				+ templatePath + ", templateQrcodeHigh=" + templateQrcodeHigh + ", templateQrcodeWide="
				+ templateQrcodeWide + ", templateQrcodeSize=" + templateQrcodeSize + ", templateHeadImgWide="
				+ templateHeadImgWide + ", templateHeadImgHigh=" + templateHeadImgHigh + ", templateConfirm="
				+ templateConfirm + "]";
	}
	

	
	
}
