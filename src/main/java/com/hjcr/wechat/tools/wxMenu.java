package com.hjcr.wechat.tools;

import java.io.IOException;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.WxMenu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;

public class wxMenu {
	   public  static void getMenu() throws IOException, WxErrorException {
		   WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
		   WxMpService wxService = new WxMpServiceImpl();
			wxService.setWxMpConfigStorage(config);
	
			WxMenu menu = new WxMenu();
	        WxMenuButton button2 = new WxMenuButton();
	        button2.setName("知鹏好帅");
	
	        WxMenuButton button21 = new WxMenuButton();
	        button21.setType(WxConsts.BUTTON_CLICK);
	        button21.setName("生成二维码");
	        button21.setKey("0");
	        
	        
	        WxMenuButton button22 = new WxMenuButton();
	        button22.setType(WxConsts.BUTTON_VIEW);
	        button22.setName("个人信息");
	        button22.setUrl("http://www.4399.com");
	
	        button2.getSubButtons().add(button21);
	        button2.getSubButtons().add(button22);
	  
	        menu.getButtons().add(button2);
	        wxService.menuCreate(menu);
	    }
	   
	   
}

