package com.hjcr.wechat.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcr.wechat.dao.DrawMoneyRecordDao;
import com.hjcr.wechat.dao.DrawMoneyWayDao;
import com.hjcr.wechat.dao.UserDao;
import com.hjcr.wechat.entity.DrawMoneyRecord;
import com.hjcr.wechat.entity.DrawMoneyWay;
import com.hjcr.wechat.entity.User;
import com.hjcr.wechat.service.DrawMoneyRecordService;

@Service("drawMoneyRecordService")
public class DrawMoneyRecordServiceImpl implements DrawMoneyRecordService {

	private final Logger logger = LoggerFactory
			.getLogger(DrawMoneyRecordServiceImpl.class);

	@Autowired
	private DrawMoneyRecordDao drawMoneyRecordDao;

	@Autowired
	private UserDao userDao;
	@Autowired
	private DrawMoneyWayDao drawMoneyWayDao;

	/**
	 * 获取所有提现记录
	 */
	public Page<DrawMoneyRecord> getAll(Pageable pageable) {
		Page<DrawMoneyRecord> page = drawMoneyRecordDao.findAll(pageable);
		setUserAndWay(page.getContent());
		// System.out.println(page.getContent()+"dddd");
		return page;
	}

	/**
	 * 获取某用户记录
	 */
	public Page<DrawMoneyRecord> getByUserId(Pageable pageable,
			Integer userId) {
		Page<DrawMoneyRecord> page = drawMoneyRecordDao.findByUserId(pageable,
				userId);
		setUserAndWay(page.getContent());
		return page;
	}

	@Override
	public Page<DrawMoneyRecord> getByStatus(Pageable pageable,
			Integer status) {
		Page<DrawMoneyRecord> page = drawMoneyRecordDao.findByStatus(pageable,
				status);
		setUserAndWay(page.getContent());
		return page;
	}

	/*
	 * 添加新的提现记录.
	 */
	@Transactional
	public DrawMoneyRecord addRecord(DrawMoneyRecord record) {
		User user = userDao.findOne(record.getUserId());
		logger.info("user'balance :" + user.getBalance());
		if (user.getBalance() < record.getMoney()) {
			throw new SecurityException("用户余额不足");
		} else {
			user.setBalance(user.getBalance() - record.getMoney());
			User flush = userDao.saveAndFlush(user);
			logger.info("After draw money user'blance :" + flush.getBalance());
			record.setStatus(1);// 设置提现记录为 “待审核”
			return drawMoneyRecordDao.save(record);
		}
	}

	/**
	 * 更新状态
	 */
	@Transactional
	public boolean update(Integer recordId, Integer status) {
		DrawMoneyRecord record = drawMoneyRecordDao.findOne(recordId);
		if (record == null) {
			throw new SecurityException("无法更新该记录");
		}

		if (status == 3) { // 拒绝提现
			User user = userDao.findOne(record.getUserId());
			user.setBalance(user.getBalance() + record.getMoney());
			userDao.saveAndFlush(user);
		}
		record.setStatus(status);
		DrawMoneyRecord flush = drawMoneyRecordDao.saveAndFlush(record);

		if (flush.getId() == null)
			throw new SecurityException("更新失败");
		else
			return true;
	}

	/*
	 * 获取各个状态的总额度.
	 */
	public Double getStatusTotal(Integer status, String startDate, String endDate) {
		if (startDate != null & endDate != null) 
			return drawMoneyRecordDao.getSumByStatus(status,startDate,endDate);//获取某个状态某个时间段的总金额
		else 
			return drawMoneyRecordDao.getSumByStatus(status); //获取某个状态的总金额
		
	}

	/*
	 * 给返回结果套上用户user和提现方式way的信息
	 */
	private List<DrawMoneyRecord> setUserAndWay(List<DrawMoneyRecord> list) {
		if (list.size() != 0) {
			User user = new User();
			DrawMoneyWay way = new DrawMoneyWay();
			for (DrawMoneyRecord record : list) {
				user = userDao.findOne(record.getUserId());
				way = drawMoneyWayDao.findOne(record.getWayId());
				record.setUser(user);
				record.setDrawMoneyWay(way);
				record = filterInfo(record);
			}
		}
		return list;
	}

	/*
	 * 过滤记录信息.
	 */
	private DrawMoneyRecord filterInfo(DrawMoneyRecord record) {
		record.getUser().setHeadImgUrl(null);
		record.getUser().setUnionID(null);
		record.getUser().setUserOpenid(null);
		record.getUser().setUserHierarchy(null);
		record.getDrawMoneyWay().setCreatTime(null);
		return record;
	}

	/**
	 * 删除
	 */
	public boolean delete(Integer id) {
		try {
			drawMoneyRecordDao.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
