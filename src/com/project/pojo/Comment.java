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
 * comment
 * @author daybreak
 *
 */
@Entity
@Table(name = "comment")
public class Comment   extends BaseEntity {
	private int id;
    private Weuser weuser;      //用户,评论者ID
    private Integer orderinfo_id;	//订单ID
    private String activeID;			//可评论的数据ID:场馆ID,教练ID,活动ID,培训/课程ID,赛事等' ,
    private int orderType;			//1场馆,2教练,3活动,4课程/培训,5赛事			Constants.DATATYPE
    private String commentcontent;//评论内容,(不下订单消费,也可以评论场馆,教练,活动,培训/课程/赛事,分享等等,但是不能打分)
    private String evaluate_score;//'评价分数(几颗星星)'
	private String orderMainNo;	//主订单编号
	
	
	
	@Column(name="orderMainNo")
	public String getOrderMainNo() {
		return orderMainNo;
	}
	public void setOrderMainNo(String orderMainNo) {
		this.orderMainNo = orderMainNo;
	}

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weuser_id")
	public Weuser getWeuser() {
		return weuser;
	}

	public void setWeuser(Weuser weuser) {
		this.weuser = weuser;
	}

    @Column(name="activeid")
	public String getActiveID() {
		return activeID;
	}

	public void setActiveID(String activeID) {
		this.activeID = activeID;
	}

    @Column(name="ordertype")
	public int getOrderType() {
		return orderType;
	}
    public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
    @Column(name="commentcontent")
	public String getCommentcontent() {
		return commentcontent;
	}

	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}

	@Column(name="orderinfo_id")
	public Integer getOrderinfo_id() {
		return orderinfo_id;
	}
	public void setOrderinfo_id(Integer orderinfo_id) {
		this.orderinfo_id = orderinfo_id;
	}
	@Column(name="evaluate_score")
	public String getEvaluate_score() {
		return evaluate_score;
	}

	public void setEvaluate_score(String evaluate_score) {
		this.evaluate_score = evaluate_score;
	}

}
