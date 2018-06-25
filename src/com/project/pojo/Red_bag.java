package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.util.CommonUtil;


/**
 * 后台发布红包
 * @author daybreak
 * 
 */
@Entity
@Table(name = "red_bag")
public class Red_bag extends BaseEntity{
	private int id;
	private int status;//'状态,1有效,0无效' ,			Constants.NORMAL_FLAG		Constants.DETELE_FLAG
	private int reward_type;// '红包类型:1代金券,2现金,3积分' ,			Constants.RewardType
	private int quantity;// '数量或者金额' ,
	private int purpose;//'作用或者用途:1支付后随机奖励,2支付后固定奖励积分,3登录奖励,4评论奖励,等等' ,		Constants.RedBagPurpose
	private String start_time;// 开始有效期
	private String end_time;// 结束有效期
	
	
	
	
	
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
	public Red_bag() {
	}

	public Red_bag(int ad_id) {
		this.id = ad_id;
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


	@Column(name = "start_time")
	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	@Column(name = "end_time")
	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	
	@Column(name="status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@Column(name="reward_type")
	public int getReward_type() {
		return reward_type;
	}

	public void setReward_type(int reward_type) {
		this.reward_type = reward_type;
	}
	
	@Column(name="quantity")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Column(name="purpose")
	public int getPurpose() {
		return purpose;
	}

	public void setPurpose(int purpose) {
		this.purpose = purpose;
	}
}
