package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 俱乐部图片
 * @author Administrator
 *
 */

@Entity
@Table(name = "clubImg")
public class ClubImg {
	private int id;				//编号
	private int clubId;			//俱乐部编号
	private String img;			//图片
	
	@Id
    @GeneratedValue
    @Column(name="id")
	public int getId() {
		return id;
	}
    @Column(name="clubId")
	public int getClubId() {
		return clubId;
	}
    @Column(name="img")
	public String getImg() {
		return img;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setClubId(int clubId) {
		this.clubId = clubId;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
	
	
}
