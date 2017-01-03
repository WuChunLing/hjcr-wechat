package com.hjcr.wechat.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hjcr.wechat.tools.propFactory;

import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import me.chanjar.weixin.mp.bean.result.WxMpUser;



@Service
public class QRcodeService {

	
	
	
	/*
	 * 创建二维码
	 * @author 知鹏
	 */
	public void  QRcodecreat(String openid) throws WxErrorException, IOException{
		
		WxMpInMemoryConfigStorage config =new propFactory().WxMpInMemoryConfigStorageFactory();
		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);
		
		
		//获取用户资料
				String lang = "zh_CN"; //语言
				WxMpUser user = wxService.userInfo(openid, lang);
				String HeadImgUrl= user.getHeadImgUrl();
				String Username=user.getNickname();
				
				//获取二维码
				WxMpQrCodeTicket ticket = wxService.qrCodeCreateTmpTicket(1, 3000000);
				//生成二维码文件
				File file = wxService.qrCodePicture(ticket);
					
				//上传图片
				WxMediaUploadResult res=wxService.mediaUpload("image", file);
				
				
				//WxMediaUploadResult res=wxService.mediaUpload(mediaType, file);
				
				WxMpCustomMessage message = WxMpCustomMessage.IMAGE() .toUser(openid) .mediaId(res.getMediaId()).build();
				try {
					wxService.customMessageSend(message);
				} catch (WxErrorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}
	

	public void	QRcodemessage(String toUserName,String fromUserName) throws WxErrorException, IOException{
		WxMpInMemoryConfigStorage config = new propFactory().WxMpInMemoryConfigStorageFactory();

		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);
		WxMpUser toUser = UserMessage(toUserName);
		
		WxMpUser fromUser = UserMessage(fromUserName);
		
		
		System.out.println(toUser+"fff");
		 WxMpCustomMessage toUserNamemessage = WxMpCustomMessage.TEXT().toUser(toUserName).content("恭喜你成为了"+fromUser.getNickname()+"的会员").build();
		 wxService.customMessageSend(toUserNamemessage);
		
		 WxMpCustomMessage fromUserNamemessage = WxMpCustomMessage.TEXT().toUser(fromUserName).content("恭喜你成为了"+toUser.getNickname()+"的领导").build();
		 wxService.customMessageSend(fromUserNamemessage);
	}
	
	
	
	//获取用户数据
	public WxMpUser	UserMessage(String openid){
		
		WxMpUser user=null;
		WxMpInMemoryConfigStorage config;
		try {
	  config = new propFactory().WxMpInMemoryConfigStorageFactory();

		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);
		
		String lang = "zh_CN"; //语言
			user = wxService.userInfo(openid, lang);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	
	
	//上传图片
	public String uploadPhoto(MultipartFile file,HttpServletRequest request){
		double numble = Math.random() * 100;
		String fileName = file.getOriginalFilename();
		String path=request.getSession().getServletContext().getRealPath("/");
		File imagePath = new File(path + "image/" + numble);
		File imageFile = new File(imagePath, fileName);
		InputStream in;
		try {
			in = file.getInputStream();
			FileOutputStream os = new FileOutputStream(imageFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
		} catch (IOException e) {
			
		}
		
		
		return imagePath+"image/" + numble;
		
	}
	
	}

