package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 补贴结算
 * @author Administrator
 *
 */
@Entity
@Table(name = "subsidiesSettlement")
public class SubsidiesSettlement {
	private int id;
	private int zhe_id;						//场馆编号或者教练编号
	private String create_time;				//加入时间
	private double subsidies_proportion;	//补贴比例
	private double money;					//有效交易额
	private double subsidies_money;			//补贴额
	private String orderMainNo;				//订单号
	private int status=1;					//状态  0为清算  1为没清算
	private int type;						// 1.教练  2.场馆
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	public int getId() {
		return id;
	}
	@Column(name = "zhe_id")
	public int getZhe_id() {
		return zhe_id;
	}
	@Column(name = "create_time")
	public String getCreate_time() {
		return create_time;
	}
	@Column(name = "subsidies_proportion")
	public double getSubsidies_proportion() {
		return subsidies_proportion;
	}
	@Column(name = "money")
	public double getMoney() {
		return money;
	}
	@Column(name = "subsidies_money")
	public double getSubsidies_money() {
		return subsidies_money;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setZhe_id(int zhe_id) {
		this.zhe_id = zhe_id;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public void setSubsidies_proportion(double subsidies_proportion) {
		this.subsidies_proportion = subsidies_proportion;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public void setSubsidies_money(double subsidies_money) {
		this.subsidies_money = subsidies_money;
	}
	@Column(name = "status")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Column(name = "orderMainNo")
	public String getOrderMainNo() {
		return orderMainNo;
	}
	public void setOrderMainNo(String orderMainNo) {
		this.orderMainNo = orderMainNo;
	}
	@Column(name = "type")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}
