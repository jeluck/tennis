package com.project.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.Random;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.project.common.ReturnCode;
import com.project.common.Settings;

public class SMSUtil {
	private static final String SMS_UID="strawsms";
	private static final String SMS_PWD="f680bfdbd7b1a9f9fc2e";
	public static String SMS_SIGNATURE="【U橙App】";
//	public static String SMS_SIGNATURE="【"+Settings.PROJECT_NAME+"】";
//	public static String CD_KEY="6SDK-EMY-6688-KJUPR";				//6SDK-EMY-6688-KDYLQ
//	public static String PASSWORD="903958";							//62195qi
//	public static String CD_KEY="6SDK-EMY-6688-KJUQL";				//6SDK-EMY-6688-KDYLQ
//	public static String PASSWORD="879890";							//62195qi
//	public static String CD_KEY="0SDK-EBB-6699-RCZTN";				//6SDK-EMY-6688-KDYLQ
//	public static String PASSWORD="108752";							//62195qi
	public static String CD_KEY="8SDK-EMY-6699-RDWOL";				//6SDK-EMY-6688-KDYLQ
	public static String PASSWORD="193087";							//62195qi
	public static String SMS_KEY="193087";
	private static final String SUCCESS_CODE = "<error>0</error>";
	private static Logger logger = Logger.getLogger(SMSUtil.class);
	public static void main(String[] args)throws Exception{
		String verifyCode = genVerifyCode(6);
		//System.err.println(SecurityUtil.encrypt("123123"));
		sendSmsMsg("18811452028", verifyCode);
	}

	
	public static boolean sendSmsMsg1(String phone,String msg){
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn"); 
		post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
		NameValuePair[] data ={ new NameValuePair("Uid", SMS_UID),
								new NameValuePair("Key", SMS_PWD),
								new NameValuePair("smsMob",phone),
								new NameValuePair("smsText",msg+SMS_SIGNATURE)};
		try {
			post.setRequestBody(data);
			client.executeMethod(post);
			String result = new String(post.getResponseBodyAsString().getBytes("gbk")); 
			logger.info("send sms message--phone:"+phone+"--message:"+msg+"--result:"+result);
			System.err.println(result);
			
			System.out.println("CD_KEY-----------------"+CD_KEY);
			System.out.println("SMS_KEY-----------------"+SMS_KEY);
			System.out.println("SMS_SIGNATURE-----------------"+SMS_SIGNATURE);
			post.releaseConnection();
		} catch (HttpException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static int sendSmsMsg(String phone,String msg){
		int result = SMSClient.getClient(CD_KEY, SMS_KEY).sendSMS(new String[] {phone}, SMS_SIGNATURE+msg,5);//带扩展码
		System.err.println(result);
		
		System.out.println("CD_KEY-----------------"+CD_KEY);
		System.out.println("SMS_KEY-----------------"+SMS_KEY);
		System.out.println("SMS_SIGNATURE-----------------"+SMS_SIGNATURE);
		if(result!=0){
			logger.debug(phone+"短信发送失败:"+msg+ReturnCode.getReCodeInfo(String.valueOf(result)));
		}
		return result;
	}

	private static String getReturnCode(String xmlDoc) {
		try {
			Element root = new SAXReader().read(new StringReader(xmlDoc)).getRootElement();
			return root.element(root.getQName("error")).getTextTrim();
		} catch (Exception e) {
			logger.error("解析短信网关返回信息出错", e);
			return null;
		}
	}
	/**
	 * 根据给定的验证码长度，生成一组对应长度的随机数
	 * @param codelength 验证码的长度
	 * @return 生成的验证码字符串
	 */
	public static String genVerifyCode(int codelength){
		char[] myCodeChar = { '6', '9', '4', '1', '7', '3', '8', '2', '5'};
		String checkCode = "";
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < codelength; i++)
			checkCode += myCodeChar[random.nextInt(myCodeChar.length)];
		return checkCode;
	}
}