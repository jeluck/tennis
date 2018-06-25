package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.util.CommonUtil;

/***
 * 用户打球的水平等级
 * @author lxc
 *
 */
@Entity
@Table(name = "user_level")
public class User_level extends BaseEntity{
	private int id;
	private String level;//'等级(1.0,1.5,2.0,2.5,....)' ,
	private String name;// '等级名称' ,
	
	
	
	
	
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
	@Column(name = "id" )
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "level" )
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "name" )
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
