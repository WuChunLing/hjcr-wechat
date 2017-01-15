package com.hjcr.wechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjcr.wechat.entity.Ordermoney;
import com.hjcr.wechat.impl.OrdermoneyImpl;

@Service
public class OrderMoneyService {

	@Autowired
	private OrdermoneyImpl ordermoneyImpl;

	
	// 添加商品分配信息
	public String sava(Ordermoney ordermoney) {
		System.out.println(ordermoney);
		ordermoneyImpl.save(ordermoney);
		return "success";
	}

	// 更新商品分配信息
	public String update(Ordermoney ordermoney) {

		ordermoneyImpl.save(ordermoney);
		return "success";
	}

	/*
	 * 删除商品分配信息
	 */
	public String delete(int ordermoneyid) {
		ordermoneyImpl.delete(ordermoneyid);
		return "success";
	}
	

	/*
	 * 获取类别分配比例信息
	 */
	@ResponseBody
	@RequestMapping("getOrderMoney")
	public List<Ordermoney> getOrderMoney() {
		return ordermoneyImpl.findAll();
		
	}
}
