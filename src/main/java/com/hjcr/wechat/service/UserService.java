package com.hjcr.wechat.service;

import java.util.List;

import com.hjcr.wechat.entity.User;

public interface UserService {

	// 获取用户的一级代理
	public List<User> getFirstAgent(int userid);

	// 获取用户的通过openid
	public User getUser(String openid);

	// 获取用户的下的二级代理
	public List<User> getSecondChildrenAgent(int userid);

	public String getOpenidbyuser(String userId);

	public String getUserInfortation(String openid);

	/*
	 * 获取一二级代理
	 */

	public List<User> getAllfirstUser(int userid);

	/*
	 * 根据id获取用户信息.
	 */
	public User findOne(Integer userId);

	// 获取一级代理
	public User getFirstUser(int userid);

	// 获取二级代理
	public User getSecondUser(int userid);
	
	//更新用户余额
	public void setUserBalance(int userID,float money);
	
}
