package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.project.util.CommonUtil;


/**
 * 教练可设置预约时间表
 * @author lxc
 */
@Entity
@Table(name = "coach_set_time")
public class Coach_set_time  extends BaseEntity {
	private int id;
	private int time ;		//小时,6点,7点,8点,9点,10点.....等等
	/**
	 * Constants.NORMAL_FLAG		
	 * Constants.DETELE_FLAG
	 */
    private int flag;    //标志位1 可用 0 停用
    private Coach coach_id;		//教练ID	
    private Integer time_type;		//判断是工作日还是节假日1，工作日 2节假日
    private double price=0d;	//价格	
    private String date;			//年月日辅助列
    @Transient
    private Integer status;			//辅助列，判断是预约了还是没预约还是不能预约 0为不可预约 1为可预约 2为预约
    private String orderInfoNo;		//辅助列，子订单编号
    @Transient
    public String getOrderInfoNo() {
		return orderInfoNo;
	}
	public void setOrderInfoNo(String orderInfoNo) {
		this.orderInfoNo = orderInfoNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	private Integer space_id=0;   // 0.为休息 1.工作    edit 2015-12-19	nantian
    
    @Column(name = "space_id")
    public Integer getSpace_id() {
		return space_id;
	}
	public void setSpace_id(Integer space_id) {
		this.space_id = space_id;
	}
    @Column(name = "time")
    public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	@Transient
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Column(name = "time_type")
	public Integer getTime_type() {
		return time_type;
	}
	public void setTime_type(Integer time_type) {
		this.time_type = time_type;
	}

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

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="flag")
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coach_id")
	public Coach getCoach_id() {
		return coach_id;
	}

	public void setCoach_id(Coach coach_id) {
		this.coach_id = coach_id;
	}

	
	@Column(name="price")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	

}
