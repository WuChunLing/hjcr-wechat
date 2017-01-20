package com.hjcr.wechat.service;

import java.util.List;

import com.hjcr.wechat.entity.DrawMoneyRecord;

public interface DrawMoneyRecordService {

	/*
	 * 获取所有提现记录
	 */
	public List<DrawMoneyRecord> getAll();

	/*
	 * 添加新的提现记录.
	 */
	public DrawMoneyRecord addRecord(DrawMoneyRecord record);

	/*
	 * 删除
	 */
	public boolean delete(Integer id);

	/*
	 * 更新状态
	 */
	public boolean update(DrawMoneyRecord record);

	
	
}
