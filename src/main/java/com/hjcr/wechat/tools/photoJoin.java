package com.hjcr.wechat.tools;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class photoJoin {

	
	/**
	   * 水印
	  * 
	   * @throws IOException
	   */

	

	 final public BufferedImage photoJoinImage(File file1,File file2) throws IOException{
		
		
		BufferedImage image= ImageIO.read(new File("D:\\qr.jpg"));//二维码
		BufferedImage bg= ImageIO.read(new File("D:\\welcome.jpg"));//获取北京图片
		Graphics2D g=bg.createGraphics();
		int width=image.getWidth(null) > bg.getWidth() * 5/10? (bg.getWidth() * 5/10) : image.getWidth(null);
		int height=image.getHeight(null) > bg.getHeight() *50 /10? (bg.getHeight() * 5/10) : image.getWidth(null);
		
		g.drawImage(image,width,height,100,100,null);
		g.dispose();
		bg.flush();
		return bg;
	     
	  }
	  
	  
	 
}


