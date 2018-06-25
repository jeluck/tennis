package com.project.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import net.sf.json.JSONObject;

public class JsonInfoToHttpPost {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(JsonInfoToHttpPost.class);
	public static final String ADD_URL = ""; // 请求的地址
	
	public static final String OPERAFILENAME = "/easemob.properties";

	/**
	 * 封装参数请求,并获取返回数据
	 * java代码发送JSON格式的httpPOST请求,并获取返回数据
	 * @return
	 */
	public static String appadd() {

		try {
			// 创建连接
			URL url = new URL(ADD_URL);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			// connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Type", "application/json"); // json格式

			connection.connect();

			// POST请求
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			JSONObject obj = new JSONObject();
			obj.element("grant_type", "client_credentials"); // 参数
			// obj.element("app_ip", "10.21.243.234"); //参数
			// obj.element("app_port", 8080); //参数
			obj.element("client_id", ""); // 参数
			obj.element("client_secret", ""); // 参数

			out.writeBytes(obj.toString());
			out.flush();
			out.close();

			// 读取响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String lines;
			StringBuffer sb = new StringBuffer("");
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				sb.append(lines);
			}
			System.out.println("请求了地址,返回的数据:"+sb);
			reader.close();
			// 断开连接
			connection.disconnect();
			return sb.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";

	}

	/**
	 *  按字符缓冲写入 BufferedWriter and FileWriter
	 * 
	 * @param str
	 *            写入字符串
	 */
	public static void bufferedWriteAndFileWriterTest(String str) {
		//String temp = JsonInfoToHttpPost.class.getClass().getResource("/").getPath();//获取项目的classes文件夹路径	(用main方法可以调用,在controller层调用会报错)
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();//获取项目的classes文件夹路径(可以直接从controller层调用方法)
		File f = new File(path+OPERAFILENAME);
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			bw.write(str);
			bw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 解析json字符串,再自行拼接字符
	 * @param json		{"access_token":"YWMtWY779DgJEeS2h9OR7fw4QgAAAUmO4Qukwd9cfJSpkWHiOa7MCSk0MrkVIco",
						  "expires_in":5184000,
						  "application":"c03b3e30-046a-11e4-8ed1-5701cdaaa0e4"
						}
	 * @return
	 */
	public static String analysisJson(String json){
		String str = "";
		//从当前系统中获取换行符，默认是"\n"  
		String lineSeparator = System.getProperty("line.separator", "\n");  
		//对传进来的
		JSONObject jsonObject = JSONObject.fromObject(json); 
        String access_token=jsonObject.getString("access_token");
        String expires_in=jsonObject.getString("expires_in");
        String application=jsonObject.getString("application");
        str+="access_token="+access_token+lineSeparator;
        str+="expires_in="+expires_in+lineSeparator;
        str+="application="+application+lineSeparator;
        str+="timestamps="+System.currentTimeMillis();
        
        return str;
	}

	/**
	 * 判断当前的时间戳减掉上一次创建文件后的时间戳 是否大于一周时间 ,如果是则返回true
	 * @return
	 */
	public static boolean CompareTimestamps(){
		InputStream is = JsonInfoToHttpPost.class.getResourceAsStream(OPERAFILENAME);
		boolean flag = false;
		if(is==null){
			return true;
		}
		
		Properties properties = new Properties();
		try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		long expires_in = 7*60*60*24*1000;			//一周时间（时间戳）
		long timestamps = Long.parseLong(properties.getProperty("timestamps"));//上一次创建此文件的时间戳
		long currenttimestamps = System.currentTimeMillis();				//当前时间戳
		
		if(currenttimestamps - timestamps>=expires_in){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 获取token值
	 * @return
	 */
	public static String getToken(){
		if(CompareTimestamps()){
			//String json="{\"access_token\":\"YWMttaKGGFdmEeWtIpOY5lC_hAAAAVDqJvyTlEHwTNOdAngZWDF5ZrxOix6ObJ8\",\"expires_in\":5184000,\"application\":\"f2a9f010-55d0-11e5-8cd6-1f037b76676f\"}";
			bufferedWriteAndFileWriterTest(analysisJson(appadd()));
		}
		InputStream is = JsonInfoToHttpPost.class.getResourceAsStream(OPERAFILENAME);
		Properties properties = new Properties();
		try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("用户注册时,取token");
		return properties.getProperty("access_token");
	}
	
	public static void main(String[] args) {
		//String str=appadd();
//		String json="{\"access_token\":\"YWMttaKGGFdmEeWtIpOY5lC_hAAAAVDqJvyTlEHwTNOdAngZWDF5ZrxOix6ObJ8\",\"expires_in\":5184000,\"application\":\"f2a9f010-55d0-11e5-8cd6-1f037b76676f\"}";
//		bufferedWriteAndFileWriterTest(analysisJson(json));
//		
//		InputStream is = JsonInfoToHttpPost.class.getResourceAsStream(OPERAFILENAME);
//		
//		Properties properties = new Properties();
//		try {
//			properties.load(is);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		JSONObject jsonObject = JSONObject.fromObject(json); 
//        String access_token=jsonObject.getString("expires_in");
//		System.out.println(access_token);
		System.err.println(getToken());
	}

}
