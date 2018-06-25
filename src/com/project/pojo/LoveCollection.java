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
 * 收藏
 * @author lxc
 *
 */
@Entity
@Table(name = "collection")
public class LoveCollection   extends BaseEntity {
	private int id;
    private Weuser weuser;      //用户,收藏者ID
    private int orderinfo_id;	//订单ID,暂时无用
    private String dataID;			//可收藏的数据ID:场馆ID,教练ID,活动ID,培训/课程ID,赛事等' ,
    private int collectionType;			//1场馆,2教练,3活动,4课程/培训,5赛事			Constants.DATATYPE
	
	
	
	
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weuser_id")
	public Weuser getWeuser() {
		return weuser;
	}

	public void setWeuser(Weuser weuser) {
		this.weuser = weuser;
	}


    @Column(name="orderinfo_id")
	public int getOrderinfo_id() {
		return orderinfo_id;
	}

	public void setOrderinfo_id(int orderinfo_id) {
		this.orderinfo_id = orderinfo_id;
	}
	public String getDataID() {
		return dataID;
	}
	public void setDataID(String dataID) {
		this.dataID = dataID;
	}
	public int getCollectionType() {
		return collectionType;
	}
	public void setCollectionType(int collectionType) {
		this.collectionType = collectionType;
	}


}
