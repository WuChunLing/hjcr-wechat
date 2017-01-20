package com.hjcr.wechat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hjcr.wechat.entity.Voucher;

public interface CardDao extends JpaRepository<Voucher, Integer> {

	/*
	 * 通过获得cardId
	 */
	@Query("SELECT voucher.voucherCord FROM Voucher voucher WHERE voucher.voucherConfirm = ?1 ")
	String gerCardId(int voucherConfirm);
	
}
