package com.project.util;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class CodeUtil {
	/**
	 * 根据给定的验证码长度，生成一组对应长度的随机数
	 * 
	 * @param codelength
	 *            验证码的长度
	 * @return 生成的验证码字符串
	 */
	public static String genVerifyCode(int codelength) {
		char[] myCodeChar = { '6', '9', '4', '1', '7', '3', '8', '2', '5' };
		String checkCode = "";
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < codelength; i++)
			checkCode += myCodeChar[random.nextInt(myCodeChar.length)];
		return checkCode;
	}
	/**
	 * 产生随机验证码
	 * 
	 * @param codelength
	 *            验证码的长度
	 * @return
	 */
	public static String getVerifyCode(int codelength) {
		char[] myCodeChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
				'Y', 'Z' };
		StringBuffer checkCode = new StringBuffer();
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < codelength; i++)
			checkCode.append(myCodeChar[random.nextInt(myCodeChar.length)]);
		return checkCode.toString();
	}
	public static String getTimeNow() {
		DateFormat format1 = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return format1.format(new Date());
	}
	/**
	 * 根据给定的密码长度，生成一组对应长度的随机数
	 * 
	 * @param codelength
	 *            密码的长度
	 * @return 生成的密码字符串
	 */
	public static String genPassword(int codelength) {
		char[] myCodeChar = { 'l', 'm', 'n', '6', 'o', 'p', 'q', 'k', '9', 'j',
				'i', 'g', 'r', 's', 'u', '4', 'h', 'v', 'w', 'z', 'x', '1',
				'y', 'e', '7', 'd', 'f', '3', 'c', '8', '2', '5', 'a', 'b' };
		String checkCode = "";
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < codelength; i++)
			checkCode += myCodeChar[random.nextInt(myCodeChar.length)];
		return checkCode;
	}
	/**
	 * 产生混乱的唯一的邮箱激活码
	 * 
	 * @param codelength
	 *            验证码的长度
	 * @return
	 */
	public static String getEmailVerifyCode(int codelength) {
		char[] myCodeChar = { '0','/','|', '1', '2', '3', '4', '5','S', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E','R','/','|','_','S', 'F', 'G', 'H','/','|', 'I', 'J', 'K', 'L',
				'M', 'N', 'O', 'P','/','|', 'Q', 'R','S', 'T', 'U', 'V', 'W', 'X',
				'Y', 'Z','l', 'm', 'n', '6', 'o', 'p', 'q', 'k', '9', 'j',
				'i', 'g', 'r', 's', 'u','/','|','_','S','4', 'h', 'v', 'w', 'z', 'x', '1',
				'y', 'e', '7', 'd', 'f','/','|','_','S', '3', 'c', '8', '2', '5', 'a', 'b'  };
		StringBuffer checkCode = new StringBuffer();
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < codelength; i++){
			checkCode.append(myCodeChar[random.nextInt(myCodeChar.length)]);
		}
		String verify = checkCode.toString()+UUID.randomUUID();
		return verify.substring(16,verify.length()).replaceAll("-", "");
	}
	
	
	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
			"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
			"t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" };

	/***
	 * 生成8位随机数
	 * @return
	 */
	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();

	}
}
