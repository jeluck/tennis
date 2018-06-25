package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.common.Constants.MES_CLOUD_TYPE;
import com.project.common.Constants.SEND_TYPE;
import com.project.util.CommonUtil;

/**
 * 平台消息发送
 * @author lxc
 *
 */
@Entity
@Table(name = "terracemessage")
public class TerraceMessage {
	private Integer id;
	private String title;				//标题
	private String content;
	private String summary;				//摘要
	private Integer status=0;			//状态0,未发送 1,已发送
	private Integer delstatus=0;		//删除状态0,未删除1,已删除
	private Integer mes_cloud_type=MES_CLOUD_TYPE.MES.getStatus();		//0为消息,1为云推送,2为短信
	private Integer send_type=SEND_TYPE.ALL_USER.getStatus();		//发送类型:0所有用户,1手动输入,2用户等级筛选(n以下,n-m区间,n以上),3用户,4教练,5场馆主,6活动....
	private String user_data="";			//用户类型,或者等级字符串,(根据所选 发送类型,填入数据)
	
	
	
	
	private String update_time=CommonUtil.getTimeNow();			//更新时间
	private String create_time=CommonUtil.getTimeNow();		//创建时间
	
	@Column(name="summary")
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
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
	
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name="delstatus")
	public Integer getDelstatus() {
		return delstatus;
	}
	public void setDelstatus(Integer delstatus) {
		this.delstatus = delstatus;
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
	@Column(name="mes_cloud_type")
	public Integer getMes_cloud_type() {
		return mes_cloud_type;
	}
	public void setMes_cloud_type(Integer mes_cloud_type) {
		this.mes_cloud_type = mes_cloud_type;
	}
	@Column(name="send_type")
	public Integer getSend_type() {
		return send_type;
	}
	public void setSend_type(Integer send_type) {
		this.send_type = send_type;
	}
	@Column(name="user_data")
	public String getUser_data() {
		return user_data;
	}
	public void setUser_data(String user_data) {
		this.user_data = user_data;
	}
	
	
}
