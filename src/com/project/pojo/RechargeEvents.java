package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 充值活动表
 * @author Administrator
 *
 */
@Entity
@Table(name = "rechargeEvents")
public class RechargeEvents {
	
	
	private int id;					//编号
	private double recharge_money;	//充值金额
	private double get_money;	//赠送金额
	private int is_vip;			//会员特权  1.是    2.否
	private String begin_time;	//开始时间
	private String end_time;	//结束时间
	
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public int getId() {
		return id;
	}
	@Column(name="recharge_money")
	public double getRecharge_money() {
		return recharge_money;
	}
	@Column(name="get_money")
	public double getGet_money() {
		return get_money;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setRecharge_money(double recharge_money) {
		this.recharge_money = recharge_money;
	}
	public void setGet_money(double get_money) {
		this.get_money = get_money;
	}
	
	
	@Column(name="begin_time")
	public String getBegin_time() {
		return begin_time;
	}
	
	@Column(name="end_time")
	public String getEnd_time() {
		return end_time;
	}
	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	@Column(name="is_vip")
	public int getIs_vip() {
		return is_vip;
	}
	public void setIs_vip(int is_vip) {
		this.is_vip = is_vip;
	}
	
}
