package com.hjcr.wechat.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hjcr.wechat.entity.Foreverqrcode;
import com.hjcr.wechat.entity.Template;
import com.hjcr.wechat.entity.User;
import com.hjcr.wechat.entity.Voucher;
import com.hjcr.wechat.impl.CardImpl;
import com.hjcr.wechat.impl.ForeverqrcodeImpl;
import com.hjcr.wechat.impl.TemplateImpl;
import com.hjcr.wechat.impl.UserImpl;

import com.hjcr.wechat.tools.photoJoin;
import com.hjcr.wechat.tools.propFactory;

import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import net.sf.json.JSONObject;

@Service
public class QRcodeService {

	@Autowired
	private TemplateImpl templateImpl;

	@Autowired
	private UserImpl userImpl;

	@Autowired
	private CardService cardService;

	@Autowired
	private ForeverqrcodeImpl foreverqrcodeImpl;

	@Autowired
	private CardImpl cardImpl;

	/*
	 * 创建二维码
	 * 
	 * @author 知鹏
	 */
	public void QRcodecreat(String openid) throws IOException, WxErrorException {

		try {
			WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
			WxMpService wxService = new WxMpServiceImpl();
			wxService.setWxMpConfigStorage(config);

			// 获取用户资料
			String lang = "zh_CN"; // 语言
			WxMpUser user = wxService.userInfo(openid, lang);
			String HeadImgUrl = user.getHeadImgUrl();
			String Username = user.getNickname();
			WxMpCustomMessage toUserNamemessage = WxMpCustomMessage.TEXT().toUser(openid).content("正在生成二维码文件请稍等")
					.build();
			wxService.customMessageSend(toUserNamemessage);// 生成二维码前先让用户等待
			// 通过用户openid获取用户id
			User userbyopenid = userImpl.getUserbyOpenid(openid);

			// 获取二维码
			WxMpQrCodeTicket ticket = wxService.qrCodeCreateTmpTicket(userbyopenid.getUserId(), 3000000);
			// 生成二维码文件
			File file = wxService.qrCodePicture(ticket);

			// 获取模板信息
			Template template = templateImpl.getTemplatebyConfirm(1);

			// 拼接图片
			InputStream image = new photoJoin().photoJoinImage(template, HeadImgUrl, file);
			// 上传图片
			WxMediaUploadResult res = wxService.mediaUpload("image", "jpg", image);
			// 发送二维码图片
			WxMpCustomMessage message = WxMpCustomMessage.IMAGE().toUser(openid).mediaId(res.getMediaId()).build();

			wxService.customMessageSend(message);
		} catch (Exception e) {
			// 发送错误信息给用户
			WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
			WxMpService wxService = new WxMpServiceImpl();
			wxService.setWxMpConfigStorage(config);
			WxMpCustomMessage toUserNamemessage = WxMpCustomMessage.TEXT().toUser(openid).content("生成二维码错误，请稍后尝试")
					.build();
			wxService.customMessageSend(toUserNamemessage);// 生成二维码前先让用户等待
		}

	}

	/*
	 * 扫描二维码成功后发送的信息
	 * 
	 * @author 知鹏
	 */
	public void QRcodemessage(String toUserid, String fromUseropenid) throws WxErrorException, IOException {
		WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config); // 获取wxService

		int Userid = Integer.parseInt(toUserid.substring(8));// 将二维码的eventkey的值截取成用户的id
		User User = userImpl.getUserbyuserid(Userid); // 通过UserId获取User
		String toUserOpenid = User.getUserOpenid(); // 获取发送方的openid
		WxMpUser toUser = UserMessage(toUserOpenid); // 获取关注方的User
		WxMpUser fromUser = UserMessage(fromUseropenid);

		// 获取设置代理关系的结果，从而判断下一步行为
		String setHierarchyresult = setUserHierarchy(Userid, fromUseropenid);

		if (setHierarchyresult.equals("HierarchyExist")) {
			// 用戶已經是代理,发送信息告知用户不能重复代理
			WxMpCustomMessage toUserNamemessage = WxMpCustomMessage.TEXT().toUser(fromUseropenid)
					.content("你已经成为代理，不可重复代理").build();
			wxService.customMessageSend(toUserNamemessage);
		} else {
			// 給双方发送通知信息
			WxMpCustomMessage toUserNamemessage = WxMpCustomMessage.TEXT().toUser(fromUseropenid)
					.content("恭喜你成为了" + toUser.getNickname() + "的会员").build();
			wxService.customMessageSend(toUserNamemessage);

			WxMpCustomMessage fromUserNamemessage = WxMpCustomMessage.TEXT().toUser(toUserOpenid)
					.content("恭喜你成为了" + fromUser.getNickname() + "的领导").build();
			wxService.customMessageSend(fromUserNamemessage);
			sendCashMessage(toUserOpenid); // 给发送方送优惠卷

			if (!User.getUserHierarchy().isEmpty()) {

				// 给一级代理发送代金券
				String hierarchy = User.getUserHierarchy();
				String[] st = hierarchy.split("/"); // 获取层级关系
				if (st.length > 2) { // 层级不至于一级
					String hierarchyfirst = st[1]; // 获取一级代理
					int agentUserid = Integer.parseInt(hierarchyfirst);
					// 获取代理UserId
					User agentUser = userImpl.getUserbyuserid(agentUserid);
					sendAgentMessage(agentUser.getUserOpenid(), toUser.getNickname());
				}

				else { // 层级只有一级
					hierarchy = hierarchy.substring(hierarchy.lastIndexOf("/") + 1);// 获取一级代理id
					int agentUserid = Integer.parseInt(hierarchy);
					// 获取代理UserId
					User agentUser = userImpl.getUserbyuserid(agentUserid);
					sendAgentMessage(agentUser.getUserOpenid(), toUser.getNickname());
				}
			}
		}
	}

	/*
	 * s 获取用户数据
	 * 
	 * @author 知鹏
	 */
	public WxMpUser UserMessage(String openid) {

		WxMpUser user = null;
		WxMpInMemoryConfigStorage config;
		try {
			config = new propFactory().WxMpInMemoryConfigStorageFactory();
			WxMpService wxService = new WxMpServiceImpl();
			wxService.setWxMpConfigStorage(config);
			String lang = "zh_CN"; // 语言
			user = wxService.userInfo(openid, lang);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	/*
	 * 上传模板图片
	 * 
	 * @author 知鹏
	 */
	public String uploadPhoto(MultipartFile file, HttpServletRequest request) throws IOException {
		int numble = (int) (Math.random() * 100);
		String fileName = numble + file.getOriginalFilename();
		String path = request.getSession().getServletContext().getRealPath("/");
		File imagePath = new File(path + "image");
		if (!imagePath.exists())
			imagePath.mkdirs(); // 若文件不存在则创建一个
		File imageFile = new File(imagePath + "/", fileName);

		InputStream in;

		in = file.getInputStream();
		FileOutputStream os = new FileOutputStream(imageFile);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = in.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}

		return ".\\" + "image" + "\\" + fileName; // 返回相对路径

	}

	// 获取选择的模板
	public Template getTemplate() {
		return (Template) templateImpl.getTemplatebyConfirm(1);
	}

	/*
	 * 保存用户
	 * 
	 * @author 知鹏
	 */
	public String savaUser(String openid) {
		WxMpUser wxMpUser = UserMessage(openid);
		if (userImpl.getUserbyOpenid(openid) == null) { // 判断数据库是否存在用户
			User savauser = new User();
			savauser.setHeadImgUrl(wxMpUser.getHeadImgUrl());
			savauser.setUserOpenid(wxMpUser.getOpenId());
			savauser.setUserName(wxMpUser.getNickname());
			savauser.setUserForeignkey(0);
			// savauser.setUnionID(wxMpUser.getUnionId());
			System.out.println(savauser);
			userImpl.save(savauser);
		}
		return "success";

	}

	/*
	 * 通过用户openid获取用户id
	 * 
	 * @author 知鹏
	 */
	public User getUserbyopenid(String openid) {
		// 通过用户openid获取用户id
		return userImpl.getUserbyOpenid(openid);
	}

	/*
	 * 设置层级关系
	 * 
	 * @author 知鹏
	 */
	public String setUserHierarchy(int Userid, String fromUseropenid) {
		User touser = userImpl.getUserbyuserid(Userid); // 获取发送者的user
		User fromuser = userImpl.getUserbyOpenid(fromUseropenid);// 获取关注者的user

		// 判断该用户是否有上级了
		if (fromuser.getUserHierarchy() != null) {
			return "HierarchyExist"; // 该用户已经存在代理不能重复被代理
		} else {

			// 判断是否上级没有层级关系
			if (touser.getUserHierarchy() == null) {
				// 没有上级
				String hierarchy = "/" + Userid;// int 转化成String
				fromuser.setUserHierarchy(hierarchy);
				userImpl.saveAndFlush(fromuser);
			} else {
				// 获取所有层级关系
				String touserhierarchy = touser.getUserHierarchy();
				String fromuserhierarchy = "/" + Userid + touserhierarchy;// 倒序的储存层级关系
				fromuser.setUserHierarchy(fromuserhierarchy);
				userImpl.saveAndFlush(fromuser);
			}
		}
		return "success";
	}

	/*
	 * 通过telepphone获取用户永久二维码
	 */
	public String creatForeverQrcode(String telephone, HttpServletRequest request) {

		// 通过电话获取永久二维码
		Foreverqrcode foreverqrcode = foreverqrcodeImpl.getqrcodebyphone(telephone);
		if (foreverqrcode == null) {
			// 该用户之前没有创建过永久二维码
			User user = userImpl.getUserByphone(telephone);
			try {
				// 创建一个永久二维码
				return LastQrcode(user, request);
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
		} else {
			// 用户之前创建过永久二维码
			return foreverqrcode.getQrcodeUrl();
		}

	}

	/*
	 * 生成永久二维码
	 */
	public String LastQrcode(User user, HttpServletRequest request) throws IOException, WxErrorException {

		WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);
		double numble = (Math.random() * 100);
		// 获取二维码
		WxMpQrCodeTicket ticket = wxService.qrCodeCreateLastTicket(user.getUserId());
		// 生成二维码文件
		File file = wxService.qrCodePicture(ticket);

		// 获取模板信息
		Template template = templateImpl.getTemplatebyConfirm(1);

		// 拼接图片
		InputStream in = new photoJoin().photoJoinImage(template, user.getHeadImgUrl(), file);
		// 保存文件
		String path = request.getSession().getServletContext().getRealPath("/");
		File imagePath = new File(path + "qrcodeimage");
		if (!imagePath.exists())
			imagePath.mkdirs();
		File imageFile = new File(imagePath + "\\", "" + numble + ".jpg");

		FileOutputStream os = new FileOutputStream(imageFile);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = in.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}
		// 保存到数据库中
		String qrcodeurl = ".\\qrcodeimage" + "\\" + numble + ".jpg"; // 获取相对路径
		Foreverqrcode foreverqrcode = new Foreverqrcode();
		foreverqrcode.setQrcodeUrl(qrcodeurl);
		foreverqrcode.setUserTelephone(user.getUserMobiphone());
		foreverqrcodeImpl.save(foreverqrcode);
		return qrcodeurl;
	}

	/*
	 * 给二级代理用户发送模板信息
	 */
	public void sendAgentMessage(String openid, String openname) throws IOException, WxErrorException {
		WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);
		Voucher voucher = cardImpl.findOne(2);

		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?";
		JSONObject jsonORG = new JSONObject();
		JSONObject data = new JSONObject();
		JSONObject first = new JSONObject();// 创建符合微信格式的json
		jsonORG.element("touser", openid);
		jsonORG.element("template_id", "kgl2C5_-NeGqpSOaCu7ZyiElipaEQEQhP4pwF1wwInc");
		jsonORG.element("url", voucher.getVoucherConfirm());
		first.element("value", "你的会员" + openname + "发展了一个下线，" + "恭喜你获得一张代金券");
		first.element("color", "#173177");
		data.element("first", first);
		jsonORG.element("data", data);
		wxService.post(url, jsonORG.toString());

	}

	/*
	 * 给发展的用户的代理发送模板信息
	 */
	public void sendCashMessage(String openid) throws IOException, WxErrorException {
		Voucher voucher = cardImpl.findOne(1);

		WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?";
		JSONObject jsonORG = new JSONObject();
		JSONObject data = new JSONObject();
		JSONObject first = new JSONObject(); // 创建符合微信格式的json
		jsonORG.element("touser", openid);
		jsonORG.element("template_id", "kgl2C5_-NeGqpSOaCu7ZyiElipaEQEQhP4pwF1wwInc");
		jsonORG.element("url", voucher.getVoucherConfirm());
		first.element("value", "恭喜你获得一张代金券");
		first.element("color", "#173177");
		data.element("first", first);
		jsonORG.element("data", data);
		wxService.post(url, jsonORG.toString());
	}

}
