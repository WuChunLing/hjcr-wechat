package com.hjcr.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hjcr.wechat.dao.BillDao;
import com.hjcr.wechat.dao.UserDao;
import com.hjcr.wechat.entity.Bill;
import com.hjcr.wechat.entity.DrawMoneyRecord;
import com.hjcr.wechat.entity.DrawMoneyWay;
import com.hjcr.wechat.entity.User;
import com.hjcr.wechat.service.BillService;

@Service("BillService")
public class BillServiceImpl implements BillService {

	@Autowired
	public BillDao billDao;

	@Autowired
	public UserDao userDao;

	public void addBill(Bill bill) {
		billDao.saveAndFlush(bill);
	}

	public void deleteBill(Integer billId) {
		billDao.delete(billId);
	}

	public Page<Bill> getAllBill(Pageable pageable) {
		Page<Bill> page = billDao.findAll(pageable);
		setUserAndWay(page.getContent());
		return page;
	}

	public Bill getBill(Integer BillId) {
		return billDao.findOne(BillId);
	}

	/*
	 * 给返回结果套上用户user
	 */
	private List<Bill> setUserAndWay(List<Bill> list) {
		if (list.size() != 0) {
			User user = new User();
			for (Bill bill : list) {
				user = userDao.findOne(bill.getUserId()); // 添加消费者的user
				bill.setUser(user);
				if (bill.getUserFirstId() == 0) { // 上级代理不存在设置为空
					bill.setUserFirst(null);
				} else {
					user = userDao.findOne(bill.getUserFirstId());
					bill.setUser(user);
				}
				if (bill.getUserSecondId() == 0) { // 上上级代理不存在设置为空
					bill.setUserFirst(null);
				} else {
					user = userDao.findOne(bill.getUserSecondId());
					bill.setUserSecond(user);
				}

			}
		}
		return list;
	}

	/*
	 * 过滤记录信息.
	 */
	private Bill filterInfo(Bill bill) {
		bill.getUser().setHeadImgUrl(null);
		bill.getUser().setUnionID(null);
		bill.getUser().setUserOpenid(null);
		bill.getUser().setUserHierarchy(null);
		return bill;
	}

}
