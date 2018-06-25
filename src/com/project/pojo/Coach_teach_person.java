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
 * 教练带人
 * @author lxc
 */
@Entity
@Table(name = "coach_teach_person")
public class Coach_teach_person  extends BaseEntity{
	private int id;
	private int people_num =1;		//人数(最多填4)
	private float price = 0;		//价格
	/**
	 * Constants.NORMAL_FLAG		
	 * Constants.DETELE_FLAG
	 */
    private int flag=1;    //标志位1 可用 0 停用
    private int coach_id;		//教练ID	
	
	
	
	
	
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

	@Column(name="people_num")
	public int getPeople_num() {
		return people_num;
	}

	public void setPeople_num(int people_num) {
		this.people_num = people_num;
	}
	@Column(name="price")
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	@Column(name="flag")
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Column(name="coach_id")
	public int getCoach_id() {
		return coach_id;
	}

	public void setCoach_id(int coach_id) {
		this.coach_id = coach_id;
	}

}
