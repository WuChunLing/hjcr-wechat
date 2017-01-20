package com.hjcr.wechat.service;

import java.util.List;

import com.hjcr.wechat.entity.Template;

public interface TemplateService {

	/*
	 * 获取模板
	 */
	public Template getTemplate(int templateId);

	/*
	 * 更新模板
	 */
	public void updateTemplate(Template template);

	/*
	 * 删除模板
	 */
	public void deleTemplate(int templateId);

	// 添加模板
	public void addTemplate(Template template);

	/*
	 * 获取所有模板
	 */
	public List<Template> getAllTemplate();

	/*
	 * 修改默认模板
	 */
	public void reviseTemplate();

}
