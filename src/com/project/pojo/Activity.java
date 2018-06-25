package com.project.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.project.util.CommonUtil;

/**
 * 活动表
 * @author lxc
 *
 */
@Entity
@Table(name = "activity")
public class Activity  extends BaseEntity{
	private int id;
    private Weuser weuser;      //用户
	private String title;
	private String content;
	private String article_link;// '文章链接' ,
	private int readedcount=0;// '阅读数' ,
	private int commentcount=0;// '评论数' ,
	private int sharecount=0;// '分享次数' ,
	private Integer  manager_id;      //平台
	private Integer playground_id;//场馆编号
	private int authod_type;		//1用户,2平台,3场馆			Constants.AUTHOD_TYPE
	private Integer playgroundmanager_id=0;				//场馆管理者ID
	
	private int stick=0;				//是否置顶，0否，1是,			Constants.STICK1		Constants.STICK0
	private int sort_num=100;// 排序
	//临时变量
	private Manager manager;			//平台
	//临时变量	
	private PlaygroundManager playgroundManager;	//场馆管理者
	
	
	
	
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

	@Column(name = "sharecount")
	public int getSharecount() {
		return sharecount;
	}

	public void setSharecount(int sharecount) {
		this.sharecount = sharecount;
	}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weuser_id")
	public Weuser getWeuser() {
		return weuser;
	}

	public void setWeuser(Weuser weuser) {
		this.weuser = weuser;
	}
	
	@Column(name = "manager_id")
	public Integer getManager_id() {
		return manager_id;
	}

	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}


	@Column(name = "authod_type")
	public int getAuthod_type() {
		return authod_type;
	}

	public void setAuthod_type(int authod_type) {
		this.authod_type = authod_type;
	}

	
	
	@Transient
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	@Transient
	public PlaygroundManager getPlaygroundManager() {
		return playgroundManager;
	}
	public void setPlaygroundManager(PlaygroundManager playgroundManager) {
		this.playgroundManager = playgroundManager;
	}
	
	@Column(name="stick")
	public int getStick() {
		return stick;
	}
	public void setStick(int stick) {
		this.stick = stick;
	}
	
	@Column(name = "sort_num")
	public int getSort_num() {
		return sort_num;
	}

	public void setSort_num(int sort_num) {
		this.sort_num = sort_num;
	}
	@Column(name = "playground_id")
	public Integer getPlayground_id() {
		return playground_id;
	}
	public void setPlayground_id(Integer playground_id) {
		this.playground_id = playground_id;
	}
	
	@Column(name="playgroundmanager_id")
	public Integer getPlaygroundmanager_id() {
		return playgroundmanager_id;
	}

	public void setPlaygroundmanager_id(Integer playgroundmanager_id) {
		this.playgroundmanager_id = playgroundmanager_id;
	}
}
