package com.hjcr.wechat.handler;

import java.awt.Menu;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hjcr.wechat.dao.UserDao;
import com.hjcr.wechat.entity.Allocation;
import com.hjcr.wechat.entity.Ordermoney;
import com.hjcr.wechat.entity.Template;
import com.hjcr.wechat.entity.User;
import com.hjcr.wechat.entity.Voucher;
import com.hjcr.wechat.service.AllocationService;
import com.hjcr.wechat.service.CardService;
import com.hjcr.wechat.service.OrderMoneyService;
import com.hjcr.wechat.service.QRcodeService;
import com.hjcr.wechat.service.TemplateService;
import com.hjcr.wechat.service.UserService;
import com.hjcr.wechat.tools.ResultMessage;
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
import net.sf.json.JSONObject;

@Controller
public class Handler extends GenericController {

	@Autowired
	private UserDao user;

	protected WxMpMessageRouter wxMpMessageRouter;

	@Autowired
	private QRcodeService qRcodeService;

	@Autowired
	private TemplateService templateService;

	@Autowired
	private UserService wxuserService;

	@Autowired
	private CardService cardService;

	@Autowired
	private OrderMoneyService orderMoneyService;

	@Autowired
	private AllocationService allocationService;

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
		String echoStr = request.getParameter("echostr");       //微信验证信息

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
				avoidRepetition(response); // 微信三次验证，排重
				qRcodeService.savaUser(inMessage.getFromUserName());
			}
			if (inMessage.getEvent().equals("CLICK") && inMessage.getEventKey().equals("0")) {
				// 生成二维码且发送给用户
				String path=request.getSession().getServletContext().getRealPath("");//获取当前项目路径
				avoidRepetition(response);// 微信三次验证，排重
				qRcodeService.QRcodecreat(inMessage.getFromUserName(),path);
			}

			if (inMessage.getEventKey() != null && inMessage.getEventKey().indexOf("qrscene") != -1) {
				// 当用户扫描二维码未关注的动作
				avoidRepetition(response);// 微信三次验证，排重
				qRcodeService.QRcodemessage(inMessage.getEventKey(), inMessage.getFromUserName());

			} else if (inMessage.getEventKey() != null && inMessage.getEventKey().indexOf("qrscene") == -1) {
				// 当用户扫描二维码已关注的动作
			}
			return;
		}

	}

	/*
	 * 创建菜单
	 */
	@RequestMapping(value = { "/createMenu" }, produces = { "application/json;charset=UTF-8" })
	public void createMenu() throws WxErrorException, IOException {
		new wxMenu().getMenu();

	}

	/*
	 * 查看个人信息
	 */
	@RequestMapping(value = { "/getUserInfortation" })
	public String getUserInfortation(@RequestParam("code") String code) throws WxErrorException, IOException {
		WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code);
		WxMpUser wxMpUser = wxService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
		String openid = wxMpUser.getOpenId();
		String result = wxuserService.getUserInfortation(openid);
		if (result.equals("success")) {
			return "";
		}
		return "";
	}

	@RequestMapping(value = { "/Test" }, produces = { "application/json;charset=UTF-8" })
	public void Test(HttpServletRequest request) throws WxErrorException, IOException {

	/*	String path=request.getSession().getServletContext().getRealPath("");
		String TemplatePath=".\\image\64welcome.jpg";
		 TemplatePath=TemplatePath.replaceFirst(".\\", path);
		 System.out.println(TemplatePath);
		String TemplatePath=".\\image\\64welcome.jpg";
		System.out.println(path+TemplatePath.substring(2));*/
		//System.out.println(cardService.sendCard("oUPl-wh3UehzmwaztIQzkvcuS3rE"));
		System.out.println(wxuserService.getfAllUser(44));
	}

	/*
	 * 防止微信三次发送信息，排重
	 */
	public void avoidRepetition(HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.print("");
		out.close(); // 防止微信三次发送信息，排重
	}

}
