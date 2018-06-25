package com.project.pojo;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.project.util.SecurityUtil;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * Manager 管理员实体类
 * @author lxc
 *
 */
@Entity
@Table(name = "manager")
@JsonIgnoreProperties(value={"admin_role_user"})
public class Manager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;// 用户名
	private String usercode;// 登录名称
	private String password;// 用户密码
	private String mobile;
	private String email;
	private String id_card;
	private int is_active;
	private String header;
	private Set<AdminRoleUser> admin_role_user = new HashSet<AdminRoleUser>(0);
	
	//临时变量
	private String encryptusercode;			//加密后的登录名称
	
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manager_id")
	public Set<AdminRoleUser> getAdmin_role_user() {
		return admin_role_user;
	}

	public void setAdmin_role_user(Set<AdminRoleUser> admin_role_user) {
		this.admin_role_user = admin_role_user;
	}

	@Transient
	public String getEncryptusercode() {
		return encryptusercode;
	}

	public void setEncryptusercode(String encryptusercode) {
		this.encryptusercode = encryptusercode;
	}
}
