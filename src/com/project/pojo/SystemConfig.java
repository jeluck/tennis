package com.project.pojo;


import javax.persistence.*;

import com.project.common.Constants;

/**
 */
@Entity
@Table(name = "systemmgt_config")
public class SystemConfig implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String configName="";  //名称
	private String key="";			//键
	private String value="";		//值
	private String remark="";		//备注
	private int deleteFlag=1;	//删除标识		删除标识符  1 没有删除  0已删除		Constants.NORMAL_FLAG	Constants.DETELE_FLAG
	
	/**
	 * 更新时间
	 */
	protected String update_time;
	/**
	 * 创建时间
	 */
	protected String create_time;
	
	/** default constructor */
	public SystemConfig() {
	}

	
	
	public SystemConfig(int id, String key, String value, String remark) {
		super();
		this.id = id;
		this.key = key;
		this.value = value;
		this.remark = remark;
	}

    @Id
    @GeneratedValue
    @Column(name="id")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(name = "config_key")
	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	@Column(name = "config_value")
	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "delete_flag")
	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}


	@Column(name = "config_name")
	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
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