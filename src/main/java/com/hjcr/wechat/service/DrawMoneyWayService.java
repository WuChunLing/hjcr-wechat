package com.hjcr.wechat.service;

import java.util.List;

import com.hjcr.wechat.entity.DrawMoneyWay;

public interface DrawMoneyWayService {

	/*
	 * 获取所有提现方式.
	 */
	public List<DrawMoneyWay> getAll();

	/*
	 * 添加新的提现方式
	 */
	public DrawMoneyWay addWay(DrawMoneyWay way);

	/*
	 * 删除提现方式
	 */
	public boolean delete(Integer id);

	/*
	 * 更新
	 */
	public boolean update(DrawMoneyWay way);

	/*
	 * 获取某用户的默认提现方式
	 */
	public DrawMoneyWay getDafultWayByUserId(Integer userId);

	/*
	 * 获取某用户所有提现方式
	 */
	public List<DrawMoneyWay> getUserWay(Integer userId);
}