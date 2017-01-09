package com.hjcr.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.entity.Template;
import com.hjcr.wechat.impl.TemplateImpl;

@Service
public class TemplateService {

	@Autowired
	private TemplateImpl templateImpl;
	
	
	
	/*
	 * 获取模板
	 */
	public Template getTemplate(int templateId){
		return templateImpl.getOne(templateId);
	}
	
	
	public String updateTemplate(Template template){
		 try {
			templateImpl.saveAndFlush(template);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		 return "success";
	}
	
	
	public String deleTemplate(int templateId){
		try {
			templateImpl.delete(templateId);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	
	//添加模板
	public String addTemplate(Template template){
		try{
			templateImpl.saveAndFlush(template);
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		
	}
		return "success";
}
}
