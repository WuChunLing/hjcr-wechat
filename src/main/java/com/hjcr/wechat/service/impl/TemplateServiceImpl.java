package com.hjcr.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.dao.TemplateDao;
import com.hjcr.wechat.entity.Template;
import com.hjcr.wechat.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService{

	@Autowired
	private TemplateDao templateDao;

	/*
	 * 获取模板
	 */
	public Template getTemplate(int templateId) {
		return templateDao.findOne(templateId);
	}

	/*
	 * 更新模板
	 */
	public void updateTemplate(Template template) {

		templateDao.saveAndFlush(template);

	}

	/*
	 * 删除模板
	 */
	public void deleTemplate(int templateId) {

		templateDao.delete(templateId);

	}

	// 添加模板
	public void addTemplate(Template template) {

		templateDao.saveAndFlush(template);

	}

	/*
	 * 获取所有模板
	 */
	public List<Template> getAllTemplate() {
		return templateDao.findAll();
	}

	/*
	 * 修改默认模板
	 */
	public void reviseTemplate() {
		Template template = templateDao.getTemplatebyConfirm(1);
		System.out.println(template);
		if(template!=null){
		template.setTemplateConfirm(0);
		templateDao.save(template);}
		System.out.println(template);
		

	}

}
