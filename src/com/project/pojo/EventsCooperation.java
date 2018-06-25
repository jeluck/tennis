package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 活动合作
 * @author Administrator
 *
 */

@Entity
@Table(name = "eventsCooperation")
public class EventsCooperation {
	private int id;			//编号
	private String img;		//图片
	private String context; //内容
	private String flag;	//标识
	private int eventId;	//赛事编号	
	
    @Id
    @GeneratedValue
    @Column(name="id")
	public int getId() {
		return id;
	}
    
    @Column(name="img")
	public String getImg() {
		return img;
	}
    @Column(name="context")
    public String getContext() {
		return context;
	}
    @Column(name="flag")
    public String getFlag() {
		return flag;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name="eventId")
	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public EventsCooperation( String img, String context, String flag,
			int eventId) {
		super();
		this.img = img;
		this.context = context;
		this.flag = flag;
		this.eventId = eventId;
	}

	public EventsCooperation() {
		super();
	}
	
	
	
}
