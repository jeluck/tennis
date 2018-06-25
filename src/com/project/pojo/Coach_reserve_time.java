package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 教练预定时间表
 * @author ZNT
 *
 */
@Entity
@Table(name = "coach_reserve_time")
public class Coach_reserve_time {
	private Integer id;
	private Integer coachId;
	private Integer hour;			//时间点 6,7,8,9等
	private String  date;			//时间 到年月日
	
	@Id
    @GeneratedValue
    @Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="coachId")
	public Integer getCoachId() {
		return coachId;
	}
	public void setCoachId(Integer coachId) {
		this.coachId = coachId;
	}
	
	@Column(name="hour")
	public Integer getHour() {
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	
	@Column(name="date")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
		
	
}