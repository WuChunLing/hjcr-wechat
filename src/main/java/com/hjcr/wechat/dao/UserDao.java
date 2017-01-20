package com.hjcr.wechat.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hjcr.wechat.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {

	/*
	 * 通过openId获得用户信息
	 */
	@Query("SELECT user FROM User user WHERE user.userOpenid = ?1 ")
	User getUserbyOpenid(String Openid);

	/*
	 * 通过userId获得用户openid
	 */
	@Query("SELECT user.userOpenid FROM User user WHERE user.userId = ?1 ")
	String getOpenidbyuser(int userId);

	/*
	 * 通过telephone获得用户user
	 */
	@Query("SELECT user FROM User user WHERE user.userMobiphone = ?1 ")
	User getUserByphone(String userMobiphone);

	/*
	 * 通过userId获得用户信息
	 */
	@Query("SELECT user FROM User user WHERE user.userId = ?1 ")
	User getUserbyuserid(int userId);

	// 获取用户的一级代理
	@Query("SELECT user FROM User user WHERE user.userId like  '/?1%'")
	List<User> getFirstAgent(int userid);

	// 获取用户的二级代理
	@Query("SELECT user FROM User user WHERE user.userId like  '/?/?1%'")
	List<User> getSecondAgent(int userid);
}
