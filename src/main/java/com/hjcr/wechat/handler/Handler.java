package com.hjcr.wechat.handler;

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
import com.hjcr.wechat.impl.User;
import com.hjcr.wechat.service.CoreService;
import com.hjcr.wechat.service.QRcodeService;
import com.hjcr.wechat.service.WxMpQrcodeService;

import com.hjcr.wechat.tools.photoJoin;
import com.hjcr.wechat.tools.propFactory;

import me.chanjar.weixin.common.api.WxConsts;
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
public class Handler extends GenericController{

	
	@Autowired
	private User user;
	
	protected WxMpMessageRouter wxMpMessageRouter;
	
	
	@Autowired
	private QRcodeService QRcodeService;
	
	 
	@RequestMapping(value = { "/Test" }, produces = { "application/json;charset=UTF-8" })
	public void Test() throws WxErrorException, IOException {
		
		
		String openid = "oUPl-wh3UehzmwaztIQzkvcuS3rE";
		
		//生成二维码且发送给用户
		QRcodeService.QRcodecreat(openid);
		
		
	}
	
	
	
	//和微信沟通的主程序
	@RequestMapping(value = { "/WxMessageRouter" }, produces = { "application/json;charset=UTF-8" })
	public void WxMessageRouter(HttpServletRequest request, HttpServletResponse response) throws WxErrorException, IOException {
		response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
		
        String echoStr = request.getParameter("echostr");
        if (StringUtils.isNotBlank(echoStr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            String echoStrOut = String.copyValueOf(echoStr.toCharArray());
            response.getWriter().println(echoStrOut);
            return;
        }
        String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type"))
                ? "raw"
                : request.getParameter("encrypt_type");
        
        WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();
		
        
        //判断是否是明文
 	 if ("raw".equals(encryptType)) {
	       
			 WxMpService wxService = new WxMpServiceImpl();
				wxService.setWxMpConfigStorage(config);
				
				
				 WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
				 String aa=inMessage.getContent();
				   if(inMessage.getEventKey()!=null&&inMessage.getEventKey().equals("qrscene_1")){
					 System.out.println(inMessage.getEventKey()+"fuzipeng");
					 //当用户扫描二维码的动作
					 
					 QRcodeService.QRcodemessage("oUPl-whRdCbCywOXjTZPdOOl4p-s","oUPl-wh3UehzmwaztIQzkvcuS3rE");
				 } else  if(inMessage.getEventKey()!=null&&inMessage.getEventKey().equals("1")){}
				
				
			      return;
	        }
		
	}

	//上传文件
	@ResponseBody
	@RequestMapping("/upload")
	public String upload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile file,Template template) throws ServletException {
		
		String path=QRcodeService.uploadPhoto(file, request);
		template.setTemplatePath(path);
		
		return "success";
		
	}
}
