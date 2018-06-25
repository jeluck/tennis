package com.project.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "pm_admin_role_user")
public class PlaygroundManagerAdminRoleUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String role_code;// 角色编码
	private int playgroundmanager_id;// 
	private int cooperative_partner_id;			//合作商ID,作用:区分是哪个合作商

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

	
	@Column(name="cooperative_partner_id")
	public int getCooperative_partner_id() {
		return cooperative_partner_id;
	}

	public void setCooperative_partner_id(int cooperative_partner_id) {
		this.cooperative_partner_id = cooperative_partner_id;
	}

	@Column(name="playgroundmanager_id")
	public int getPlaygroundmanager_id() {
		return playgroundmanager_id;
	}

	public void setPlaygroundmanager_id(int playgroundmanager_id) {
		this.playgroundmanager_id = playgroundmanager_id;
	}


}
