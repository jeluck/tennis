package com.project.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "admin_role_user")
public class AdminRoleUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String role_code;// 角色编码
	private int manager_id;// 

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

//	@ManyToOne
//	@JoinColumn(name="manager_id") 
//	public Manager getManager_id() {
//		return manager_id;
//	}
//
//	public void setManager_id(Manager manager_id) {
//		this.manager_id = manager_id;
//	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	@Column(name="manager_id")
	public int getManager_id() {
		return manager_id;
	}

//	@Column(columnName="uid")
//	public int getUid() {
//		return uid;
//	}
//
//	public void setUid(int uid) {
//		this.uid = uid;
//	}
	
	


}
