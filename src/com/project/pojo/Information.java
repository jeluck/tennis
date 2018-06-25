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
 * 新闻资讯
 * @author LXC
 *
 */
@Entity
@Table(name = "information")
public class Information  extends BaseEntity{
	private int id;
	private String title;
	private String content;
	private InformationCategoryInfo information_categoryinfo_id;//新闻资讯分类ID
	
	
	
	
	
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
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "info_category_id")
	public InformationCategoryInfo getInformation_categoryinfo_id() {
		return information_categoryinfo_id;
	}

	public void setInformation_categoryinfo_id(
			InformationCategoryInfo information_categoryinfo_id) {
		this.information_categoryinfo_id = information_categoryinfo_id;
	}

}
