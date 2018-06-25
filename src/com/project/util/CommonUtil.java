package com.project.util;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;

public class CommonUtil {
	
	private static Logger logger = Logger.getLogger(CommonUtil.class);


	public static double set2DecimalsForDouble(double oldd)
	{
		BigDecimal bg = new BigDecimal(oldd);
		double newd = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		return newd;
	}

	public static String getTimeNow() {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format1.format(new Date());
	}
	
	public static String getTimeNow1() {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		return format1.format(new Date());
	}
	
	public static String getTimeNow2() {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		return format1.format(new Date());
	}
	

	// 获得没有时间秒的当前时间在后面加了个S
	public static String getTimeNows() {
		DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
		return format1.format(new Date());
	}

	public static String getTodayDate(String dateFormat) throws Exception
	{
		DateFormat format = new java.text.SimpleDateFormat(dateFormat);
		String result = format.format(new Date());

		return result;
	}
	
	/**
	 * 日期格式加小时：yyyy-MM-dd HH
	 * @return
	 */
	public static SimpleDateFormat getFormatYYYY_MM_dd_HH()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
		return format;
	}

	public static String getTimeNow(String timeFormat) throws Exception
	{
		DateFormat format = new SimpleDateFormat(timeFormat);
		String result = format.format(new Date());

		return result;
	}

	// private static boolean isLocal = true;
	public static String getDataFilePath(HttpServletRequest request) {
		File file = new File("/wxhome/data");
		if (!file.exists()) {
			return request.getSession().getServletContext().getRealPath("");
		} else {
			return "/wxhome/data";
		}
	}

	public static String fromatSimple(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	
	 /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    } 
    
	/**
	 * 得到下几天的时间
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getNextSimple(int num) {
		Date date = new Date();
		date.setDate(date.getDate() + num);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 得到前几天的时间
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getAgoSimple(int num) {
		Date date = new Date();
		date.setDate(date.getDate() - num);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	/**
	 * 得到前几天的时间
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getAgoSimple_yyyyMMddHHmmss(int num) {
		Date date = new Date();
		date.setDate(date.getDate() - num);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * N周钱
	 * 
	 * @param num
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getNextWeekSimple(int num) {
		Date date = new Date();
		date.setDate(date.getDay() + num);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 根据当前时间计算月份
	 * 
	 * @param type
	 *            1:过去 2：将来
	 * @param num
	 *            要处理的月份数
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getMonthSimple(int type, int num) {
		Date date = new Date();
		if (type == 1) {
			date.setMonth(date.getMonth() - num);
		} else if (type == 2) {
			date.setMonth(date.getMonth() + num);
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	/**
	 * N年钱
	 * 
	 * @param num
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getNextYearSimple(int num) {
		Date date = new Date();
		date.setYear(date.getYear() - num);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 得到指定时间下几天的时间
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getNextSimple(String srcdate, int num) {
		Date date = getSimplaDate(srcdate);
		date.setDate(date.getDate() + num);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static Date getSimplaDate(String srcdate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(srcdate);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date getSimpleDayDate(String srcdate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(srcdate);
		} catch (ParseException e) {
			return null;
		}
	}

//	public static String getLongStringDate(String srcdate) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		return sdf.format(new Date(srcdate+" "));
//	}
	
	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		return sdf.format(date);
	}

	/**
	 * 获得下周星期一的日期
	 * 
	 * @return
	 */
	public static Date getNextMonday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
		Date monday = currentDate.getTime();
		// DateFormat df = DateFormat.getDateInstance();
		// String preMonday = df.format(monday);
		return monday;
	}

	/**
	 * 获得下周星期日的日期
	 * 
	 * @return
	 */
	public static Date getNextSunday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + 6);
		Date monday = currentDate.getTime();
		// DateFormat df = DateFormat.getDateInstance();
		// String preMonday = df.format(monday);
		return monday;
	}

	/**
	 * 获得下周星期一开始的截至日期
	 * 
	 * @return
	 */
	public static Date getNextSunday(int day) {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + (day - 1));
		Date monday = currentDate.getTime();
		// DateFormat df = DateFormat.getDateInstance();
		// String preMonday = df.format(monday);
		return monday;
	}

	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
		// 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	/**
	 * 获取本周一
	 * 
	 * @return
	 */
	public static String getMondayOFWeek() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}

	/**
	 * 获取本周日
	 * 
	 * @return
	 */
	public static String getCurrentWeekday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}

	public static String converTime(long timeGap) {
		// long timeGap = currentSeconds - timestamp;// 与现在时间相差秒数
		String timeStr = null;
		// if (timeGap > 24 * 60 * 60) {// 1天以上
		// timeStr = timeGap / (24 * 60 * 60) + "天";
		// } else if (timeGap > 60 * 60) {// 1小时-24小时
		// timeStr = timeGap / (60 * 60) + "小时";
		// } else if (timeGap > 60) {// 1分钟-59分钟
		if (timeGap > 60) {
			timeStr = timeGap / 60 + "分钟";

		} else {
			timeStr = timeGap + "秒";
		}
		// } else {// 1秒钟-59秒钟
		// timeStr = "刚刚";
		// }
		return timeStr;
	}

	public static String NumToChinese(String temp) {
		// 单位数组
		String[] units = new String[] { "十", "百", "千", "万", "十", "百", "千", "亿" };
		// 中文大写数字数组
		String[] numeric = new String[] { "零", "一", "二", "三", "四", "五", "六",
				"七", "八", "九" };
		String res = "";
		if (null != temp) {
			// 遍历一行中所有数字
			for (int k = -1; temp.length() > 0; k++) {
				// 解析最后一位
				int j = Integer.parseInt(temp.substring(temp.length() - 1,
						temp.length()));
				String rtemp = numeric[j];
				// 数值不是0且不是个位 或者是万位或者是亿位 则去取单位
				if (j != 0 && k != -1 || k % 8 == 3 || k % 8 == 7) {
					rtemp += units[k % 8];
				}
				// 拼在之前的前面
				res = rtemp + res;
				// 去除最后一位
				temp = temp.substring(0, temp.length() - 1);
			}
			// 去除后面连续的零零..
			while (res.endsWith(numeric[0])) {
				res = res.substring(0, res.lastIndexOf(numeric[0]));
			}
			// 将零零替换成零
			while (res.indexOf(numeric[0] + numeric[0]) != -1) {
				res = res.replaceAll(numeric[0] + numeric[0], numeric[0]);
			}
			// 将 零+某个单位 这样的窜替换成 该单位 去掉单位前面的零
			for (int m = 1; m < units.length; m++) {
				res = res.replaceAll(numeric[0] + units[m], units[m]);
			}
		}
		return res;
	}

	public static boolean NotEmpty(String target) {
		if (target != null && !target.equals("")) {
			return true;
		}
		return false;
	}
	
	
	public static boolean NotEmptyObject(Object target) {
		if (target != null && !target.equals("")) {
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(String target) {
		if (target == null || "".equals(target)) {
			return true;
		}
		return false;
	}
	
	public static boolean isEmptyLengthisZero(String target) {
		if (target == null) {
			return true;
		}else if("".equals(target)){
			return true;
		}else if(target.trim().length()==0){
			return true;
		}
		return false;
	}

	/**
	 * 将参数转为对象，支持一级对象
	 * 
	 * @param clazz
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T SerializableObj(Map<String, String[]> parrmmap,
			Class<T> clazz) {
		T serobj = null;
		Map<String, Object> mymaps = new HashMap<String, Object>();
		try {
			Object serlizeobj = clazz.newInstance();// 调用默认方法来创建实例
			Field[] fields = clazz.getDeclaredFields();
			for (String key : parrmmap.keySet()) {
				mymaps.put(key, Array.get(parrmmap.get(key), 0));
			}
			for (Field field : fields) {
				if (mymaps.keySet().contains(field.getName())) {
					String value = String.valueOf(mymaps.get(field.getName()))
							.trim();
					field.setAccessible(true);
					field.set(serlizeobj, ConvertValue(field.getType(), value));
				}
			}
			serobj = (T) serlizeobj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serobj;
	}

	/**
	 * 
	 * @param clazz
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T MapToObj(Map<String, Object> parammap, Class<T> clazz) {
		T serobj = null;
		try {
			Object serlizeobj = clazz.newInstance();// 调用默认方法来创建实例
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (parammap.keySet().contains(field.getName())) {
					String value = String
							.valueOf(parammap.get(field.getName())).trim();
					field.setAccessible(true);
					field.set(serlizeobj, ConvertValue(field.getType(), value));
				}
			}
			serobj = (T) serlizeobj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serobj;
	}

	/**
	 * 
	 * @param clazz
	 * @param value
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Object ConvertValue(Class<?> clazz, String value) {
		Object obj = null;
		if (value != null && !"".equals(value.trim())) {
			if (clazz == int.class || clazz == Integer.class) {
				obj = Integer.valueOf(value);
			} else if (clazz == String.class) {
				obj = value;
			} else if (clazz == short.class || clazz == Short.class) {
				obj = Short.valueOf(value);
			} else if (clazz == long.class || clazz == Long.class) {
				obj = Long.valueOf(value);
			} else if (clazz == boolean.class || clazz == Boolean.class) {
				obj = Boolean.valueOf(value);
			} else if (clazz == float.class || clazz == Float.class) {
				obj = Float.valueOf(value);
			} else if (clazz == double.class || clazz == Double.class) {
				obj = Double.valueOf(value);
			} else if (clazz == Date.class) {
				obj = Date.parse(value);
			} else if (clazz == byte.class || clazz == Byte.class) {
				obj = Byte.valueOf(value);
			}
		}
		return obj;
	}

	/**
	 * 根据字符串时间得到时间戳
	 * 
	 * @param start_time
	 * @return
	 */
	public static long getTimeMillis(String target_time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(target_time).getTime();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("根据字符串时间得到时间戳，失败");
		}
	}

	/**
	 * 给指定时间加上指定的月数
	 * 
	 * @param tradingStartTime
	 *            起始时间
	 * @param loanPeriod
	 *            要加的月数
	 * @return 起始时间增加指定月数以后的时间
	 */
	public static String timeAddMonth(String tradingStartTime, String loanPeriod) {
		DateFormat format1 = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			return format1.format(DateUtils.addMonths(
					format1.parse(tradingStartTime),
					Integer.parseInt(loanPeriod)));
		} catch (NumberFormatException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 给指定时间加上指定的月数
	 * 
	 * @param tradingStartTime
	 *            起始时间
	 * @param loanPeriod
	 *            要加的月数
	 * @return 起始时间增加指定月数以后的时间
	 */
	public static String timeAddMonthWithShort(String tradingStartTime,
			String loanPeriod) {
		DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
		try {
			return format1.format(DateUtils.addMonths(
					format1.parse(tradingStartTime),
					Integer.parseInt(loanPeriod)));
		} catch (NumberFormatException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 给指定时间加上指定的天数
	 * 
	 * @param starttime
	 *            起始时间 yyyy-MM-dd
	 * @param loanPeriod
	 *            要加的天数
	 * @return 起始时间增加指定天数以后的时间
	 */
	public static String timeAddDays(String starttime, int days) {
		DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
		try {
			return format1.format(DateUtils.addDays(format1.parse(starttime),
					days));
		} catch (NumberFormatException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 给指定时间加上指定的天数
	 * 
	 * @param starttime
	 *            起始时间 yyyy-MM-dd hh:mm:ss
	 * @param loanPeriod
	 *            要加的天数
	 * @return 起始时间增加指定天数以后的时间
	 */
	public static String timeAddDay(String starttime, int days) {
		DateFormat format1 = new java.text.SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		try {
			return format1.format(DateUtils.addDays(format1.parse(starttime),
					days));
		} catch (NumberFormatException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 得到当前年份
	 * 
	 * @return
	 */
	public static int getCurrentYear() {
		DateFormat format1 = new java.text.SimpleDateFormat("yyyy");
		return Integer.valueOf(format1.format(new Date()));
	}

	/**
	 * 获取内容图片路径
	 * 
	 * @param fileName
	 *            文件名
	 * @return 图片路径
	 */
	public static String getContentImgPath() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")); // 获取东八区时间
		return "images/content/" + c.get(Calendar.YEAR) + "/"
				+ (c.get(Calendar.MONTH) + 1) + "-"
				+ c.get(Calendar.DAY_OF_MONTH);
	}

	public static String getImgPath(HttpServletRequest request) {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")); // 获取东八区时间
		String string = "images/content/" + c.get(Calendar.YEAR) + "/"
				+ (c.get(Calendar.MONTH) + 1) + "-"
				+ c.get(Calendar.DAY_OF_MONTH);
		return request.getSession().getServletContext().getRealPath(string);
	}

	/**
	 * 得到客户端IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 过滤文本中HTML标签
	 * 
	 * @param inputString
	 * @return
	 */
	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}

	/**
	 * 得到本月第一天
	 * 
	 * @return
	 */
	public static String getMonthFirstDay() {
		DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM");
		return format1.format(new Date()) + "-01";
	}

	/**
	 * 判断原时间是否早于当前时间
	 * 
	 * @param check_time
	 * @return
	 * @throws Exception
	 */
	public static boolean CompareToCurrentTime(String check_time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long time = sdf.parse(check_time).getTime();
			long cur_time = System.currentTimeMillis();
			// System.out.println("流标时间："+time + " 当前时间："+cur_time);
			if (time <= cur_time) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 打印请求情况 printHTTP(request);
	 * 
	 * @param request
	 */
	public static void printHTTP(HttpServletRequest request) {
		// 打印请求情况
		logger.info("\n-------------------------------------------------------------------------------");
		logger.info("请求路径：" + request.getRequestURL());// .+
																// request.getRequestURI()
		logger.info("请求参数：[ ");
		int i = 0;
		for (String key : request.getParameterMap().keySet()) {
			if (i++ == 0) {
				logger.info(key + "=" + request.getParameter(key));
			} else {
				logger.info(" | " + key + "=" + request.getParameter(key));
			}
		}
		logger.info("  ]\n");
		//logger.info("完整路径：" + getFullUrl(request) + "\n");
	}
	
	
	/**
	 * 打印ModelMap情况 printModelMap(map);
	 * @param map
	 * @param request
	 */
	public static void printModelMap(HttpServletRequest request,ModelMap map) {
		// 打印请求情况
		logger.info("\n-------------------------------------------------------------------------------");
		logger.info("请求路径：" + request.getRequestURL());// .+
																// request.getRequestURI()
		logger.info("请求参数：[ ");
		int i = 0;
		for (String key : map.keySet()) {
			if (i++ == 0) {
				logger.info(key + "=" + map.get(key));
			} else {
				logger.info(" | " + key + "=" + map.get(key));
			}
		}
		logger.info("  ]\n");
		//logger.info("完整路径：" + getFullUrl(request) + "\n");
	}
	

	/**
	 * 得到一个完整URL（包含参数）
	 * 
	 * @param request
	 * @return
	 */
	public static String getFullUrl(HttpServletRequest request) {
		StringBuffer url = new StringBuffer();

		int i = 0;
		// 来自一个所知Iframe
		if (request.getParameter("iframe") != null) {
			String iframe = request.getParameter("iframe");
			int index = request.getRequestURI().lastIndexOf("/");
			String suburl = request.getRequestURI().substring(index + 1);
			if ("personal_center".equals(iframe)) {
				url.append("individual_center.do?iframe_page=" + suburl);// 处理连接
			}
		} else {
			url.append(request.getRequestURL());
			for (String key : request.getParameterMap().keySet()) {
				if (i++ == 0) {
					url.append("?" + key + "=" + request.getParameter(key));
				} else {
					url.append("&" + key + "=" + request.getParameter(key));
				}
			}
		}
		System.out.println(url.toString());
		return url.toString();
	}

	/**
	 * 校验银行卡卡号
	 * 
	 * @param cardId 卡号
	 * @return
	 */
	public static boolean checkBankCard(String cardId) {
		char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
		if (bit == 'N') {
			return false;
		}
		return cardId.charAt(cardId.length() - 1) == bit;
	}

	/**
	 * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
	 * 
	 * @param nonCheckCodeCardId
	 * @return
	 */
	public static char getBankCardCheckCode(String nonCheckCodeCardId) {
		if (nonCheckCodeCardId == null
				|| nonCheckCodeCardId.trim().length() == 0
				|| !nonCheckCodeCardId.matches("\\d+")) {
			// 如果传的不是数据返回N
			return 'N';
		}
		char[] chs = nonCheckCodeCardId.trim().toCharArray();
		int luhmSum = 0;
		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			int k = chs[i] - '0';
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}
			luhmSum += k;
		}
		return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
	}

	public static String ConvertTime(String FINTIME) {
		try {
			
			DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = format1.parse(FINTIME);
			format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
			return format1.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String encodeEmail(String email){
		if(email==null||email.trim().equals("")){
			return email;
		}
		if(email.indexOf("@")==1){
			return "*"+email.substring(email.indexOf("@"));
		}else if(email.indexOf("@")==2){
			return email.substring(0,1)+"*"+email.substring(email.indexOf("@"));
		}else if(email.indexOf("@")==3){
			return email.substring(0,1)+"**"+email.substring(email.indexOf("@"));
		}else if(email.indexOf("@")>3){
			return email.substring(0,3)+"******"+email.substring(email.indexOf("@"));
		}
		return null;
	}
	
	/**
	    * 把Hshmap转换成json
	    *
	    */  
    public static String hashMapToJson(Map<String, Object> map) { 
        String string = "{"; 
        for (Iterator it = map.entrySet().iterator(); it.hasNext();) { 
            Entry e = (Entry) it.next(); 
            string += "'" + e.getKey() + "':"; 
            string += "'" + e.getValue() + "',"; 
        } 
        string = string.substring(0, string.lastIndexOf(",")); 
        string += "}"; 
        return string; 
    }  
    
    public String getRemoteHost(javax.servlet.http.HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }
    
    
    public static String JudgeIsMoblie(HttpServletRequest request) {
		String isMoblie = "";
		String[] mobileAgents = { "iphone", "android", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
				"opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
				"nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
				"docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
				"techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
				"wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
				"pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
				"240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
				"blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
				"kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
				"mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
				"prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
				"smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
				"voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
				"Googlebot-Mobile" };
		if (request.getHeader("User-Agent") != null) {
			for (String mobileAgent : mobileAgents) {
				
				if (request.getHeader("User-Agent").toLowerCase().indexOf(mobileAgent) >= 0) {
					System.err.println(request.getHeader("User-Agent").toLowerCase());
					isMoblie = mobileAgent;
					break;
				}
			}
		}
		return isMoblie;
	}
}
