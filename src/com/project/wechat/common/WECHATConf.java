package com.project.wechat.common;

import com.project.common.Settings;

public class WECHATConf {
	//微信支付商户开通后 微信会提供appid和appsecret和商户号partner
//	public static String APPID = "wx0268f85b93bf4e83";
//	public static String APPSECRET = "43dfad791a57e9a4ad952e5f91080758";
//	public static String PARTNER = "1242369902";
//	//这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
//	public static String PARTNERKEY = "888liuxiaochang16819870920888888";
//	//openId 是微信用户针对公众号的标识，授权的部分这里不解释	(edit by lxc 2015-05-30	扫码支付接口,好像不需要openId)
//	public static String OPENID = "";
//	//微信支付成功后通知地址 必须要求80端口并且地址不能带参数
//	public static String NOTIFYURL = "http://test.wapacc.com/pay/wechat/getPayResult.sc";	
//	//订单生成的机器 IP
//	public static String SPBILLCREATEIP="127.0.0.1";
	
	
	
	public static String APPID = "wxe6a3546b9e3c2b68";
	//API密钥，在商户平台设置
	public static String APPSECRET = "d4624c36b6795d1d99dcf0547af5443d";
	 //商户号
	public static String PARTNER = "1289823601";
	//这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
	public static String PARTNERKEY = "pYXI30PFXIy0s0WcHWvqoB44Xx7gjOjm";
	//openId 是微信用户针对公众号的标识，授权的部分这里不解释	(edit by lxc 2015-05-30	扫码支付接口,好像不需要openId)
	public static String OPENID = "";
	//微信支付成功后通知地址 必须要求80端口并且地址不能带参数
	public static String NOTIFYURL = Settings.SERVER_HOST+Settings.NOTIFYURL_WECHAT;
	//订单生成的机器 IP
	public static String SPBILLCREATEIP="127.0.0.1";
	
	public static String CHARSET="UTF-8";
}
