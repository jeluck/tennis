package com.project.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.util.CommonUtil;

/**
 * 资质证书
 * @author Administrator
 *
 */
@Entity
@Table(name = "qualification_certificate")
public class Qualification_certificate extends BaseEntity {
	private int id;
	private int type;	// 1.教练   Constants.CERTIFICATE_COACH  2.场馆    Constants.CERTIFICATE_VENUE 
	
	private String ph1=""; //证书图片1		(当为场馆,身份证正面)
	private String ph2=""; //证书图片2		(当为场馆,身份证反面)
	private String ph3=""; //证书图片3		(当为场馆,身份证持证照)
	private String ph4=""; //证书图片4
	private String ph5=""; //证书图片5
	private String ph6=""; //证书图片6
	private String ph7=""; //证书图片7		(当为教练,身份证正面)
	private String ph8=""; //证书图片8		(当为教练,身份证反面)
	private String ph9=""; //证书图片9		(当为教练,身份证持证照)
	private String ph10=""; //证书图片10
	
	
	private String update_time=CommonUtil.getTimeNow();			//更新时间
	private String create_time=CommonUtil.getTimeNow();		    //创建时间
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
	@Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "type")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	@Column(name = "ph1")
	public String getPh1() {
		return ph1;
	}
	public void setPh1(String ph1) {
		this.ph1 = ph1;
	}
	@Column(name = "ph2")
	public String getPh2() {
		return ph2;
	}
	public void setPh2(String ph2) {
		this.ph2 = ph2;
	}
	
	@Column(name = "ph3")
	public String getPh3() {
		return ph3;
	}
	public void setPh3(String ph3) {
		this.ph3 = ph3;
	}

	@Column(name = "ph4")
	public String getPh4() {
		return ph4;
	}
	public void setPh4(String ph4) {
		this.ph4 = ph4;
	}
	
	@Column(name = "ph5")
	public String getPh5() {
		return ph5;
	}
	public void setPh5(String ph5) {
		this.ph5 = ph5;
	}
	
	@Column(name = "ph6")
	public String getPh6() {
		return ph6;
	}
	public void setPh6(String ph6) {
		this.ph6 = ph6;
	}
	
	@Column(name = "ph7")
	public String getPh7() {
		return ph7;
	}
	public void setPh7(String ph7) {
		this.ph7 = ph7;
	}
	
	@Column(name = "ph8")
	public String getPh8() {
		return ph8;
	}
	public void setPh8(String ph8) {
		this.ph8 = ph8;
	}
	
	@Column(name = "ph9")
	public String getPh9() {
		return ph9;
	}
	public void setPh9(String ph9) {
		this.ph9 = ph9;
	}
	
	@Column(name = "ph10")
	public String getPh10() {
		return ph10;
	}
	public void setPh10(String ph10) {
		this.ph10 = ph10;
	}
	
	
	
	
	
}
