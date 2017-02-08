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

import com.hjcr.wechat.entity.DrawMoneyWay;
import com.hjcr.wechat.service.DrawMoneyWayService;
import com.hjcr.wechat.tools.ResultMessage;

/*
 * 体现方式管理控制器
 */

@Controller
@RequestMapping(value = "/drawway")
public class DrawMoneyWayHandler {
	
	@Autowired
	private DrawMoneyWayService drawMoneyWayService;
	
	private final Logger log = LoggerFactory.getLogger(DrawMoneyWayHandler.class);

	/**
	 * 获取所有提现方式.(测试接口)
	 * @author Kellan
	 * @return
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getAll() {
		log.info("获取所有提现方式");
		ResultMessage result = new ResultMessage();
		List<DrawMoneyWay> list = drawMoneyWayService.getAll();
		result.getResultParm().put("list", list);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 获取某用户的默认提现方式
	 * @author Kellan
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getDafultWayByUserId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getDafultWayByUserId(Integer userId) {
		log.info("获取某用户的默认提现方式getDafultWayByUserId");
		if (userId == null) {
			throw new SecurityException("数据有误");
		}
		ResultMessage result = new ResultMessage();
		DrawMoneyWay way = drawMoneyWayService.getDafultWayByUserId(userId);
		result.getResultParm().put("way",way);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 获取某用户所有提现方式
	 * @author Kellan
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getUserWay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getUserWay(@RequestBody Integer userId) {
		log.info("获取某用户所有提现方式getUserWay");
		if (userId == null) {
			throw new SecurityException("数据有误");
		}
		ResultMessage result = new ResultMessage();
		List<DrawMoneyWay> list = drawMoneyWayService.getUserWay(userId);
		result.getResultParm().put("list",list);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 添加新的提现方式.
	 * @author Kellan
	 * @param way
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> add(@RequestBody DrawMoneyWay way) {
		log.info("保存新的提现方式");
		if (way.getUserId() == null) {
			throw new SecurityException("数据有误");
		}
		ResultMessage result = new ResultMessage();
		way = drawMoneyWayService.addWay(way);
		if (way.getId() == null) {
			throw new SecurityException("添加失败");
		}
		result.setResultInfo("添加成功");
		result.getResultParm().put("way", way);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 根据id删除提现方式
	 * @author Kellan
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> delete(@RequestBody Integer id) {
		log.info("根据id删除提现方式");
		if (id == null) {
			throw new SecurityException("数据有误");
		}
		ResultMessage result = new ResultMessage();
		if (!drawMoneyWayService.delete(id)) {
			throw new SecurityException("删除失败");
		}
		result.setResultInfo("删除成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 更改提现方式
	 * @author Kellan
	 * @param way
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> update(@RequestBody DrawMoneyWay way) {
		log.info("更新提现方式");
		if (way.getId() == null) {
			throw new SecurityException("数据有误");
		}
		ResultMessage result = new ResultMessage();
		if (!drawMoneyWayService.update(way)) {
			throw new SecurityException("更新失败");
		}
		result.setResultInfo("更新成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	

}
