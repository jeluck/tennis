package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.util.CommonUtil;

/**
 * SEO设置
 * 
 * @author lxc
 * 
 */
@Entity
@Table(name =  "seo_setting")
public class SEOSetting  extends BaseEntity{
	private int id;
	private String html_title;//网页标题   
	private String meta_keywords;//Meta Keywords关键字         
	private String meta_description;//Meta Description 网页描述 
	private String header_info;//其他头部信息
	
	
	
	
	
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

	@Column(name = "html_title")
	public String getHtml_title() {
		return html_title;
	}

	public void setHtml_title(String html_title) {
		this.html_title = html_title;
	}

	@Column(name = "meta_keywords")
	public String getMeta_keywords() {
		return meta_keywords;
	}

	public void setMeta_keywords(String meta_keywords) {
		this.meta_keywords = meta_keywords;
	}

	@Column(name = "meta_description")
	public String getMeta_description() {
		return meta_description;
	}

	public void setMeta_description(String meta_description) {
		this.meta_description = meta_description;
	}

	@Column(name = "header_info")
	public String getHeader_info() {
		return header_info;
	}

	public void setHeader_info(String header_info) {
		this.header_info = header_info;
	}
}
