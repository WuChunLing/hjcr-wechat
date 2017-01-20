package com.hjcr.wechat.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<ResultMessage> getAll() {
		log.info("获取所有提现记录");
		ResultMessage result = new ResultMessage();
		List<DrawMoneyRecord> list = drawMoneyRecordService.getAll();
		result.getResultParm().put("list", list);
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
		if (!drawMoneyRecordService.update(record)) {
			throw new SecurityException("更新失败");
		}
		result.setResultInfo("更新成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	

}
