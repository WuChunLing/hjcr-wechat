package com.hjcr.wechat.service;

import java.util.List;

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
	public Template getTemplate(int templateId) {
		return templateImpl.findOne(templateId);
	}

	/*
	 * 更新模板
	 */
	public void updateTemplate(Template template) {

		templateImpl.saveAndFlush(template);

	}

	/*
	 * 删除模板
	 */
	public void deleTemplate(int templateId) {

		templateImpl.delete(templateId);

	}

	// 添加模板
	public void addTemplate(Template template) {

		templateImpl.saveAndFlush(template);

	}
	
	/*
	 * 获取所有模板
	 */
	public List<Template> getAllTemplate() {
		return templateImpl.findAll();
	}
	
	/*
	 * 修改默认模板
	 */
	
	public void reviseTemplate(){
		Template template=templateImpl.getTemplatebyConfirm("1");
		template.setTemplateConfirm("0");
	}
	
	

	
}



