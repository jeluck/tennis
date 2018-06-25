package com.project.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CalculateUtil {
	/**
	 * 费率相乘
	 * @param String multiplicand 被乘数
	 * @param String rate 费率,费率单位为%，例如0。2%，则只用传入0.2即可
	 * @return 计算结果,最多保留两位小数
	 */
	public static String multiplyRate(String multiplicand, String rate) {
		BigDecimal _multiplicand = new BigDecimal(multiplicand);
		BigDecimal _rate = new BigDecimal(rate);
		BigDecimal multiplicandRate = new BigDecimal(100);
		BigDecimal taxrate = _rate.divide(multiplicandRate, 7, BigDecimal.ROUND_HALF_UP);
		return formatScale(_multiplicand.multiply(taxrate),2);
	}
	
	/**
	 * 两个数相乘
	 * @param String multiplicand 被乘数
	 * @param String multiplier 乘数
	 * @return 计算结果 
	 */
	public static String multiply(String multiplicand, String multiplier) {
		BigDecimal _multiplicand = new BigDecimal(multiplicand);
		BigDecimal _multiplier = new BigDecimal(multiplier);
		return formatScale(_multiplicand.multiply(_multiplier),2);
	}
	
	/**
	 * 四舍五入除法计算 
	 * @param dividend 被除数
	 * @param divider 除数
	 * @param scale 保留的小数位数
	 * @return 返回计算结果 默认四舍五入
	 */
	public static String divideUp(String dividend,String divider,int scale){
		BigDecimal dividend_dec = new BigDecimal(dividend);// 被除数
		BigDecimal divider_dec = new BigDecimal(divider);// 除数
		return formatScale(dividend_dec.divide(divider_dec, 6, BigDecimal.ROUND_HALF_UP),scale);//new DecimalFormat(pattern).format(result);
	}
	
	/**
	 * 四舍五入除法计算 
	 * @param dividend 被除数
	 * @param divider 除数
	 * @param scale 保留的小数位数
	 * @return 返回计算结果 只舍不入
	 */
	public static String divideDown(String dividend,String divider,int scale){
		BigDecimal dividend_dec = new BigDecimal(dividend);// 被除数
		BigDecimal divider_dec = new BigDecimal(divider);// 除数
		return formatScale(dividend_dec.divide(divider_dec, 6, BigDecimal.ROUND_HALF_DOWN),scale);//new DecimalFormat(pattern).format(result);
	}
	
	/**
	 * 减法计算
	 * @param minuend 被减数
	 * @param subtractor 减数
	 * @return 返回计算结果  
	 */
	public static String subtraction(String minuend ,String subtractor){
		BigDecimal bigdec = new BigDecimal(minuend);
		return formatScale(bigdec.subtract(new BigDecimal(subtractor)),2);
	}
	
	/**
	 * 最多保留scale位小数
	 * @param bd
	 * @param scale
	 * @return
	 */
	public static String formatScale(BigDecimal bd , int scale){
		String pattern = "#.";
		for(int k=1;k<=scale;k++){
			pattern += "#";
		}
		return new DecimalFormat(pattern).format(bd);
	}
	
	/**
	 * 计算多个数相加之和
	 * @param addparams
	 * @return 返回加数之和,最多只保留三位小数
	 */
	public static String add(String... addparams){
		if(addparams==null||addparams.length==0)
			return null;
		BigDecimal bigdec = new BigDecimal(addparams[0]);
		for(int i=1;i<addparams.length;i++){
			bigdec = bigdec.add(new BigDecimal(addparams[i]));
		}
		return bigdec.toPlainString();
	}
	
	/**
	 * 计算两数相加之和
	 * @param param1
	 * @param param2
	 * @return 返回两数相加之和
	 */
	public static String add(String param1,String param2){
		return new BigDecimal(param1).add(new BigDecimal(param2)).toPlainString();
	}
	
	/**
	 * 比较两个数的大小
	 * @param param1 第一个数
	 * @param param2 第二个数
	 * @return -1第一个数比第二个数小，0两数相等，1第一个数比第二个数大
	 */
	public static int compare(String param1,String param2){
		return new BigDecimal(param1).compareTo(new BigDecimal(param2));
	}
	
	/**
	 * 判断第一个参数是否为第二个参数的整数倍
	 * @param param1 第一个数
	 * @param param2 第二个数
	 * @return true第一个数是第二个数的整数倍，false第一个数不上第二个数的整数倍
	 */
	public static boolean timesOnSecondDecimal(String param1,String param2){
		if(param1==null||param2==null)
			return false;
		if("".equals(param1)||"".equals(param2))
			return false;
		if(new BigDecimal(param1).divideAndRemainder(new BigDecimal(param2))[1].compareTo(new BigDecimal("0"))==0)
			return true;
		return false;
	}
}
