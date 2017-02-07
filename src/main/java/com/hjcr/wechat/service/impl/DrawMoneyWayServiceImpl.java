package com.hjcr.wechat.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.dao.DrawMoneyWayDao;
import com.hjcr.wechat.entity.DrawMoneyWay;
import com.hjcr.wechat.service.DrawMoneyWayService;

@Service("drawMoneyWayService")
public class DrawMoneyWayServiceImpl implements DrawMoneyWayService{

	@Autowired
	private DrawMoneyWayDao drawMoneyWayDao;

	/*
	 * 获取所有提现方式.
	 */
	public List<DrawMoneyWay> getAll() {
		return drawMoneyWayDao.findAll();
	}

	/*
	 * 添加新的提现方式
	 */
	public DrawMoneyWay addWay(DrawMoneyWay way) {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = d.format(new Date());
		way.setCreatTime(date);
		return drawMoneyWayDao.save(way);
	}

	/*
	 * 删除提现方式
	 */
	public boolean delete(Integer id) {
		try {
			drawMoneyWayDao.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * 更新
	 */
	public boolean update(DrawMoneyWay way) {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = d.format(new Date());
		way.setCreatTime(date);
		DrawMoneyWay flush = drawMoneyWayDao.saveAndFlush(way);
		return flush.getId() == null ? false:true;
	}

	/*
	 * 获取某用户的默认提现方式
	 */
	public DrawMoneyWay getDafultWayByUserId(Integer userId) {
		return drawMoneyWayDao.getDafultWayByUserId(userId);
	}

	/*
	 * 获取某用户所有提现方式
	 */
	public List<DrawMoneyWay> getUserWay(Integer userId) {
		return drawMoneyWayDao.getUserWay(userId);
	}
	
	
}
