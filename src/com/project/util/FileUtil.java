package com.project.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import com.project.common.Settings;

public class FileUtil {
	static final Pattern RANGE_PATTERN = Pattern.compile("bytes \\d+-\\d+/\\d+");

	/**
	 * 根据ID获取文件路径
	 *
	 * @return
	 */
	public static String path() {
		String key = "www.straw-soft.com";
		String hash = SecurityUtil.MD5(key + "\t" + System.currentTimeMillis()
				+ "\t" + String.valueOf(System.currentTimeMillis()).length()
				+ "\t" + System.currentTimeMillis() % 10);
		String uuid = UUID.randomUUID().toString();
		hash += uuid.substring(uuid.length()-8,uuid.length());
		hash = hash.substring(hash.length() - 24, hash.length());
		return hash;
	}

	/**
	 * 根据ID获取文件路径
	 * 
	 * @param id
	 * @return
	 */
	public static String path(int id) {
		String key = "www.straw-soft.com";
		String hash = MD5.getMD5(key + "\t" + id + "\t"
				+ String.valueOf(id).length() + "\t" + id % 10);
		String t = hash.substring(id % 32, id % 32 + 1);
		String path = t + "/" + Math.abs(MD5.getCRC32(hash) % 100);
		return path;
	}

	/**
	 * 获取为文件生成唯一路径
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName) {
		String exc = fileName.substring(fileName.lastIndexOf("."),
				fileName.length());
		String filename = path();
		return filename + exc;
	}

//	public static String getAuthImagePath(String authtype, int uid) {
//		return Settings.IMAGE_SAVE_FILE_NAME+"/image/" + authtype + "/" + path(uid);
//	}

	/**
	 * 删除指定文件夹下的所有文件
	 * 
	 * @param filepath
	 * @return
	 */
	public static boolean delFileInfo(String filepath) {
		try {
			File file = new File(filepath);
			if (file.exists()) {
				if (file.isDirectory()) {
					File[] filelist = file.listFiles();
					for (File fileinfo : filelist) {
						delFileInfo(fileinfo.getAbsolutePath());
					}
				} else {
					file.delete();
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 删除文件夹
	// param folderPath 文件夹完整绝对路径

	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 如果不存在该目录，则创建
	 * 
	 * @param filepath
	 */
	public static void creatIfNotExsit(String filepath) {
		File file = new File(filepath);
		if (file.isAbsolute() && !file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 判断该目录或者文件是否存在
	 * 
	 * @param filepath
	 * @return
	 */
	public static boolean isExsit(String filepath) {
		File file = new File(filepath);
		if (file.exists())
			return true;
		return false;
	}

	/**
	 * 列出指定目录下的所有文件
	 * 
	 * @param fliepath
	 * @return 日期：Sep 5, 2012
	 */
	public String[] fileList(String fliepath) {
		String[] myList = null;
		try {
			File path = new File(fliepath);// 定义一个File对象
			// 定义一个字符串数组
			myList = path.list();
			for (int i = 0; i < myList.length; i++)// 输出文件列表
			{
				System.out.println(myList[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myList;
	}

	public static boolean saveToFile(String str, String filepath,
			String encoding) {
		InputStream bis = null;
		try {
			if (encoding == null || "".equals(encoding)) {
				bis = new ByteArrayInputStream(str.getBytes("GBK"));
			} else {
				bis = new ByteArrayInputStream(str.getBytes(encoding));
			}
			OutputStream os = new FileOutputStream(filepath);
			IOUtils.copy(bis, os);
			bis.close();
			os.close();
			System.out.println("保存文件:" + filepath);
			return true;
		} catch (IOException e) {
			System.out.println("保存文件失败:" + filepath);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 检查文件是否合法
	 * 
	 * @param fileName  文件名称
	 * @param extendesc 适配文件类型 jpg....
	 * 使用方式：FileUtil.checkFile(file.getOriginalFilename(), ".jpg", ".jpeg",".png")
	 * @return
	 */
	public static boolean checkFile(String fileName, String... extendesc) {
		if (fileName.contains(".")) {
			String exc = fileName.substring(fileName.lastIndexOf("."),
					fileName.length());
			for (String exten : extendesc) {
				if (exc.toLowerCase().equals(exten)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 返回唯一的文件名
	 * @param str
	 * @return
	 */
	public static String fileRename(String str) {

		String formatDate = new SimpleDateFormat("yyMMddHHmmssSSS")
				.format(new Date());
		int last = str.lastIndexOf(".");
		int i = (int) (Math.random() * 1000);

		String str_type = str.substring(last);

		str = formatDate + i + str_type;
		return str;
	}
}
