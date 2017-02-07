package com.hjcr.wechat.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

	private final Logger log = LoggerFactory.getLogger(DrawMoneyRecordHandler.class);

	/*
	 * 添加分润订单信息
	 */
	@RequestMapping(value = "/addBill", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> addBill(int billId, Float billMoney, String unionId, int OrderMoneyId) {
		log.info("添加分润订单信息");
		ResultMessage result = new ResultMessage();
		billService.addBill(billId, billMoney, unionId, OrderMoneyId);
		result.setResultInfo("添加成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	
	/*
	 * 获取所有的订单
	 */
	@RequestMapping(value = "/getAllBill", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getAllBill(
			@RequestParam(value = "currentPage", defaultValue = "0") Integer currentPage,
			@RequestParam(value = "currentPage", defaultValue = "5") Integer size,String startDate, String endDate) {
		log.info("获取所有订单");

		ResultMessage result = new ResultMessage();
		Sort sort = new Sort(Direction.DESC, "billDate");
		Pageable pageable = new PageRequest(currentPage, size, sort);
		Page<Bill> page = billService.getAllBill(pageable,startDate, endDate);
		result.getResultParm().put("list", page.getContent());
		result.getResultParm().put("totalPages", page.getTotalPages());
		result.getResultParm().put("currentPage", page.getNumber());
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	
	/*
	 * 通过订单号获取订单
	 */
	@RequestMapping(value = "/getBillbyId")
	public ResponseEntity<ResultMessage> getBillbyId(@RequestParam(value = "billId") Integer billId) {
		log.info("通过订单号获取订单信息");
		ResultMessage result = new ResultMessage();
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<Bill> list=new ArrayList<Bill>();
		list.add(billService.getBill(billId));
		result.getResultParm().put("list",list);
		result.setResultInfo("查询成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 获取订单的消费额度和利润.
	 */
	@RequestMapping(value = "/getSumConsume", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getSumConsume(String startDate, String endDate) {
		log.info("获取订单消费的总额度");
		ResultMessage result = new ResultMessage();
		Double total = billService.getSumConsume(startDate, endDate);
		Double SumFeeSplittingtotal = billService.getSumFeeSplitting(startDate, endDate);
		result.getResultParm().put("total", total);
		result.getResultParm().put("SumFeeSplittingtotal", SumFeeSplittingtotal);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	
	
	/*
	 * 通过用户获取所有的订单
	 */
	@RequestMapping(value = "/getBillbyUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getBillbyUser(
			@RequestParam(value = "currentPage", defaultValue = "0") Integer currentPage,
			@RequestParam(value = "currentPage", defaultValue = "5") Integer size, String startDate, String endDate,Integer UserId) {
		log.info("通过用户获取所有的订单");

		ResultMessage result = new ResultMessage();
		Sort sort = new Sort(Direction.DESC, "billDate");
		Pageable pageable = new PageRequest(currentPage, size, sort);
		Page<Bill> page = billService.getBillByUser(pageable, startDate, endDate,UserId);
		result.getResultParm().put("list", page.getContent());
		result.getResultParm().put("totalPages", page.getTotalPages());
		result.getResultParm().put("currentPage", page.getNumber());
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
}
