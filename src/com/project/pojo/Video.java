package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.util.CommonUtil;

/**
 * 视频
 * @author LXC
 *
 */
@Entity
@Table(name = "video")
public class Video  extends BaseEntity{
	private int id;
	private String title;
	private String content;
	private String httpurl;	//外部的视频地址
	private Video_classification video_classification_id;//视频分类ID
	
	
	
	
	
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
    @Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "httpurl")
	public String getHttpurl() {
		return httpurl;
	}

	public void setHttpurl(String httpurl) {
		this.httpurl = httpurl;
	}
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_class_id")
	public Video_classification getVideo_classification_id() {
		return video_classification_id;
	}

	public void setVideo_classification_id(
			Video_classification video_classification_id) {
		this.video_classification_id = video_classification_id;
	}

}
