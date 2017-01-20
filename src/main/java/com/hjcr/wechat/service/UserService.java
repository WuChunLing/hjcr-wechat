package com.hjcr.wechat.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcr.wechat.entity.User;
import com.hjcr.wechat.impl.UserImpl;
import com.hjcr.wechat.tools.propFactory;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Service
public class UserService {

	@Autowired
	private UserImpl userImpl;

	public WxMpInMemoryConfigStorage config;

	// 关联app用户
	public String setUserForeignkey(String openid, int userid, String mobiphone) {
		User user = userImpl.getUserbyOpenid(openid);

		// 用户没有之前关注微信公众号
		if (user == null) {
			WxMpInMemoryConfigStorage config;
			try {
				config = new propFactory().WxMpInMemoryConfigStorageFactory();
				WxMpService wxService = new WxMpServiceImpl();
				wxService.setWxMpConfigStorage(config);
				String lang = "zh_CN"; // 语言
				try {
					// 通过openid获取微信的信息
					WxMpUser wxuser = wxService.userInfo(openid, lang);
				} catch (WxErrorException e) {
					e.printStackTrace();
					return "WxErrorException";
				}
			} catch (IOException e) {
				e.printStackTrace();
				return "IOException";
			}

		}
		// 用户之前已经关注了公众号
		else {
			// 添加用户的手机号码和用户id
			user.setUserForeignkey(userid);
			user.setUserMobiphone(mobiphone);
		}
		return "success";
	}

	// 获取用户的下的一级代理
	public List<User> getFirstChildrenAgent(int userid) {
		return userImpl.getFirstChildrenAgent(userid);
	}

	// 获取用户的下的二级代理
	public List<User> getSecondChildrenAgent(int userid) {
		return userImpl.getSecondChildrenAgent(userid);
	}

	// 获取用户的通过openid
	public User getUser(String openid) {
		return userImpl.getUserbyOpenid(openid);
	}

	public String getOpenidbyuser(String userId) {
		int Userid = Integer.parseInt(userId);
		return userImpl.getOpenidbyuser(Userid);
	}


	public String getUserInfortation(String openid) {
		// 通过Openid获取User
		User user = userImpl.getUserbyOpenid(openid);
		if (user.getUserForeignkey() == null) {

			try {
				config = new propFactory().WxMpInMemoryConfigStorageFactory();
				WxMpService wxService = new WxMpServiceImpl();
				wxService.setWxMpConfigStorage(config);
				WxMpCustomMessage toUserNamemessage = WxMpCustomMessage.TEXT().toUser(openid)
						.content("没有绑定微信账号暂时无法查看，请到app上绑定").build();
				return "notexistaccount";
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return "success";
	}
	
	
	/*
	 *获取一二级代理
	 */

	public List<User> getfAllUser(int userid){
		User user=userImpl.findOne(userid);
		String hierarchy = user.getUserHierarchy();
		if(!hierarchy.equals(null)){
		String[] st = hierarchy.split("/"); // 获取层级关系
		List list=new ArrayList<User>();
		
		int userfirstid=Integer.valueOf(st[1]);
		list.add(userImpl.findOne(userfirstid));
		if(st.length>2){
			int usersecondid=Integer.valueOf(st[2]);
			list.add(userImpl.findOne(usersecondid));
		}
		return list;
		}
		return null;
	}
	
	
	
	
}
