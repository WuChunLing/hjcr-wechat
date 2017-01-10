package com.hjcr.wechat.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hjcr.wechat.entity.Foreverqrcode;
import com.hjcr.wechat.entity.User;

public interface ForeverqrcodeImpl extends JpaRepository<Foreverqrcode, Integer> {

	/*
	 * 通过telephone获得二维码信息
	 */
	@Query("SELECT foreverqrcode FROM Foreverqrcode foreverqrcode WHERE foreverqrcode.userTelephone = ?1 ")
	Foreverqrcode getqrcodebyphone(String userTelephone);

}
