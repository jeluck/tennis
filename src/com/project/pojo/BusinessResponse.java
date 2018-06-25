package com.project.pojo;

import com.project.common.Constants.ResponseStatus;

public class BusinessResponse {
	private int status;
	private String msg;
	private Object data = "";
	public BusinessResponse() {
		
	}
	
	public BusinessResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	public BusinessResponse(int status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		if(data==null){
			this.data = "";
		}else{
			this.data = data;
		}
	}
	public BusinessResponse(ResponseStatus status, String msg) {
		this.status = status.getStatus();
		this.msg = msg;
	}
	public BusinessResponse(ResponseStatus status, String msg, Object data) {
		this.status = status.getStatus();
		this.msg = msg;
		if(data==null){
			this.data = "";
		}else{
			this.data = data;
		}
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		if(data==null){
			this.data = "";
		}else{
			this.data = data;
		}
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
