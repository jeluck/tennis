package com.project.pojo;

public class PushMsg {
	private Object info;//信息
	private Boolean status;//状态
	public PushMsg(){}
	
	public PushMsg(String info, Boolean status) {
		this.info = info;
		this.status = status;
	}
	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
}
