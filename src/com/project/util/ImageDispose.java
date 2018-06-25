package com.project.util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 产品图片处理
 *@author TDZ
 *@date 2014-11-28
 */
public class ImageDispose {
	
	/** 大图：1024*680 中图：485*322 小图：200*150 */
	
	/** 大图像处理
	 * @param srcImageFile		原文件路径
	 * @param resultFile		新文件存放目录
	 * @param fileImage			新文件名称
	 *   */
	public static void bigPhoto(String srcImageFile,String resultFile,String fileImage) {
		File filePath = new File(new File(resultFile), fileImage); 
		if (!filePath.getParentFile().exists()) { //如果不存在则创建目录
			filePath.getParentFile().mkdirs();
		}
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
			Image image = src.getScaledInstance(1024, 680,Image.SCALE_DEFAULT); // 返回图像的缩放版本。默认的图像缩放算法
			BufferedImage tag = new BufferedImage(1024, 680,BufferedImage.TYPE_INT_RGB); // 预定义一个图像
			Graphics g = tag.getGraphics(); // 返回Graphics，可用于绘制预定义的图像。
			g.drawImage(image, 0, 0, null); // 用图像的缩放版本去绘制缩放后的图
			g.dispose(); // 释放此图形的上下文以及它使用的所有系统资源。调用 dispose 之后，就不能再使用 Graphics
			ImageIO.write(tag, "JPEG", new File(resultFile+""+fileImage)); // 输出到文件流
		} catch (IOException e) {}
	}
	
	/** 中图像处理 
	 * @param srcImageFile		原文件路径
	 * @param resultFile		新文件存放目录
	 * @param fileImage			新文件名称
	 */
	public static void middlePhoto(String srcImageFile,String resultFile,String fileImage) {
		File filePath = new File(new File(resultFile), fileImage); 
		if (!filePath.getParentFile().exists()) { //如果不存在则创建目录
			filePath.getParentFile().mkdirs();
		} 
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile)); 
			Image image = src.getScaledInstance(485, 322,Image.SCALE_DEFAULT); 
			BufferedImage tag = new BufferedImage(485, 322,BufferedImage.TYPE_INT_RGB); 
			Graphics g = tag.getGraphics(); 
			g.drawImage(image, 0, 0, null); 
			g.dispose(); 
			ImageIO.write(tag, "JPEG", new File(resultFile+""+fileImage));
		} catch (IOException e) {}
	}
	
	/**
	 * 小图像处理 
	 * @param srcImageFile		原文件路径
	 * @param resultFile		新文件存放目录
	 * @param fileImage			新文件名称
	 */
	public static void smallPhoto(String srcImageFile,String resultFile,String fileImage) {
		File filePath = new File(new File(resultFile), fileImage); 
		if (!filePath.getParentFile().exists()) { //如果不存在则创建目录
			filePath.getParentFile().mkdirs();
		}
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile)); 
			Image image = src.getScaledInstance(200, 150,Image.SCALE_DEFAULT); 
			BufferedImage tag = new BufferedImage(200, 150,BufferedImage.TYPE_INT_RGB); 
			Graphics g = tag.getGraphics(); 
			g.drawImage(image, 0, 0, null); 
			g.dispose(); 
			ImageIO.write(tag, "JPEG", new File(resultFile+""+fileImage));
		} catch (IOException e) {}
	}
	
	/**
	 * 图片名称,前辍+根据时间戳+后辍(格式)
	 * @param prefixed		前辍
	 * @param postfix		后辍(格式)
	 */
	public static String imageName(String prefixed,String postfix) {
		return prefixed + System.currentTimeMillis() + postfix;
	}
	
	public static void main(String[] args) {
		System.out.println(new SimpleDateFormat("HH:mm:ss SSS").format(new Date()));
//		bigPhoto("E://t1.jpg", "E://t5.jpg","E://");ssssssss
//		centrePhoto("E://t1.jpg", "E://t6.jpg","E://");
//		middlePhoto("E://t1.jpg","E://","t7.jpg");
//		smallPhoto("E://workspace//tennis//webcontent//upload//banner//banner1446695433412.jpg","E://img//","t7.jpg");
//		System.out.println(new SimpleDateFormat("HH:mm:ss SSS").format(new Date()));
		
	        
	}
	
	/**
	 * 逆时针旋转图片90度
	 * @param oldFile 地址
	 */
	public static void rotateImg(String oldFile){
		BufferedImage src;
		try {
			src = ImageIO.read(new File(oldFile));
			BufferedImage des = Rotate(src, 90);  
			ImageIO.write(des, "jpg", new File(oldFile)); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	/**
	 * 直接指定压缩后的宽高： (先保存原文件，再压缩、上传) 壹拍项目中用于二维码压缩
	 * 
	 * @param oldFile
	 *            要进行压缩的文件全路径
	 * @param width
	 *            压缩后的宽度
	 * @param height
	 *            压缩后的高度
	 * @param quality
	 *            压缩质量
	 * @param resultFile		新文件存放目录
	 * @param fileImage			新文件名称
	 *            ).jpg
	 * @return 返回压缩后的文件的全路径
	 */
	public static String zipImageFile(String oldFile, String resultFile,String fileImage, int width, int height,
			float quality) {
		if (oldFile == null) {
			return null;
		}
//		String newImage = null;
		try {
			
           
			/** 对服务器上的临时文件进行处理 */
			Image srcFile = ImageIO.read(new File(oldFile));
			/** 宽,高设定 */
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);
			/** 压缩之后临时存放位置 */
			FileOutputStream out = new FileOutputStream(resultFile+""+fileImage);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
			/** 压缩质量 */
			jep.setQuality(quality, true);
			encoder.encode(tag, jep);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultFile+""+fileImage;
	}
	
	
	public static BufferedImage Rotate(Image src, int angel) {  
        int src_width = src.getWidth(null);  
        int src_height = src.getHeight(null);  
        BufferedImage res = null;  
        if(src_width>src_height){
        	 // calculate the new image size  
            Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(  
                    src_width, src_height)), angel);  
            res = new BufferedImage(rect_des.width, rect_des.height,  
                    BufferedImage.TYPE_INT_RGB);  
            Graphics2D g2 = res.createGraphics();  
            // transform  
            g2.translate((rect_des.width - src_width) / 2,  
                    (rect_des.height - src_height) / 2);  
            g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);  
      
            g2.drawImage(src, null, null);  
           
        }
        return res;  
    }  
  
    public static Rectangle CalcRotatedSize(Rectangle src, int angel) {  
        // if angel is greater than 90 degree, we need to do some conversion  
        if (angel >= 90) {  
            if(angel / 90 % 2 == 1){  
                int temp = src.height;  
                src.height = src.width;  
                src.width = temp;  
            }  
            angel = angel % 90;  
        }  
  
        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;  
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;  
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;  
        double angel_dalta_width = Math.atan((double) src.height / src.width);  
        double angel_dalta_height = Math.atan((double) src.width / src.height);  
  
        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha  
                - angel_dalta_width));  
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha  
                - angel_dalta_height));  
        int des_width = src.width + len_dalta_width * 2;  
        int des_height = src.height + len_dalta_height * 2;  
        return new java.awt.Rectangle(new Dimension(des_width, des_height));  
    }  
}