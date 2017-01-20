package com.hjcr.wechat.service;

import java.util.List;

import com.hjcr.wechat.entity.User;

public interface UserService {

	// 获取用户的一级代理
	public List<User> getFirstAgent(int userid);

	// 获取用户的二级代理
	public List<User> getSecondAgent(int userid);

	// 获取用户的通过openid
	public User getUser(String openid);

	public String getOpenidbyuser(String userId);

	public String getUserInfortation(String openid);
}
