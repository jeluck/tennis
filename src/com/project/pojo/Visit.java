package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 上门服务表
 * @author Administrator
 *
 */
@Entity
@Table(name = "visit")
public class Visit {
	
	private int id;			//ID
	private int coachId;	//教练编号
	private int viType;		//上门类型  1、区域   2、场馆  
	private int zhe_id;		//所属编号
	
	@Id
    @GeneratedValue
    @Column(name="id")
	public int getId() {
		return id;
	}
	@Column(name="coachId")
	public int getCoachId() {
		return coachId;
	}
    @Column(name="viType")
	public int getViType() {
		return viType;
	}
    @Column(name="zhe_id")
	public int getZhe_id() {
		return zhe_id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCoachId(int coachId) {
		this.coachId = coachId;
	}
	public void setViType(int viType) {
		this.viType = viType;
	}
	public void setZhe_id(int zhe_id) {
		this.zhe_id = zhe_id;
	}
	
	
	
}
