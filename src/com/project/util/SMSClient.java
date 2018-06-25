package com.project.util;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


import cn.emay.sdk.client.api.Client;

public class SMSClient {
	private static Client client=null;
	private SMSClient(){
	}
	public synchronized static Client getClient(String softwareSerialNo,String key){
		if(client==null){
			try {
				client=new Client(softwareSerialNo,key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}
	public synchronized static Client getClient(){
		ResourceBundle bundle=PropertyResourceBundle.getBundle("config");
		if(client==null){
			try {
				client=new Client(bundle.getString("softwareSerialNo"),bundle.getString("key"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}
	public static void main(String str[]){
		SMSClient.getClient();
	}
	public enum SMSRegResultType{
		SUCESS("0","注册成功"),
		FAIED("911005","客户端注册失败"),
		USED("911003","该序列号已经使用其它key值注册过了"),
		NET_TIMEOUT("303","网络超时或者网络故障"),
		SERVER_ERROR("305","服务器端返回错误，错误的返回值"),
		OPT_ERROR("999","操作频繁");
		private String type;
		private String desc;
		private SMSRegResultType(String type,String desc){
			this.setType(type);
			this.setDesc(desc);
		}
		public static SMSRegResultType typeOf(String type) {
			for (SMSRegResultType c : SMSRegResultType.values()) {
				if (c.getType().equals(type)) {
					return c;
				}
			}
			return null;
		}
		public String getType() {
			return type;
		}
		private void setType(String type) {
			this.type = type;
		}
		public String getDesc() {
			return desc;
		}
		private void setDesc(String desc) {
			this.desc = desc;
		}
	}
}
