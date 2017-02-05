package com.hjcr.wechat.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hjcr.wechat.entity.Bill;
import com.hjcr.wechat.entity.DrawMoneyRecord;
import com.hjcr.wechat.service.BillService;
import com.hjcr.wechat.tools.ResultMessage;

@Controller
@RequestMapping(value = "/bill")
public class BillHandler {

	@Autowired
	private BillService billService;
	
	
	public ResponseEntity<ResultMessage> addBillHandler() {
		ResultMessage result = new ResultMessage();
		return null;
	}

	/*
	 * 
	 */
	@RequestMapping(value = "/getAllBill")
	public ResponseEntity<Page<Bill>> getAllBill(@RequestParam(value="currentPage", defaultValue="0")Integer currentPage, @RequestParam(value="size", defaultValue="10")Integer size) {
		ResultMessage result = new ResultMessage();
		Sort sort = new Sort(Direction.DESC,"billDate");
		Pageable pageable = new PageRequest(currentPage,size,sort);
		Page<Bill> list = billService.getAllBill(pageable);
		result.getResultParm().put("list", list);
		return new ResponseEntity<Page<Bill>>(list, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/getBillbyid")
	public ResponseEntity<ResultMessage> getBillbyid(@RequestParam(value="billId")Integer billId) {
		System.out.println("dff");
		ResultMessage result = new ResultMessage();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allocation", billService.getBill(billId)); // 获取分润信息
		result.setResultInfo("查询成功");
		result.setResultParm(map);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
}
