package com.project.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.common.Constants.MessageReadStatus;
import com.project.util.CommonUtil;


@Entity
@Table(name = "suggestion")
public class Suggestion extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sid;
	private String phone="";// 手机号
	private String detailcontent="";//建议内容
	private int readstatus=0;		//未读已读状态,0未读,1已读		MessageReadStatus.NOT_READ.getStatus()
	
	
	
	
	
	private String update_time=CommonUtil.getTimeNow();			//更新时间
	private String create_time=CommonUtil.getTimeNow();		//创建时间
	@Column(name = "update_time")
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	@Column(name = "create_time")
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	@Id
	@GeneratedValue
	@Column(name="id" )
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "detailcontent")
	public String getDetailcontent() {
		return detailcontent;
	}

	public void setDetailcontent(String detailcontent) {
		this.detailcontent = detailcontent;
	}

	@Column(name = "readstatus")
	public int getReadstatus() {
		return readstatus;
	}

	public void setReadstatus(int readstatus) {
		this.readstatus = readstatus;
	}


}
