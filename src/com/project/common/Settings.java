package com.project.common;

import com.project.util.Config;

/////////gaidong

/**
 * 系统设置参数
 * @author daybreak
 *
 */
public class Settings {
	
	//服务器主机 	
	public static String SERVER_HOST = "http://yu.yihezhai.cc/";
	//静态服务器主机  http://static.jinzhuangbc.com | http://localhost:8080/p2p
	public static String STATIC_SERVER_HOST = "http://static.huiqian168.com/";
	
	public static String getServerHost(){
		return System.getProperty("SERVER_HOST")==null?SERVER_HOST:System.getProperty("SERVER_HOST");
	}
	
	public static String getStaticServerHost(){
		return System.getProperty("STATIC_SERVER_HOST")==null?STATIC_SERVER_HOST:System.getProperty("STATIC_SERVER_HOST");
	}
	
	/**
	 * 后台用户Session KEY 
	 */
	public final static String MANAGER_SESSION = "manager";
	/**
	 * 前端用户Session KEY 
	 */
	public final static String USER_SESSION = "user";
	
	/**
	 * 前端教练Session KEY 
	 */
	public final static String COACH_SESSION = "coach";
	
	/**
	 * 后台用户Session KEY 
	 */
	public final static String PLAYGROUNDMANAGER_SESSION = "playgroundmanager";
	
	/**
	 * 系统
	 */
	public final static String SYSTEM_HANDLE = "system";
	
	/**
	 * 菜单归属角色,在AdminMenu表里menu_belong_to_role,字段有值,则表示合作商菜单
	 */
	public final static String MENU_BELONG_TO_ROLE_COOPERATIVE_PARTNER = "playgroundmanager";	
	
	/***
	 * 安卓手机
	 */
	public final static String ANDROID = "android";
	
	/***
	 * IOS手机
	 */
	public final static String IOS = "ios";
	
	/**
	 * 微信_订单支付异步返回链接
	 */
	public final static String NOTIFYURL_WECHAT_PAY_ORDER = "wechatPay.do";
	
	/**
	 * 支付宝_订单支付异步返回链接
	 */
	public final static String NOTIFYURL_APLIY_PAY_ORDER = "apliyPay.do";
	
	/**
	 * 微信_入会支付异步返回链接
	 */
	public final static String MEMBERSHIP_WECHAT = "weChatMembership.do";
	
	/**
	 * 支付宝_入会支付异步返回链接
	 */
	public final static String MEMBERSHIP_ALIPAY = "alipayMembership.do";
	
	/**
	 * 微信充值异步返回链接
	 */
	public final static String NOTIFYURL_WECHAT = "wechatCharPay.do";
	
	/**
	 * 支付时填的body信息
	 */
	public final static String BODY_PAY_ORDER = "订单支付";
	
	/**
	 * 充值时填的body信息
	 */
	public final static String BODY_RECHARGE = "充值";
	
	
	/**
	 * 入会时填的body信息
	 */
	public final static String BODY_MEMBERSHIP = "申请VIP会员";
	
	
	/**
	 * 图片大小
	 */
	public final static long IMAGESIZE = 4194304;
	
	
	
	
	
	
	/***
	 * 公司电话
	 */
	public final static String PHONEBYCOMPANY = "phone_by_company";
	/**
	 * true
	 */
	public final static String TRUE = "true";
	
	/***
	 * false
	 */
	public final static String FALSE = "false";
	
	//系统邮箱帐号
	public static String EML_ACCOUNT = "";
	//系统邮箱密码
	public static String EML_PASSWORD = "";
	//系统邮箱SMTP服务器主机地址
	public static String EML_HOST = "smtp.exmail.qq.com";
	//系统邮箱SMTP服务器主机端口
	public static int EML_PORT;
	/***
	 * 图片保存文件夹名称(总文件夹)(文章内的图片,用户头像,上传的图片等等)
	 */
	public final static String IMAGE_SAVE_FILE_NAME="upload"; 
	
	/**
	 * 项目的名称(放到application容器标识)
	 */
	public final static String APPLICATION_PROJECT_NAME="project_name";
	
	/**
	 * 项目的名称
	 */
	public final static String PROJECT_NAME="U橙"; 
	
	/***
	 * 图片格式	 .jpg
	 */
	public static final String JPG_PICTUREFORMAT= ".jpg";
	
	/***
	 * 头像图片固定字符  head_
	 */
	public static final String HEAD_IMAGENAMESTR= "head_";
	
	/***
	 * 头像图片固定字符  head_
	 */
	public static final String COACHHEAD_IMAGENAMESTR= "coachhead_";

	/***
	 * 场馆图片固定字符  playpround_
	 */
	public static final String PLAYPROUND_IMAGENAMESTR= "playpround_";
	
	/***
	 * 证书图片固定字符  certificate_
	 */
	public static final String CERTIFICATE_IMAGENAMESTR= "certificate_";
	/***
	 * 赛事图片固定字符  events_
	 */
	public static final String EVENTS_IMAGENAMESTR= "events_";
	
	/***
	 * 小图片固定字符  small_
	 */
	public static final String SMALL_IMAGENAMESTR= "small_";
	
	/***
	 * 中图片固定字符  middle_
	 */
	public static final String MIDDLE_IMAGENAMESTR= "middle_";
	
	/***
	 * 大图片固定字符  big_
	 */
	public static final String BIG_IMAGENAMESTR= "big_";
	
	
	/**
	 * 手机验证码  KEY 把数据放到容器中 
	 */
	public final static String PHONECODE_APPLICATION = "phonecode_application";
	
	
	/**
	 * 防止页面js,css缓存,获取版本号
	 */
	public static String PREVENT_PAGE_CACHE_GET_VERSION=Config.getInstance().getProperties().getProperty("PREVENT_PAGE_CACHE_GET_VERSION");
	
	
	
	
	
	
	/**
	 * 首页静态化tomcat路径配置
	 */
	public static String STATIC_TOMCAT_PATH=Config.getInstance().getProperties().getProperty("com.p2p.common.static.tomcat.path");
	/**
	 * 短信超时时间
	 */
	public static int SMS_TIMELIMIT = 20*60*1000;
	/**
	 * 注册短信和户忘记密码并修改 SMS KEY
	 */
	public final static String REG_FORGET_PASS_SMS_APPLICATION = "regforgetsmscode";
	
}
