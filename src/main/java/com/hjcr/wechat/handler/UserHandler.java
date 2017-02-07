package com.hjcr.wechat.handler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hjcr.wechat.service.BillService;
import com.hjcr.wechat.service.UserService;
import com.hjcr.wechat.tools.ResultMessage;

@Controller
@RequestMapping(value = "/user")
public class UserHandler {

	@Autowired
	private UserService userService;

	private final Logger log = LoggerFactory.getLogger(DrawMoneyRecordHandler.class);
	
	/*
	 * 通过订单号获取订单
	 */
	@RequestMapping(value = "/getUser")
	public ResponseEntity<ResultMessage> getUser(@RequestParam(value = "userId") Integer userId) {
		log.info("获取用户信息");
		ResultMessage result = new ResultMessage();
		Map<String, Object> map = new HashMap<String, Object>();
		result.getResultParm().put("list", userService.findOne(userId));
		result.setResultInfo("查询成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

}
