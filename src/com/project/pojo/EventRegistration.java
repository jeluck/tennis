package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.util.CommonUtil;

/**
 * 赛事报名
 * @author Administrator
 *
 */
@Entity
@Table(name = "eventRegistration")
public class EventRegistration {
	private int id;					//编号
	private String name;			//姓名
	private String sex;				//性别
	private double money;			//报名费
	private String phone;			//手机号
	private String idcard_no;		//身份证号
	private String remark;  		//备注
	private Events eventsId;		//赛事
	
	
	
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
    @Column(name="id")
	public int getId() {
		return id;
	}
    @Column(name="name")
	public String getName() {
		return name;
	}
    @Column(name="sex")
	public String getSex() {
		return sex;
	}
    @Column(name="money")
	public double getMoney() {
		return money;
	}
    @Column(name="phone")
	public String getPhone() {
		return phone;
	}
    @Column(name="idcard_no")
	public String getIdcard_no() {
		return idcard_no;
	}
    @Column(name="remark")
	public String getRemark() {
		return remark;
	}
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventsId")
	public Events getEventsId() {
		return eventsId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setIdcard_no(String idcard_no) {
		this.idcard_no = idcard_no;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setEventsId(Events eventsId) {
		this.eventsId = eventsId;
	}
}
