package com.hjcr.wechat.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hjcr.wechat.entity.Voucher;

public interface CardImpl extends JpaRepository<Voucher, Integer> {

	/*
	 * 通过获得cardId
	 */
	@Query("SELECT voucher FROM Voucher voucher WHERE voucher.voucherConfirm = ?1 ")
	String gerCardId(int voucherConfirm);
	
}
