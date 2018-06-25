package com.project.pojo;

import javax.persistence.Column;

import com.project.util.CommonUtil;

/**
 * 公有字段类
 * @author lxc
 * @createtime 2015-10-12
 * @updatetime 2015-10-12
 */
public class BaseEntity {
	/**
	 * 更新时间
	 */
	public String update_time;
	/**
	 * 创建时间
	 */
	public String create_time;

	public BaseEntity() {
		this.update_time = CommonUtil.getTimeNow();
		this.create_time = CommonUtil.getTimeNow();
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
