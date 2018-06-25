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
 * 申请驻场教练表
 * @author Administrator
 *
 */
@Entity
@Table(name = "apply")
public class Apply {
	private int id;
	private Integer playground_id=0;			//属于场馆
	private Integer playgroundmanager_id=0;		//场馆管理者
	private Coach coach;		//教练
	private String apyTime = "";			    //申请时间
	private int status = 1;							//申请状态  1.待审核  2.已拒绝  3.已通过

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
	
	@Column(name="playground_id")
	public Integer getPlayground_id() {
		return playground_id;
	}
	@Column(name="playgroundmanager_id")
	public Integer getPlaygroundmanager_id() {
		return playgroundmanager_id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coach")
	public Coach getCoach() {
		return coach;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPlayground_id(Integer playground_id) {
		this.playground_id = playground_id;
	}
	public void setPlaygroundmanager_id(Integer playgroundmanager_id) {
		this.playgroundmanager_id = playgroundmanager_id;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	
	@Column(name = "apyTime")
	public String getApyTime() {
		return apyTime;
	}
	public void setApyTime(String apyTime) {
		this.apyTime = apyTime;
	}
	@Column(name = "status")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
