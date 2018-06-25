package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/***
 * 俱乐部首页
 * @author Administrator
 *
 */
@Entity
@Table(name = "clubIndex")
public class ClubIndex {
	private int id;
	private String img;		//图片
	private String des;   	//描述
	
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
	@Column(name="des")
	public String getDes() {
		return des;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public void setDes(String des) {
		this.des = des;
	}
}
