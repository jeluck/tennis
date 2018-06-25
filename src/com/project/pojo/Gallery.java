package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 活动集锦
 * @author Administrator
 *
 */
@Entity
@Table(name = "gallery")
public class Gallery {
	private int id;			//编号
	private String img;		//图片
	private int type;		//类型   1.俱乐部   2.学校
	private int acId;		//所属编号
	
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
    @Column(name="type")
	public int getType() {
		return type;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	@Column(name="acId")
	public int getAcId() {
		return acId;
	}
	public void setAcId(int acId) {
		this.acId = acId;
	}
	
	
}
