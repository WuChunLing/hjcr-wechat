package com.hjcr.wechat.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcr.wechat.dao.DrawMoneyRecordDao;
import com.hjcr.wechat.dao.UserDao;
import com.hjcr.wechat.entity.DrawMoneyRecord;
import com.hjcr.wechat.entity.User;
import com.hjcr.wechat.service.DrawMoneyRecordService;


@Service("drawMoneyRecordService")
public class DrawMoneyRecordServiceImpl implements DrawMoneyRecordService{
	
	private final Logger logger = LoggerFactory.getLogger(DrawMoneyRecordServiceImpl.class);

	@Autowired
	private DrawMoneyRecordDao drawMoneyRecordDao;
	
	@Autowired
	private UserDao userImpl;

	/*
	 * 获取所有提现记录
	 */
	public List<DrawMoneyRecord> getAll() {
		return drawMoneyRecordDao.findAll();
	}

	/*
	 * 添加新的提现记录.
	 */
	@Transactional
	public DrawMoneyRecord addRecord(DrawMoneyRecord record) {
		User user = userImpl.findOne(record.getUserId());
		logger.info("user'balance :" + user.getBalance());
		if (user.getBalance() < record.getMoney()) {
			throw new SecurityException("用户余额不足");
		} else {
			user.setBalance(user.getBalance() - record.getMoney());
			User flush = userImpl.saveAndFlush(user);
			logger.info("After draw money user'blance :" + flush.getBalance());
			record.setStatus(1);//设置提现记录为 “待审核”
			return drawMoneyRecordDao.save(record);
		}
	}

	/*
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

	/*
	 * 更新状态
	 */
	public boolean update(DrawMoneyRecord record) {
		DrawMoneyRecord flush = drawMoneyRecordDao.saveAndFlush(record);
		return flush.getId() == null ? false:true;
	}

	
	
}
