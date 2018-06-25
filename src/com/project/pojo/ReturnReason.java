package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 退回理由表
 * @author Administrator
 *
 */
@Entity
@Table(name = "returnReason")
public class ReturnReason {
	private int id;				//编号
	private int type;			//类型  1.教练	2.场馆
	private int s_id;			//编号
	private String reason;		//理由
	
	
	@Id
    @GeneratedValue
    @Column(name="id")
	public int getId() {
		return id;
	}
    @Column(name="type")
	public int getType() {
		return type;
	}
    @Column(name="s_id")
	public int getS_id() {
		return s_id;
	}
    
    @Column(name="reason")
	public String getReason() {
		return reason;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
}
