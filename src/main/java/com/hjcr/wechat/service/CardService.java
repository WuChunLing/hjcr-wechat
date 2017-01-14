package com.hjcr.wechat.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjcr.wechat.entity.Voucher;
import com.hjcr.wechat.impl.CardImpl;
import com.hjcr.wechat.impl.UserImpl;
import com.hjcr.wechat.tools.propFactory;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service
public class CardService {

	
	@Autowired
	private CardImpl cardImpl;
	
	@Autowired
	private UserImpl userImpl;
	/*
	 * 创建一级代理的卡卷
	 */
	public String creatfiftyCard() throws IOException, WxErrorException {
		WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);


		JSONObject jsonORG = new JSONObject();
		JSONObject card = new JSONObject();
		JSONObject general_coupon = new JSONObject();
		JSONObject base_info = new JSONObject();
		JSONObject sku = new JSONObject();
		JSONObject date_info = new JSONObject();
		card.element("card_type", "GENERAL_COUPON");// 设置卡券的类型
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
		general_coupon.element("base_info", base_info);
		general_coupon.element("default_detail", "皇嘉財潤50元优惠卷");
		card.element("general_coupon", general_coupon);
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
		JSONObject general_coupon = new JSONObject();
		JSONObject base_info = new JSONObject();
		JSONObject sku = new JSONObject();
		JSONObject date_info = new JSONObject();
		card.element("card_type", "GENERAL_COUPON");// 设置卡券的类型
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
		general_coupon.element("base_info", base_info);
		general_coupon.element("default_detail", "皇嘉財潤30元优惠卷");
		card.element("general_coupon", general_coupon);
		jsonORG.element("card", card);
		String postData = jsonORG.toString();
		String url = "https://api.weixin.qq.com/card/create?";
		System.out.println(postData);
		

		return wxService.post(url, postData);
	}

	
	
	/*
	 * 创建自定义卡卷
	 */
	public String creatCard(String money) throws IOException, WxErrorException {
		WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);

		JSONObject jsonORG = new JSONObject();
		JSONObject card = new JSONObject();
		JSONObject general_coupon = new JSONObject();
		JSONObject base_info = new JSONObject();
		JSONObject sku = new JSONObject();
		JSONObject date_info = new JSONObject();
		card.element("card_type", "GENERAL_COUPON");// 设置卡券的类型
		base_info.element("code_type", "CODE_TYPE_TEXT");
		base_info.element("logo_url",
				"http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0");// 卡券的商户logo
		base_info.element("brand_name", "皇嘉財潤");
		base_info.element("title", money+"元优惠券");
		base_info.element("color", "Color010");
		base_info.element("notice", "请输入code码");
		base_info.element("description", "不可与其他优惠同享");

		date_info.element("type", "DATE_TYPE_FIX_TIME_RANGE");
		date_info.element("begin_timestamp", 1483724261);
		date_info.element("end_timestamp", 1542724261);

		sku.element("quantity", 100000);
		base_info.element("date_info", date_info);
		base_info.element("sku", sku);
		general_coupon.element("base_info", base_info);
		general_coupon.element("default_detail", "皇嘉財潤"+money+"元优惠卷");
		card.element("general_coupon", general_coupon);
		jsonORG.element("card", card);
		String postData = jsonORG.toString();
		String url = "https://api.weixin.qq.com/card/create?";
		System.out.println(postData);
		

		return wxService.post(url, postData);
	}

	
	

	/*
	 * 创建卡卷卡架url
	 */
	public String creatCardUrl(String card_id) throws IOException, WxErrorException {
		WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);

		JSONObject jsonORG = new JSONObject();
		JSONObject card = new JSONObject();
		JSONArray card_list = new JSONArray();
		
		jsonORG.element("banner", "http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7h");
		jsonORG.element("page_title", "皇家财润优惠卷发放");
		jsonORG.element("can_share",false);
		jsonORG.element("scene", "SCENE_NEAR_BY");
		card.element("card_id", card_id);
		card.element("thumb_url", "www.qq.com/a.jpg");
		card_list.add(card);
		jsonORG.element("card_list", card_list);
		String postData = jsonORG.toString();
		System.out.println(postData);
		String url = "https://api.weixin.qq.com/card/landingpage/create?";
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

	/*
	 * 查询用户卡券
	 */
	public String  queryCode(String code) throws WxErrorException, IOException{
		try{
		WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);
		JSONObject jsonORG = new JSONObject();
		jsonORG.element("code", code);
		String postData = jsonORG.toString();
		String url = "https://api.weixin.qq.com/card/code/get?";
		String returnCode= wxService.post(url, postData);
		System.out.println();
		return returnCode.substring(46, 74);}catch(Exception e){
			return null;
		}
	}
	 

	/*
	 * 获取所有优惠卷类型
	 */
	public List<Voucher> getAllVoucher() {
		return cardImpl.findAll();
	}
	
	
	
	/*
	 * 更新一级代理优惠卷
	 */

	public String updateFirstVoucher(String money) {
		Voucher voucher=cardImpl.findOne(1);
		
		try {
			String code=creatCard(money);
			code=code.substring(38, 66);
			voucher.setVoucherMoney(Integer.parseInt(money));
			voucher.setVoucherCord(code);
			System.out.println(code);
			String url=creatCardUrl(code);
			System.out.println(url+"ddddd");
			JSONObject jsStr = JSONObject.fromObject(url);
			System.out.println(jsStr.get("url"));
			voucher.setVoucherConfirm(jsStr.get("url").toString());
			System.out.println(voucher);
			cardImpl.saveAndFlush(voucher); //更新原有的数据
		}catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	/*
	 * 更新二级代理优惠卷
	 */

	public String updateSecondVoucher(String money) throws IOException, WxErrorException {
		Voucher voucher=cardImpl.findOne(2);
	
			String code=creatCard(money);
			code=code.substring(38, 66);
			voucher.setVoucherMoney(Integer.parseInt(money));
			voucher.setVoucherCord(code);
			System.out.println(code);
			String url=creatCardUrl(code);
			System.out.println(url+"ddddd");
			JSONObject jsStr = JSONObject.fromObject(url);
			System.out.println(jsStr.get("url"));
			voucher.setVoucherConfirm(jsStr.get("url").toString());
			cardImpl.saveAndFlush(voucher); //更新原有的数据
		
		return "success";
	}
	
}




