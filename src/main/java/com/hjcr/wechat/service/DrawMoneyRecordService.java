package com.hjcr.wechat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hjcr.wechat.entity.DrawMoneyRecord;

public interface DrawMoneyRecordService {

	/*
	 * 获取所有提现记录
	 */
	public Page<DrawMoneyRecord> getAll(Pageable pageable);

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
	public boolean update(Integer id, Integer status);

	public Page<DrawMoneyRecord> getByUserId(Pageable pageable, Integer userId);

}
