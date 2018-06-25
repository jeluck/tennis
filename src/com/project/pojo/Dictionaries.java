package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 字典表 （服务）
 * @author Administrator
 *
 */
@Entity
@Table(name = "dictionaries")
public class Dictionaries {
	private int id;           //编号
	private int type;		  //类型  1.场馆  2.教练
	private String service;	  //服务
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "service")
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	@Column(name = "type")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
