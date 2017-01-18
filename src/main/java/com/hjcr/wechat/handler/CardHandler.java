package com.hjcr.wechat.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjcr.wechat.entity.Voucher;
import com.hjcr.wechat.service.CardService;
import com.hjcr.wechat.tools.ResultMessage;

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
	@RequestMapping(value = { "/sendCard" })
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
	@RequestMapping(value = { "/deleteCard" })
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
	public ResponseEntity<ResultMessage> getAllVoucher() {
		ResultMessage result = new ResultMessage();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allVoucher",cardService.getAllVoucher()); //获取所有模板信息
			result.setResultParm(map);
			result.setResultInfo("获取成功"); 
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityException("获取失败"); 
		}
		
	}

	/*
	 * 更新一级代理优惠卷
	 */
	@ResponseBody
	@RequestMapping("updateFirstVoucher")
	public ResponseEntity<ResultMessage> updateFirstVoucher(@RequestBody Map<String, Object> Vouchermap) {
		String money=(String) Vouchermap.get("money");
		ResultMessage result = new ResultMessage();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			cardService.updateFirstVoucher(money); 
			result.setResultParm(map);
			result.setResultInfo("获取成功"); 
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityException("获取失败"); 
		}
	}

	/*
	 * 更新二级代理优惠卷
	 */
	@ResponseBody
	@RequestMapping("updateSecondVoucher")
	public ResponseEntity<ResultMessage> updateSecondVoucher(@RequestBody Map<String, Object> Vouchermap) {
			String money=(String) Vouchermap.get("money");
			ResultMessage result = new ResultMessage();
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				cardService.updateSecondVoucher(money); 
				result.setResultParm(map);
				result.setResultInfo("获取成功"); 
				return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				throw new SecurityException("获取失败"); 
			}
	}

}
