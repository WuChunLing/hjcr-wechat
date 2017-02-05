package com.hjcr.wechat.handler;

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

import com.hjcr.wechat.entity.DrawMoneyRecord;
import com.hjcr.wechat.service.DrawMoneyRecordService;
import com.hjcr.wechat.tools.ResultMessage;

/*
 * 体现记录管理控制器
 */

@Controller
@RequestMapping(value = "/drawrecord")
public class DrawMoneyRecordHandler {
	
	@Autowired
	private DrawMoneyRecordService drawMoneyRecordService;
	

	
	private final Logger log = LoggerFactory.getLogger(DrawMoneyRecordHandler.class);

	/**
	 * 获取所有提现记录(测试)
	 * @author Kellan
	 * @return
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<DrawMoneyRecord>> getAll(Integer currentPage, Integer size) {
		log.info("获取所有提现记录");
		ResultMessage result = new ResultMessage();
		Sort sort = new Sort(Direction.DESC,"creatTime");
		Pageable pageable = new PageRequest(currentPage,size,sort);
		Page<DrawMoneyRecord> list = drawMoneyRecordService.getAll(pageable);
		result.getResultParm().put("list", list);
		return new ResponseEntity<Page<DrawMoneyRecord>>(list, HttpStatus.OK);
	}
	
	
	
	/**
	 * 获取用户自身提现账单.
	 * @author Kellan
	 * @return
	 */
	@RequestMapping(value = "/getByUserId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getByUserId(Integer userId, Integer currentPage, Integer size) {
		log.info("获取用户自身提现账单");
		ResultMessage result = new ResultMessage();
		Sort sort = new Sort(Direction.DESC, "creatTime");
		Pageable pageable = new PageRequest(currentPage, size, sort);
		Page<DrawMoneyRecord> list = drawMoneyRecordService.getByUserId(pageable,userId);
		result.getResultParm().put("list", list);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 根据状态获取提现账单.
	 * @author Kellan
	 * @return
	 */
	@RequestMapping(value = "/getByStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getByStatus(Integer status, Integer currentPage, Integer size, String startDate, String endDate) {
		log.info("获取用户自身提现账单");
		ResultMessage result = new ResultMessage();
		Sort sort = new Sort(Direction.DESC, "creatTime");
		Pageable pageable = new PageRequest(currentPage, size, sort);
		Page<DrawMoneyRecord> list = drawMoneyRecordService.getByStatus(pageable,status,startDate,endDate);
		result.getResultParm().put("list", list);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 获取各个状态的总额度.
	 * @author Kellan
	 * @return
	 */
	@RequestMapping(value = "/getStatusTotal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getStatusTotal(Integer status, String startDate, String endDate) {
		log.info("获取各个状态的总额度");
		ResultMessage result = new ResultMessage();
		if (status == null) {
			throw new SecurityException("数据有误");
		}
		Double total = drawMoneyRecordService.getStatusTotal(status,startDate,endDate);
		result.getResultParm().put("total",total);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 添加新的提现申请.
	 * @author Kellan
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> add(@RequestBody DrawMoneyRecord record) {
		log.info("保存新的提现申请");
		if (record.getUserId() == null) {
			throw new SecurityException("数据有误");
		}
		ResultMessage result = new ResultMessage();
		record = drawMoneyRecordService.addRecord(record);
		if (record.getId() == null) {
			throw new SecurityException("添加失败");
		}
		result.setResultInfo("添加成功");
		result.getResultParm().put("record", record);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 根据id删除提现记录
	 * @author Kellan
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> delete(@RequestBody Integer id) {
		log.info("根据id删除提现记录");
		if (id == null) {
			throw new SecurityException("数据有误");
		}
		ResultMessage result = new ResultMessage();
		if (!drawMoneyRecordService.delete(id)) {
			throw new SecurityException("删除失败");
		}
		result.setResultInfo("删除成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 更改提现记录状态
	 * @author Kellan
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> update(@RequestBody DrawMoneyRecord record) {
		log.info("更新提现记录状态");
		if (record.getId() == null || record.getStatus() == null) {
			throw new SecurityException("数据有误");
		}
		ResultMessage result = new ResultMessage();
		if (!drawMoneyRecordService.update(record.getId(),record.getStatus())) {
			throw new SecurityException("更新失败");
		}
		result.setResultInfo("更新成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	

}
