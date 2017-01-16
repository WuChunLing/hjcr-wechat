package com.hjcr.wechat.handler;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjcr.wechat.entity.Allocation;
import com.hjcr.wechat.entity.Ordermoney;
import com.hjcr.wechat.service.OrderMoneyService;
import com.hjcr.wechat.tools.ResultMessage;

@Controller
public class OrdermoneyHandler {

	@Autowired
	private OrderMoneyService orderMoneyService;

	
	/*
	 * 添加类别分配比例信息
	 */
	@RequestMapping(value = "addOrderMoney", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> addOrderMoney(@RequestBody Ordermoney ordermoney) {
		ResultMessage result = new ResultMessage();
		try {
			orderMoneyService.sava(ordermoney);
			result.setResultInfo("success");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.setServiceResult(false);
			result.setResultInfo("savaerror");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		}

	}

	/*
	 * 获取类别分配比例信息
	 */
	@ResponseBody
	@RequestMapping(value = "getOrderMoney", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getOrderMoney() {
		ResultMessage result = new ResultMessage();
		try {
			result.setResultInfo("success");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderMoney", orderMoneyService.getOrderMoney());
			result.setResultParm(map);
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.setServiceResult(false);
			result.setResultInfo("savaerror");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		}
	}

	/*
	 * 更新类别分配比例信息
	 */
	@RequestMapping(value = "updataOrderMoney", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> updataOrderMoney(@RequestBody Ordermoney ordermoney) {
		ResultMessage result = new ResultMessage();
		try {
			orderMoneyService.update(ordermoney);
			result.setResultInfo("success");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.setServiceResult(false);
			result.setResultInfo("savaerror");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		}

	}

	/*
	 * 删除类别分配比例信息
	 */
	@RequestMapping(value = "deteleOrderMoney", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> deteleOrderMoney(@RequestBody Map<String,Object> map) {
		ResultMessage result = new ResultMessage();
		try {
			 
			int orderMoneyId= (int) map.get("orderMoneyId");
			orderMoneyService.delete(orderMoneyId);
			result.setResultInfo("success");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.setServiceResult(false);
			result.setResultInfo("savaerror");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		}
	}

	
	
	
}
