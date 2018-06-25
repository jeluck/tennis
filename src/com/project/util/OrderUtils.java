/*
 * Copyright 2011 ems.com All right reserved. This software is the confidential and proprietary information of
 * ems.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with ems.com.
 */
package com.project.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.project.common.Constants;
import com.project.common.OrderEnum.LogType;
import com.project.common.OrderEnum.MethodCode;
import com.project.pojo.OrderLog;

/**
 * 类OrderUtils.java的实现描述：TODO 类实现描述
 * 
 * @author user 2011-5-10 上午10:38:42
 */
public class OrderUtils {

	private static final Logger log = Logger.getLogger(OrderUtils.class);

	/**
	 * 通过两个日期得到他们之间相差多少天 creator liuwenjun create time 2011-6-17 下午08:10:49
	 * 
	 * @param begin
	 * @param endDate
	 * @return
	 */
	public static long getDaysByBeginEndDate(Date begin, Date endDate) {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(begin);
		calendar2.setTime(endDate);
		long milliseconds1 = calendar1.getTimeInMillis();
		long milliseconds2 = calendar2.getTimeInMillis();
		long diff = milliseconds2 - milliseconds1;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays;
	}

	/**
	 * 通过当前日期获取相差 day 天前的时间
	 * 
	 * @param time
	 * @param day
	 * @return
	 */
	public static String getStartTime(Date time, int day) {
		Calendar calendar1 = Calendar.getInstance();

		calendar1.setTime(time);

		long millSeconds1 = calendar1.getTimeInMillis();

		long tmpDays = day * (24 * 60 * 60 * 1000);

		return DateUtil.format(new Date(millSeconds1 - tmpDays), "yyyy-MM-dd");
	}

	/**
	 * 通过系统时间戳来得到时间 creator liuwenjun create time 2011-6-17 下午07:41:55
	 * 
	 * @param systemtime
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateByLong(long systemtime) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = format.format(systemtime);
		return format.parse(date);
	}

	/**
	 * 返回一个订单日志对象
	 * 
	 * @param orderNo
	 * @param methodCode
	 * @param logType
	 * @param operateUser
	 * @param remark
	 * @param flag
	 * @return
	 */
	public static OrderLog getOrderLog(String orderNo, MethodCode methodCode,
			LogType logType, String operateUser, String remark, String applyId,
			boolean flag) {
		OrderLog log = new OrderLog();
		log.setLogType(logType.getValue());
		log.setCreateTime(new Date());
		log.setOperateResult(flag ? Constants.OPERATE_SUCCESS
				: Constants.OPERATE_FAIL);
		log.setBehavioutDescribe(methodCode.getName());
		log.setOrderNo(orderNo);
		log.setApplyId(applyId);
		log.setOperateUser(operateUser);
		log.setRemark(remark);
		return log;
	}

	/**
	 * 对list进行排序
	 * 
	 * @param list
	 * @param method
	 * @param sort
	 */
	public static void Sort(List<?> list, final String method, final String sort) {
		Collections.sort(list, new Comparator<Object>() {

			public int compare(Object a, Object b) {
				int ret = 0;
				try {
					Method m1 = (a).getClass().getMethod(method);
					Method m2 = (b).getClass().getMethod(method);
					// 倒序
					if (sort != null && "desc".equals(sort)) {
						ret = m2.invoke((b)).toString()
								.compareTo(m1.invoke((a)).toString());
					} else {// 正序
						ret = m1.invoke((a)).toString()
								.compareTo(m2.invoke((b)).toString());
					}
				} catch (NoSuchMethodException e) {
					System.out.println(e);
				} catch (IllegalAccessException e) {
					System.out.println(e);
				} catch (InvocationTargetException e) {
					System.out.println(e);
				}
				return ret;
			}
		});
	}


	
	/**
	 * 支持key 是数字类型
	 * 
	 * @param oriMap
	 * @return add sxw
	 */
	private static Map<String, String> sortMapByKey(Map<String, String> oriMap) {
		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}
		Map<String, String> sortedMap = new TreeMap<String, String>(
				new Comparator<String>() {
					public int compare(String key1, String key2) {
						int intKey1 = 0, intKey2 = 0;
						try {
							intKey1 = getInt(key1);
							intKey2 = getInt(key2);
						} catch (Exception e) {
							intKey1 = 0;
							intKey2 = 0;
						}
						return intKey1 - intKey2;
					}
				});
		sortedMap.putAll(oriMap);
		return sortedMap;
	}

	private static int getInt(String str) {
		int i = 0;
		try {
			Pattern p = Pattern.compile("^\\d+");
			Matcher m = p.matcher(str);
			if (m.find()) {
				i = Integer.valueOf(m.group());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * 支付状态
	 * 
	 * @return 未支付 7; 部分支付 8; 已支付 9; 退款申请中 10; 部分退款 11; 全额退款 12; 退款失败 13; add
	 *         sxw
	 */
	public static Map<String, String> getPayStatus() {
		Map<String, String> reMap = new HashMap<String, String>();
		reMap.put("7", "未支付");
		 reMap.put("8", "部分支付");
		reMap.put("9", "已支付");
		reMap.put("10", "退款申请中");
		// reMap.put("11", "部分退款");
		reMap.put("12", "全额退款");
		 reMap.put("13", "退款失败");
		reMap = sortMapByKey(reMap);
		return reMap;
	}

	/**
	 * 基本状态
	 * 
	 * @return 未处理(默认值) 1; 已确认 2; 已挂起 (订单表加入挂起类型 类型有缺货挂起、预售挂起) 3; 已完成 4; 已作废 5;
	 *         客服取消 6; add sxw
	 */
	public static Map<String, String> getBasicStatus() {
		Map<String, String> reMap = new HashMap<String, String>();
		reMap.put("1", "未处理");
		reMap.put("2", "已确认");
		reMap.put("3", "已挂起");
		reMap.put("4", "已完成");
		reMap.put("5", "已作废");
		reMap.put("6", "客服取消");
		reMap = sortMapByKey(reMap);
		return reMap;
	}

	/**
	 * 配送状态 准备 14; 部分发货 15; 已发货 16; 已收货 17; 拒收 18; 部分退货 19; 已取消发货 20; 已终止发货 21;
	 * 拣货中 22;
	 * 
	 * @return add sxw
	 */
	public static Map<String, String> getDeliveryStatus() {
		Map<String, String> reMap = new HashMap<String, String>();
		reMap.put("14", "准备");
		// reMap.put("15", "部分发货");
		reMap.put("16", "已发货");
		reMap.put("17", "已收货");
		reMap.put("18", "拒收");
		// reMap.put("19", "部分退货");
		reMap.put("20", "已取消发货");
		reMap.put("21", "已终止发货");
		reMap.put("22", "拣货中");
		reMap = sortMapByKey(reMap);
		return reMap;
	}

	/**
	 * 获得订单操作日志的类型信息
	 * 
	 * @return add by sxw 20120910
	 */
	public static Map<String, String> getOrderLogType() {
		Map<String, String> reMap = new HashMap<String, String>();
		LogType[] logTypes = LogType.values();
		for (LogType lt : logTypes) {
			reMap.put(String.valueOf(lt.getValue()), lt.getName());
		}
		reMap = sortMapByKey(reMap);
		return reMap;
	}
	
}
