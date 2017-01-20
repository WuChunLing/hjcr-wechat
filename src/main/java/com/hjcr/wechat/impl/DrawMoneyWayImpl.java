package com.hjcr.wechat.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hjcr.wechat.entity.DrawMoneyWay;

public interface DrawMoneyWayImpl extends JpaRepository<DrawMoneyWay, Integer>{

	/*
	 * 获取某用户的默认提现方式
	 */
	@Query("select d from DrawMoneyWay d where d.userId = ?1 and d.defaultWay = 1")
	DrawMoneyWay getDafultWayByUserId(Integer userId);

	/*
	 * 获取某用户所有提现方式
	 */
	@Query("select d from DrawMoneyWay d where d.userId = ?1")
	List<DrawMoneyWay> getUserWay(Integer userId);

	
}
