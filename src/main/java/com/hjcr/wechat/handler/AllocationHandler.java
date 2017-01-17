package com.hjcr.wechat.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
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
			map.put("allocation", allocationService.getAllocation()); // 获取分润信息
			result.setResultInfo("查询成功");
			result.setResultParm(map);
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityException("查询失败");
		}
	}

	/*
	 * @ModelAttribute public void getupdataAllocation(@RequestParam(value =
	 * "allocationId", required = false) Integer allocationId, Map<String,
	 * Object> map) { if (allocationId != null) { map.put("allocation", (Object)
	 * allocationService.getAllocation()); } }
	 */
	/*
	 * 更新一级代理分润分配比例信息
	 */
	@RequestMapping(value = "updatafirstAllocation", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> updatafirstAllocation(@RequestBody Map<String, Object> map) {
		ResultMessage result = new ResultMessage();
		try {
			float orderMoneyFirst = (float) map.get("orderMoneyFirst");  //从map中获取orderMoneyId转化成int
			//allocationService.updataAllocation(allocation); // 更新分润信息
			float orderMoneySecond=1-orderMoneyFirst;
			Allocation allocation=allocationService.getAllocation();
			allocation.setOrderMoneyFirst(orderMoneyFirst);
			allocation.setOrderMoneySecond(orderMoneySecond);
			allocationService.updataAllocation(allocation); // 更新分润信息
			result.setResultInfo("更新成功");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityException("更新失败");
		}
	}

	@RequestMapping(value = "updatasecondAllocation", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> updatasecondAllocation(@RequestBody Map<String, Object> map) {
		ResultMessage result = new ResultMessage();
		try {
			float orderMoneySecond = (float) map.get("orderMoneySecond");  //从map中获取orderMoneyId转化成int
			//allocationService.updataAllocation(allocation); // 更新分润信息
			float orderMoneyFirst=1-orderMoneySecond;
			Allocation allocation=allocationService.getAllocation();
			allocation.setOrderMoneyFirst(orderMoneyFirst);
			allocation.setOrderMoneySecond(orderMoneySecond);
			allocationService.updataAllocation(allocation); // 更新分润信息
			result.setResultInfo("更新成功");
			return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityException("更新失败");
		}
	}
	
	
}
