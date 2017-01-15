package com.hjcr.wechat.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
	public ResponseEntity<ResultMessage> getlastqrcode(@RequestParam("telephone") String telephone,
			HttpServletRequest request) {
		ResultMessage result = new ResultMessage();

		try {

			result.setResultInfo(qRcodeService.creatForeverQrcode(telephone, request));
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK); // 返回一个永久的二维码

		} catch (Exception e) {
			e.printStackTrace();
			result.setServiceResult(false);
			result.setResultInfo("error");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		}

	}
}
