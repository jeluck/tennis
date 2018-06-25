package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 时间收费表
 * @author ZNT
 *
 */
@Entity
@Table(name = "timemoeny")
public class TimeMoeny {
	private int    id;
	private double price;			//价格
	private Space  space;			//对应场地对象
	private int    time_type;		//时间类型 1，工作日 2，节假日
	private int    hour;			//对应的时间点
	private int    help_filed=2;		//是否需要助教 1，需要 2，不需要
	private int    playground_id;	//场馆
	
	@Column(name="playground_id")
	public int getPlayground_id() {
		return playground_id;
	}
	public void setPlayground_id(int playground_id) {
		this.playground_id = playground_id;
	}
	@Id
    @GeneratedValue
    @Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "price")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
	@JsonIgnore
	public Space getSpace() {
		return space;
	}
	public void setSpace(Space space) {
		this.space = space;
	}
	
	@Column(name = "time_type")
	public int getTime_type() {
		return time_type;
	}
	public void setTime_type(int time_type) {
		this.time_type = time_type;
	}
	
	@Column(name = "hour")
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	@Column(name = "help_filed")
	public int getHelp_filed() {
		return help_filed;
	}
	public void setHelp_filed(int help_filed) {
		this.help_filed = help_filed;
	}
	
	
}
