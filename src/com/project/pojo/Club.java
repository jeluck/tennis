package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.transaction.annotation.Transactional;

/***
 * 俱乐部
 * @author Administrator
 *
 */
@Entity
@Table(name = "club")
public class Club {
	private int id;				//编号
	private String name;		//名称
	private String context;     //内容
	
	private String img;  //临时变量 图片
	
	
	@Id
    @GeneratedValue
    @Column(name="id")
	public int getId() {
		return id;
	}
    @Column(name="name")
	public String getName() {
		return name;
	}
    @Column(name="context")
	public String getContext() {
		return context;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
	@Transient
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
}
