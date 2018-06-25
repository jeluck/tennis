package com.project.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "pm_admin_role")
public class PlaygroundManagerAdminRole implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String role_code;// 角色编码
	private String role_name;// 角色名称
	private int role_type;// 角色类型    1:系统角色（如：超级管理员） 2：用户自定义角色
	private String role_desc;
	private int cooperative_partner_id;			//合作商ID,作用:区分是哪个合作商
	
	/**
	 * 更新时间
	 */
	protected String update_time;
	/**
	 * 创建时间
	 */
	protected String create_time;
	@Id
	@GeneratedValue
	@Column(name="id" )
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="role_code")
	public String getRole_code() {
		return role_code;
	}

	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	@Column(name="role_name")
	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	@Column(name="role_type" )
	public int getRole_type() {
		return role_type;
	}

	public void setRole_type(int role_type) {
		this.role_type = role_type;
	}

	@Column(name="role_desc")
	public String getRole_desc() {
		return role_desc;
	}

	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}

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

	@Column(name = "cooperative_partner_id")
	public int getCooperative_partner_id() {
		return cooperative_partner_id;
	}

	public void setCooperative_partner_id(int cooperative_partner_id) {
		this.cooperative_partner_id = cooperative_partner_id;
	}
}
