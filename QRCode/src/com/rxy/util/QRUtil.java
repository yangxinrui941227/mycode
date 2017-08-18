package com.rxy.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class QRUtil {
	
	public static void getImage(int width, int height, String content, String path, String logo){
		Qrcode qrCode = new Qrcode();
		//设置二维码拍错率
		qrCode.setQrcodeErrorCorrect('M');
		//设置二维码版本大小
		qrCode.setQrcodeVersion(15);
		//设置二维码编码集
		qrCode.setQrcodeEncodeMode('B');
		
		 
		
		BufferedImage bufImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = bufImage.createGraphics();
		graphics.setBackground(Color.WHITE);
		graphics.setColor(Color.BLACK);
		graphics.clearRect(0, 0, width, height);
		
		try {
			
			boolean[][] calQrcode = qrCode.calQrcode(content.getBytes("utf-8"));
			int offset = width%calQrcode.length/2;
			int len = width/calQrcode.length;
			for (int i = 0; i < calQrcode.length; i++){
				for (int j = 0; j < calQrcode.length; j++){
					if (calQrcode[j][i])
					graphics.fillRect(i*len+offset,j*len+offset, len,len);
				}
				
			}
			if (logo!=null && !"".equals(logo)){
				
				BufferedImage read = ImageIO.read(new File(logo));
				graphics.drawImage(read, (width-50)/2,(height-50)/2,50,50,null);
					
			}
			graphics.dispose();
			bufImage.flush();
			
			ImageIO.write(bufImage, "png", new File(path));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	public static void main(String[] args) {
		QRUtil.getImage(200, 200, "test", "f:/1.png", "f:/logo.gif");
		
	}
}
