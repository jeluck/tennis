package com.project.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 系统菜单实体
 * @author daybreak
 * 
 */
@Entity
@Table(name = "admin_menu")
public class AdminMenu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;//
	private String menu_name;//
	private String menu_code;//
	private String parent_menu_code;// 若为一级菜单，则此字段取值为：top,若为控制台，则取值：console，子菜单则为其父菜单的menu_code
	private String menu_desc;// 菜单描述
	private String menu_url;// 菜单地址
	private String menu_icon;//菜单图标
	private int weight;// 菜单排序权重，递增排序，最小的排在最前边
	private String menu_belong_to_role;			//菜单归属者,空为平台,	如果有Settings.MENU_BELONG_TO_ROLE_SON值,则表示合作商菜单
	
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

	@Column(name="menu_name")
	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	@Column(name="menu_code")
	public String getMenu_code() {
		return menu_code;
	}

	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}

	@Column(name="parent_menu_code")
	public String getParent_menu_code() {
		return parent_menu_code;
	}

	public void setParent_menu_code(String parent_menu_code) {
		this.parent_menu_code = parent_menu_code;
	}

	@Column(name="menu_desc")
	public String getMenu_desc() {
		return menu_desc;
	}

	public void setMenu_desc(String menu_desc) {
		this.menu_desc = menu_desc;
	}

	@Column(name="menu_url")
	public String getMenu_url() {
		return menu_url;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	
	@Column(name="menu_icon")
	public String getMenu_icon() {
		return menu_icon;
	}

	public void setMenu_icon(String menu_icon) {
		this.menu_icon = menu_icon;
	}

	@Column(name="weight" )
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
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
	
	@Column(name = "menu_belong_to_role")
	public String getMenu_belong_to_role() {
		return menu_belong_to_role;
	}

	public void setMenu_belong_to_role(String menu_belong_to_role) {
		this.menu_belong_to_role = menu_belong_to_role;
	}
}
