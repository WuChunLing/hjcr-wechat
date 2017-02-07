package com.hjcr.wechat.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hjcr.wechat.entity.Allocation;
import com.hjcr.wechat.entity.Bill;
import com.hjcr.wechat.entity.DrawMoneyRecord;

public interface BillDao extends JpaRepository<Bill, Integer> {

	// 根据时间获取订单记录
	@Query("SELECT r FROM Bill r where r.billDate >= :startDate and r.billDate <= :endDate")
	Page<Bill> findByStatus(Pageable pageable, @Param("startDate") String startDate, @Param("endDate") String endDate);

	// 获取订单的总金额
	@Query("SELECT SUM(r.billMoney) FROM Bill r")
	Double getSumConsume();

	// 获取订单某个时间段的总金额
	@Query("SELECT SUM(r.billMoney) FROM Bill r where r.billDate >= ?1 and r.billDate <= ?2")
	Double getSumConsume(String startDate, String endDate);

	// 获取订单的总金额
	@Query("SELECT SUM(r.billProfit) FROM Bill r")
	Double getSumFeeSplitting();

	// 获取订单某个时间段的分润的金额
	@Query("SELECT SUM(r.billProfit) FROM Bill r where r.billDate >= ?1 and r.billDate <= ?2")
	Double getSumFeeSplitting(String startDate, String endDate);

	// 根据时间获取订单记录
	@Query("SELECT r FROM Bill r where r.billDate >= :startDate and r.billDate <= :endDate and r.userId =:userId or r.userFirstId=:userId or r.userSecondId=:userId")
	Page<Bill> getBillByUser(Pageable pageable, @Param("startDate") String startDate, @Param("endDate") String endDate,@Param("userId")Integer userId);

	// 根据时间获取订单记录
	@Query("SELECT r FROM Bill r where  r.userId =:userId or r.userFirstId=:userId or r.userSecondId=:userId")
	Page<Bill> getBillByUser(Pageable pageable, @Param("userId") Integer userId);

}
