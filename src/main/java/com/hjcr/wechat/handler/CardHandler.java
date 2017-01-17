package com.hjcr.wechat.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjcr.wechat.entity.Voucher;
import com.hjcr.wechat.service.CardService;

@Controller
public class CardHandler {

	@Autowired
	private CardService cardService;

	// 创建50元卡卷
	@RequestMapping(value = { "/creatfiftyCard" })
	public String creatCard() {
		try {
			System.out.println(cardService.creatthirtyCard());
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	/*
	 * 发送卡卷给用户
	 */
	public String sendCard(String touseropenid) {
		try {
			cardService.sendCard(touseropenid);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	/*
	 * 删除卡卷
	 */
	public String deleteCard(String code) {
		try {
			cardService.deleteCard(code);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";

	}

	/*
	 * 获取所有优惠卷类型
	 */
	@ResponseBody
	@RequestMapping("getAllVoucher")
	public List<Voucher> getAllVoucher() {
		return cardService.getAllVoucher();
	}

	/*
	 * 更新一级代理优惠卷
	 */
	@ResponseBody
	@RequestMapping("updateFirstVoucher")
	public String updateFirstVoucher(@RequestParam("money") String money) {
		cardService.updateFirstVoucher(money);
		return "success";
	}

	/*
	 * 更新二级代理优惠卷
	 */
	@ResponseBody
	@RequestMapping("updateSecondVoucher")
	public String updateSecondVoucher(@RequestParam("money") String money) {
		try {
			cardService.updateSecondVoucher(money);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

}
