package com.hjcr.wechat.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hjcr.wechat.entity.DrawMoneyRecord;

public interface DrawMoneyRecordDao
		extends JpaRepository<DrawMoneyRecord, Integer> {

	/*
	 * 获取某用户记录
	 */
	Page<DrawMoneyRecord> findByUserId(Pageable pageable, Integer userId);

	// 根据状态获取记录
	Page<DrawMoneyRecord> findByStatus(Pageable pageable, Integer status);
	
	//根据状态和时间获取记录
	@Query("SELECT r FROM DrawMoneyRecord r where r.status = :status and r.creatTime >= :startDate and r.creatTime <= :endDate")
	Page<DrawMoneyRecord> findByStatus(Pageable pageable, @Param("status")Integer status,@Param("startDate")String startDate, @Param("endDate")String endDate);

	// 获取某个状态的总金额
	@Query("SELECT SUM(r.money) FROM DrawMoneyRecord r where r.status = ?1")
	Double getSumByStatus(Integer status);

	// 获取某个状态某个时间段的总金额
	@Query("SELECT SUM(r.money) FROM DrawMoneyRecord r where r.status = ?1 and r.creatTime >= ?2 and r.creatTime <= ?3")
	Double getSumByStatus(Integer status, String startDate, String endDate);

	

}
