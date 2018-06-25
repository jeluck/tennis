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

import org.codehaus.jackson.annotate.JsonIgnore;

import com.project.util.CommonUtil;

/**
 * 发布的课程表
 * @author lxc
 *
 */
@Entity
@Table(name = "course")
public class Course  extends BaseEntity{
	private int id;
    private Weuser weuser;      //用户
	private String title="";
	private String content="";
	private int type;				//1课程,2培训		Constants.CourseType
	private String article_link="";// '文章链接' ,
	private int readedcount=0;// '阅读数' ,
	private int commentcount=0;// '评论数' ,
	private int sharecount=0;// '分享次数' ,
	private String image1="";
	private String image2="";
	private String image3="";
	private int city_show_id;//' ,关联region表的 region_id' ,
	private Integer  manager_id;      //平台
	private Integer playgroundManager_id;//场馆管理者
	private int authod_type;		//1用户,2平台,3场管理者			Constants.AUTHOD_TYPE
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weuser_id")
    @JsonIgnore
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

	@Column(name = "sharecount")
	public int getSharecount() {
		return sharecount;
	}

	public void setSharecount(int sharecount) {
		this.sharecount = sharecount;
	}

	@Column(name = "image1")
	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	@Column(name = "image2")
	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	@Column(name = "image3")
	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	@Column(name = "city_show_id")
	public int getCity_show_id() {
		return city_show_id;
	}

	public void setCity_show_id(int city_show_id) {
		this.city_show_id = city_show_id;
	}

	@Column(name = "type")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	@Column(name = "playgroundManager_id")
	public Integer getPlaygroundManager_id() {
		return playgroundManager_id;
	}

	public void setPlaygroundManager_id(Integer playgroundManager_id) {
		this.playgroundManager_id = playgroundManager_id;
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
}
