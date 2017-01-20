package com.hjcr.wechat.service;

import java.io.IOException;
import java.util.List;

import com.hjcr.wechat.entity.Voucher;

import me.chanjar.weixin.common.exception.WxErrorException;

public interface CardService {

	/*
	 * 创建一级代理的卡卷
	 */
	public String creatfiftyCard() throws IOException, WxErrorException;

	/*
	 * 创建一个30元的卡卷
	 */
	public String creatthirtyCard() throws IOException, WxErrorException;

	/*
	 * 创建自定义卡卷
	 */
	public String creatCard(String money) throws IOException, WxErrorException;
	/*
	 * 创建卡卷卡架url
	 */
	public String creatCardUrl(String card_id) throws IOException, WxErrorException;

	/*
	 * 发送卡卷给微信用户
	 */
	public String sendCard(String touseropenid) throws IOException, WxErrorException;

	/*
	 * 删除用户卡卷
	 */
	public String deleteCard(String code) throws IOException, WxErrorException;

	/*
	 * 查询用户卡券
	 */
	public String queryCode(String code) throws WxErrorException, IOException;

	/*
	 * 获取所有优惠卷类型
	 */
	public List<Voucher> getAllVoucher();

	/*
	 * 更新一级代理优惠卷
	 */

	public String updateFirstVoucher(String money);

	/*
	 * 更新二级代理优惠卷
	 */

	public String updateSecondVoucher(String money) throws IOException, WxErrorException;

}
