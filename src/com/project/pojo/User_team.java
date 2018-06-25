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
 * user_team
 * @author LXC
 *
 */
@Entity
@Table(name = "user_team")
public class User_team  extends BaseEntity{
	private int id;
	private String  name;// '球队名称' ,
	private int user_id;//'创建者' ,
	/**
	 * Constants.CREATE_TEAM
	 * Constants.JOIN_TEAM
	 */
	private int type_level;	//'1为创建,2为加入' ,
	private Weuser join_user_id;//'加入用户ID' ,
	private User_team user_team_id;//球队ID
	
	
	
	
	
	
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

	@Column(name = "name" )
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "user_id" )
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Column(name = "type_level" )
	public int getType_level() {
		return type_level;
	}

	public void setType_level(int type_level) {
		this.type_level = type_level;
	}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "join_user_id")
	public Weuser getJoin_user_id() {
		return join_user_id;
	}

	public void setJoin_user_id(Weuser join_user_id) {
		this.join_user_id = join_user_id;
	}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_team_id")
	public User_team getUser_team_id() {
		return user_team_id;
	}

	public void setUser_team_id(User_team user_team_id) {
		this.user_team_id = user_team_id;
	}

}
