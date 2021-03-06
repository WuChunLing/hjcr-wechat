package com.hjcr.wechat.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.junit.Test;

import com.hjcr.wechat.entity.Template;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class photoJoin {

	/*	*//**
			 * 水印
			 * 
			 * @throws IOException
			 *//*
			 * 
			 * @Test final public BufferedImage photoJoinImage1(File file1, File
			 * file2) throws IOException {
			 * 
			 * BufferedImage image = ImageIO.read(new File("D:\\qr.jpg"));// 二维码
			 * BufferedImage bg = ImageIO.read(new File("D:\\welcome.jpg"));//
			 * 获取北京图片 BufferedImage HeadImg = ImageIO.read(new
			 * File("D:\\2.jpg"));// 获取头像图片 Graphics2D g = bg.createGraphics();
			 * int width = image.getWidth(null) > bg.getWidth() * 5 / 10 ?
			 * (bg.getWidth() * 5 / 10) : image.getWidth(null); int height =
			 * image.getHeight(null) > bg.getHeight() * 50 / 10 ?
			 * (bg.getHeight() * 5 / 10) : image.getWidth(null);
			 * 
			 * g.drawImage(image, width, height, 100, 100, null); g.dispose();
			 * Graphics2D QR = bg.createGraphics(); QR.drawImage(HeadImg, width,
			 * height, 100, 100, null); ImageIO.write(bg, "png", new
			 * File("D:\\10.jpg")); bg.flush(); return bg;
			 * 
			 * }
			 */

	final public InputStream photoJoinImage(Template template, String HeadImgUrl, File file,String path) throws IOException {

		 BufferedImage image= ImageIO.read(file);//二维码
		//BufferedImage image = ImageIO.read(new File("D://14.jpg"));// 测试二维码
		
		 
		 String TemplatePath=template.getTemplatePath();
		 TemplatePath=path+TemplatePath.substring(2);
		 System.out.println(TemplatePath);
		BufferedImage bg = ImageIO.read(new File(TemplatePath));// 获取模板图片
		BufferedImage HeadImg = getBufferedImage(HeadImgUrl);// 获取头像图片
		// BufferedImage HeadImg = ImageIO.read(new File("D://2.jpg"));//
		// 测试获取头像图片

		Graphics2D g = bg.createGraphics();
		int width = image.getWidth(null) > bg.getWidth() * 5 / 10 ? (bg.getWidth() * 5 / 10) : image.getWidth(null);
		int height = image.getHeight(null) > bg.getHeight() * 50 / 10 ? (bg.getHeight() * 5 / 10)
				: image.getWidth(null);

		int size =(int) (bg.getWidth()*template.getTemplateQrcodeSize());
		
		int width1=(int) (bg.getWidth()*template.getTemplateQrcodeWide());
		int high1=(int) (bg.getHeight()*template.getTemplateQrcodeHigh());
		
		g.drawImage(image, width1,high1, size, size, null);// 绘制二维码
		g.dispose();
		bg.flush();
		int weixinsize =(int) (bg.getWidth()*70/450);
		int width2=(int) (bg.getWidth()*template.getTemplateHeadImgWide()-5);
		int high2=(int) (bg.getHeight()*template.getTemplateHeadImgHigh());
		System.out.println(width2+"高"+high2);
		Graphics2D QR = bg.createGraphics();
		QR.drawImage(HeadImg,width2,high2,weixinsize, weixinsize, null);// 绘制微信头像
		 BufferedImage newBufferedImage = new BufferedImage(bg.getWidth(), bg.getHeight(), BufferedImage.TYPE_INT_RGB);  
	        newBufferedImage.createGraphics().drawImage(bg, 0, 0, Color.WHITE, null);  
		File weixinfile = new File("file");
		ImageIO.write(newBufferedImage, "jpg", weixinfile);

		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
		ImageIO.write(newBufferedImage, "jpg", imOut);
		InputStream is = new ByteArrayInputStream(bs.toByteArray());

		return is;

	}

	/*
	 * 根據微信url获取BUfferdImage
	 */
	public BufferedImage getBufferedImage(String imageurl) {

		URL url;
		try {
			url = new URL(imageurl);
			System.out.println(url);
			URLConnection con = url.openConnection();
			// 输入流
			System.out.println(con);
			InputStream is = con.getInputStream();
			BufferedImage ImageBuffered = ImageIO.read(is);
			return ImageBuffered;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
