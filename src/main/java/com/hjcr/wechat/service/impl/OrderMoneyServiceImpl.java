package com.hjcr.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.dao.OrdermoneyDao;
import com.hjcr.wechat.entity.Ordermoney;
import com.hjcr.wechat.service.OrderMoneyService;

@Service("orderMoneyService")
public class OrderMoneyServiceImpl implements OrderMoneyService{

	@Autowired
	private OrdermoneyDao ordermoneyDao;

	// 添加商品分配信息
	public void sava(Ordermoney ordermoney) {
		ordermoneyDao.save(ordermoney);
	}

	// 更新商品分配信息
	public void update(Ordermoney ordermoney) {
		ordermoneyDao.save(ordermoney);
	}

	/*
	 * 删除商品分配信息
	 */
	public void delete(int ordermoneyid) {
		ordermoneyDao.delete(ordermoneyid);
	}

	/*
	 * 获取类别分配比例信息
	 */
	public List<Ordermoney> getOrderMoney() {
		return ordermoneyDao.findAll();
	}
}
