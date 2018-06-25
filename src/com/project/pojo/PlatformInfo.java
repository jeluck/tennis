package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.util.CommonUtil;


/**
 * 平台信息介绍
 * 
 * @author lxc
 */
@Entity
@Table(name = "platforminfo")
public class PlatformInfo   extends BaseEntity{
	private int id;
	private String company_info;// 公司介绍
	private String contact_us;// 联系我们

	private String go_licai;
	private String go_jiekuan;
	private String anquan;
	
	private String agreement; //协议
	
	private String level_explain;		//等级说明
	
	
	@Column(name = "agreement")
	public String getAgreement() {
		return agreement;
	}
	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

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

	@Column(name = "company_info")
	public String getCompany_info() {
		return company_info;
	}

	public void setCompany_info(String company_info) {
		this.company_info = company_info;
	}

	@Column(name = "contact_us")
	public String getContact_us() {
		return contact_us;
	}

	public void setContact_us(String contact_us) {
		this.contact_us = contact_us;
	}

	@Column(name = "go_licai")
	public String getGo_licai() {
		return go_licai;
	}

	public void setGo_licai(String go_licai) {
		this.go_licai = go_licai;
	}

	@Column(name = "go_jiekuan")
	public String getGo_jiekuan() {
		return go_jiekuan;
	}

	public void setGo_jiekuan(String go_jiekuan) {
		this.go_jiekuan = go_jiekuan;
	}

	@Column(name = "anquan")
	public String getAnquan() {
		return anquan;
	}

	public void setAnquan(String anquan) {
		this.anquan = anquan;
	}
	
	@Column(name = "level_explain")
	public String getLevel_explain() {
		return level_explain;
	}
	public void setLevel_explain(String level_explain) {
		this.level_explain = level_explain;
	}
	
}
