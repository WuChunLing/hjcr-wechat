package com.hjcr.wechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.entity.DrawMoneyWay;
import com.hjcr.wechat.impl.DrawMoneyWayImpl;

@Service(value = "drawMoneyWayService")
public class DrawMoneyWayService {

	@Autowired
	private DrawMoneyWayImpl drawMoneyWayImpl;

	/*
	 * 获取所有提现方式.
	 */
	public List<DrawMoneyWay> getAll() {
		return drawMoneyWayImpl.findAll();
	}

	/*
	 * 添加新的提现方式
	 */
	public DrawMoneyWay addWay(DrawMoneyWay way) {
		return drawMoneyWayImpl.save(way);
	}

	/*
	 * 删除提现方式
	 */
	public boolean delete(Integer id) {
		try {
			drawMoneyWayImpl.delete(id);
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
		DrawMoneyWay flush = drawMoneyWayImpl.saveAndFlush(way);
		return flush.getId() == null ? false:true;
	}
	
	
}
