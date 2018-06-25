package com.project.common;

import com.project.util.Config;

/////////gaidong

/**
 * 系统设置参数
 * @author daybreak
 *
 */
public class ConfigKey {
	
	/**
	 * 用户限制在最早打球时间多久之前不能取消订单 单位为小时
	 */
	public final static String EARLIST_CANCEL_ORDER_TIME="earlist_cancel_order_time";
	
	/***
	 * 默认取消订单时间 单位为分钟
	 */
	public final static String DEFAULT_CANCLE_ORDER_TIME="default_cancle_order_time";
	
	/***
	 * 默认手机验证码有效时间 单位为分钟
	 */
	public final static String DEFAULT_MOBILE_VERIFICATION_CODE_TIME="default_mobile_verification_code_time";
	
	/***
	 * 补贴发放时间设置(单位:天)
	 */
	public final static String SUBSIDIES_GRANT_TIME="subsidies_grant_time";
	
	/***
	 * 交易结算时间设置(单位:天)
	 */
	public final static String TRADE_BALANCE_TIME="trade_balance_time";
	/***
	 * 已支付订单在有效时间之前可取消,单位为分钟
	 */
	public final static String PAID_ORDER_CANCLE_BEFORE_TIME="paid_order_cancle_before_time";
	
	
	/***
	 * 百度云推送apikey_value		安卓教练端
	 */
	public final static String BAIDU_APIKEY_APP_VALUEFORANDROIDCOACH = Config.getInstance().getProperties().getProperty("BAIDU_APIKEY_APP_VALUEFORANDROIDCOACH");
	/***
	 * 百度云推送secretKey_value		安卓教练端
	 */
	public final static String BAIDU_SECRETKEY_APP_VALUEFORANDROIDCOACH = Config.getInstance().getProperties().getProperty("BAIDU_SECRETKEY_APP_VALUEFORANDROIDCOACH");
	/***
	 * 百度云推送apikey_value		安卓用户端
	 */
	public final static String BAIDU_APIKEY_APP_VALUEFORANDROIDUSER = Config.getInstance().getProperties().getProperty("BAIDU_APIKEY_APP_VALUEFORANDROIDUSER");
	/***
	 * 百度云推送secretKey_value		安卓用户端
	 */
	public final static String BAIDU_SECRETKEY_APP_VALUEFORANDROIDUSER = Config.getInstance().getProperties().getProperty("BAIDU_SECRETKEY_APP_VALUEFORANDROIDUSER");
	/***
	 * 百度云推送apikey_value		IOS教练端
	 */
	public final static String BAIDU_APIKEY_APP_VALUEFORIOSCOACH = Config.getInstance().getProperties().getProperty("BAIDU_APIKEY_APP_VALUEFORIOSCOACH");
	/***
	 * 百度云推送secretKey_value		IOS教练端
	 */
	public final static String BAIDU_SECRETKEY_APP_VALUEFORIOSCOACH = Config.getInstance().getProperties().getProperty("BAIDU_SECRETKEY_APP_VALUEFORIOSCOACH");
	/***
	 * 百度云推送apikey_value		IOS用户端
	 */
	public final static String BAIDU_APIKEY_APP_VALUEFORIOSUSER = Config.getInstance().getProperties().getProperty("BAIDU_APIKEY_APP_VALUEFORIOSUSER");
	/***
	 * 百度云推送secretKey_value		IOS用户端
	 */
	public final static String BAIDU_SECRETKEY_APP_VALUEFORIOSUSER = Config.getInstance().getProperties().getProperty("BAIDU_SECRETKEY_APP_VALUEFORIOSUSER");
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 订单时间倒计时标识
	 */
	public final static String ORDER_COUNTDOWN_REMARD = "order_countdown_remard";
	

	/***
	 * 发送短信开关
	 */
	public final static String SEND_SMS_ON_OFF = "send_sms_on_off";
	
	/**
	 * 提现手续费(系统变量)
	 */
	public final static String WithdrawCounterFee = "withdraw_counter_fee";
	
	
	
	/***
	 * 百度云推送apikey
	 */
	public final static String BAIDU_APIKEY_APP = "baidu_apikey_app";
	
	/***
	 * 百度云推送secretKey
	 */
	public final static String BAIDU_SECRETKEY_APP = "baidu_secretkey_app";
	
	
	

	public final static String ALIPAY_URL = "alipayurl";

	public final static String PAYMENT_METHOD = "paymentmethod";
	public final static String DRIVER_BALANCE = "driverbalance";

	/***
	 * 支付宝商户id
	 */
	public final static String SELLER_ID = "sellerid";
	/***
	 * 支付宝商户账号
	 */
	public final static String SELLER_EMAIL = "selleremail";
	/***
	 * PID
	 */
	public final static String ALIPAY_PID = "alipaypid";
	/***
	 * key
	 */
	public final static String ALIPAY_KEY = "alipaykey";
	/***
	 * 首页搜索默认数据
	 */
	public final static String INDEX_SEARCH = "index_ search";
	
	/***
	 * 安卓教练端版本
	 */
	public final static String VIEWVERSIONFORANDROIDDRIVER = "view_version_for_android_coach";
	
	/***
	 * IOS教练端版本
	 */
	public final static String VIEWVERSIONFORIOSDRIVER = "view_version_for_ios_coach";
	
	/***
	 * 安卓用户版本
	 */
	public final static String VIEWVERSIONFORANDROIDUSER = "view_version_for_android_user";
	
	
	/***
	 * IOS用户版本
	 */
	public final static String VIEWVERSIONFORIOSUSER = "view_version_for_ios_user";
	
	
	/***
	 * 安卓用户版本下载地址
	 */
	public final static String ANDROIDUSERVERSIONURL="android_user_version_url";
	
	/***
	 * 安卓教练版本下载地址
	 */
	public final static String ANDROIDCOACHVERSIONURL="android_coach_version_url";
	
	/***
	 * 教练补贴比例
	 */
	public final static String COACH_SUBSIDY_PROPORTION="coach_subsidy_proportion";
	
	/***
	 * 教练补贴类型
	 */
	public final static String COACH_SUBSIDY_TYPE="coach_subsidy_type";
	
	/***
	 * 场馆补贴比例
	 */
	public final static String PLAYGROUND_SUBSIDY_PROPORTION="playground_subsidy_proportion";
	
	/***
	 * 场馆补贴类型
	 */
	public final static String PLAYGROUND_SUBSIDY_TYPE="playground_subsidy_type";
	
	
	/***
	 * 安卓手机
	 */
	public final static String ANDROID = "android";
	
	/***
	 * IOS手机
	 */
	public final static String IOS = "ios";
	
	/***
	 * 司机端
	 */
	public final static String PLATFORMTYPEFORDRIVER = "driver";
	
	/***
	 * 用户端
	 */
	public final static String PLATFORMTYPEFORUSER = "user";
	
	/***
	 * 安卓司机端APP下载路径
	 */
	public final static String ANDROID_APP_PATHFORDRIVER = "android_app_path_for_driver";
	
	/***
	 * 安卓用户端APP下载路径
	 */
	public final static String ANDROID_APP_PATHFORUSER = "android_app_path_for_user";
	
	/***
	 * IOS司机端APP下载路径
	 */
	public final static String IOS_APP_PATHFORDRIVER = "ios_app_path_for_driver";
	
	/***
	 * IOS用户端APP下载路径
	 */
	public final static String IOS_APP_PATHFORUSER = "ios_app_path_for_user";
	
	/**
	 * 货品初始流水号从1开始(需补足3位)
	 */
	public static int PRODUCT_SER_NO = 1;
}
