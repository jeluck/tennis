package com.project.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileOpera {
	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 *            文件名
	 */
	public static String readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		try {
			//System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				//System.out.println("line " + line + ": " + tempString);
				line++;
				sb.append(tempString+ "\r\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return sb.toString();
	}
	
	 /**
     * Write the content to a file.
     * */
	/***
	 * 把content写入到路径path文件里
	 * @param content	内容
	 * @param path		文件路径
	 */
    public static void writeFile(String content, String path){
    	 try {
    		   File file = new File(path);
    		   // if file doesnt exists, then create it
    		   if (!file.exists()) {
    		    file.createNewFile();
    		   }

    		   //FileWriter fw = new FileWriter(file.getAbsoluteFile());
    		   
    		   FileOutputStream fos = new FileOutputStream(content);
    		   OutputStreamWriter fw = new OutputStreamWriter(fos,"UTF-8");
    		   BufferedWriter bw = new BufferedWriter(fw);
    		   bw.write(content);
    		   bw.close();

    		   //System.out.println("Done");

    		  } catch (IOException e) {
    		   e.printStackTrace();
    		  }
    }
}
