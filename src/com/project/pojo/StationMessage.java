package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.util.CommonUtil;

/**
 * 站内消息表，由平台给用户发送信息，用户可以查看，用户接收
 * @author ZNT
 *
 */
@Entity
@Table(name = "stationmessage")
public class StationMessage {
	private Integer id;
	private Integer weuser_id;			//可以为用户ID也可以为场馆主ID
	private Integer status=0;			//状态0,未删除 1,删除
	private String content;				//内容
	private int readstatus=0;			//未读已读状态,0未读,1已读
	private String title="";			//标题
	private String summary="";			//摘要
	private String update_time=CommonUtil.getTimeNow();			//更新时间
	private String create_time=CommonUtil.getTimeNow();		//创建时间
	private Integer send_type=1;		//1为用户 2,为场馆主
	private String appsyetem="";			//安卓手机:Settings.ANDROID		苹果手机:Settings.IOS					
	
	
	@Column(name="send_type")
	public Integer getSend_type() {
		return send_type;
	}
	public void setSend_type(Integer send_type) {
		this.send_type = send_type;
	}
	@Id
	@GeneratedValue
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="weuser_id")
	public Integer getWeuser_id() {
		return weuser_id;
	}
	public void setWeuser_id(Integer weuser_id) {
		this.weuser_id = weuser_id;
	}
	
	@Column(name="status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name="content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="readstatus")
	public int getReadstatus() {
		return readstatus;
	}
	public void setReadstatus(int readstatus) {
		this.readstatus = readstatus;
	}
	
	@Column(name="update_time")
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	
	@Column(name="create_time")
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	@Column(name="summary")
	public String getSummary() {
		return summary;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@Column(name="appsyetem")
	public String getAppsyetem() {
		return appsyetem;
	}
	public void setAppsyetem(String appsyetem) {
		this.appsyetem = appsyetem;
	}
	
	
}
