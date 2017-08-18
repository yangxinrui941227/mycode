package com.rxy.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRZXingUtil {
	
	/**
	 * 生成png格式的二维码
	 * @param width
	 * @param height
	 * @param content
	 * @param path
	 */
	public static void getQRImage(int width,int height,String content,String path){
		Hashtable<Object, Object> hints = new Hashtable<Object, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.M);
		hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
		
		try {
			BitMatrix bit = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			
			MatrixToImageWriter.writeToFile(bit, "png", new File(path));
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据二维码获取内容
	 * @param path
	 */
	public static String getTextByImage(String path){
		
		Result result = null;
		
		File file = new File(path);
		if (!file.exists()){
			System.out.println("file not found");
			return "";
		}
		try {
			BufferedImage read = ImageIO.read(file);
			
			LuminanceSource lu = new BufferedImageLuminanceSource(read);
			
			   BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(lu));     
			    
	            Hashtable<Object, Object> hints = new Hashtable<Object, Object>();     
	            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");     
	    
	            result = new MultiFormatReader().decode(bitmap, hints);     
	            return result.getText();   
	            
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		
		return "";
	}
	public static void main(String[] args) {
		
		QRZXingUtil.getQRImage(400, 400, "https://www.baidu.com/s?ie=utf-8&f=3&rsv_bp=1&rsv_idx=1&ch=12&tn=98012088_5_dg&wd=zxing%20jar&oq=zxing&rsv_pq=f94e101c00004357&rsv_t=7d298bUnHXn9IKDJjJkYw4I7pXwkTcwBIDbW6ArVL7uKEdRjxy2rrU9%2BcbDXRf2AzHb0yw&rqlang=cn&rsv_enter=0&rsv_sug3=21&rsv_sug1=8&rsv_sug7=100&rsv_sug2=1&prefixsug=zxing&rsp=2&rsv_sug4=2450", "f:/a.png");
		System.out.println(QRZXingUtil.getTextByImage("f:/a.png"));
	}
}
