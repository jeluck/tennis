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
 * 网球圈评论
 * @author lxc
 *
 */
@Entity
@Table(name = "tennis_cricles_comment")
public class Tennis_cricles_comment   extends BaseEntity {
	private int id;
    private Weuser weuser;      //用户,评论者ID
    private int tennis_cricles_id;//'网球圈ID' 
    private String comment;			//
    private Weuser reply_id;// '回复对方,对方ID' ,
	
	
	
	
	
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
	
    @Column(name="tennis_cricles_id")
	public int getTennis_cricles_id() {
		return tennis_cricles_id;
	}

	public void setTennis_cricles_id(int tennis_cricles_id) {
		this.tennis_cricles_id = tennis_cricles_id;
	}
	
    @Column(name="comment")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_id")
	public Weuser getReply_id() {
		return reply_id;
	}

	public void setReply_id(Weuser reply_id) {
		this.reply_id = reply_id;
	}
}
