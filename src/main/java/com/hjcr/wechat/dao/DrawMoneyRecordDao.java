package com.hjcr.wechat.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hjcr.wechat.entity.DrawMoneyRecord;


public interface DrawMoneyRecordDao extends JpaRepository<DrawMoneyRecord, Integer>{

	/*
	 * 获取某用户记录
	 */
	Page<DrawMoneyRecord> findByUserId(Pageable pageable, Integer userId);

	@Query("SELECT SUM(r.money) FROM DrawMoneyRecord r where r.status = 1")
	Double getApplyTotal();
	
	@Query("SELECT SUM(r.money) FROM DrawMoneyRecord r where r.status = 2")
	Double getSuccessTotal();
	
	@Query("SELECT SUM(r.money) FROM DrawMoneyRecord r where r.status = 3")
	Double getRejectTotal();
}
