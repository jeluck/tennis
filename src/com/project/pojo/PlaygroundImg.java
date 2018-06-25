package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 场馆图片表
 * 
 * @author lxc
 */
@Entity
@Table(name = "playgroundImg")
public class PlaygroundImg extends BaseEntity {
	private int id;				//编号
	private int pdId;			//场馆编号
	private String img;			//图片
	
	@Id
    @GeneratedValue
    @Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="pdId")
	public int getPdId() {
		return pdId;
	}
	public void setPdId(int pdId) {
		this.pdId = pdId;
	}
	
	@Column(name="img")
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
}
