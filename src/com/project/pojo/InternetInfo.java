package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 站点信息
 * 
 * @author lxc
 * 
 */
@Entity
@Table(name =  "internet_info")
public class InternetInfo {
	
	private int id;
	private String internet_name;// 站点名称
	private String company_name;// 公司名称
	private String postal_code;// 邮政编码
	private String company_address;// 公司地址
	private String principal;// 负责人
	private String linkman;// 联系人
	private String telphone;// 电话号码
	private String mobilephone;// 手机号码
	private String faxes;// 传真
	private String company_email;// 公司邮箱
	private String enterprise_qq;// 企业QQ
	private String service_phone;// 服务电话
	private String icp_number;// ICP证书号
	private String internet_domain;// 站点域名
    /**
     * 更新时间
     */
    private String update_time;
    /**
     * 创建时间
     */
    private String create_time;

    
    @Id
    @GeneratedValue
    @Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "internet_name")
	public String getInternet_name() {
		return internet_name;
	}

	public void setInternet_name(String internet_name) {
		this.internet_name = internet_name;
	}

	@Column(name = "company_name")
	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	@Column(name = "postal_code")
	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	@Column(name = "company_address")
	public String getCompany_address() {
		return company_address;
	}

	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}

	@Column(name = "principal")
	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	@Column(name = "linkman")
	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	@Column(name = "telphone")
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "mobilephone")
	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	@Column(name = "faxes")
	public String getFaxes() {
		return faxes;
	}

	public void setFaxes(String faxes) {
		this.faxes = faxes;
	}

	@Column(name = "company_email")
	public String getCompany_email() {
		return company_email;
	}

	public void setCompany_email(String company_email) {
		this.company_email = company_email;
	}

	@Column(name = "enterprise_qq")
	public String getEnterprise_qq() {
		return enterprise_qq;
	}

	public void setEnterprise_qq(String enterprise_qq) {
		this.enterprise_qq = enterprise_qq;
	}

	@Column(name = "service_phone")
	public String getService_phone() {
		return service_phone;
	}

	public void setService_phone(String service_phone) {
		this.service_phone = service_phone;
	}

	@Column(name = "icp_number")
	public String getIcp_number() {
		return icp_number;
	}

	public void setIcp_number(String icp_number) {
		this.icp_number = icp_number;
	}

	@Column(name = "internet_domain")
	public String getInternet_domain() {
		return internet_domain;
	}

	public void setInternet_domain(String internet_domain) {
		this.internet_domain = internet_domain;
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
}
