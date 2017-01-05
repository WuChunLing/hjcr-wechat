package com.hjcr.wechat.handler;

import java.awt.Menu;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hjcr.wechat.entity.Template;
import com.hjcr.wechat.impl.UserImpl;

import com.hjcr.wechat.service.QRcodeService;
import com.hjcr.wechat.service.TemplateService;
import com.hjcr.wechat.tools.photoJoin;
import com.hjcr.wechat.tools.propFactory;
import com.hjcr.wechat.tools.wxMenu;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.StringUtils;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Controller
public class Handler extends GenericController {

	@Autowired
	private UserImpl user;

	protected WxMpMessageRouter wxMpMessageRouter;

	@Autowired
	private QRcodeService QRcodeService;

	@Autowired
	private TemplateService templateService;

	// 和微信沟通的主程序
	@RequestMapping(value = { "/WxMessageRouter" }, produces = { "application/json;charset=UTF-8" })
	public void WxMessageRouter(HttpServletRequest request, HttpServletResponse response)
			throws WxErrorException, IOException {
		System.out.println("fuzhipeng");
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);

		String signature = request.getParameter("signature");
		String nonce = request.getParameter("nonce");
		String timestamp = request.getParameter("timestamp");
		System.out.println("fuzhipeng");
		String echoStr = request.getParameter("echostr");

		if (StringUtils.isNotBlank(echoStr)) {
			// 说明是一个仅仅用来验证的请求，回显echostr
			String echoStrOut = String.copyValueOf(echoStr.toCharArray());
			response.getWriter().println(echoStrOut);
			return;
		}
		String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ? "raw"
				: request.getParameter("encrypt_type");

		// 获取wxService
		WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);

		// 判断是否是明文
		if ("raw".equals(encryptType)) {
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
			String aa = inMessage.getContent();

			if (inMessage.getEvent().equals("subscribe")) {
				// 用户订阅公众号保存信息
				QRcodeService.savaUser(inMessage.getFromUserName());
			}
			if (inMessage.getEventKey() != null && inMessage.getEventKey().indexOf("qrscene") != -1) {
				// 当用户扫描二维码未关注的动作

				QRcodeService.QRcodemessage(inMessage.getEventKey(), inMessage.getFromUserName());

			} else if (inMessage.getEventKey() != null && inMessage.getEventKey().indexOf("qrscene") == -1) {
				// 当用户扫描二维码已关注的动作
			}
			return;
		}

	}

	// 添加模板
	@ResponseBody
	@RequestMapping("/addTemplate")
	public String addTemplate(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile file, Template template) throws ServletException {
		System.out.println("aa");
		String path;
		try {
			path = QRcodeService.uploadPhoto(file, request);
			template.setTemplatePath(path);
			templateService.addTemplate(template);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

		return "success";

	}

	// 获取跳转链接
	/*
	 * @RequestMapping(value = { "/getcode" }, produces = {
	 * "application/json;charset=UTF-8" }) public String
	 * getcode(HttpServletRequest request, HttpServletResponse response) throws
	 * WxErrorException, IOException { WxMpInMemoryConfigStorage config = new
	 * propFactory().WxMpInMemoryConfigStorageFactory(); WxMpService wxService =
	 * new WxMpServiceImpl(); wxService.setWxMpConfigStorage(config); return
	 * "redirect:/" + wxService.oauth2buildAuthorizationUrl("getuser",
	 * WxConsts.OAUTH2_SCOPE_USER_INFO, null); }
	 */
	/*
	 * 获取二维码
	 */
	@RequestMapping(value = { "/getqrcode" })
	public void getuser(@RequestParam("code") String code) throws WxErrorException, IOException {
		WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code);
		WxMpUser wxMpUser = wxService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
		String openid = wxMpUser.getOpenId();

		// 生成二维码且发送给用户
		QRcodeService.QRcodecreat(openid);

	}

	/*
	 * 创建菜单
	 */
	@RequestMapping(value = { "/createMenu" }, produces = { "application/json;charset=UTF-8" })
	public void createMenu() throws WxErrorException, IOException {
		System.out.println("ddd");

		new wxMenu().getMenu();

	}

	@RequestMapping(value = { "/Test" }, produces = { "application/json;charset=UTF-8" })
	public void Test() throws WxErrorException, IOException {

		String openid = "oUPl-wh3UehzmwaztIQzkvcuS3rE";

		// 生成二维码且发送给用户
		QRcodeService.savaUser(openid);

	}

	@RequestMapping(value = { "/Url" }, produces = { "application/json;charset=UTF-8" })
	public void Url() throws WxErrorException, IOException {
		WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);
		System.out.println(wxService.oauth2buildAuthorizationUrl("http://fuzhi.ngrok.cc/hjcr-wechat/getuser",
				WxConsts.OAUTH2_SCOPE_USER_INFO, null));

	}
}
