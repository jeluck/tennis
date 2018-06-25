package com.project.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import com.project.common.Settings;


public class GenNumberTool {

	/**
	 * 产生随机字符串
	 * */
	private static Random randGen = null;
	private static char[] numbersAndLetters = null;
	private static Object initLock = new Object();

	public static final String randomString(int length) {

		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			synchronized (initLock) {
				if (randGen == null) {
					randGen = new Random();
					numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
							+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
							.toCharArray();
				}
			}
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	/**
	 * 生成一定长度的流水号，不足位在中间补0
	 * 
	 * @param start	起始位(流水号的  start.length() 位),可以为空字符串
	 * @param num
	 * @param decimals
	 *            生成流水号的长度
	 * @return
	 */
	public static String toIntString(String start,Integer num, int decimals) {
		String str = start;
		String s = num.toString();
		for (int i = s.length(); i < decimals; i++) {
			str += "0";
		}
		return str + num;
	}

	/**
	 * 生成流水号
	 * 
	 * @param t
	 *            流水号位数
	 * @return 流水号
	 */
	public static String getSequenceNumber(int t) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String str = sdf.format(d);
		String haomiao = String.valueOf(System.nanoTime());
		str = str + haomiao.substring(haomiao.length() - 6, haomiao.length());
		return str.substring(str.length() - t, str.length());
	}

	/**
	 * 根据结构名，获取当前分类从父到子的结构字符串数组
	 * 
	 * @return
	 */
	public static String[] getParamsByStructName(String structName) {
		if (StringUtils.isBlank(structName))
			return null;
		if (structName.indexOf("-") < 0) {
			String[] str = new String[1];
			str[0] = structName;
		}
		String[] arr = structName.trim().split("-");
		String[] result = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				result[i] = arr[i];
			} else {
				result[i] = result[i - 1] + "-" + arr[i];
			}
		}
		return result;
	}

	/**
	 * 生成货品编码(货品编码=商品编码+颜色编码+尺码编码)
	 * 
	 * @param productNo
	 * @param colorNo
	 * @param sizeNo
	 * @return
	 */
	/*
	 * public static String genProductNo(String commodityNo, String colorNo,
	 * String sizeNo) { return commodityNo + CommodityConstant.UNDERLINE +
	 * colorNo + CommodityConstant.UNDERLINE + sizeNo; }
	 */

	private static long[] ls = new long[3000];
	private static int li = 0;

	public static String getNo() {
		Random rand = new Random();
		int s = rand.nextInt(7);
		Long re = getpk(s);

		return String.valueOf(re);
	}

	public synchronized static long getPK(int s) {
		long lo = getpk(s);
		for (int i = 0; i < 3000; i++) {
			long lt = ls[i];
			if (lt == lo) {
				lo = getPK(s);
				break;
			}
		}
		ls[li] = lo;
		li++;
		if (li == 3000) {
			li = 0;
		}
		return lo;
	}

	private static long getpk(int s) {
		String a = (String.valueOf(System.currentTimeMillis())).substring(s,
				s + 6);
		String d = (String.valueOf(Math.random())).substring(2, 4);
		return Long.parseLong(a + d);
	}
	
    /**
     * 增加方法同步
     * 方法描述： 生成订单编号
     * @date Jun 4, 2011 5:21:52 PM
     * @return String
     * @throws
     */
    public static synchronized String creatOrderNo(String key) {
//        SimpleDateFormat sdf = new SimpleDateFormat("MMyyddHHmmss");
        SimpleDateFormat sdf = new SimpleDateFormat("ddHHmmss");
        return key + sdf.format(new Date())+RandomStringUtils.randomNumeric(3);
    }

    /**
     *
     * 方法描述： 生成子订单编号
     * @date Jun 4, 2011 5:22:15 PM
     * @param i
     * @param orderMainNo
     * @return String
     * @throws
     */
    public static String getOrderSubNo(int i, String orderMainNo) {
        return new StringBuffer().append(orderMainNo).append("_").append(i).toString();
    }

    /***
     * 生成贷品编码	
     * @param commodityNo		商品编码
     * @param index				第几个
     * @return
     */
	public static String genProductNo(String commodityNo,int index) {
		int start = index+1;
		String str = GenNumberTool.toIntString("",start, 3);
		return commodityNo + str;
	}
}
