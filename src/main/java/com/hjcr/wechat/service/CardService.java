package com.hjcr.wechat.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.impl.CardImpl;
import com.hjcr.wechat.impl.UserImpl;
import com.hjcr.wechat.tools.propFactory;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import net.sf.json.JSONObject;

@Service
public class CardService {

	
	@Autowired
	private CardImpl cardImpl;
	
	@Autowired
	private UserImpl userImpl;
	/*
	 * 创建一个50元的卡卷
	 */
	public String creatfiftyCard() throws IOException, WxErrorException {
		WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);

		JSONObject jsonORG = new JSONObject();
		JSONObject card = new JSONObject();
		JSONObject groupon = new JSONObject();
		JSONObject base_info = new JSONObject();
		JSONObject sku = new JSONObject();
		JSONObject date_info = new JSONObject();
		card.element("card_type", "GROUPON");// 设置卡券的类型
		base_info.element("code_type", "CODE_TYPE_TEXT");
		base_info.element("logo_url",
				"http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0");// 卡券的商户logo
		base_info.element("brand_name", "皇嘉財潤");
		base_info.element("title", "50元优惠券");
		base_info.element("color", "Color010");
		base_info.element("notice", "请出示二维码核销卡券");
		base_info.element("description", "不可与其他优惠同享/n如需团购券发票，请向店员提出要求。");

		date_info.element("type", "DATE_TYPE_FIX_TIME_RANGE");
		date_info.element("begin_timestamp", 1483724261);
		date_info.element("end_timestamp", 1542724261);

		sku.element("quantity", 100000);
		base_info.element("date_info", date_info);
		base_info.element("sku", sku);
		groupon.element("base_info", base_info);
		groupon.element("deal_detail", "以下锅底2选1（有菌王锅、麻辣锅、大骨锅、番茄锅、清补 凉锅、酸菜鱼锅可选）： 大锅1份 12元 小锅2份 16元");
		card.element("groupon", groupon);
		jsonORG.element("card", card);
		String postData = jsonORG.toString();
		String url = "https://api.weixin.qq.com/card/create?";
		System.out.println(postData);
		

		return wxService.post(url, postData);
	}

	/*
	 * 创建一个30元的卡卷
	 */
	public String creatthirtyCard() throws IOException, WxErrorException {
		WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);

		JSONObject jsonORG = new JSONObject();
		JSONObject card = new JSONObject();
		JSONObject groupon = new JSONObject();
		JSONObject base_info = new JSONObject();
		JSONObject sku = new JSONObject();
		JSONObject date_info = new JSONObject();
		card.element("card_type", "GROUPON");// 设置卡券的类型
		base_info.element("code_type", "CODE_TYPE_TEXT");
		base_info.element("logo_url",
				"http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0");// 卡券的商户logo
		base_info.element("brand_name", "皇嘉財潤");
		base_info.element("title", "30元优惠券");
		base_info.element("color", "Color010");
		base_info.element("notice", "请出示二维码核销卡券");
		base_info.element("description", "不可与其他优惠同享/n如需团购券发票，请向店员提出要求。");

		date_info.element("type", "DATE_TYPE_FIX_TIME_RANGE");
		date_info.element("begin_timestamp", 1483724261);
		date_info.element("end_timestamp", 1542724261);

		sku.element("quantity", 100000);
		base_info.element("date_info", date_info);
		base_info.element("sku", sku);
		groupon.element("base_info", base_info);
		groupon.element("deal_detail", "以下锅底2选1（有菌王锅、麻辣锅、大骨锅、番茄锅、清补 凉锅、酸菜鱼锅可选）： 大锅1份 12元 小锅2份 16元");
		card.element("groupon", groupon);
		jsonORG.element("card", card);
		String postData = jsonORG.toString();
		String url = "https://api.weixin.qq.com/card/create?";
		System.out.println(postData);
		

		return wxService.post(url, postData);
	}

	/*
	 * 发送卡卷给微信用户
	 */
	public String sendCard(String touseropenid) throws IOException, WxErrorException {
		System.out.println("9999");
		WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);
		
		String cardid=cardImpl.gerCardId(1);
		
		JSONObject jsonORG = new JSONObject();
		JSONObject jsonOR2 = new JSONObject();
		jsonOR2.element("card_id", cardid);
		jsonORG.element("touser", touseropenid);
		jsonORG.element("msgtype", "wxcard");
		jsonORG.element("wxcard", jsonOR2);

		String postData = jsonORG.toString();
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?";
		return wxService.post(url, postData);
	}

	/*
	 * 删除用户卡卷
	 */
	public String deleteCard(String code) throws IOException, WxErrorException {
		WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);
		JSONObject jsonORG = new JSONObject();
		jsonORG.element("code", code);
		String postData = jsonORG.toString();
		String url = "https://api.weixin.qq.com/card/code/consume?";
		return wxService.post(url, postData);
	}

}
