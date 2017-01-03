package com.hjcr.wechat.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;

public class propFactory {

	
	
	public  WxMpInMemoryConfigStorage WxMpInMemoryConfigStorageFactory() throws IOException{
		WxMpInMemoryConfigStorage config =new WxMpInMemoryConfigStorage();
		Properties prop = new Properties();  
		System.out.println(this.getClass());
		InputStream ins = this.getClass().getClassLoader().getResourceAsStream("wechat.properties");
		System.out.println(ins);
		prop.load(ins);
		config.setAppId(prop.getProperty("appid")); // 设置微信公众号的appid
		System.out.println(prop.getProperty("appid"));
		config.setSecret(prop.getProperty("corpSecret")); // 设置微信公众号的app corpSecret
		config.setToken(prop.getProperty("token")); // 设置微信公众号的token
		config.setAesKey(prop.getProperty("EncodingAESKey")); // 设置微信公众号的EncodingAESKey
		return config;
	}
	
}
