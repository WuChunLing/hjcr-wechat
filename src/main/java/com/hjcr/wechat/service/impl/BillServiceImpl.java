package com.hjcr.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcr.wechat.dao.AllocationDao;
import com.hjcr.wechat.dao.BillDao;
import com.hjcr.wechat.dao.OrdermoneyDao;
import com.hjcr.wechat.dao.UserDao;
import com.hjcr.wechat.entity.Allocation;
import com.hjcr.wechat.entity.Bill;
import com.hjcr.wechat.entity.DrawMoneyRecord;
import com.hjcr.wechat.entity.DrawMoneyWay;
import com.hjcr.wechat.entity.Ordermoney;
import com.hjcr.wechat.entity.User;
import com.hjcr.wechat.service.BillService;
import com.hjcr.wechat.service.UserService;

@Service("BillService")
public class BillServiceImpl implements BillService {

	@Autowired
	public BillDao billDao;

	@Autowired
	public UserDao userDao;

	@Autowired
	public OrdermoneyDao ordermoneyDao;

	@Autowired
	public AllocationDao allocationDao;

	@Autowired
	public UserService userService;

	@Transactional
	public void addBill(int billId, Float billMoney, String unionId, int OrderMoneyId) {
		Bill bill = new Bill();
		User cumstomuser = userDao.getUserbyUnionId(unionId);
		User firstUser = userService.getFirstUser(cumstomuser.getUserId());// 获取一级代理
		User secondUser = userService.getSecondUser(cumstomuser.getUserId());// 获取二级代理
		Ordermoney orderMoney = ordermoneyDao.findOne(OrderMoneyId);
		Float billProfit = billMoney * orderMoney.getOrderMoneyDistribution();
		Allocation allocation = allocationDao.findAll().get(0);
		if (firstUser != null) {
			bill.setUserFirstId(firstUser.getUserId());
			bill.setUserFirst(firstUser);
			Float firstProfit = (float) (billProfit * allocation.getOrderMoneyFirst());
			userService.setUserBalance(firstUser.getUserId(), firstProfit);
			bill.setUserFirstProfit(firstProfit);
		}
		if (secondUser != null) {
			bill.setUserSecondId(secondUser.getUserId());
			bill.setUserSecond(secondUser);
			Float secondProfit = (float) (billProfit * allocation.getOrderMoneySecond());
			userService.setUserBalance(secondUser.getUserId(), secondProfit);
			bill.setUserSecondProfit(secondProfit);
		}
		bill.setUserId(cumstomuser.getUserId());
		bill.setBillId(billId);
		bill.setTypeid(OrderMoneyId);
		bill.setBillMoney(billMoney);
		bill.setBillProfit(billProfit);
		billDao.saveAndFlush(bill);
	}

	public void deleteBill(Integer billId) {
		billDao.delete(billId);
	}

	public Page<Bill> getAllBill(Pageable pageable, String startDate, String endDate) {
		Page<Bill> page = null;
		if (startDate != null && endDate != null) {
			System.out.println("fuzhipneh");
			page = billDao.findByStatus(pageable, startDate, endDate);
		} else {
			page = billDao.findAll(pageable);
		}
		if (page != null)
			setUser(page.getContent());
		return page;
	}

	public Bill getBill(Integer BillId) {
		Bill bill = billDao.findByBillId(BillId);
		setUser(bill);
		return bill;
	}

	/*
	 * 给返回结果套上用户user
	 */
	private List<Bill> setUser(List<Bill> list) {
		if (list.size() != 0) {
			User user = new User();
			for (Bill bill : list) {
				user = userDao.findOne(bill.getUserId()); // 添加消费者的user
				bill.setUser(user);
				if (bill.getUserFirstId() == 0) { // 上级代理不存在设置为空
					bill.setUserFirst(null);
				} else {
					user = userDao.findOne(bill.getUserFirstId());
					bill.setUserFirst(user);
				}
				if (bill.getUserSecondId() == 0) { // 上上级代理不存在设置为空
					bill.setUserSecond(null);
				} else {
					user = userDao.findOne(bill.getUserSecondId());
					bill.setUserSecond(user);
					billFilterInfo(bill);
				}

			}
		}
		return list;
	}

	/*
	 * 给返回结果套上用户user
	 */
	private Bill setUser(Bill bill) {

		User user = new User();

		user = userDao.findOne(bill.getUserId()); // 添加消费者的user
		bill.setUser(user);
		if (bill.getUserFirstId() == 0) { // 上级代理不存在设置为空
			bill.setUserFirst(null);
		} else {
			user = userDao.findOne(bill.getUserFirstId());
			bill.setUserFirst(user);
		}
		if (bill.getUserSecondId() == 0) { // 上上级代理不存在设置为空
			bill.setUserSecond(null);
		} else {
			user = userDao.findOne(bill.getUserSecondId());
			bill.setUserSecond(user);
		}
		billFilterInfo(bill);
		return bill;

	}

	/*
	 * 过滤用户记录信息.
	 */
	private User UserFilterInfo(User user) {
		user.setHeadImgUrl(null);
		user.setUnionId(null);
		user.setUserOpenid(null);
		user.setUserHierarchy(null);
		return user;
	}

	/*
	 * 过滤订单记录信息.
	 */
	private Bill billFilterInfo(Bill bill) {
		UserFilterInfo(bill.getUser());
		if (bill.getUserFirst() != null) {
			UserFilterInfo(bill.getUserFirst());
		}
		if (bill.getUserSecond() != null) {
			UserFilterInfo(bill.getUserSecond());
		}
		return bill;
	}

	/*
	 * 获取订单的消费总额度.
	 */
	public Double getSumConsume(String startDate, String endDate) {
		if (startDate != null & endDate != null)
			return billDao.getSumConsume(startDate, endDate);// 获取订单某个时间段的消费总金额
		else
			return billDao.getSumConsume(); // 获取订单的消费总金额

	}

	@Override
	public Double getSumFeeSplitting(String startDate, String endDate) {
		if (startDate != null & endDate != null)
			return billDao.getSumFeeSplitting(startDate, endDate);// 获取某个状态某个时间段的总金额
		else
			return billDao.getSumFeeSplitting(); // 获取某个状态的总金额
	}

	public Page<Bill> getBillByUser(Pageable pageable, String startDate, String endDate, Integer UserId) {
		Page<Bill> page = null;
		if (startDate != null && endDate != null) {
			System.out.println("fuzhipneh");
			page = billDao.getBillByUser(pageable, startDate, endDate, UserId);
		} else {
			page = billDao.getBillByUser(pageable, UserId);
		}
		if (page != null)
			setUser(page.getContent());
		return page;
	}

}
