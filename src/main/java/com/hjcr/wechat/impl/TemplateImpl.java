package com.hjcr.wechat.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.hjcr.wechat.entity.Template;

public interface TemplateImpl extends JpaRepository<Template, Integer>{


	@Query("SELECT template FROM Template template WHERE template.templateConfirm = ?1 ")
	List<Template> getByUser(Integer userID);

}
