package com.hjcr.wechat.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hjcr.wechat.entity.Template;

public interface TemplateImpl extends JpaRepository<Template, Integer> {

	/*
	 * 通过Confirm获得二维码模板
	 */
	@Query("SELECT template FROM Template template WHERE template.templateConfirm = ?1 ")
<<<<<<< HEAD
<<<<<<< HEAD
	Template getTemplatebyConfirm(Boolean Confirm);
=======
	Template getTemplatebyConfirm(int Confirm);
>>>>>>> 1440158e66f78701f46d444388cc003dae3f7ff5
=======
	Template getTemplatebyConfirm(int Confirm);
>>>>>>> 3369541ff6d29fea17db742242cdad3d2fb415f0

}
