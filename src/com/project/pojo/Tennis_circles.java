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
 * 网球圈
 * @author lxc
 *
 */
@Entity
@Table(name = "tennis_circles")
public class Tennis_circles   extends BaseEntity {
	private int id;
    private Weuser weuser;      //用户
	private String title="";
	private String content="";
	private String   article_link="";// '文章链接' ,
	private int readedcount =0;// '阅读数' ,
	private int commentcount =0;// '评论数' ,
	private int praisecount =0 ;// '点赞数' ,
	private int sharecount =0;// '分享次数' ,
	
	
	
	
	
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
    @JoinColumn(name = "weuser_id")
	public Weuser getWeuser() {
		return weuser;
	}

	public void setWeuser(Weuser weuser) {
		this.weuser = weuser;
	}

	@Column(name = "article_link")
	public String getArticle_link() {
		return article_link;
	}

	public void setArticle_link(String article_link) {
		this.article_link = article_link;
	}

	@Column(name = "readedcount")
	public int getReadedcount() {
		return readedcount;
	}

	public void setReadedcount(int readedcount) {
		this.readedcount = readedcount;
	}

	@Column(name = "commentcount")
	public int getCommentcount() {
		return commentcount;
	}

	public void setCommentcount(int commentcount) {
		this.commentcount = commentcount;
	}

	@Column(name = "praisecount")
	public int getPraisecount() {
		return praisecount;
	}

	public void setPraisecount(int praisecount) {
		this.praisecount = praisecount;
	}

	@Column(name = "sharecount")
	public int getSharecount() {
		return sharecount;
	}

	public void setSharecount(int sharecount) {
		this.sharecount = sharecount;
	}
}
