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

import com.project.util.CommonUtil;


/**
 * 场地时间价格
 * 
 * @author lxc
 */
@Entity
@Table(name = "space_time_price")
public class Space_time_price  extends BaseEntity{
	private int id;
	private String date;//日期
	private int time;//'时间' ,
	private double price=0;// '价格' ,
	private Space space_id;//场地ID
	private int is_reserve=0;		//预订状态,1已预订,0未预订 2,不可预约' ,			Constants.IS_RESERVE0			Constants.IS_RESERVE1
	private int must_coach=2;// '是否需要教练,默认为2不用,1需要.' ,		Constants.MUST_COACH0			Constants.MUST_COACH1
	private String update_time=CommonUtil.getTimeNow();			//更新时间
	private String create_time=CommonUtil.getTimeNow();		//创建时间
	private Integer dateType;					//时间类型    1，表示工作日  2，表示节假日
	
	
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

	public void setId(int id) {
		this.id = id;
	}

    @Column(name="time")
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
    @Column(name="price")
	public double getPrice() {
		return price;
	}

	public void setPrice(double d) {
		this.price = d;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
	public Space getSpace_id() {
		return space_id;
	}

	public void setSpace_id(Space space_id) {
		this.space_id = space_id;
	}
	
    @Column(name="must_coach")
	public int getMust_coach() {
		return must_coach;
	}

	public void setMust_coach(int must_coach) {
		this.must_coach = must_coach;
	}

	@Column(name="is_reserve")
	public int getIs_reserve() {
		return is_reserve;
	}

	public void setIs_reserve(int is_reserve) {
		this.is_reserve = is_reserve;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Column(name = "dateType")
	public Integer getDateType() {
		return dateType;
	}
	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}
}
