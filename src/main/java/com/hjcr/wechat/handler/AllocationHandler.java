package com.hjcr.wechat.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hjcr.wechat.entity.Allocation;
import com.hjcr.wechat.service.AllocationService;
import com.hjcr.wechat.tools.ResultMessage;

@Controller
public class AllocationHandler {

	@Autowired
	private AllocationService allocationService;

	
	/*
	 * 获取分润分配比例信息
	 */
	@RequestMapping(value = "getAllocation", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getAllocation() {
		ResultMessage result = new ResultMessage();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allocation",allocationService.getAllocation());
			result.setResultInfo("success");
			result.setResultParm(map);
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.setServiceResult(false);
			result.setResultInfo("savaerror");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		}
	}

	@ModelAttribute
	public void getupdataAllocation(@RequestParam(value = "allocationId", required = false) Integer allocationId,
			Map<String, Object> map) {
		if (allocationId != null) {
			map.put("allocation", (Object) allocationService.getAllocation());
		}
	}
	
	
	/*
	 * 更新分润分配比例信息
	 */
	@RequestMapping(value = "updataAllocation", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> updataAllocation(Allocation allocation) {
		ResultMessage result = new ResultMessage();
		try {
			allocationService.updataAllocation(allocation);
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
