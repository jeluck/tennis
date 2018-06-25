package com.project.pojo;

import javax.persistence.*;


import com.project.util.CommonUtil;
import com.project.util.SecurityUtil;

import java.io.Serializable;


/**
 * 场馆管理者
 * @author lxc
 *
 */
@Entity
@Table(name = "playgroundmanager")
public class PlaygroundManager   extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;// 用户名
	private String usercode;// 登录名称
	private String password;// 用户密码
    private double amount=0d;				//用户账户里的金额(可用余额):recharge+totalcommission
	private String mobile;
	private String email;
	private String id_card;
	private int is_active=0;					//1激活,0未激活
	private String header;
	private int pg_manager_type = 1; // 场馆管理者类型：// 1为场馆管理者，2场馆管理内的其他人（清洁，人事，行政等等）
	private Integer pgm_president; // 场馆管理者id
	private Integer coachid=0; // 教练表ID(后台注册的场馆管理者为生成一个教练数据)
	
	//临时变量
	private String encryptusercode;			//加密后的登录名称
	//临时变量
	private Coach coach; 					//教练对象
	
	
	
	
	
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
	
	@Column(name="username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="usercode")
	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
		this.encryptusercode = SecurityUtil.encrypt(usercode);
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="id_card")
	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	@Column(name="is_active")
	public int getIs_active() {
		return is_active;
	}

	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}

	@Column(name="header")
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	
	@Transient
	public String getEncryptusercode() {
		return encryptusercode;
	}

	public void setEncryptusercode(String encryptusercode) {
		this.encryptusercode = encryptusercode;
	}

	@Column(name="amount")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Column(name="pg_manager_type")
	public int getPg_manager_type() {
		return pg_manager_type;
	}
	public void setPg_manager_type(int pg_manager_type) {
		this.pg_manager_type = pg_manager_type;
	}
	
	@Column(name="pgm_president")
	public Integer getPgm_president() {
		return pgm_president;
	}
	public void setPgm_president(Integer pgm_president) {
		this.pgm_president = pgm_president;
	}
	
	@Column(name="coachid")
	public Integer getCoachid() {
		return coachid;
	}
	public void setCoachid(Integer coachid) {
		this.coachid = coachid;
	}
	
	@Transient
	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
}
