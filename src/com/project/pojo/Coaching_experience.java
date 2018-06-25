package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "coaching_experience")
public class Coaching_experience {
	
	
	private int id;
	
	private int coachId;	//教练编号
	private String unitName;	//单位名称
	private String begin_time;	//开始时间
	private String end_time;	//结束时间
	
	@Id
	@GeneratedValue
	@Column(name="id" )
	public int getId() {
		return id;
	}
	@Column(name="coachId" )
	public int getCoachId() {
		return coachId;
	}
	@Column(name="unitName" )
	public String getUnitName() {
		return unitName;
	}
	@Column(name="begin_time" )
	public String getBegin_time() {
		return begin_time;
	}
	@Column(name="end_time" )
	public String getEnd_time() {
		return end_time;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCoachId(int coachId) {
		this.coachId = coachId;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	
}
