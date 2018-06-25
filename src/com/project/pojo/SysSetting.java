package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统设置参数
 * 
 * @author lxc
 * 
 */
@Entity
@Table(name =  "sys_setting")
public class SysSetting {
	private int id;
	private String eml_account;
	private String eml_password;
	private String eml_host;
	private int eml_port=0;
	private String sms_signature;
	private String sms_cd_key;
	private String sms_password;
	private String update_time;
	private String sms_key;

    @Id
    @GeneratedValue
    @Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "eml_account")
	public String getEml_account() {
		return eml_account;
	}

	public void setEml_account(String eml_account) {
		this.eml_account = eml_account;
	}

	@Column(name = "eml_password")
	public String getEml_password() {
		return eml_password;
	}

	public void setEml_password(String eml_password) {
		this.eml_password = eml_password;
	}

	@Column(name = "eml_host")
	public String getEml_host() {
		return eml_host;
	}

	public void setEml_host(String eml_host) {
		this.eml_host = eml_host;
	}
	@Column(name = "eml_port")
	public int getEml_port() {
		return eml_port;
	}

	public void setEml_port(int eml_port) {
		this.eml_port = eml_port;
	}

	@Column(name = "sms_signature")
	public String getSms_signature() {
		return sms_signature;
	}

	public void setSms_signature(String sms_signature) {
		this.sms_signature = sms_signature;
	}

	@Column(name = "sms_cd_key")
	public String getSms_cd_key() {
		return sms_cd_key;
	}

	public void setSms_cd_key(String sms_cd_key) {
		this.sms_cd_key = sms_cd_key;
	}

	@Column(name = "sms_password")
	public String getSms_password() {
		return sms_password;
	}

	public void setSms_password(String sms_password) {
		this.sms_password = sms_password;
	}

	@Column(name = "update_time")
	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	@Column(name = "sms_key")
	public String getSms_key() {
		return sms_key;
	}

	public void setSms_key(String sms_key) {
		this.sms_key = sms_key;
	}

}
