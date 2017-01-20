package com.hjcr.wechat.service;

import java.util.List;

import com.hjcr.wechat.entity.Ordermoney;

public interface OrderMoneyService {

	// 添加商品分配信息
	public void sava(Ordermoney ordermoney);

	// 更新商品分配信息
	public void update(Ordermoney ordermoney);

	/*
	 * 删除商品分配信息
	 */
	public void delete(int ordermoneyid);

	/*
	 * 获取类别分配比例信息
	 */
	public List<Ordermoney> getOrderMoney();
}
