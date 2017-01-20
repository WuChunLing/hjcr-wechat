package com.hjcr.wechat.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
			orderMoneyService.sava(ordermoney); // 添加信息
			result.setResultInfo("添加成功");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityException("添加失败");
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
			Map<String, Object> map = new HashMap<String, Object>();//发送list<OrderMoney>
			map.put("orderMoney", orderMoneyService.getOrderMoney());  //获取类别信息
			result.setResultParm(map);
			result.setResultInfo("获取成功");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityException("获取失败");
		}
	}

	/*
	 * 更新类别分配比例信息
	 */
	@RequestMapping(value = "updataOrderMoney", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> updataOrderMoney(@RequestBody Ordermoney ordermoney) {
		ResultMessage result = new ResultMessage();
		try {
			orderMoneyService.update(ordermoney); //更新信息
			result.setResultInfo("更新成功");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityException("更新失败");
		}

	}

	/*
	 * 删除类别分配比例信息
	 */
	@RequestMapping(value = "deteleOrderMoney", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> deteleOrderMoney(@RequestBody Map<String, Object> map) {
		ResultMessage result = new ResultMessage();
		try {
			int orderMoneyId = (int) map.get("orderMoneyId");  //从map中获取orderMoneyId转化成int
			orderMoneyService.delete(orderMoneyId);  //删除信息
			result.setResultInfo("删除成功");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityException("删除失败");
		}
	}

}
