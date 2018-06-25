package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.util.CommonUtil;

/**
 * 补贴
 * @author Administrator
 *
 */
@Entity
@Table(name = "subsidies")
public class Subsidies  extends BaseEntity {
	private int id;
	private int zhe_id;	// 教练编号或者场馆编号
	private int type;	//1.月  Constants.SUBSIDIES_APRIL 2.季度    Constants.SUBSIDIES_QUARTER 3.半年   Constants.SUBSIDIES_HALFYEAR
	private float money = 0.01f;		//比例
	private int zhe_type;		// 1 教练  2 场馆
	private String year;		//补贴年份
	private String month;		//补贴月份
	private int status=1;					//状态  0为清算  1为没清算
	private double jmoney;					//有效交易额(收入额)
	private double subsidies_money;			//补贴额
	
	
	
	
	
	
	
	
	
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
	
	
	
	@Column(name = "money")
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
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
	
	@Column(name = "zhe_id")
	public int getZhe_id() {
		return zhe_id;
	}
	public void setZhe_id(int zhe_id) {
		this.zhe_id = zhe_id;
	}
	
	@Column(name = "type")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	@Column(name = "zhe_type")
	public int getZhe_type() {
		return zhe_type;
	}
	public void setZhe_type(int zhe_type) {
		this.zhe_type = zhe_type;
	}
	
	@Column(name = "year")
	public String getYear() {
		return year;
	}
	
	@Column(name = "month")
	public String getMonth() {
		return month;
	}
	
	@Column(name = "status")
	public int getStatus() {
		return status;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Column(name = "jmoney")
	public double getJmoney() {
		return jmoney;
	}
	@Column(name = "subsidies_money")
	public double getSubsidies_money() {
		return subsidies_money;
	}
	public void setJmoney(double jmoney) {
		this.jmoney = jmoney;
	}
	public void setSubsidies_money(double subsidies_money) {
		this.subsidies_money = subsidies_money;
	}
	
	
	
}
