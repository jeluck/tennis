package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 模块（首页中间显示六个模块）
 * @author Administrator
 *
 */
@Entity
@Table(name = "module")
public class Module {
	private int id;					//编号
	private String address="";			//地址
	private String img="";				//图片	
	private int status=2;				//状态  1为显示   Constants.MODULE_SHOW    2为不显示 Constants.MODULE_NONE			
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public int getId() {
		return id;
	}
	
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	
	@Column(name="img")
	public String getImg() {
		return img;
	}
	
	@Column(name="status")
	public int getStatus() {
		return status;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
