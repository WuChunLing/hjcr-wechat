package com.hjcr.wechat.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.hjcr.wechat.entity.Template;
import com.hjcr.wechat.entity.User;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

public interface QRcodeService {

	/*
	 * 创建二维码
	 *
	 * @author 知鹏
	 */
	public void QRcodecreat(String openid,String path) throws IOException, WxErrorException;

	/*
	 * 扫描二维码成功后发送的信息
	 *
	 * @author 知鹏
	 */
	public void QRcodemessage(String toUserid, String fromUseropenid) throws WxErrorException, IOException;

	/*
	 * s 获取用户数据
	 *
	 * @author 知鹏
	 */
	public WxMpUser UserMessage(String openid);

	/*
	 * 上传模板图片
	 *
	 * @author 知鹏
	 */
	public String uploadPhoto(MultipartFile file, HttpServletRequest request) throws IOException;

	// 获取选择的模板
	public Template getTemplate();

	/*
	 * 保存用户
	 *
	 * @author 知鹏
	 */
	public String savaUser(String openid);

	/*
	 * 通过用户openid获取用户id
	 *
	 * @author 知鹏
	 */
	public User getUserbyopenid(String openid);

	/*
	 * 设置层级关系
	 *
	 * @author 知鹏
	 */
	public String setUserHierarchy(int Userid, String fromUseropenid);

	/*
	 * 通过telepphone获取用户永久二维码
	 */
	public String creatForeverQrcode(String telephone, HttpServletRequest request);

	/*
	 * 生成永久二维码
	 */
	public String LastQrcode(User user, HttpServletRequest request) throws IOException, WxErrorException;
	/*
	 * 给二级代理用户发送模板信息
	 */
	public void sendAgentMessage(String openid, String openname) throws IOException, WxErrorException;

	/*
	 * 给发展的用户的代理发送模板信息
	 */
	public void sendCashMessage(String openid) throws IOException, WxErrorException;

}
