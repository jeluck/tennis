package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 教练日程表，用于判断能否预约，在这张表中的数据都不可预约
 * @author HeZhai
 *
 */
@Entity
@Table(name = "schedule")
public class Schedule {
	@Id
    @GeneratedValue
    @Column(name="id")
	private Integer id;
	@Column(name="timepoint")
	private Integer timepoint;
	@Column(name="week")
	private String week;				//星期
	@Column(name="activeId")
	private Integer activeId;			//1,表示场地ID 2,表示教练ID
	@Column(name="belong")
	private Integer belong;				//1,表示场地 2,表示教练
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTimepoint() {
		return timepoint;
	}
	public void setTimepoint(Integer timepoint) {
		this.timepoint = timepoint;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public Integer getActiveId() {
		return activeId;
	}
	public void setActiveId(Integer activeId) {
		this.activeId = activeId;
	}
	public Integer getBelong() {
		return belong;
	}
	public void setBelong(Integer belong) {
		this.belong = belong;
	}
}
