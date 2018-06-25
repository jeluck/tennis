package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.util.CommonUtil;

@Entity
@Table(name = "user_vip")
public class User_vip {
	private int id;
	private String name;	//'等级名称'
	private String img;		//图片
	private double price;	//价格
	
	private String update_time=CommonUtil.getTimeNow();			//更新时间
	private String create_time=CommonUtil.getTimeNow();		//创建时间
	@Column(name = "update_time")
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	@Column(name = "create_time")
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	@Id
    @GeneratedValue
	@Column(name = "id" )
	public int getId() {
		return id;
	}
	@Column(name = "name" )
	public String getName() {
		return name;
	}
	@Column(name = "img" )
	public String getImg() {
		return img;
	}
	@Column(name = "price" )
	public double getPrice() {
		return price;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
