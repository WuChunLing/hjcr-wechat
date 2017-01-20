package com.hjcr.wechat.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hjcr.wechat.entity.DrawMoneyRecord;


public interface DrawMoneyRecordDao extends JpaRepository<DrawMoneyRecord, Integer>{

	/*
	 * 获取某用户记录
	 */
	Page<DrawMoneyRecord> findByUserId(Pageable pageable, Integer userId);

	
}
