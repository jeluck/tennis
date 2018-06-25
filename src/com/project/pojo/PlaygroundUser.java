package com.project.pojo;

import java.util.Date;

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
import com.project.util.SecurityUtil;


/***
 * 场馆用户表(只要下过单的就存在数据,不会重复)
 * 2015-12-29
 * @author lxc
 *
 */
@Entity
@Table(name = "playground_user")
public class PlaygroundUser   extends BaseEntity{
    private int    id;
	private Weuser user;			//用户ID
	private Playground playground;	//场馆ID

	
    
    
    
    
    
    
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
    
    @JoinColumn(name = "weuser_id")
    @ManyToOne(fetch = FetchType.LAZY)
	public Weuser getUser() {
		return user;
	}
	public void setUser(Weuser user) {
		this.user = user;
	}
	
    @JoinColumn(name = "playground_id")
    @ManyToOne(fetch = FetchType.LAZY)
	public Playground getPlayground() {
		return playground;
	}
	public void setPlayground(Playground playground) {
		this.playground = playground;
	}

}
