package com.hjcr.wechat.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hjcr.wechat.service.QRcodeService;
import com.hjcr.wechat.tools.ResultMessage;

@Controller
public class ForeverqrcodeHandler {

	@Autowired
	private QRcodeService qRcodeService;

	/*
	 * 获取永久二维码
	 */
	@RequestMapping(value = { "/getlastqrcode" }, produces = MediaType.APPLICATION_JSON_VALUE)
<<<<<<< HEAD
<<<<<<< HEAD
	public ResponseEntity<ResultMessage> getlastqrcode(@RequestBody Map<String,Object> map,
=======
	public ResponseEntity<ResultMessage> getlastqrcode(@RequestBody Map<String, Object> map,
>>>>>>> 1440158e66f78701f46d444388cc003dae3f7ff5
=======

	public ResponseEntity<ResultMessage> getlastqrcode(@RequestBody Map<String, Object> map,
>>>>>>> 3369541ff6d29fea17db742242cdad3d2fb415f0
			HttpServletRequest request) {
		ResultMessage result = new ResultMessage();

		try {
<<<<<<< HEAD
<<<<<<< HEAD
      String telephone=(String) map.get("telephone");
			result.setResultInfo(qRcodeService.creatForeverQrcode(telephone, request));
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK); // 返回一个永久的二维码
=======
			String telephone = (String) map.get("telephone"); // 获取电话号码
			result.setResultInfo(qRcodeService.creatForeverQrcode(telephone, request));// 返回一个永久的二维码
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK); 
>>>>>>> 1440158e66f78701f46d444388cc003dae3f7ff5
=======
			String telephone = (String) map.get("telephone"); // 获取电话号码
			result.setResultInfo(qRcodeService.creatForeverQrcode(telephone, request));// 返回一个永久的二维码
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK); 
>>>>>>> 3369541ff6d29fea17db742242cdad3d2fb415f0

		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityException("获取失败");
		}

	}
}
