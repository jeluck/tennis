package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 营业时间表
 * @author lxc
 *
 */
@Entity
@Table(name = "business_time")
public class Business_time {
	private int id;
	private int start_time;					//开始时间
	private int end_time;					//结束时间
	private int dateType;					//时间类型    1，表示工作日  2，表示节假日
	private Playground playground;			//对应的场馆
	
	@Id
    @GeneratedValue
    @Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "start_time")
	public int getStart_time() {
		return start_time;
	}
	public void setStart_time(int start_time) {
		this.start_time = start_time;
	}
	
	@Column(name = "end_time")
	public int getEnd_time() {
		return end_time;
	}
	public void setEnd_time(int end_time) {
		this.end_time = end_time;
	}
	
	@Column(name = "dateType")
	public int getDateType() {
		return dateType;
	}
	public void setDateType(int dateType) {
		this.dateType = dateType;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playground_id")
	public Playground getPlayground() {
		return playground;
	}
	public void setPlayground(Playground playground) {
		this.playground = playground;
	}
	
}
