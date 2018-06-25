package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 教练预约表
 * @author HeZhai
 *
 */
@Entity
@Table(name = "coachreserve")
public class CoachReserve {
	private Integer id;
	private String date;
	private Integer timepoint;
	private Integer coachId;
	private String orderSubNo;
	@Column(name="orderSubNo")
	public String getOrderSubNo() {
		return orderSubNo;
	}
	public void setOrderSubNo(String orderSubNo) {
		this.orderSubNo = orderSubNo;
	}
	@Id
    @GeneratedValue
    @Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="date")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Column(name="timepoint")
	public Integer getTimepoint() {
		return timepoint;
	}
	public void setTimepoint(Integer timepoint) {
		this.timepoint = timepoint;
	}
	@Column(name="coachId")
	public Integer getCoachId() {
		return coachId;
	}
	public void setCoachId(Integer coachId) {
		this.coachId = coachId;
	}
	
	
}
